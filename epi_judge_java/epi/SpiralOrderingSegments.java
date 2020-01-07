package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrderingSegments {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    if (squareMatrix.size() == 0) return new ArrayList<>();
    int top = 0, left = 0, height = squareMatrix.size(), width = squareMatrix.get(0).size();
    List<Integer> result = new ArrayList<>();
    while (width > 0 && height > 0) {
      getRing(squareMatrix, top++, left++, width, height, result);
      width -= 2;
      height -= 2;
    }
    return result;
  }

  private static void getRing(List<List<Integer>> m, int top, int left, int w, int h, List<Integer> list) {
    if (w == 1 && h == 1) {
      list.add(m.get(top).get(left));
      return;
    }
    int right = left+w-1, bottom = top+h-1;
    for (int i = left; i < right; i++) list.add(m.get(top).get(i));
    for (int i = top; i < bottom; i++) list.add(m.get(i).get(right));
    for (int i = right; i > left; i--) list.add(m.get(bottom).get(i));
    for (int i = bottom; i > top; i--) list.add(m.get(i).get(left));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
