package HTTPRequests.Interfaces;

import HTTPRequests.Class.HTTPRequest;
import HTTPRequests.Class.StepResult;

public interface TestStep {
     StepResult execute();
     static StepResult getResult(int code){
        if ((code == 200) ||(code == 301))
        {return StepResult.OK;}
        else return StepResult.NOK;
    }
    void setRequest(HTTPRequest request);
    void setVehicleID(String vehicleID);
}
