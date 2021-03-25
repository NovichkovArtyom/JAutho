import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        final URL url = new URL("");
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int CONNECTION_TIMEOUT = 100;
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setConnectTimeout(CONNECTION_TIMEOUT);
        conn.setReadTimeout(CONNECTION_TIMEOUT);
        TestExecutor executor = new TestExecutor();

        String HTTPresponce = executor.get_httpresponce(conn);
        System.out.println(ANSI_Colors.ANSI_BLUE + HTTPresponce + ANSI_Colors.ANSI_RESET);

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
            System.out.println(responseString);
            channel.disconnect();
            session.disconnect();
        }
        catch (Exception ex){System.out.println(ex.toString());};

    }

}
