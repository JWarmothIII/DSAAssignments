package dev.jameswarmothiii.assignment3.questions.chapter7.six;

public class LinkedQueue<E> implements Cloneable {
  private static final class Node<T> {
    private final T dataValue;
    private Node<T> link;

    private Node(T initialDataValue, Node<T> initialLink) {
      dataValue = initialDataValue;
      link = initialLink;
    }
  }

  private int manyNodes;
  private Node<E> rear;

  public LinkedQueue() {
    manyNodes = 0;
    rear = null;
  }

  public void add(E itemValue) {
    ensureCanIncreaseSizeByOne();

    Node<E> newNode = new Node<>(itemValue, null);

    if (isEmpty()) {
      newNode.link = newNode;
      rear = newNode;
    } else {
      newNode.link = rear.link;
      rear.link = newNode;
      rear = newNode;
    }

    manyNodes++;
  }

  @Override
  public LinkedQueue<E> clone() {
    try {
      @SuppressWarnings("unchecked")
      LinkedQueue<E> copiedQueue = (LinkedQueue<E>) super.clone();

      if (manyNodes == 0) {
        copiedQueue.rear = null;
        copiedQueue.manyNodes = 0;
        return copiedQueue;
      }

      Node<E> sourceFront = rear.link;
      Node<E> sourceCursor = sourceFront.link;

      Node<E> copiedFront = new Node<>(sourceFront.dataValue, null);
      Node<E> copiedTail = copiedFront;

      while (sourceCursor != sourceFront) {
        copiedTail.link = new Node<>(sourceCursor.dataValue, null);
        copiedTail = copiedTail.link;
        sourceCursor = sourceCursor.link;
      }

      copiedTail.link = copiedFront;
      copiedQueue.rear = copiedTail;
      copiedQueue.manyNodes = manyNodes;
      return copiedQueue;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw LinkedQueueException.cloneShouldBeSupported(cloneNotSupportedException);
    }
  }

  public boolean isEmpty() {
    return manyNodes == 0;
  }

  public E remove() {
    if (manyNodes == 0) {
      throw LinkedQueueException.queueUnderflow();
    }

    Node<E> frontNode = rear.link;
    E removedValue = frontNode.dataValue;

    if (manyNodes == 1) {
      rear = null;
    } else {
      rear.link = frontNode.link;
    }

    manyNodes--;
    return removedValue;
  }

  public int size() {
    return manyNodes;
  }

  private void ensureCanIncreaseSizeByOne() {
    if (manyNodes == Integer.MAX_VALUE) {
      throw LinkedQueueException.queueSizeOverflow();
    }
  }
}
