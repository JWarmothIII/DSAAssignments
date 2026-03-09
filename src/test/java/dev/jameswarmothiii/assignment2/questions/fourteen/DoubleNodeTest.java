package dev.jameswarmothiii.assignment2.questions.fourteen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.jameswarmothiii.assignment2.questions.fourteen.DoubleNode;
import org.junit.jupiter.api.Test;

class DoubleNodeTest {

  @Test
  void constructorShouldInitializeDataAndNullLinks() {
    DoubleNode node = new DoubleNode(5.25, null, null);

    assertEquals(5.25, node.getData(), 0.000001);
    assertNull(node.getPrevious());
    assertNull(node.getNext());
  }

  @Test
  void constructorShouldLinkNewNodeBetweenNeighbors() {
    DoubleNode previousNode = new DoubleNode(1.0, null, null);
    DoubleNode nextNode = new DoubleNode(3.0, null, null);

    DoubleNode middleNode = new DoubleNode(2.0, previousNode, nextNode);

    assertSame(previousNode, middleNode.getPrevious());
    assertSame(nextNode, middleNode.getNext());
    assertSame(middleNode, previousNode.getNext());
    assertSame(middleNode, nextNode.getPrevious());
  }

  @Test
  void listLengthShouldReturnZeroForNullHead() {
    assertEquals(0, DoubleNode.listLength(null));
  }

  @Test
  void listLengthShouldReturnNodeCountForNonEmptyList() {
    DoubleNode headNode = new DoubleNode(10.0, null, null);
    headNode.addNodeAfter(20.0);
    headNode.getNext().addNodeAfter(30.0);

    assertEquals(3, DoubleNode.listLength(headNode));
  }

  @Test
  void listPositionShouldReturnNodeAtGivenPosition() {
    DoubleNode headNode = new DoubleNode(10.0, null, null);
    headNode.addNodeAfter(20.0);
    headNode.getNext().addNodeAfter(30.0);

    DoubleNode secondPositionNode = DoubleNode.listPosition(headNode, 2);
    DoubleNode thirdPositionNode = DoubleNode.listPosition(headNode, 3);

    assertNotNull(secondPositionNode);
    assertNotNull(thirdPositionNode);
    assertEquals(20.0, secondPositionNode.getData(), 0.000001);
    assertEquals(30.0, thirdPositionNode.getData(), 0.000001);
  }

  @Test
  void listPositionShouldReturnNullWhenPositionIsPastEnd() {
    DoubleNode headNode = new DoubleNode(10.0, null, null);
    headNode.addNodeAfter(20.0);

    DoubleNode resultNode = DoubleNode.listPosition(headNode, 3);

    assertNull(resultNode);
  }

  @Test
  void listPositionShouldThrowForNonPositivePosition() {
    DoubleNode headNode = new DoubleNode(10.0, null, null);

    assertThrows(IllegalArgumentException.class, () -> DoubleNode.listPosition(headNode, 0));
  }

  @Test
  void listSearchShouldReturnFirstMatchingNode() {
    DoubleNode headNode = new DoubleNode(1.0, null, null);
    headNode.addNodeAfter(2.0);
    DoubleNode firstMatchingNode = headNode.getNext();
    firstMatchingNode.addNodeAfter(2.0);

    DoubleNode foundNode = DoubleNode.listSearch(headNode, 2.0);

    assertSame(firstMatchingNode, foundNode);
  }

  @Test
  void listSearchShouldReturnNullWhenTargetMissing() {
    DoubleNode headNode = new DoubleNode(1.0, null, null);
    headNode.addNodeAfter(2.0);

    DoubleNode foundNode = DoubleNode.listSearch(headNode, 99.0);

    assertNull(foundNode);
  }

  @Test
  void addNodeAfterShouldInsertAndSetBidirectionalLinks() {
    DoubleNode headNode = new DoubleNode(1.0, null, null);
    headNode.addNodeAfter(2.0);

    DoubleNode insertedNode = headNode.getNext();

    assertNotNull(insertedNode);
    assertEquals(2.0, insertedNode.getData(), 0.000001);
    assertSame(headNode, insertedNode.getPrevious());
    assertNull(insertedNode.getNext());
  }

  @Test
  void addNodeBeforeShouldInsertAndSetBidirectionalLinks() {
    DoubleNode tailNode = new DoubleNode(3.0, null, null);
    tailNode.addNodeBefore(2.0);

    DoubleNode insertedNode = tailNode.getPrevious();

    assertNotNull(insertedNode);
    assertEquals(2.0, insertedNode.getData(), 0.000001);
    assertSame(tailNode, insertedNode.getNext());
    assertNull(insertedNode.getPrevious());
  }

  @Test
  void settersShouldUpdateStoredValues() {
    DoubleNode node = new DoubleNode(1.0, null, null);
    DoubleNode previousNode = new DoubleNode(0.0, null, null);
    DoubleNode nextNode = new DoubleNode(2.0, null, null);

    node.setData(9.5);
    node.setPrevious(previousNode);
    node.setNext(nextNode);

    assertEquals(9.5, node.getData(), 0.000001);
    assertSame(previousNode, node.getPrevious());
    assertSame(nextNode, node.getNext());
  }

  @Test
  void listCopyShouldReturnNullWhenSourceIsNull() {
    assertNull(DoubleNode.listCopy(null));
  }

  @Test
  void listCopyShouldCreateIndependentCopyWithSameValues() {
    DoubleNode sourceHeadNode = new DoubleNode(1.0, null, null);
    sourceHeadNode.addNodeAfter(2.0);
    sourceHeadNode.getNext().addNodeAfter(3.0);

    DoubleNode copiedHeadNode = DoubleNode.listCopy(sourceHeadNode);

    assertNotNull(copiedHeadNode);
    assertNotSame(sourceHeadNode, copiedHeadNode);
    assertEquals(3, DoubleNode.listLength(copiedHeadNode));
    assertEquals(1.0, copiedHeadNode.getData(), 0.000001);
    assertEquals(2.0, copiedHeadNode.getNext().getData(), 0.000001);
    assertEquals(3.0, copiedHeadNode.getNext().getNext().getData(), 0.000001);

    copiedHeadNode.getNext().setData(99.0);
    assertEquals(2.0, sourceHeadNode.getNext().getData(), 0.000001);
  }

  @Test
  void listCopyWithTailShouldReturnNullPairWhenSourceIsNull() {
    DoubleNode[] copiedHeadAndTailNodes = DoubleNode.listCopyWithTail(null);

    assertNotNull(copiedHeadAndTailNodes);
    assertEquals(2, copiedHeadAndTailNodes.length);
    assertNull(copiedHeadAndTailNodes[0]);
    assertNull(copiedHeadAndTailNodes[1]);
  }

  @Test
  void listCopyWithTailShouldReturnHeadAndTailOfCopiedList() {
    DoubleNode sourceHeadNode = new DoubleNode(4.0, null, null);
    sourceHeadNode.addNodeAfter(5.0);
    sourceHeadNode.getNext().addNodeAfter(6.0);

    DoubleNode[] copiedHeadAndTailNodes = DoubleNode.listCopyWithTail(sourceHeadNode);
    DoubleNode copiedHeadNode = copiedHeadAndTailNodes[0];
    DoubleNode copiedTailNode = copiedHeadAndTailNodes[1];

    assertNotNull(copiedHeadNode);
    assertNotNull(copiedTailNode);
    assertEquals(4.0, copiedHeadNode.getData(), 0.000001);
    assertEquals(6.0, copiedTailNode.getData(), 0.000001);
    assertSame(copiedTailNode, copiedHeadNode.getNext().getNext());
    assertNull(copiedHeadNode.getPrevious());
    assertNull(copiedTailNode.getNext());
  }

  @Test
  void removeNodeShouldDetachMiddleNodeAndLinkNeighborsTogether() {
    DoubleNode headNode = new DoubleNode(1.0, null, null);
    headNode.addNodeAfter(2.0);
    headNode.getNext().addNodeAfter(3.0);

    DoubleNode removedNode = headNode.getNext();
    DoubleNode tailNode = removedNode.getNext();

    removedNode.removeNode();

    assertSame(tailNode, headNode.getNext());
    assertSame(headNode, tailNode.getPrevious());
    assertNull(removedNode.getPrevious());
    assertNull(removedNode.getNext());
    assertEquals(2, DoubleNode.listLength(headNode));
  }

  @Test
  void removeNodeShouldHandleHeadNodeWithoutPreviousNeighbor() {
    DoubleNode headNode = new DoubleNode(1.0, null, null);
    headNode.addNodeAfter(2.0);
    DoubleNode newHeadNode = headNode.getNext();

    headNode.removeNode();

    assertNull(headNode.getPrevious());
    assertNull(headNode.getNext());
    assertNull(newHeadNode.getPrevious());
  }

  @Test
  void removeNodeShouldHandleTailNodeWithoutNextNeighbor() {
    DoubleNode headNode = new DoubleNode(1.0, null, null);
    headNode.addNodeAfter(2.0);
    DoubleNode tailNode = headNode.getNext();

    tailNode.removeNode();

    assertNull(tailNode.getPrevious());
    assertNull(tailNode.getNext());
    assertNull(headNode.getNext());
  }

  @Test
  void removeNodeAfterShouldRemoveCurrentNodeThatCallsMethod() {
    DoubleNode headNode = new DoubleNode(1.0, null, null);
    headNode.addNodeAfter(2.0);
    headNode.getNext().addNodeAfter(3.0);

    DoubleNode nodeCallingMethod = headNode.getNext();
    DoubleNode tailNode = nodeCallingMethod.getNext();

    nodeCallingMethod.removeNodeAfter();

    assertSame(tailNode, headNode.getNext());
    assertSame(headNode, tailNode.getPrevious());
    assertNull(nodeCallingMethod.getPrevious());
    assertNull(nodeCallingMethod.getNext());
    assertEquals(2, DoubleNode.listLength(headNode));
  }
}
