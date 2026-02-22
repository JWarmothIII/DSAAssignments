package dev.jameswarmothiii;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class QuadraticTest {

    @Test
    void constructorShouldInitializeCoefficientsToZero() {
        Quadratic q = new Quadratic();

        assertEquals(0.0, q.getCoefficientA(), 0.000001);
        assertEquals(0.0, q.getCoefficientB(), 0.000001);
        assertEquals(0.0, q.getCoefficientC(), 0.000001);
    }

    @Test
    void settersAndGettersShouldWork() {
        Quadratic q = new Quadratic();

        q.setCoefficientA(2.5);
        q.setCoefficientB(-3.0);
        q.setCoefficientC(4.75);

        assertEquals(2.5, q.getCoefficientA(), 0.000001);
        assertEquals(-3.0, q.getCoefficientB(), 0.000001);
        assertEquals(4.75, q.getCoefficientC(), 0.000001);
    }

    @Test
    void evaluateShouldReturnCorrectValue() {
        Quadratic q = new Quadratic();
        q.setCoefficientA(2.0);
        q.setCoefficientB(3.0);
        q.setCoefficientC(4.0);

        // 2(2^2) + 3(2) + 4 = 8 + 6 + 4 = 18
        double result = q.evaluate(2.0);

        assertEquals(18.0, result, 0.000001);
    }

    @Test
    void evaluateShouldWorkWithNegativeX() {
        Quadratic q = new Quadratic();
        q.setCoefficientA(1.0);
        q.setCoefficientB(-2.0);
        q.setCoefficientC(1.0);

        // 1(-3^2) + (-2)(-3) + 1 = 9 + 6 + 1 = 16
        double result = q.evaluate(-3.0);

        assertEquals(16.0, result, 0.000001);
    }

    @Test
    void sumShouldReturnQuadraticWithAddedCoefficients() {
        Quadratic q1 = new Quadratic();
        q1.setCoefficientA(1.0);
        q1.setCoefficientB(2.0);
        q1.setCoefficientC(3.0);

        Quadratic q2 = new Quadratic();
        q2.setCoefficientA(4.0);
        q2.setCoefficientB(-1.0);
        q2.setCoefficientC(5.0);

        Quadratic sum = Quadratic.sum(q1, q2);

        assertEquals(5.0, sum.getCoefficientA(), 0.000001);
        assertEquals(1.0, sum.getCoefficientB(), 0.000001);
        assertEquals(8.0, sum.getCoefficientC(), 0.000001);
    }

    @Test
    void scaleShouldReturnQuadraticWithScaledCoefficients() {
        Quadratic q = new Quadratic();
        q.setCoefficientA(2.0);
        q.setCoefficientB(-3.0);
        q.setCoefficientC(4.0);

        Quadratic scaled = Quadratic.scale(2.5, q);

        assertEquals(5.0, scaled.getCoefficientA(), 0.000001);
        assertEquals(-7.5, scaled.getCoefficientB(), 0.000001);
        assertEquals(10.0, scaled.getCoefficientC(), 0.000001);
    }

    @Test
    void scaleShouldNotModifyOriginalQuadratic() {
        Quadratic q = new Quadratic();
        q.setCoefficientA(2.0);
        q.setCoefficientB(3.0);
        q.setCoefficientC(4.0);

        Quadratic scaled = Quadratic.scale(3.0, q);

        // Original should stay unchanged
        assertEquals(2.0, q.getCoefficientA(), 0.000001);
        assertEquals(3.0, q.getCoefficientB(), 0.000001);
        assertEquals(4.0, q.getCoefficientC(), 0.000001);

        // Scaled should be different
        assertEquals(6.0, scaled.getCoefficientA(), 0.000001);
        assertEquals(9.0, scaled.getCoefficientB(), 0.000001);
        assertEquals(12.0, scaled.getCoefficientC(), 0.000001);
    }
}
