package week1;
import java.util.*;
import java.lang.*;
import java.io.*;
import edu.duke.*;

public class SimpleLocation {
  public double latitude;
  public double longitude;

  public SimpleLocation() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Provide a latitude: ");
    this.latitude = scanner.nextDouble();
    System.out.print("Provide a longitude ");
    this.longitude = scanner.nextDouble();
  }

  public SimpleLocation(double lat, double lon) {
    this.latitude = lat;
    this.longitude = lon;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public void setLatitude(double lat) {
    if(lat < -180 || lat > 180) {
      System.out.println("That latitude is out of allowable range");
    } else {
      this.latitude = lat;
    }
  }

  public void setLongitude(double lon) {
    if(lon < -90 || lat > 90) {
      System.out.println("That longitude is out of allowable range");
    } else {
      this.longitude = lon;
    }
  }

  public double distance(SimpleLocation other) {
    double a = 0;
    double b = 0;
    if(other.latitude * this.latitude > 0) {
      a = other.latitude - this.latitude;
    } else {
      a = Math.abs(other.latitude) + Math.abs(this.latitude);
    }

    if(other.longitude * this.longitude > 0) {
      b = other.longitude - this.longitude;
    } else {
      b = Math.abs(other.latitude) + Math.abs(this.latitude);
    }
    double cSq = Math.pow(a,2) + Math.pow(b,2);
    return Math.sqrt(cSq);
  }

  public double distance(double lat, double lon) {
    double a = 0;
    double b = 0;
    if(lat * this.latitude > 0) {
      a = lat - this.latitude;
    } else {
      a = Math.abs(lat) + Math.abs(this.latitude);
    }

    if(lon * this.longitude > 0) {
      b = lon - this.longitude;
    } else {
      b = Math.abs(lon) + Math.abs(this.latitude);
    }
    double cSq = Math.pow(a,2) + Math.pow(b,2);
    return Math.sqrt(cSq);
  }

}
