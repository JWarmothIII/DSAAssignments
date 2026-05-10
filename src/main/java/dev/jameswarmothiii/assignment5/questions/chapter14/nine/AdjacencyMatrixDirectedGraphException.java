package dev.jameswarmothiii.assignment5.questions.chapter14.nine;

public class AdjacencyMatrixDirectedGraphException extends IllegalArgumentException {
  public AdjacencyMatrixDirectedGraphException(String message) {
    super(message);
  }

  public static AdjacencyMatrixDirectedGraphException vertexCountMustNotBeNegative(
      int vertexCountValue) {
    return new AdjacencyMatrixDirectedGraphException(
        "vertexCount must not be negative, but was " + vertexCountValue + ".");
  }

  public static AdjacencyMatrixDirectedGraphException vertexNumberMustBeInRange(
      int vertexNumberValue, int vertexCountValue) {
    return new AdjacencyMatrixDirectedGraphException(
        "vertex number must be between 0 and "
            + (vertexCountValue - 1)
            + ", but was "
            + vertexNumberValue
            + ".");
  }
}
