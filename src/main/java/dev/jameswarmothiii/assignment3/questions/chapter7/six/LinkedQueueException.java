package dev.jameswarmothiii.assignment3.questions.chapter7.six;

public class LinkedQueueException extends IllegalArgumentException {
  public LinkedQueueException(String message) {
    super(message);
  }

  public LinkedQueueException(String message, Throwable cause) {
    super(message, cause);
  }

  public static LinkedQueueException queueUnderflow() {
    return new LinkedQueueException("Queue underflow.");
  }

  public static LinkedQueueException queueSizeOverflow() {
    return new LinkedQueueException("Queue size overflow");
  }

  public static LinkedQueueException cloneShouldBeSupported(
      CloneNotSupportedException cloneNotSupportedException) {
    return new LinkedQueueException("Clone should be supported", cloneNotSupportedException);
  }
}
