package dev.jameswarmothiii.assignment5.questions.chapter11.four;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import dev.jameswarmothiii.assignment5.questions.chapter11.four.Table;
import dev.jameswarmothiii.assignment5.questions.chapter11.four.TableException;
import org.junit.jupiter.api.Test;

class TableTest {

  @Test
  void constructorShouldRejectNonPositiveCapacity() {
    assertThrows(TableException.class, () -> new Table<>(0));
    assertThrows(TableException.class, () -> new Table<>(-3));
  }

  @Test
  void putAndGetShouldStoreAndReturnValues() {
    Table<String, Integer> table = new Table<>(7);

    assertNull(table.put("alpha", 11));
    assertNull(table.put("beta", 22));
    assertEquals(11, table.get("alpha"));
    assertEquals(22, table.get("beta"));
    assertEquals(2, table.size());
    assertTrue(table.containsKey("alpha"));
    assertFalse(table.containsKey("gamma"));
  }

  @Test
  void putShouldReturnPreviousValueWhenUpdatingExistingKey() {
    Table<String, Integer> table = new Table<>(7);

    table.put("alpha", 11);
    Integer previousValue = table.put("alpha", 99);

    assertEquals(11, previousValue);
    assertEquals(99, table.get("alpha"));
    assertEquals(1, table.size());
  }

  @Test
  void removeShouldDeleteKeyAndReturnRemovedValue() {
    Table<String, Integer> table = new Table<>(7);
    table.put("alpha", 11);
    table.put("beta", 22);

    Integer removedValue = table.remove("alpha");

    assertEquals(11, removedValue);
    assertNull(table.get("alpha"));
    assertFalse(table.containsKey("alpha"));
    assertEquals(1, table.size());
    assertNull(table.remove("missing"));
  }

  @Test
  void putShouldReuseRemovedSlotAndAllowContinuedSearch() {
    Table<FixedHashKey, String> table = new Table<>(7);
    FixedHashKey firstKey = new FixedHashKey("first", 0);
    FixedHashKey secondKey = new FixedHashKey("second", 14);
    FixedHashKey thirdKey = new FixedHashKey("third", 28);

    table.put(firstKey, "A");
    table.put(secondKey, "B");
    table.remove(firstKey);
    table.put(thirdKey, "C");

    assertEquals("B", table.get(secondKey));
    assertEquals("C", table.get(thirdKey));
    assertEquals(2, table.size());
  }

  @Test
  void putShouldUseDoubleHashingProbeStepForCollisions() throws ReflectiveOperationException {
    Table<FixedHashKey, String> table = new Table<>(7);
    FixedHashKey firstKey = new FixedHashKey("first", 0);
    FixedHashKey secondKey = new FixedHashKey("second", 14);

    table.put(firstKey, "A");
    table.put(secondKey, "B");

    Object[] storedKeys = readKeysArray(table);
    assertEquals(firstKey, storedKeys[0]);
    assertEquals(secondKey, storedKeys[3]);
  }

  @Test
  void keyBasedOperationsShouldRejectNullKey() {
    Table<String, Integer> table = new Table<>(7);

    assertThrows(TableException.class, () -> table.put(null, 10));
    assertThrows(TableException.class, () -> table.get(null));
    assertThrows(TableException.class, () -> table.containsKey(null));
    assertThrows(TableException.class, () -> table.remove(null));
  }

  @Test
  void putShouldThrowWhenTableIsFull() {
    Table<FixedHashKey, String> table = new Table<>(3);
    table.put(new FixedHashKey("first", 0), "A");
    table.put(new FixedHashKey("second", 1), "B");
    table.put(new FixedHashKey("third", 2), "C");

    assertThrows(
        TableException.class, () -> table.put(new FixedHashKey("fourth", 3), "D"));
  }

  private Object[] readKeysArray(Table<?, ?> tableInstance) throws ReflectiveOperationException {
    Field keysField = tableInstance.getClass().getDeclaredField("keys");
    keysField.setAccessible(true);
    return (Object[]) keysField.get(tableInstance);
  }

  private static final class FixedHashKey {
    private final String identifier;
    private final int hashCodeValue;

    private FixedHashKey(String identifierValue, int hashCodeValueValue) {
      identifier = identifierValue;
      hashCodeValue = hashCodeValueValue;
    }

    @Override
    public boolean equals(Object otherObject) {
      if (!(otherObject instanceof FixedHashKey otherKey)) {
        return false;
      }

      return identifier.equals(otherKey.identifier);
    }

    @Override
    public int hashCode() {
      return hashCodeValue;
    }
  }
}
