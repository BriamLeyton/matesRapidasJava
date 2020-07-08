package application;

/**
 * clase que define las operaciones propuestas al jugador para evaluar el resultado
 */
public class Operations {
    static final String SUM = "sum";
    public static final String SUBTRACTION = "subtract";
    public static final String MULTIPLICATION = "multiply";
    public static final String DIVISION = "division";


    /**
     * aplica para las cuatro funciones:  sum, substract, multiply, divide
     * @param a operando 1
     * @param b operando 2
     * @return devuelve el resultado de la operacion propuesta en entero
     */
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
