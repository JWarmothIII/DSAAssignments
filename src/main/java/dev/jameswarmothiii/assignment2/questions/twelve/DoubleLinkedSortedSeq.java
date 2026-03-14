package dev.jameswarmothiii.assignment2.questions.twelve;

public class DoubleLinkedSortedSeq implements Cloneable {
  private static final class DoubleNode {
    private double dataValue;
    private DoubleNode nextNode;

    private DoubleNode(double initialDataValue, DoubleNode initialNextNode) {
      dataValue = initialDataValue;
      nextNode = initialNextNode;
    }
  }

  private DoubleNode headNode;
  private int manyItems;
  private int currentIndex;

  public DoubleLinkedSortedSeq() {
    headNode = null;
    manyItems = 0;
    currentIndex = 0;
  }

  public static DoubleLinkedSortedSeq concatenation(
      DoubleLinkedSortedSeq firstSequence, DoubleLinkedSortedSeq secondSequence) {
    if (firstSequence == null || secondSequence == null) {
      throw DoubleLinkedSortedSeqException.oneSequenceIsNull();
    }

    DoubleLinkedSortedSeq answer = new DoubleLinkedSortedSeq();

    DoubleNode firstCursorNode = firstSequence.headNode;
    DoubleNode secondCursorNode = secondSequence.headNode;

    while (firstCursorNode != null && secondCursorNode != null) {
      if (firstCursorNode.dataValue <= secondCursorNode.dataValue) {
        answer.appendAtTail(firstCursorNode.dataValue);
        firstCursorNode = firstCursorNode.nextNode;
      } else {
        answer.appendAtTail(secondCursorNode.dataValue);
        secondCursorNode = secondCursorNode.nextNode;
      }
    }

    while (firstCursorNode != null) {
      answer.appendAtTail(firstCursorNode.dataValue);
      firstCursorNode = firstCursorNode.nextNode;
    }

    while (secondCursorNode != null) {
      answer.appendAtTail(secondCursorNode.dataValue);
      secondCursorNode = secondCursorNode.nextNode;
    }

    answer.currentIndex = answer.manyItems;
    return answer;
  }

  public void add(double elementValue) {
    int insertionIndex = 0;
    DoubleNode previousNode = null;
    DoubleNode currentNode = headNode;

    while (currentNode != null && currentNode.dataValue <= elementValue) {
      previousNode = currentNode;
      currentNode = currentNode.nextNode;
      insertionIndex++;
    }

    DoubleNode newNode = new DoubleNode(elementValue, currentNode);
    if (previousNode == null) {
      headNode = newNode;
    } else {
      previousNode.nextNode = newNode;
    }

    manyItems++;
    currentIndex = insertionIndex;
  }

  public void addAll(DoubleLinkedSortedSeq addend) {
    if (addend == null) {
      throw DoubleLinkedSortedSeqException.addendIsNull();
    }

    if (addend.manyItems == 0) {
      return;
    }

    DoubleLinkedSortedSeq sourceSequence = (addend == this) ? addend.clone() : addend;

    for (DoubleNode currentNode = sourceSequence.headNode;
        currentNode != null;
        currentNode = currentNode.nextNode) {
      add(currentNode.dataValue);
    }
  }

  public void advance() {
    if (!isCurrent()) {
      throw DoubleLinkedSortedSeqException.noCurrentElement();
    }

    currentIndex++;
  }

  @Override
  public DoubleLinkedSortedSeq clone() {
    try {
      DoubleLinkedSortedSeq copiedSequence = (DoubleLinkedSortedSeq) super.clone();
      copiedSequence.headNode = copyNodeChain(headNode);
      return copiedSequence;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw DoubleLinkedSortedSeqException.cloneShouldBeSupported(cloneNotSupportedException);
    }
  }

  public double getCurrent() {
    if (!isCurrent()) {
      throw DoubleLinkedSortedSeqException.noCurrentElement();
    }

    return nodeAt(currentIndex).dataValue;
  }

  public boolean isCurrent() {
    return currentIndex < manyItems;
  }

  public void removeCurrent() {
    if (!isCurrent()) {
      throw DoubleLinkedSortedSeqException.noCurrentElement();
    }

    if (currentIndex == 0) {
      headNode = headNode.nextNode;
    } else {
      DoubleNode previousNode = nodeAt(currentIndex - 1);
      previousNode.nextNode = previousNode.nextNode.nextNode;
    }

    manyItems--;
  }

  public int size() {
    return manyItems;
  }

  public void start() {
    currentIndex = (manyItems == 0) ? manyItems : 0;
  }

  public double getElementAt(int indexValue) {
    if (indexValue < 0 || indexValue >= manyItems) {
      throw DoubleLinkedSortedSeqException.indexOutOfBounds(indexValue, manyItems);
    }

    return nodeAt(indexValue).dataValue;
  }

  public void setCurrentToIndex(int indexValue) {
    if (indexValue < 0 || indexValue >= manyItems) {
      throw DoubleLinkedSortedSeqException.indexOutOfBounds(indexValue, manyItems);
    }

    currentIndex = indexValue;
  }

  public void setCurrentToLast() {
    if (manyItems == 0) {
      currentIndex = manyItems;
    } else {
      currentIndex = manyItems - 1;
    }
  }

  private static DoubleNode copyNodeChain(DoubleNode sourceHeadNode) {
    if (sourceHeadNode == null) {
      return null;
    }

    DoubleNode copiedHeadNode = new DoubleNode(sourceHeadNode.dataValue, null);
    DoubleNode copiedTailNode = copiedHeadNode;

    for (DoubleNode currentSourceNode = sourceHeadNode.nextNode;
        currentSourceNode != null;
        currentSourceNode = currentSourceNode.nextNode) {
      copiedTailNode.nextNode = new DoubleNode(currentSourceNode.dataValue, null);
      copiedTailNode = copiedTailNode.nextNode;
    }

    return copiedHeadNode;
  }

  private DoubleNode nodeAt(int indexValue) {
    DoubleNode currentNode = headNode;

    for (int currentPosition = 0; currentPosition < indexValue; currentPosition++) {
      currentNode = currentNode.nextNode;
    }

    return currentNode;
  }

  private void appendAtTail(double elementValue) {
    DoubleNode newNode = new DoubleNode(elementValue, null);

    if (headNode == null) {
      headNode = newNode;
      manyItems = 1;
      return;
    }

    DoubleNode tailNode = nodeAt(manyItems - 1);
    tailNode.nextNode = newNode;
    manyItems++;
  }
}
