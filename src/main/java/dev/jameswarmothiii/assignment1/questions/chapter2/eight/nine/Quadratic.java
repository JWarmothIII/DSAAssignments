package dev.jameswarmothiii.assignment1.questions.chapter2.eight.nine;

public class Quadratic {
  private double coefficientA;
  private double coefficientB;
  private double coefficientC;

  public Quadratic() {
    this.coefficientA = 0;
    this.coefficientB = 0;
    this.coefficientC = 0;
  }

  public static Quadratic sum(Quadratic quadratic1, Quadratic quadratic2) {
    Quadratic quadraticResult = new Quadratic();
    quadraticResult.setCoefficientA(quadratic1.getCoefficientA() + quadratic2.getCoefficientA());
    quadraticResult.setCoefficientB(quadratic1.getCoefficientB() + quadratic2.getCoefficientB());
    quadraticResult.setCoefficientC(quadratic1.getCoefficientC() + quadratic2.getCoefficientC());
    return quadraticResult;
  }

  public static Quadratic scale(double r, Quadratic quadratic) {
    Quadratic quadraticResult = new Quadratic();
    quadraticResult.setCoefficientA(quadratic.getCoefficientA() * r);
    quadraticResult.setCoefficientB(quadratic.getCoefficientB() * r);
    quadraticResult.setCoefficientC(quadratic.getCoefficientC() * r);
    return quadraticResult;
  }

  public static int getRealRoots(Quadratic quadratic) {
    double coefficientA = quadratic.getCoefficientA();
    double coefficientB = quadratic.getCoefficientB();
    double coefficientC = quadratic.getCoefficientC();

    if (coefficientA == 0 && coefficientB == 0 && coefficientC == 0) {
      return 3;
    }

    if (coefficientA == 0 && coefficientB == 0 && coefficientC != 0) {
      return 0;
    }

    if (coefficientA == 0 && coefficientB != 0) {
      return 1;
    }

    double coefficientBSquared = coefficientB * coefficientB;
    double coefficient4AC = 4 * coefficientA * coefficientC;

    if (coefficientBSquared < coefficient4AC) {
      return 0;
    }

    if (coefficientBSquared == coefficient4AC) {
      return 1;
    }

    return 2;
  }

  public static double getSmallRoot(Quadratic quadratic) {
    double coefficientA = quadratic.getCoefficientA();
    double coefficientB = quadratic.getCoefficientB();
    double coefficientC = quadratic.getCoefficientC();

    int rootCount = getRealRoots(quadratic);

    if (rootCount == 3) {
      return 0;
    }

    if (coefficientA == 0) {
      return -coefficientC / coefficientB;
    }

    double coefficientBSquared = coefficientB * coefficientB;
    double coefficient4AC = 4 * coefficientA * coefficientC;

    if (coefficientBSquared == coefficient4AC) {
      return -coefficientB / (2 * coefficientA);
    }

    double coefficientsSquareRoot = Math.sqrt(coefficientBSquared - coefficient4AC);
    double root1 = (-coefficientB - coefficientsSquareRoot) / (2 * coefficientA);
    double root2 = (-coefficientB + coefficientsSquareRoot) / (2 * coefficientA);

    return Math.min(root1, root2);
  }

  public static double getLargeRoot(Quadratic quadratic) {
    double coefficientA = quadratic.getCoefficientA();
    double coefficientB = quadratic.getCoefficientB();
    double coefficientC = quadratic.getCoefficientC();

    int rootCount = getRealRoots(quadratic);

    if (rootCount == 3) {
      return 0;
    }

    if (coefficientA == 0) {
      return -coefficientC / coefficientB;
    }

    double coefficientBSquared = coefficientB * coefficientB;
    double coefficient4AC = 4 * coefficientA * coefficientC;

    if (coefficientBSquared == coefficient4AC) {
      return -coefficientB / (2 * coefficientA);
    }

    double coefficientsSquareRoot = Math.sqrt(coefficientBSquared - coefficient4AC);
    double root1 = (-coefficientB - coefficientsSquareRoot) / (2 * coefficientA);
    double root2 = (-coefficientB + coefficientsSquareRoot) / (2 * coefficientA);

    return Math.max(root1, root2);
  }

  public double getCoefficientA() {
    return coefficientA;
  }

  public void setCoefficientA(double coefficientA) {
    this.coefficientA = coefficientA;
  }

  public double getCoefficientB() {
    return coefficientB;
  }

  public void setCoefficientB(double coefficientB) {
    this.coefficientB = coefficientB;
  }

  public double getCoefficientC() {
    return coefficientC;
  }

  public void setCoefficientC(double coefficientC) {
    this.coefficientC = coefficientC;
  }

  public double evaluate(double x) {
    return (coefficientA * x * x) + (coefficientB * x) + coefficientC;
  }
}
