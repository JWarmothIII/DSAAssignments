package dev.jameswarmothiii.assignment2.questions.two.fourteen;

import java.util.HashSet;
import java.util.Set;

public class DoubleNode {
  private double dataValue;
  private DoubleNode previousNode;
  private DoubleNode nextNode;

  public DoubleNode(
      double initialDataValue, DoubleNode initialPreviousNode, DoubleNode initialNextNode) {
    dataValue = initialDataValue;
    previousNode = initialPreviousNode;
    nextNode = initialNextNode;

    if (previousNode != null) {
      previousNode.nextNode = this;
    }

    if (nextNode != null) {
      nextNode.previousNode = this;
    }
  }

  public static int listLength(DoubleNode headNode) {
    int listLengthValue = 0;
    DoubleNode currentNode;

    for (currentNode = headNode; currentNode != null; currentNode = currentNode.nextNode) {
      listLengthValue++;
    }

    return listLengthValue;
  }

  public static DoubleNode listPosition(DoubleNode headNode, int positionValue) {
    if (positionValue <= 0) {
      throw new IllegalArgumentException("positionValue is not positive: " + positionValue);
    }

    DoubleNode currentNode = headNode;

    for (int currentPosition = 1;
        currentNode != null && currentPosition < positionValue;
        currentPosition++) {
      currentNode = currentNode.nextNode;
    }

    return currentNode;
  }

  public static DoubleNode listSearch(DoubleNode headNode, double targetValue) {
    DoubleNode currentNode;

    for (currentNode = headNode; currentNode != null; currentNode = currentNode.nextNode) {
      if (currentNode.dataValue == targetValue) {
        return currentNode;
      }
    }

    return null;
  }

  public static DoubleNode listCopy(DoubleNode sourceNode) {
    return listCopyWithTail(sourceNode)[0];
  }

  public static DoubleNode listCopyWithoutRepetitions(DoubleNode sourceHeadNode) {
    if (sourceHeadNode == null) {
      return null;
    }

    Set<Double> seenDataValues = new HashSet<>();
    DoubleNode copiedHeadNode = null;
    DoubleNode copiedTailNode = null;

    for (DoubleNode currentSourceNode = sourceHeadNode;
        currentSourceNode != null;
        currentSourceNode = currentSourceNode.nextNode) {
      if (!seenDataValues.add(currentSourceNode.dataValue)) {
        continue;
      }

      if (copiedHeadNode == null) {
        copiedHeadNode = new DoubleNode(currentSourceNode.dataValue, null, null);
        copiedTailNode = copiedHeadNode;
      } else {
        copiedTailNode = new DoubleNode(currentSourceNode.dataValue, copiedTailNode, null);
      }
    }

    return copiedHeadNode;
  }

  public static DoubleNode[] listCopyWithTail(DoubleNode sourceNode) {
    if (sourceNode == null) {
      return new DoubleNode[] {null, null};
    }

    DoubleNode copiedHeadNode = new DoubleNode(sourceNode.dataValue, null, null);
    DoubleNode copiedTailNode = copiedHeadNode;

    for (DoubleNode currentSourceNode = sourceNode.nextNode;
        currentSourceNode != null;
        currentSourceNode = currentSourceNode.nextNode) {
      copiedTailNode.addNodeAfter(currentSourceNode.dataValue);
      copiedTailNode = copiedTailNode.nextNode;
    }

    return new DoubleNode[] {copiedHeadNode, copiedTailNode};
  }

  public void addNodeAfter(double elementDataValue) {
    new DoubleNode(elementDataValue, this, nextNode);
  }

  public void addNodeBefore(double elementDataValue) {
    new DoubleNode(elementDataValue, previousNode, this);
  }

  public double getData() {
    return dataValue;
  }

  public DoubleNode getPrevious() {
    return previousNode;
  }

  public DoubleNode getNext() {
    return nextNode;
  }

  public void setData(double newDataValue) {
    dataValue = newDataValue;
  }

  public void setPrevious(DoubleNode newPreviousNode) {
    previousNode = newPreviousNode;
  }

  public void setNext(DoubleNode newNextNode) {
    nextNode = newNextNode;
  }

  public void removeNode() {
    if (previousNode != null) {
      previousNode.nextNode = nextNode;
    }

    if (nextNode != null) {
      nextNode.previousNode = previousNode;
    }

    previousNode = null;
    nextNode = null;
  }

  public void removeNodeAfter() {
    removeNode();
  }
}
