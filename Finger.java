import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Finger {
    final static int port = 79;
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("This application requires 1 arg e.g. arb33@hermes.cam.ac.uk");
            return;
        }
        String user = args[0].split("@")[0];
        String domain = args[0 -.split("@")[1];
        try {
            final Socket s = new Socket(domain, port);
            final PrintWriter pw = new PrintWriter(s.getOutputStream());
            final BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            pw.print(user + " \r\n");
            pw.flush();
            System.out.printf("[%s]%n",domain);
            while (true) {
                final String line = in.readLine();
                if (line == null) break;
                System.out.println(line);
            }

            s.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
