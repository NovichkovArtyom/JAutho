package HTTPRequests.Class;

import HTTPRequests.Interfaces.URLGenerator;

public class URLBuilder implements URLGenerator {
    private String URL;
    URLBuilder (String host, String vehicleID, String method){
        generateURL(host, vehicleID, method);
    }
    @Override
    public String generateURL(String host, String vehicleID, String method) {
        URL = "https://squadron.c-cars.tech/" + host + vehicleID + "/" + method;
        return this.URL;
    }
    String getURL(){
        return this.URL;
    }
}