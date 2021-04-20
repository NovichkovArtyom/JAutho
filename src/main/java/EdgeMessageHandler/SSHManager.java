package EdgeMessageHandler;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;

import com.jcraft.jsch.Session;

import javax.json.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class SSHManager {
    private final String publickeyfile = "./.ssh//na";
    private final String passphrase = "an1101";
    private final String host = "qa-edge-gw01.c-cars.tech", user = "anovichkov";

    public void connect ()  {
        JSch jSch = new JSch();
        try {
            jSch.addIdentity(publickeyfile, passphrase);
            jSch.setKnownHosts("./.ssh//known_hosts");
        } catch (Exception exception){
            System.out.println("Что - то с known hosts, с ключом или паролем");
            System.exit(0);
        }

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
            String responseString = new String(responseStream.toString());
            if (responseString.isEmpty()) {
                System.out.println(ConsoleColors.ANSI_Colors.ANSI_YELLOW + "Response Is Empty!" + ConsoleColors.ANSI_Colors.ANSI_RESET);
            }
            JsonReader jsonReader = Json.createReader();
            JsonObject jsonObject = jsonReader.readObject();
            channel.disconnect();
            session.disconnect();
        } catch (
                Exception ex) {
            System.out.println("Не законнектился. что то SSH Manager-ом");
            System.out.println(ex.toString());
        }


    }
}