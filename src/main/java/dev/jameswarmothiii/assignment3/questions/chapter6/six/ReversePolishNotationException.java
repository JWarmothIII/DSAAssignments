package dev.jameswarmothiii.assignment3.questions.chapter6.six;

public class ReversePolishNotationException extends IllegalArgumentException {
  public ReversePolishNotationException(String message) {
    super(message);
  }

  public ReversePolishNotationException(String message, Throwable cause) {
    super(message, cause);
  }

  public static ReversePolishNotationException expressionIsEmpty() {
    return new ReversePolishNotationException("Expression is empty");
  }

  public static ReversePolishNotationException illegalToken(String tokenValue) {
    return new ReversePolishNotationException("Illegal token: " + tokenValue);
  }

  public static ReversePolishNotationException missingOperand(String operation) {
    return new ReversePolishNotationException("Missing operand for '" + operation + "'");
  }

  public static ReversePolishNotationException illegalExpressionFormat() {
    return new ReversePolishNotationException("Illegal postfix expression format");
  }

  public static ReversePolishNotationException illegalOperation(String operation) {
    return new ReversePolishNotationException("Illegal operation: " + operation);
  }
}
