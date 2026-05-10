package dev.jameswarmothiii.assignment5.questions.chapter11.four;

public class TableException extends IllegalArgumentException {
  public TableException(String message) {
    super(message);
  }

  public TableException(String message, Throwable cause) {
    super(message, cause);
  }

  public static TableException capacityMustBePositive(int capacityValue) {
    return new TableException("Capacity must be positive, but was " + capacityValue);
  }

  public static TableException keyMustNotBeNull() {
    return new TableException("Key must not be null");
  }

  public static TableException tableIsFull() {
    return new TableException("Table is full");
  }
}
