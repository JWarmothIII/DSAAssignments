package dev.jameswarmothiii.assignment3.questions.chapter6.six;

import java.util.Scanner;
import java.util.Stack;

public final class ReversePolishNotation {
  private ReversePolishNotation() {}

  public static double evaluate(String expression) {
    if (expression == null || expression.isBlank()) {
      throw ReversePolishNotationException.expressionIsEmpty();
    }

    Stack<Double> numbers = new Stack<>();

    try (Scanner tokenScanner = new Scanner(expression)) {
      while (tokenScanner.hasNext()) {
        String token = tokenScanner.next();

        if (isOperation(token)) {
          applyOperation(numbers, token);
          continue;
        }

        try {
          numbers.push(Double.parseDouble(token));
        } catch (NumberFormatException numberFormatException) {
          throw ReversePolishNotationException.illegalToken(token);
        }
      }
    }

    if (numbers.size() != 1) {
      throw ReversePolishNotationException.illegalExpressionFormat();
    }

    return numbers.pop();
  }

  public static void main(String[] args) {
    try (Scanner input = new Scanner(System.in)) {
      while (true) {
        System.out.print("Enter a postfix expression (q to quit): ");
        if (!input.hasNextLine()) {
          break;
        }
        String expression = input.nextLine().trim();

        if (expression.equalsIgnoreCase("q") || expression.equalsIgnoreCase("quit")) {
          break;
        }

        try {
          double result = evaluate(expression);
          System.out.println("Result: " + result);
        } catch (ReversePolishNotationException reversePolishNotationException) {
          System.out.println("Invalid expression: " + reversePolishNotationException.getMessage());
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

  private static void applyOperation(Stack<Double> numbers, String operation) {
    if (numbers.size() < 2) {
      throw ReversePolishNotationException.missingOperand(operation);
    }

    double rightOperand = numbers.pop();
    double leftOperand = numbers.pop();

    switch (operation) {
      case "+":
        numbers.push(leftOperand + rightOperand);
        break;
      case "-":
        numbers.push(leftOperand - rightOperand);
        break;
      case "*":
        numbers.push(leftOperand * rightOperand);
        break;
      case "/":
        numbers.push(leftOperand / rightOperand);
        break;
      default:
        throw ReversePolishNotationException.illegalOperation(operation);
    }
  }

  private static boolean isOperation(String token) {
    return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
  }
}
