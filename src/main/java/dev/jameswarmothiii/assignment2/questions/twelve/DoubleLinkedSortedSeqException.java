package dev.jameswarmothiii.assignment2.questions.twelve;

public class DoubleLinkedSortedSeqException extends IllegalArgumentException {
  public DoubleLinkedSortedSeqException(String message) {
    super(message);
  }

  public DoubleLinkedSortedSeqException(String message, Throwable cause) {
    super(message, cause);
  }

  public static DoubleLinkedSortedSeqException oneSequenceIsNull() {
    return new DoubleLinkedSortedSeqException("One of the sequences is null");
  }

  public static DoubleLinkedSortedSeqException addendIsNull() {
    return new DoubleLinkedSortedSeqException("addend is null");
  }

  public static DoubleLinkedSortedSeqException noCurrentElement() {
    return new DoubleLinkedSortedSeqException("No current element");
  }

  public static DoubleLinkedSortedSeqException indexOutOfBounds(int indexValue, int sizeValue) {
    return new DoubleLinkedSortedSeqException("index: " + indexValue + ", size: " + sizeValue);
  }

  public static DoubleLinkedSortedSeqException cloneShouldBeSupported(
      CloneNotSupportedException cloneNotSupportedException) {
    return new DoubleLinkedSortedSeqException(
        "Clone should be supported", cloneNotSupportedException);
  }
}
