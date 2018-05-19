package week2;
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {

  private String play;
  private ArrayList<String> characters;
  private ArrayList<Integer> lines;

  public CharactersInPlay() {
    characters = new ArrayList<String>();
    lines = new ArrayList<Integer>();
  }

  public String[] getPlay(){
    characters.clear();
    lines.clear();
    FileResource fr = new FileResource();
    String curPlay = fr.asString();
    String[] playLines = curPlay.split("\r\n|\r|\n");
    return playLines;
  }

  public void update(String person) {
    int charIdx = characters.indexOf(person);

    if(charIdx != -1) {
      int charLines = lines.get(charIdx);
      lines.set(charIdx, charLines + 1);
    } else {
      characters.add(person);
      lines.add(1);
    }
  }

  public void getAllCharacters() {
    String[] playLines = getPlay();

    for(String line : playLines) {
      int periodIndex = line.indexOf('.');
      if(periodIndex != -1 & periodIndex < 25) { // assuming longest name of character is not >14
        String character = line.substring(0, periodIndex);
        update(character);
      }
    }
    // int i = 0;
    // for(String ourChar : chars.characters) {
    //   System.out.println(ourChar);
    //   System.out.println(chars.lines.get(i));
    //   i += 1;
    // }
  }

  // why is this method not catching Hamlet?
  public void printChattyFolk() {
    getAllCharacters();

    for(int i = 0; i < lines.size(); i++) {
      int charLines = lines.get(i);
      if(charLines > 25) {
        String chattyChar = characters.get(i);
        System.out.println(chattyChar + " has " + charLines + " lines");
      }
    }
  }

  public void charactersWithNumParts(int num1, int num2) {
    getAllCharacters();

    for(int i = 0; i < lines.size(); i++) {
      int charLines = lines.get(i);
      if(charLines >= num1 & charLines <= num2) {
        System.out.println(characters.get(i));
        System.out.println("They have " + charLines + " lines");
      }
    }
  }

  public static void main(String args[]) {
    CharactersInPlay cip = new CharactersInPlay();
    cip.printChattyFolk();
  }
}
