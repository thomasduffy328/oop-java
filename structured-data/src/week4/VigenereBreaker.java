package week4;
import java.util.*;
import java.lang.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public String sliceString(String message, int whichSlice, int totalSlices) {
      if(whichSlice > totalSlices - 1) {
        throw new java.lang.Error("whichSlice has to be one of the totalSlices");
      }
      StringBuilder sb = new StringBuilder();

      for(int i = whichSlice; i < message.length(); i += totalSlices) {
        sb.append(message.charAt(i));
      }
      return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
      CaesarCracker cc = new CaesarCracker(mostCommon);
      int[] keys = new int[klength];

      for(int i = 0; i < klength; i++ ) {
        String splitMsg = sliceString(encrypted, i, klength);
        keys[i] = cc.getKey(splitMsg);
      }
      return keys;
    }

    public HashSet<String> readDictionary(String filename) {
      FileResource fr = new FileResource(filename);
      HashSet<String> dictionaryHash = new HashSet<String>();

      for(String word : fr.lines()) {
        dictionaryHash.add(word.toLowerCase());
      }
      return dictionaryHash;
    }

    public HashMap<String,HashSet<String>> readAllDict(String dictionaryDir) {
      HashMap<String,HashSet<String>> dictMap = new HashMap<String,HashSet<String>>();
      File path = new File(dictionaryDir);
      File[] dictionaries = path.listFiles();

      for(File dictionary : dictionaries) {
        String dictName = dictionary.getName();
        dictMap.put(dictName, readDictionary(path + "/" + dictName));
        System.out.println("Added " + dictName + " dictionary");
      }
      return dictMap;
    }

    public char mostCommonChar(HashSet<String> dictionary) {
      int maxCount = 0;
      char maxChar = '-';
      HashMap<Character,Integer> letterFreq = new HashMap<Character,Integer>();

      for(String word : dictionary) {
        for(int j = 0; j < word.length(); j++) {
          char c = word.charAt(j);
          if(! letterFreq.keySet().contains(c)) {
            letterFreq.put(c, 1);
          } else {
            int charCount = letterFreq.get(c);
            letterFreq.put(c, charCount += 1);
          }
        }
      }
      for(char key : letterFreq.keySet()) {
        int charFreq = letterFreq.get(key);
        // System.out.println("The char: " + key + " appears " + charFreq + " times");
        if(charFreq > maxCount) {
          maxCount = charFreq;
          maxChar = key;
        }
      }
      return maxChar;
    }

    public int countWords(String message, HashSet<String> dictionary) {
      int validWords = 0;
      String[] splitMsg = message.split("\\W+");

      for(String word : splitMsg) {
        word = word.toLowerCase();
        if(dictionary.contains(word)) {
          validWords ++;
        }
      }
      return validWords;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary, boolean verbose) {
      int maxValid = 0;
      int keyGuess = 0;
      char commonChar = mostCommonChar(dictionary);

      for(int klength = 1; klength <= 100; klength ++) { // 100 is arbitrary, can be up to encrypted.length()
        int[] tmpKeys = tryKeyLength(encrypted, klength, commonChar); // change mostCommon if not English
        VigenereCipher tmpCipher = new VigenereCipher(tmpKeys);
        String tmpDecrypted = tmpCipher.decrypt(encrypted);
        int validWords = countWords(tmpDecrypted, dictionary); // printed 1 off for test

        if(validWords > maxValid) {
          maxValid = validWords;
          keyGuess = klength;
        }
      }
      int[] keys = tryKeyLength(encrypted, keyGuess, commonChar);

      VigenereCipher cipher = new VigenereCipher(keys);
      if(verbose) {
        for(int i : keys) {
          System.out.println("Our key guess includes: " + i);
        }
        System.out.println("The max valid words was " + maxValid + " for keyGuess " + keyGuess);
      }
      return cipher.decrypt(encrypted);
    }

    public void breakForAllLanguages(String encrypted, String dictionaryDir, boolean verbose) {
      HashMap<String,HashSet<String>> dictionaries = readAllDict(dictionaryDir);
      int maxValid = 0;
      String srcLanguage = "NA";
      for(String language : dictionaries.keySet()){
        System.out.println("Evaluating language: " + language);
        String tmpDecrypted = breakForLanguage(encrypted, dictionaries.get(language), verbose);
        int tmpMax = countWords(tmpDecrypted, dictionaries.get(language));
        if(tmpMax > maxValid) {
          maxValid = tmpMax;
          srcLanguage = language;
        }
      }
      String decrypted = breakForLanguage(encrypted, dictionaries.get(srcLanguage), verbose);
      System.out.println("Our message is in : " + srcLanguage);
      System.out.println(decrypted);

    }

    public void breakVigenere(String filename, String dictionaryDir, boolean verbose) {
      FileResource fr = new FileResource(filename);
      String message = fr.asString();
      breakForAllLanguages(message, dictionaryDir, verbose);
    }

}
