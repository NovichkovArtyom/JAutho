import HTTPRequests.Class.StepResult;

public class wTestStep {
    public StepResult compare(RequestFeedback expectedfeedback, RequestAlert expectedalert, RequestFeedback feedback, RequestAlert alert) {
        if ((expectedalert == alert) && (expectedfeedback == feedback)) {
            return StepResult.OK;
        } else {
            return StepResult.NOK;
        }
    }

    public void sendHTTPRequest() {

    }

}