package HTTPRequests.Class;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;


public class HTTPRequest {

    public int sendHTTPGETRequest(String url) {
        int code=0;

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tokenHolder.getToken());
            try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
                HttpEntity entity1 = response1.getEntity();
                EntityUtils.consume(entity1);
                code = response1.getCode();
            } catch (java.io.IOException IOException) {
                System.out.println("Исключение. Причина: " + IOException.getCause());
                System.out.println("Исключение. Сообщение: " + IOException.getMessage());
                return 0;
            }
        } catch (java.io.IOException IOException) {
            System.out.println("Исключение. Причина: " + IOException.getCause());
            System.out.println("Исключение. Сообщение: " + IOException.getMessage());
            return 0;
        }

        return code;
    }

    public int sendHTTPPOSTRequest(String url) {

        return 0;
    }

    SquadronAuthorizationTokenHolder tokenHolder;

}
