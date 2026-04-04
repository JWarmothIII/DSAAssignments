package dev.jameswarmothiii.assignment3.questions.chapter6.five;

public class BooleanExpressionEvaluatorException extends IllegalArgumentException {
  public BooleanExpressionEvaluatorException(String message) {
    super(message);
  }

  public BooleanExpressionEvaluatorException(String message, Throwable cause) {
    super(message, cause);
  }

  public static BooleanExpressionEvaluatorException expressionIsEmpty() {
    return new BooleanExpressionEvaluatorException("Expression is empty");
  }

  public static BooleanExpressionEvaluatorException illegalTokenAtIndex(int indexValue) {
    return new BooleanExpressionEvaluatorException("Illegal token at index " + indexValue);
  }

  public static BooleanExpressionEvaluatorException illegalToken(String tokenValue) {
    return new BooleanExpressionEvaluatorException("Illegal token: " + tokenValue);
  }

  public static BooleanExpressionEvaluatorException mismatchedParentheses() {
    return new BooleanExpressionEvaluatorException("Mismatched parentheses");
  }

  public static BooleanExpressionEvaluatorException illegalExpressionFormat() {
    return new BooleanExpressionEvaluatorException("Illegal expression format");
  }

  public static BooleanExpressionEvaluatorException missingOperand(String operation) {
    return new BooleanExpressionEvaluatorException("Missing operand for '" + operation + "'");
  }

  public static BooleanExpressionEvaluatorException illegalOperation(String operation) {
    return new BooleanExpressionEvaluatorException("Illegal operation: " + operation);
  }

  public static BooleanExpressionEvaluatorException expressionMissingComparison() {
    return new BooleanExpressionEvaluatorException("Expression must include at least one comparison");
  }

  public static BooleanExpressionEvaluatorException illegalComparisonOperation(String operation) {
    return new BooleanExpressionEvaluatorException("Illegal comparison operation: " + operation);
  }
}
