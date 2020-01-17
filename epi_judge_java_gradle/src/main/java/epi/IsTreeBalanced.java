package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  private static class TreeStats {
    int height;
    boolean isBalanced;
    public TreeStats(int h, boolean b) {
      height = h;
      isBalanced = b;
    }
  }
  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here
    return checkTreeBalance(tree).isBalanced;
  }

  private static TreeStats checkTreeBalance(BinaryTreeNode<Integer> tree) {
    if (tree == null) return new TreeStats(0, true);
    TreeStats l = checkTreeBalance(tree.left);
    TreeStats r = checkTreeBalance(tree.right);
    boolean b = l.isBalanced && r.isBalanced && Math.abs(l.height - r.height) < 2;
    return new TreeStats(Math.max(l.height, r.height) + 1, b);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
