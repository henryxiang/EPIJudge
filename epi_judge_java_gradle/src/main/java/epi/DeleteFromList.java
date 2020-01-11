package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class DeleteFromList {

  // Delete the node immediately following aNode. Assumes aNode is not a tail.
  public static void deleteList(ListNode<Integer> aNode) {
    // TODO - you fill in here.
    ListNode<Integer> d = aNode.next;
    aNode.next = aNode.next.next;
    d.next = null;
    return;
  }

  @EpiTest(testDataFile = "delete_from_list.tsv")
  public static ListNode<Integer> testDeleteList(TimedExecutor executor,
               ListNode<Integer> l, int nodeIdx) throws Exception {
    ListNode<Integer> node = l;
    while (nodeIdx > 1) {
      node = node.next;
      --nodeIdx;
    }

    final ListNode<Integer> finalNode = node;
    executor.run(() -> deleteList(finalNode));

    return l;
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
