package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    // TODO - you fill in here.
    long bi = (x >>> i) & 1L;
    long bj = (x >>> j) & 1L;
    if (bi == bj) return x;
    long mask = (1L << i) | (1L << j);
    return x ^ mask;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
