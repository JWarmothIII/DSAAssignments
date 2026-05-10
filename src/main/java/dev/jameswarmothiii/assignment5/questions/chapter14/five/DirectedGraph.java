package dev.jameswarmothiii.assignment5.questions.chapter14.five;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectedGraph {
  private final List<List<Integer>> adjacentVertexNumbers;

  public DirectedGraph(int vertexCount) {
    if (vertexCount < 0) {
      throw DirectedGraphException.vertexCountMustNotBeNegative(vertexCount);
    }

    adjacentVertexNumbers = new ArrayList<>(vertexCount);
    for (int currentVertexNumber = 0; currentVertexNumber < vertexCount; currentVertexNumber++) {
      adjacentVertexNumbers.add(new ArrayList<>());
    }
  }

  public int getVertexCount() {
    return adjacentVertexNumbers.size();
  }

  public void addDirectedEdge(int fromVertexNumber, int toVertexNumber) {
    validateVertexNumber(fromVertexNumber);
    validateVertexNumber(toVertexNumber);

    adjacentVertexNumbers.get(fromVertexNumber).add(toVertexNumber);
  }

  public List<Integer> getAdjacentVertexNumbers(int vertexNumber) {
    validateVertexNumber(vertexNumber);
    return Collections.unmodifiableList(adjacentVertexNumbers.get(vertexNumber));
  }

  private void validateVertexNumber(int vertexNumber) {
    int vertexCount = getVertexCount();
    if (vertexNumber < 0 || vertexNumber >= vertexCount) {
      throw DirectedGraphException.vertexNumberMustBeInRange(vertexNumber, vertexCount);
    }
  }
}
