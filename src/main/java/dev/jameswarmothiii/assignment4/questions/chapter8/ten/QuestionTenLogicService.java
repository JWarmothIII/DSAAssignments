package dev.jameswarmothiii.assignment4.questions.chapter8.ten;

import java.io.PrintStream;
import java.util.Scanner;

public final class QuestionTenLogicService {
  private Scanner activeInputScanner;
  private PrintStream activeOutputStream;

  public int guess(int lowValue, int highValue, Scanner inputScanner, PrintStream outputStream) {
    if (inputScanner == null) {
      throw QuestionTenException.inputScannerIsNull();
    }

    if (outputStream == null) {
      throw QuestionTenException.outputStreamIsNull();
    }

    Scanner previousInputScanner = activeInputScanner;
    PrintStream previousOutputStream = activeOutputStream;

    try {
      activeInputScanner = inputScanner;
      activeOutputStream = outputStream;
      return guess(lowValue, highValue);
    } finally {
      activeInputScanner = previousInputScanner;
      activeOutputStream = previousOutputStream;
    }
  }

  public int guess(int lowValue, int highValue) {
    if (activeInputScanner == null || activeOutputStream == null) {
      throw QuestionTenException.guessingSessionIsNotConfigured();
    }

    if (lowValue <= 0 || highValue <= 0) {
      throw QuestionTenException.invalidRange(lowValue, highValue);
    }

    if (lowValue > highValue) {
      throw QuestionTenException.inconsistentAnswers(lowValue, highValue);
    }

    if (lowValue == highValue) {
      activeOutputStream.println("Your number must be " + lowValue + ".");
      return lowValue;
    }

    int midpointValue = (lowValue + highValue) / 2;

    if (askYesOrNoQuestion("Is your number " + midpointValue + "? (yes/no): ")) {
      return midpointValue;
    }

    if (askYesOrNoQuestion("Is your number larger than " + midpointValue + "? (yes/no): ")) {
      return guess(midpointValue + 1, highValue);
    }

    return guess(lowValue, midpointValue - 1);
  }

  private boolean askYesOrNoQuestion(String questionPrompt) {
    while (true) {
      activeOutputStream.print(questionPrompt);

      if (!activeInputScanner.hasNextLine()) {
        throw QuestionTenException.unexpectedEndOfInput();
      }

      String responseText = activeInputScanner.nextLine().trim();
      if (responseText.equalsIgnoreCase("yes") || responseText.equalsIgnoreCase("y")) {
        return true;
      }

      if (responseText.equalsIgnoreCase("no") || responseText.equalsIgnoreCase("n")) {
        return false;
      }

      activeOutputStream.println("Please answer yes or no.");
    }
  }
}
