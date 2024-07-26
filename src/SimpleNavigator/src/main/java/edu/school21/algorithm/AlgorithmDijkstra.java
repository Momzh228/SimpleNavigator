package edu.school21.algorithm;

import edu.school21.exception.PathToVertexNotExistException;
import edu.school21.graph.Graph;
import java.util.*;

public class AlgorithmDijkstra {

  public int GetShortestPathBetweenVertices(Graph graph, int vertex1, int vertex2)
      throws PathToVertexNotExistException {

    if (vertex1 < 0
        || vertex2 < 0
        || vertex1 > graph.vertexCount()
        || vertex2 > graph.vertexCount()) {
      throw new IllegalArgumentException();
    }
    Set<Integer> unprocessedNodes = new HashSet<>();
    Map<Integer, Integer> timeToNodes = new HashMap<>();
    initHashTables(unprocessedNodes, timeToNodes, vertex1, graph);
    calculateTimeToEachVertex(unprocessedNodes, timeToNodes, vertex1, graph);
    if (timeToNodes.get(vertex2) == Integer.MAX_VALUE) {
      throw new PathToVertexNotExistException();
    }
    return timeToNodes.get(vertex2);
  }

  private void initHashTables(
      Set<Integer> unprocessedNodes, Map<Integer, Integer> timeToNodes, int start, Graph graph) {
    List<Integer> edges = graph.getEdges(start);
    for (int i = 0; i < edges.size(); i++) {
      unprocessedNodes.add(i + 1);
      timeToNodes.put(i + 1, Integer.MAX_VALUE);
    }
    timeToNodes.put(start, 0);
  }

  private void calculateTimeToEachVertex(
      Set<Integer> unprocessedNodes, Map<Integer, Integer> timeToNodes, int start, Graph graph) {
    while (!unprocessedNodes.isEmpty()) {
      int vertexWithMinTime = getVertexWithMinTime(unprocessedNodes, timeToNodes);
      if (vertexWithMinTime == -1) {
        return;
      }
      List<Integer> list = graph.getEdges(vertexWithMinTime);
      for (int i = 0; i < list.size(); i++) {
        if (unprocessedNodes.contains(i + 1) && list.get(i) != 0) {
          int time = timeToNodes.get(vertexWithMinTime) + list.get(i);
          if (time < timeToNodes.get(i + 1)) {
            timeToNodes.put(i + 1, time);
          }
        }
      }
      unprocessedNodes.remove(vertexWithMinTime);
    }
  }

  private int getVertexWithMinTime(
      Set<Integer> unprocessedNodes, Map<Integer, Integer> timeToNodes) {
    int minTime = Integer.MAX_VALUE;
    int vertexWithMinTime = -1;
    for (int vertex : unprocessedNodes) {
      if (timeToNodes.get(vertex) < minTime) {
        minTime = timeToNodes.get(vertex);
        vertexWithMinTime = vertex;
      }
    }
    return vertexWithMinTime;
  }
}
