package dev.jameswarmothiii.assignment4.questions.chapter9.ten;

public class QuestionTenException extends IllegalArgumentException {
  public QuestionTenException(String message) {
    super(message);
  }

  public QuestionTenException(String message, Throwable cause) {
    super(message, cause);
  }

  public static QuestionTenException elementValueIsNull() {
    return new QuestionTenException("Element value cannot be null.");
  }

  public static QuestionTenException targetValueIsNull() {
    return new QuestionTenException("Target value cannot be null.");
  }

  public static QuestionTenException sourceArrayIsNull() {
    return new QuestionTenException("Source array cannot be null.");
  }
}
