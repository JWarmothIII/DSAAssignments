package dev.jameswarmothiii.assignment4.questions.chapter8.ten;

public class QuestionTenException extends IllegalArgumentException {
  public QuestionTenException(String message) {
    super(message);
  }

  public QuestionTenException(String message, Throwable cause) {
    super(message, cause);
  }

  public static QuestionTenException invalidRange(int lowValue, int highValue) {
    return new QuestionTenException("Invalid range: " + lowValue + "..." + highValue);
  }

  public static QuestionTenException inconsistentAnswers(int lowValue, int highValue) {
    return new QuestionTenException(
        "Answers are inconsistent. Remaining range was " + lowValue + "..." + highValue);
  }

  public static QuestionTenException unexpectedEndOfInput() {
    return new QuestionTenException("Expected a yes/no answer, but input ended.");
  }

  public static QuestionTenException inputScannerIsNull() {
    return new QuestionTenException("Input scanner must not be null.");
  }

  public static QuestionTenException outputStreamIsNull() {
    return new QuestionTenException("Output stream must not be null.");
  }

  public static QuestionTenException guessingSessionIsNotConfigured() {
    return new QuestionTenException(
        "Guessing session is not configured. Call guess(low, high, scanner, output) or play().");
  }
}
