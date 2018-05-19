package week2;
import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {

  private ArrayList<String> myWords;
  private ArrayList<Integer> myFreqs;

  public WordFrequenciesMap() {
    myWords = new ArrayList<String>();
    myFreqs = new ArrayList<Integer>();
  }

  public void countWords() {
    FileResource fr = new FileResource();
    HashMap<String, Integer> wordMap = new HashMap<String,Integer>();
    int total = 0;
    for(String word : fr.words()) {
      word = word.toLowerCase();
      if(wordMap.keySet().contains(word)) {
        wordMap.put(word, wordMap.get(word) + 1);
      } else {
        wordMap.put(word, 1);
      }
    }
    for(String word : wordMap.keySet()) {
      int occurrences = wordMap.get(word);
      if(occurrences > 100) {
        System.out.println(word + " has " + occurrences + " occurrences");
      }
    }
  }
  // does not account for punctuation, i.e. 'juliet' != 'juliet,'
  public void findUnique() {
    myWords.clear();
    myFreqs.clear();
    FileResource resource = new FileResource();

    for(String s : resource.words()) {
      s = s.toLowerCase();
      int index = myWords.indexOf(s);
      if(index == -1) {
        myWords.add(s);
        myFreqs.add(1);
      } else {
        int value = myFreqs.get(index);
        myFreqs.set(index, value + 1);
      }
    }
  }

  public int findIndexOfMax() {
    int maxLength = 0;
    int indexOfMax = -1;
    for(int i = 0; i < myFreqs.size(); i++) {
      int tmpLength = myFreqs.get(i);
      if(tmpLength > maxLength) {
        maxLength = tmpLength;
        indexOfMax = i;
      }
    }
    return indexOfMax;
  }

  public void userSearchWord() {
    findUnique();
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter a word you'd like the frequency of: ");
    String userWord = scan.nextLine();
    int wordIdx = myWords.indexOf(userWord);
    if(wordIdx != -1) {
      System.out.println("That word occurs: " + myFreqs.get(wordIdx) + " times");
    } else {
      System.out.println("Apologies, that word does not seem to be in the file.");
    }
  }

  public void tester() {
    findUnique();
    System.out.println("Number of unique words " + myWords.size());
    for(int i = 0; i < myWords.size(); i++) {
      System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
    }
    int maxPos = findIndexOfMax();
    System.out.println("The word occurring most often is: " + myWords.get(maxPos));
    System.out.println("It occurs " + myFreqs.get(maxPos) + " times");
  }

  public static void main(String args[]) {
    WordFrequenciesMap wf = new WordFrequenciesMap();
    wf.countWords();
  }

}
