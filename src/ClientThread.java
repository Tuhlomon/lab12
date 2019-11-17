import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread {

    private Socket socket;
    private ArrayList<Pair<Socket, String>> socket_list;
    private String name;

    public ClientThread(Socket s, ArrayList<Pair<Socket, String>> sl, String n) {
        socket = s;
        socket_list = sl;
        name = n;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            PrintWriter out2;
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("@senduser")){
                    String[] str = inputLine.split(" ");
                    m:
                    {
                        for (int i = 0; i < socket_list.size(); i++) {
                            if (str[1].equals(socket_list.get(i).getValue())) {
                                out2 = new PrintWriter(socket_list.get(i).getKey().getOutputStream(), true);
                                out2.println(name + ": " + inputLine.substring(11 + str[1].length()) + " (ONLY FOR YOU)");
                                out.println(name + ": " + inputLine.substring(11 + str[1].length()) + " (DELIVERED)");
                                break m;
                            }
                        }
                        out.println(name + ": " + inputLine + " (DELIVERY ERROR)");
                    }
                }
                else {
                    for (int i = 0; i < socket_list.size(); i++) {
                        out2 = new PrintWriter(socket_list.get(i).getKey().getOutputStream(), true);
                        out2.println(name + ": " + inputLine);
                    }
                }
            }
        }
        catch(Exception e) {}
    }
}
