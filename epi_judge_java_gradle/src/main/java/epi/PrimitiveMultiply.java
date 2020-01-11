package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    // TODO - you fill in here.
    long result = 0L;
    int i = 0;
    while (x != 0) {
      if ((x & 1) > 0) result = add(result, y << i);
      x = x >>> 1;
      ++i;
    }
    return result;
  }

  private static long add(long x, long y) {
    long sum = x ^ y;
    long carry = x & y;
    while (carry > 0) {
      long c = sum & (carry << 1);
      sum ^= carry << 1;
      carry = c;
    }
    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
