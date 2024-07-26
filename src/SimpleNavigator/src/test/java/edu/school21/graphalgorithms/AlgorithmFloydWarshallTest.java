package edu.school21.graphalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.school21.graph.Graph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AlgorithmFloydWarshallTest {
  private static GraphAlgorithms graphAlgorithms;

  @BeforeAll
  public static void setUp() {
    graphAlgorithms = new GraphAlgorithms();
  }

  private String getPath(String fileName) {
    return getClass().getClassLoader().getResource(fileName).getPath();
  }

  @Test
  public void testFloydWarshall() throws IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("DIJKSTRA.txt"));
    List<List<Integer>> allPathsEq = new ArrayList<>();
    allPathsEq.add(new ArrayList<>(List.of(0, 3, 7, 10, 4, 9)));
    allPathsEq.add(new ArrayList<>(List.of(Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 7, 1, 6)));
    allPathsEq.add(
        new ArrayList<>(
            List.of(
                Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 5, Integer.MAX_VALUE, Integer.MAX_VALUE)));
    allPathsEq.add(
        new ArrayList<>(
            List.of(
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                0,
                Integer.MAX_VALUE,
                Integer.MAX_VALUE)));
    allPathsEq.add(
        new ArrayList<>(List.of(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0, 5)));
    allPathsEq.add(
        new ArrayList<>(
            List.of(
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 0)));
    List<List<Integer>> allPaths = graphAlgorithms.GetShortestPathsBetweenAllVertices(graph);
    for (int i = 0; i < allPaths.size(); i++) {
      assertEquals(allPathsEq.get(i), allPaths.get(i));
    }
  }
}
