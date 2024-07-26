package edu.school21.graphalgorithms;

import edu.school21.algorithm.*;
import edu.school21.exception.ImpossibleBuildSpanningTreeException;
import edu.school21.exception.InvalidMatrixException;
import edu.school21.exception.PathToVertexNotExistException;
import edu.school21.graph.Graph;
import edu.school21.model.TsmResult;
import java.util.*;

public class GraphAlgorithms {

  public int GetShortestPathBetweenVertices(Graph graph, int vertex1, int vertex2)
      throws PathToVertexNotExistException {
    return new AlgorithmDijkstra().GetShortestPathBetweenVertices(graph, vertex1, vertex2);
  }

  public List<List<Integer>> GetShortestPathsBetweenAllVertices(Graph graph) {
    return new AlgorithmFloydWarshall().GetShortestPathsBetweenAllVertices(graph);
  }

  public List<Integer> depthFirstSearch(Graph graph, int start_vertex) {
    return new AlgorithmDepthFirstSearch().depthFirstSearch(graph, start_vertex);
  }

  public List<Integer> breadthFirstSearch(Graph graph, int start_vertex) {
    return new AlgorithmBreadthFirstSearch().breadthFirstSearch(graph, start_vertex);
  }

  public TsmResult SolveTravelingSalesmanProblem(Graph graph) throws InvalidMatrixException {
    return new AlgorithmAntColony().SolveTravelingSalesmanProblem(graph);
  }

  public List<List<Integer>> GetLeastSpanningTree(Graph graph)
      throws ImpossibleBuildSpanningTreeException {
    return new AlgorithmPrima().GetLeastSpanningTree(graph);
  }
}
