import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;

import static java.lang.System.in;
import static java.lang.System.setOut;

/**
 * Created by Vincent Brands on 5/26/2016.
 */
public class Client {

    public static void main(String[] args) {
        new Client();
        System.out.println("type HELP for the commands");
    }

    private Socket socket;


    public  Client(){
        try{
            this.socket = new Socket("localhost", 8000);
            OutputStream clientOutput = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(clientOutput);
            System.out.println("Enter name please...");
            Scanner scanner = new Scanner(System.in);
            String s= "";

            Thread t = new Thread(new Receiver(socket));
            t.start();

            while (true){
                s = scanner.nextLine();
                writer.println(s);
                writer.flush();
                Thread.sleep(1);
            }

        }
        catch(IOException e){
            e.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
