import HTTPRequests.Class.GetMetadataRequest;
import HTTPRequests.Class.HTTPRequest;
import HTTPRequests.Class.StepResult;
import HTTPRequests.Interfaces.TestStep;

import java.util.Vector;

public class Main {
    public static void main(String[] args) throws Exception {

        Vector<TestStep> CASE = new Vector<TestStep>();
        CASE.add(new GetMetadataRequest());
        HTTPRequest request = new HTTPRequest();
        for (TestStep step : CASE){
            step.setRequest(request);
            step.setVehicleID("");
            if (step.execute() == StepResult.OK){
            Thread.sleep(5000);
            System.out.println("Main:caseOK");
        }
            else {
                System.out.println("Main:break");
                break;}
        }
    }
}