package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {

  private static long[] rev;
  public static void init() {
    rev = new long[16];
    for (int i = 0; i < 16; i++) {
      rev[i] = (long) (
        ((i & 1) << 3) |
        ((i & 2) << 1) |
        ((i & 4) >>> 1) |
        ((i & 8) >>> 3)
      );
    }
  }

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // TODO - you fill in here.
    init();
    long result = 0;
    long mask = 0xf;
    for (int i = 0; i < 16; i++) {
      int n = (int)(x & mask);
      result = (result << 4) | rev[n];
      x >>>= 4;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
