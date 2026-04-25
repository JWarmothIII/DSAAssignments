package dev.jameswarmothiii.assignment4.questions.chapter8.one;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class QuestionOneTest {

  @Test
  void writeCallsShouldPrintExpectedOutputForDepthFour() {
    String expected =
        String.join(
                System.lineSeparator(),
                "This was written by call number 1.",
                " This was written by call number 2.",
                "  This was written by call number 3.",
                "   This was written by call number 4.",
                "   This was ALSO written by call number 4.",
                "  This was ALSO written by call number 3.",
                " This was ALSO written by call number 2.",
                "This was ALSO written by call number 1.")
            + System.lineSeparator();

    assertPrintedOutput(expected, 4);
  }

  @Test
  void writeCallsShouldPrintExpectedOutputForDepthOne() {
    String expected =
        String.join(
                System.lineSeparator(),
                "This was written by call number 1.",
                "This was ALSO written by call number 1.")
            + System.lineSeparator();

    assertPrintedOutput(expected, 1);
  }

  @Test
  void writeCallsShouldThrowForInvalidDepth() {
    assertThrows(QuestionOneException.class, () -> QuestionOne.writeCalls(0));
  }

  private static void assertPrintedOutput(String expected, int depth) {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));

    try {
      QuestionOne.writeCalls(depth);
    } finally {
      System.setOut(originalOut);
    }

    assertEquals(expected, output.toString(StandardCharsets.UTF_8));
  }
}
