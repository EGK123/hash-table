/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          cs400_p3
// FILES:            HashTest.java
//                   PerformanceAnalysis.java
//                   HashTable.java
//TYPE OF TREE:      Hash Table
// USER:             Ege Kula (ekula@wisc.edu) Josh Stamn (jstamn@wisc.edu)
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// 
//
// March 19, 2018 
//////////////////////////// 80 columns wide //////////////////////////////////

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
			itemCount += 1;
		} else {
			boolean insert = false;
			while (insert == false) {
				System.out.println("Index:"+ index);
				System.out.println("arraysize:"+ arraySize);
				if (index-1 < arraySize) {		//allows linear probing to loop
					index += 1;
				} else {
					index = 0;
				}
				
				if (hashTable[index] == null) {
					old = hashTable[index];
					hashTable[index] = value;
					keys[index] = key;
					insert = true;
					itemCount += 1;
				}
			}
		}
		System.out.println("ItemCount: " + itemCount);
		if (itemCount / arraySize >= loadFactor) {
			increase(arraySize);
		}
		return old;
	}

	/**
     * @param the key to be hashed
     * 
     * @return the hashIndex where this key belongs
     */
	private int hashFunction(K key) {
	    int hashIndex;
	    try {
	        hashIndex = Integer.parseInt(key.toString()) % arraySize;
	    } catch (NumberFormatException E){
	        String k = key.toString();
	        int hash = 7;      //This and next two lines modified from a post on stackoverflow,
	        for (int i = 0; i < k.length(); i++) {     //source : https://stackoverflow.com/questions/2624192/good-hash-function-for-strings
	            hash = Math.abs(hash*31 + k.charAt(i));
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
	    itemCount = 0;
	}

	/**
	 * @param key:The key for which the value is returned
	 *            
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
		try {
			boolean found = false;
		    while (found != true) {
			    if (keys[index].equals(key)) {
			    	value = hashTable[index];
			    	found = true;
			    } else {
			    	if (index - 1 < arraySize) {	// allows linear probing to loop
			    		index += 1; 
			    	} else {
			    		index = 0;
			    	}
			    	
			    }
		    }
		} catch (NullPointerException E) {
			throw new NoSuchElementException();
		}
		
		return value;
	}

	/**
<<<<<<< HEAD
	 * increase array size, and rehash keys. This is done to try to maximize the
	 * Evenness of the distribution of keys
=======
	 * increase array size to double the current number, and rehash keys. 
>>>>>>> eff4d858afd3b838434eec25ded4c8e2d8e4b436
	 * @param currentArraySize the current array size
	 */
	@SuppressWarnings("unchecked")
	private void increase(int currentArraySize) {
		int nextPrime = currentArraySize * 2;
		// this stuff should make a temparray to hold the data then transfer it to the new bigger hashTable
		V[] tempArray = hashTable;
		int tempSize = arraySize;
		K[] tempKeys = keys;
//		System.out.println("prime" + nextPrime);
		hashTable = (V[]) new Object[nextPrime];
		keys = (K[]) new Object[nextPrime];
//		int j = hashTable.get(12);
		arraySize = nextPrime;
//		System.out.println("*** arraySize" + arraySize);
		itemCount = 0;
		for (int i = 0; i < tempSize; i++) {
			if (tempKeys[i] != null) {
				put(tempKeys[i], tempArray[hashFunction(tempKeys[i])]);
			}
		}
	}

	/**
     * Checks if the hashtable is empty
     * @return true : if Empty, else False
     */
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
	    V value = null;
	    int index = hashFunction(key);
	    boolean removed = false;
	    while (removed != true) {
		    if (keys[index] == key) {
		    	keys[index] = null;
		    	value = hashTable[index];
		    	hashTable[index] = null;
		    	removed = true;
		    } else {
		    	index += 1; 
		    }
	    }
	    itemCount -= 1;

	    
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
