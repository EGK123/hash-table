import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PerformanceAnalysisHash implements PerformanceAnalysis {

    // The input data from each file is stored in this/ per file
    private ArrayList<String> inputData;
    
    public PerformanceAnalysisHash(){
    }

    public PerformanceAnalysisHash(String details_filename) throws IOException{
        //TODO: Save the details of the test data files
    	details_filename.replace('\\',File.separatorChar).replace('/',File.separatorChar);		//adapted from method found on stackoverflow 
    	for (String line : Files.readAllLines(Paths.get(File.separator + "data" + File.separator + "details_filename"))) {	//can be found at : https://stackoverflow.com/questions/2788080/java-how-to-read-a-text-file
    		for (String part : line.split("\\s+")) {
    			inputData.add(part);
    		}
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
    }

    /** Function used to print out the final report
    *
    */
    @Override
    public void printReport() {
        //TODO: Complete this method
    }

    /**
     * Standalone method for comparing insertion operation
     * across HashTable and TreeMap
     */
    @Override
    public void compareInsertion() {
        //TODO: Complete this method
    	
    }

    /**
     * Standalone method for comparing deletion operation
     * across HashTable and TreeMap
     */
    @Override
    public void compareDeletion() {
        //TODO: Complete this method
    }

    /**
     * Standalone method for comparing search operation
     * across HashTable and TreeMap
     */
    @Override
    public void compareSearch() {
        //TODO: Complete this method
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
