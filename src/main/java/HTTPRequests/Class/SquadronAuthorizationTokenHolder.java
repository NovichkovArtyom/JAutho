package HTTPRequests.Class;

import ConsoleColors.ANSI_Colors;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SquadronAuthorizationTokenHolder {
    public SquadronAuthorizationTokenHolder(){
        getNewToken();
    }

    private String token;
    private long expireTime;

    private String getNewToken() {

        try (CloseableHttpClient httpClientToken = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://qa004.c-cars.tech/connect/token");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
            nvps.add(new BasicNameValuePair("scope", "Qa.Squadron.Proxy.Api"));
            nvps.add(new BasicNameValuePair("client_id", "fms.service.client"));
            nvps.add(new BasicNameValuePair("client_secret", "$1$ygDUnQzk$hN5S4RxcskLZGlfqjiGgw/"));
            HttpEntity entity1 = new UrlEncodedFormEntity(nvps);
            httpPost.setEntity(entity1);


            try (CloseableHttpResponse responsiveToken = httpClientToken.execute(httpPost)){
                HttpEntity response = responsiveToken.getEntity();
                String responseStream = EntityUtils.toString(response);
                JsonReader jsonReader = Json.createReader(new StringReader(responseStream));
                JsonObject jsonObject = jsonReader.readObject();
                this.token = jsonObject.getString("access_token");
                this.expireTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(jsonObject.getInt("expires_in"));
                EntityUtils.consume(response);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Что-то пошло не так при получении нового токена либо при парсинге json: ");
                System.out.println(ANSI_Colors.ANSI_YELLOW + ex.toString() + ANSI_Colors.ANSI_RESET);
            }

        } catch (Exception exception) {
            System.out.println("Не смог создать httpDefaultClient: " + exception.toString());
        }

        return this.token;
    }

    public String getToken() {
        if (this.expireTime <= System.currentTimeMillis()) {
            getNewToken();
        }
        return this.token;
    }

    public void setToken(String token, int expireDate) {
        this.token = token;
        this.expireTime = expireDate;
    }
}
