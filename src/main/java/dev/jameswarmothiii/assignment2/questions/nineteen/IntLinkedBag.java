package dev.jameswarmothiii.assignment2.questions.nineteen;

public class IntLinkedBag implements Cloneable {
  private CountNode headNode;
  private int totalElementCount;

  private static final class CountNode {
    private int occurrenceCount;
    private int dataValue;
    private CountNode nextNode;

    private CountNode(int initialOccurrenceCount, int initialDataValue, CountNode initialNextNode) {
      occurrenceCount = initialOccurrenceCount;
      dataValue = initialDataValue;
      nextNode = initialNextNode;
    }
  }

  public IntLinkedBag() {
    headNode = null;
    totalElementCount = 0;
  }

  public void add(int elementValue) {
    addOccurrences(elementValue, 1);
  }

  public void addAll(IntLinkedBag addend) {
    if (addend == null) {
      throw new IllegalArgumentException("addend is null.");
    }

    IntLinkedBag sourceBag = (addend == this) ? addend.clone() : addend;

    for (CountNode currentSourceNode = sourceBag.headNode;
        currentSourceNode != null;
        currentSourceNode = currentSourceNode.nextNode) {
      addOccurrences(currentSourceNode.dataValue, currentSourceNode.occurrenceCount);
    }
  }

  public void addAll(int[] elements) {
    for (int elementValue : elements) {
      add(elementValue);
    }
  }

  @Override
  public IntLinkedBag clone() {
    try {
      IntLinkedBag copiedBag = (IntLinkedBag) super.clone();
      copiedBag.headNode = copyNodeChain(headNode);
      return copiedBag;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError("Clone should be supported", cloneNotSupportedException);
    }
  }

  public int countOccurrences(int targetValue) {
    for (CountNode currentNode = headNode;
        currentNode != null;
        currentNode = currentNode.nextNode) {
      if (currentNode.dataValue == targetValue) {
        return currentNode.occurrenceCount;
      }

      if (currentNode.dataValue > targetValue) {
        return 0;
      }
    }

    return 0;
  }

  public boolean remove(int targetValue) {
    CountNode previousNode = null;
    CountNode currentNode = headNode;

    while (currentNode != null && currentNode.dataValue < targetValue) {
      previousNode = currentNode;
      currentNode = currentNode.nextNode;
    }

    if (currentNode == null || currentNode.dataValue != targetValue) {
      return false;
    }

    currentNode.occurrenceCount--;
    totalElementCount--;

    if (currentNode.occurrenceCount == 0) {
      if (previousNode == null) {
        headNode = currentNode.nextNode;
      } else {
        previousNode.nextNode = currentNode.nextNode;
      }
    }

    return true;
  }

  public int size() {
    return totalElementCount;
  }

  public static IntLinkedBag union(IntLinkedBag b1, IntLinkedBag b2) {
    if (b1 == null) {
      throw new IllegalArgumentException("b1 is null.");
    }

    if (b2 == null) {
      throw new IllegalArgumentException("b2 is null.");
    }

    IntLinkedBag answer = new IntLinkedBag();
    answer.addAll(b1);
    answer.addAll(b2);
    return answer;
  }

  private void addOccurrences(int elementValue, int occurrenceCountToAdd) {
    if (occurrenceCountToAdd <= 0) {
      return;
    }

    CountNode previousNode = null;
    CountNode currentNode = headNode;

    while (currentNode != null && currentNode.dataValue < elementValue) {
      previousNode = currentNode;
      currentNode = currentNode.nextNode;
    }

    if (currentNode != null && currentNode.dataValue == elementValue) {
      currentNode.occurrenceCount = safeAdd(currentNode.occurrenceCount, occurrenceCountToAdd);
      totalElementCount = safeAdd(totalElementCount, occurrenceCountToAdd);
      return;
    }

    CountNode newNode = new CountNode(occurrenceCountToAdd, elementValue, currentNode);

    if (previousNode == null) {
      headNode = newNode;
    } else {
      previousNode.nextNode = newNode;
    }

    totalElementCount = safeAdd(totalElementCount, occurrenceCountToAdd);
  }

  private static CountNode copyNodeChain(CountNode sourceHeadNode) {
    if (sourceHeadNode == null) {
      return null;
    }

    CountNode copiedHeadNode =
        new CountNode(sourceHeadNode.occurrenceCount, sourceHeadNode.dataValue, null);
    CountNode copiedTailNode = copiedHeadNode;

    for (CountNode currentSourceNode = sourceHeadNode.nextNode;
        currentSourceNode != null;
        currentSourceNode = currentSourceNode.nextNode) {
      copiedTailNode.nextNode =
          new CountNode(currentSourceNode.occurrenceCount, currentSourceNode.dataValue, null);
      copiedTailNode = copiedTailNode.nextNode;
    }

    return copiedHeadNode;
  }

  private static int safeAdd(int firstValue, int secondValue) {
    long sumValue = (long) firstValue + secondValue;

    if (sumValue > Integer.MAX_VALUE) {
      throw new OutOfMemoryError("Bag size overflow");
    }

    return (int) sumValue;
  }
}
