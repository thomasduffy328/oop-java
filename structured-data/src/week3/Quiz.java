package week3;
import edu.duke.*;
import java.util.*;

public class Quiz {
  public static void main(String args[]) {
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("week3/data/weblog2_log", false);

    // 4.
    System.out.println("Question 4: ");
    int ansFour = la.countUniqueIPs();
    System.out.println("There are " + ansFour + " unique IPs in the file");

    // 5.
    System.out.println("Question 5: ");
    ArrayList<LogEntry> uniqueDay = la.uniqueIPVisitsOnDay("Sep 27");
    System.out.println("The size of the ArrayList is " + uniqueDay.size());

    // 6.
    System.out.println("Question 6: ");
    int rangeCount = la.countUniqueIPsInRange(200,299);
    System.out.println("The number of IPs in that range: " + rangeCount);

    // 7.
    System.out.println("Question 7: ");
    HashMap<String,Integer> IPvisits = la.countVisitsPerIP();
    int maxVisits = la.mostNumberVisitsByIP(IPvisits);
    System.out.println("The most visits was " + maxVisits);

    // 8.
    System.out.println("Question 8: ");
    ArrayList<String> visitedIP = la.iPsMostVisits(IPvisits);
    for(String entry : visitedIP) {
      System.out.println("The IP with the most visits: " + entry);
    }

    // 9.
    System.out.println("Question 9: ");
    HashMap<String,ArrayList<String>> hashNine = la.iPsForDays();
    String day = la.dayWithMostIPVisits(hashNine);
    System.out.println("The day with the most visits: " + day);

    // 10.
    System.out.println("Question 10: ");
    ArrayList<String> maxDay = la.iPsWithMostVisitsOnDay("Sep 29");
    for(String entry : maxDay) {
      System.out.println("The IP " + entry + " visited the most");
    }

    }
  }
