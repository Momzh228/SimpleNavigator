package edu.school21.algorithm;

import edu.school21.exception.ImpossibleBuildSpanningTreeException;
import edu.school21.graph.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgorithmPrima {

  List<List<Integer>> spanningTree;

  public List<List<Integer>> GetLeastSpanningTree(Graph graph)
      throws ImpossibleBuildSpanningTreeException {
    Set<Integer> visitedVertex = new HashSet<>();
    initSet(graph, visitedVertex);
    while (visitedVertex.size() < graph.vertexCount()) {
      int minEdge = Integer.MAX_VALUE;
      int startVertex = -1;
      int endVertex = -1;
      for (int vertex : visitedVertex) {
        List<Integer> neighbors = graph.getEdges(vertex);
        for (int i = 0; i < neighbors.size(); i++) {
          if (neighbors.get(i) < minEdge
              && neighbors.get(i) != 0
              && !visitedVertex.contains(i + 1)) {
            startVertex = vertex;
            minEdge = neighbors.get(i);
            endVertex = i + 1;
          }
        }
      }
      if (startVertex == -1 || endVertex == -1) {
        throw new ImpossibleBuildSpanningTreeException();
      }
      spanningTree.get(startVertex - 1).set(endVertex - 1, minEdge);
      spanningTree.get(endVertex - 1).set(startVertex - 1, minEdge);
      visitedVertex.add(endVertex);
    }
    return spanningTree;
  }

  private void initSet(Graph graph, Set<Integer> visitedVertexs) {
    spanningTree = new ArrayList<>();
    for (int i = 0; i < graph.vertexCount(); ++i) {
      List<Integer> list = new ArrayList<>();
      for (int j = 0; j < graph.vertexCount(); j++) {
        list.add(0);
      }
      spanningTree.add(list);
    }
    visitedVertexs.add(1);
  }
}
