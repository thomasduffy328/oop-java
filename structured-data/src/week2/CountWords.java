package week2;
import edu.duke.*;
import java.util.*;

public class CountWords {

  StorageResource myWords;
  ArrayList<String> myWords = new ArrayList<String>();

  public CountWords() {
    myWords = new StorageResource();
  }

  public void readWords(String source) {
    my.Words.clear();

    if(source.startsWith("http")) {
      URLResource resource = new URLResource(source);
      for(String word : resource.words()) {
        word = word.toLowerCase();
        if(! myWords.contains(word)) {
          myWords.add(word);
        }
      }
    } else {
      FileResource resource = new FileResource();
      for(String word : resource.words()) {
        word = word.toLowerCase();
        if(! myWords.contains(word)) {
          myWords.add(word.toLowerCase());
        }
      }
    }

  }

  public int getCount() {
    return myWords.size();
  }

}
