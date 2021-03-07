import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // String pubkeyfile="C:/Users/Артём/Desktop/squadron-edge-finder/na";
        String pubkeyfile= "C://.ssh//na";
        String passphrase="an1101";
        String host="qa-edge-gw01.c-cars.tech", user="anovichkov";
        JSch jSch = new JSch();
        jSch.addIdentity(pubkeyfile, passphrase);
        jSch.setKnownHosts("C://.ssh//known_hosts");
        Session session = null;
        ChannelExec channel = null;
        try {
            session = jSch.getSession(user, host, 22);
            //  session.setInputStream( new FileInputStream("C:/.ssh/command.txt"));
            // session.setOutputStream(new FileOutputStream("C:/.ssh/result.txt"));
            session.setPassword(passphrase);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            ((ChannelExec)channel).setCommand("docker logs -f -t --tail=10000" +
                    " protocol-adapter-cs | grep '31475be9-1015-42f7-8223-d3213e30dc02'");
            OutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();
            Thread.sleep(1000);
            String responseString = new String(responseStream.toString());
            System.out.println(responseString);
            channel.disconnect();
            session.disconnect();
        }
        catch (Exception ex){System.out.println(ex.toString());};

    }

}
