package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    // TODO - you fill in here.
    if (x < 0) return false;
    return x == reverseDigits(x);
  }

  private static long reverseDigits(int x) {
    long result = 0;
    while (x > 0) {
      result = 10*result + x%10;
      x /= 10;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
