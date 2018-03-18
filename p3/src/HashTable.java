import java.util.NoSuchElementException;

public class HashTable<K, V> implements HashTableADT<K, V> {
	/*
	 * Instance variables and constructors
	 */
	double loadFactor = 0.75;
	int arraySize;
	V[] hashTable;
	K[] keys;
	int itemCount = 0;


	HashTable(int size) {
		arraySize = size;
		hashTable = (V[]) new Object[size];
		keys = (K[]) new Object[size];
	}

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
        if (itemCount/arraySize >= 0.75) {
            hashTable = tableExpand();
        }
        return old;
    }
 /*   
    private V[] tableExpand() {
        //FIXME: couldnt figure out generic array, will do research and fix 3/18
        arraySize = arraySize * 2;
        V[] newTable = (V[]) newTable[arraySize];
        return hashTable;
        
    }*/
    
    private int hashFunction(K key) {
        //FIXME: takes wrong parameter, must take key and output hashIndex
        
        return -99;
    }


	@Override
	public void clear() {
		// TODO: Implement this method
	}

	/**
	 * @param key:
	 *            The key for which the value is returned
	 * @return The value associated with the key, else throws NoSuch Element
	 *         Exception
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

	@SuppressWarnings("unchecked")
	private void increase(int currentArraySize) {
		boolean prime = false;
		int nextPrime = 0;
		int num = currentArraySize;
		for (num++; !prime; num++) {

			if (!(num % 2 == 0)) {
				prime = true;
				for (int i = 3; i * i <= num; i += 2) {
					if (!(num % i == 0) && prime) {
						prime = true;
						nextPrime = num;
					} else
						prime = false;

				}
			}

		}
		V[] tempArray = hashTable ;
		int tempSize = arraySize;
		hashTable = (V[]) new Object[nextPrime];
		int j = hashTable.get(12);
		arraySize = nextPrime;
		for(int i=0;i<itemCount;i++) {
			hashTable.put(keys[i], hashTable.get(keys[i]) );
		}
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
