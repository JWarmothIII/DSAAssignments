package dev.jameswarmothiii.assignment4.questions.chapter8.one;

public final class QuestionOne {

  private QuestionOne() {}

  public static void writeCalls(int maxDepth) {
    if (maxDepth < 1) {
      throw QuestionOneException.invalidMaxDepth(maxDepth);
    }

    writeCalls(1, maxDepth);
  }

  private static void writeCalls(int callNumber, int maxDepth) {
    String indentation = " ".repeat(callNumber - 1);

    System.out.println(indentation + "This was written by call number " + callNumber + ".");

    if (callNumber < maxDepth) {
      writeCalls(callNumber + 1, maxDepth);
    }

    System.out.println(indentation + "This was ALSO written by call number " + callNumber + ".");
  }
}
