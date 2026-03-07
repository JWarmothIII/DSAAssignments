package dev.jameswarmothiii.assignment1.questions.chapter3.eight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.jameswarmothiii.assignment1.questions.chapter3.eight.Polynomial;
import org.junit.jupiter.api.Test;

class PolynomialTest {

  @Test
  void defaultConstructorShouldCreateZeroPolynomial() {
    Polynomial polynomial = new Polynomial();

    assertEquals(-1, polynomial.degree());
    assertEquals(0.0, polynomial.coefficient(0), 0.000001);
    assertEquals(0.0, polynomial.evaluate(2.0), 0.000001);
  }

  @Test
  void constantConstructorShouldSetConstantTerm() {
    Polynomial polynomial = new Polynomial(4.2);

    assertEquals(0, polynomial.degree());
    assertEquals(4.2, polynomial.coefficient(0), 0.000001);
    assertEquals(4.2, polynomial.evaluate(10.0), 0.000001);
  }

  @Test
  void copyConstructorShouldCopyPolynomial() {
    Polynomial originalPolynomial = new Polynomial();
    originalPolynomial.assignCoefficient(3.0, 2);
    originalPolynomial.assignCoefficient(-1.0, 0);

    Polynomial copiedPolynomial = new Polynomial(originalPolynomial);

    assertEquals(2, copiedPolynomial.degree());
    assertEquals(3.0, copiedPolynomial.coefficient(2), 0.000001);
    assertEquals(-1.0, copiedPolynomial.coefficient(0), 0.000001);
  }

  @Test
  void assignCoefficientAndAddToCoefficientShouldWork() {
    Polynomial polynomial = new Polynomial();

    polynomial.assignCoefficient(2.0, 3);
    polynomial.addToCoefficient(1.5, 3);

    assertEquals(3.5, polynomial.coefficient(3), 0.000001);
    assertEquals(3, polynomial.degree());
  }

  @Test
  void clearShouldResetPolynomial() {
    Polynomial polynomial = new Polynomial();
    polynomial.assignCoefficient(5.0, 4);

    polynomial.clear();

    assertEquals(-1, polynomial.degree());
    assertEquals(0.0, polynomial.coefficient(4), 0.000001);
  }

  @Test
  void nextTermShouldReturnNextNonzeroExponent() {
    Polynomial polynomial = new Polynomial();
    polynomial.assignCoefficient(1.0, 0);
    polynomial.assignCoefficient(3.0, 4);
    polynomial.assignCoefficient(2.0, 7);

    assertEquals(4, polynomial.nextTerm(0));
    assertEquals(7, polynomial.nextTerm(4));
    assertEquals(-1, polynomial.nextTerm(7));
  }

  @Test
  void evaluateShouldReturnCorrectValue() {
    Polynomial polynomial = new Polynomial();
    polynomial.assignCoefficient(1.0, 0);
    polynomial.assignCoefficient(-0.9, 1);
    polynomial.assignCoefficient(0.5, 2);
    polynomial.assignCoefficient(0.3, 3);

    assertEquals(3.6, polynomial.evaluate(2.0), 0.000001);
  }

  @Test
  void addShouldReturnCorrectSum() {
    Polynomial firstPolynomial = new Polynomial();
    firstPolynomial.assignCoefficient(2.0, 2);
    firstPolynomial.assignCoefficient(1.0, 0);

    Polynomial secondPolynomial = new Polynomial();
    secondPolynomial.assignCoefficient(3.0, 1);
    secondPolynomial.assignCoefficient(-1.0, 0);

    Polynomial sumPolynomial = Polynomial.add(firstPolynomial, secondPolynomial);

    assertEquals(2.0, sumPolynomial.coefficient(2), 0.000001);
    assertEquals(3.0, sumPolynomial.coefficient(1), 0.000001);
    assertEquals(0.0, sumPolynomial.coefficient(0), 0.000001);
  }

  @Test
  void subtractShouldReturnCorrectDifference() {
    Polynomial firstPolynomial = new Polynomial();
    firstPolynomial.assignCoefficient(5.0, 3);
    firstPolynomial.assignCoefficient(2.0, 0);

    Polynomial secondPolynomial = new Polynomial();
    secondPolynomial.assignCoefficient(1.5, 3);
    secondPolynomial.assignCoefficient(4.0, 1);

    Polynomial differencePolynomial = Polynomial.subtract(firstPolynomial, secondPolynomial);

    assertEquals(3.5, differencePolynomial.coefficient(3), 0.000001);
    assertEquals(-4.0, differencePolynomial.coefficient(1), 0.000001);
    assertEquals(2.0, differencePolynomial.coefficient(0), 0.000001);
  }

  @Test
  void multiplyShouldReturnCorrectProduct() {
    Polynomial firstPolynomial = new Polynomial();
    firstPolynomial.assignCoefficient(1.0, 1);
    firstPolynomial.assignCoefficient(1.0, 0);

    Polynomial secondPolynomial = new Polynomial();
    secondPolynomial.assignCoefficient(1.0, 1);
    secondPolynomial.assignCoefficient(-1.0, 0);

    Polynomial productPolynomial = Polynomial.multiply(firstPolynomial, secondPolynomial);

    assertEquals(1.0, productPolynomial.coefficient(2), 0.000001);
    assertEquals(0.0, productPolynomial.coefficient(1), 0.000001);
    assertEquals(-1.0, productPolynomial.coefficient(0), 0.000001);
  }

  @Test
  void methodsShouldThrowForInvalidInput() {
    Polynomial polynomial = new Polynomial();

    assertThrows(IllegalArgumentException.class, () -> polynomial.assignCoefficient(1.0, -1));
    assertThrows(IllegalArgumentException.class, () -> polynomial.addToCoefficient(1.0, -1));
    assertThrows(IllegalArgumentException.class, () -> polynomial.coefficient(-1));
    assertThrows(NullPointerException.class, () -> new Polynomial(null));
    assertThrows(NullPointerException.class, () -> Polynomial.add(polynomial, null));
  }
}
