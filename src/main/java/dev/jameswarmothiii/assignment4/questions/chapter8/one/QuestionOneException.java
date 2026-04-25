package dev.jameswarmothiii.assignment4.questions.chapter8.one;

public class QuestionOneException extends IllegalArgumentException {
  public QuestionOneException(String message) {
    super(message);
  }

  public QuestionOneException(String message, Throwable cause) {
    super(message, cause);
  }

  public static QuestionOneException invalidMaxDepth(int maxDepthValue) {
    return new QuestionOneException("maxDepth must be at least 1, but was " + maxDepthValue);
  }
}
