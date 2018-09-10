import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static int PORT = 31337;
    private static ArrayList<String> cache = new ArrayList<>();

    private static String getElement(String element) {
        for (String s : cache) {
            //implement your string comparison method.

        }
        // This loop is just to simulate time passing.
        // It could be a really complex call to a remote database.
        for (double i = 0; i < 999999999; i++) {}


        //do something here as well


        return element;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                System.out.println("Waiting for client ...");

                Socket socket = listener.accept();
                try {
                    BufferedReader inFromClient =
                            new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    DataOutputStream outToClient =
                            new DataOutputStream(socket.getOutputStream());

                    String clientSentence = inFromClient.readLine();
                    System.out.println("Received: " + clientSentence);

                    //stopwatch
                    long startTime = System.nanoTime();

                    //Capitalize string received from server and return to client as response
                    String serverResponse = getElement(clientSentence);

                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime)/1000;

                    outToClient.writeBytes("Getting the " + serverResponse + " element took " + duration + "ms ");

                } finally {
                    socket.close();
                }

            }
        }
        finally {
            listener.close();
        }
    }
}
