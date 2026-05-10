package dev.jameswarmothiii.assignment5.questions.chapter11.four;

public class Table<K, E> {
  private int manyItems;
  private final Object[] keys;
  private final Object[] data;
  private final boolean[] hasBeenUsed;

  public Table(int capacity) {
    if (capacity <= 0) {
      throw TableException.capacityMustBePositive(capacity);
    }

    manyItems = 0;
    keys = new Object[capacity];
    data = new Object[capacity];
    hasBeenUsed = new boolean[capacity];
  }

  public int size() {
    return manyItems;
  }

  public boolean containsKey(K key) {
    return findIndex(key) != -1;
  }

  private int findIndex(K key) {
    validateKeyIsNotNull(key);

    int stepSize = secondaryHash(key);
    int currentIndex = hash(key);
    int probeCount = 0;

    while (probeCount < data.length && hasBeenUsed[currentIndex]) {
      if (keys[currentIndex] != null && key.equals(keys[currentIndex])) {
        return currentIndex;
      }

      probeCount++;
      currentIndex = nextIndex(currentIndex, stepSize);
    }

    return -1;
  }

  @SuppressWarnings("unchecked")
  public E get(K key) {
    int index = findIndex(key);
    if (index == -1) {
      return null;
    }

    return (E) data[index];
  }

  public E put(K key, E element) {
    validateKeyIsNotNull(key);

    int stepSize = secondaryHash(key);
    int currentIndex = hash(key);
    int firstRemovedIndex = -1;
    int probeCount = 0;

    while (probeCount < data.length && hasBeenUsed[currentIndex]) {
      if (keys[currentIndex] != null && key.equals(keys[currentIndex])) {
        @SuppressWarnings("unchecked")
        E previousElement = (E) data[currentIndex];
        data[currentIndex] = element;
        return previousElement;
      }

      if (keys[currentIndex] == null && firstRemovedIndex == -1) {
        firstRemovedIndex = currentIndex;
      }

      probeCount++;
      currentIndex = nextIndex(currentIndex, stepSize);
    }

    int insertionIndex = firstRemovedIndex;
    if (insertionIndex == -1 && probeCount < data.length) {
      insertionIndex = currentIndex;
    }

    if (insertionIndex == -1) {
      throw TableException.tableIsFull();
    }

    keys[insertionIndex] = key;
    data[insertionIndex] = element;
    hasBeenUsed[insertionIndex] = true;
    manyItems++;
    return null;
  }

  public E remove(K key) {
    int index = findIndex(key);
    if (index == -1) {
      return null;
    }

    @SuppressWarnings("unchecked")
    E removedElement = (E) data[index];
    keys[index] = null;
    data[index] = null;
    manyItems--;
    return removedElement;
  }

  private int hash(Object key) {
    return Math.floorMod(key.hashCode(), data.length);
  }

  private int secondaryHash(Object key) {
    if (data.length == 1) {
      return 1;
    }

    int stepSize = 1 + Math.floorMod(key.hashCode(), data.length - 1);
    while (greatestCommonDivisor(stepSize, data.length) != 1) {
      stepSize++;
      if (stepSize == data.length) {
        stepSize = 1;
      }
    }

    return stepSize;
  }

  private int nextIndex(int indexValue, int stepSize) {
    return (indexValue + stepSize) % data.length;
  }

  private int greatestCommonDivisor(int firstValue, int secondValue) {
    int firstAbsoluteValue = Math.abs(firstValue);
    int secondAbsoluteValue = Math.abs(secondValue);

    while (secondAbsoluteValue != 0) {
      int remainder = firstAbsoluteValue % secondAbsoluteValue;
      firstAbsoluteValue = secondAbsoluteValue;
      secondAbsoluteValue = remainder;
    }

    return firstAbsoluteValue;
  }

  private void validateKeyIsNotNull(K key) {
    if (key == null) {
      throw TableException.keyMustNotBeNull();
    }
  }
}
