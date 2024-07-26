package edu.school21.model;

import java.util.List;

public class TsmResult {
  private final List<Integer> vertices;
  private final double distance;

  public TsmResult(List<Integer> vertices, double distance) {
    this.vertices = vertices;
    this.distance = distance;
  }

  public List<Integer> getVertices() {
    return vertices;
  }

  public double getDistance() {
    return distance;
  }

  @Override
  public String toString() {
    return vertices + " : " + distance;
  }
}
