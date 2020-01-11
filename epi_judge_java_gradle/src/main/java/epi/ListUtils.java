package epi;

public class ListUtils {
    public static <T> ListNode<T> index(ListNode<T> list, int i) {
        if (list == null) return null;
        ListNode<T> cur = list;
        int j = i;
        while (j-- > 0 && cur != null) cur = cur.next;
        if (j >= 0) throw new IndexOutOfBoundsException("Invalid index i = " + i);
        return cur;
    }

    public static <T> ListNode<T> createList(T[] data) {
        if (data == null || data.length == 0) return null;
        ListNode<T> head = new ListNode<>(data[0], null),
                    tail = head;
        for (int i = 1; i < data.length; i++) {
            tail = append(tail, new ListNode<>(data[i], null));
        }
        return head;
    }

    public static <T> ListNode<T> append(ListNode<T> head, ListNode<T> e) {
        ListNode<T> dummyHead = new ListNode(null, head), tail = dummyHead;
        while (tail.next != null) tail = tail.next;
        tail.next = e;
        tail = tail.next;
        return tail;
    }

    public static <T> ListNode<T> prepend(ListNode<T> head, ListNode<T> e) {
        ListNode<T> dummyHead = new ListNode(null, head);
        e.next = dummyHead.next;
        dummyHead.next = e;
        return dummyHead.next;
    }

    public static <T> int length(ListNode<T> head) {
        ListNode<T> node = head;
        int length = 0;
        while (node != null) {
            length += 1;
            node = node.next;
        }
        return length;
    }

    public static <T extends Comparable<T>> boolean isEqual(ListNode<T> a, ListNode<T> b) {
        while (a != null && b != null) {
            if (a.data.compareTo(b.data) != 0) return false;
            a = a.next;
            b = b.next;
        }
        if (a == null && b != null || a != null && b == null) return false;
        return true;
    }

    public static <T> ListNode<T> reverseList(ListNode<T> list) {
        ListNode<T> dummyHead = new ListNode<>(null, list),
                    tail = list;
        while (tail != null && tail.next != null) {
            ListNode<T> temp = tail.next;
            tail.next = temp.next;
            temp.next = dummyHead.next;
            dummyHead.next = temp;
        }
        return dummyHead.next;
    }
}
