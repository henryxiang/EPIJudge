package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    // TODO - you fill in here.
    for (int i = 0; i < perm.size(); i++) {
      if (perm.get(i) < 0) continue;
      int t = A.get(i);
      while (perm.get(i) >= 0) {
        int j = perm.get(i);
        t = replace(A, j, t);
        perm.set(i, j-perm.size());
        i = j;
      }
    }
    for (int i = 0; i < perm.size(); i++) {
      perm.set(i, perm.get(i)+perm.size());
    }
    return;
  }

  private static int replace(List<Integer> A, int i, int num) {
    int t = A.get(i);
    A.set(i, num);
    return t;
  }

  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
