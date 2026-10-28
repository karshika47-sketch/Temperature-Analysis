package projectsem2;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        Scanner sc = new Scanner(System.in);
        TemperatureAnalysis analysis = new TemperatureAnalysis();

        // Input temperatures with validation
        analysis.inputTemperatures(sc);

        // Perform analysis
        analysis.findHotCold();
        analysis.monthlyAverage();
        analysis.longestHeatwave();
        analysis.displayGraph();

        sc.close();
 

	}

}
