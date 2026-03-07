package dev.jameswarmothiii.assignment1.questions.chapter3.four;

public class DoubleArraySeq implements Cloneable {
  private double[] data;
  private int manyItems;
  private int currentIndex;

  public DoubleArraySeq() {
    this(10);
  }

  public DoubleArraySeq(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("initialCapacity is negative: " + initialCapacity);
    }

    data = new double[initialCapacity];
    manyItems = 0;
    currentIndex = 0;
  }

  public static DoubleArraySeq concatenation(
      DoubleArraySeq firstSequence, DoubleArraySeq secondSequence) {
    if (firstSequence == null || secondSequence == null) {
      throw new NullPointerException("One of the sequences is null");
    }

    int combinedSize = safeAdd(firstSequence.manyItems, secondSequence.manyItems);
    DoubleArraySeq answer = new DoubleArraySeq(combinedSize);

    System.arraycopy(firstSequence.data, 0, answer.data, 0, firstSequence.manyItems);
    System.arraycopy(
        secondSequence.data, 0, answer.data, firstSequence.manyItems, secondSequence.manyItems);

    answer.manyItems = combinedSize;
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

  public void addAfter(double element) {
    if (manyItems == data.length) {
      ensureCapacity(growthCapacity(manyItems + 1));
    }

    if (!isCurrent()) {
      currentIndex = manyItems;
    } else {
      currentIndex = currentIndex + 1;
      System.arraycopy(data, currentIndex, data, currentIndex + 1, manyItems - currentIndex);
    }

    data[currentIndex] = element;
    manyItems++;
  }

  public void addBefore(double element) {
    if (manyItems == data.length) {
      ensureCapacity(growthCapacity(manyItems + 1));
    }

    if (!isCurrent()) {
      currentIndex = 0;
    }

    System.arraycopy(data, currentIndex, data, currentIndex + 1, manyItems - currentIndex);
    data[currentIndex] = element;
    manyItems++;
  }

  public void addAll(DoubleArraySeq addend) {
    if (addend == null) {
      throw new NullPointerException("addend is null");
    }

    if (addend.manyItems == 0) {
      return;
    }

    ensureCapacity(safeAdd(manyItems, addend.manyItems));
    System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
    manyItems += addend.manyItems;
  }

  public void advance() {
    if (!isCurrent()) {
      throw new IllegalStateException("No current element");
    }

    currentIndex++;
  }

  @Override
  public DoubleArraySeq clone() throws CloneNotSupportedException {
    DoubleArraySeq answer = (DoubleArraySeq) super.clone();
    answer.data = data.clone();
    return answer;
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

  // Extra methods for fun

  public void trimToSize() {
    if (data.length == manyItems) {
      return;
    }

    double[] trimmedArray = new double[manyItems];
    System.arraycopy(data, 0, trimmedArray, 0, manyItems);
    data = trimmedArray;
  }

  public void addFront(double element) {
    currentIndex = 0;
    addBefore(element);
  }

  public void removeFront() {
    if (manyItems == 0) {
      throw new IllegalStateException("Sequence is empty");
    }

    currentIndex = 0;
    removeCurrent();
  }

  public void addEnd(double element) {
    currentIndex = manyItems;
    addAfter(element);
  }

  public void setCurrentToLast() {
    if (manyItems == 0) {
      currentIndex = manyItems;
    } else {
      currentIndex = manyItems - 1;
    }
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

  private int growthCapacity(int requiredCapacity) {
    if (requiredCapacity < 0) {
      throw new OutOfMemoryError("Capacity overflow");
    }

    int doubled = (data.length == 0) ? 1 : data.length * 2;

    if (doubled < 0) {
      doubled = Integer.MAX_VALUE;
    }

    return Math.max(requiredCapacity, doubled);
  }
}
