package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class SpreadsheetEncoding {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {
    // TODO - you fill in here.
    Map<Character, Integer> digits = new HashMap<>();
    int i = 1;
    for (char c = 'A'; c <= 'Z'; c++) digits.put(c, i++);
    int n = 0;
    for (char c : col.toCharArray()) n = 26*n + digits.get(c);
    return n;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
