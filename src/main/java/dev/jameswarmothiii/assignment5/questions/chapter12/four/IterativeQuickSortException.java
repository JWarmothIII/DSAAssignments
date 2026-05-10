package dev.jameswarmothiii.assignment5.questions.chapter12.four;

public class IterativeQuickSortException extends IllegalArgumentException {
  public IterativeQuickSortException(String message) {
    super(message);
  }

  public IterativeQuickSortException(String message, Throwable cause) {
    super(message, cause);
  }

  public static IterativeQuickSortException sourceArrayMustNotBeNull() {
    return new IterativeQuickSortException("Source array must not be null.");
  }

  public static IterativeQuickSortException firstIndexMustBeNonNegative(int firstIndexValue) {
    return new IterativeQuickSortException(
        "firstIndex must be non-negative, but was " + firstIndexValue);
  }

  public static IterativeQuickSortException segmentLengthMustBeNonNegative(int segmentLengthValue) {
    return new IterativeQuickSortException(
        "segmentLength must be non-negative, but was " + segmentLengthValue);
  }

  public static IterativeQuickSortException arraySegmentMustFitInsideArray(
      int firstIndexValue, int segmentLengthValue, int arrayLengthValue) {
    return new IterativeQuickSortException(
        "Array segment starting at "
            + firstIndexValue
            + " with length "
            + segmentLengthValue
            + " must fit inside array length "
            + arrayLengthValue
            + ".");
  }
}
