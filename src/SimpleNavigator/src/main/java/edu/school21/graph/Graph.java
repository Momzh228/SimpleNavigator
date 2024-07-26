package edu.school21.graph;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

import guru.nidi.graphviz.engine.Engine;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.Renderer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {
  private final List<List<Integer>> adj = new ArrayList<>();
  private int edgeCount = 0;

  public void LoadGraphFromFile(String path) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String line = reader.readLine();
      while (line != null) {
        String[] edges = line.trim().split(" ");
        adj.add(new ArrayList<Integer>());
        for (String edgeWeight : edges) {
          int weight = Integer.parseInt(edgeWeight);
          if (weight != 0) ++edgeCount;
          adj.get(adj.size() - 1).add(weight);
        }
        line = reader.readLine();
      }
    }
  }

  public void ExportGraphToDot(String path) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      String dot = renderer().toString();
      writer.append(dot);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public int vertexCount() {
    return adj.get(0).size();
  }

  public int edgeCount() {
    return edgeCount;
  }

  public boolean haveEdge(int start, int end) {
    return adj.get(start).get(end) != 0;
  }

  public boolean isEmpty() {
    return vertexCount() == 0;
  }

  public List<Integer> getEdges(int vertex) {
    if (vertex <= 0) return null;
    return new ArrayList<>(adj.get(vertex - 1));
  }

  public List<List<Integer>> getAdj() {
    return adj;
  }

  private <V> Renderer renderer() {
    return Graphviz.fromGraph(process()).engine(Engine.DOT).render(Format.DOT);
  }

  private <V> guru.nidi.graphviz.model.Graph process() {
    guru.nidi.graphviz.model.Graph g = graph();
    g = g.directed();

    for (int start = 0; start < adj.size(); ++start) {
      List<Integer> edges = adj.get(start);
      for (int end = 0; end < edges.size(); ++end) {
        Integer weight = edges.get(end);
        if (weight != 0)
          g =
              g.with(
                  node(Integer.toString(start + 1))
                      .link(to(node(Integer.toString(end + 1))).with(attr("weight", weight))));
      }
    }
    return g;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (List<Integer> edges : adj) {
      for (Integer edge : edges) {
        str.append(edge).append(" ");
      }
      str.append("\n");
    }
    return str.toString();
  }
}
