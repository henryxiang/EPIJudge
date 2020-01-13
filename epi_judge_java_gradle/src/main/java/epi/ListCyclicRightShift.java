package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L, int k) {
    // TODO - you fill in here.
    if (L == null) return null;
    ListNode<Integer> head = L, tail = head;
    int size = 1;
    while (tail.next != null) {
      tail = tail.next;
      size += 1;
    }
    tail.next = head;
    int n = size - k % size;
    while (n-- > 0) {
      head = head.next;
      tail = tail.next;
    }
    tail.next = null;
    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
