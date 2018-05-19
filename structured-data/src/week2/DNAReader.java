package week2;
import edu.duke.*;
import java.util.*;
import java.lang.*;

public class DNAReader {

  private String dna;
  private HashMap<String,Integer> dnaMap = new HashMap<String, Integer>();

  public DNAReader() {
    FileResource fr = new FileResource();
    dna = fr.asString();
    dna = dna.toUpperCase();
    dnaMap.clear();
  }

  public DNAReader(String source) {
    FileResource fr = new FileResource(source);
    dna = fr.asString();
    dna = dna.toUpperCase();
    dnaMap.clear();
  }

  private void buildCodonMap(int start) {
    if(start != 0 & start != 1 & start != 2) {
      throw new Error("Start must be either 0, 1, 2");
    }
    for(int i = 0 + start; i < dna.length() - (start + 3); i += 3) {
      String subDNA = dna.substring(i, i + 3);
      if(! dnaMap.keySet().contains(subDNA)) {
        dnaMap.put(subDNA, 1);
      } else {
        dnaMap.put(subDNA, dnaMap.get(subDNA) + 1);
      }
    }
  }

  public String getMostCommonCodon() {

    // find max
    int max = 0;
    for(String key : dnaMap.keySet()) {
      int count = dnaMap.get(key);
      if(count > max) {
        max = count;
      }
    }

    // find corresponding key
    for(String key : dnaMap.keySet()) {
      int tmp = dnaMap.get(key);
      if(tmp == max) {
        return key;
      }
    }
    return "Something went wrong";
  }

  public void printCodonCounts(int start, int end) {
    for(String key : dnaMap.keySet()) {
      int tmp = dnaMap.get(key);
      if(tmp >= start & tmp <= end) {
        System.out.println("The codon: " + key + " has " + tmp + " occurrences");
      }
    }
  }

  public static void main(String args[]) {
    DNAReader dnaZero = new DNAReader();
    dnaZero.buildCodonMap(0);

    DNAReader dnaOne = new DNAReader();
    dnaOne.buildCodonMap(1);

    DNAReader dnaTwo = new DNAReader();
    dnaTwo.buildCodonMap(2);

  }
}
