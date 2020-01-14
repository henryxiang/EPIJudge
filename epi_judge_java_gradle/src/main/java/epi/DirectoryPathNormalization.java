package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    // TODO - you fill in here.
    Deque<String> directories = new LinkedList<>();
    for (String d : path.split("\\/")) {
      if (".".equals(d) || d.isEmpty()) continue;
      if ("..".equals(d)) {
        if (directories.isEmpty() || "..".equals(directories.peekLast())) directories.addLast(d);
        else directories.removeLast();
      }
      else directories.addLast(d);
    }
    String p = directories.stream().collect(Collectors.joining("/"));
    if (path.charAt(0) == '/') p = "/" + p;
    return p.isEmpty() ? "/" : p;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
