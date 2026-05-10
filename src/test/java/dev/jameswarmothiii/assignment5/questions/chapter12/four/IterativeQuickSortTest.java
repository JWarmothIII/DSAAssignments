package dev.jameswarmothiii.assignment5.questions.chapter12.four;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IterativeQuickSortTest {

  @Test
  void quicksortShouldSortFullArrayWithMixedValues() {
    int[] sourceValues = {9, -2, 7, 7, 3, 0, -5, 11};

    IterativeQuickSort.quicksort(sourceValues);

    assertArrayEquals(new int[] {-5, -2, 0, 3, 7, 7, 9, 11}, sourceValues);
  }

  @Test
  void quicksortShouldHandleAlreadySortedArray() {
    int[] sourceValues = {-3, -1, 0, 2, 6, 9};

    IterativeQuickSort.quicksort(sourceValues);

    assertArrayEquals(new int[] {-3, -1, 0, 2, 6, 9}, sourceValues);
  }

  @Test
  void quicksortShouldHandleReverseSortedArray() {
    int[] sourceValues = {10, 8, 6, 4, 2, 0};

    IterativeQuickSort.quicksort(sourceValues);

    assertArrayEquals(new int[] {0, 2, 4, 6, 8, 10}, sourceValues);
  }

  @Test
  void quicksortShouldSortRequestedSubSegmentOnly() {
    int[] sourceValues = {50, 40, 9, 3, 7, 1, 80, 70};

    IterativeQuickSort.quicksort(sourceValues, 2, 4);

    assertArrayEquals(new int[] {50, 40, 1, 3, 7, 9, 80, 70}, sourceValues);
  }

  @Test
  void quicksortShouldAllowEmptyAndSingleElementSegments() {
    int[] sourceValues = {4, 3, 2, 1};

    IterativeQuickSort.quicksort(sourceValues, 1, 0);
    IterativeQuickSort.quicksort(sourceValues, 2, 1);

    assertArrayEquals(new int[] {4, 3, 2, 1}, sourceValues);
  }

  @Test
  void quicksortShouldRejectNullSourceArray() {
    assertThrows(IterativeQuickSortException.class, () -> IterativeQuickSort.quicksort(null));
    assertThrows(
        IterativeQuickSortException.class, () -> IterativeQuickSort.quicksort(null, 0, 0));
  }

  @Test
  void quicksortShouldRejectNegativeFirstIndex() {
    int[] sourceValues = {1, 2, 3};

    assertThrows(
        IterativeQuickSortException.class,
        () -> IterativeQuickSort.quicksort(sourceValues, -1, 2));
  }

  @Test
  void quicksortShouldRejectNegativeSegmentLength() {
    int[] sourceValues = {1, 2, 3};

    assertThrows(
        IterativeQuickSortException.class,
        () -> IterativeQuickSort.quicksort(sourceValues, 0, -1));
  }

  @Test
  void quicksortShouldRejectOutOfBoundsSegment() {
    int[] sourceValues = {1, 2, 3, 4};

    assertThrows(
        IterativeQuickSortException.class,
        () -> IterativeQuickSort.quicksort(sourceValues, 5, 0));
    assertThrows(
        IterativeQuickSortException.class,
        () -> IterativeQuickSort.quicksort(sourceValues, 2, 3));
  }
}
