package edu.school21.algorithm;

import edu.school21.collection.Stack;
import edu.school21.graph.Graph;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmDepthFirstSearch {
  public List<Integer> depthFirstSearch(Graph graph, int start_vertex) {
    List<Integer> result = new ArrayList<>();
    edu.school21.collection.Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[graph.vertexCount()];
    stack.push(start_vertex);
    visited[start_vertex - 1] = true;
    while (!stack.isEmpty()) {
      int vertex = stack.pop();
      result.add(vertex);
      List<Integer> edges = graph.getEdges(vertex);
      for (int i = edges.size() - 1; i >= 0; i--) {
        int edge = edges.get(i);
        if (!visited[i] && edge != 0) {
          visited[i] = true;
          stack.push(i + 1);
        }
      }
    }
    return result;
  }
}
