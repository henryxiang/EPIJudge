package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SubstringMatchRabinKarp {
  @EpiTest(testDataFile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String t, String s) {
    // TODO - you fill in here.
    if (t.isEmpty() || t.length() < s.length()) return -1;
    if (s.isEmpty()) return 0;
    int b = 26, p = 1, len = s.length();
    for (int i = 0; i < s.length() - 1; i++) p *= b;
    int hs = hash(s, b);
    int ht = hash(t.substring(0, s.length()), b);
    for (int i = 0; i <= t.length()-len; i++) {
      if (i == 0) {
        ht = hash(t.substring(0, len), b);
      } else {
        ht = hash(ht, p, b, t.charAt(i-1), t.charAt(i+len-1));
      }
      if (hs == ht) {
        int j = i, k = 0;
        while(k < len && t.charAt(j++) == s.charAt(k++)) continue;
        if (k == s.length()) return i;
      }
    }
    return -1;
  }

  private static int hash(String s, int b) {
    int h = 0;
    for (char c : s.toCharArray()) h = b*h + c;
    return h;
  }

  private static int hash(int h, int p, int b, char c1, char c2) {
    return (h - c1*p)*b + c2;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatchRabinKarp.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
