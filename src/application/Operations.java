package application;

public class Operations {
    static final String SUM = "sum";
    public static final String SUBTRACTION = "subtract";
    public static final String MULTIPLICATION = "multiply";
    public static final String DIVISION = "division";

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        return a / b;
    }
}
