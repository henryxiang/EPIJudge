package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    if (x == 0) return "0";
    String sign = x < 0 ? "-" : "";
    StringBuilder stringBuilder = new StringBuilder();
    x = Math.abs(x);
    while (x > 0) {
      stringBuilder.append(x % 10);
      x /= 10;
    }
    stringBuilder.append(sign);
    return stringBuilder.reverse().toString();
  }

  public static int stringToInt(String s) {
    // TODO - you fill in here.
    if (s == null || s.isEmpty()) throw new IllegalArgumentException();
    int sign = 1, start = 0;
    if (s.charAt(0) == '-') {
      sign = -1;
      start = 1;
    }
    int n = 0;
    for (int i = start; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c < '0' || c > '9') throw new IllegalArgumentException();
      n = 10*n + (c - '0');
    }
    return sign*n;
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
