package dev.jameswarmothiii.assignment1.questions.chapter2.eight.nine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.jameswarmothiii.assignment1.questions.chapter2.eight.nine.Quadratic;
import org.junit.jupiter.api.Test;

class QuadraticTest {

  @Test
  void constructorShouldInitializeCoefficientsToZero() {
    Quadratic quadratic = new Quadratic();

    assertEquals(0.0, quadratic.getCoefficientA(), 0.000001);
    assertEquals(0.0, quadratic.getCoefficientB(), 0.000001);
    assertEquals(0.0, quadratic.getCoefficientC(), 0.000001);
  }

  @Test
  void settersAndGettersShouldWork() {
    Quadratic quadratic = new Quadratic();

    quadratic.setCoefficientA(2.5);
    quadratic.setCoefficientB(-3.0);
    quadratic.setCoefficientC(4.75);

    assertEquals(2.5, quadratic.getCoefficientA(), 0.000001);
    assertEquals(-3.0, quadratic.getCoefficientB(), 0.000001);
    assertEquals(4.75, quadratic.getCoefficientC(), 0.000001);
  }

  @Test
  void evaluateShouldReturnCorrectValue() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientA(2.0);
    quadratic.setCoefficientB(3.0);
    quadratic.setCoefficientC(4.0);

    double result = quadratic.evaluate(2.0);

    assertEquals(18.0, result, 0.000001);
  }

  @Test
  void evaluateShouldWorkWithNegativeX() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientA(1.0);
    quadratic.setCoefficientB(-2.0);
    quadratic.setCoefficientC(1.0);

    double result = quadratic.evaluate(-3.0);

    assertEquals(16.0, result, 0.000001);
  }

  @Test
  void sumShouldReturnQuadraticWithAddedCoefficients() {
    Quadratic firstQuadratic = new Quadratic();
    firstQuadratic.setCoefficientA(1.0);
    firstQuadratic.setCoefficientB(2.0);
    firstQuadratic.setCoefficientC(3.0);

    Quadratic secondQuadratic = new Quadratic();
    secondQuadratic.setCoefficientA(4.0);
    secondQuadratic.setCoefficientB(-1.0);
    secondQuadratic.setCoefficientC(5.0);

    Quadratic sumQuadratic = Quadratic.sum(firstQuadratic, secondQuadratic);

    assertEquals(5.0, sumQuadratic.getCoefficientA(), 0.000001);
    assertEquals(1.0, sumQuadratic.getCoefficientB(), 0.000001);
    assertEquals(8.0, sumQuadratic.getCoefficientC(), 0.000001);
  }

  @Test
  void scaleShouldReturnQuadraticWithScaledCoefficients() {
    Quadratic originalQuadratic = new Quadratic();
    originalQuadratic.setCoefficientA(2.0);
    originalQuadratic.setCoefficientB(-3.0);
    originalQuadratic.setCoefficientC(4.0);

    Quadratic scaledQuadratic = Quadratic.scale(2.5, originalQuadratic);

    assertEquals(5.0, scaledQuadratic.getCoefficientA(), 0.000001);
    assertEquals(-7.5, scaledQuadratic.getCoefficientB(), 0.000001);
    assertEquals(10.0, scaledQuadratic.getCoefficientC(), 0.000001);
  }

  @Test
  void scaleShouldNotModifyOriginalQuadratic() {
    Quadratic originalQuadratic = new Quadratic();
    originalQuadratic.setCoefficientA(2.0);
    originalQuadratic.setCoefficientB(3.0);
    originalQuadratic.setCoefficientC(4.0);

    Quadratic scaledQuadratic = Quadratic.scale(3.0, originalQuadratic);

    assertEquals(2.0, originalQuadratic.getCoefficientA(), 0.000001);
    assertEquals(3.0, originalQuadratic.getCoefficientB(), 0.000001);
    assertEquals(4.0, originalQuadratic.getCoefficientC(), 0.000001);

    assertEquals(6.0, scaledQuadratic.getCoefficientA(), 0.000001);
    assertEquals(9.0, scaledQuadratic.getCoefficientB(), 0.000001);
    assertEquals(12.0, scaledQuadratic.getCoefficientC(), 0.000001);
  }

  @Test
  void getRealRootsShouldReturn3ForInfiniteRoots() {
    Quadratic quadratic = new Quadratic();

    assertEquals(3, Quadratic.getRealRoots(quadratic));
  }

  @Test
  void getRealRootsShouldReturn0WhenAAndBAreZeroAndCNonZero() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientC(5.0);

    assertEquals(0, Quadratic.getRealRoots(quadratic));
  }

  @Test
  void getRealRootsShouldReturn1ForLinearEquation() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientB(2.0);
    quadratic.setCoefficientC(-8.0);

    assertEquals(1, Quadratic.getRealRoots(quadratic));
  }

  @Test
  void getRealRootsShouldReturn0ForNegativeDiscriminant() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientA(1.0);
    quadratic.setCoefficientB(0.0);
    quadratic.setCoefficientC(1.0);

    assertEquals(0, Quadratic.getRealRoots(quadratic));
  }

  @Test
  void getRealRootsShouldReturn1ForZeroDiscriminant() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientA(1.0);
    quadratic.setCoefficientB(-2.0);
    quadratic.setCoefficientC(1.0);

    assertEquals(1, Quadratic.getRealRoots(quadratic));
  }

  @Test
  void getRealRootsShouldReturn2ForPositiveDiscriminant() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientA(1.0);
    quadratic.setCoefficientB(-3.0);
    quadratic.setCoefficientC(2.0);

    assertEquals(2, Quadratic.getRealRoots(quadratic));
  }

  @Test
  void getSmallRootShouldReturnZeroForInfiniteRootsCase() {
    Quadratic quadratic = new Quadratic();

    assertEquals(0.0, Quadratic.getSmallRoot(quadratic), 0.000001);
  }

  @Test
  void getLargeRootShouldReturnZeroForInfiniteRootsCase() {
    Quadratic quadratic = new Quadratic();

    assertEquals(0.0, Quadratic.getLargeRoot(quadratic), 0.000001);
  }

  @Test
  void getSmallAndLargeRootShouldReturnSameValueForLinearEquation() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientB(2.0);
    quadratic.setCoefficientC(-8.0);

    assertEquals(4.0, Quadratic.getSmallRoot(quadratic), 0.000001);
    assertEquals(4.0, Quadratic.getLargeRoot(quadratic), 0.000001);
  }

  @Test
  void getSmallAndLargeRootShouldReturnSameValueForDoubleRoot() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientA(1.0);
    quadratic.setCoefficientB(-2.0);
    quadratic.setCoefficientC(1.0);

    assertEquals(1.0, Quadratic.getSmallRoot(quadratic), 0.000001);
    assertEquals(1.0, Quadratic.getLargeRoot(quadratic), 0.000001);
  }

  @Test
  void getSmallAndLargeRootShouldReturnCorrectRootsForTwoRootCase() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientA(1.0);
    quadratic.setCoefficientB(-3.0);
    quadratic.setCoefficientC(2.0);

    assertEquals(1.0, Quadratic.getSmallRoot(quadratic), 0.000001);
    assertEquals(2.0, Quadratic.getLargeRoot(quadratic), 0.000001);
  }

  @Test
  void getSmallAndLargeRootShouldHandleNegativeA() {
    Quadratic quadratic = new Quadratic();
    quadratic.setCoefficientA(-1.0);
    quadratic.setCoefficientB(1.0);
    quadratic.setCoefficientC(6.0);

    assertEquals(-2.0, Quadratic.getSmallRoot(quadratic), 0.000001);
    assertEquals(3.0, Quadratic.getLargeRoot(quadratic), 0.000001);
  }
}
