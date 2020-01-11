package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    // TODO - you fill in here.
    if (L == null) return true;
    ListNode<Integer> first = L, mid = L;
    while(first != null && first.next != null) {
      mid = mid.next;
      first = first.next.next;
    }
    first = L;
    mid = reverseList(mid.next);
    while (mid != null) {
      if (first.data.intValue() != mid.data.intValue()) return false;
      first = first.next;
      mid = mid.next;
    }
    return true;
  }

  private static ListNode<Integer> reverseList(ListNode<Integer> list) {
    ListNode<Integer> dummyHead = new ListNode<>(0, list),
            tail = dummyHead.next;
    while (tail != null && tail.next != null) {
      ListNode<Integer> temp = tail.next;
      tail.next = temp.next;
      temp.next = dummyHead.next;
      dummyHead.next = temp;
    }
    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
