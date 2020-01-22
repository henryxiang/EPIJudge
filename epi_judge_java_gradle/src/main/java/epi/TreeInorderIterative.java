package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeInorderIterative {
    @EpiTest(testDataFile = "tree_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
        // TODO - you fill in here.
        List<Integer> results = new ArrayList<>();
        BinaryTree<Integer> curr = tree, prev = null, next;
        while (curr != null) {
            if (curr.parent == prev) {
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    results.add(curr.data);
                    next = curr.right != null ? curr.right : curr.parent;
                }
            }
            else if (curr.left == prev) {
                results.add(curr.data);
                next = curr.right != null ? curr.right : curr.parent;
            }
            else {
                next = curr.parent;
            }
            prev = curr;
            curr = next;
        }
        return results;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
                        .ordinal());
    }
}
