# In-Memory DB — Followup Questions & Extension Design

> These are extensions to propose if time remains in the interview.
> Each answer is written as you'd speak it to an interviewer, followed by the exact code changes required.

---

## Q1: How would you add transactions — BEGIN, COMMIT, ROLLBACK?

**How to propose it:**

> "Right now every insert is auto-committed — there's no way to group multiple writes and either commit or roll them back as a unit. To fix that, I'd introduce a snapshot-based transaction model. When `begin()` is called, I deep-copy the current state of every table into a snapshot. If `commit()` is called, I just discard the snapshot since the live data is already correct. If `rollback()` is called, I restore the live data from the snapshot. This gives us atomicity — either all the writes in a transaction land, or none of them do."
>
> "The trade-off is memory — we're keeping two copies of every table's rows during a transaction. For a production system I'd look at MVCC (Multi-Version Concurrency Control) or a Write-Ahead Log (WAL) instead, which are more memory-efficient, but for an in-memory interview scope this approach is clean and simple."

---

**Changes in `Table.java`** — add snapshot fields and three new methods:

```java
// Add these two fields to Table.java
private List<Row> snapshot;
private Integer snapshotAutoId;

// Call this when BEGIN is issued
protected void beginTransaction() {
    // Deep copy every row so mutations to live data don't affect the snapshot
    this.snapshot = this.rows.stream()
        .map(r -> new Row(r.getRowId(), new HashMap<>(r.getColumnData())))
        .collect(Collectors.toList());
    this.snapshotAutoId = this.autoIncrementId;
}

// Call this when COMMIT is issued — snapshot is no longer needed
protected void commit() {
    this.snapshot = null;
}

// Call this when ROLLBACK is issued — restore live data from snapshot
protected void rollback() {
    this.rows = this.snapshot;
    this.autoIncrementId = this.snapshotAutoId;
    this.snapshot = null;
}
```

---

**Changes in `Database.java`** — track transaction state and expose the three new public methods:

```java
// Add these two fields to Database.java
private boolean inTransaction = false;
private Set<String> dirtyTables = new HashSet<>(); // tracks which tables were touched

// Mark a table as dirty when it's mutated during a transaction
// Call this inside insertTableRows(), before delegating to table.insertRow()
private void markDirty(String tableName) {
    if (inTransaction) dirtyTables.add(tableName);
}

public void begin() {
    if (inTransaction) {
        System.out.println("A transaction is already in progress.");
        return;
    }
    inTransaction = true;
    // Snapshot every table that might be touched
    for (Table table : tableMap.values()) {
        table.beginTransaction();
    }
}

public void commit() {
    if (!inTransaction) {
        System.out.println("No active transaction to commit.");
        return;
    }
    for (String tableName : dirtyTables) {
        tableMap.get(tableName).commit();
    }
    inTransaction = false;
    dirtyTables.clear();
}

public void rollback() {
    if (!inTransaction) {
        System.out.println("No active transaction to rollback.");
        return;
    }
    for (String tableName : dirtyTables) {
        tableMap.get(tableName).rollback();
    }
    inTransaction = false;
    dirtyTables.clear();
}
```

---

## Q2: How would you add a secondary index to speed up column-value lookups?

**How to propose it:**

> "Right now `filterByColumnValue` does a full linear scan — O(n) every time. If that column is queried frequently, we can maintain an inverted index: a map from column value to the list of row IDs that have that value. On insert, we update the index in O(1). On query, instead of scanning all rows, we look up the index in O(1) and then fetch only the matching rows by their ID. The trade-off is that inserts and deletes become slightly more expensive because we need to keep the index in sync, and we use extra memory proportional to the number of distinct values in that column."

---

**Changes in `Table.java`** — add index structures and wire them into insert and query:

```java
// Add these two fields to Table.java
// Outer key = column name, inner key = column value, value = list of matching row IDs
private Map<String, Map<Object, List<Integer>>> indexes = new HashMap<>();

// Separate map for O(1) row fetch by ID (needed once we stop scanning rows list)
private Map<Integer, Row> rowById = new HashMap<>();

// Call this from Database when createIndex() is invoked
protected void createIndex(String columnName) {
    if (!columnMap.containsKey(columnName)) {
        System.out.println("Column " + columnName + " does not exist.");
        return;
    }
    Map<Object, List<Integer>> indexMap = new HashMap<>();
    // Backfill existing rows into the new index
    for (Row row : this.rows) {
        Object value = row.getColumnData().get(columnMap.get(columnName));
        indexMap.computeIfAbsent(value, k -> new ArrayList<>()).add(row.getRowId());
    }
    indexes.put(columnName, indexMap);
}

// Update insertRow() to also populate any existing indexes
protected void insertRow(Map<Column, Object> columnValues) {
    for (Column column : columnValues.keySet()) {
        if (!checkIfColumnExists(column.getColumnName())) return;
    }
    Integer rowId = getAutoIncrementId();
    Map<Column, Object> columnData = new HashMap<>(columnValues);
    Row row = new Row(rowId, columnData);
    this.rows.add(row);
    this.rowById.put(rowId, row); // keep the ID-based lookup in sync

    // Update every index that covers any of the inserted columns
    for (Map.Entry<Column, Object> entry : columnValues.entrySet()) {
        String colName = entry.getKey().getColumnName();
        if (indexes.containsKey(colName)) {
            indexes.get(colName)
                   .computeIfAbsent(entry.getValue(), k -> new ArrayList<>())
                   .add(rowId);
        }
    }
}

// Update getRecordsByColumnValue() to use the index when available
protected List<Row> getRecordsByColumnValue(Column column, Object value) {
    String colName = column.getColumnName();
    if (indexes.containsKey(colName)) {
        // O(1) index lookup
        List<Integer> rowIds = indexes.get(colName).getOrDefault(value, Collections.emptyList());
        return rowIds.stream().map(rowById::get).collect(Collectors.toList());
    }
    // Fall back to full scan if no index exists
    return this.rows.stream()
        .filter(row -> value.equals(row.getColumnData().get(column)))
        .collect(Collectors.toList());
}
```

---

**Changes in `Database.java`** — expose `createIndex` as a public method:

```java
public void createIndex(String tableName, String columnName) {
    if (!checkIfTableExists(tableName)) return;
    tableMap.get(tableName).createIndex(columnName);
    System.out.println("Index created on " + tableName + "." + columnName);
}
```

---

## Q3: How would you handle row-level locking for concurrent writes?

**How to propose it:**

> "Right now if two threads insert simultaneously, the only thing that's thread-safe is the auto-increment ID counter, because that method is `synchronized`. But if two threads try to update or delete the same row at the same time, we have a race condition. To fix that, I'd give each row its own `ReadWriteLock`. A read lock allows multiple readers concurrently — useful for select queries. A write lock is exclusive — only one writer at a time, and it blocks all readers. So a thread doing `updateRow` acquires the write lock on that specific row, while threads doing `filterByColumnValue` each acquire read locks. This way we're not locking the entire table for a single-row update."
>
> "If we also need to protect against concurrent inserts or the rows list itself being modified while being iterated, I'd add a table-level `ReadWriteLock` on top — reads on the list take a shared lock, inserts and deletes take an exclusive lock."

---

**New file `RowLock.java`:**

```java
package InMemoryDB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RowLock {
    // ReentrantReadWriteLock allows multiple concurrent readers OR one exclusive writer
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Lock readLock()  { return lock.readLock();  }
    public Lock writeLock() { return lock.writeLock(); }
}
```

---

**Changes in `Row.java`** — add a lock per row:

```java
public class Row {
    private Integer rowId;
    private Map<Column, Object> columnData;
    private final RowLock rowLock = new RowLock(); // one lock per row

    public Row(Integer rowId, Map<Column, Object> columnData) {
        this.rowId = rowId;
        this.columnData = columnData;
    }

    public RowLock getRowLock() { return rowLock; }

    // ... existing getters unchanged
}
```

---

**Changes in `Table.java`** — acquire the appropriate lock in read/write operations:

```java
// In getRecordsByColumnValue — acquire a read lock on each row before reading
protected List<Row> getRecordsByColumnValue(Column column, Object value) {
    List<Row> result = new ArrayList<>();
    for (Row row : this.rows) {
        Lock readLock = row.getRowLock().readLock();
        readLock.lock();
        try {
            Object columnValue = row.getColumnData().get(column);
            if (columnValue != null && columnValue.equals(value)) {
                result.add(row);
            }
        } finally {
            readLock.unlock(); // always release in finally to avoid deadlocks
        }
    }
    return result;
}

// In updateRow — acquire a write lock before modifying
protected void updateRow(int rowId, Column targetCol, Object newValue) {
    Row row = rowById.get(rowId);
    if (row == null) return;
    Lock writeLock = row.getRowLock().writeLock();
    writeLock.lock();
    try {
        row.getColumnData().put(targetCol, newValue);
    } finally {
        writeLock.unlock();
    }
}
```

---

## Q4: How would you add UPDATE and DELETE?

**How to propose it:**

> "These are pretty straightforward additions. For DELETE, I'd filter out rows that match the given condition using `removeIf` on the rows list — it's clean and avoids manual index tracking. For UPDATE, I'd iterate and mutate in place. The one thing I need to be careful about is keeping any secondary indexes in sync — if I delete a row that had an indexed value, I need to remove its ID from the index too. Same for update — remove the old value from the index and add the new one."

---

**Changes in `Table.java`** — add `deleteRows` and `updateRows`:

```java
// DELETE WHERE filterCol = filterVal
protected void deleteRows(Column filterCol, Object filterVal) {
    this.rows.removeIf(row -> {
        Object value = row.getColumnData().get(filterCol);
        if (value != null && value.equals(filterVal)) {
            rowById.remove(row.getRowId()); // keep ID map in sync
            removeFromIndexes(row);         // keep indexes in sync
            return true;
        }
        return false;
    });
}

// UPDATE SET targetCol = newValue WHERE filterCol = filterVal
protected void updateRows(Column filterCol, Object filterVal, Column targetCol, Object newValue) {
    for (Row row : this.rows) {
        if (filterVal.equals(row.getColumnData().get(filterCol))) {
            // Remove old index entry before changing the value
            String targetColName = targetCol.getColumnName();
            if (indexes.containsKey(targetColName)) {
                Object oldValue = row.getColumnData().get(targetCol);
                indexes.get(targetColName).getOrDefault(oldValue, Collections.emptyList())
                       .remove(row.getRowId());
            }
            row.getColumnData().put(targetCol, newValue);
            // Add new index entry after changing the value
            if (indexes.containsKey(targetColName)) {
                indexes.get(targetColName)
                       .computeIfAbsent(newValue, k -> new ArrayList<>())
                       .add(row.getRowId());
            }
        }
    }
}

// Helper — strip a row from all indexes (used in delete)
private void removeFromIndexes(Row row) {
    for (Map.Entry<String, Map<Object, List<Integer>>> indexEntry : indexes.entrySet()) {
        Column col = columnMap.get(indexEntry.getKey());
        Object value = row.getColumnData().get(col);
        if (value != null) {
            indexEntry.getValue().getOrDefault(value, Collections.emptyList())
                      .remove(row.getRowId());
        }
    }
}
```

---

**Changes in `Database.java`** — expose the two new public methods:

```java
public void deleteTableRows(String tableName, Column filterCol, Object filterVal) {
    if (!checkIfTableExists(tableName)) return;
    tableMap.get(tableName).deleteRows(filterCol, filterVal);
}

public void updateTableRows(String tableName, Column filterCol, Object filterVal,
                             Column targetCol, Object newValue) {
    if (!checkIfTableExists(tableName)) return;
    tableMap.get(tableName).updateRows(filterCol, filterVal, targetCol, newValue);
}
```

---

## Q5: How would you support multi-column WHERE with AND / OR?

**How to propose it:**

> "Right now `filterByColumnValue` only accepts a single column-value pair. To support `WHERE age = 28 AND name = 'Kim'` or `WHERE age = 25 OR salary > 10000`, I'd introduce a `Predicate<Row>` abstraction. Each condition becomes a predicate, and AND/OR are just predicate composition using Java's built-in `.and()` and `.or()` combinators. This way the query logic is entirely caller-controlled — the Table doesn't need to know anything about how conditions are combined, it just applies the predicate to each row."

---

**New file `Filter.java`:**

```java
package InMemoryDB;

import java.util.function.Predicate;

public class Filter {

    // Exact equality match on a column
    public static Predicate<Row> eq(Column col, Object val) {
        return row -> val.equals(row.getColumnData().get(col));
    }

    // Compose two predicates with AND
    public static Predicate<Row> and(Predicate<Row> a, Predicate<Row> b) {
        return a.and(b);
    }

    // Compose two predicates with OR
    public static Predicate<Row> or(Predicate<Row> a, Predicate<Row> b) {
        return a.or(b);
    }
}
```

---

**Changes in `Table.java`** — replace the single-column filter with a predicate-based query:

```java
// Old method removed. New general-purpose query method:
protected List<Row> query(Predicate<Row> filter) {
    return this.rows.stream().filter(filter).collect(Collectors.toList());
}
```

---

**Caller usage in `InMemoryDBDemo.java`:**

```java
// WHERE age = 28 AND name = "Kim"
Predicate<Row> condition = Filter.and(Filter.eq(age, 28), Filter.eq(name, "Kim"));
db.queryTable("Employee", condition);

// WHERE age = 25 OR salary = 12000
Predicate<Row> condition2 = Filter.or(Filter.eq(age, 25), Filter.eq(salary, 12000));
db.queryTable("Employee", condition2);
```

---

## Q6: How would you add a PRIMARY KEY constraint?

**How to propose it:**

> "A primary key has two rules: the value must be unique across all rows, and it cannot be null. I'd track which column is the primary key at table creation time, and on every insert I'd validate both rules before accepting the row. I'd also maintain a separate map from primary key value to row for O(1) lookup by primary key — that's essentially a free index you always get with a primary key."

---

**Changes in `Column.java`** — add a `isPrimaryKey` flag:

```java
public class Column {
    private String columnName;
    private ColumnTypeEnum columnType;
    private boolean isPrimaryKey; // new field

    public Column(String columnName, ColumnTypeEnum columnType, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isPrimaryKey = isPrimaryKey;
    }

    // Convenience constructor for non-PK columns — keeps existing code unbroken
    public Column(String columnName, ColumnTypeEnum columnType) {
        this(columnName, columnType, false);
    }

    public boolean isPrimaryKey() { return isPrimaryKey; }

    // ... existing getters unchanged
}
```

---

**Changes in `Table.java`** — enforce PK constraint on insert:

```java
// Add these two fields to Table.java
private Column primaryKeyColumn;
private Set<Object> primaryKeyValues = new HashSet<>(); // fast uniqueness check

// In constructor, detect PK column from the column list
public Table(String tableName, List<Column> columns) {
    this.autoIncrementId = 1;
    this.name = tableName;
    populateColumnMap(columns);
    for (Column col : columns) {
        if (col.isPrimaryKey()) {
            this.primaryKeyColumn = col;
            break;
        }
    }
}

// In insertRow(), add PK validation before inserting
protected void insertRow(Map<Column, Object> columnValues) {
    for (Column column : columnValues.keySet()) {
        if (!checkIfColumnExists(column.getColumnName())) return;
    }
    if (primaryKeyColumn != null) {
        Object pkValue = columnValues.get(primaryKeyColumn);
        if (pkValue == null) {
            System.out.println("Primary key column '" + primaryKeyColumn.getColumnName() + "' cannot be null.");
            return;
        }
        if (primaryKeyValues.contains(pkValue)) {
            System.out.println("Duplicate primary key value: " + pkValue);
            return;
        }
        primaryKeyValues.add(pkValue);
    }
    Integer rowId = getAutoIncrementId();
    this.rows.add(new Row(rowId, new HashMap<>(columnValues)));
}
```

---

## Complexity Summary

| Operation | Without Index | With Index |
|-----------|--------------|------------|
| `createTable` | O(k) — k = number of columns | O(k) |
| `insertRow` | O(k) | O(k) per indexed column |
| `filterByColumnValue` | O(n) — full scan | O(1) lookup + O(result size) |
| `deleteRows` | O(n) | O(n) + O(result) for index cleanup |
| `truncate` | O(1) | O(1) — clear list and indexes together |
| `dropTable` | O(1) | O(1) |
| Space | O(n × k) | O(n × k) + O(n) per indexed column |

> n = number of rows, k = number of columns per row
