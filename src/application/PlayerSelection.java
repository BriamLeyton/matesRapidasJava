package application;

public class PlayerSelection {

    private int selectionOne;
    private int selectionTwo;
    private String operation;
    private float resultOperation;
    private float playerResult;
    private boolean isValid;

    public int getSelectionOne() {
        return selectionOne;
    }

    public void setSelectionOne(int selectionOne) {
        this.selectionOne = selectionOne;
    }

    public int getSelectionTwo() {
        return selectionTwo;
    }

    public void setSelectionTwo(int selectionTwo) {
        this.selectionTwo = selectionTwo;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public float getResultOperation() {
        return resultOperation;
    }

    private void setResultOperation(float resultOperation) {
        this.resultOperation = resultOperation;
    }

    public float getPlayerResult() {
        return playerResult;
    }

    public void setPlayerResult(float playerResult) {
        this.playerResult = playerResult;
    }

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

    private void evaluateResult() {
        this.isValid = getResultOperation() == getPlayerResult();
    }

    public boolean isValid() {
        return isValid;
    }
}
