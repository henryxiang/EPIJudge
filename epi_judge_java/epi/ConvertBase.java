package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class ConvertBase {
  private static final char[] DIGITS = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
      'A', 'B', 'C', 'D', 'E', 'F'
  };

  @EpiTest(testDataFile = "convert_base.tsv")
  public static String convertBase(String numAsString, int b1, int b2) {
    // TODO - you fill in here.
    if ("0".equals(numAsString) || "-0".equals(numAsString)) return numAsString;
    return intToString(stringToInt(numAsString, b1), b2);
  }

  private static int stringToInt(String str, int base) {
    if (str == null || str.isEmpty()) throw new IllegalArgumentException();
    Map<Character, Integer> digits = new HashMap<>();
    for (int i = 0; i < DIGITS.length; i++) digits.put(DIGITS[i], i);
    int sign = 1, start = 0;
    if (str.charAt(0) == '-') {
      sign = -1;
      start = 1;
    }
    int n = 0;
    for (int i = start; i < str.length(); i++) {
      char c = str.charAt(i);
      if (!digits.containsKey(c)) throw new IllegalArgumentException();
      n = n*base + digits.get(c);
    }
    return sign * n;
  }

  private static String intToString(int num, int base) {
    if (num == 0) return "0";
    String sign = num < 0 ? "-" : "";
    StringBuilder stringBuilder = new StringBuilder();
    int x = Math.abs(num);
    while (x > 0) {
      int i = x % base;
      stringBuilder.append(DIGITS[i]);
      x /= base;
    }
    stringBuilder.append(sign);
    return stringBuilder.reverse().toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
