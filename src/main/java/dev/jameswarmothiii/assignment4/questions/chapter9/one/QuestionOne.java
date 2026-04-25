package dev.jameswarmothiii.assignment4.questions.chapter9.one;

public final class QuestionOne {
  private Node root;

  public QuestionOne() {}

  private QuestionOne(Node rootNode) {
    root = rootNode;
  }

  public static QuestionOne leaf(double value) {
    return new QuestionOne(new Node(value));
  }

  public static QuestionOne add(QuestionOne leftSubtree, QuestionOne rightSubtree) {
    return combine('+', leftSubtree, rightSubtree);
  }

  public static QuestionOne multiply(QuestionOne leftSubtree, QuestionOne rightSubtree) {
    return combine('*', leftSubtree, rightSubtree);
  }

  public static QuestionOne combine(
      char operator, QuestionOne leftSubtree, QuestionOne rightSubtree) {
    return new QuestionOne(
        new Node(operator, readSubtree("Left", leftSubtree), readSubtree("Right", rightSubtree)));
  }

  public void makeLeaf(double value) {
    root = new Node(value);
  }

  public void makeOperator(char operator, QuestionOne leftSubtree, QuestionOne rightSubtree) {
    root = new Node(operator, readSubtree("Left", leftSubtree), readSubtree("Right", rightSubtree));
  }

  public boolean isEmpty() {
    return root == null;
  }

  public void clear() {
    root = null;
  }

  public double evaluate() {
    if (isEmpty()) {
      throw QuestionOneException.treeIsEmpty();
    }

    return evaluate(root);
  }

  private static Node readSubtree(String subtreeName, QuestionOne subtree) {
    if (subtree == null) {
      throw QuestionOneException.subtreeIsNull(subtreeName);
    }

    if (subtree.isEmpty()) {
      throw QuestionOneException.subtreeIsEmpty(subtreeName);
    }

    return subtree.root;
  }

  private static double evaluate(Node node) {
    if (node.isLeaf()) {
      return node.getNumberValue();
    }

    double leftValue = evaluate(node.getLeftChild());
    double rightValue = evaluate(node.getRightChild());

    if (node.getOperator() == '+') {
      return leftValue + rightValue;
    }

    return leftValue * rightValue;
  }

  private static final class Node {
    private final Double numberValue;
    private final Character operator;
    private final Node leftChild;
    private final Node rightChild;

    private Node(double numberValue) {
      this.numberValue = numberValue;
      this.operator = null;
      this.leftChild = null;
      this.rightChild = null;
    }

    private Node(char operatorValue, Node leftChildNode, Node rightChildNode) {
      if (operatorValue != '+' && operatorValue != '*') {
        throw QuestionOneException.operatorIsInvalid(operatorValue);
      }

      this.numberValue = null;
      this.operator = operatorValue;
      this.leftChild = leftChildNode;
      this.rightChild = rightChildNode;
    }

    private boolean isLeaf() {
      return operator == null;
    }

    private double getNumberValue() {
      return numberValue;
    }

    private char getOperator() {
      return operator;
    }

    private Node getLeftChild() {
      return leftChild;
    }

    private Node getRightChild() {
      return rightChild;
    }
  }
}
