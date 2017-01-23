import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Server {

    public static ArrayList<ClientThread> clients = new ArrayList<>();
    public static LinkedList<Message> messages = new LinkedList<>();

    public static void main(String[] args) {
        new Server();

    }

    public Server(){
       loop();

    }

    public void loop() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("server started");

            while (true) {
                Thread t;
                t = new Thread(new ServerOptions());
                t.start();

                ClientThread w;
                w = new ClientThread(serverSocket.accept());
                Thread t1 = new Thread(w);
                t1.start();
                clients.add(w);
//                if (clients.size() > 3) {
//                    Thread.sleep(5000);
//                    Collections.sort(clients, new ClientJoinedComparator().reversed());
//                    for(ClientThread c: clients){
//                        System.out.println(c.getUser());
//                    }
//                    }
                }
           } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}


