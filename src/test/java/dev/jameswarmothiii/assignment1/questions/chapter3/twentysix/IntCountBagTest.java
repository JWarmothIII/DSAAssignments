package dev.jameswarmothiii.assignment1.questions.chapter3.twentysix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.jameswarmothiii.assignment1.questions.chapter3.twentysix.IntCountBag;
import org.junit.jupiter.api.Test;

class IntCountBagTest {

  @Test
  void defaultConstructorShouldCreateEmptyBag() {
    IntCountBag bag = new IntCountBag();

    assertEquals(0, bag.size());
    assertEquals(0, bag.countOccurrences(0));
    assertEquals(0, bag.countOccurrences(10));
  }

  @Test
  void constructorShouldThrowForNegativeInitialMaximumValue() {
    assertThrows(IllegalArgumentException.class, () -> new IntCountBag(-1));
  }

  @Test
  void addShouldIncreaseSizeAndOccurrences() {
    IntCountBag bag = new IntCountBag();

    bag.add(3);
    bag.add(3);
    bag.add(1);

    assertEquals(3, bag.size());
    assertEquals(2, bag.countOccurrences(3));
    assertEquals(1, bag.countOccurrences(1));
    assertEquals(0, bag.countOccurrences(2));
  }

  @Test
  void addShouldGrowWhenValueExceedsCurrentMaximum() {
    IntCountBag bag = new IntCountBag(2);

    bag.add(8);
    bag.add(8);
    bag.add(3);

    assertEquals(3, bag.size());
    assertEquals(2, bag.countOccurrences(8));
    assertEquals(1, bag.countOccurrences(3));
  }

  @Test
  void addShouldThrowForNegativeValue() {
    IntCountBag bag = new IntCountBag();

    assertThrows(IllegalArgumentException.class, () -> bag.add(-5));
  }

  @Test
  void countOccurrencesShouldThrowForNegativeValue() {
    IntCountBag bag = new IntCountBag();

    assertThrows(IllegalArgumentException.class, () -> bag.countOccurrences(-1));
  }

  @Test
  void removeShouldReturnTrueAndDecreaseCountsWhenPresent() {
    IntCountBag bag = new IntCountBag();
    bag.add(4);
    bag.add(4);
    bag.add(2);

    boolean removed = bag.remove(4);

    assertTrue(removed);
    assertEquals(2, bag.size());
    assertEquals(1, bag.countOccurrences(4));
    assertEquals(1, bag.countOccurrences(2));
  }

  @Test
  void removeShouldReturnFalseWhenValueNotPresent() {
    IntCountBag bag = new IntCountBag();
    bag.add(2);

    assertFalse(bag.remove(5));
    assertFalse(bag.remove(2 + 100));

    assertEquals(1, bag.size());
    assertEquals(1, bag.countOccurrences(2));
  }

  @Test
  void removeShouldReturnFalseWhenOccurrenceCountIsZero() {
    IntCountBag bag = new IntCountBag();
    bag.add(7);
    bag.remove(7);

    boolean removedAgain = bag.remove(7);

    assertFalse(removedAgain);
    assertEquals(0, bag.size());
    assertEquals(0, bag.countOccurrences(7));
  }

  @Test
  void removeShouldThrowForNegativeValue() {
    IntCountBag bag = new IntCountBag();

    assertThrows(IllegalArgumentException.class, () -> bag.remove(-2));
  }

  @Test
  void setAnticipatedMaximumValueShouldAllowFutureAdds() {
    IntCountBag bag = new IntCountBag(1);

    bag.setAnticipatedMaximumValue(20);
    bag.add(20);
    bag.add(15);

    assertEquals(2, bag.size());
    assertEquals(1, bag.countOccurrences(20));
    assertEquals(1, bag.countOccurrences(15));
  }

  @Test
  void setAnticipatedMaximumValueShouldThrowForNegativeValue() {
    IntCountBag bag = new IntCountBag();

    assertThrows(IllegalArgumentException.class, () -> bag.setAnticipatedMaximumValue(-10));
  }

  @Test
  void addAllShouldMergeCountsAndSizes() {
    IntCountBag firstBag = new IntCountBag();
    firstBag.add(1);
    firstBag.add(3);

    IntCountBag secondBag = new IntCountBag();
    secondBag.add(3);
    secondBag.add(3);
    secondBag.add(7);

    firstBag.addAll(secondBag);

    assertEquals(5, firstBag.size());
    assertEquals(1, firstBag.countOccurrences(1));
    assertEquals(3, firstBag.countOccurrences(3));
    assertEquals(1, firstBag.countOccurrences(7));

    assertEquals(3, secondBag.size());
    assertEquals(2, secondBag.countOccurrences(3));
    assertEquals(1, secondBag.countOccurrences(7));
  }

  @Test
  void addAllShouldThrowForNullAddend() {
    IntCountBag bag = new IntCountBag();

    assertThrows(NullPointerException.class, () -> bag.addAll(null));
  }

  @Test
  void cloneShouldCreateIndependentCopy() {
    IntCountBag originalBag = new IntCountBag();
    originalBag.add(2);
    originalBag.add(2);
    originalBag.add(9);

    IntCountBag copiedBag = originalBag.clone();

    assertEquals(3, copiedBag.size());
    assertEquals(2, copiedBag.countOccurrences(2));
    assertEquals(1, copiedBag.countOccurrences(9));

    copiedBag.add(2);
    copiedBag.remove(9);

    assertEquals(3, originalBag.size());
    assertEquals(2, originalBag.countOccurrences(2));
    assertEquals(1, originalBag.countOccurrences(9));

    assertEquals(3, copiedBag.countOccurrences(2));
    assertEquals(0, copiedBag.countOccurrences(9));
  }
}
