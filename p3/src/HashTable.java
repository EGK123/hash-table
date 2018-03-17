
public class HashTable<K, V> implements HashTableADT<K, V> {
    /*
     * Instance variables and constructors
     */
    double loadFactor = 0.75;
    int arraySize;
    V[] hashTable;
    int itemCount = 0;

    @Override
    public V put(K key, V value) {
        // TODO: Implement put method - using efficient algorithm
        if (hashTable == null) {
            arraySize = 10;
            hashTable = new V[arraySize];
        }

        return null;
    }
    
    private int hashFunction(K key) {
        //FIXME: takes wrong parameter, must take key and output hashIndex
        return -99;
    }

    @Override
    public void clear() {
        // TODO: Implement this method
    }

    @Override
    public V get(K key) {
        // TODO: Implement the get method
        int index = hashFunction(key);
        return hashTable[index];
    }

    @Override
    public boolean isEmpty() {
        // TODO: Implement the method
        if (itemCount == 0) {
            return true;
        }
        return false;
    }

    @Override
    public V remove(K key) {
        // TODO: Implement the remove method
        return null;
    }

    @Override
    public int size() {
        // TODO: Implement this method
        return 0;
    }
}
