package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class TwoSum {
  @EpiTest(testDataFile = "two_sum.tsv")
  public static boolean hasTwoSum(List<Integer> A, int t) {
    // TODO - you fill in here.
    Set<Integer> num = new HashSet<>();
    for (Integer i : A) num.add(i);
    for (Integer i : A) {
      if (num.contains(t-i)) return true;
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
