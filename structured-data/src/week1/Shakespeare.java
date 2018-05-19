package week1;
import edu.duke.*;

public class Shakespeare {

  String directory = "week1/data/";

  public String[] getCommon() {
    FileResource resource = new FileResource(directory + "common.txt");
    String[] common = new String[20]; // known it has 20 words
    int index = 0;
    for(String s : resource.words()) {
      common[index] = s;
      index += 1;
    }
    return common;
  }

  public int indexOf(String[] list, String word) {
    for(int pos = 0; pos < list.length; pos++) {
      if(list[pos].equals(word)) {
        return pos;
      }
    }
    return -1;
  }

  public void countWords(FileResource fr, String[] common, int[] counts) {
    for(String word : fr.words()) {
      word = word.toLowerCase();
      int position = indexOf(common, word);
      if(position != -1) {
        counts[position] += 1;
      }
    }
  }

  public void countShakespeare() {
    String[] plays = {"romeo.txt", "caesar.txt", "errors.txt",
                      "hamlet.txt","likeit.txt", "macbeth.txt"};
    String[] common = getCommon();
    int[] counts = new int[common.length];
    for(int i = 0; i < plays.length; i++) {
      FileResource fr = new FileResource(directory + plays[i]);
      countWords(fr, common, counts);
      System.out.println("done with " + plays[i]);
    }

    for(int k = 0; k < common.length; k++) {
      System.out.println(common[k] + "\t" + counts[k]);
    }
  }

  public static void main(String args[]) {
    Shakespeare bard = new Shakespeare();
    bard.countShakespeare();

  }
}
