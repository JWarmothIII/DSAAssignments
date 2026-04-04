package dev.jameswarmothiii.assignment3.questions.chapter7.six;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LinkedQueueTest {

  @Test
  void constructorShouldCreateEmptyQueue() {
    LinkedQueue<Integer> queue = new LinkedQueue<>();

    assertTrue(queue.isEmpty());
    assertEquals(0, queue.size());
  }

  @Test
  void addAndRemoveShouldFollowFifoOrder() {
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.add(10);
    queue.add(20);
    queue.add(30);

    assertEquals(10, queue.remove());
    assertEquals(20, queue.remove());
    assertEquals(30, queue.remove());
    assertTrue(queue.isEmpty());
    assertEquals(0, queue.size());
  }

  @Test
  void removeShouldThrowWhenQueueIsEmpty() {
    LinkedQueue<Integer> queue = new LinkedQueue<>();

    assertThrows(LinkedQueueException.class, queue::remove);
  }

  @Test
  void sizeShouldTrackAddsAndRemoves() {
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.remove();

    assertEquals(2, queue.size());
    assertFalse(queue.isEmpty());
  }

  @Test
  void cloneShouldCreateIndependentCopy() {
    LinkedQueue<Integer> originalQueue = new LinkedQueue<>();
    originalQueue.add(1);
    originalQueue.add(2);
    originalQueue.add(3);

    LinkedQueue<Integer> copiedQueue = originalQueue.clone();

    assertNotSame(originalQueue, copiedQueue);
    assertEquals(3, copiedQueue.size());
    assertEquals(1, copiedQueue.remove());
    copiedQueue.add(4);

    assertEquals(3, originalQueue.size());
    assertEquals(1, originalQueue.remove());
    assertEquals(2, originalQueue.remove());
    assertEquals(3, originalQueue.remove());
    assertTrue(originalQueue.isEmpty());

    assertEquals(3, copiedQueue.size());
    assertEquals(2, copiedQueue.remove());
    assertEquals(3, copiedQueue.remove());
    assertEquals(4, copiedQueue.remove());
    assertTrue(copiedQueue.isEmpty());
  }
}
