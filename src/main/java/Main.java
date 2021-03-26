import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import netscape.javascript.JSObject;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.*;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import javax.json.*;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        
        
        String finalToken = "";


        
        
        
        try (CloseableHttpClient httpClientToken = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://qa004.c-cars.tech/connect/token");
        /*  httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpPost.setHeader("grant_type=", "client_credentials");
            httpPost.setHeader("&scope=", "Qa.Squadron.Proxy.Api");
            httpPost.setHeader("&client_id=", "fms.service.client");
            httpPost.setHeader("@client_secret=", "$1$ygDUnQzk$hN5S4RxcskLZGlfqjiGgw/");*/

            List<NameValuePair> nvps = new ArrayList<>();
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
          //  nvps.add(new BasicNameValuePair(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded"));
            nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
            nvps.add(new BasicNameValuePair("&scope", "Qa.Squadron.Proxy.Api"));
            nvps.add(new BasicNameValuePair("&client_id", "fms.service.client"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            nvps.add(new BasicNameValuePair("@client_secret", "$1$ygDUnQzk$hN5S4RxcskLZGlfqjiGgw/"));
            StringEntity stringEntity = new StringEntity(nvps.toString());
            httpPost.setEntity(stringEntity);
          //  System.out.println((nvps));

       try (CloseableHttpResponse responsewithToken = httpClientToken.execute(httpPost)) {
            System.out.println(ANSI_Colors.ANSI_BLUE + responsewithToken.getCode() + " " + responsewithToken.getReasonPhrase() + ANSI_Colors.ANSI_RESET);
            finalToken= responsewithToken.toString();
            System.out.println(finalToken);
            HttpEntity entity = responsewithToken.getEntity();

            System.out.println( EntityUtils.toString(entity));
            EntityUtils.consume(entity);
        }
}

       /* try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://squadron.c-cars.tech/car/0e021cb1-cbda-4a47-b634-0194e0b8c2da/commands/metadata");

            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + finalToken);
            try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
                System.out.println(ANSI_Colors.ANSI_BLUE + response1.getCode() + " " + response1.getReasonPhrase() + ANSI_Colors.ANSI_RESET);
                HttpEntity entity1 = response1.getEntity();
                EntityUtils.consume(entity1);
            }}



        String pubkeyfile= "./.ssh//na";
        String passphrase="an1101";
        String host="qa-edge-gw01.c-cars.tech", user="anovichkov";
        JSch jSch = new JSch();
        jSch.addIdentity(pubkeyfile, passphrase);
        jSch.setKnownHosts("./.ssh//known_hosts");
        Session session = null;
        ChannelExec channel = null;
        try {
            session = jSch.getSession(user, host, 22);
            session.setPassword(passphrase);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            ((ChannelExec)channel).setCommand("docker logs -f -t --tail=1000 protocol-adapter-cs | grep '31475be9-1015-42f7-8223-d3213e30dc02'");
            OutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();
            Thread.sleep(1000);
            String responseString = new String(responseStream.toString());
            if (responseString.isEmpty())
            {
                System.out.println(ANSI_Colors.ANSI_YELLOW + "Resonse Is Empty!" + ANSI_Colors.ANSI_RESET);
            }
          //  System.out.println(responseString);
            channel.disconnect();
            session.disconnect();
        }
        catch (Exception ex){System.out.println(ex.toString());};
*/
    }

}
