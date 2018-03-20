/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          cs400_p3
// FILES:            HashTest.java
//                   PerformanceAnalysis.java
//                   HashTable.java
//TYPE OF TREE:      Hash Table
// USER:             Ege Kula Josh Stamn (jstamn@wisc.edu)
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// 
//
// March 19, 2018 
//////////////////////////// 80 columns wide //////////////////////////////////

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashTest {

	HashTableADT<String, String> table = new HashTable(11, .75);
	String expected = null;
	String actual = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01_one_insert_null_key() {
		HashTableADT<String, String> table = new HashTable(11, .75);
		boolean pass = false;
		try {
			table.put(null, "value");
		} catch (NullPointerException E) {
			pass = true;
		}
		
		if (pass == false)
			fail("did not throw null pointer when null key inserted");
	}
	
	@Test
	public void test02_insert_10_keys() {
		HashTableADT<Integer, String> table = new HashTable(11, .75);
		boolean pass = false;
		int count = 10;
		try {
			for (int i = 0; i < count; i++) {
				table.put(i, "value"+i);
			}
			pass = true;
			
		} catch (NullPointerException E) {
			pass = false;
		}
		
		if (pass == false)
			fail("failed to insert 10 keys");
	}
	
	@Test
	public void test03_insert_10_keys_remove_10_keys() {
		HashTableADT<Integer, String> table = new HashTable(11, .75);
		boolean pass = false;
		int count = 10;
		try {
			for (int i = 0; i < count; i++) {
				table.put(i, "value"+i);
			}
			for (int i = 0; i < count; i++) {
				table.remove(i);
			}
			pass = true;
			
		} catch (NullPointerException E) {
			pass = false;
		}
		
		if (pass == false)
			fail("failed to insert 10 keys");
	}
	
	@Test
	public void test04_one_insert_and_get() {
		HashTableADT<Integer, String> table = new HashTable(11, .75);
		boolean pass = false;
		try {
			table.put(888, "value");
			if (table.get(888) == "value") {
				pass = true;
			}
		} catch (NullPointerException E) {
			pass = false;
		}
		
		if (pass == false)
			fail("did not throw null pointer when null key inserted");
	}
	
	@Test
	public void test05_one_insert_and_get_string() {
		HashTableADT<String, String> table = new HashTable(11, .75);
		boolean pass = false;
		try {
			table.put("pleaseWork", "value");
			if (table.get("pleaseWork").equals("value")) {
				pass = true;
			}
		} catch (NullPointerException E) {
			pass = false;
		}
		
		if (pass == false)
			fail("did not throw null pointer when null key inserted");
	}
	
	@Test
	public void test06_insert_100_keys() {
		HashTableADT<Integer, String> table = new HashTable(11, .75);
		boolean pass = false;
		int count = 100;
		try {
			for (int i = 0; i < count; i++) {
				table.put(i, "value"+i);
			}
			pass = true;
			
		} catch (NullPointerException E) {
			pass = false;
		}

		if (pass == false)
			fail("failed to insert 100 keys");
	}
	
	@Test
	public void test07_insert_10000_keys() {
		HashTableADT<Integer, String> table = new HashTable(11, .75);
		boolean pass = false;
		int count = 10000;
		try {
			for (int i = 0; i < count; i++) {
				table.put(i, "value"+i);
			}
			pass = true;
			
		} catch (NullPointerException E) {
			pass = false;
		}

		if (pass == false)
			fail("failed to insert 100 keys");
	}
	
	@Test
	public void test08_insert_100_keys_get_3() {
		HashTableADT<Integer, String> table = new HashTable(11, .75);
		boolean pass = false;
		int count = 100;
		try {
			for (int i = 0; i < count; i++) {
				table.put(i, "value"+i);
			}
			if (table.get(50).equals("value50") && table.get(11).equals("value11") && table.get(99).equals("value99")) {
				pass = true;
			}
			
		} catch (NullPointerException E) {
			pass = false;
		}

		if (pass == false)
			fail("failed to insert 100 keys");
	}
	
	@Test
	public void test09_insert_100_strings() {
		HashTableADT<String, String> table = new HashTable(11, .75);
		boolean pass = false;
		int count = 100;
		try {
			for (int i = 0; i < count; i++) {
				table.put("String"+i, "value"+i);
				System.out.println(i);
			}
			pass = true;
			
		} catch (NullPointerException E) {
			pass = false;
		}

		if (pass == false)
			fail("failed to insert 100 string keys");
	}
}
