package dev.jameswarmothiii.assignment3.questions.chapter6.six;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ReversePolishNotationTest {

  @Test
  void evaluateShouldHandleSimpleAddition() {
    double result = ReversePolishNotation.evaluate("3 4 +");

    assertEquals(7.0, result, 0.000001);
  }

  @Test
  void evaluateShouldHandleComplexPostfixExpression() {
    double result = ReversePolishNotation.evaluate("5 1 2 + 4 * + 3 -");

    assertEquals(14.0, result, 0.000001);
  }

  @Test
  void evaluateShouldHandleMixedOperations() {
    double result = ReversePolishNotation.evaluate("10 2 / 3 -");

    assertEquals(2.0, result, 0.000001);
  }

  @Test
  void evaluateShouldThrowWhenTokenIsIllegal() {
    assertThrows(ReversePolishNotationException.class, () -> ReversePolishNotation.evaluate("2 a +"));
  }

  @Test
  void evaluateShouldThrowWhenOperandIsMissing() {
    assertThrows(ReversePolishNotationException.class, () -> ReversePolishNotation.evaluate("3 +"));
  }

  @Test
  void evaluateShouldThrowWhenExpressionHasTooManyOperands() {
    assertThrows(ReversePolishNotationException.class, () -> ReversePolishNotation.evaluate("3 4 5 +"));
  }

  @Test
  void evaluateShouldThrowWhenExpressionIsEmpty() {
    assertThrows(ReversePolishNotationException.class, () -> ReversePolishNotation.evaluate("   "));
  }
}
