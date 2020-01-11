package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    // TODO - you fill in here.
    boolean[] prime = new boolean[n+1];
    Arrays.fill(prime, true);
    for (int i = 2; i <= n; i++) {
      if (!prime[i]) continue;
      for (int j = 2; i*j <= n; j++) prime[i*j] = false;
    }
    List<Integer> results = new ArrayList<>();
    for (int i = 2; i < prime.length; i++) {
      if(prime[i]) results.add(i);  
    }
    return results;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
