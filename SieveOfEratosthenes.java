package primes;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class SieveOfEratosthenes {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Get primes less than: ");
    int num = sc.nextInt();
    sc.close();

    try {
      File file = new File("PrimesTo"+num+".txt");
      file.createNewFile();
      PrintWriter writer = new PrintWriter(file);

      int[] primes = primes(num);

      for (int i = 2; i < primes.length; i++) {
        System.out.println(primes[i]);
        writer.println(primes[i]);
      }
      writer.close();
    } catch (FileNotFoundException ex) {
      System.out.println("FileNotFoundException");
    } catch (Exception ex) {
      System.out.print(ex);
      return;
    }    
  }

  public static int[] primes(int num) {
    boolean[] primes = new boolean[num];
    for (int i = 0; i < primes.length; i++) {
      primes[i] = true;
    }

    int primsSQRT = (int)(Math.sqrt(primes.length)+1);
    for (int i = 0; i < primes.length; i++) {
      if (primes[i]) { // If i is still canidate for being prime
        for (int j = 2; j < primsSQRT && j < i; j++) {
          if (i % j == 0) {
            primes[i] = false;
            continue;
          }
        }
      }

      // Show Completion status
      if (i % 1000 == 0) {
        System.out.println(""+((double)i/primes.length * 100)+"%");
      }
    }

    ArrayList<Integer> output = new ArrayList<Integer>();
    for (int i = 0; i < primes.length; i++) {
      if (primes[i]) {
        output.add(i);
      }
    }
    
    return output.stream().mapToInt(i -> i).toArray();
  }
}
