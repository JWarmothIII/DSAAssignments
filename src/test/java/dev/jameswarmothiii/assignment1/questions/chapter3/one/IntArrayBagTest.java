package dev.jameswarmothiii.assignment1.questions.chapter3.one;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.jameswarmothiii.assignment1.questions.chapter3.one.IntArrayBag;
import org.junit.jupiter.api.Test;

class IntArrayBagTest {

    @Test
    void constructorWithNegativeCapacityShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new IntArrayBag(-1));
    }

    @Test
    void equalsShouldReturnFalseWhenOtherBagIsNull() {
        IntArrayBag firstBag = new IntArrayBag();

        assertFalse(firstBag.equals(null));
    }

    @Test
    void equalsShouldReturnTrueForTwoEmptyBags() {
        IntArrayBag firstBag = new IntArrayBag();
        IntArrayBag secondBag = new IntArrayBag();

        assertTrue(firstBag.equals(secondBag));
    }

    @Test
    void equalsShouldReturnTrueForSameElementsInSameOrder() {
        IntArrayBag firstBag = new IntArrayBag();
        firstBag.add(1);
        firstBag.add(2);
        firstBag.add(2);
        firstBag.add(3);

        IntArrayBag secondBag = new IntArrayBag();
        secondBag.add(1);
        secondBag.add(2);
        secondBag.add(2);
        secondBag.add(3);

        assertTrue(firstBag.equals(secondBag));
    }

    @Test
    void equalsShouldReturnTrueForSameElementsInDifferentOrder() {
        IntArrayBag firstBag = new IntArrayBag();
        firstBag.add(1);
        firstBag.add(2);
        firstBag.add(2);
        firstBag.add(3);

        IntArrayBag secondBag = new IntArrayBag();
        secondBag.add(2);
        secondBag.add(3);
        secondBag.add(1);
        secondBag.add(2);

        assertTrue(firstBag.equals(secondBag));
    }

    @Test
    void equalsShouldReturnFalseWhenSizesAreDifferent() {
        IntArrayBag firstBag = new IntArrayBag();
        firstBag.add(1);
        firstBag.add(2);

        IntArrayBag secondBag = new IntArrayBag();
        secondBag.add(1);
        secondBag.add(2);
        secondBag.add(2);

        assertFalse(firstBag.equals(secondBag));
    }

    @Test
    void equalsShouldReturnFalseWhenCountsOfSameElementDiffer() {
        IntArrayBag firstBag = new IntArrayBag();
        firstBag.add(4);
        firstBag.add(4);
        firstBag.add(5);

        IntArrayBag secondBag = new IntArrayBag();
        secondBag.add(4);
        secondBag.add(5);
        secondBag.add(5);

        assertFalse(firstBag.equals(secondBag));
    }

    @Test
    void equalsShouldReturnFalseWhenValuesDifferButSizesMatch() {
        IntArrayBag firstBag = new IntArrayBag();
        firstBag.add(1);
        firstBag.add(2);
        firstBag.add(3);

        IntArrayBag secondBag = new IntArrayBag();
        secondBag.add(1);
        secondBag.add(2);
        secondBag.add(4);

        assertFalse(firstBag.equals(secondBag));
    }

    @Test
    void equalsShouldHandleNegativeValuesCorrectly() {
        IntArrayBag firstBag = new IntArrayBag();
        firstBag.add(-1);
        firstBag.add(-1);
        firstBag.add(2);

        IntArrayBag secondBag = new IntArrayBag();
        secondBag.add(2);
        secondBag.add(-1);
        secondBag.add(-1);

        assertTrue(firstBag.equals(secondBag));
    }

    @Test
    void equalsShouldWorkAfterCapacityExpansion() {
        IntArrayBag firstBag = new IntArrayBag(2);
        IntArrayBag secondBag = new IntArrayBag(2);

        for (int value = 0; value < 15; value++) {
            firstBag.add(value % 3);
        }

        for (int value = 14; value >= 0; value--) {
            secondBag.add(value % 3);
        }

        assertTrue(firstBag.equals(secondBag));
    }
}