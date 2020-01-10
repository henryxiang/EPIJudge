package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.xml.sax.ext.Locator2;

public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {
    // TODO - you fill in here.
    ListNode<Integer> node1 = L1, node2 = L2, head = new ListNode<>(0, null), tail = head;
    while (node1 != null || node2 != null) {
      if (node1 == null) {
        tail.next = node2;
        node2 = null;
      }
      else if (node2 == null) {
        tail.next = node1;
        node1 = null;
      }
      else if (node1.data.intValue() <= node2.data.intValue()) {
        tail.next = node1;
        node1 = node1.next;
        tail = tail.next;
      }
      else {
        tail.next = node2;
        node2 = node2.next;
        tail = tail.next;
      }
    }
    return head.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
