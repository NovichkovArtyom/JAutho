import HTTPRequests.Class.RequestMethods;
import HTTPRequests.Class.SquadronRequest;
import HTTPRequests.Class.HTTPRequest;
import HTTPRequests.Class.StepResult;
import HTTPRequests.Interfaces.TestStep;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Thread current = Thread.currentThread();
        StackTraceElement[] methods = current.getStackTrace();
        Vector<TestStep> CASE = new Vector<TestStep>();
        CASE.add(new SquadronRequest());

        HTTPRequest request = new HTTPRequest();
        for (TestStep step : CASE){
            step.setRequest(request);
            step.setVehicleID("0e021cb1-cbda-4a47-b634-0194e0b8c2da");
            step.setHost("car/");
            step.setMethod(RequestMethods.METADATA_GET);
            if (step.execute() == StepResult.OK){
                try {
                    Thread.sleep(5000);
                    System.out.println("Main:caseOK");
                } catch (InterruptedException exception ){
                    System.out.println("SLEEP EXCEPTION!");
                }
            }
            else {
                System.out.println("Main:break");
                break;
            }
        }
    }
}