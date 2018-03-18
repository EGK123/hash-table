import java.util.NoSuchElementException;
import java.lang.Math;

public class HashTable<K, V> implements HashTableADT<K, V> {
	/*
	 * Instance variables and constructors
	 */
	double loadFactor;
	int arraySize;
	V[] hashTable;
	K[] keys;
	int itemCount = 0;

	HashTable(int initialCapacity, double loadFactor) {
	    this.loadFactor = loadFactor;
		arraySize = initialCapacity;
		hashTable = (V[]) new Object[arraySize];
		keys = (K[]) new Object[arraySize];
	}
	
	HashTable(int initialCapacity) {
	    loadFactor = 0.75;
        arraySize = initialCapacity;
        hashTable = (V[]) new Object[initialCapacity];
        keys = (K[]) new Object[initialCapacity];
	}

	/**
	 *
	 * @param key
	 *            : The key that goes into the hashtable
	 * @param value:
	 *            The Value associated with the key
	 * @return value of the key added to the hashtable, throws NullPointerException
	 *         if key is null
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
			keys[index] = key;
		} else {
			boolean insert = false;
			while (insert == false) {
				index += 1;
				if (hashTable[index] == null) {
					old = hashTable[index];
					hashTable[index] = value;
					keys[index] = key;
					insert = true;
				}
			}
		}
		if (itemCount / arraySize >= loadFactor) {
			increase(arraySize);
		}
		return old;
	}


	private int hashFunction(K key) {
	    int hashIndex;
	    try {
	        hashIndex = Integer.parseInt(key.toString()) % arraySize;
	    } catch (NumberFormatException E){
	        String k = key.toString();
	        int hash = 7;      //This and next two lines modified from a post on stackoverflow,
	        for (int i = 0; i < k.length(); i++) {     //source : https://stackoverflow.com/questions/2624192/good-hash-function-for-strings
	            hash = hash*31 + k.charAt(i);
	        }
	        hashIndex = hash % arraySize;
	    }
		return hashIndex;
	}
	
	/**
     * Clear the hashtable of all its contents
     */
	@Override
	public void clear() {
		// TODO: Implement this method
	    for (V element : hashTable) {
	        element = null;
	    }
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
		// this stuff gets the next prime number which will be the size of the new array

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
		// this stuff should make a temparray to hold the data then transfer it to the new bigger hashTable
		V[] tempArray = hashTable;
		int tempSize = arraySize;
		K[] tempKeys = keys;
		hashTable = (V[]) new Object[nextPrime];
		keys = (K[]) new Object[nextPrime];
//		int j = hashTable.get(12);
		arraySize = nextPrime;
		for (int i = 0; i < itemCount; i++) {
			put(tempKeys[i], get(tempKeys[i]));
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

	/**
    *
    * @param key: Key of the entry to be removed
    * @return value: Value of the key-value pair removed,
    *          null if key did not have a mapping
    * @throws NullPointerException if key is null
    */
	@Override
	public V remove(K key) {
	    if (key == null) {
	        throw new NullPointerException();
	    }
	    int index = hashFunction(key);
	    V value = hashTable[index];
	    
		return value;
	}

	/**
    * @return: The total number of entries in the hashtable
    */
	@Override
	public int size() {
		return itemCount;
	}
}
