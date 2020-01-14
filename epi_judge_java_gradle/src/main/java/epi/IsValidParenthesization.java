package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    // TODO - you fill in here.
    Deque<Character> paren = new LinkedList<>();
    Map<Character, Character> pair = new HashMap<>();
    pair.put('(', ')');
    pair.put('[', ']');
    pair.put('{', '}');
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') paren.push(c);
      if (c == ')' || c == ']' || c == '}') {
        if (paren.isEmpty() || pair.get(paren.pop()) != c) return false;
      }
    }
    return paren.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
