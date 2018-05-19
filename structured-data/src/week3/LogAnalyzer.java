package week3;
import edu.duke.*;
import java.util.*;

public class LogAnalyzer {
  private ArrayList<LogEntry> records;
  private ArrayList<String> uniqueIPs;

  public LogAnalyzer() {
    records = new ArrayList<LogEntry>();
    uniqueIPs = new ArrayList<String>();
  }

  public void readFile(String filename, boolean verbose) {
    WebLogParser wlp = new WebLogParser();
    FileResource fr = new FileResource(filename);
    String[] logs = fr.asString().split("\n");

    for(String line : logs) {
      LogEntry tmpLog = wlp.parseEntry(line);
      records.add(tmpLog);
    }

    if(verbose) {
      for(LogEntry item : records) {
        System.out.println(item);
      }
    }
  }

  public HashMap<String,Integer> countVisitsPerIP() {
    HashMap<String,Integer> IPcounts = new HashMap<String,Integer>();
    for(LogEntry visit : records) {
      String IP = visit.getipAddress();
      if(! IPcounts.containsKey(IP)) {
        IPcounts.put(IP, 1);
      } else {
        IPcounts.put(IP, IPcounts.get(IP) + 1);
      }
    }
    return IPcounts;
  }

  public HashMap<String,Integer> countVisitsPerIPUser(ArrayList<LogEntry> array) {
    HashMap<String,Integer> IPcounts = new HashMap<String,Integer>();
    for(LogEntry visit : array) {
      String IP = visit.getipAddress();
      if(! IPcounts.containsKey(IP)) {
        IPcounts.put(IP, 1);
      } else {
        IPcounts.put(IP, IPcounts.get(IP) + 1);
      }
    }
    return IPcounts;
  }

  public int mostNumberVisitsByIP(HashMap<String,Integer> hash) {
    int max = 0;
    for(String key : hash.keySet()) {
      int tmpVisits = hash.get(key);
      if(tmpVisits > max) {
        max = tmpVisits;
      }
    }
    return max;
  }

  public ArrayList<String> iPsMostVisits(HashMap<String,Integer> hash) {
    ArrayList<String> IPsVisited = new ArrayList<String>();
    int max = mostNumberVisitsByIP(hash);
    for(String key : hash.keySet()) {
      int tmpVisits = hash.get(key);
      if(tmpVisits == max) {
        IPsVisited.add(key);
      }
    }
    return IPsVisited;
  }

  public int countUniqueIPs() {
    HashMap<String,Integer> counts = countVisitsPerIP();
    return counts.size();
  }

  public int countUniqueIPsInRange(int low, int high) {
    ArrayList<LogEntry> rangeIPs = new ArrayList<LogEntry>();
    ArrayList<String> uniqueIPs = new ArrayList<String>();
    for(LogEntry visit : records) {
      int tmpStatus = visit.getstatusCode();
      if(tmpStatus >= low & tmpStatus <= high) {
        rangeIPs.add(visit);
      }
    }
    for(LogEntry visit : rangeIPs) {
      String IP = visit.getipAddress();
      if(! uniqueIPs.contains(IP)) {
        uniqueIPs.add(IP);
      }
    }
    return uniqueIPs.size();
  }

  public void printAllHigherThanNum(int num) {
    for(LogEntry visit : records) {
      int tmpStatus = visit.getstatusCode();
      if(tmpStatus > num) {
        System.out.println(visit);
      }
    }
  }

  public ArrayList<LogEntry> uniqueIPVisitsOnDay(String day) {
    ArrayList<LogEntry> dayEntries = new ArrayList<LogEntry>();
    ArrayList<String> uniqueIPs = new ArrayList<String>();
    ArrayList<LogEntry> uniqueEntries = new ArrayList<LogEntry>();

    for(LogEntry visit : records) {
      String d = visit.getaccessTime().toString();
      d = d.substring(4,10);  // pull out the month & day
      if(d.equals(day)) {
        dayEntries.add(visit);
      }
    }
    for(LogEntry visit : dayEntries) {
      String IP = visit.getipAddress();
      if(! uniqueIPs.contains(IP)) {
        uniqueEntries.add(visit);
        uniqueIPs.add(IP);
      }
    }
    return uniqueEntries;
  }

  public HashMap<String,ArrayList<String>> iPsForDays() {

    HashMap<String,ArrayList<String>> ipHash = new HashMap<String,ArrayList<String>>();

    for(LogEntry visit : records) {
      String d = visit.getaccessTime().toString();
      d = d.substring(4,10);  // pull out the month & day
      ArrayList<String> dateIPs = new ArrayList<String>();
      if(! ipHash.keySet().contains(d)) {
        dateIPs.add(visit.getipAddress());
      } else {
        dateIPs = ipHash.get(d);
        dateIPs.add(visit.getipAddress());
      }
      ipHash.put(d, dateIPs);
    }
    return ipHash;
  }

  public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> hash) {
    String maxDay = "";
    int maxSize = 0;
    for(String key : hash.keySet()) {
      int size = hash.get(key).size();
      if(size > maxSize) {
        maxDay = key;
        maxSize = size;
      }
    }
    return maxDay;
  }

  public ArrayList<String> iPsWithMostVisitsOnDay(String day) {
    ArrayList<LogEntry> dayVisits = new ArrayList<LogEntry>();

    for(LogEntry visit : records) {
      String d = visit.getaccessTime().toString();
      d = d.substring(4,10);  // pull out the month & day
      if(d.equals(day)) {
        dayVisits.add(visit);
      }
    }
    HashMap<String,Integer> counts = countVisitsPerIPUser(dayVisits);
    ArrayList<String> dayMaxVisits = iPsMostVisits(counts);
    return dayMaxVisits;
  }

}
