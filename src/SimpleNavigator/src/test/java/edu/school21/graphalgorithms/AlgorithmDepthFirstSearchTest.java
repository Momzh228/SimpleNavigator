package edu.school21.graphalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.school21.graph.Graph;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AlgorithmDepthFirstSearchTest {
  private static GraphAlgorithms graphAlgorithms;

  @BeforeAll
  public static void setUp() {
    graphAlgorithms = new GraphAlgorithms();
  }

  private String getPath(String fileName) {
    return getClass().getClassLoader().getResource(fileName).getPath();
  }

  @Test
  public void testDFSExample1() throws IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("BFSDFS1.txt"));
    List<Integer> expected = Arrays.asList(1, 2, 4, 3);
    assertEquals(expected, graphAlgorithms.depthFirstSearch(graph, 1));
  }

  @Test
  public void testDFSExample2() throws IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("BFSDFS2.txt"));
    List<Integer> expected = Arrays.asList(1, 2, 3, 4);
    assertEquals(expected, graphAlgorithms.depthFirstSearch(graph, 1));
  }

  @Test
  public void testDFSExample3() throws IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("BFSDFS3.txt"));
    List<Integer> expected = Arrays.asList(1, 2);
    assertEquals(expected, graphAlgorithms.depthFirstSearch(graph, 1));
  }
}
