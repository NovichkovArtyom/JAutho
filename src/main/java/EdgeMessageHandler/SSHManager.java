package EdgeMessageHandler;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class SSHManager {
    private final String publickeyfile = "./.ssh//na";
    private final String passphrase = "an1101";
    private final String host = "qa-edge-gw01.c-cars.tech", user = "anovichkov";

    public void connect () throws JSchException {
        JSch jSch = new JSch();
        jSch.addIdentity(publickeyfile, passphrase);
        jSch.setKnownHosts("./.ssh//known_hosts");
        Session session = null;
        ChannelExec channel = null;

        try {
            session = jSch.getSession(user, host, 22);
            session.setPassword(passphrase);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            ((ChannelExec) channel).setCommand("docker logs -f -t --tail=1000 protocol-adapter-cs | grep '31475be9-1015-42f7-8223-d3213e30dc02'");
            OutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();
            Thread.sleep(1000);
            String responseString = new String(responseStream.toString());
            if (responseString.isEmpty()) {
                System.out.println(ConsoleColors.ANSI_Colors.ANSI_YELLOW + "Response Is Empty!" + ConsoleColors.ANSI_Colors.ANSI_RESET);
            }
            //  System.out.println(responseString);
            channel.disconnect();
            session.disconnect();
        } catch (
                Exception ex) {
            System.out.println(ex.toString());
        }


    }
}