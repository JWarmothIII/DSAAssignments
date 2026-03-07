package dev.jameswarmothiii.assignment1.questions.chapter3.seven;

public class DoubleArraySortedSeq implements Cloneable {
  private double[] data;
  private int manyItems;
  private int currentIndex;

  public DoubleArraySortedSeq() {
    this(10);
  }

  public DoubleArraySortedSeq(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("initialCapacity is negative: " + initialCapacity);
    }

    data = new double[initialCapacity];
    manyItems = 0;
    currentIndex = 0;
  }

  public static DoubleArraySortedSeq concatenation(
      DoubleArraySortedSeq firstSequence, DoubleArraySortedSeq secondSequence) {
    if (firstSequence == null || secondSequence == null) {
      throw new NullPointerException("One of the sequences is null");
    }

    DoubleArraySortedSeq answer =
        new DoubleArraySortedSeq(safeAdd(firstSequence.manyItems, secondSequence.manyItems));

    int firstIndex = 0;
    int secondIndex = 0;
    int answerIndex = 0;

    while (firstIndex < firstSequence.manyItems && secondIndex < secondSequence.manyItems) {
      if (firstSequence.data[firstIndex] <= secondSequence.data[secondIndex]) {
        answer.data[answerIndex] = firstSequence.data[firstIndex];
        firstIndex++;
      } else {
        answer.data[answerIndex] = secondSequence.data[secondIndex];
        secondIndex++;
      }
      answerIndex++;
    }

    while (firstIndex < firstSequence.manyItems) {
      answer.data[answerIndex] = firstSequence.data[firstIndex];
      firstIndex++;
      answerIndex++;
    }

    while (secondIndex < secondSequence.manyItems) {
      answer.data[answerIndex] = secondSequence.data[secondIndex];
      secondIndex++;
      answerIndex++;
    }

    answer.manyItems = answerIndex;
    answer.currentIndex = answer.manyItems;
    return answer;
  }

  private static int safeAdd(int firstValue, int secondValue) {
    long sum = (long) firstValue + secondValue;
    if (sum > Integer.MAX_VALUE) {
      throw new OutOfMemoryError("Capacity overflow");
    }
    return (int) sum;
  }

  public void add(double element) {
    if (manyItems == data.length) {
      ensureCapacity(growthCapacity(manyItems + 1));
    }

    int insertionIndex = 0;
    while (insertionIndex < manyItems && data[insertionIndex] <= element) {
      insertionIndex++;
    }

    System.arraycopy(data, insertionIndex, data, insertionIndex + 1, manyItems - insertionIndex);
    data[insertionIndex] = element;
    manyItems++;
    currentIndex = insertionIndex;
  }

  public void addAll(DoubleArraySortedSeq addend) {
    if (addend == null) {
      throw new NullPointerException("addend is null");
    }

    for (int index = 0; index < addend.manyItems; index++) {
      add(addend.data[index]);
    }
  }

  public void advance() {
    if (!isCurrent()) {
      throw new IllegalStateException("No current element");
    }

    currentIndex++;
  }

  @Override
  public DoubleArraySortedSeq clone() {
    try {
      DoubleArraySortedSeq answer = (DoubleArraySortedSeq) super.clone();
      answer.data = data.clone();
      return answer;
    } catch (CloneNotSupportedException exception) {
      throw new AssertionError("Clone should be supported", exception);
    }
  }

  public void ensureCapacity(int minimumCapacity) {
    if (minimumCapacity < 0) {
      throw new IllegalArgumentException("minimumCapacity is negative: " + minimumCapacity);
    }

    if (data.length >= minimumCapacity) {
      return;
    }

    double[] biggerArray = new double[minimumCapacity];
    System.arraycopy(data, 0, biggerArray, 0, manyItems);
    data = biggerArray;
  }

  public int getCapacity() {
    return data.length;
  }

  public double getCurrent() {
    if (!isCurrent()) {
      throw new IllegalStateException("No current element");
    }

    return data[currentIndex];
  }

  public boolean isCurrent() {
    return currentIndex < manyItems;
  }

  public void removeCurrent() {
    if (!isCurrent()) {
      throw new IllegalStateException("No current element");
    }

    System.arraycopy(data, currentIndex + 1, data, currentIndex, manyItems - currentIndex - 1);
    manyItems--;
  }

  public int size() {
    return manyItems;
  }

  public void start() {
    currentIndex = (manyItems == 0) ? manyItems : 0;
  }

  public void trimToSize() {
    if (data.length == manyItems) {
      return;
    }

    double[] trimmedArray = new double[manyItems];
    System.arraycopy(data, 0, trimmedArray, 0, manyItems);
    data = trimmedArray;
  }

  public double getElementAt(int index) {
    if (index < 0 || index >= manyItems) {
      throw new IndexOutOfBoundsException("index: " + index + ", size: " + manyItems);
    }

    return data[index];
  }

  public void setCurrentToIndex(int index) {
    if (index < 0 || index >= manyItems) {
      throw new IndexOutOfBoundsException("index: " + index + ", size: " + manyItems);
    }

    currentIndex = index;
  }

  public void setCurrentToLast() {
    if (manyItems == 0) {
      currentIndex = manyItems;
    } else {
      currentIndex = manyItems - 1;
    }
  }

  private int growthCapacity(int requiredCapacity) {
    if (requiredCapacity < 0) {
      throw new OutOfMemoryError("Capacity overflow");
    }

    int doubledCapacity = (data.length == 0) ? 1 : data.length * 2;

    if (doubledCapacity < 0) {
      doubledCapacity = Integer.MAX_VALUE;
    }

    return Math.max(requiredCapacity, doubledCapacity);
  }
}
