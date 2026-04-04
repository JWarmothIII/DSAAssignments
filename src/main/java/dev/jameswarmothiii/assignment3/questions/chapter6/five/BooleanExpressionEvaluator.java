package dev.jameswarmothiii.assignment3.questions.chapter6.five;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BooleanExpressionEvaluator {
  private static final Pattern COMPARISON_PATTERN =
      Pattern.compile("\\(\\s*(-?\\d+)\\s*(<=|>=|==|!=|=|<|>)\\s*(-?\\d+)\\s*\\)");

  private static final Pattern BOOLEAN_TOKEN_PATTERN =
      Pattern.compile("(true|false|&&|\\|\\||!|\\(|\\))");

  private BooleanExpressionEvaluator() {}

  public static boolean evaluate(String expression) {
    if (expression == null || expression.isBlank()) {
      throw BooleanExpressionEvaluatorException.expressionIsEmpty();
    }

    String reducedExpression = reduceComparisons(expression);
    Stack<Boolean> values = new Stack<>();
    Stack<String> operations = new Stack<>();

    Matcher tokenMatcher = BOOLEAN_TOKEN_PATTERN.matcher(reducedExpression);
    int currentIndex = 0;

    while (currentIndex < reducedExpression.length()) {
      currentIndex = skipWhitespace(reducedExpression, currentIndex);
      if (currentIndex >= reducedExpression.length()) {
        break;
      }

      if (!tokenMatcher.find(currentIndex) || tokenMatcher.start() != currentIndex) {
        throw BooleanExpressionEvaluatorException.illegalTokenAtIndex(currentIndex);
      }

      String token = tokenMatcher.group(1);
      currentIndex = tokenMatcher.end();

      switch (token) {
        case "true":
        case "false":
          values.push(Boolean.parseBoolean(token));
          applyPendingNegations(values, operations);
          break;
        case "(":
          operations.push(token);
          break;
        case ")":
          collapseUntilLeftParenthesis(values, operations);
          break;
        case "&&":
        case "||":
        case "!":
          operations.push(token);
          break;
        default:
          throw BooleanExpressionEvaluatorException.illegalToken(token);
      }
    }

    while (!operations.isEmpty()) {
      String operation = operations.pop();
      if ("(".equals(operation)) {
        throw BooleanExpressionEvaluatorException.mismatchedParentheses();
      }
      applyOperation(values, operation);
    }

    if (values.size() != 1) {
      throw BooleanExpressionEvaluatorException.illegalExpressionFormat();
    }

    return values.pop();
  }

  public static void main(String[] args) {
    try (Scanner input = new Scanner(System.in)) {
      while (true) {
        System.out.print("Enter a boolean expression (q to quit): ");
        if (!input.hasNextLine()) {
          break;
        }
        String expression = input.nextLine().trim();

        if (expression.equalsIgnoreCase("q") || expression.equalsIgnoreCase("quit")) {
          break;
        }

        try {
          boolean result = evaluate(expression);
          System.out.println("Result: " + result);
        } catch (BooleanExpressionEvaluatorException evaluatorException) {
          System.out.println("Invalid expression: " + evaluatorException.getMessage());
          continue;
        }

        System.out.print("Evaluate another expression? (y/n): ");
        if (!input.hasNextLine()) {
          break;
        }
        String response = input.nextLine().trim();
        if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("yes")) {
          break;
        }
      }
    }
  }

  private static void collapseUntilLeftParenthesis(
      Stack<Boolean> values, Stack<String> operations) {
    while (!operations.isEmpty() && !"(".equals(operations.peek())) {
      applyOperation(values, operations.pop());
    }

    if (operations.isEmpty()) {
      throw BooleanExpressionEvaluatorException.mismatchedParentheses();
    }

    operations.pop();
    applyPendingNegations(values, operations);
  }

  private static void applyPendingNegations(Stack<Boolean> values, Stack<String> operations) {
    while (!operations.isEmpty() && "!".equals(operations.peek())) {
      applyOperation(values, operations.pop());
    }
  }

  private static void applyOperation(Stack<Boolean> values, String operation) {
    switch (operation) {
      case "!":
        if (values.isEmpty()) {
          throw BooleanExpressionEvaluatorException.missingOperand("!");
        }
        values.push(!values.pop());
        break;
      case "&&":
      case "||":
        if (values.size() < 2) {
          throw BooleanExpressionEvaluatorException.missingOperand(operation);
        }
        boolean rightOperand = values.pop();
        boolean leftOperand = values.pop();
        values.push("&&".equals(operation) ? leftOperand && rightOperand : leftOperand || rightOperand);
        break;
      default:
        throw BooleanExpressionEvaluatorException.illegalOperation(operation);
    }
  }

  private static int skipWhitespace(String text, int startIndex) {
    int index = startIndex;
    while (index < text.length() && Character.isWhitespace(text.charAt(index))) {
      index++;
    }
    return index;
  }

  private static String reduceComparisons(String expression) {
    Matcher comparisonMatcher = COMPARISON_PATTERN.matcher(expression);
    StringBuffer reducedExpression = new StringBuffer();
    boolean foundComparison = false;

    while (comparisonMatcher.find()) {
      foundComparison = true;

      long leftValue = Long.parseLong(comparisonMatcher.group(1));
      String comparisonOperation = comparisonMatcher.group(2);
      long rightValue = Long.parseLong(comparisonMatcher.group(3));

      boolean comparisonResult = evaluateComparison(leftValue, rightValue, comparisonOperation);
      comparisonMatcher.appendReplacement(reducedExpression, Boolean.toString(comparisonResult));
    }

    comparisonMatcher.appendTail(reducedExpression);

    if (!foundComparison) {
      throw BooleanExpressionEvaluatorException.expressionMissingComparison();
    }

    return reducedExpression.toString();
  }

  private static boolean evaluateComparison(long leftValue, long rightValue, String operation) {
    switch (operation) {
      case "<":
        return leftValue < rightValue;
      case ">":
        return leftValue > rightValue;
      case "<=":
        return leftValue <= rightValue;
      case ">=":
        return leftValue >= rightValue;
      case "==":
      case "=":
        return leftValue == rightValue;
      case "!=":
        return leftValue != rightValue;
      default:
        throw BooleanExpressionEvaluatorException.illegalComparisonOperation(operation);
    }
  }
}
