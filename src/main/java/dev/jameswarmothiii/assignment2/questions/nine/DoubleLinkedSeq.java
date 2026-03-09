package dev.jameswarmothiii.assignment2.questions.nine;

public class DoubleLinkedSeq implements Cloneable {
  private static final class DoubleNode {
    private double dataValue;
    private DoubleNode nextNode;

    private DoubleNode(double initialDataValue, DoubleNode initialNextNode) {
      dataValue = initialDataValue;
      nextNode = initialNextNode;
    }
  }

  private DoubleNode headNode;
  private DoubleNode tailNode;
  private DoubleNode currentNode;
  private DoubleNode previousNode;
  private int manyNodes;

  public DoubleLinkedSeq() {
    headNode = null;
    tailNode = null;
    currentNode = null;
    previousNode = null;
    manyNodes = 0;
  }

  public void addAfter(double elementValue) {
    ensureCanIncreaseSizeByOne();

    DoubleNode newNode;

    if (!isCurrent()) {
      newNode = new DoubleNode(elementValue, null);

      if (manyNodes == 0) {
        headNode = newNode;
        tailNode = newNode;
        previousNode = null;
      } else {
        previousNode = tailNode;
        tailNode.nextNode = newNode;
        tailNode = newNode;
      }

      currentNode = newNode;
      manyNodes++;
      return;
    }

    newNode = new DoubleNode(elementValue, currentNode.nextNode);
    currentNode.nextNode = newNode;
    previousNode = currentNode;
    currentNode = newNode;

    if (newNode.nextNode == null) {
      tailNode = newNode;
    }

    manyNodes++;
  }

  public void addBefore(double elementValue) {
    ensureCanIncreaseSizeByOne();

    DoubleNode newNode;

    if (!isCurrent() || currentNode == headNode) {
      newNode = new DoubleNode(elementValue, headNode);
      headNode = newNode;

      if (manyNodes == 0) {
        tailNode = newNode;
      }

      currentNode = newNode;
      previousNode = null;
      manyNodes++;
      return;
    }

    newNode = new DoubleNode(elementValue, currentNode);
    previousNode.nextNode = newNode;
    currentNode = newNode;
    manyNodes++;
  }

  public void addAll(DoubleLinkedSeq addend) {
    if (addend == null) {
      throw new NullPointerException("addend is null");
    }

    if (addend.manyNodes == 0) {
      return;
    }

    DoubleNode[] copiedHeadAndTailNodes = copyNodeChainWithTail(addend.headNode);
    DoubleNode copiedHeadNode = copiedHeadAndTailNodes[0];
    DoubleNode copiedTailNode = copiedHeadAndTailNodes[1];

    if (headNode == null) {
      headNode = copiedHeadNode;
      tailNode = copiedTailNode;
    } else {
      tailNode.nextNode = copiedHeadNode;
      tailNode = copiedTailNode;
    }

    manyNodes = safeAdd(manyNodes, addend.manyNodes);
  }

  public void advance() {
    if (!isCurrent()) {
      throw new IllegalStateException("No current element");
    }

    previousNode = currentNode;
    currentNode = currentNode.nextNode;
  }

  @Override
  public DoubleLinkedSeq clone() {
    try {
      DoubleLinkedSeq copiedSequence = (DoubleLinkedSeq) super.clone();

      if (headNode == null) {
        copiedSequence.headNode = null;
        copiedSequence.tailNode = null;
        copiedSequence.currentNode = null;
        copiedSequence.previousNode = null;
        copiedSequence.manyNodes = 0;
        return copiedSequence;
      }

      DoubleNode copiedHeadNode = null;
      DoubleNode copiedTailNode = null;
      DoubleNode copiedCurrentNode = null;
      DoubleNode copiedPreviousNode = null;

      for (DoubleNode sourceNode = headNode; sourceNode != null; sourceNode = sourceNode.nextNode) {
        DoubleNode newCopiedNode = new DoubleNode(sourceNode.dataValue, null);

        if (copiedHeadNode == null) {
          copiedHeadNode = newCopiedNode;
        } else {
          copiedTailNode.nextNode = newCopiedNode;
        }
        copiedTailNode = newCopiedNode;

        if (sourceNode == currentNode) {
          copiedCurrentNode = newCopiedNode;
        }

        if (sourceNode == previousNode) {
          copiedPreviousNode = newCopiedNode;
        }
      }

      copiedSequence.headNode = copiedHeadNode;
      copiedSequence.tailNode = copiedTailNode;
      copiedSequence.currentNode = copiedCurrentNode;
      copiedSequence.previousNode = copiedPreviousNode;
      copiedSequence.manyNodes = manyNodes;
      return copiedSequence;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError("Clone should be supported", cloneNotSupportedException);
    }
  }

  public static DoubleLinkedSeq concatenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) {
    if (s1 == null || s2 == null) {
      throw new IllegalArgumentException("One of the sequences is null");
    }

    DoubleLinkedSeq answer = new DoubleLinkedSeq();
    answer.addAll(s1);
    answer.addAll(s2);
    return answer;
  }

  public double getCurrent() {
    if (!isCurrent()) {
      throw new IllegalStateException("No current element");
    }

    return currentNode.dataValue;
  }

  public boolean isCurrent() {
    return currentNode != null;
  }

  public void removeCurrent() {
    if (!isCurrent()) {
      throw new IllegalStateException("No current element");
    }

    if (currentNode == headNode) {
      headNode = headNode.nextNode;
      currentNode = headNode;
      previousNode = null;

      if (headNode == null) {
        tailNode = null;
      }
    } else {
      previousNode.nextNode = currentNode.nextNode;

      if (currentNode == tailNode) {
        tailNode = previousNode;
      }

      currentNode = previousNode.nextNode;
    }

    manyNodes--;
  }

  public int size() {
    return manyNodes;
  }

  public void start() {
    currentNode = headNode;
    previousNode = null;
  }

  public void addFront(double elementValue) {
    ensureCanIncreaseSizeByOne();

    DoubleNode newNode = new DoubleNode(elementValue, headNode);
    headNode = newNode;

    if (manyNodes == 0) {
      tailNode = newNode;
    }

    currentNode = newNode;
    previousNode = null;
    manyNodes++;
  }

  public void removeFront() {
    if (manyNodes == 0) {
      throw new IllegalStateException("Sequence is empty");
    }

    DoubleNode removedHeadNode = headNode;
    headNode = headNode.nextNode;
    manyNodes--;

    if (headNode == null) {
      tailNode = null;
      currentNode = null;
      previousNode = null;
      return;
    }

    if (currentNode == removedHeadNode) {
      currentNode = headNode;
      previousNode = null;
      return;
    }

    if (previousNode == removedHeadNode) {
      previousNode = null;
    }
  }

  public void addEnd(double elementValue) {
    ensureCanIncreaseSizeByOne();

    DoubleNode newNode = new DoubleNode(elementValue, null);

    if (manyNodes == 0) {
      headNode = newNode;
      tailNode = newNode;
      currentNode = newNode;
      previousNode = null;
      manyNodes = 1;
      return;
    }

    previousNode = tailNode;
    tailNode.nextNode = newNode;
    tailNode = newNode;
    currentNode = newNode;
    manyNodes++;
  }

  public void setCurrentToLast() {
    if (manyNodes == 0) {
      currentNode = null;
      previousNode = null;
      return;
    }

    currentNode = tailNode;

    if (headNode == tailNode) {
      previousNode = null;
      return;
    }

    DoubleNode nodeBeforeTail = headNode;
    while (nodeBeforeTail.nextNode != tailNode) {
      nodeBeforeTail = nodeBeforeTail.nextNode;
    }

    previousNode = nodeBeforeTail;
  }

  public double getElementAt(int indexValue) {
    if (indexValue < 0 || indexValue >= manyNodes) {
      throw new IndexOutOfBoundsException("index: " + indexValue + ", size: " + manyNodes);
    }

    DoubleNode nodeAtIndex = headNode;
    for (int currentIndexValue = 0; currentIndexValue < indexValue; currentIndexValue++) {
      nodeAtIndex = nodeAtIndex.nextNode;
    }

    return nodeAtIndex.dataValue;
  }

  public void setCurrentToIndex(int indexValue) {
    if (indexValue < 0 || indexValue >= manyNodes) {
      throw new IndexOutOfBoundsException("index: " + indexValue + ", size: " + manyNodes);
    }

    previousNode = null;
    currentNode = headNode;

    for (int currentIndexValue = 0; currentIndexValue < indexValue; currentIndexValue++) {
      previousNode = currentNode;
      currentNode = currentNode.nextNode;
    }
  }

  private void ensureCanIncreaseSizeByOne() {
    if (manyNodes == Integer.MAX_VALUE) {
      throw new OutOfMemoryError("Sequence size overflow");
    }
  }

  private static int safeAdd(int firstValue, int secondValue) {
    long sumValue = (long) firstValue + secondValue;

    if (sumValue > Integer.MAX_VALUE) {
      throw new OutOfMemoryError("Sequence size overflow");
    }

    return (int) sumValue;
  }

  private static DoubleNode[] copyNodeChainWithTail(DoubleNode sourceHeadNode) {
    if (sourceHeadNode == null) {
      return new DoubleNode[] {null, null};
    }

    DoubleNode copiedHeadNode = new DoubleNode(sourceHeadNode.dataValue, null);
    DoubleNode copiedTailNode = copiedHeadNode;

    for (DoubleNode sourceNode = sourceHeadNode.nextNode;
        sourceNode != null;
        sourceNode = sourceNode.nextNode) {
      copiedTailNode.nextNode = new DoubleNode(sourceNode.dataValue, null);
      copiedTailNode = copiedTailNode.nextNode;
    }

    return new DoubleNode[] {copiedHeadNode, copiedTailNode};
  }
}
