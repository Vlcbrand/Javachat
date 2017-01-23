import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Vincent Brands on 5/26/2016.
 *
 * Every time a client tries to connect with the server an instance of
 * "ClientThread" will be created, this allows for back and forth connection
 * between the client and server.
 *
 */
public class ClientThread implements Runnable, Comparable<ClientThread> {

    private boolean isAlive;
    private Socket clientsocket;
    private String user;
    private Thread sender;
    private Date date;
    private BufferedReader in = null;

    public ClientThread(Socket client){
        this.clientsocket = client;
        sender = new Thread(new Sender(clientsocket));
        isAlive = true;
        date = new Date();
    }

    @Override
    //Used for sorting the Server.clients list on an alphabetical order
    public int compareTo(ClientThread o) {
        if (this.user == null && o.user == null) {
            return 0;
        } else if (this.user == null) {
            return -1;
        } else if (o.user == null || o == null) {
            return 1;
        }

            else return this.user.compareTo(o.user);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new
                    InputStreamReader(clientsocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(user== null) {
            try {
//                if(in.readLine() != "" || in.readLine() != null || !in.readLine().isEmpty() || in.readLine() != "\n" ) {
                    user = in.readLine();
                    System.out.println(this.getTime() + " " + this.user + " has connected");
                    sender.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        while (isAlive) {
            try {
                //ServerOptions.checkClientCommand(in.readLine(),this);
                Message m1 = new Message(user,in.readLine());
                Server.messages.push(m1);
            } catch (IOException e) {
                isAlive = false;
                Server.messages.push(new Message(user," disconnected"));
                Server.clients.remove(this);
            }
        }
    }

    public void setUser(String user) {
        this.user = user;
    }

    public BufferedReader getIn() {
        return in;
    }

    public Date getDate() {
        return date;
    }

    public String getUser(){
        return user;
    }

    public String getTime(){
        if(date.getMinutes() <10)
            return "" + date.getHours() + ":0" + date.getMinutes();
        else
            return "" + date.getHours() + ":" + date.getMinutes();
    }
}
