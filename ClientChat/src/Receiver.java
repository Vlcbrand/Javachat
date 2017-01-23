import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Vincent Brands on 5/30/2016.
 */
public class Receiver implements Runnable
{
    BufferedReader incoming;
    Socket socket;

    public Receiver(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            String s;
            try {
                s = incoming.readLine();
                System.out.println(s);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

    }
}
