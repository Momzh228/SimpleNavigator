package edu.school21.cli;

import edu.school21.exception.ImpossibleBuildSpanningTreeException;
import edu.school21.exception.InvalidMatrixException;
import edu.school21.exception.PathToVertexNotExistException;
import edu.school21.graph.Graph;
import edu.school21.graphalgorithms.GraphAlgorithms;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInterface {
  private static final int BFS_OPTION = 1;
  private static final int DFS_OPTION = 2;
  private static final int DIJKSTRA_OPTION = 3;
  private static final int FLOYD_WARSHALL_OPTION = 4;
  private static final int PRIM_OPTION = 5;
  private static final int ANT_COLONY_OPTION = 6;
  private static final int EXIT_OPTION = 7;

  private final Graph graph;
  private final GraphAlgorithms graphAlgorithms;
  private final Scanner scanner;
  private boolean isRunning;

  public ConsoleInterface() {
    this.graph = new Graph();
    this.graphAlgorithms = new GraphAlgorithms();
    this.scanner = new Scanner(System.in);
    this.isRunning = true;
  }

  public void run() {
    System.out.println(
        "Welcome to the Graph Algorithms Console Interface!\n"
            + "Please enter the path to the file to download the source graph:");
    loadGraph();
    while (isRunning) {
      printMenu();
      chooseAlgorithm();
    }
  }

  private void loadGraph() {
    while (true) {
      String path = scanner.nextLine();
      if (path.equalsIgnoreCase("exit")) {
        exit();
        return;
      }
      try {
        graph.LoadGraphFromFile(path);
        System.out.println("Loaded graph:");
        System.out.println(graph);
        return;
      } catch (IOException e) {
        System.out.println("Please enter a valid file path or type 'exit' to exit.");
      }
    }
  }

  private void printMenu() {
    System.out.println("Please enter the number of the algorithm you want to run:");
    System.out.println(BFS_OPTION + ". Breadth First Search");
    System.out.println(DFS_OPTION + ". Depth First Search");
    System.out.println(DIJKSTRA_OPTION + ". Dijkstra's Algorithm");
    System.out.println(FLOYD_WARSHALL_OPTION + ". Floyd-Warshall Algorithm");
    System.out.println(PRIM_OPTION + ". Prim's algorithm");
    System.out.println(ANT_COLONY_OPTION + ". Ant colony algorithm");
    System.out.println(EXIT_OPTION + ". Exit");
  }

  private void chooseAlgorithm() {
    try {
      int choice = scanner.nextInt();
      switch (choice) {
        case BFS_OPTION:
          System.out.println(graphAlgorithms.breadthFirstSearch(graph, enterStartVertex()));
          break;
        case DFS_OPTION:
          System.out.println(graphAlgorithms.depthFirstSearch(graph, enterStartVertex()));
          break;
        case DIJKSTRA_OPTION:
          System.out.println(
              graphAlgorithms.GetShortestPathBetweenVertices(
                  graph, enterStartVertex(), enterEndVertex()));
          break;
        case FLOYD_WARSHALL_OPTION:
          System.out.println(graphAlgorithms.GetShortestPathsBetweenAllVertices(graph));
          break;
        case PRIM_OPTION:
          System.out.println(graphAlgorithms.GetLeastSpanningTree(graph));
          break;
        case ANT_COLONY_OPTION:
          System.out.println(graphAlgorithms.SolveTravelingSalesmanProblem(graph));
          break;
        case EXIT_OPTION:
          exit();
          break;
        default:
          System.out.println("Invalid choice. Please select a valid option.");
          break;
      }
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please enter an integer.");
      scanner.nextLine();
    } catch (PathToVertexNotExistException | ImpossibleBuildSpanningTreeException e) {
      System.out.println("There is no path between given vertices.");
    } catch (InvalidMatrixException e) {
      System.out.println("Incorrect matrix.");
    }
  }

  public int enterStartVertex() {
    return enterVertex("start");
  }

  public int enterEndVertex() {
    return enterVertex("end");
  }

  private int enterVertex(String vertexType) {
    while (true) {
      System.out.println("Please enter the " + vertexType + " vertex:");
      try {
        int vertex = scanner.nextInt();
        int vertexCount = graph.vertexCount();
        if (vertex <= 0 || vertex > vertexCount) {
          System.out.println(
              "Vertex "
                  + vertexType
                  + " is out of range. It should be between 1 and "
                  + vertexCount
                  + ".");
        } else {
          return vertex;
        }
      } catch (InputMismatchException e) {
        System.out.println("Incorrect input, please enter a valid integer.");
        scanner.nextLine();
      }
    }
  }

  private void exit() {
    System.out.println("Exiting the application...");
    isRunning = false;
  }
}
