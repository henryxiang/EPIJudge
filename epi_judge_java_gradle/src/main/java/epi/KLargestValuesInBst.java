package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    // TODO - you fill in here.
    List<Integer> results = new LinkedList<>();
    reverseOrderTraverse(tree, k, results);
    return results;
  }

  private static void reverseOrderTraverse(BstNode<Integer> tree, int k, List<Integer> results) {
    if (tree == null || results.size() >= k) return;
    reverseOrderTraverse(tree.right, k, results);
    if (results.size() < k) {
      results.add(tree.data);
      reverseOrderTraverse(tree.left, k, results);
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
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
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
