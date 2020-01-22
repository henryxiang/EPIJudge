package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class TreeFromPreorderWithNull {
  private static int i = 0;

  public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
    // TODO - you fill in here.
    if (preorder == null) return null;
    i = 0;
    return getRoot(preorder);
  }

  private static BinaryTreeNode<Integer> getRoot(List<Integer> preorder) {
    Integer key = preorder.get(i++);
    if (key == null) return null;
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(key);
    root.left = getRoot(preorder);
    root.right = getRoot(preorder);
    return root;
  }

  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }
    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
