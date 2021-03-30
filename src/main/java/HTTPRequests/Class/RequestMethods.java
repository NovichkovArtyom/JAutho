package HTTPRequests.Class;

public enum RequestMethods {
    ENGINE_START("Commands/engine/start"),
    DTC_START("Commands/diagnostic/start"),
    FOTA_START("Commands/fota/start"),
    METADATA_GET("Commands/metadata"),
    RESET("Commands/reset");
    private String method;
    RequestMethods(String method){
        this.method = method;
    }
    public String getMethod (){
        return this.method;
    }
}
