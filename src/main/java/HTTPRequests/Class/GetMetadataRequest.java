package HTTPRequests.Class;

import HTTPRequests.Interfaces.TestStep;

public class GetMetadataRequest implements TestStep {

    @Override
    public StepResult execute() {
        int code = requesthandler.sendHTTPGETRequest(new URLBuilder("car/",vehicleID,Methods.METADATA_GET.getMethod()).getURL());
        return TestStep.getResult(code);
    }

    private HTTPRequest requesthandler;
    private String vehicleID;

    public void setRequest(HTTPRequest request) {
        this.requesthandler = request;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
}
