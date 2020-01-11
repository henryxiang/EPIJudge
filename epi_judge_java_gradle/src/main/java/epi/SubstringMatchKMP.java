package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class SubstringMatchKMP {
  @EpiTest(testDataFile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int kmp(String t, String s) {
    if (s.isEmpty()) return 0;
    if (t.isEmpty()) return -1;
    int[] pi = buildPiTable(s);
    int i = 0, j = 0;
    while (i < t.length()) {
      while (i < t.length() && j < s.length() && t.charAt(i) == s.charAt(j)) {
        i += 1;
        j += 1;
      }
      if (j == s.length()) return i - s.length();
      if (j != 0) j = pi[j-1];
      else i += 1;
    }
    return -1;
  }

  private static int[] buildPiTable(String s) {
    int[] pi = new int[s.length()];
    pi[0] = 0;
    int i = 0, j = 1, n = 0;
    while (j < s.length()){
      if (s.charAt(j) == s.charAt(i)) {
        pi[j++] = ++n;
        i += 1;
      } else if (s.charAt(j) == s.charAt(0)){
        i = 1;
        n = 1;
        pi[j++] = n;
      } else {
        i = 0;
        n = 0;
        j += 1;
      }
    }
    System.out.println("\n" + s + ": " + Arrays.toString(pi));
    return pi;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatchKMP.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
