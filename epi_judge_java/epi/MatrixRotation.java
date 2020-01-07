package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    int N = squareMatrix.size()-1;
    for (int i = 0; i <= N/2; i++) {
      for (int j = i; j < N - i ; j++) {
        int t = squareMatrix.get(i).get(j);
        int x = N - i, y = j;
        for (int k = 0; k < 4; k++) {
          t = replace(squareMatrix, x, y, t);
          int y1 = y;
          y = x;
          x = N - y1;
        }
      }
    }
    return;
  }

  private static int replace(List<List<Integer>> m, int x, int y, int num) {
    int t = m.get(y).get(x);
    m.get(y).set(x, num);
    return t;
  }

  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
