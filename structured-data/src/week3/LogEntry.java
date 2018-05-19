package week3;
import edu.duke.*;
import java.util.*;

public class LogEntry {
  private String ipAddress; // java has built-in IP data-type as well
  private Date accessTime;
  private String request;
  private int statusCode;
  private int bytesReturned;

  // WebLogParser.parseEntry which returns a log entry object
  // constructor
  public LogEntry(String ip, Date time, String req, int status, int bytes) {
    ipAddress = ip;
    accessTime = time;
    request = req;
    statusCode = status;
    bytesReturned = bytes;
  }

  // getter methods
  public String getipAddress() {
    return ipAddress;
  }

  public Date getaccessTime() {
    return accessTime;
  }

  public String getrequest() {
    return request;
  }

  public int getstatusCode() {
    return statusCode;
  }

  public int getbytesReturned() {
    return bytesReturned;
  }

  // methods
  public String toString() {
    return ipAddress + " " + accessTime + " " + request
           + " " + statusCode + " " + bytesReturned;
  }

}
