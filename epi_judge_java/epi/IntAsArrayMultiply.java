package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // TODO - you fill in here.
    if (num1 == null || num2 == null || num1.size() == 0 || num2.size() == 0) throw new IllegalArgumentException();
    if (num1.size() == 1 && num1.get(0) == 0 || num2.size() == 1 && num2.get(0) == 0)
      return Arrays.asList(0);
    int sign = num1.get(0) < 0 && num2.get(0) > 0 || num1.get(0) > 0 && num2.get(0) < 0 ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));
    int n = num1.size() + num2.size();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      result.add(0);
    }
    for (int i = num1.size()-1; i >= 0; i--) {
      for (int j = num2.size()-1; j >= 0; j--) {
        int v = result.get(i+j+1);
        v += num1.get(i) * num2.get(j);
        result.set(i+j+1, v);
      }
      for (int k = result.size()-1; k > 0; k--) {
        int d = result.get(k);
        int pd = result.get(k-1);
        pd += d/10;
        d %= 10;
        result.set(k, d);
        result.set(k-1, pd);
      }
    }
    if (result.get(0) == 0) result.remove(0);
    result.set(0, sign*result.get(0));
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
