package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    // TODO - you fill in here.
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      Integer[] row = new Integer[i+1];
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) {
          row[j] = 1;
        } else {
          row[j] = result.get(i-1).get(j-1) + result.get(i-1).get(j);
        }
      }
      result.add(Arrays.asList(row));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
