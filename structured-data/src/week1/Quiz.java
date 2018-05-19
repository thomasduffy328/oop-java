package week1;
import edu.duke.*;
import java.util.*;

public class Quiz{

  public static void main(String args[]) {
    // 1.
    String phraseOne = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

    CaesarCipher cc = new CaesarCipher();
    String AnsOne = cc.encrypt(phraseOne, 15);
    System.out.println("Answer 1: " + AnsOne);

    // 2.
    String AnsTwo = cc.encryptTwoKeys(phraseOne, 21, 8);
    System.out.println("Answer 2: " + AnsTwo);

    // 4.
    // for errors.txt
    System.out.println("This question concerns errors.txt");
    WordLengths wl = new WordLengths();
    System.out.println("Answer 4: ");
    wl.test_CountWordLengths();

    // 5.
    // for manywords.txt
    System.out.println("This question concerns manywords.txt");
    System.out.println("Answer 5: ");
    wl.test_CountWordLengths();


    // 6.
    String phraseSix = "Hfs cpwewloj loks cd Hoto kyg Cyy.";

    String AnsSix = cc.encryptTwoKeys(phraseSix, 26 - 14, 26 - 24);
    System.out.println("Answer 6: " + AnsSix);

    // 7.
    String phraseSeven = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
    CaesarBreaker breaker = new CaesarBreaker();
    String ansSeven = breaker.decrypt(phraseSeven);
    System.out.println(ansSeven);

    // 8 & 9.
    System.out.println("This question concerns mysteryTwoKeysQuiz.txt");
    FileResource fr = new FileResource();
    String messageEight = fr.asString();
    String ansEight = breaker.twoKeyDecrypt(messageEight);
    System.out.println(ansEight);

  }
}
