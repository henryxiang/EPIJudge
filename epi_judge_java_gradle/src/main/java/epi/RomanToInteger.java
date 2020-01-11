package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
  private static final Map<Character, Integer> R = new HashMap<>();
  @EpiTest(testDataFile = "roman_to_integer.tsv")
  public static int romanToInteger(String s) {
    // TODO - you fill in here.
    init();
    int n = R.get(s.charAt(s.length()-1));
    for (int i = s.length()-2; i >= 0; i--) {
      int d1 = R.get(s.charAt(i));
      int d2 = R.get(s.charAt(i+1));
      if (d1 < d2) n -= d1;
      else n += d1;
    }
    return n;
  }

  private static void init() {
    R.put('I', 1);
    R.put('V', 5);
    R.put('X', 10);
    R.put('L', 50);
    R.put('C', 100);
    R.put('D', 500);
    R.put('M', 1000);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
