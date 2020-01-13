package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.HashSet;
import java.util.Set;
public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    // TODO - you fill in here.
    return hasOverlapCyclic(l0, l1);
  }

  private static ListNode<Integer> hasCycle(ListNode<Integer> list) {
    ListNode<Integer> fast = list, slow = list;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        slow = list;
        while (fast != slow) {
          fast = fast.next;
          slow = slow.next;
        }
        return slow;
      }
    }
    return null;
  }

  private static int size(ListNode<Integer> list) {
    ListNode<Integer> n = list;
    int length = 0;
    while (n != null) {
      length += 1;
      n = n.next;
    }
    return length;
  }

  private static ListNode<Integer> advanceTo(ListNode<Integer> list, int index) {
    int n = 0;
    ListNode<Integer> node = list;
    while (n++ < index) node = node.next;
    return node;
  }

  private static ListNode<Integer> hasOverlapAcyclic(ListNode<Integer> list1, ListNode<Integer> list2) {
    if (list1 == null || list2 == null) return null;
    int size1 = size(list1);
    int size2 = size(list2);
    ListNode<Integer> n1 = list1, n2 = list2;
    if (size1 > size2) n1 = advanceTo(n1, size1 - size2);
    else if (size1 < size2) n2 = advanceTo(n2, size2 - size1);
    while (n1 != null && n2 != null) {
      if (n1 == n2) return n1;
      n1 = n1.next;
      n2 = n2.next;
    }
    return null;
  }

  private static ListNode<Integer> hasOverlapCyclic(ListNode<Integer> list1, ListNode<Integer> list2) {
    ListNode<Integer> n1 = hasCycle(list1), n2 = hasCycle(list2);
    if (n1 == null && n2 == null) return hasOverlapAcyclic(list1, list2);
    else if (n1 == null || n2 == null) return null;
    if (n1 != n2) {
      if (!onSameLoop(n1, n2)) return null;
      else return n1;
    }
    ListNode<Integer> n = n1;
    int d1 = distance(list1, n), d2 = distance(list2, n);
    n1 = list1;
    n2 = list2;
    if (d1 > d2) n1 = advanceTo(n1, d1 - d2);
    else if (d1 < d2) n2 = advanceTo(n2, d2 - d1);
    while (n1 != n) {
      n1 = n1.next;
      n2 = n2.next;
    }
    return n1;
  }

  private static boolean onSameLoop(ListNode<Integer> n1, ListNode<Integer> n2) {
    ListNode<Integer> n = n1.next;
    while (n != n1) {
      if (n == n2) return true;
      n = n.next;
    }
    return false;
  }

  private static int distance(ListNode<Integer> from, ListNode<Integer> to) {
    int d = 0;
    ListNode<Integer> n = from;
    while (n != null && n != to) {
      n = n.next;
      d += 1;
    }
    return n == to ? d : 0;
  }

  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
