package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    // TODO - you fill in here.
    ListNode<Integer> head = new ListNode<>(0, L);
    ListNode<Integer> n1 = head.next, n2 = head;
    for (int i = 0; i < k; i++) n1 = n1.next;
    while (n1 != null) {
      n1 = n1.next;
      n2 = n2.next;
    }
    n2.next = n2.next.next;
    return head.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
