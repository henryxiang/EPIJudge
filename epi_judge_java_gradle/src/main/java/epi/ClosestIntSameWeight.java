package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    // TODO - you fill in here.
    for (int i = 0; i < Long.SIZE - 1; i++) {
      if ((x >>> i & 1) != (x >>> i+1 & 1)) {
        return x ^ (3 << i);
      }
    }
    throw new IllegalArgumentException();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
