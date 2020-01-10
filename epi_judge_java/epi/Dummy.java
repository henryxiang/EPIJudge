package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestResult;

import java.lang.invoke.MethodHandles;

public class Dummy {

    @EpiTest(testDataFile = "dummy.tsv")
    public static int add(int x, int y) {
        // TODO - you fill in here.
        return x + y;
    }

    public static void main(String[] args) {
        Class testClass = MethodHandles.lookup().lookupClass();
        String testFile = testClass.getSimpleName() + ".java";
        TestResult result = GenericTest.runFromAnnotations(args, testFile, testClass);
        System.exit(result.ordinal());
    }
}
