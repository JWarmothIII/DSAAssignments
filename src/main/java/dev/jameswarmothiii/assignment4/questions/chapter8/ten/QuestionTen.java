package dev.jameswarmothiii.assignment4.questions.chapter8.ten;

public final class QuestionTen {
  private QuestionTen() {}

  public static void main(String[] arguments) {
    QuestionTenLogicService questionTenLogicService = new QuestionTenLogicService();
    QuestionTenPresentationService questionTenPresentationService =
        new QuestionTenPresentationService(questionTenLogicService);
    questionTenPresentationService.play();
  }
}
