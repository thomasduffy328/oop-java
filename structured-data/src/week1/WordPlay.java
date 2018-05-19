package week1;
import edu.duke.*;

public class WordPlay {

  String vowels = "aeiouAEIOU";

  public boolean isVowel(char ch) {

    int idx = vowels.indexOf(ch);
    if(idx != -1) {
      return true;
    } else {
      return false;
    }
  }

  public String replaceVowels(String phrase, char ch) {

    StringBuilder mutatedPhrase = new StringBuilder(phrase);

    for(int i = 0; i < mutatedPhrase.length(); i++) {
      if(isVowel(mutatedPhrase.charAt(i))) {
        mutatedPhrase.setCharAt(i, ch);
      }
    }
    return mutatedPhrase.toString();
  }

  public String emphasize(String phrase, char ch) {
    // this is case sensitive
    char evenChar = '*';
    char oddChar = '+';

    StringBuilder mutatedPhrase = new StringBuilder(phrase);
    for(int i = 0; i < mutatedPhrase.length(); i++) {

      if(mutatedPhrase.charAt(i) == ch) {
        if(i % 2 == 0) {
          mutatedPhrase.setCharAt(i, evenChar);
        } else {
          mutatedPhrase.setCharAt(i, oddChar);
        }
      }
    }
    System.out.println("Notice this is case sensitive:");
    return mutatedPhrase.toString();
  }

  /*
  TESTING #####
  */

  public void test_isVowel(char ch) {
    boolean haveVowel = isVowel(ch);
    System.out.println(haveVowel);
  }

  public void test_replaceVowels(String phrase, char ch) {
    String sansVowels = replaceVowels(phrase, ch);
    System.out.println(sansVowels);
  }

  public void test_emphasize(String phrase, char ch) {
    String newString = emphasize(phrase, ch);
    System.out.println(newString);
  }

  /*
  MAIN #####
  */

  public static void main(String args[]) {
    WordPlay play = new WordPlay();
    play.test_isVowel('e');
    play.test_replaceVowels("Hi my name is Bobert", 'g');
    play.test_emphasize("A well this is awkward", 'a');
  }
}
