package dev.jameswarmothiii.assignment4.questions.chapter9.one;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class QuestionOneTest {
  private static final double TOLERANCE = 0.000001;

  @Test
  void evaluateShouldReturnLeafValue() {
    QuestionOne expressionTree = QuestionOne.leaf(42.5);

    assertEquals(42.5, expressionTree.evaluate(), TOLERANCE);
  }

  @Test
  void evaluateShouldHandleMixedPlusAndMultiplyOperations() {
    QuestionOne leftSubtree = QuestionOne.add(QuestionOne.leaf(3), QuestionOne.leaf(7));
    QuestionOne rightSubtree = QuestionOne.multiply(QuestionOne.leaf(2), QuestionOne.leaf(7));
    QuestionOne expressionTree = QuestionOne.multiply(leftSubtree, rightSubtree);

    assertEquals(140.0, expressionTree.evaluate(), TOLERANCE);
  }

  @Test
  void evaluateShouldThrowWhenTreeIsEmpty() {
    QuestionOne expressionTree = new QuestionOne();

    assertThrows(QuestionOneException.class, expressionTree::evaluate);
  }

  @Test
  void makeOperatorShouldBuildTreeOnExistingInstance() {
    QuestionOne expressionTree = new QuestionOne();
    expressionTree.makeOperator('+', QuestionOne.leaf(1.5), QuestionOne.leaf(2.5));

    assertEquals(4.0, expressionTree.evaluate(), TOLERANCE);
  }

  @Test
  void combineShouldRejectInvalidOperator() {
    assertThrows(
        QuestionOneException.class,
        () -> QuestionOne.combine('-', QuestionOne.leaf(1), QuestionOne.leaf(2)));
  }
}
