package dev.jameswarmothiii.assignment4.questions.chapter9.one;

public class QuestionOneException extends IllegalArgumentException {
  public QuestionOneException(String message) {
    super(message);
  }

  public QuestionOneException(String message, Throwable cause) {
    super(message, cause);
  }

  public static QuestionOneException treeIsEmpty() {
    return new QuestionOneException("Expression tree is empty");
  }

  public static QuestionOneException operatorIsInvalid(char operatorValue) {
    return new QuestionOneException(
        "Operator must be '+' or '*', but was '" + operatorValue + "'");
  }

  public static QuestionOneException subtreeIsNull(String subtreeName) {
    return new QuestionOneException(subtreeName + " subtree cannot be null");
  }

  public static QuestionOneException subtreeIsEmpty(String subtreeName) {
    return new QuestionOneException(subtreeName + " subtree cannot be empty");
  }
}
