package dev.jameswarmothiii.assignment4.questions.chapter8.five;

public class QuestionFiveException extends IllegalArgumentException {
  public QuestionFiveException(String message) {
    super(message);
  }

  public QuestionFiveException(String message, Throwable cause) {
    super(message, cause);
  }

  public static QuestionFiveException numberOfTermsMustBeNonNegative(
      int numberOfTermsValue) {
    return new QuestionFiveException(
        "numberOfTerms must be non-negative, but was " + numberOfTermsValue);
  }
}
