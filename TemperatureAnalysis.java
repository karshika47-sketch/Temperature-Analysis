package projectsem2;
import java.util.*;
public class TemperatureAnalysis {

	    double[] temps; // Will be initialized based on year (365 or 366 days)

	    // Input year and temperatures from user with validation
	    public void inputTemperatures(Scanner sc) {
	        int year = 0;
	        while (true) {
	            System.out.print("Enter the year: ");
	            if (sc.hasNextInt()) {
	                year = sc.nextInt();
	                if (year > 0) break;
	                else System.out.println("Year must be positive!");
	            } else {
	                System.out.println("Invalid input! Please enter a numeric year.");
	                sc.next(); // clear invalid input
	            }
	        }

	        boolean isLeap = isLeapYear(year);
	        int daysInYear = isLeap ? 366 : 365;
	        temps = new double[daysInYear];

	        System.out.println("Year " + year + (isLeap ? " is a leap year." : " is not a leap year."));
	        System.out.println("Enter " + daysInYear + " daily temperatures (0°C to 50°C):");

	        for (int i = 0; i < daysInYear; i++) {
	            while (true) {
	                System.out.print("Day " + (i + 1) + ": ");
	                if (sc.hasNextDouble()) {
	                    double temp = sc.nextDouble();
	                    if (temp >= 0 && temp <= 50) {
	                        temps[i] = temp;
	                        break;
	                    } else {
	                        System.out.println("Invalid input! Temperature must be between 0°C and 50°C. Try again.");
	                    }
	                } else {
	                    System.out.println("Invalid input! Please enter a number.");
	                    sc.next(); // clear invalid input
	                }
	            }
	        }
	    }

	    // Check if a year is leap year
	    private boolean isLeapYear(int year) {
	        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	    }

	    // 1. Find hottest and coldest days (all days with same temperature)
	    public void findHotCold() {
	        double maxTemp = temps[0];
	        double minTemp = temps[0];

	        // Find max and min temperatures
	        for (double temp : temps) {
	            if (temp > maxTemp) maxTemp = temp;
	            if (temp < minTemp) minTemp = temp;
	        }

	        // Collect all days with max and min temperatures
	        ArrayList<Integer> hottestDays = new ArrayList<>();
	        ArrayList<Integer> coldestDays = new ArrayList<>();

	        for (int i = 0; i < temps.length; i++) {
	            if (temps[i] == maxTemp) hottestDays.add(i + 1);
	            if (temps[i] == minTemp) coldestDays.add(i + 1);
	        }

	        System.out.println("\nHottest Temperature: " + maxTemp + "°C on day(s): " + hottestDays);
	        System.out.println("Coldest Temperature: " + minTemp + "°C on day(s): " + coldestDays);
	    }

	    // 2. Calculate average monthly temperature
	    public void monthlyAverage() {
	        int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
	        if (temps.length == 366) daysInMonth[1] = 29;

	        int start = 0;
	        System.out.println("\nAverage Monthly Temperatures:");
	        for (int i = 0; i < 12; i++) {
	            double sum = 0;
	            for (int j = start; j < start + daysInMonth[i]; j++) {
	                sum += temps[j];
	            }
	            double avg = sum / daysInMonth[i];
	            System.out.println("Month " + (i + 1) + ": " + String.format("%.2f", avg) + "°C");
	            start += daysInMonth[i];
	        }
	    }

	    // 3. Find the longest heatwave (>35°C)
	    public void longestHeatwave() {
	        int maxStreak = 0;
	        int currentStreak = 0;

	        for (int i = 0; i < temps.length; i++) {
	            if (temps[i] > 35) {
	                currentStreak++;
	                if (currentStreak > maxStreak) maxStreak = currentStreak;
	            } else {
	                currentStreak = 0;
	            }
	        }

	        System.out.println("\nLongest Heatwave Duration: " + maxStreak + " days");
	    }

	    // 4. Display text-based temperature trend graph
	    public void displayGraph() {
	        int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
	        if (temps.length == 366) daysInMonth[1] = 29;

	        int start = 0;
	        System.out.println("\nMonthly Temperature Trend Graph (Average Temperature):");
	        for (int i = 0; i < 12; i++) {
	            double sum = 0;
	            for (int j = start; j < start + daysInMonth[i]; j++) {
	                sum += temps[j];
	            }
	            double avg = sum / daysInMonth[i];

	            int stars = (int)(avg / 2);
	            System.out.print("Month " + (i + 1) + ": ");
	            for (int k = 0; k < stars; k++) System.out.print("*");
	            System.out.println();
	            start += daysInMonth[i];
	        }
	    }
	}


