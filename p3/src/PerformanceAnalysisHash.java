import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;

public class PerformanceAnalysisHash implements PerformanceAnalysis {

    // The input data from each file is stored in this/ per file
    private ArrayList<?> inputData;
    long insertionTime;
    long deletionTime;
    long compareTime;
    long insertionTree;
	long deletionTree;
	long compareTree;
    
    public PerformanceAnalysisHash(){
    }

    public PerformanceAnalysisHash(String details_filename) throws IOException{		
//    	loadData( details_filename);	
//    	if (details_filename.contains("Integer")) {
//    		ArrayList<?> intData = new ArrayList<>();
//    		int length = inputData.size();
//    		for (int i = 0; i < length ; i++) {
//    			intData.add(Integer.valueOf((String)inputData.get(i)), null);
//    		}
//    		inputData = intData;		//overwrote input data to integer arraylist
//    	}
    	details_filename.replace('\\',File.separatorChar).replace('/',File.separatorChar);		//adapted from method found on stackoverflow 
    	ArrayList<String> toRead = new ArrayList<>();
    	ArrayList<String> paths = new ArrayList<>();
    	for (String line : Files.readAllLines(Paths.get(File.separator + "data" + File.separator + "details_filename"))) {	//can be found at : https://stackoverflow.com/questions/2788080/java-how-to-read-a-text-file
    		for (String part : line.split("\\s+")) {
    			toRead.add(part);
    		}
    	}
    	String filepath = toRead.get(0).split(",")[1];	//splits first line on the comma and keeps the second half
    	for (int i = 1; i < toRead.size(); i++) {
    		paths.add(toRead.get(i).split(",")[0]); 	//splits on comma and keeps first half as path 
    	}
    	for (int i = 1)

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
    	while(data.hasNextLine()) {
    		PerformanceAnalysisHash("data_details.txt");
    		while(FileName.hasNextLine()) {
    		compareDataStructures();
    		//	System.out.println("|"    +IntegerSmall.txt+"|"            +PUT+"|"      +HASHTABLE+"|"                   +1279.0+"|"             +0+"|");
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "PUT|", "HASHTABLE|", time +"|", bytes+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "PUT|", "TREEMAP|", time +"|", bytes+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "GET|", "HASHTABLE|", time +"|", bytes+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "GET|", "TREEMAP|", time +"|", bytes+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "REMOVE|", "HASHTABLE|", time +"|", bytes+"|" );
    			System.out.printf("%1s %20s %15s %15s %25d %15d %n", "|", dataFile+"|", "REMOVE|", "TREEMAP|", time +"|", bytes+"|" );
    		}
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
    	for (int i = 0; i < inputData.size(); i++) {
    		map.put(inputData.get(i), inputData.get(i));
    	}
    	long endTreeMap = System.nanoTime();
    	long timeTreeMap = endTreeMap - startTreeMap;
    	
    	HashTable table = new HashTable(inputData.size(), .75);
    	long startHashTable = System.nanoTime();
    	for (int i = 0; i < inputData.size(); i++) {
    		table.put(inputData.get(i), inputData.get(i));
    	}
    	long endHashTable = System.nanoTime();
    	long timeHashTable = endHashTable - startHashTable;
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
    	
    	long startHashTable = System.nanoTime();
    	for (int i = 0; i < inputData.size(); i++) {
    		table.remove(inputData.get(i));
    	}
    	long endHashTable = System.nanoTime();
    	long timeHashTable = endHashTable - startHashTable;
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
    	
    	long startHashTable = System.nanoTime();
    	for (int i = inputData.size(); i > 0; i--) {
    		table.get(inputData.get(i));
    	}
    	long endHashTable = System.nanoTime();
    	long timeHashTable = endHashTable - startHashTable;
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
