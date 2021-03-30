package HTTPRequests.Class;

import HTTPRequests.Interfaces.TestStep;

public class SquadronRequest implements TestStep {

    @Override
    public StepResult execute() {
       try {
           if ((method == null) || (vehicleID==null) ||(requesthandler == null) || (host== null)) {throw new IllegalArgumentException("Не все поля заполнены");}
       } catch (Exception e) {System.out.println("Не все поля заполнены");}
        int code = requesthandler.sendHTTPGETRequest(new URLBuilder(host,vehicleID,method.getMethod()).getURL());
        return TestStep.getResult(code);
    }

    private HTTPRequest requesthandler;
    private String vehicleID;
    private RequestMethods method;
    private String host;

    public void setRequest(HTTPRequest request) {
        this.requesthandler = request;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
    public void setMethod(RequestMethods method){
        this.method=method;
    }
    public void setHost (String host){
        this.host=host;
    }

}
