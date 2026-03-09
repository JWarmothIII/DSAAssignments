package dev.jameswarmothiii.assignment2.questions.nine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.jameswarmothiii.assignment2.questions.nine.DoubleLinkedSeq;
import org.junit.jupiter.api.Test;

class DoubleLinkedSeqTest {

  @Test
  void constructorShouldCreateEmptySequence() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    assertEquals(0, sequence.size());
    assertFalse(sequence.isCurrent());
  }

  @Test
  void startShouldSetCurrentToFrontElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(10.0);
    sequence.addEnd(20.0);
    sequence.addEnd(30.0);

    sequence.setCurrentToLast();
    sequence.start();

    assertTrue(sequence.isCurrent());
    assertEquals(10.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void startShouldLeaveNoCurrentForEmptySequence() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    sequence.start();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void getCurrentShouldThrowWhenNoCurrentElementExists() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    assertThrows(DoubleLinkedSeqException.class, sequence::getCurrent);
  }

  @Test
  void advanceShouldMoveCurrentToNextElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);
    sequence.addEnd(2.0);
    sequence.addEnd(3.0);
    sequence.start();

    sequence.advance();

    assertTrue(sequence.isCurrent());
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void advanceShouldClearCurrentWhenCurrentWasLastElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);
    sequence.addEnd(2.0);
    sequence.setCurrentToLast();

    sequence.advance();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void advanceShouldThrowWhenNoCurrentElementExists() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    assertThrows(DoubleLinkedSeqException.class, sequence::advance);
  }

  @Test
  void addAfterShouldInsertAfterCurrentElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);
    sequence.addEnd(3.0);
    sequence.start();

    sequence.addAfter(2.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void addAfterShouldAppendWhenNoCurrentElementExists() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);
    sequence.addEnd(2.0);
    sequence.setCurrentToLast();
    sequence.advance();

    sequence.addAfter(3.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(3.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void addBeforeShouldInsertBeforeCurrentElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);
    sequence.addEnd(3.0);
    sequence.setCurrentToLast();

    sequence.addBefore(2.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void addBeforeShouldInsertAtFrontWhenNoCurrentElementExists() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(2.0);
    sequence.addEnd(3.0);
    sequence.setCurrentToLast();
    sequence.advance();

    sequence.addBefore(1.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(1.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void removeCurrentShouldRemoveMiddleElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);
    sequence.addEnd(2.0);
    sequence.addEnd(3.0);
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
  void removeCurrentShouldRemoveFirstElementAndMoveCurrentToNewFront() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(10.0);
    sequence.addEnd(20.0);
    sequence.addEnd(30.0);
    sequence.start();

    sequence.removeCurrent();

    assertEquals(2, sequence.size());
    assertEquals(20.0, sequence.getElementAt(0), 0.000001);
    assertEquals(30.0, sequence.getElementAt(1), 0.000001);
    assertTrue(sequence.isCurrent());
    assertEquals(20.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void removeCurrentShouldClearCurrentWhenRemovingLastElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(10.0);
    sequence.addEnd(20.0);
    sequence.setCurrentToLast();

    sequence.removeCurrent();

    assertEquals(1, sequence.size());
    assertEquals(10.0, sequence.getElementAt(0), 0.000001);
    assertFalse(sequence.isCurrent());
  }

  @Test
  void removeCurrentShouldThrowWhenNoCurrentElementExists() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    assertThrows(DoubleLinkedSeqException.class, sequence::removeCurrent);
  }

  @Test
  void addAllShouldAppendElementsAndKeepCurrentElementUnchanged() {
    DoubleLinkedSeq firstSequence = new DoubleLinkedSeq();
    firstSequence.addEnd(1.0);
    firstSequence.addEnd(2.0);
    firstSequence.start();

    DoubleLinkedSeq secondSequence = new DoubleLinkedSeq();
    secondSequence.addEnd(3.0);
    secondSequence.addEnd(4.0);

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
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    assertThrows(DoubleLinkedSeqException.class, () -> sequence.addAll(null));
  }

  @Test
  void concatenationShouldReturnCombinedSequenceWithNoCurrentElement() {
    DoubleLinkedSeq firstSequence = new DoubleLinkedSeq();
    firstSequence.addEnd(1.0);
    firstSequence.addEnd(2.0);

    DoubleLinkedSeq secondSequence = new DoubleLinkedSeq();
    secondSequence.addEnd(3.0);
    secondSequence.addEnd(4.0);

    DoubleLinkedSeq combinedSequence = DoubleLinkedSeq.concatenation(firstSequence, secondSequence);

    assertEquals(4, combinedSequence.size());
    assertFalse(combinedSequence.isCurrent());
    assertEquals(1.0, combinedSequence.getElementAt(0), 0.000001);
    assertEquals(2.0, combinedSequence.getElementAt(1), 0.000001);
    assertEquals(3.0, combinedSequence.getElementAt(2), 0.000001);
    assertEquals(4.0, combinedSequence.getElementAt(3), 0.000001);
  }

  @Test
  void concatenationShouldThrowWhenEitherArgumentIsNull() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    assertThrows(
        DoubleLinkedSeqException.class, () -> DoubleLinkedSeq.concatenation(sequence, null));
    assertThrows(
        DoubleLinkedSeqException.class, () -> DoubleLinkedSeq.concatenation(null, sequence));
  }

  @Test
  void cloneShouldCreateIndependentCopyWithSameCurrentPosition() {
    DoubleLinkedSeq originalSequence = new DoubleLinkedSeq();
    originalSequence.addEnd(10.0);
    originalSequence.addEnd(20.0);
    originalSequence.addEnd(30.0);
    originalSequence.setCurrentToIndex(1);

    DoubleLinkedSeq copiedSequence = originalSequence.clone();

    assertNotSame(originalSequence, copiedSequence);
    assertEquals(3, copiedSequence.size());
    assertEquals(10.0, copiedSequence.getElementAt(0), 0.000001);
    assertEquals(20.0, copiedSequence.getElementAt(1), 0.000001);
    assertEquals(30.0, copiedSequence.getElementAt(2), 0.000001);
    assertTrue(copiedSequence.isCurrent());
    assertEquals(20.0, copiedSequence.getCurrent(), 0.000001);

    copiedSequence.removeCurrent();

    assertEquals(3, originalSequence.size());
    assertEquals(2, copiedSequence.size());
    assertEquals(20.0, originalSequence.getCurrent(), 0.000001);
  }

  @Test
  void addFrontShouldInsertAtFrontAndSetCurrent() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(2.0);
    sequence.addEnd(3.0);

    sequence.addFront(1.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(1.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void removeFrontShouldRemoveFrontElementAndUpdateCurrentWhenNeeded() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);
    sequence.addEnd(2.0);
    sequence.addEnd(3.0);
    sequence.start();

    sequence.removeFront();

    assertEquals(2, sequence.size());
    assertEquals(2.0, sequence.getElementAt(0), 0.000001);
    assertEquals(3.0, sequence.getElementAt(1), 0.000001);
    assertTrue(sequence.isCurrent());
    assertEquals(2.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void removeFrontShouldThrowWhenSequenceIsEmpty() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    assertThrows(DoubleLinkedSeqException.class, sequence::removeFront);
  }

  @Test
  void addEndShouldAppendElementAndSetItCurrent() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);
    sequence.addEnd(2.0);

    sequence.addEnd(3.0);

    assertEquals(3, sequence.size());
    assertEquals(1.0, sequence.getElementAt(0), 0.000001);
    assertEquals(2.0, sequence.getElementAt(1), 0.000001);
    assertEquals(3.0, sequence.getElementAt(2), 0.000001);
    assertEquals(3.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToLastShouldSetCurrentToLastElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(5.0);
    sequence.addEnd(6.0);

    sequence.setCurrentToLast();

    assertTrue(sequence.isCurrent());
    assertEquals(6.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToLastShouldClearCurrentWhenSequenceIsEmpty() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();

    sequence.setCurrentToLast();

    assertFalse(sequence.isCurrent());
  }

  @Test
  void getElementAtShouldReturnCorrectElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(11.0);
    sequence.addEnd(22.0);
    sequence.addEnd(33.0);

    assertEquals(11.0, sequence.getElementAt(0), 0.000001);
    assertEquals(22.0, sequence.getElementAt(1), 0.000001);
    assertEquals(33.0, sequence.getElementAt(2), 0.000001);
  }

  @Test
  void getElementAtShouldThrowForInvalidIndex() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(11.0);

    assertThrows(DoubleLinkedSeqException.class, () -> sequence.getElementAt(-1));
    assertThrows(DoubleLinkedSeqException.class, () -> sequence.getElementAt(1));
  }

  @Test
  void setCurrentToIndexShouldSetCurrentElement() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(100.0);
    sequence.addEnd(200.0);
    sequence.addEnd(300.0);

    sequence.setCurrentToIndex(1);

    assertTrue(sequence.isCurrent());
    assertEquals(200.0, sequence.getCurrent(), 0.000001);
  }

  @Test
  void setCurrentToIndexShouldThrowForInvalidIndex() {
    DoubleLinkedSeq sequence = new DoubleLinkedSeq();
    sequence.addEnd(1.0);

    assertThrows(DoubleLinkedSeqException.class, () -> sequence.setCurrentToIndex(-1));
    assertThrows(DoubleLinkedSeqException.class, () -> sequence.setCurrentToIndex(1));
  }
}
