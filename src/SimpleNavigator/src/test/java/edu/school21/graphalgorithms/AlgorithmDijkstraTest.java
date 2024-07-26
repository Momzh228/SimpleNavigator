package edu.school21.graphalgorithms;

import edu.school21.exception.PathToVertexNotExistException;
import edu.school21.graph.Graph;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AlgorithmDijkstraTest {
  private static GraphAlgorithms graphAlgorithms;

  @BeforeAll
  public static void setUp() {
    graphAlgorithms = new GraphAlgorithms();
  }

  private String getPath(String fileName) {
    return getClass().getClassLoader().getResource(fileName).getPath();
  }

  @Test
  public void testDijkstra() throws PathToVertexNotExistException, IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("DIJKSTRA.txt"));
    Assertions.assertEquals(10, graphAlgorithms.GetShortestPathBetweenVertices(graph, 1, 4));
  }

  @Test
  public void testDijkstraException1() throws PathToVertexNotExistException, IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("DIJKSTRA2.txt"));
    Assertions.assertThrows(
        PathToVertexNotExistException.class,
        () -> graphAlgorithms.GetShortestPathBetweenVertices(graph, 1, 2));
  }

  @Test
  public void testDijkstraException2() throws PathToVertexNotExistException, IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("DIJKSTRA2.txt"));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> graphAlgorithms.GetShortestPathBetweenVertices(graph, 1, 100));
  }
}
