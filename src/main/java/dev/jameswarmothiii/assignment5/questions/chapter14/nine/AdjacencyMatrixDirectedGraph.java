package dev.jameswarmothiii.assignment5.questions.chapter14.nine;

public class AdjacencyMatrixDirectedGraph {
  private int[] labels;
  private boolean[][] edges;

  public AdjacencyMatrixDirectedGraph(int vertexCount) {
    if (vertexCount < 0) {
      throw AdjacencyMatrixDirectedGraphException.vertexCountMustNotBeNegative(vertexCount);
    }

    labels = new int[vertexCount];
    edges = new boolean[vertexCount][vertexCount];
    for (int vertexNumber = 0; vertexNumber < vertexCount; vertexNumber++) {
      labels[vertexNumber] = vertexNumber;
    }
  }

  public int getVertexCount() {
    return labels.length;
  }

  public int getLabel(int vertex) {
    validateVertexNumber(vertex);
    return labels[vertex];
  }

  public void setLabel(int vertex, int label) {
    validateVertexNumber(vertex);
    labels[vertex] = label;
  }

  public void addDirectedEdge(int sourceVertex, int targetVertex) {
    validateVertexNumber(sourceVertex);
    validateVertexNumber(targetVertex);
    edges[sourceVertex][targetVertex] = true;
  }

  public boolean hasDirectedEdge(int sourceVertex, int targetVertex) {
    validateVertexNumber(sourceVertex);
    validateVertexNumber(targetVertex);
    return edges[sourceVertex][targetVertex];
  }

  public void deleteVertex(int vertex) {
    validateVertexNumber(vertex);

    int[] nextLabels = new int[getVertexCount() - 1];
    boolean[][] nextEdges = new boolean[getVertexCount() - 1][getVertexCount() - 1];

    for (int oldVertex = 0; oldVertex < getVertexCount(); oldVertex++) {
      if (oldVertex == vertex) {
        continue;
      }
      int newVertex = shiftedVertex(oldVertex, vertex);
      nextLabels[newVertex] = labels[oldVertex];
    }

    for (int oldSource = 0; oldSource < getVertexCount(); oldSource++) {
      if (oldSource == vertex) {
        continue;
      }
      for (int oldTarget = 0; oldTarget < getVertexCount(); oldTarget++) {
        if (oldTarget == vertex) {
          continue;
        }
        if (!edges[oldSource][oldTarget]) {
          continue;
        }

        int newSource = shiftedVertex(oldSource, vertex);
        int newTarget = shiftedVertex(oldTarget, vertex);
        nextEdges[newSource][newTarget] = true;
      }
    }

    labels = nextLabels;
    edges = nextEdges;
  }

  private int shiftedVertex(int oldVertex, int deletedVertex) {
    if (oldVertex < deletedVertex) {
      return oldVertex;
    }

    return oldVertex - 1;
  }

  private void validateVertexNumber(int vertexNumber) {
    int vertexCount = getVertexCount();
    if (vertexNumber < 0 || vertexNumber >= vertexCount) {
      throw AdjacencyMatrixDirectedGraphException.vertexNumberMustBeInRange(
          vertexNumber, vertexCount);
    }
  }
}
