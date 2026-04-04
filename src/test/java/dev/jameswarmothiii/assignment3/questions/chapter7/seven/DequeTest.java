package dev.jameswarmothiii.assignment3.questions.chapter7.seven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DequeTest {

  @Test
  void constructorShouldCreateEmptyDeque() {
    Deque<Integer> deque = new Deque<>();

    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

  @Test
  void addFrontAndAddRearShouldAllowRemovingFromBothEnds() {
    Deque<Integer> deque = new Deque<>();
    deque.addFront(2);
    deque.addFront(1);
    deque.addRear(3);
    deque.addRear(4);

    assertEquals(1, deque.removeFront());
    assertEquals(4, deque.removeRear());
    assertEquals(2, deque.removeFront());
    assertEquals(3, deque.removeRear());
    assertTrue(deque.isEmpty());
  }

  @Test
  void removeFrontShouldThrowWhenDequeIsEmpty() {
    Deque<Integer> deque = new Deque<>();

    assertThrows(DequeException.class, deque::removeFront);
  }

  @Test
  void removeRearShouldThrowWhenDequeIsEmpty() {
    Deque<Integer> deque = new Deque<>();

    assertThrows(DequeException.class, deque::removeRear);
  }

  @Test
  void sizeShouldTrackAddsAndRemoves() {
    Deque<Integer> deque = new Deque<>();
    deque.addFront(2);
    deque.addRear(3);
    deque.addFront(1);

    assertEquals(3, deque.size());
    assertEquals(1, deque.removeFront());
    assertEquals(2, deque.removeFront());
    assertEquals(1, deque.size());
    assertEquals(3, deque.removeRear());
    assertEquals(0, deque.size());
  }

  @Test
  void singleItemDequeShouldWorkFromEitherEnd() {
    Deque<String> deque = new Deque<>();
    deque.addRear("a");

    assertEquals("a", deque.removeFront());
    assertTrue(deque.isEmpty());

    deque.addFront("b");
    assertEquals("b", deque.removeRear());
    assertTrue(deque.isEmpty());
  }
}
