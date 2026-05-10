package dev.jameswarmothiii.assignment5.questions.chapter14.five;

public class DirectedGraphException extends IllegalArgumentException {
  public DirectedGraphException(String message) {
    super(message);
  }

  public DirectedGraphException(String message, Throwable cause) {
    super(message, cause);
  }

  public static DirectedGraphException graphMustNotBeNull() {
    return new DirectedGraphException("Graph must not be null.");
  }

  public static DirectedGraphException vertexCountMustNotBeNegative(int vertexCountValue) {
    return new DirectedGraphException(
        "vertexCount must not be negative, but was " + vertexCountValue + ".");
  }

  public static DirectedGraphException vertexNumberMustBeInRange(
      int vertexNumberValue, int vertexCountValue) {
    return new DirectedGraphException(
        "vertex number must be between 0 and "
            + (vertexCountValue - 1)
            + ", but was "
            + vertexNumberValue
            + ".");
  }
}
