import javafx.util.Pair;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = Integer.parseInt("9876");
        ArrayList<Pair<Socket, String>> socket_list = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while(true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String name = in.readLine();
                    socket_list.add(new Pair(clientSocket, name));
                    ClientThread cliThread = new ClientThread(clientSocket, socket_list, name);
                    cliThread.start();
                }
                catch(IOException ioe) { }
            }
        } catch (IOException e) { }
    }
}
