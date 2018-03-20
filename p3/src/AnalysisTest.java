import java.io.IOException;

public class AnalysisTest {
    public static void main(String[] args) throws IOException  {

                // TODO Add code for checking command line arguments
                
                PerformanceAnalysisHash ana = new PerformanceAnalysisHash("F:/Documents/College/Sophmore/Semester_2/CS400/GitHub/hash-table/p3/data/data_details.txt");
                ana.compareDataStructures();
                ana.printReport();
            }
}
