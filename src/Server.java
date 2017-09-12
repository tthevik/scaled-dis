import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int PORT = 31337;

    public static void main(String args[]) {

        try {

            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {

                System.out.println("Waiting for client ...");

                Socket connectionSocket = serverSocket.accept();

                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                //Print request headers from client
                String str = ".";
                while (!str.equals("")) {
                    str = inFromClient.readLine();
                    //System.out.println(str);
                }

                //Create output stream (to client)
                PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream());

                //Write response headers
                outToClient.println("HTTP/1.0 200 OK");
                outToClient.println("Content-Type: text/plain");
                outToClient.println("Server: Cowboy");
                outToClient.println("");

                //Content
                Thread.sleep(4000);

                //Flush and Close
                outToClient.flush();
                outToClient.close();

                //Close socket response
                connectionSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
