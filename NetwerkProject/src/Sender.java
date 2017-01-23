import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.out;
import static java.lang.System.setErr;

/**
 * Created by Vincent Brands on 5/30/2016.
 *
 * This class will allow every ClientThread to send back data to it's
 * connected client.
 *
 */
public class Sender implements Runnable {

    private PrintWriter out = null;
    private Socket clientsocket;

    public Sender(Socket client) {
        this.clientsocket = client;
    }

    @Override
    public void run() {
        Message m1= null;
        try {
            out = new
                    PrintWriter(clientsocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!Server.messages.isEmpty()){
            m1 = Server.messages.peekLast();
        }


        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(m1 != Server.messages.peekFirst()){

                    m1 = Server.messages.peekFirst();
                    out.println(m1.getTime() + " - " +m1.getName() + ": " + m1.getMessage());
                    out.flush();
                }
                }

        }
}


