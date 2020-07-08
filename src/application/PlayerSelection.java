package application;

/**
 * clase para la seleccion de jugador en tiempo de ejecucion
 */
public class PlayerSelection {

    private int selectionOne;
    private int selectionTwo;
    private String operation;
    private float resultOperation;
    private float playerResult;
    private boolean isValid;

    /**
     * @return retorna el numero seleccionado
     */
    public int getSelectionOne() {
        return selectionOne;
    }

    /**
     * @param selectionOne configura el valor del primer numero seleccionado
     */
    public void setSelectionOne(int selectionOne) {
        this.selectionOne = selectionOne;
    }

    /**
     * @return retorna el segundo numero seleccionado
     */
    public int getSelectionTwo() {
        return selectionTwo;
    }

    /**
     * @param selectionTwo configura el valor del segundo numero seleccionado
     */
    public void setSelectionTwo(int selectionTwo) {
        this.selectionTwo = selectionTwo;
    }

    /**
     * @return retorna la operacion
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation configura la operacion a realizar
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return retorna el resultado de la operacion
     */
    public float getResultOperation() {
        return resultOperation;
    }

    /**
     * @param resultOperation guarda el resultado de la operacion
     */
    private void setResultOperation(float resultOperation) {
        this.resultOperation = resultOperation;
    }

    /**
     * @return retorna el valor indicador por el usuario
     */
    public float getPlayerResult() {
        return playerResult;
    }

    /**
     * @param playerResult guarda el resultado del jugador
     */
    public void setPlayerResult(float playerResult) {
        this.playerResult = playerResult;
    }

    /**
     * elige la operacion segun el operador seteado en la variable this.operation
     * @throws Exception
     */
    public void makeOperation() throws Exception {
        switch (this.operation){
            case Operations.SUM:
                this.setResultOperation(Operations.sum(selectionOne, selectionTwo));
                break;
            case Operations.SUBTRACTION:
                this.setResultOperation(Operations.subtract(selectionOne, selectionTwo));
                break;
            case Operations.MULTIPLICATION:
                this.setResultOperation(Operations.multiply(selectionOne, selectionTwo));
                break;
            case Operations.DIVISION:
                this.setResultOperation(Operations.divide(selectionOne, selectionTwo));
                break;
            default:
                throw new Exception("Operations doesn't exist");
        }
        evaluateResult();
    }

    /**
     * guarda en la variable isValid si el resultado otorgado por el usuario es igual al calculado por la aplicacion
     */
    private void evaluateResult() {
        this.isValid = getResultOperation() == getPlayerResult();
    }

    /**
     * @return retorna el resultado boolean para la comparacion
     */
    public boolean isValid() {
        return isValid;
    }
}
