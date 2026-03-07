package dev.jameswarmothiii.assignment1.questions.chapter2.sixteen;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.jameswarmothiii.assignment1.questions.chapter2.sixteen.RationalNumber;
import org.junit.jupiter.api.Test;

class RationalNumberTest {

  @Test
  void noArgumentsConstructorShouldCreateZeroOverOne() {
    RationalNumber rationalNumber = new RationalNumber();

    assertEquals(0, rationalNumber.getNumerator());
    assertEquals(1, rationalNumber.getDenominator());
  }

  @Test
  void constructorShouldNormalizeFraction() {
    RationalNumber rationalNumber = new RationalNumber(4, -8);

    assertEquals(-1, rationalNumber.getNumerator());
    assertEquals(2, rationalNumber.getDenominator());
  }

  @Test
  void constructorShouldRejectZeroDenominator() {
    assertThrows(IllegalArgumentException.class, () -> new RationalNumber(1, 0));
  }

  @Test
  void setNumeratorShouldNormalizeFraction() {
    RationalNumber rationalNumber = new RationalNumber(2, 4);
    rationalNumber.setNumerator(6);

    assertEquals(3, rationalNumber.getNumerator());
    assertEquals(1, rationalNumber.getDenominator());
  }

  @Test
  void setDenominatorShouldNormalizeFractionAndKeepDenominatorPositive() {
    RationalNumber rationalNumber = new RationalNumber(3, 6);
    rationalNumber.setDenominator(-4);

    assertEquals(-1, rationalNumber.getNumerator());
    assertEquals(4, rationalNumber.getDenominator());
  }

  @Test
  void setDenominatorShouldRejectZero() {
    RationalNumber rationalNumber = new RationalNumber(1, 2);

    assertThrows(IllegalArgumentException.class, () -> rationalNumber.setDenominator(0));
  }

  @Test
  void normalizeShouldConvertZeroNumeratorToZeroOverOne() {
    RationalNumber rationalNumber = new RationalNumber(0, -7);

    assertEquals(0, rationalNumber.getNumerator());
    assertEquals(1, rationalNumber.getDenominator());
  }

  @Test
  void addShouldReturnCorrectReducedResult() {
    RationalNumber firstRationalNumber = new RationalNumber(1, 2);
    RationalNumber secondRationalNumber = new RationalNumber(1, 3);

    RationalNumber resultRationalNumber =
        RationalNumber.add(firstRationalNumber, secondRationalNumber);

    assertEquals(5, resultRationalNumber.getNumerator());
    assertEquals(6, resultRationalNumber.getDenominator());
  }

  @Test
  void subtractShouldReturnCorrectReducedResult() {
    RationalNumber firstRationalNumber = new RationalNumber(3, 4);
    RationalNumber secondRationalNumber = new RationalNumber(1, 2);

    RationalNumber resultRationalNumber =
        RationalNumber.subtract(firstRationalNumber, secondRationalNumber);

    assertEquals(1, resultRationalNumber.getNumerator());
    assertEquals(4, resultRationalNumber.getDenominator());
  }

  @Test
  void multiplyShouldReturnCorrectReducedResult() {
    RationalNumber firstRationalNumber = new RationalNumber(2, 3);
    RationalNumber secondRationalNumber = new RationalNumber(3, 4);

    RationalNumber resultRationalNumber =
        RationalNumber.multiply(firstRationalNumber, secondRationalNumber);

    assertEquals(1, resultRationalNumber.getNumerator());
    assertEquals(2, resultRationalNumber.getDenominator());
  }

  @Test
  void divideShouldReturnCorrectReducedResult() {
    RationalNumber firstRationalNumber = new RationalNumber(2, 3);
    RationalNumber secondRationalNumber = new RationalNumber(4, 5);

    RationalNumber resultRationalNumber =
        RationalNumber.divide(firstRationalNumber, secondRationalNumber);

    assertEquals(5, resultRationalNumber.getNumerator());
    assertEquals(6, resultRationalNumber.getDenominator());
  }

  @Test
  void divideShouldThrowWhenDividingByZeroRationalNumber() {
    RationalNumber firstRationalNumber = new RationalNumber(1, 2);
    RationalNumber zeroRationalNumber = new RationalNumber(0, 5);

    assertThrows(
        ArithmeticException.class,
        () -> RationalNumber.divide(firstRationalNumber, zeroRationalNumber));
  }

  @Test
  void equalsShouldReturnTrueForEquivalentFractions() {
    RationalNumber firstRationalNumber = new RationalNumber(1, 2);
    RationalNumber secondRationalNumber = new RationalNumber(2, 4);

    assertTrue(RationalNumber.equals(firstRationalNumber, secondRationalNumber));
  }

  @Test
  void notEqualsShouldReturnTrueForDifferentFractions() {
    RationalNumber firstRationalNumber = new RationalNumber(1, 2);
    RationalNumber secondRationalNumber = new RationalNumber(3, 4);

    assertTrue(RationalNumber.notEquals(firstRationalNumber, secondRationalNumber));
    assertFalse(RationalNumber.equals(firstRationalNumber, secondRationalNumber));
  }

  @Test
  void lessThanShouldCompareCorrectly() {
    RationalNumber firstRationalNumber = new RationalNumber(1, 3);
    RationalNumber secondRationalNumber = new RationalNumber(1, 2);

    assertTrue(RationalNumber.lessThan(firstRationalNumber, secondRationalNumber));
    assertFalse(RationalNumber.lessThan(secondRationalNumber, firstRationalNumber));
  }

  @Test
  void lessThanOrEqualShouldCompareCorrectly() {
    RationalNumber firstRationalNumber = new RationalNumber(2, 4);
    RationalNumber secondRationalNumber = new RationalNumber(1, 2);

    assertTrue(RationalNumber.lessThanOrEqual(firstRationalNumber, secondRationalNumber));
    assertTrue(RationalNumber.lessThanOrEqual(secondRationalNumber, firstRationalNumber));
  }

  @Test
  void greaterThanShouldCompareCorrectly() {
    RationalNumber firstRationalNumber = new RationalNumber(3, 4);
    RationalNumber secondRationalNumber = new RationalNumber(2, 3);

    assertTrue(RationalNumber.greaterThan(firstRationalNumber, secondRationalNumber));
    assertFalse(RationalNumber.greaterThan(secondRationalNumber, firstRationalNumber));
  }

  @Test
  void greaterThanOrEqualShouldCompareCorrectly() {
    RationalNumber firstRationalNumber = new RationalNumber(-2, 4);
    RationalNumber secondRationalNumber = new RationalNumber(1, -2);

    assertTrue(RationalNumber.greaterThanOrEqual(firstRationalNumber, secondRationalNumber));
    assertTrue(RationalNumber.greaterThanOrEqual(secondRationalNumber, firstRationalNumber));
  }

  @Test
  void toStringShouldReturnNormalizedForm() {
    RationalNumber rationalNumber = new RationalNumber(10, -20);

    assertEquals("-1/2", rationalNumber.toString());
  }
}
