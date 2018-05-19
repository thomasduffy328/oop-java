package week1;
import edu.duke.*;
import java.util.*;

public class CaesarBreaker {

  String directory = "week1/data/";
  String alphabet = "abcdefghijklmnopqrstuvwxyz";

  /*
  GENERAL DECRYPTION METHODS
  */

  /*
  Implements an eyeball check by printing out decrypted message for every possible key (0-25)
  */
  public void eyeballDecrypt(String encrypted) {
  CaesarCipher cipher = new CaesarCipher();

    for(int i = 0; i < 26; i++) {
        String s = cipher.encrypt(encrypted, i);
        System.out.println(i + " : " + s);
    }
  }

  /*
  Increment through a block of text and count the letter frequency
  */
  public int[] countLetters(String message) {
    int[] counts = new int[26];
    for(int i = 0; i < message.length(); i++) {
      char ch = Character.toLowerCase(message.charAt(i));
      int pos = alphabet.indexOf(ch);

      if(pos != -1) {
          counts[pos] += 1;
      }
    }
    return counts;
  }

  /*
  Decrypts message using letter frequency algorithm in countLetters
  assuming 'e' is the most frequent letter used
  */
  // add to handle cases of 2nd = 't', 3rd = 'a', 4th = 'o', 5th = 'i' to increase accuracy
  public String decrypt(String encrypted) {
    CaesarCipher cc = new CaesarCipher();
    WordLengths wl = new WordLengths();

    int[] freqs = countLetters(encrypted);
    int maxLetter = wl.indexOfMax(freqs);
    int dkey = maxLetter - 4; // b/c e is in position 4 in alphabet

    if(maxLetter < 4) {
      dkey = 26 - (4 - maxLetter);
    }

    System.out.println("The key is: " + dkey);
    return cc.encrypt(encrypted, 26 - dkey);
  }

  /*
  TWO KEY DECRYPTION
  */

  public String[] splitMessage(String encrypted) {
    StringBuilder sbEven = new StringBuilder();
    StringBuilder sbOdd = new StringBuilder();

    for(int i = 0; i < encrypted.length(); i++) {
      if(i % 2 == 0) {
        sbEven.append(encrypted.charAt(i));
      } else {
        sbOdd.append(encrypted.charAt(i));
      }
    }

    String[] strings = {sbEven.toString(), sbOdd.toString()};
    return strings;
  }

  public String twoKeyDecrypt(String message) {
    CaesarBreaker breaker = new CaesarBreaker();
    String[] strings = breaker.splitMessage(message);

    for(int elem = 0; elem < strings.length; elem++) {
      strings[elem] = breaker.decrypt(strings[elem]);
    }
    // merge back together
    StringBuilder sb = new StringBuilder();
    int counterEven = 0;
    int counterOdd = 0;
    for(int i = 0; i < strings[0].length() + strings[1].length(); i++) {
      if(i % 2 == 0) {
        sb.append(strings[0].charAt(counterEven));
        counterEven += 1;
      } else {
        sb.append(strings[1].charAt(counterOdd));
        counterOdd += 1;
      }
    }

    return sb.toString();
  }

  /*
  TESTER METHODS
  */
  public void testEyeball(String message, boolean isEncrypted, int key) {
    CaesarCipher cipher = new CaesarCipher();
    CaesarBreaker breaker = new CaesarBreaker();

    if(isEncrypted) {
      breaker.eyeballDecrypt(message);
    } else {
      String encrypted = cipher.encrypt(message, key);
      breaker.eyeballDecrypt(encrypted);
    }
  }

  public void testSplitter(String message) {
    CaesarBreaker breaker = new CaesarBreaker();
    String[] broken = breaker.splitMessage(message);

    System.out.println("This is the even string: " + broken[0]);
    System.out.println("This is the odd string: " + broken[1]);
  }

  /*
  MAIN METHOD
  */
  public static void main(String args[]) {

    CaesarCipher cc = new CaesarCipher();
    CaesarBreaker breaker = new CaesarBreaker();
    FileResource resource = new FileResource();
    // fill in with tests you'd like to perform

  }

}
