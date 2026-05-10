package dev.jameswarmothiii.assignment4.questions.chapter9.ten;

public class BinaryTreeNode<ElementType> {
  private ElementType data;
  private BinaryTreeNode<ElementType> left;
  private BinaryTreeNode<ElementType> right;

  public BinaryTreeNode(
      ElementType initialData,
      BinaryTreeNode<ElementType> initialLeft,
      BinaryTreeNode<ElementType> initialRight) {
    data = initialData;
    left = initialLeft;
    right = initialRight;
  }

  public ElementType getData() {
    return data;
  }

  public BinaryTreeNode<ElementType> getLeft() {
    return left;
  }

  public ElementType getLeftmostData() {
    if (left == null) {
      return data;
    } else {
      return left.getLeftmostData();
    }
  }

  public BinaryTreeNode<ElementType> getRight() {
    return right;
  }

  public ElementType getRightmostData() {
    if (right == null) {
      return data;
    } else {
      return right.getRightmostData();
    }
  }

  public void inorderPrint() {
    if (left != null) {
      left.inorderPrint();
    }

    System.out.println(data);

    if (right != null) {
      right.inorderPrint();
    }
  }

  public boolean isLeaf() {
    return (left == null) && (right == null);
  }

  public void postorderPrint() {
    if (left != null) {
      left.postorderPrint();
    }

    if (right != null) {
      right.postorderPrint();
    }

    System.out.println(data);
  }

  public void preorderPrint() {
    System.out.println(data);

    if (left != null) {
      left.preorderPrint();
    }

    if (right != null) {
      right.preorderPrint();
    }
  }

  public void print(int depth) {
    int indentationDepthIndex;

    for (indentationDepthIndex = 1; indentationDepthIndex <= depth; indentationDepthIndex++) {
      System.out.print("    ");
    }
    System.out.println(data);

    if (left != null) {
      left.print(depth + 1);
    } else if (right != null) {
      for (indentationDepthIndex = 1;
          indentationDepthIndex <= depth + 1;
          indentationDepthIndex++) {
        System.out.print("    ");
      }
      System.out.println("--");
    }

    if (right != null) {
      right.print(depth + 1);
    } else if (left != null) {
      for (indentationDepthIndex = 1;
          indentationDepthIndex <= depth + 1;
          indentationDepthIndex++) {
        System.out.print("    ");
      }
      System.out.println("--");
    }
  }

  public BinaryTreeNode<ElementType> removeLeftmost() {
    if (left == null) {
      return right;
    } else {
      left = left.removeLeftmost();
      return this;
    }
  }

  public BinaryTreeNode<ElementType> removeRightmost() {
    if (right == null) {
      return left;
    } else {
      right = right.removeRightmost();
      return this;
    }
  }

  public void setData(ElementType newData) {
    data = newData;
  }

  public void setLeft(BinaryTreeNode<ElementType> newLeft) {
    left = newLeft;
  }

  public void setRight(BinaryTreeNode<ElementType> newRight) {
    right = newRight;
  }

  public static <ElementType> BinaryTreeNode<ElementType> treeCopy(
      BinaryTreeNode<ElementType> sourceNode) {
    BinaryTreeNode<ElementType> leftCopy;
    BinaryTreeNode<ElementType> rightCopy;

    if (sourceNode == null) {
      return null;
    } else {
      leftCopy = treeCopy(sourceNode.left);
      rightCopy = treeCopy(sourceNode.right);
      return new BinaryTreeNode<>(sourceNode.data, leftCopy, rightCopy);
    }
  }

  public static <ElementType> int treeSize(BinaryTreeNode<ElementType> rootNode) {
    if (rootNode == null) {
      return 0;
    } else {
      return 1 + treeSize(rootNode.left) + treeSize(rootNode.right);
    }
  }
}
