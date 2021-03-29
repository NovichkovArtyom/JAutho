public enum HTTPRequests {
    METADATA_GET("GET_METADATA_REQUEST"),
    WAKEUP_TCU("TCU_WAKEUP_REQUEST"),
    DIAGNOSTIC_START("DIAGNOSTIC_DTC_START_REQUEST"),
    HEATER_START("HEATER_START_REQUEST");

    private String res;
    HTTPRequests (String res){
        this.res = res;
    }
}
