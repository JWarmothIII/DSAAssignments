package dev.jameswarmothiii.assignment3.questions.chapter7.seven;

public class DequeException extends IllegalArgumentException {
  public DequeException(String message) {
    super(message);
  }

  public DequeException(String message, Throwable cause) {
    super(message, cause);
  }

  public static DequeException dequeUnderflow() {
    return new DequeException("Deque underflow.");
  }

  public static DequeException dequeSizeOverflow() {
    return new DequeException("Deque size overflow");
  }
}
