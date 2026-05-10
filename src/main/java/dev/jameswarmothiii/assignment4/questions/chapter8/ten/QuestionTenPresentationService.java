package dev.jameswarmothiii.assignment4.questions.chapter8.ten;

import java.io.PrintStream;
import java.util.Scanner;

public final class QuestionTenPresentationService {
  private final QuestionTenLogicService questionTenLogicService;

  public QuestionTenPresentationService(QuestionTenLogicService initialQuestionTenLogicService) {
    questionTenLogicService = initialQuestionTenLogicService;
  }

  public void play() {
    play(new Scanner(System.in), System.out);
  }

  public void play(Scanner inputScanner, PrintStream outputStream) {
    if (inputScanner == null) {
      throw QuestionTenException.inputScannerIsNull();
    }

    if (outputStream == null) {
      throw QuestionTenException.outputStreamIsNull();
    }

    try (Scanner autoClosableInputScanner = inputScanner) {
      outputStream.println("Think of an integer between 1 and 1,000,000.");
      int guessedNumber =
          questionTenLogicService.guess(1, 1_000_000, autoClosableInputScanner, outputStream);
      outputStream.println("Your number is " + guessedNumber + ".");
    }
  }
}
