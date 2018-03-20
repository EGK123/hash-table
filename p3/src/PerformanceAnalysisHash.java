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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;

public class PerformanceAnalysisHash implements PerformanceAnalysis {

    // The input data from each file is stored in this/ per file
    private ArrayList<String> inputData;
    long insertionTime;
    long deletionTime;
    long compareTime;
    long insertionTree;
	long deletionTree;
	long compareTree;
	long insertionTable;
    long deletionMemory;
    long compareTable;
    long insertionMemory;
	long deletionTable;
	long compareMemory;
	private ArrayList<String> paths;
	
    
    public PerformanceAnalysisHash(){
    }

    public PerformanceAnalysisHash(String details_filename) throws IOException{		
    	details_filename.replace('\\',File.separatorChar).replace('/',File.separatorChar);		//adapted from method found on stackoverflow 
    	ArrayList<String> toRead = new ArrayList<>();
    	paths = new ArrayList<>();
    	for (String line : Files.readAllLines(Paths.get(details_filename))) {	//can be found at : https://stackoverflow.com/questions/2788080/java-how-to-read-a-text-file
    		for (String part : line.split("\\s+")) {
    			toRead.add(part);
    		}
    	}
    	String filepath = toRead.get(0).split(",")[1];	//splits first line on the comma and keeps the second half
    	for (int i = 1; i < toRead.size(); i++) {
    		paths.add(toRead.get(i).split(",")[0]); 	//splits on comma and keeps first half as path 
    	}
    	for (int i = 1; i < paths.size(); i++) {
    		loadData(filepath + paths.get(i-1));
    	}

    }
    
    /**
     * The important function that compares the implemented HashTable with
     * TreeMap of Java and generates the table with all the comparision details
     * This can internally call - compareInsertion, compareDeletion, CompareSearch
     * for all the test data provided.
     */
    @Override
    public void compareDataStructures() {
        //TODO: Complete this function which compares the ds and generates the details
    	
    	
    	
    	compareInsertion();
    	compareDeletion();
    	compareSearch();
    	
    }

    /** Function used to print out the final report
    *
    */
    @Override
    public void printReport() {
        //TODO: Complete this method
    	System.out.println("The report name : Perfrmance Analysis Report");
    	System.out.println("------------------------------------------------------------------------------------------------");
    	System.out.println("|            FileName|      Operation| Data Structure|   Time Taken (micro sec)|     Bytes Used|");		
    	System.out.println("------------------------------------------------------------------------------------------------");


    		for (int i = 0; i < paths.size(); i++) {
    			String dataFile = paths.get(i);
    			try {
					loadData(dataFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		compareDataStructures();
    		
    		long bytes=0;
    		//	System.out.println("|"    +IntegerSmall.txt+"|"            +PUT+"|"      +HASHTABLE+"|"                   +1279.0+"|"             +0+"|");
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "PUT|", "HASHTABLE|", insertionTime +"|", insertionTable+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "PUT|", "TREEMAP|", insertionTree +"|", insertionMemory+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "GET|", "HASHTABLE|", compareTime +"|", compareTable+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "GET|", "TREEMAP|", compareTree +"|", compareMemory+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "REMOVE|", "HASHTABLE|", deletionTime +"|", deletionTable+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "REMOVE|", "TREEMAP|", deletionTree +"|", deletionMemory+"|" );
    		}
    	
    	
    		System.out.println("------------------------------------------------------------------------------------------------");
    }

    /**
     * Standalone method for comparing insertion operation
     * across HashTable and TreeMap
     */
    @Override
    public void compareInsertion() {
        //TODO: Complete this method
    	
    	TreeMap map = new TreeMap();
    	long startTreeMap = System.nanoTime();
    	Runtime runtimeTree = Runtime.getRuntime();
    	for (int i = 0; i < inputData.size(); i++) {
    		map.put(inputData.get(i), inputData.get(i));
    	}
    	long endTreeMap = System.nanoTime();
    	long timeTreeMap = endTreeMap - startTreeMap;
    	runtimeTree.gc();
    	insertionMemory = runtimeTree.totalMemory() - runtimeTree.freeMemory();
    	
    	Runtime runtimeTable = Runtime.getRuntime();
    	HashTable table = new HashTable(inputData.size(), .75);
    	long startHashTable = System.nanoTime();
    	for (int i = 0; i < inputData.size(); i++) {
    		table.put(inputData.get(i), inputData.get(i));
    	}
    	long endHashTable = System.nanoTime();
    	long timeHashTable = endHashTable - startHashTable;
    	runtimeTable.gc();
    	insertionTable = runtimeTable.totalMemory() - runtimeTable.freeMemory();

    		insertionTime = timeHashTable;
    		insertionTree = timeTreeMap;
    }

    /**
     * Standalone method for comparing deletion operation
     * across HashTable and TreeMap
     */
    @Override
    public void compareDeletion() {
        //TODO: Complete this method
    	TreeMap map = new TreeMap();
    	Runtime runtimeTree = Runtime.getRuntime();
    	HashTable table = new HashTable(inputData.size(), .75);
    	for (int i = 0; i < inputData.size(); i++) {
    		map.put(inputData.get(i), inputData.get(i));
    	}
    	for (int i = 0; i < inputData.size(); i++) {
    		table.put(inputData.get(i), inputData.get(i));
    	}
    	
    	long startTreeMap = System.nanoTime();
    	for (int i = 0; i < inputData.size(); i++) {
    		map.remove(inputData.get(i));
    	}
    	long endTreeMap = System.nanoTime();
    	long timeTreeMap = endTreeMap - startTreeMap;
    	runtimeTree.gc();
    	compareTable = runtimeTree.totalMemory() - runtimeTree.freeMemory();

    	
    	Runtime runtimeTable = Runtime.getRuntime();
    	long startHashTable = System.nanoTime();
    	for (int i = 0; i < inputData.size(); i++) {
    		table.remove(inputData.get(i));
    	}
    	long endHashTable = System.nanoTime();
    	long timeHashTable = endHashTable - startHashTable;
    	runtimeTable.gc();
    	compareTable = runtimeTable.totalMemory() - runtimeTable.freeMemory();

    	deletionTime = timeHashTable;
    	deletionTree = timeTreeMap;
    }

    /**
     * Standalone method for comparing search operation
     * across HashTable and TreeMap
     */
    @Override
    public void compareSearch() {
        //TODO: Complete this method
    	TreeMap map = new TreeMap();
    	Runtime runtimeTree = Runtime.getRuntime();
    	HashTable table = new HashTable(inputData.size(), .75);
    	for (int i = 0; i < inputData.size(); i++) {
    		map.put(inputData.get(i), inputData.get(i));
    	}
    	for (int i = 0; i < inputData.size(); i++) {
    		table.put(inputData.get(i), inputData.get(i));
    	}
    	
    	long startTreeMap = System.nanoTime();
    	for (int i = inputData.size(); i > 0; i--) {
    		map.get(inputData.get(i));
    	}
    	long endTreeMap = System.nanoTime();
    	long timeTreeMap = endTreeMap - startTreeMap;
    	runtimeTree.gc();
    	deletionTable = runtimeTree.totalMemory() - runtimeTree.freeMemory();

    	Runtime runtimeTable = Runtime.getRuntime();
    	long startHashTable = System.nanoTime();
    	for (int i = inputData.size(); i > 0; i--) {
    		table.get(inputData.get(i));
    	}
    	long endHashTable = System.nanoTime();
    	long timeHashTable = endHashTable - startHashTable;
    	runtimeTable.gc();
    	deletionTable = runtimeTable.totalMemory() - runtimeTable.freeMemory();

    	compareTime = timeHashTable;
    	compareTree = timeTreeMap;
    }

    /*
    An implementation of loading files into local data structure is provided to you
    Please feel free to make any changes if required as per your implementation.
    However, this function can be used as is.
     */
    /**
    *
    * @param filename: Loads the data from the test file to local data structure
    *                This is already implemented for you.
    * @throws IOException
    */
    @Override
    public void loadData(String filename) throws IOException {

        // Opens the given test file and stores the objects each line as a string
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        inputData = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            inputData.add(line);
            line = br.readLine();
        }
        br.close();
    }
}
