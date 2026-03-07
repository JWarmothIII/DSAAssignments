package dev.jameswarmothiii.assignment1.questions.chapter3.seven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.jameswarmothiii.assignment1.questions.chapter3.seven.DoubleArraySortedSeq;
import org.junit.jupiter.api.Test;

class DoubleArraySortedSeqTest {

  @Test
  void defaultConstructorShouldCreateEmptySequenceWithCapacityTen() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    assertEquals(0, sequence.size());
    assertEquals(10, sequence.getCapacity());
    assertFalse(sequence.isCurrent());
  }

  @Test
  void constructorShouldThrowForNegativeCapacity() {
    assertThrows(IllegalArgumentException.class, () -> new DoubleArraySortedSeq(-1));
  }

  @Test
  void constructorShouldUseRequestedCapacity() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq(12);

    assertEquals(0, sequence.size());
    assertEquals(12, sequence.getCapacity());
    assertFalse(sequence.isCurrent());
  }

  @Test
  void addShouldInsertIntoEmptySequence() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    sequence.add(5.0);

    assertEquals(1, sequence.size());
    assertTrue(sequence.isCurrent());
    assertEquals(5.0, sequence.getCurrent(), 0.000001);
    assertEquals(5.0, sequence.getElementAt(0), 0.000001);
  }

  @Test
  void addShouldKeepElementsSortedWhenAddedOutOfOrder() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    sequence.add(5.0);
    sequence.add(1.0);
    sequence.add(3.0);
    sequence.add(2.0);
    sequence.add(4.0);

    assertEquals(5, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(4.0, sequence.getElementAt(3), 0.000001);
    assertEquals(5.0, sequence.getElementAt(4), 0.000001);
  }

  @Test
  void addShouldAllowDuplicateValuesAndKeepSortedOrder() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    sequence.add(2.0);
    sequence.add(2.0);
    sequence.add(1.0);
    sequence.add(2.0);

    assertEquals(4, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(2.0, sequence.getElementAt(2), 0.000001);
    assertEquals(2.0, sequence.getElementAt(3), 0.000001);
  }

  @Test
  void addShouldGrowCapacityWhenFull() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq(1);

    sequence.add(10.0);
    sequence.add(5.0);

    assertEquals(2, sequence.size());
    assertTrue(sequence.getCapacity() >= 2);
    assertEquals(5.0, sequence.getElementAt(0), 0.000001);
    assertEquals(10.0, sequence.getElementAt(1), 0.000001);
  }

  @Test
  void startShouldSetCurrentToFront() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(3.0);
    sequence.add(1.0);
    sequence.add(2.0);

    sequence.setCurrentToLast();
    sequence.start();

    assertTrue(sequence.isCurrent());
    assertEquals(1.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void startShouldLeaveNoCurrentWhenSequenceIsEmpty() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    sequence.start();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void advanceShouldMoveToNextElement() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(1.0);
    sequence.add(2.0);
    sequence.add(3.0);

    sequence.start();
    sequence.advance();

    assertTrue(sequence.isCurrent());
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void advanceShouldClearCurrentAtEnd() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(1.0);
    sequence.add(2.0);

    sequence.setCurrentToLast();
    sequence.advance();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void advanceShouldThrowWhenNoCurrent() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    assertThrows(IllegalStateException.class, sequence::advance);
  }

  @Test
  void getCurrentShouldThrowWhenNoCurrent() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    assertThrows(IllegalStateException.class, sequence::getCurrent);
  }

  @Test
  void removeCurrentShouldRemoveMiddleElement() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(3.0);
    sequence.add(1.0);
    sequence.add(2.0);

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
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(1.0);
    sequence.add(2.0);

    sequence.setCurrentToLast();
    sequence.removeCurrent();

    assertEquals(1, sequence.size());
    assertFalse(sequence.isCurrent());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
  }

  @Test
  void removeCurrentShouldThrowWhenNoCurrent() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    assertThrows(IllegalStateException.class, sequence::removeCurrent);
  }

  @Test
  void addAllShouldKeepSequenceSorted() {
    DoubleArraySortedSeq firstSequence = new DoubleArraySortedSeq();
    firstSequence.add(1.0);
    firstSequence.add(4.0);
    firstSequence.add(7.0);

    DoubleArraySortedSeq secondSequence = new DoubleArraySortedSeq();
    secondSequence.add(2.0);
    secondSequence.add(3.0);
    secondSequence.add(6.0);

    firstSequence.start();
    firstSequence.addAll(secondSequence);

    assertEquals(6, firstSequence.size());
    assertEquals(1.0, firstSequence.getElementAt(0), 0.000001);
    assertEquals(2.0, firstSequence.getElementAt(1), 0.000001);
    assertEquals(3.0, firstSequence.getElementAt(2), 0.000001);
    assertEquals(4.0, firstSequence.getElementAt(3), 0.000001);
    assertEquals(6.0, firstSequence.getElementAt(4), 0.000001);
    assertEquals(7.0, firstSequence.getElementAt(5), 0.000001);

    assertTrue(firstSequence.isCurrent());
  }

  @Test
  void addAllShouldThrowWhenAddendIsNull() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    assertThrows(NullPointerException.class, () -> sequence.addAll(null));
  }

  @Test
  void concatenationShouldReturnSortedCombinedSequenceWithNoCurrent() {
    DoubleArraySortedSeq firstSequence = new DoubleArraySortedSeq();
    firstSequence.add(1.0);
    firstSequence.add(3.0);
    firstSequence.add(5.0);

    DoubleArraySortedSeq secondSequence = new DoubleArraySortedSeq();
    secondSequence.add(2.0);
    secondSequence.add(4.0);
    secondSequence.add(6.0);

    DoubleArraySortedSeq combinedSequence =
        DoubleArraySortedSeq.concatenation(firstSequence, secondSequence);

    assertEquals(6, combinedSequence.size());
    assertFalse(combinedSequence.isCurrent());
    assertEquals(1.0, combinedSequence.getElementAt(0), 0.000001);
    assertEquals(2.0, combinedSequence.getElementAt(1), 0.000001);
    assertEquals(3.0, combinedSequence.getElementAt(2), 0.000001);
    assertEquals(4.0, combinedSequence.getElementAt(3), 0.000001);
    assertEquals(5.0, combinedSequence.getElementAt(4), 0.000001);
    assertEquals(6.0, combinedSequence.getElementAt(5), 0.000001);
  }

  @Test
  void concatenationShouldThrowWhenInputIsNull() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    assertThrows(
        NullPointerException.class, () -> DoubleArraySortedSeq.concatenation(sequence, null));
    assertThrows(
        NullPointerException.class, () -> DoubleArraySortedSeq.concatenation(null, sequence));
  }

  @Test
  void ensureCapacityShouldIncreaseCapacity() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq(2);

    sequence.ensureCapacity(8);

    assertTrue(sequence.getCapacity() >= 8);
  }

  @Test
  void ensureCapacityShouldNotShrinkCapacity() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq(10);

    sequence.ensureCapacity(5);

    assertEquals(10, sequence.getCapacity());
  }

  @Test
  void ensureCapacityShouldThrowForNegativeValue() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    assertThrows(IllegalArgumentException.class, () -> sequence.ensureCapacity(-1));
  }

  @Test
  void trimToSizeShouldReduceCapacityToSize() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq(20);
    sequence.add(3.0);
    sequence.add(1.0);
    sequence.add(2.0);

    sequence.trimToSize();

    assertEquals(3, sequence.size());
    assertEquals(3, sequence.getCapacity());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
  }

  @Test
  void cloneShouldCreateIndependentCopy() {
    DoubleArraySortedSeq originalSequence = new DoubleArraySortedSeq();
    originalSequence.add(3.0);
    originalSequence.add(1.0);
    originalSequence.add(2.0);
    originalSequence.start();

    DoubleArraySortedSeq clonedSequence = originalSequence.clone();

    assertNotSame(originalSequence, clonedSequence);
    assertEquals(originalSequence.size(), clonedSequence.size());
    assertEquals(1.0, clonedSequence.getElementAt(0), 0.000001);
    assertEquals(2.0, clonedSequence.getElementAt(1), 0.000001);
    assertEquals(3.0, clonedSequence.getElementAt(2), 0.000001);
    assertTrue(clonedSequence.isCurrent());
    assertEquals(1.0, clonedSequence.getCurrent(), 0.000001);

    clonedSequence.add(4.0);

    assertEquals(3, originalSequence.size());
    assertEquals(4, clonedSequence.size());
  }

  @Test
  void setCurrentToLastShouldSetCurrentToLastElement() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(2.0);
    sequence.add(1.0);

    sequence.setCurrentToLast();

    assertTrue(sequence.isCurrent());
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToLastShouldClearCurrentWhenEmpty() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();

    sequence.setCurrentToLast();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void getElementAtShouldReturnCorrectElement() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(30.0);
    sequence.add(10.0);
    sequence.add(20.0);

    assertEquals(10.0, sequence.getElementAt(0), 0.000001);
    assertEquals(20.0, sequence.getElementAt(1), 0.000001);
    assertEquals(30.0, sequence.getElementAt(2), 0.000001);
  }

  @Test
  void getElementAtShouldThrowForInvalidIndex() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(1.0);

    assertThrows(IndexOutOfBoundsException.class, () -> sequence.getElementAt(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> sequence.getElementAt(1));
  }

  @Test
  void setCurrentToIndexShouldSetCurrent() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(30.0);
    sequence.add(10.0);
    sequence.add(20.0);

    sequence.setCurrentToIndex(1);

    assertTrue(sequence.isCurrent());
    assertEquals(20.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToIndexShouldThrowForInvalidIndex() {
    DoubleArraySortedSeq sequence = new DoubleArraySortedSeq();
    sequence.add(1.0);

    assertThrows(IndexOutOfBoundsException.class, () -> sequence.setCurrentToIndex(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> sequence.setCurrentToIndex(2));
  }
}
