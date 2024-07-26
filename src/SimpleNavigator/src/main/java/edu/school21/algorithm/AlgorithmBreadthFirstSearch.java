package edu.school21.algorithm;

import edu.school21.collection.Queue;
import edu.school21.graph.Graph;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmBreadthFirstSearch {
  public List<Integer> breadthFirstSearch(Graph graph, int start_vertex) {
    List<Integer> result = new ArrayList<>();
    edu.school21.collection.Queue<Integer> queue = new Queue<>();
    boolean[] visited = new boolean[graph.vertexCount()];
    queue.push(start_vertex);
    visited[start_vertex - 1] = true;
    while (!queue.isEmpty()) {
      int vertex = queue.pop();
      result.add(vertex);
      List<Integer> edges = graph.getEdges(vertex);
      for (int i = 0; i < edges.size(); i++) {
        int edge = edges.get(i);
        if (edge != 0 && !visited[i]) {
          visited[i] = true;
          queue.push(i + 1);
        }
      }
    }
    return result;
  }
}
