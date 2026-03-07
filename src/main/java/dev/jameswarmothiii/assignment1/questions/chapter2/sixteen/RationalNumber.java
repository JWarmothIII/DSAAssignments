package dev.jameswarmothiii.assignment1.questions.chapter2.sixteen;

public class RationalNumber {
  private int numerator;
  private int denominator;

  public RationalNumber() {
    this.numerator = 0;
    this.denominator = 1;
  }

  public RationalNumber(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be zero.");
    }
    this.numerator = numerator;
    this.denominator = denominator;
    normalize();
  }

  public static RationalNumber add(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    int resultNumerator =
        firstRationalNumber.numerator * secondRationalNumber.denominator
            + secondRationalNumber.numerator * firstRationalNumber.denominator;

    int resultDenominator = firstRationalNumber.denominator * secondRationalNumber.denominator;

    return new RationalNumber(resultNumerator, resultDenominator);
  }

  public static RationalNumber subtract(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    int resultNumerator =
        firstRationalNumber.numerator * secondRationalNumber.denominator
            - secondRationalNumber.numerator * firstRationalNumber.denominator;

    int resultDenominator = firstRationalNumber.denominator * secondRationalNumber.denominator;

    return new RationalNumber(resultNumerator, resultDenominator);
  }

  public static RationalNumber multiply(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    int resultNumerator = firstRationalNumber.numerator * secondRationalNumber.numerator;
    int resultDenominator = firstRationalNumber.denominator * secondRationalNumber.denominator;

    return new RationalNumber(resultNumerator, resultDenominator);
  }

  public static RationalNumber divide(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    if (secondRationalNumber.numerator == 0) {
      throw new ArithmeticException("Cannot divide by zero.");
    }

    int resultNumerator = firstRationalNumber.numerator * secondRationalNumber.denominator;
    int resultDenominator = firstRationalNumber.denominator * secondRationalNumber.numerator;

    return new RationalNumber(resultNumerator, resultDenominator);
  }

  public static boolean equals(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    return firstRationalNumber.numerator * secondRationalNumber.denominator
        == secondRationalNumber.numerator * firstRationalNumber.denominator;
  }

  public static boolean lessThan(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    return firstRationalNumber.numerator * secondRationalNumber.denominator
        < secondRationalNumber.numerator * firstRationalNumber.denominator;
  }

  public static boolean lessThanOrEqual(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    return firstRationalNumber.numerator * secondRationalNumber.denominator
        <= secondRationalNumber.numerator * firstRationalNumber.denominator;
  }

  public static boolean greaterThan(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    return firstRationalNumber.numerator * secondRationalNumber.denominator
        > secondRationalNumber.numerator * firstRationalNumber.denominator;
  }

  public static boolean greaterThanOrEqual(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    return firstRationalNumber.numerator * secondRationalNumber.denominator
        >= secondRationalNumber.numerator * firstRationalNumber.denominator;
  }

  public static boolean notEquals(
      RationalNumber firstRationalNumber, RationalNumber secondRationalNumber) {
    return !equals(firstRationalNumber, secondRationalNumber);
  }

  private static int greatestCommonDivisor(int firstValue, int secondValue) {
    while (secondValue != 0) {
      int temporaryValue = firstValue % secondValue;
      firstValue = secondValue;
      secondValue = temporaryValue;
    }
    return firstValue;
  }

  public static void main(String[] args) {
    RationalNumber firstRationalNumber = new RationalNumber(5, 6);
    RationalNumber secondRationalNumber = new RationalNumber(4, -8);

    System.out.print("firstRationalNumber = ");
    firstRationalNumber.print();

    System.out.print("secondRationalNumber = ");
    secondRationalNumber.print();

    System.out.println("add: " + RationalNumber.add(firstRationalNumber, secondRationalNumber));
    System.out.println(
        "subtract: " + RationalNumber.subtract(firstRationalNumber, secondRationalNumber));
    System.out.println(
        "multiply: " + RationalNumber.multiply(firstRationalNumber, secondRationalNumber));
    System.out.println(
        "divide: " + RationalNumber.divide(firstRationalNumber, secondRationalNumber));

    System.out.println(
        "firstRationalNumber == secondRationalNumber? "
            + RationalNumber.equals(firstRationalNumber, secondRationalNumber));
    System.out.println(
        "firstRationalNumber < secondRationalNumber? "
            + RationalNumber.lessThan(firstRationalNumber, secondRationalNumber));
    System.out.println(
        "firstRationalNumber > secondRationalNumber? "
            + RationalNumber.greaterThan(firstRationalNumber, secondRationalNumber));
  }

  public int getNumerator() {
    return numerator;
  }

  public void setNumerator(int numerator) {
    this.numerator = numerator;
    normalize();
  }

  public int getDenominator() {
    return denominator;
  }

  public void setDenominator(int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be zero.");
    }
    this.denominator = denominator;
    normalize();
  }

  public void print() {
    System.out.println(this);
  }

  public void normalize() {
    if (denominator == 0) {
      throw new IllegalStateException("Denominator cannot be zero.");
    }

    if (denominator < 0) {
      numerator = -numerator;
      denominator = -denominator;
    }

    if (numerator == 0) {
      denominator = 1;
      return;
    }

    int greatestCommonDivisorValue =
        greatestCommonDivisor(Math.abs(numerator), Math.abs(denominator));

    numerator /= greatestCommonDivisorValue;
    denominator /= greatestCommonDivisorValue;
  }

  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }
}
