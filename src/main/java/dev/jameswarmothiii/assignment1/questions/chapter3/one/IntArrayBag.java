package dev.jameswarmothiii.assignment1.questions.chapter3.one;

public class IntArrayBag {
  private int[] data;
  private int manyItems;

  public IntArrayBag() {
    final int initialCapacity = 10;
    manyItems = 0;
    data = new int[initialCapacity];
  }

  public IntArrayBag(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("initialCapacity is negative: " + initialCapacity);
    }

    manyItems = 0;
    data = new int[initialCapacity];
  }

  public void add(int element) {
    if (manyItems == data.length) {
      ensureCapacity((manyItems + 1) * 2);
    }

    data[manyItems] = element;
    manyItems++;
  }

  public void ensureCapacity(int minimumCapacity) {
    int[] biggerArray;

    if (data.length < minimumCapacity) {
      biggerArray = new int[minimumCapacity];
      System.arraycopy(data, 0, biggerArray, 0, manyItems);
      data = biggerArray;
    }
  }

  public boolean equals(IntArrayBag b) {
    if (b == null) {
      return false;
    }

    if (this.manyItems != b.manyItems) {
      return false;
    }

    for (int index = 0; index < this.manyItems; index++) {
      int value = this.data[index];

      if (this.countOfValueInThisBag(value) != b.countOfValueInThisBag(value)) {
        return false;
      }
    }

    return true;
  }

  private int countOfValueInThisBag(int targetValue) {
    int count = 0;

    for (int index = 0; index < manyItems; index++) {
      if (data[index] == targetValue) {
        count++;
      }
    }

    return count;
  }
}
