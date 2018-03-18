import java.util.NoSuchElementException;

public class HashTable<K, V> implements HashTableADT<K, V> {
    /*
     * Instance variables and constructors
     */
    double loadFactor = 0.75;
    int arraySize;
    V[] hashTable;
    int itemCount = 0;

    /**
    *
    * @param key : The key that goes into the hashtable
    * @param value: The Value associated with the key
    * @return value of the key added to the hashtable,
    *      throws NullPointerException if key is null
    */
    @Override
    public V put(K key, V value) {
        // TODO: Implement put method - using efficient algorithm
        if (key == null) {
            throw new NullPointerException();
        }
        V old = null;
        int index = hashFunction(key);
        if (hashTable[index] == null) {
            old = hashTable[index];
            hashTable[index] = value;
        } else {
            boolean insert = false;
            while (insert == false) {
                index += 1;
                if (hashTable[index] == null) {
                    old = hashTable[index];
                    hashTable[index] = value;
                    insert = true;
                }
            }
        }
        return old;
    }
    
    private int hashFunction(K key) {
        //FIXME: takes wrong parameter, must take key and output hashIndex
        
        return -99;
    }

    @Override
    public void clear() {
        // TODO: Implement this method
    }

    /**
     * @param key: The key for which the value is returned
     * @return The value associated with the key,
     *          else throws NoSuch Element Exception
     */
    @Override
    public V get(K key) {
        // TODO: Implement the get method
        int index = hashFunction(key);
        V value = hashTable[index];
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
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
