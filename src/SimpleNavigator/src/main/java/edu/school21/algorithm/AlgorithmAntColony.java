package edu.school21.algorithm;

import edu.school21.exception.InvalidMatrixException;
import edu.school21.graph.Graph;
import edu.school21.model.TsmResult;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AlgorithmAntColony {

  public AlgorithmAntColony() {}

  public TsmResult SolveTravelingSalesmanProblem(Graph graph) throws InvalidMatrixException {
    if (graph.isEmpty()) throw new InvalidMatrixException("Empty graph.");
    else if (graph.vertexCount() == 1) throw new InvalidMatrixException("Single vertex graph.");

    List<List<Integer>> adj = graph.getAdj();
    double minDist = Double.MAX_VALUE; // Наименьшая длина пути
    List<Integer> minPath = null; // Наименьший путь

    int antCount = graph.vertexCount(); // Количество обходов муравьев на 1 итерации
    double alpha = 1; // Степень важности феромонов между узлами
    double beta = 1; // Степень важности расстояния между узлами
    double Q = 1; // Коэфф количества доп феромона
    double kq = 0.66; // Коэфф испарения феромонов
    double initPheromone = 1; // Начальное количество феромона на путях
    List<List<Double>> pheromones =
        initPheromones(adj, initPheromone); // Таблица феромонов на путях

    int iterations = 1000; // Количество итераций алгоритма (выбирается императивно)
    while (iterations-- > 0) {
      List<List<Double>> deltaPheromones = initPheromones(adj, 0);
      // На каждой итерации несколько муравьев обходят граф (обычно, число муравьев равно числу
      // вершин)
      // каждый муравей начинает с неиспользованной ранее вершины графа
      // это помогает избежать единственного устоявшего маршрута до дальних вершин
      for (int startPos = 0; startPos < adj.size(); ++startPos) {
        int curPos = startPos;
        double dist = 0;
        List<Integer> path = new LinkedList<>(); // Текущий маршрут муравья
        path.add(startPos);

        while (canMove(adj, path, curPos)
            && (path.size() == 1 || !path.get(0).equals(path.get(path.size() - 1)))) {
          int newPos = makeChoice(adj, pheromones, path, curPos, alpha, beta);
          path.add(newPos);
          dist += adj.get(curPos).get(newPos);
          curPos = newPos;
        }

        // Если в пути N + 1 вершина, то муравей вернулся в начало
        // Если он не вернулся, то такой маршрут не решает задачу и его не рассматриваем
        if (path.size() == graph.vertexCount() + 1) {
          // Если маршрут минимальный, то сохраняем его
          if (dist < minDist) {
            minDist = dist;
            minPath = path;
          }
          // Запоминаем где на пути должны появиться феромоны
          for (int i = 1; i < path.size(); ++i) {
            int start = path.get(i - 1), end = path.get(i);
            double curDelta = deltaPheromones.get(start).get(end);
            deltaPheromones.get(start).set(end, curDelta + Q / adj.get(start).get(end));
          }
        }
      }
      // После того как все муравьи прошли изменяем феромоны согласно дельте
      for (int i = 0; i < pheromones.size(); ++i) {
        for (int j = 0; j < pheromones.size(); ++j) {
          double cur = pheromones.get(i).get(j);
          pheromones.get(i).set(j, kq * cur + deltaPheromones.get(i).get(j));
        }
      }
    }
    if (minPath == null)
      throw new InvalidMatrixException("Can not solve. Invalid graph or low count of iterations.");
    else {
      for (int i = 0; i < minPath.size(); ++i) {
        minPath.set(i, minPath.get(i) + 1);
      }
    }
    return new TsmResult(minPath, minDist);
  }

  private int makeChoice(
      List<List<Integer>> adj,
      List<List<Double>> pheromones,
      List<Integer> path,
      int cur,
      double alpha,
      double beta) {
    List<Integer> goodNeighbours =
        new ArrayList<>(); // Список вершин-соседей, где муравей еще не был
    List<Integer> neighbours = adj.get(cur);
    for (int i = 0; i < neighbours.size(); ++i) {
      if (neighbours.get(i) != 0) {
        if (path.get(0).equals(i) || !path.contains(i)) goodNeighbours.add(i);
      }
    }

    // Рассчитаем желание
    List<Double> wish = new ArrayList<>();
    double sumWish = 0; // Суммарная желание муравья
    for (Integer next : goodNeighbours) {
      double t = pheromones.get(cur).get(next);
      double w = adj.get(cur).get(next);
      double n = 1.0 / w;
      double localWish = Math.pow(t, alpha) * Math.pow(n, beta);
      wish.add(localWish);
      sumWish += localWish;
    }

    List<Double> chooseRoulette = new ArrayList<>();
    List<Double> probability = new ArrayList<>();
    for (int i = 0; i < goodNeighbours.size(); ++i) {
      double prob = wish.get(i) / sumWish;
      probability.add(prob);
      // Заполняем рулетку для выбора
      if (i == 0) chooseRoulette.add(prob);
      else chooseRoulette.add(chooseRoulette.get(chooseRoulette.size() - 1) + prob);
    }

    int result = 0;
    Random r = new Random();
    double randValue = r.nextDouble(1.0);
    for (int i = 0; i < chooseRoulette.size(); ++i) {
      if (randValue <= chooseRoulette.get(i)) {
        result = goodNeighbours.get(i);
        break;
      }
    }

    return result;
  }

  private boolean canMove(List<List<Integer>> adj, List<Integer> path, int cur) {
    List<Integer> neighbours = adj.get(cur);
    for (int neighbourIndex = 0; neighbourIndex < neighbours.size(); ++neighbourIndex) {
      // Если между вершинами есть ребро
      if (neighbours.get(neighbourIndex) != 0) {
        // Муравей может двигаться если он может перейти в начало или вершину, где он еще не был
        if (path.get(0).equals(neighbourIndex) || !path.contains(neighbourIndex)) return true;
      }
    }
    return false;
  }

  private List<List<Double>> initPheromones(List<List<Integer>> adj, double initValue) {
    List<List<Double>> pheromones = new ArrayList<>();
    for (int i = 0; i < adj.size(); ++i) {
      ArrayList<Double> p = new ArrayList<>();
      List<Integer> edges = adj.get(i);
      for (Integer edge : edges) p.add(edge == 0 ? 0 : initValue);
      pheromones.add(p);
    }
    return pheromones;
  }
}
