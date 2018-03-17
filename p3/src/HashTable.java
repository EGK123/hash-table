
public class HashTable<K, V> implements HashTableADT<K, V> {
    /*
     * Instance variables and constructors
     */
    double loadFactor = 0.75;
    V[] hashTable;
    int arraySize;
    int itemCount = 0;

    @Override
    public V put(K key, V value) {
        // TODO: Implement put method - using efficient algorithm

        return null;
    }
    
    private K hashFunction(V value) {
        K key = null;
        return key;
    }

    @Override
    public void clear() {
        // TODO: Implement this method
    }

    @Override
    public V get(K key) {
        // TODO: Implement the get method
        return null;
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
