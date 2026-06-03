package HashMap;

public class MyHashMap<K,V> {
    private final static int MAX_CAPACITY = 1 << 30;
    private final static int SIZE = 1 << 4;

    private Entry[] hashTable;

    public MyHashMap(){
        hashTable = new Entry[SIZE];
    }

    public MyHashMap(int capacity){
        int maxSize = calculateHashTableSize(capacity);
        hashTable = new Entry[maxSize];
    }

    int calculateHashTableSize(int cap){
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAX_CAPACITY) ? MAX_CAPACITY : n + 1;
    }

    public class Entry<K,V>{
        public K key;
        public V value;
        public Entry next;

        public Entry(K k, V v){
            key = k;
            value = v;
        }
    }

    public void put(K key, V v){
        int hashCodeForKey = Math.floorMod(key.hashCode(), hashTable.length);
        Entry mapEntry = hashTable[hashCodeForKey];
        if(mapEntry == null){
            hashTable[hashCodeForKey] = new Entry(key, v);
            return;
        }
        while(mapEntry.next != null && mapEntry.key != key){
            mapEntry = mapEntry.next;
        }
        if(mapEntry.key == key){
            mapEntry.value = v;
            return;
        }
        Entry newNode = new Entry(key, v);
        mapEntry.next = newNode;
        return;
    }

    public V get(K key){
        int hashCodeForKey = Math.floorMod(key.hashCode(), hashTable.length);
        Entry mapEntry = hashTable[hashCodeForKey];
        while(mapEntry.next != null && mapEntry.key != key){
            mapEntry = mapEntry.next;
        }
        if(mapEntry == null) return null;
        return (V) mapEntry.value;
    }

    public static void main(String[] args){
        MyHashMap<String, String> hashmap = new MyHashMap<String, String>(1000);
        hashmap.put("Aryansh", "Kurmi");
        hashmap.put("Suraj", "Bhan");
        hashmap.put("Ishika", "Chaudhary");
        hashmap.put("Aryansh", "Chaudhary");
        hashmap.put("Suraj", "Jain");
        hashmap.put("SurajBhan", "Mundo");
        System.out.println(hashmap.get("Suraj"));
        System.out.println(hashmap.get("Ishika"));
        System.out.println(hashmap.get("Aryansh"));
        System.out.println(hashmap.get("SurajBhan"));
        return;
    }
}
