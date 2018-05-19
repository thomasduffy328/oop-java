package week1;
import edu.duke.*;

public class CaesarCipher {

  /*
  Encrypts a message (input) using the Caesar Cipher algorithm that shifts the Alphabet
  using a fixed key (key)
  */
  public String encrypt(String input, int key) {

    StringBuilder encrypted = new StringBuilder(input);
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

    for (int i = 0; i < encrypted.length(); i++) {

      char currentChar = encrypted.charAt(i);
      if(Character.isLowerCase(currentChar)) {
        alphabet = alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet.toLowerCase();
      }

      int idx = alphabet.indexOf(currentChar);
      if (idx != -1) {
        char newChar = shiftedAlphabet.charAt(idx);
        encrypted.setCharAt(i, newChar);
      }

      alphabet = alphabet.toUpperCase();
      shiftedAlphabet = shiftedAlphabet.toUpperCase();
    }
    return encrypted.toString();
  }

  /*
  Encrypts a message (input) using two keys (key1 and key2)
  Alphabet from key1 is used to encrypt the first character and every other character (i.e. even index)
  after that while key2 is used to encrypt the odd index characters with the Caesar Cipher algorithm.
  */
  public String encryptTwoKeys(String input, int key1, int key2) {

    StringBuilder encrypted = new StringBuilder(input);
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String key1Alphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
    String key2Alphabet = alphabet.substring(key2) + alphabet.substring(0, key2);

    for(int i = 0; i < encrypted.length(); i++) {

      char currentChar = encrypted.charAt(i);
      if(Character.isLowerCase(currentChar)) {
        alphabet = alphabet.toLowerCase();
        key1Alphabet = key1Alphabet.toLowerCase();
        key2Alphabet = key2Alphabet.toLowerCase();
      }

      int idx = alphabet.indexOf(currentChar);
      if(idx != -1) {
        if(i % 2 == 0) {
          char newChar = key1Alphabet.charAt(idx);
          encrypted.setCharAt(i, newChar);
        } else {
          char newChar = key2Alphabet.charAt(idx);
          encrypted.setCharAt(i, newChar);
        }
      }

      alphabet = alphabet.toUpperCase();
      key1Alphabet = key1Alphabet.toUpperCase();
      key2Alphabet = key2Alphabet.toUpperCase();
    }
    return encrypted.toString();
  }

  /*
  Tester methods which provides test cases for both encrypt and encryptTwoKeys.
  The encrypted and decrypted back again results are printed for the user to review.
  */
  public void testSingleCaesar(int key) {
    FileResource fr = new FileResource();
    String message = fr.asString();
    String encrypted = encrypt(message, key);
    System.out.println(encrypted);
    String decrypted = encrypt(encrypted, 26 - key);
    System.out.println(decrypted);
  }

  public void testDoubleCaesar(int key1, int key2) {
    FileResource fr = new FileResource();
    String message = fr.asString();
    String encrypted = encryptTwoKeys(message, key1, key2);
    System.out.println(encrypted);
    String decrypted = encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    System.out.println(decrypted);
  }

  public static void main(String[] args) {
    CaesarCipher cc = new CaesarCipher();
    // fill with tests you'd like to perform

  }
}
