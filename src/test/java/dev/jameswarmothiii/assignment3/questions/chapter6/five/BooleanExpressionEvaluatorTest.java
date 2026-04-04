package dev.jameswarmothiii.assignment3.questions.chapter6.five;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BooleanExpressionEvaluatorTest {

  @Test
  void evaluateShouldHandleNestedAndOrExpressions() {
    boolean result = BooleanExpressionEvaluator.evaluate("((1 < 2) && ((3 > 7) || (9 >= 9)))");

    assertTrue(result);
  }

  @Test
  void evaluateShouldHandleUnaryNotOfComparison() {
    boolean result = BooleanExpressionEvaluator.evaluate("!(1 < 2)");

    assertFalse(result);
  }

  @Test
  void evaluateShouldHandleUnaryNotOfGroupedExpression() {
    boolean result = BooleanExpressionEvaluator.evaluate("!((6 < 3) || (8 != 8))");

    assertTrue(result);
  }

  @Test
  void evaluateShouldHandleEqualityWithEitherEqualsSymbol() {
    assertTrue(BooleanExpressionEvaluator.evaluate("((4 == 4) && (7 != 2))"));
    assertTrue(BooleanExpressionEvaluator.evaluate("((4 = 4) && (7 != 2))"));
  }

  @Test
  void evaluateShouldThrowForMalformedExpression() {
    assertThrows(
        BooleanExpressionEvaluatorException.class,
        () -> BooleanExpressionEvaluator.evaluate("((1 < 2) &&)"));
  }

  @Test
  void evaluateShouldThrowForMismatchedParentheses() {
    assertThrows(
        BooleanExpressionEvaluatorException.class,
        () -> BooleanExpressionEvaluator.evaluate("((1 < 2) && (3 < 4)"));
  }

  @Test
  void evaluateShouldThrowForIllegalToken() {
    assertThrows(
        BooleanExpressionEvaluatorException.class,
        () -> BooleanExpressionEvaluator.evaluate("((1 < 2) %% (3 < 4))"));
  }
}
