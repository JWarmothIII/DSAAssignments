package dev.jameswarmothiii.assignment1.questions.chapter3.four;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.jameswarmothiii.assignment1.questions.chapter3.four.DoubleArraySeq;
import org.junit.jupiter.api.Test;

class DoubleArraySeqTest {

  @Test
  void defaultConstructorShouldCreateEmptySequenceWithCapacityTen() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    assertEquals(0, sequence.size());
    assertEquals(10, sequence.getCapacity());
    assertFalse(sequence.isCurrent());
  }

  @Test
  void constructorShouldThrowForNegativeCapacity() {
    assertThrows(IllegalArgumentException.class, () -> new DoubleArraySeq(-1));
  }

  @Test
  void constructorShouldUseRequestedCapacity() {
    DoubleArraySeq sequence = new DoubleArraySeq(25);

    assertEquals(0, sequence.size());
    assertEquals(25, sequence.getCapacity());
    assertFalse(sequence.isCurrent());
  }

  @Test
  void addAfterShouldAddToEndWhenNoCurrentElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    sequence.addAfter(4.5);

    assertEquals(1, sequence.size());
    assertTrue(sequence.isCurrent());
    assertEquals(4.5, sequence.getCurrent(), 0.000001);
  }

  @Test
  void addBeforeShouldAddToFrontWhenNoCurrentElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    sequence.addBefore(7.25);

    assertEquals(1, sequence.size());
    assertTrue(sequence.isCurrent());
    assertEquals(7.25, sequence.getCurrent(), 0.000001);
    assertEquals(7.25, sequence.getElementAt(0), 0.000001);
  }

  @Test
  void addAfterShouldInsertAfterCurrentElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(1.0);
    sequence.addAfter(3.0);

    sequence.start();
    sequence.addAfter(2.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void addBeforeShouldInsertBeforeCurrentElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(1.0);
    sequence.addAfter(3.0);

    sequence.setCurrentToLast();
    sequence.addBefore(2.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void addAfterShouldGrowCapacityWhenFull() {
    DoubleArraySeq sequence = new DoubleArraySeq(1);

    sequence.addAfter(1.0);
    sequence.addAfter(2.0);

    assertEquals(2, sequence.size());
    assertTrue(sequence.getCapacity() >= 2);
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
  }

  @Test
  void startShouldSetCurrentToFront() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(10.0);
    sequence.addAfter(20.0);
    sequence.addAfter(30.0);

    sequence.setCurrentToLast();
    sequence.start();

    assertTrue(sequence.isCurrent());
    assertEquals(10.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void startShouldLeaveNoCurrentWhenSequenceIsEmpty() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    sequence.start();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void advanceShouldMoveToNextElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(5.0);
    sequence.addAfter(6.0);
    sequence.addAfter(7.0);

    sequence.start();
    sequence.advance();

    assertTrue(sequence.isCurrent());
    assertEquals(6.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void advanceShouldClearCurrentWhenAdvancingPastLastElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(5.0);
    sequence.addAfter(6.0);

    sequence.setCurrentToLast();
    sequence.advance();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void advanceShouldThrowWhenNoCurrentElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    assertThrows(IllegalStateException.class, sequence::advance);
  }

  @Test
  void getCurrentShouldThrowWhenNoCurrentElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    assertThrows(IllegalStateException.class, sequence::getCurrent);
  }

  @Test
  void removeCurrentShouldRemoveMiddleElementAndKeepNextCurrent() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(1.0);
    sequence.addAfter(2.0);
    sequence.addAfter(3.0);

    sequence.start();
    sequence.advance();
    sequence.removeCurrent();

    assertEquals(2, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(3.0, sequence.getElementAt(1), 0.000001);
    assertTrue(sequence.isCurrent());
    assertEquals(3.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void removeCurrentShouldClearCurrentWhenRemovingLastElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(1.0);
    sequence.addAfter(2.0);

    sequence.setCurrentToLast();
    sequence.removeCurrent();

    assertEquals(1, sequence.size());
    assertFalse(sequence.isCurrent());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
  }

  @Test
  void removeCurrentShouldThrowWhenNoCurrentElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    assertThrows(IllegalStateException.class, sequence::removeCurrent);
  }

  @Test
  void addAllShouldAppendContentsAndPreserveCurrentElement() {
    DoubleArraySeq firstSequence = new DoubleArraySeq();
    firstSequence.addAfter(1.0);
    firstSequence.addAfter(2.0);

    DoubleArraySeq secondSequence = new DoubleArraySeq();
    secondSequence.addAfter(3.0);
    secondSequence.addAfter(4.0);

    firstSequence.start();

    firstSequence.addAll(secondSequence);

    assertEquals(4, firstSequence.size());
    assertEquals(1.0, firstSequence.getCurrent(), 0.000001);
    assertEquals(1.0, firstSequence.getElementAt(0), 0.000001);
    assertEquals(2.0, firstSequence.getElementAt(1), 0.000001);
    assertEquals(3.0, firstSequence.getElementAt(2), 0.000001);
    assertEquals(4.0, firstSequence.getElementAt(3), 0.000001);

    assertEquals(2, secondSequence.size());
    assertEquals(3.0, secondSequence.getElementAt(0), 0.000001);
    assertEquals(4.0, secondSequence.getElementAt(1), 0.000001);
  }

  @Test
  void addAllShouldThrowWhenAddendIsNull() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    assertThrows(NullPointerException.class, () -> sequence.addAll(null));
  }

  @Test
  void concatenationShouldCreateCombinedSequenceWithNoCurrentElement() {
    DoubleArraySeq firstSequence = new DoubleArraySeq();
    firstSequence.addAfter(1.0);
    firstSequence.addAfter(2.0);

    DoubleArraySeq secondSequence = new DoubleArraySeq();
    secondSequence.addAfter(3.0);
    secondSequence.addAfter(4.0);

    DoubleArraySeq combinedSequence = DoubleArraySeq.concatenation(firstSequence, secondSequence);

    assertEquals(4, combinedSequence.size());
    assertFalse(combinedSequence.isCurrent());
    assertEquals(1.0, combinedSequence.getElementAt(0), 0.000001);
    assertEquals(2.0, combinedSequence.getElementAt(1), 0.000001);
    assertEquals(3.0, combinedSequence.getElementAt(2), 0.000001);
    assertEquals(4.0, combinedSequence.getElementAt(3), 0.000001);
  }

  @Test
  void concatenationShouldThrowWhenEitherSequenceIsNull() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    assertThrows(NullPointerException.class, () -> DoubleArraySeq.concatenation(sequence, null));
    assertThrows(NullPointerException.class, () -> DoubleArraySeq.concatenation(null, sequence));
  }

  @Test
  void ensureCapacityShouldIncreaseCapacityWhenNeeded() {
    DoubleArraySeq sequence = new DoubleArraySeq(2);

    sequence.ensureCapacity(8);

    assertTrue(sequence.getCapacity() >= 8);
  }

  @Test
  void ensureCapacityShouldNotShrinkCapacity() {
    DoubleArraySeq sequence = new DoubleArraySeq(10);

    sequence.ensureCapacity(5);

    assertEquals(10, sequence.getCapacity());
  }

  @Test
  void ensureCapacityShouldThrowForNegativeMinimumCapacity() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    assertThrows(IllegalArgumentException.class, () -> sequence.ensureCapacity(-1));
  }

  @Test
  void trimToSizeShouldReduceCapacityToSize() {
    DoubleArraySeq sequence = new DoubleArraySeq(20);
    sequence.addAfter(1.0);
    sequence.addAfter(2.0);
    sequence.addAfter(3.0);

    sequence.trimToSize();

    assertEquals(3, sequence.size());
    assertEquals(3, sequence.getCapacity());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
  }

  @Test
  void cloneShouldCreateIndependentCopy() throws CloneNotSupportedException {
    DoubleArraySeq originalSequence = new DoubleArraySeq();
    originalSequence.addAfter(1.0);
    originalSequence.addAfter(2.0);
    originalSequence.start();

    DoubleArraySeq clonedSequence = originalSequence.clone();

    assertNotSame(originalSequence, clonedSequence);
    assertEquals(originalSequence.size(), clonedSequence.size());
    assertEquals(1.0, clonedSequence.getElementAt(0), 0.000001);
    assertEquals(2.0, clonedSequence.getElementAt(1), 0.000001);
    assertTrue(clonedSequence.isCurrent());
    assertEquals(1.0, clonedSequence.getCurrent(), 0.000001);

    clonedSequence.addEnd(99.0);

    assertEquals(2, originalSequence.size());
    assertEquals(3, clonedSequence.size());
  }

  @Test
  void addFrontShouldInsertAtFront() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(2.0);
    sequence.addAfter(3.0);

    sequence.addFront(1.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(1.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void removeFrontShouldRemoveFirstElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(1.0);
    sequence.addAfter(2.0);
    sequence.addAfter(3.0);

    sequence.removeFront();

    assertEquals(2, sequence.size());
    assertEquals(2.0, sequence.getElementAt(0), 0.000001);
    assertEquals(3.0, sequence.getElementAt(1), 0.000001);
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void removeFrontShouldThrowWhenEmpty() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    assertThrows(IllegalStateException.class, sequence::removeFront);
  }

  @Test
  void addEndShouldAppendElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(1.0);
    sequence.addAfter(2.0);

    sequence.addEnd(3.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(3.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToLastShouldSetCurrentToLastElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(8.0);
    sequence.addAfter(9.0);

    sequence.setCurrentToLast();

    assertTrue(sequence.isCurrent());
    assertEquals(9.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToLastShouldClearCurrentWhenEmpty() {
    DoubleArraySeq sequence = new DoubleArraySeq();

    sequence.setCurrentToLast();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void getElementAtShouldReturnElementAtIndex() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(10.0);
    sequence.addAfter(20.0);
    sequence.addAfter(30.0);

    assertEquals(10.0, sequence.getElementAt(0), 0.000001);
    assertEquals(20.0, sequence.getElementAt(1), 0.000001);
    assertEquals(30.0, sequence.getElementAt(2), 0.000001);
  }

  @Test
  void getElementAtShouldThrowForInvalidIndex() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(1.0);

    assertThrows(IndexOutOfBoundsException.class, () -> sequence.getElementAt(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> sequence.getElementAt(1));
  }

  @Test
  void setCurrentToIndexShouldSetCurrentElement() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(10.0);
    sequence.addAfter(20.0);
    sequence.addAfter(30.0);

    sequence.setCurrentToIndex(1);

    assertTrue(sequence.isCurrent());
    assertEquals(20.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToIndexShouldThrowForInvalidIndex() {
    DoubleArraySeq sequence = new DoubleArraySeq();
    sequence.addAfter(1.0);

    assertThrows(IndexOutOfBoundsException.class, () -> sequence.setCurrentToIndex(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> sequence.setCurrentToIndex(2));
  }
}
