package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SunsetView {
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    // TODO - you fill in here.
    Deque<Integer> buildings = new LinkedList<>();
    Deque<Integer> ids = new LinkedList<>();
    int id = 0;
    while (sequence.hasNext()) {
      int h = sequence.next();
      while (!buildings.isEmpty() && buildings.peekFirst() <= h) {
        buildings.removeFirst();
        ids.removeFirst();
      }
      buildings.addFirst(h);
      ids.addFirst(id++);
    }
    return ids.stream().collect(Collectors.toList());
  }

  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
