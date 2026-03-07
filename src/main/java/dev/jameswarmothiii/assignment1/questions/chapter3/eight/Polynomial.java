package dev.jameswarmothiii.assignment1.questions.chapter3.eight;

public class Polynomial implements Cloneable {
    private static final int defaultCapacity = 1;
    private static final double zeroTolerance = 1.0e-12;

    private double[] coefficientValues;
    private int currentPolynomialDegree;

    public Polynomial() {
        coefficientValues = new double[defaultCapacity];
        currentPolynomialDegree = -1;
    }

    public Polynomial(double constantCoefficient) {
        coefficientValues = new double[defaultCapacity];
        coefficientValues[0] = constantCoefficient;
        updateCurrentPolynomialDegree();
    }

    public Polynomial(Polynomial sourcePolynomial) {
        if (sourcePolynomial == null) {
            throw new NullPointerException("sourcePolynomial is null");
        }

        coefficientValues = sourcePolynomial.coefficientValues.clone();
        currentPolynomialDegree = sourcePolynomial.currentPolynomialDegree;
    }

    public static Polynomial add(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        validatePolynomialOperands(firstPolynomial, secondPolynomial);

        int maximumDegree = Math.max(
                firstPolynomial.currentPolynomialDegree,
                secondPolynomial.currentPolynomialDegree
        );

        if (maximumDegree < 0) {
            return new Polynomial();
        }

        Polynomial sumPolynomial = new Polynomial();
        sumPolynomial.reserve(maximumDegree);

        for (int exponentValue = 0; exponentValue <= maximumDegree; exponentValue++) {
            double summedCoefficient =
                    firstPolynomial.coefficient(exponentValue)
                            + secondPolynomial.coefficient(exponentValue);

            sumPolynomial.coefficientValues[exponentValue] = summedCoefficient;
        }

        sumPolynomial.updateCurrentPolynomialDegree();
        return sumPolynomial;
    }

    public static Polynomial subtract(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        validatePolynomialOperands(firstPolynomial, secondPolynomial);

        int maximumDegree = Math.max(
                firstPolynomial.currentPolynomialDegree,
                secondPolynomial.currentPolynomialDegree
        );

        if (maximumDegree < 0) {
            return new Polynomial();
        }

        Polynomial differencePolynomial = new Polynomial();
        differencePolynomial.reserve(maximumDegree);

        for (int exponentValue = 0; exponentValue <= maximumDegree; exponentValue++) {
            double differenceCoefficient =
                    firstPolynomial.coefficient(exponentValue)
                            - secondPolynomial.coefficient(exponentValue);

            differencePolynomial.coefficientValues[exponentValue] = differenceCoefficient;
        }

        differencePolynomial.updateCurrentPolynomialDegree();
        return differencePolynomial;
    }

    public static Polynomial multiply(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        validatePolynomialOperands(firstPolynomial, secondPolynomial);

        if (firstPolynomial.currentPolynomialDegree < 0 || secondPolynomial.currentPolynomialDegree < 0) {
            return new Polynomial();
        }

        int productDegree = safeAddIntegers(
                firstPolynomial.currentPolynomialDegree,
                secondPolynomial.currentPolynomialDegree
        );

        Polynomial productPolynomial = new Polynomial();
        productPolynomial.reserve(productDegree);

        for (int firstExponentValue = 0;
             firstExponentValue <= firstPolynomial.currentPolynomialDegree;
             firstExponentValue++) {

            if (isEffectivelyZero(firstPolynomial.coefficientValues[firstExponentValue])) {
                continue;
            }

            for (int secondExponentValue = 0;
                 secondExponentValue <= secondPolynomial.currentPolynomialDegree;
                 secondExponentValue++) {

                if (isEffectivelyZero(secondPolynomial.coefficientValues[secondExponentValue])) {
                    continue;
                }

                int productExponentValue = firstExponentValue + secondExponentValue;

                productPolynomial.coefficientValues[productExponentValue] +=
                        firstPolynomial.coefficientValues[firstExponentValue]
                                * secondPolynomial.coefficientValues[secondExponentValue];
            }
        }

        productPolynomial.updateCurrentPolynomialDegree();
        return productPolynomial;
    }

    private static void validatePolynomialOperands(
            Polynomial firstPolynomial,
            Polynomial secondPolynomial
    ) {
        if (firstPolynomial == null || secondPolynomial == null) {
            throw new NullPointerException("Polynomial argument is null");
        }
    }

    private static void validateExponentValue(int exponentValue) {
        if (exponentValue < 0) {
            throw new IllegalArgumentException("exponentValue is negative: " + exponentValue);
        }
    }

    private static int safeAddIntegers(int firstValue, int secondValue) {
        long sumValue = (long) firstValue + secondValue;

        if (sumValue > Integer.MAX_VALUE) {
            throw new OutOfMemoryError("Polynomial degree overflow");
        }

        return (int) sumValue;
    }

    private static boolean isEffectivelyZero(double value) {
        return Math.abs(value) < zeroTolerance;
    }

    @Override
    public Polynomial clone() {
        try {
            Polynomial copiedPolynomial = (Polynomial) super.clone();
            copiedPolynomial.coefficientValues = coefficientValues.clone();
            return copiedPolynomial;
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError("Clone should be supported", cloneNotSupportedException);
        }
    }

    public void addToCoefficient(double amountToAdd, int exponentValue) {
        validateExponentValue(exponentValue);
        reserve(exponentValue);
        coefficientValues[exponentValue] += amountToAdd;
        updateDegreeAfterCoefficientWrite(exponentValue);
    }

    public void assignCoefficient(double newCoefficientValue, int exponentValue) {
        validateExponentValue(exponentValue);
        reserve(exponentValue);
        coefficientValues[exponentValue] = newCoefficientValue;
        updateDegreeAfterCoefficientWrite(exponentValue);
    }

    public void clear() {
        coefficientValues = new double[defaultCapacity];
        currentPolynomialDegree = -1;
    }

    public void reserve(int degreeValue) {
        validateExponentValue(degreeValue);

        int requiredArrayLength = safeAddIntegers(degreeValue, 1);
        if (coefficientValues.length >= requiredArrayLength) {
            return;
        }

        double[] expandedCoefficientValues = new double[requiredArrayLength];
        System.arraycopy(
                coefficientValues,
                0,
                expandedCoefficientValues,
                0,
                coefficientValues.length
        );
        coefficientValues = expandedCoefficientValues;
    }

    public double coefficient(int exponentValue) {
        validateExponentValue(exponentValue);

        if (exponentValue >= coefficientValues.length) {
            return 0.0;
        }

        return coefficientValues[exponentValue];
    }

    public int degree() {
        return currentPolynomialDegree;
    }

    public int nextTerm(int exponentValue) {
        if (currentPolynomialDegree < 0) {
            return -1;
        }

        int startingIndex = exponentValue + 1;
        if (startingIndex < 0) {
            startingIndex = 0;
        }

        for (int index = startingIndex; index <= currentPolynomialDegree; index++) {
            if (!isEffectivelyZero(coefficientValues[index])) {
                return index;
            }
        }

        return -1;
    }

    public double evaluate(double xValue) {
        if (currentPolynomialDegree < 0) {
            return 0.0;
        }

        double evaluationResult = 0.0;

        for (int exponentValue = currentPolynomialDegree; exponentValue >= 0; exponentValue--) {
            evaluationResult = (evaluationResult * xValue) + coefficientValues[exponentValue];
        }

        return evaluationResult;
    }

    @Override
    public String toString() {
        if (currentPolynomialDegree < 0) {
            return "0.0";
        }

        StringBuilder polynomialText = new StringBuilder();

        for (int exponentValue = currentPolynomialDegree; exponentValue >= 0; exponentValue--) {
            double coefficientValue = coefficientValues[exponentValue];

            if (isEffectivelyZero(coefficientValue)) {
                continue;
            }

            if (polynomialText.length() > 0) {
                if (coefficientValue >= 0) {
                    polynomialText.append(" + ");
                } else {
                    polynomialText.append(" - ");
                    coefficientValue = -coefficientValue;
                }
            } else if (coefficientValue < 0) {
                polynomialText.append("-");
                coefficientValue = -coefficientValue;
            }

            if (exponentValue == 0) {
                polynomialText.append(coefficientValue);
            } else if (exponentValue == 1) {
                polynomialText.append(coefficientValue).append("x");
            } else {
                polynomialText.append(coefficientValue).append("x^").append(exponentValue);
            }
        }

        return polynomialText.toString();
    }

    private void updateDegreeAfterCoefficientWrite(int changedExponentValue) {
        if (changedExponentValue > currentPolynomialDegree
                && !isEffectivelyZero(coefficientValues[changedExponentValue])) {
            currentPolynomialDegree = changedExponentValue;
            return;
        }

        if (changedExponentValue == currentPolynomialDegree
                && isEffectivelyZero(coefficientValues[changedExponentValue])) {
            updateCurrentPolynomialDegree();
        }
    }

    private void updateCurrentPolynomialDegree() {
        int index = coefficientValues.length - 1;

        while (index >= 0 && isEffectivelyZero(coefficientValues[index])) {
            index--;
        }

        currentPolynomialDegree = index;
    }
}