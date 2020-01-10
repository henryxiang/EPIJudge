package epi;

import epi.test_framework.*;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;

public class DummyArray {

    public static void swap(int[] a, int i, int j) {
        // TODO - you fill in here.
        if (a == null) throw new IllegalArgumentException("Null array");
        if (i >= a.length || j >= a.length)
            throw new ArrayIndexOutOfBoundsException(String.format("i = %d, j = %d", i, j));
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return;
    }

    @EpiTest(testDataFile = "dummy_array.tsv")
    public static void runTest(TimedExecutor executor, List<Integer> list, int i, int j) throws Exception {
        int[] before = list.stream().mapToInt(n -> n).toArray();
        int[] data = list.stream().mapToInt(n -> n).toArray();
        String[] markers = list.stream()
                .map(n -> n.toString().replaceAll("\\w", " ")).toArray(String[]::new);
        if (i < list.size()) markers[i] = "|";
        if (j < list.size()) markers[j] = "|";
        try {
            swap(data, i, j);
            if (before[i] != data[j] || before[j] != data[i])
                throw new TestFailure("swapped elements do not match:\n" +
                        Arrays.toString(before) + "\n " +
                        String.join("  ", markers) + "\n" +
                        Arrays.toString(data));
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
    }

    public static void main(String[] args) {
        Class testClass = MethodHandles.lookup().lookupClass();
        String testFile = testClass.getSimpleName() + ".java";
        TestResult result = GenericTest.runFromAnnotations(args, testFile, testClass);
        System.exit(result.ordinal());
    }
}
