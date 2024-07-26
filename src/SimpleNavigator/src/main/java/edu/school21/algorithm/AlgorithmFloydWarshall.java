package edu.school21.algorithm;

import edu.school21.graph.Graph;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmFloydWarshall {

  List<List<Integer>> initMatrix;

  public List<List<Integer>> GetShortestPathsBetweenAllVertices(Graph graph) {
    createInitMatrix(graph);
    fillInitMatrix();
    processAlgorithm();
    return initMatrix;
  }

  private void processAlgorithm() {
    for (int k = 0; k < initMatrix.size(); k++) {
      for (int i = 0; i < initMatrix.size(); i++) {
        for (int j = 0; j < initMatrix.size(); j++) {
          if (initMatrix.get(i).get(k) != Integer.MAX_VALUE
              && initMatrix.get(k).get(j) != Integer.MAX_VALUE
              && initMatrix.get(i).get(j) > initMatrix.get(i).get(k) + initMatrix.get(k).get(j)) {
            initMatrix.get(i).set(j, initMatrix.get(i).get(k) + initMatrix.get(k).get(j));
          }
        }
      }
    }
  }

  private void createInitMatrix(Graph graph) {
    initMatrix = new ArrayList<>();
    for (int i = 0; i < graph.getAdj().size(); i++) {
      initMatrix.add(graph.getEdges(i + 1));
    }
  }

  private void fillInitMatrix() {
    for (int i = 0; i < initMatrix.size(); i++) {
      for (int j = 0; j < initMatrix.get(i).size(); j++) {
        if (i != j && initMatrix.get(i).get(j) == 0) {
          initMatrix.get(i).set(j, Integer.MAX_VALUE);
        }
      }
    }
  }
}
