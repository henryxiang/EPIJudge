package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    int last = A.size() - 1;
    A.set(last, A.get(last)+1);
    for (int i = last; i > 0; i--) {
      int d = A.get(i);
      int p = A.get(i-1);
      if (d < 10) break;
      A.set(i, d%10);
      p += d/10;
      A.set(i-1, p);
    }
    if (A.get(0) == 10) {
      A.set(0, 1);
      A.add(0);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
