import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        int portNumber = Integer.parseInt("9876");
        try {
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            ClientReader cr = new ClientReader(stdIn, out);
            cr.start();
            ClientWriter cw = new ClientWriter(in);
            cw.start();
        }
        catch (UnknownHostException e) { }
        catch (IOException e) { }
    }
}
