package dev.jameswarmothiii.assignment4.questions.chapter8.five;

public final class QuestionFive {

  private QuestionFive() {}

  public static double sumover(int numberOfTerms) {
    if (numberOfTerms < 0) {
      throw QuestionFiveException.numberOfTermsMustBeNonNegative(numberOfTerms);
    }

    return numberOfTerms == 0 ? 0.0 : (1.0 / numberOfTerms) + sumover(numberOfTerms - 1);
  }
}
