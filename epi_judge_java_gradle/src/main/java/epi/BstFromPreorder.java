package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BstFromPreorder {
  private static int i;
  @EpiTest(testDataFile = "bst_from_preorder.tsv")
  public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    // TODO - you fill in here.
    i = 0;
    return rebuildBst(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BstNode<Integer> rebuildBst(List<Integer> preorder, int min, int max) {
    if (preorder == null || i >= preorder.size()) return null;
    int v = preorder.get(i);
    if (v < min || v > max) return null;
    BstNode<Integer> root = new BstNode<>(v);
    i++;
    root.left = rebuildBst(preorder, min, v);
    root.right = rebuildBst(preorder, v, max);
    return root;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
