import java.io.IOException;

public class AnalysisTest {
    public static void main(String[] args) throws IOException  {

                // TODO Add code for checking command line arguments
                
                PerformanceAnalysisHash ana = new PerformanceAnalysisHash(args[0]);
                ana.compareDataStructures();
                ana.printReport();
            }
}
