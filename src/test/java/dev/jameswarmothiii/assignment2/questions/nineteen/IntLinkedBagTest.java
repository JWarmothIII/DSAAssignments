package dev.jameswarmothiii.assignment2.questions.nineteen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.jameswarmothiii.assignment2.questions.nineteen.IntLinkedBag;
import org.junit.jupiter.api.Test;

class IntLinkedBagTest {

  @Test
  void constructorShouldCreateEmptyBag() {
    IntLinkedBag bag = new IntLinkedBag();

    assertEquals(0, bag.size());
    assertEquals(0, bag.countOccurrences(10));
  }

  @Test
  void addShouldIncreaseSizeAndCountOccurrences() {
    IntLinkedBag bag = new IntLinkedBag();

    bag.add(10);
    bag.add(10);
    bag.add(5);

    assertEquals(3, bag.size());
    assertEquals(2, bag.countOccurrences(10));
    assertEquals(1, bag.countOccurrences(5));
    assertEquals(0, bag.countOccurrences(7));
  }

  @Test
  void addAllBagShouldMergeCountsFromAddend() {
    IntLinkedBag firstBag = new IntLinkedBag();
    firstBag.add(1);
    firstBag.add(3);
    firstBag.add(3);

    IntLinkedBag secondBag = new IntLinkedBag();
    secondBag.add(3);
    secondBag.add(4);

    firstBag.addAll(secondBag);

    assertEquals(5, firstBag.size());
    assertEquals(1, firstBag.countOccurrences(1));
    assertEquals(3, firstBag.countOccurrences(3));
    assertEquals(1, firstBag.countOccurrences(4));

    assertEquals(2, secondBag.size());
    assertEquals(1, secondBag.countOccurrences(3));
    assertEquals(1, secondBag.countOccurrences(4));
  }

  @Test
  void addAllBagShouldThrowWhenAddendIsNull() {
    IntLinkedBag bag = new IntLinkedBag();

    assertThrows(IntLinkedBagException.class, () -> bag.addAll((IntLinkedBag) null));
  }

  @Test
  void addAllArrayShouldAddEachElement() {
    IntLinkedBag bag = new IntLinkedBag();

    bag.addAll(new int[] {8, 2, 8, 1});

    assertEquals(4, bag.size());
    assertEquals(2, bag.countOccurrences(8));
    assertEquals(1, bag.countOccurrences(2));
    assertEquals(1, bag.countOccurrences(1));
  }

  @Test
  void addAllShouldHandleSelfAddByDoublingCounts() {
    IntLinkedBag bag = new IntLinkedBag();
    bag.add(2);
    bag.add(2);
    bag.add(5);

    bag.addAll(bag);

    assertEquals(6, bag.size());
    assertEquals(4, bag.countOccurrences(2));
    assertEquals(2, bag.countOccurrences(5));
  }

  @Test
  void removeShouldDecreaseCountWhenElementIsPresent() {
    IntLinkedBag bag = new IntLinkedBag();
    bag.add(10);
    bag.add(10);
    bag.add(10);

    boolean removed = bag.remove(10);

    assertTrue(removed);
    assertEquals(2, bag.size());
    assertEquals(2, bag.countOccurrences(10));
  }

  @Test
  void removeShouldEliminateElementWhenLastOccurrenceIsRemoved() {
    IntLinkedBag bag = new IntLinkedBag();
    bag.add(4);

    boolean removed = bag.remove(4);

    assertTrue(removed);
    assertEquals(0, bag.size());
    assertEquals(0, bag.countOccurrences(4));
    assertFalse(bag.remove(4));
  }

  @Test
  void removeShouldReturnFalseWhenElementIsMissing() {
    IntLinkedBag bag = new IntLinkedBag();
    bag.add(1);
    bag.add(2);

    boolean removed = bag.remove(9);

    assertFalse(removed);
    assertEquals(2, bag.size());
    assertEquals(1, bag.countOccurrences(1));
    assertEquals(1, bag.countOccurrences(2));
  }

  @Test
  void cloneShouldCreateIndependentCopy() {
    IntLinkedBag originalBag = new IntLinkedBag();
    originalBag.add(3);
    originalBag.add(3);
    originalBag.add(9);

    IntLinkedBag copiedBag = originalBag.clone();

    assertNotSame(originalBag, copiedBag);
    assertEquals(3, copiedBag.size());
    assertEquals(2, copiedBag.countOccurrences(3));
    assertEquals(1, copiedBag.countOccurrences(9));

    copiedBag.add(3);
    copiedBag.remove(9);

    assertEquals(3, originalBag.size());
    assertEquals(2, originalBag.countOccurrences(3));
    assertEquals(1, originalBag.countOccurrences(9));

    assertEquals(3, copiedBag.countOccurrences(3));
    assertEquals(0, copiedBag.countOccurrences(9));
  }

  @Test
  void unionShouldReturnCombinedBagWithoutChangingInputs() {
    IntLinkedBag firstBag = new IntLinkedBag();
    firstBag.add(1);
    firstBag.add(2);

    IntLinkedBag secondBag = new IntLinkedBag();
    secondBag.add(2);
    secondBag.add(3);

    IntLinkedBag unionBag = IntLinkedBag.union(firstBag, secondBag);

    assertEquals(4, unionBag.size());
    assertEquals(1, unionBag.countOccurrences(1));
    assertEquals(2, unionBag.countOccurrences(2));
    assertEquals(1, unionBag.countOccurrences(3));

    assertEquals(2, firstBag.size());
    assertEquals(2, secondBag.size());
  }

  @Test
  void unionShouldThrowWhenFirstBagIsNull() {
    IntLinkedBag bag = new IntLinkedBag();

    assertThrows(IntLinkedBagException.class, () -> IntLinkedBag.union(null, bag));
  }

  @Test
  void unionShouldThrowWhenSecondBagIsNull() {
    IntLinkedBag bag = new IntLinkedBag();

    assertThrows(IntLinkedBagException.class, () -> IntLinkedBag.union(bag, null));
  }
}
