package week3;
import edu.duke.*;
import java.util.*;

public class Tester {

  public static void main(String args[]) {
    // readFile test
    // LogAnalyzer logAnalyzer = new LogAnalyzer();
    // logAnalyzer.readFile("week3/data/short-test_log", false); // 2nd parameter prints all log entries
    //
    // // unique IP's test
    // int unique = logAnalyzer.getuniqueIP();
    // System.out.println("There are " + unique + " unique IP\'s in our log file");
    //
    // // printAllHigherThanNum test
    // int testNum = 200;
    // System.out.println("Below are the logs that have status codes greater than " + testNum);
    // logAnalyzer.printAllHigherThanNum(testNum);
    //
    // int uniqueInRange = logAnalyzer.countUniqueIPsInRange(200, 299);
    // System.out.println("In our status range there were " + uniqueInRange + " unique IP's");
    //
    // LogAnalyzer laTwo = new LogAnalyzer();
    // laTwo.readFile("week3/data/weblog-short_log", false);
    // String testDate = "Sep 14";
    // System.out.println("Below are the unique visits for " + testDate);
    // ArrayList<LogEntry> uniqueVisits = laTwo.uniqueIPVisitsOnDay(testDate);
    // for(LogEntry item : uniqueVisits) {
    //   System.out.println(item);
    // }

    // test IPcounter
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("week3/data/weblog3-short_log", true);

    HashMap<String,Integer> countTest = la.countVisitsPerIP();
    for(String key : countTest.keySet()) {
      System.out.println("The IP " + key + " pinged the site " + countTest.get(key) + " times");
    }
    System.out.println("\n");
    /*
    int max = la.mostNumberVisitsByIP(countTest);
    System.out.println("The max visits was: " + max + "\n");
    */
    ArrayList<String> tmpCounts = la.iPsMostVisits(countTest);
    for(String value : tmpCounts) {
      System.out.println("This IP had the most visits: " + value + "\n");
    }

    ArrayList<String> maxOfDay = la.iPsWithMostVisitsOnDay("Sep 30");
    for(String entry : maxOfDay) {
      System.out.println(entry);
    }
    /*
    HashMap<String,ArrayList<String>> testHash = new HashMap<String,ArrayList<String>>();
    testHash = la.iPsForDays();
    for(String key : testHash.keySet()) {
      System.out.println("The date " + key + " is associated with the IP: " + testHash.get(key));
    }
    System.out.println("\n");

    String maxDay = la.dayWithMostIPVisits(testHash);
    System.out.println("The day with the most visits is : " + maxDay + "\n");
    */

  }

}
