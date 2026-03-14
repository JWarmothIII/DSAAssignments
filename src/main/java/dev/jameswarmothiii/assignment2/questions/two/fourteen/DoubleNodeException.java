package dev.jameswarmothiii.assignment2.questions.two.fourteen;

public class DoubleNodeException extends IllegalArgumentException {
  public DoubleNodeException(String message) {
    super(message);
  }

  public static DoubleNodeException isNotPositive(int value) {
    return new DoubleNodeException("positionValue is not positive: " + value);
  }
}