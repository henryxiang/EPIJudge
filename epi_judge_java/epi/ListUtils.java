package epi;

import epi.test_framework.GenericTest;
import epi.test_framework.TestResult;

import java.lang.invoke.MethodHandles;

public class ListUtils {
    public static <T> ListNode<T> index(ListNode<T> list, int i) {
        if (list == null) return null;
        ListNode<T> cur = list;
        for (int j = 0; j < i && cur.next != null; j++) cur = cur.next;
        if (i != 0) throw new IndexOutOfBoundsException("Invalid index");
        return cur;
    }

    public static void main(String[] args) {
        Class testClass = MethodHandles.lookup().lookupClass();
        String testFile = testClass.getSimpleName() + ".java";
        TestResult result = GenericTest.runFromAnnotations(args, testFile, testClass);
        System.exit(result.ordinal());
    }
}
