package dev.jameswarmothiii.assignment2.questions.nineteen;

public class IntLinkedBagException extends IllegalArgumentException {
  public IntLinkedBagException(String message) {
    super(message);
  }

  public IntLinkedBagException(String message, Throwable cause) {
    super(message, cause);
  }

  public static IntLinkedBagException addendIsNull() {
    return new IntLinkedBagException("addend is null.");
  }

  public static IntLinkedBagException firstBagIsNull() {
    return new IntLinkedBagException("bag1 is null.");
  }

  public static IntLinkedBagException secondBagIsNull() {
    return new IntLinkedBagException("bag2 is null.");
  }

  public static IntLinkedBagException bagSizeOverflow() {
    return new IntLinkedBagException("Bag size overflow");
  }

  public static IntLinkedBagException cloneShouldBeSupported(
      CloneNotSupportedException cloneNotSupportedException) {
    return new IntLinkedBagException("Clone should be supported", cloneNotSupportedException);
  }
}
