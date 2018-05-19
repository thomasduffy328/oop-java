package week1;
import java.util.Random;
import java.util.Scanner;

public class Simulate {

  // generalize this to print out the entire table
  public void diceSim(int rolls) {
    Random rand = new Random();
    // change to count d1 & d2 separately
    int[] counts = new int [13];

    for(int i = 0; i < rolls; i++) {
      int d1 = rand.nextInt(6) + 1;
      int d2 = rand.nextInt(6) + 1;

      counts[d1 + d2] += 1;
    }

    for(int sum = 2; sum < 13; sum++) {
      System.out.println(sum + "'s= " + counts[sum] + "\t Percent: " +  100.00 * counts[sum]/rolls);
    }
  }

  public void userSim(int runs) {
    Simulate sim = new Simulate();
    sim.diceSim(runs);
  }

  public static void main(String ars[]) {
    Scanner kb = new Scanner(System.in);
    System.out.print("Enter a number of simulations: ");
    int simRuns = kb.nextInt();

    Simulate diceRun = new Simulate();
    diceRun.userSim(simRuns);
  }

}
