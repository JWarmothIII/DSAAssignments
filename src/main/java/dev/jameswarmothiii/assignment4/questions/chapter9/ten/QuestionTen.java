package dev.jameswarmothiii.assignment4.questions.chapter9.ten;

public class QuestionTen {
  private BinaryTreeNode<String> rootNode;
  private int totalElementCount;

  private static final class RemovalResult {
    private final BinaryTreeNode<String> updatedRootNode;
    private final boolean wasElementRemoved;

    private RemovalResult(
        BinaryTreeNode<String> initialUpdatedRootNode, boolean initialWasElementRemoved) {
      updatedRootNode = initialUpdatedRootNode;
      wasElementRemoved = initialWasElementRemoved;
    }
  }

  public QuestionTen() {
    rootNode = null;
    totalElementCount = 0;
  }

  public void add(String elementValue) {
    if (elementValue == null) {
      throw QuestionTenException.elementValueIsNull();
    }

    rootNode = addToTree(rootNode, elementValue);
    totalElementCount++;
  }

  public void addAll(String[] sourceValues) {
    if (sourceValues == null) {
      throw QuestionTenException.sourceArrayIsNull();
    }

    for (String sourceValue : sourceValues) {
      add(sourceValue);
    }
  }

  public int countOccurrences(String targetValue) {
    if (targetValue == null) {
      throw QuestionTenException.targetValueIsNull();
    }

    return countOccurrencesInTree(rootNode, targetValue);
  }

  public boolean contains(String targetValue) {
    return countOccurrences(targetValue) > 0;
  }

  public boolean remove(String targetValue) {
    if (targetValue == null) {
      throw QuestionTenException.targetValueIsNull();
    }

    RemovalResult removalResult = removeFromTree(rootNode, targetValue);
    rootNode = removalResult.updatedRootNode;

    if (removalResult.wasElementRemoved) {
      totalElementCount--;
      return true;
    }

    return false;
  }

  public int size() {
    return totalElementCount;
  }

  public boolean isEmpty() {
    return totalElementCount == 0;
  }

  public void clear() {
    rootNode = null;
    totalElementCount = 0;
  }

  public BinaryTreeNode<String> getRootNode() {
    return rootNode;
  }

  private static BinaryTreeNode<String> addToTree(
      BinaryTreeNode<String> currentRootNode, String elementValue) {
    if (currentRootNode == null) {
      return new BinaryTreeNode<>(elementValue, null, null);
    }

    int comparisonValue = elementValue.compareTo(currentRootNode.getData());

    if (comparisonValue < 0) {
      currentRootNode.setLeft(addToTree(currentRootNode.getLeft(), elementValue));
    } else {
      currentRootNode.setRight(addToTree(currentRootNode.getRight(), elementValue));
    }

    return currentRootNode;
  }

  private static int countOccurrencesInTree(
      BinaryTreeNode<String> currentRootNode, String targetValue) {
    if (currentRootNode == null) {
      return 0;
    }

    int comparisonValue = targetValue.compareTo(currentRootNode.getData());

    if (comparisonValue < 0) {
      return countOccurrencesInTree(currentRootNode.getLeft(), targetValue);
    }

    if (comparisonValue > 0) {
      return countOccurrencesInTree(currentRootNode.getRight(), targetValue);
    }

    return 1
        + countOccurrencesInTree(currentRootNode.getLeft(), targetValue)
        + countOccurrencesInTree(currentRootNode.getRight(), targetValue);
  }

  private static RemovalResult removeFromTree(
      BinaryTreeNode<String> currentRootNode, String targetValue) {
    if (currentRootNode == null) {
      return new RemovalResult(null, false);
    }

    int comparisonValue = targetValue.compareTo(currentRootNode.getData());

    if (comparisonValue < 0) {
      RemovalResult removalResult = removeFromTree(currentRootNode.getLeft(), targetValue);
      currentRootNode.setLeft(removalResult.updatedRootNode);
      return new RemovalResult(currentRootNode, removalResult.wasElementRemoved);
    }

    if (comparisonValue > 0) {
      RemovalResult removalResult = removeFromTree(currentRootNode.getRight(), targetValue);
      currentRootNode.setRight(removalResult.updatedRootNode);
      return new RemovalResult(currentRootNode, removalResult.wasElementRemoved);
    }

    if (currentRootNode.getLeft() == null) {
      return new RemovalResult(currentRootNode.getRight(), true);
    }

    if (currentRootNode.getRight() == null) {
      return new RemovalResult(currentRootNode.getLeft(), true);
    }

    String replacementValue = currentRootNode.getRight().getLeftmostData();
    currentRootNode.setData(replacementValue);
    currentRootNode.setRight(currentRootNode.getRight().removeLeftmost());
    return new RemovalResult(currentRootNode, true);
  }
}
