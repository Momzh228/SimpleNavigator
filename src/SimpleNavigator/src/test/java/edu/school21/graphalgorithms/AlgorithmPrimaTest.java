package edu.school21.graphalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.school21.exception.ImpossibleBuildSpanningTreeException;
import edu.school21.graph.Graph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AlgorithmPrimaTest {

  private static GraphAlgorithms graphAlgorithms;

  @BeforeAll
  public static void setUp() {
    graphAlgorithms = new GraphAlgorithms();
  }

  private String getPath(String fileName) {
    return getClass().getClassLoader().getResource(fileName).getPath();
  }

  @Test
  public void testPrima() throws IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("PRIMA.txt"));
    List<List<Integer>> allPathsEq = new ArrayList<>();
    allPathsEq.add(new ArrayList<>(List.of(0, 0, 1, 0, 0, 0)));
    allPathsEq.add(new ArrayList<>(List.of(0, 0, 5, 0, 3, 0)));
    allPathsEq.add(new ArrayList<>(List.of(1, 5, 0, 0, 0, 4)));
    allPathsEq.add(new ArrayList<>(List.of(0, 0, 0, 0, 0, 2)));
    allPathsEq.add(new ArrayList<>(List.of(0, 3, 0, 0, 0, 0)));
    allPathsEq.add(new ArrayList<>(List.of(0, 0, 4, 2, 0, 0)));
    List<List<Integer>> allPaths = null;
    try {
      allPaths = graphAlgorithms.GetLeastSpanningTree(graph);
    } catch (ImpossibleBuildSpanningTreeException e) {
      throw new RuntimeException(e);
    }
    for (int i = 0; i < allPaths.size(); i++) {
      assertEquals(allPathsEq.get(i), allPaths.get(i));
    }
  }
}
