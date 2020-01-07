package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    // TODO - you fill in here.
    if (perm.size() < 2) return Collections.emptyList();
    int i = perm.size()-2;
    while(i >= 0 && perm.get(i).compareTo(perm.get(i+1)) >= 0) i--;
    if (i < 0) return Collections.emptyList();
    List<Integer> next = new ArrayList<>(perm);
    int j = perm.size()-1;
    while(perm.get(j).compareTo(perm.get(i)) <= 0) j--;
    Collections.swap(next, i, j);
    j = perm.size()-1;
    i++;
    while (i < j) Collections.swap(next, i++, j--);
    return next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
