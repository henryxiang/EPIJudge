package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    // TODO - you fill in here.
    ListNode<Integer> sumHead = new ListNode<>(0, null), sumTail = sumHead,
            it1 = L1, it2 = L2;
    int carry = 0;
    while (it1 != null || it2 != null) {
      int s = 0;
      if (it1 != null) s += it1.data;
      if (it2 != null) s += it2.data;
      sumTail.next = new ListNode<>((s + carry) % 10, null);
      carry = (s + carry) / 10;
      if (it1 != null) it1 = it1.next;
      if (it2 != null) it2 = it2.next;
      sumTail = sumTail.next;
    }
    if (carry > 0) sumTail.next = new ListNode<>(carry, null);
    return sumHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
