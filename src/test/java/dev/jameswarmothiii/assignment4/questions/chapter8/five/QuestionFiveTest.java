package dev.jameswarmothiii.assignment4.questions.chapter8.five;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class QuestionFiveTest {

  @Test
  void sumoverShouldReturnZeroWhenNIsZero() {
    assertEquals(0.0, QuestionFive.sumover(0));
  }

  @Test
  void sumoverShouldReturnExpectedValuesForSmallInputs() {
    assertEquals(1.0, QuestionFive.sumover(1), 1.0e-12);
    assertEquals(1.5, QuestionFive.sumover(2), 1.0e-12);
    assertEquals(1.8333333333333333, QuestionFive.sumover(3), 1.0e-12);
  }

  @Test
  void sumoverShouldThrowWhenNIsNegative() {
    assertThrows(QuestionFiveException.class, () -> QuestionFive.sumover(-1));
  }
}
