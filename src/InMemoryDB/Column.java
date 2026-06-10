package InMemoryDB;

public class Column {

    private String columnName;
    private ColumnTypeEnum columnType;

    public Column(String columnName, ColumnTypeEnum columnType) {

        this.columnName = columnName;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return this.columnName;
    }
}