package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    // TODO - you fill in here.
    if (L == null || L.next == null) return L;
    ListNode<Integer> headEven = L, headOdd = L.next, tailEven = headEven, tailOdd = headOdd;
    while (tailOdd != null && tailOdd.next != null) {
      tailEven.next = tailOdd.next;
      tailEven = tailEven.next;
      tailOdd.next = tailEven.next;
      tailOdd = tailOdd.next;
    }
    tailEven.next = headOdd;
    return headEven;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
