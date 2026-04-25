package dev.jameswarmothiii.assignment4.questions.chapter8.ten;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class QuestionTenTest {

  @Test
  void guessShouldReturnMidpointWhenUserSaysYesImmediately() {
    int guessedNumber = runGuessWithInput(1, 100, "yes" + System.lineSeparator());
    assertEquals(50, guessedNumber);
  }

  @Test
  void guessShouldRecurseIntoLowerHalf() {
    String answerScript =
        "no" + System.lineSeparator() + "no" + System.lineSeparator() + "yes" + System.lineSeparator();

    int guessedNumber = runGuessWithInput(1, 100, answerScript);
    assertEquals(25, guessedNumber);
  }

  @Test
  void guessShouldRecurseIntoUpperHalf() {
    String answerScript =
        "no"
            + System.lineSeparator()
            + "yes"
            + System.lineSeparator()
            + "no"
            + System.lineSeparator()
            + "yes"
            + System.lineSeparator()
            + "yes"
            + System.lineSeparator();

    int guessedNumber = runGuessWithInput(1, 100, answerScript);
    assertEquals(88, guessedNumber);
  }

  @Test
  void guessShouldReturnSingleValueRangeWithoutQuestions() {
    int guessedNumber = runGuessWithInput(777, 777, "");
    assertEquals(777, guessedNumber);
  }

  @Test
  void guessShouldRejectInvalidRange() {
    QuestionTenLogicService questionTenLogicService = new QuestionTenLogicService();

    assertThrows(
        QuestionTenException.class,
        () ->
            questionTenLogicService.guess(
                0, 10, new Scanner(""), new PrintStream(new ByteArrayOutputStream())));
  }

  @Test
  void guessTwoParameterMethodShouldRequireSessionSetup() {
    QuestionTenLogicService questionTenLogicService = new QuestionTenLogicService();
    assertThrows(QuestionTenException.class, () -> questionTenLogicService.guess(1, 10));
  }

  @Test
  void guessShouldThrowForInconsistentAnswers() {
    String answerScript = "no" + System.lineSeparator() + "no" + System.lineSeparator();

    assertThrows(
        QuestionTenException.class,
        () ->
            new QuestionTenLogicService()
                .guess(
                1,
                2,
                new Scanner(answerScript),
                new PrintStream(new ByteArrayOutputStream())));
  }

  @Test
  void guessShouldRepromptForInvalidYesNoAnswer() {
    ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
    PrintStream outputStream = new PrintStream(outputBytes);

    QuestionTenLogicService questionTenLogicService = new QuestionTenLogicService();
    int guessedNumber =
        questionTenLogicService.guess(
            1, 100, new Scanner("maybe" + System.lineSeparator() + "yes" + System.lineSeparator()), outputStream);

    assertEquals(50, guessedNumber);
    assertTrue(outputBytes.toString(StandardCharsets.UTF_8).contains("Please answer yes or no."));
  }

  @Test
  void guessShouldAcceptYNAndBeCaseInsensitive() {
    String answerScript =
        "N"
            + System.lineSeparator()
            + "y"
            + System.lineSeparator()
            + "n"
            + System.lineSeparator()
            + "Y"
            + System.lineSeparator()
            + "Y"
            + System.lineSeparator();

    int guessedNumber = runGuessWithInput(1, 100, answerScript);
    assertEquals(88, guessedNumber);
  }

  private static int runGuessWithInput(int lowValue, int highValue, String answerScript) {
    QuestionTenLogicService questionTenLogicService = new QuestionTenLogicService();
    return questionTenLogicService.guess(
        lowValue, highValue, new Scanner(answerScript), new PrintStream(new ByteArrayOutputStream()));
  }
}
