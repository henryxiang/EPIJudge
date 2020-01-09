package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    // TODO - you fill in here.
    String s = "1";
    for (int i = 0; i < n; i++) {
      s = nextNumber(s);
    }
    return s;
  }

  private static String nextNumber(String s) {
    StringBuilder stringBuilder = new StringBuilder();
    char d = s.charAt(0);
    int i = 0;
    while (i < s.length()) {
      int n = 0;
      while(s.charAt(i++) == d) n++;
      stringBuilder.append(n).append(d);
      if (i < s.length()) d = s.charAt(i);
    }
    return stringBuilder.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
