package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class ReverseSublist {

  @EpiTest(testDataFile = "reverse_sublist.tsv")
  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start, int finish) {
    // TODO - you fill in here.
    if (L == null || start == finish) return L;
    ListNode<Integer> head = new ListNode<>(0, L), subHead = head;
    for (int i = 1; i < start; i++) subHead = subHead.next;
    ListNode<Integer> cur = subHead.next;
    while(start++ < finish) {
      ListNode<Integer> temp = cur.next;
      cur.next = temp.next;
      temp.next = subHead.next;
      subHead.next = temp;
    }
    return head.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
