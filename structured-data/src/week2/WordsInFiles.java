package week2;
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {

  private HashMap<String, ArrayList<String>> fileMap = new HashMap<String, ArrayList<String>>();
  private File directory = null;

  public WordsInFiles(String path) {
    directory = new File(path);
  }

  private void addWordsFromFile(File f) {
    FileResource fr = new FileResource(f);
    String words = fr.asString();
    String[] splitWords = words.split(" |\n|\r\n|\r");
    ArrayList<String> uniqueWords = new ArrayList<String>();
    for(String word : splitWords) {
      if(! uniqueWords.contains(word)) {
        uniqueWords.add(word);
      }
    }

    for(String word : uniqueWords) {
      if(fileMap.keySet().contains(word)) {
        ArrayList<String> tmpList = fileMap.get(word);
        tmpList.add(f.getName());
        fileMap.put(word, tmpList);
      } else {
        ArrayList<String> tmpList = new ArrayList<String>();
        tmpList.add(f.getName());
        fileMap.put(word, tmpList);
      }
    }
  }

  private void buildWordFileMap() {
    fileMap.clear();
    ArrayList<File> files = new ArrayList<File>();
    File[] contents = directory.listFiles();

    for(File f : contents) {
      if(f.isFile()) {
        files.add(f);
      }
    }

    for(File f : files) {
      addWordsFromFile(f);
    }

    // print for testing
    // for(String key : fileMap.keySet()) {
    //   System.out.println("The key: " + key);
    //   System.out.println("Is associated with: " + fileMap.get(key));
    // }
  }

  public int maxNumber() {
    int max = 0;
    for(String key : fileMap.keySet()) {
      int tmpLength = fileMap.get(key).size();
      if(tmpLength > max) {
        max = tmpLength;
      }
    }

    return max;
  }

  public ArrayList<String> wordsInNumFiles(int number) {
    ArrayList<String> words = new ArrayList<String>();
    for(String key : fileMap.keySet()) {
      int size = fileMap.get(key).size();
      if(size == number) {
        words.add(key);
      }
    }
    return words;
  }

  // test
  public void printFilesIn (String word) {
    for(String key : fileMap.keySet()) {
      if(key.equals(word)) {
        System.out.println("The word: " + key + " is in " + fileMap.get(key));
      }
    }
  }

  public static void main(String args[]) {

    String yourPath = ""; // fill with your own pathway
    WordsInFiles wif = new WordsInFiles(yourPath);
    wif.buildWordFileMap();

  }
}
