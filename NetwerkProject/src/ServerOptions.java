import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Vincent Brands on 6/1/2016.
 */
public class ServerOptions implements Runnable {

    Scanner scanner = new Scanner(System.in);
    String s = "";

    @Override
    public void run() {
        System.out.println("type HELP for server commands");
        while (true) {
            s = scanner.nextLine().toUpperCase();
            switch (s) {
                case "SORT":
                    System.out.println("Sort on: ALPHABET / JOINED");
                    s = scanner.nextLine().toUpperCase();

                    if (s.equals("ALPHABET")) {
                        Collections.sort(Server.clients);

                    } else if (s.equals("JOINED")) {
                        Collections.sort(Server.clients, new ClientJoinedComparator());
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (ClientThread c : Server.clients) {
                        System.out.println(c.getDate() + ": " + c.getUser());
                    }
                    break;
                case "EXIT":
                    System.out.println("Are you sure? (YES / NO)");
                    s = scanner.nextLine().toUpperCase();
                    if (s.equals("YES")) {
                        System.out.println("Server closing down");
                        System.exit(0);
                    }
                    break;
                case "HELP":
                    System.out.println("Server commands: \n SORT \n EXIT");
                    break;
            }
        }
    }

    //TODO: work this out so user can change name / exit with commands.
    public static String checkClientCommand(String s, ClientThread t){

        switch (s) {
            case "HELP":
                System.out.println("Client commands: \n NAME \n EXIT");
                break;
            case "NAME":
                try {
                    s = t.getIn().readLine();
                    t.setUser(s);
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }
        return s;
    }

}


