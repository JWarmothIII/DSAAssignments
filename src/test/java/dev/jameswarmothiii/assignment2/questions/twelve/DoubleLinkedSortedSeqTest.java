package dev.jameswarmothiii.assignment2.questions.twelve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.jameswarmothiii.assignment2.questions.twelve.DoubleLinkedSortedSeq;
import org.junit.jupiter.api.Test;

class DoubleLinkedSortedSeqTest {

  @Test
  void defaultConstructorShouldCreateEmptySequence() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

    assertEquals(0, sequence.size());
    assertFalse(sequence.isCurrent());
  }

  @Test
  void addShouldInsertIntoEmptySequence() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

    sequence.add(5.0);

    assertEquals(1, sequence.size());
    assertTrue(sequence.isCurrent());
    assertEquals(5.0, sequence.getCurrent(), 0.000001);
    assertEquals(5.0, sequence.getElementAt(0), 0.000001);
  }

  @Test
  void addShouldKeepElementsSortedWhenAddedOutOfOrder() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

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
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

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
  void startShouldSetCurrentToFront() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();
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
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

    sequence.start();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void advanceShouldMoveToNextElement() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();
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
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();
    sequence.add(1.0);
    sequence.add(2.0);

    sequence.setCurrentToLast();
    sequence.advance();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void advanceShouldThrowWhenNoCurrent() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

    assertThrows(DoubleLinkedSortedSeqException.class, sequence::advance);
  }

  @Test
  void getCurrentShouldThrowWhenNoCurrent() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

    assertThrows(DoubleLinkedSortedSeqException.class, sequence::getCurrent);
  }

  @Test
  void removeCurrentShouldRemoveMiddleElement() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();
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
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();
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
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

    assertThrows(DoubleLinkedSortedSeqException.class, sequence::removeCurrent);
  }

  @Test
  void addAllShouldKeepSequenceSorted() {
    DoubleLinkedSortedSeq firstSequence = new DoubleLinkedSortedSeq();
    firstSequence.add(1.0);
    firstSequence.add(4.0);
    firstSequence.add(7.0);

    DoubleLinkedSortedSeq secondSequence = new DoubleLinkedSortedSeq();
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
  }

  @Test
  void addAllShouldThrowWhenAddendIsNull() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

    assertThrows(DoubleLinkedSortedSeqException.class, () -> sequence.addAll(null));
  }

  @Test
  void concatenationShouldReturnSortedCombinedSequenceWithNoCurrent() {
    DoubleLinkedSortedSeq firstSequence = new DoubleLinkedSortedSeq();
    firstSequence.add(1.0);
    firstSequence.add(3.0);
    firstSequence.add(5.0);

    DoubleLinkedSortedSeq secondSequence = new DoubleLinkedSortedSeq();
    secondSequence.add(2.0);
    secondSequence.add(4.0);
    secondSequence.add(6.0);

    DoubleLinkedSortedSeq combinedSequence =
        DoubleLinkedSortedSeq.concatenation(firstSequence, secondSequence);

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
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();

    assertThrows(
        DoubleLinkedSortedSeqException.class,
        () -> DoubleLinkedSortedSeq.concatenation(sequence, null));
    assertThrows(
        DoubleLinkedSortedSeqException.class,
        () -> DoubleLinkedSortedSeq.concatenation(null, sequence));
  }

  @Test
  void cloneShouldCreateIndependentCopy() {
    DoubleLinkedSortedSeq originalSequence = new DoubleLinkedSortedSeq();
    originalSequence.add(3.0);
    originalSequence.add(1.0);
    originalSequence.add(2.0);
    originalSequence.start();

    DoubleLinkedSortedSeq clonedSequence = originalSequence.clone();

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
  void getElementAtShouldThrowForInvalidIndex() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();
    sequence.add(1.0);

    assertThrows(DoubleLinkedSortedSeqException.class, () -> sequence.getElementAt(-1));
    assertThrows(DoubleLinkedSortedSeqException.class, () -> sequence.getElementAt(1));
  }

  @Test
  void setCurrentToIndexShouldSetCurrent() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();
    sequence.add(30.0);
    sequence.add(10.0);
    sequence.add(20.0);

    sequence.setCurrentToIndex(1);

    assertTrue(sequence.isCurrent());
    assertEquals(20.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToIndexShouldThrowForInvalidIndex() {
    DoubleLinkedSortedSeq sequence = new DoubleLinkedSortedSeq();
    sequence.add(1.0);

    assertThrows(DoubleLinkedSortedSeqException.class, () -> sequence.setCurrentToIndex(-1));
    assertThrows(DoubleLinkedSortedSeqException.class, () -> sequence.setCurrentToIndex(2));
  }
}
