import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class TestExecutor {
    public String get_httpresponce (HttpURLConnection conn){
try (final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
        String inputLine;
        final StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        return content.toString();
    } catch (final Exception ex) {
        ex.printStackTrace();
        return "";
    }}
}
