package co.edu.eci.mathservice.model;

public class MathResponse {

    private String function;
    private double input;
    private double result;
    private String instanceId;

    public MathResponse(String function, double input, double result, String instanceId) {
        this.function = function;
        this.input = input;
        this.result = result;
        this.instanceId = instanceId;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
