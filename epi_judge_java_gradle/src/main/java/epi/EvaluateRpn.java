package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // TODO - you fill in here.
    Deque<Integer> result = new LinkedList<>();
    String[] tokens = expression.split(",");
    for (String token : tokens) {
      int v1, v2;
      switch (token) {
        case "+":
          v2 = result.removeLast();
          v1 = result.removeLast();
          result.addLast(v1 + v2);
          break;
        case "-":
          v2 = result.removeLast();
          v1 = result.removeLast();
          result.addLast(v1 - v2);
          break;
        case "*":
          v2 = result.removeLast();
          v1 = result.removeLast();
          result.addLast(v1 * v2);
          break;
        case "/":
          v2 = result.removeLast();
          v1 = result.removeLast();
          result.addLast(v1 / v2);
          break;
        default:
          result.addLast(Integer.parseInt(token));
          break;
      }
    }
    return result.peekLast();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
