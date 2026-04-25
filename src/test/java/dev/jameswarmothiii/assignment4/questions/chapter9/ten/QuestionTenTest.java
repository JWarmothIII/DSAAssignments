package dev.jameswarmothiii.assignment4.questions.chapter9.ten;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class QuestionTenTest {

  @Test
  void constructorShouldCreateEmptyBag() {
    QuestionTen stringBag = new QuestionTen();

    assertTrue(stringBag.isEmpty());
    assertEquals(0, stringBag.size());
  }

  @Test
  void addShouldStoreStringsByCompareToOrder() {
    QuestionTen stringBag = new QuestionTen();
    stringBag.add("delta");
    stringBag.add("bravo");
    stringBag.add("echo");
    stringBag.add("alpha");
    stringBag.add("charlie");

    BinaryTreeNode<String> rootNode = stringBag.getRootNode();

    assertEquals("delta", rootNode.getData());
    assertEquals("bravo", rootNode.getLeft().getData());
    assertEquals("echo", rootNode.getRight().getData());
    assertEquals("alpha", rootNode.getLeft().getLeft().getData());
    assertEquals("charlie", rootNode.getLeft().getRight().getData());
  }

  @Test
  void addShouldAllowDuplicateValues() {
    QuestionTen stringBag = new QuestionTen();
    stringBag.add("apple");
    stringBag.add("apple");
    stringBag.add("apple");

    assertEquals(3, stringBag.size());
    assertEquals(3, stringBag.countOccurrences("apple"));
  }

  @Test
  void addAllShouldAddEveryElementInArray() {
    QuestionTen stringBag = new QuestionTen();
    stringBag.addAll(new String[] {"pear", "banana", "pear", "grape"});

    assertEquals(4, stringBag.size());
    assertEquals(2, stringBag.countOccurrences("pear"));
    assertEquals(1, stringBag.countOccurrences("banana"));
    assertEquals(1, stringBag.countOccurrences("grape"));
  }

  @Test
  void countOccurrencesShouldReturnZeroWhenValueIsMissing() {
    QuestionTen stringBag = new QuestionTen();
    stringBag.add("oak");
    stringBag.add("elm");

    assertEquals(0, stringBag.countOccurrences("maple"));
    assertFalse(stringBag.contains("maple"));
    assertTrue(stringBag.contains("oak"));
  }

  @Test
  void removeShouldDeleteSingleOccurrenceWhenDuplicatesExist() {
    QuestionTen stringBag = new QuestionTen();
    stringBag.add("kiwi");
    stringBag.add("kiwi");
    stringBag.add("kiwi");

    boolean wasRemoved = stringBag.remove("kiwi");

    assertTrue(wasRemoved);
    assertEquals(2, stringBag.size());
    assertEquals(2, stringBag.countOccurrences("kiwi"));
  }

  @Test
  void removeShouldReturnFalseWhenTargetIsMissing() {
    QuestionTen stringBag = new QuestionTen();
    stringBag.add("sun");
    stringBag.add("moon");

    boolean wasRemoved = stringBag.remove("star");

    assertFalse(wasRemoved);
    assertEquals(2, stringBag.size());
  }

  @Test
  void clearShouldRemoveAllElements() {
    QuestionTen stringBag = new QuestionTen();
    stringBag.addAll(new String[] {"one", "two", "three"});

    stringBag.clear();

    assertTrue(stringBag.isEmpty());
    assertEquals(0, stringBag.size());
    assertEquals(0, BinaryTreeNode.treeSize(stringBag.getRootNode()));
  }

  @Test
  void methodsShouldRejectNullValues() {
    QuestionTen stringBag = new QuestionTen();

    assertThrows(QuestionTenException.class, () -> stringBag.add(null));
    assertThrows(QuestionTenException.class, () -> stringBag.addAll(null));
    assertThrows(QuestionTenException.class, () -> stringBag.countOccurrences(null));
    assertThrows(QuestionTenException.class, () -> stringBag.remove(null));
  }
}
