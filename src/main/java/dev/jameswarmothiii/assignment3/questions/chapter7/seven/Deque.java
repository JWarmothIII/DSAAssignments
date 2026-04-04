package dev.jameswarmothiii.assignment3.questions.chapter7.seven;

public class Deque<E> {
  private static final class DoubleNode<T> {
    private final T dataValue;
    private DoubleNode<T> previousNode;
    private DoubleNode<T> nextNode;

    private DoubleNode(
        T initialDataValue, DoubleNode<T> initialPreviousNode, DoubleNode<T> initialNextNode) {
      dataValue = initialDataValue;
      previousNode = initialPreviousNode;
      nextNode = initialNextNode;
    }
  }

  private int manyNodes;
  private DoubleNode<E> frontNode;
  private DoubleNode<E> rearNode;

  public Deque() {
    manyNodes = 0;
    frontNode = null;
    rearNode = null;
  }

  public boolean isEmpty() {
    return manyNodes == 0;
  }

  public int size() {
    return manyNodes;
  }

  public void addFront(E itemValue) {
    ensureCanIncreaseSizeByOne();

    DoubleNode<E> newFrontNode = new DoubleNode<>(itemValue, null, frontNode);
    if (isEmpty()) {
      frontNode = newFrontNode;
      rearNode = newFrontNode;
    } else {
      frontNode.previousNode = newFrontNode;
      frontNode = newFrontNode;
    }

    manyNodes++;
  }

  public void addRear(E itemValue) {
    ensureCanIncreaseSizeByOne();

    DoubleNode<E> newRearNode = new DoubleNode<>(itemValue, rearNode, null);
    if (isEmpty()) {
      frontNode = newRearNode;
      rearNode = newRearNode;
    } else {
      rearNode.nextNode = newRearNode;
      rearNode = newRearNode;
    }

    manyNodes++;
  }

  public E removeFront() {
    if (isEmpty()) {
      throw DequeException.dequeUnderflow();
    }

    E removedValue = frontNode.dataValue;
    frontNode = frontNode.nextNode;
    manyNodes--;

    if (frontNode == null) {
      rearNode = null;
    } else {
      frontNode.previousNode = null;
    }

    return removedValue;
  }

  public E removeRear() {
    if (isEmpty()) {
      throw DequeException.dequeUnderflow();
    }

    E removedValue = rearNode.dataValue;
    rearNode = rearNode.previousNode;
    manyNodes--;

    if (rearNode == null) {
      frontNode = null;
    } else {
      rearNode.nextNode = null;
    }

    return removedValue;
  }

  private void ensureCanIncreaseSizeByOne() {
    if (manyNodes == Integer.MAX_VALUE) {
      throw DequeException.dequeSizeOverflow();
    }
  }
}
