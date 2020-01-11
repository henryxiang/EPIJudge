package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.EpiTestComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class ValidIpAddresses {
  @EpiTest(testDataFile = "valid_ip_addresses.tsv")
  public static List<String> getValidIpAddress(String s) {
    // TODO - you fill in here.
    List<String> results = new ArrayList<>();
    buildIpString(s, 0, 1, "", results);
    return results;
  }

  private static void buildIpString(String s, int i, int p, String partial, List<String> results) {
    if (i >= s.length() && p == 5) {
      results.add(partial);
      return;
    }
    else if (p > 4) {
      return;
    }
    String seg = "";
    for (int j = i; j < i+3 && j < s.length(); j++) {
      if ("0".equals(seg)) continue;
      seg += s.charAt(j);
      if (Integer.parseInt(seg) <= 255) {
        String newPartial = "".equals(partial) ? seg : partial + "." + seg;
        buildIpString(s, j+1, p+1, newPartial, results);
      }
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
