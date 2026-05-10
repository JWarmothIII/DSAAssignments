package dev.jameswarmothiii.assignment5.questions.chapter12.four;

public final class IterativeQuickSort {

  private IterativeQuickSort() {}

  public static void quicksort(int[] sourceValues) {
    if (sourceValues == null) {
      throw IterativeQuickSortException.sourceArrayMustNotBeNull();
    }

    quicksort(sourceValues, 0, sourceValues.length);
  }

  public static void quicksort(int[] sourceValues, int firstIndex, int segmentLength) {
    validateInputs(sourceValues, firstIndex, segmentLength);

    if (segmentLength <= 1) {
      return;
    }

    int[] pendingSegmentStartIndices = new int[segmentLength];
    int[] pendingSegmentLengths = new int[segmentLength];
    int pendingSegmentCount = 0;

    pendingSegmentCount =
        pushSegment(
            pendingSegmentStartIndices,
            pendingSegmentLengths,
            pendingSegmentCount,
            firstIndex,
            segmentLength);

    while (pendingSegmentCount > 0) {
      pendingSegmentCount--;
      int currentSegmentStartIndex = pendingSegmentStartIndices[pendingSegmentCount];
      int currentSegmentLength = pendingSegmentLengths[pendingSegmentCount];

      int pivotIndex = partition(sourceValues, currentSegmentStartIndex, currentSegmentLength);

      int leftSegmentStartIndex = currentSegmentStartIndex;
      int leftSegmentLength = pivotIndex - currentSegmentStartIndex;

      int rightSegmentStartIndex = pivotIndex + 1;
      int rightSegmentLength = currentSegmentLength - leftSegmentLength - 1;

      pendingSegmentCount =
          pushSegmentsThatNeedSorting(
              pendingSegmentStartIndices,
              pendingSegmentLengths,
              pendingSegmentCount,
              leftSegmentStartIndex,
              leftSegmentLength,
              rightSegmentStartIndex,
              rightSegmentLength);
    }
  }

  private static int pushSegmentsThatNeedSorting(
      int[] pendingSegmentStartIndices,
      int[] pendingSegmentLengths,
      int pendingSegmentCount,
      int leftSegmentStartIndex,
      int leftSegmentLength,
      int rightSegmentStartIndex,
      int rightSegmentLength) {
    boolean leftSegmentNeedsSorting = leftSegmentLength > 1;
    boolean rightSegmentNeedsSorting = rightSegmentLength > 1;

    if (leftSegmentNeedsSorting && rightSegmentNeedsSorting) {
      if (leftSegmentLength >= rightSegmentLength) {
        pendingSegmentCount =
            pushSegment(
                pendingSegmentStartIndices,
                pendingSegmentLengths,
                pendingSegmentCount,
                leftSegmentStartIndex,
                leftSegmentLength);
        return pushSegment(
            pendingSegmentStartIndices,
            pendingSegmentLengths,
            pendingSegmentCount,
            rightSegmentStartIndex,
            rightSegmentLength);
      }

      pendingSegmentCount =
          pushSegment(
              pendingSegmentStartIndices,
              pendingSegmentLengths,
              pendingSegmentCount,
              rightSegmentStartIndex,
              rightSegmentLength);
      return pushSegment(
          pendingSegmentStartIndices,
          pendingSegmentLengths,
          pendingSegmentCount,
          leftSegmentStartIndex,
          leftSegmentLength);
    }

    if (leftSegmentNeedsSorting) {
      return pushSegment(
          pendingSegmentStartIndices,
          pendingSegmentLengths,
          pendingSegmentCount,
          leftSegmentStartIndex,
          leftSegmentLength);
    }

    if (rightSegmentNeedsSorting) {
      return pushSegment(
          pendingSegmentStartIndices,
          pendingSegmentLengths,
          pendingSegmentCount,
          rightSegmentStartIndex,
          rightSegmentLength);
    }

    return pendingSegmentCount;
  }

  private static int pushSegment(
      int[] pendingSegmentStartIndices,
      int[] pendingSegmentLengths,
      int pendingSegmentCount,
      int segmentStartIndex,
      int segmentLength) {
    pendingSegmentStartIndices[pendingSegmentCount] = segmentStartIndex;
    pendingSegmentLengths[pendingSegmentCount] = segmentLength;
    return pendingSegmentCount + 1;
  }

  private static void validateInputs(int[] sourceValues, int firstIndex, int segmentLength) {
    if (sourceValues == null) {
      throw IterativeQuickSortException.sourceArrayMustNotBeNull();
    }

    if (firstIndex < 0) {
      throw IterativeQuickSortException.firstIndexMustBeNonNegative(firstIndex);
    }

    if (segmentLength < 0) {
      throw IterativeQuickSortException.segmentLengthMustBeNonNegative(segmentLength);
    }

    if (firstIndex > sourceValues.length || firstIndex + segmentLength > sourceValues.length) {
      throw IterativeQuickSortException.arraySegmentMustFitInsideArray(
          firstIndex, segmentLength, sourceValues.length);
    }
  }

  private static int partition(int[] sourceValues, int firstIndex, int segmentLength) {
    int pivotIndex = firstIndex + segmentLength - 1;
    int pivotValue = sourceValues[pivotIndex];

    int boundaryIndex = firstIndex;
    for (int currentIndex = firstIndex; currentIndex < pivotIndex; currentIndex++) {
      if (sourceValues[currentIndex] <= pivotValue) {
        swap(sourceValues, boundaryIndex, currentIndex);
        boundaryIndex++;
      }
    }

    swap(sourceValues, boundaryIndex, pivotIndex);
    return boundaryIndex;
  }

  private static void swap(int[] sourceValues, int firstSwapIndex, int secondSwapIndex) {
    int temporaryValue = sourceValues[firstSwapIndex];
    sourceValues[firstSwapIndex] = sourceValues[secondSwapIndex];
    sourceValues[secondSwapIndex] = temporaryValue;
  }
}
