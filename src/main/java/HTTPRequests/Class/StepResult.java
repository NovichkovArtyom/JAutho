package HTTPRequests.Class;

public enum StepResult {
    OK("OK"),
    NOK("NOT OK");
    private String res;
    StepResult(String res){
        this.res = res;
    }
}
