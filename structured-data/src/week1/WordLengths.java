package week1;
import edu.duke.*;
import java.util.*;

public class WordLengths {

  public void countWordLengths(FileResource resource, int counts[]) {
    // use Character.isLetter();
    String textSource = resource.asString();
    String[] splitText = textSource.split("\\s+");

    for(int i = 0; i < splitText.length; i++) {

      int letterCount = 0;
      for(int j = 0; j < splitText[i].length(); j++) {

        char ch = splitText[i].charAt(j);
        if(Character.isLetter(ch)) {
          letterCount += 1;
        }
      }

      if(letterCount <= 30) {
        counts[letterCount] += 1;
      } else {
        counts[31] += 1;
      }
    }
  }

  public int indexOfMax(int[] values) {
    // is there a bi-section/log(n) search way of implementing this?
    // solution currently O(n)
    int maxCount = 0;
    int maxIndex = -1;
    for(int i = 0; i < values.length; i++) {
      if(values[i] > maxCount) {
        maxCount = values[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  // TESTING #####
  // update to include words of each length
  public void test_CountWordLengths() {
    int[] testCounts = new int[31];
    FileResource fr = new FileResource();
    countWordLengths(fr, testCounts);

    System.out.println("This file has word of the following lengths:");
    for(int pos = 0; pos < testCounts.length; pos++) {
      String statement = testCounts[pos] + " words of length: " + pos;
      System.out.println(statement);
    }

    int testMax = 0;
    testMax = indexOfMax(testCounts);
    System.out.println("The most frequent word length is: " + testMax);
  }

  // MAIN #####
  public static void main(String args[]) {
    WordLengths wl = new WordLengths();
    wl.test_CountWordLengths();
  }
}
