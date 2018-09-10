import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client {

    private static int PORT = 31337;

    public static void main(String args[]) throws IOException {

        while(true) {
            Socket clientSocket = new Socket("localhost", PORT);

            try {

                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));


                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.println("\nClient Input:");
                String sentence = inFromUser.readLine();

                outToServer.writeBytes(sentence + "\n");

                String modifiedSentence = inFromServer.readLine();
                System.out.println("Received from server:" + modifiedSentence);

            } finally {
                clientSocket.close();
            }
        }


    }

}