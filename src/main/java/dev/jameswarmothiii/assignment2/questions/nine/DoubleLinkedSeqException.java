package dev.jameswarmothiii.assignment2.questions.nine;

public class DoubleLinkedSeqException extends IllegalArgumentException {
  public DoubleLinkedSeqException(String message) {
    super(message);
  }

  public DoubleLinkedSeqException(String message, Throwable cause) {
    super(message, cause);
  }

  public static DoubleLinkedSeqException addendIsNull() {
    return new DoubleLinkedSeqException("addend is null");
  }

  public static DoubleLinkedSeqException noCurrentElement() {
    return new DoubleLinkedSeqException("No current element");
  }

  public static DoubleLinkedSeqException sequenceIsEmpty() {
    return new DoubleLinkedSeqException("Sequence is empty");
  }

  public static DoubleLinkedSeqException oneSequenceIsNull() {
    return new DoubleLinkedSeqException("One of the sequences is null");
  }

  public static DoubleLinkedSeqException indexOutOfBounds(int indexValue, int sizeValue) {
    return new DoubleLinkedSeqException("index: " + indexValue + ", size: " + sizeValue);
  }

  public static DoubleLinkedSeqException sequenceSizeOverflow() {
    return new DoubleLinkedSeqException("Sequence size overflow");
  }

  public static DoubleLinkedSeqException cloneShouldBeSupported(
      CloneNotSupportedException cloneNotSupportedException) {
    return new DoubleLinkedSeqException(
        "Clone should be supported", cloneNotSupportedException);
  }
}
