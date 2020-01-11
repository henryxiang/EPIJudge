package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {

  private static final String[] T = {
          "0", "1", "ABC", "DEF", "GHI",
          "JKL", "MNO", "PQRS", "TUV", "WXYZ"
  };

  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String phoneNumber) {
    // TODO - you fill in here.
    List<String> results = new ArrayList<>();
    buildWordList(phoneNumber, 0, "", results);
    return results;
  }

  public static void buildWordList(String phoneNumber, int i, String partial, List<String> results) {
    if (i >= phoneNumber.length()) {
      results.add(partial);
      return;
    }
    int d = phoneNumber.charAt(i) - '0';
    for (char c : T[d].toCharArray()) {
      buildWordList(phoneNumber, i+1, partial + c, results);
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
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
