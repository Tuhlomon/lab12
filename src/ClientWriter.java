import java.io.*;

public class ClientWriter extends Thread {

    BufferedReader in;

    ClientWriter(BufferedReader br) {
        in = br;
    }

    public void run() {
        try {
            String input;
            while(true) {
                input = in.readLine();
                System.out.println(input);
            }
        }
        catch(Exception e) {}
    }
}