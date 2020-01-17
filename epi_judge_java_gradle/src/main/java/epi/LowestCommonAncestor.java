package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  private static class Result {
    int descendants;
    BinaryTreeNode<Integer> lca;
    public Result(int d, BinaryTreeNode<Integer> l) {
      descendants = d;
      lca = l;
    }
  }

  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    // TODO - you fill in here.
    return findLCA(tree, node0, node1).lca;
  }

  private static Result findLCA(BinaryTreeNode<Integer> tree,
                                BinaryTreeNode<Integer> node0,
                                BinaryTreeNode<Integer> node1) {
    if (tree == null) return new Result(0, null);
    Result l = findLCA(tree.left, node0, node1);
    Result r = findLCA(tree.right, node0, node1);
    if (l.descendants == 2) return l;
    if (r.descendants == 2) return r;
    int d = l.descendants + r.descendants;
    if (tree == node0) d += 1;
    if (tree == node1) d += 1;
    return new Result(d, d == 2 ? tree : null);
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
