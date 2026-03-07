package dev.jameswarmothiii.assignment1.questions.chapter3.twentysix;

public class IntCountBag {
  private int[] countsByValue;
  private int totalItemCount;
  private int maximumAllowedValue;

  public IntCountBag() {
    maximumAllowedValue = 10;
    countsByValue = new int[maximumAllowedValue + 1];
    totalItemCount = 0;
  }

  public IntCountBag(int initialMaximumValue) {
    if (initialMaximumValue < 0) {
      throw new IllegalArgumentException("initialMaximumValue is negative: " + initialMaximumValue);
    }

    maximumAllowedValue = initialMaximumValue;
    countsByValue = new int[maximumAllowedValue + 1];
    totalItemCount = 0;
  }

  private static void validateNonNegative(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("value is negative: " + value);
    }
  }

  public void setAnticipatedMaximumValue(int anticipatedMaximumValue) {
    if (anticipatedMaximumValue < 0) {
      throw new IllegalArgumentException(
          "anticipatedMaximumValue is negative: " + anticipatedMaximumValue);
    }

    ensureCapacityForValue(anticipatedMaximumValue);
  }

  public int size() {
    return totalItemCount;
  }

  public int countOccurrences(int value) {
    validateNonNegative(value);

    if (value > maximumAllowedValue) {
      return 0;
    }

    return countsByValue[value];
  }

  public void add(int value) {
    validateNonNegative(value);

    ensureCapacityForValue(value);

    countsByValue[value]++;
    totalItemCount++;
  }

  public boolean remove(int value) {
    validateNonNegative(value);

    if (value > maximumAllowedValue) {
      return false;
    }

    if (countsByValue[value] == 0) {
      return false;
    }

    countsByValue[value]--;
    totalItemCount--;
    return true;
  }

  public void addAll(IntCountBag addend) {
    if (addend == null) {
      throw new NullPointerException("addend is null");
    }

    if (addend.totalItemCount == 0) {
      return;
    }

    ensureCapacityForValue(addend.maximumAllowedValue);

    for (int value = 0; value <= addend.maximumAllowedValue; value++) {
      int countToAdd = addend.countsByValue[value];
      if (countToAdd == 0) {
        continue;
      }

      countsByValue[value] += countToAdd;
      totalItemCount += countToAdd;
    }
  }

  public IntCountBag clone() {
    IntCountBag copiedBag = new IntCountBag(maximumAllowedValue);
    copiedBag.countsByValue = countsByValue.clone();
    copiedBag.totalItemCount = totalItemCount;
    return copiedBag;
  }

  private void ensureCapacityForValue(int value) {
    if (value <= maximumAllowedValue) {
      return;
    }

    int newMaximumAllowedValue = Math.max(value, maximumAllowedValue * 2);
    if (newMaximumAllowedValue < 0) {
      newMaximumAllowedValue = Integer.MAX_VALUE - 1;
    }

    int[] expandedCountsByValue = new int[newMaximumAllowedValue + 1];
    System.arraycopy(countsByValue, 0, expandedCountsByValue, 0, countsByValue.length);
    countsByValue = expandedCountsByValue;
    maximumAllowedValue = newMaximumAllowedValue;
  }
}
