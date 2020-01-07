package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    // TODO - you fill in here.
    for (int i = 0; i < 9; i++) {
      if (!isValidRow(partialAssignment, i) || !(isValidCol(partialAssignment, i))) return false;
    }
    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j += 3) {
        if (!isValidSquare(partialAssignment, i, j)) return false;
      }
    }
    return true;
  }

  private static boolean isValidRow(List<List<Integer>> square, int row) {
    boolean[] used = new boolean[10];
    for (int i = 0; i < 9; i++) {
      int n = square.get(row).get(i);
      if (n == 0) continue;
      if (used[n]) return false;
      used[n] = true;
    }
    return true;
  }

  private static boolean isValidCol(List<List<Integer>> square, int col) {
    boolean[] used = new boolean[10];
    for (int i = 0; i < 9; i++) {
      int n = square.get(i).get(col);
      if (n == 0) continue;
      if (used[n]) return false;
      used[n] = true;
    }
    return true;
  }

  private static boolean isValidSquare(List<List<Integer>> square, int row, int col) {
    boolean[] used = new boolean[10];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int n = square.get(row+i).get(col+j);
        if (n == 0) continue;
        if (used[n]) return false;
        used[n] = true;
      }
    }
    return true;
  }
  
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
