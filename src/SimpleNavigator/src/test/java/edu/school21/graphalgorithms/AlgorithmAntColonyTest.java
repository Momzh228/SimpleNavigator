package edu.school21.graphalgorithms;

import static org.junit.jupiter.api.Assertions.*;

import edu.school21.exception.InvalidMatrixException;
import edu.school21.graph.Graph;
import edu.school21.model.TsmResult;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AlgorithmAntColonyTest {
  private static GraphAlgorithms graphAlgorithms;

  @BeforeAll
  public static void setUp() {
    graphAlgorithms = new GraphAlgorithms();
  }

  private String getPath(String fileName) {
    return getClass().getClassLoader().getResource(fileName).getPath();
  }

  @Test
  public void testSalesman1() throws InvalidMatrixException, IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("salesman1.txt"));
    double expectedLength = 4.0;
    TsmResult result = graphAlgorithms.SolveTravelingSalesmanProblem(graph);
    assertEquals(expectedLength, result.getDistance(), 1);
  }

  @Test
  public void testSalesman2() throws InvalidMatrixException, IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("salesman2.txt"));
    double expectedLength = 4.0;
    TsmResult result = graphAlgorithms.SolveTravelingSalesmanProblem(graph);
    assertEquals(expectedLength, result.getDistance(), 1);
  }

  @Test
  public void testSalesman3() throws InvalidMatrixException, IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("salesman3.txt"));
    double expectedLength = 174.0;
    TsmResult result = graphAlgorithms.SolveTravelingSalesmanProblem(graph);
    assertEquals(expectedLength, result.getDistance(), 5);
  }

  @Test
  public void testSalesman4() throws IOException {
    Graph graph = new Graph();
    graph.LoadGraphFromFile(getPath("salesman4.txt"));
    Exception ex =
        assertThrows(
            InvalidMatrixException.class,
            () -> {
              graphAlgorithms.SolveTravelingSalesmanProblem(graph);
            });

    String expectedMessage = "Can not solve. Invalid graph or low count of iterations.";
    String actualMessage = ex.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}
