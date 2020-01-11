package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class RunLengthCompression {

  public static String decoding(String s) {
    // TODO - you fill in here.
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      int n = 0;
      while(i < s.length() && Character.isDigit(s.charAt(i))) {
        n = 10*n + (s.charAt(i) - '0');
        i += 1;
      }
      for (int k = 0; k < n; k++) {
        stringBuilder.append(s.charAt(i));
      }
    }
    return stringBuilder.toString();
  }

  public static String encoding(String s) {
    // TODO - you fill in here.
    StringBuilder stringBuilder = new StringBuilder();
    char c = s.charAt(0);
    int i = 0;
    while (i < s.length()) {
      int n = 0;
      while (i < s.length() && s.charAt(i) == c) {
        n += 1;
        i += 1;
      }
      stringBuilder.append(n).append(c);
      if (i < s.length()) c = s.charAt(i);
    }
    return stringBuilder.toString();
  }
  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
