package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    // TODO - you fill in here.
    int power = 0;
    long yp = y;
    while (yp <= x) {
      yp <<= 1;
      power += 1;
    }
    int result = 0;
    while (x >= y) {
      while (yp > x) {
        yp >>>= 1;
        power -= 1;
      }
      result += 1 << power;
      x -= yp;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
