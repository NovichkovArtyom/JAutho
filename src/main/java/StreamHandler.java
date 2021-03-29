import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

public class StreamHandler {
    public static void main(String[] args) throws Exception {
        try {
        JsonReader jsonReader = Json.createReader(new StringReader("{\"access_token\" : \"token\"}"));
        JsonObject jsonObject = jsonReader.readObject();
        String finalToken = jsonObject.getString("access_token");
        System.out.println(finalToken);
    }
        catch (Exception exception){
            System.out.println(exception.toString());
        }
    }
}
