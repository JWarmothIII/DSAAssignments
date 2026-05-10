package dev.jameswarmothiii.assignment5.questions.chapter14.five;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public final class DirectedPathFinderUtility {

  private DirectedPathFinderUtility() {}

  public static boolean hasDirectedPath(
      DirectedGraph graph, int startingVertexNumber, int endingVertexNumber) {
    validateInputs(graph, startingVertexNumber, endingVertexNumber);

    if (startingVertexNumber == endingVertexNumber) {
      return true;
    }

    boolean[] visitedVertexNumbers = new boolean[graph.getVertexCount()];
    Deque<Integer> pendingVertexNumbers = new ArrayDeque<>();

    visitedVertexNumbers[startingVertexNumber] = true;
    pendingVertexNumbers.push(startingVertexNumber);

    while (!pendingVertexNumbers.isEmpty()) {
      int currentVertexNumber = pendingVertexNumbers.pop();
      List<Integer> adjacentVertexNumbers = graph.getAdjacentVertexNumbers(currentVertexNumber);

      for (int adjacentVertexNumber : adjacentVertexNumbers) {
        if (adjacentVertexNumber == endingVertexNumber) {
          return true;
        }

        if (!visitedVertexNumbers[adjacentVertexNumber]) {
          visitedVertexNumbers[adjacentVertexNumber] = true;
          pendingVertexNumbers.push(adjacentVertexNumber);
        }
      }
    }

    return false;
  }

  private static void validateInputs(
      DirectedGraph graph, int startingVertexNumber, int endingVertexNumber) {
    if (graph == null) {
      throw DirectedGraphException.graphMustNotBeNull();
    }

    validateVertexNumber(startingVertexNumber, graph.getVertexCount());
    validateVertexNumber(endingVertexNumber, graph.getVertexCount());
  }

  private static void validateVertexNumber(int vertexNumber, int vertexCount) {
    if (vertexNumber < 0 || vertexNumber >= vertexCount) {
      throw DirectedGraphException.vertexNumberMustBeInRange(vertexNumber, vertexCount);
    }
  }
}
