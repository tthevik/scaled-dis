
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int PORT = 31337;

    public static void main(String args[]) {

        try {

            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {

                System.out.println("Wating for clients on port " + PORT);

                //Hang while-loop and wait for socket request
                Socket remoteSocket = serverSocket.accept();

                Runnable responseHandler = new ResponseHandler(remoteSocket);
                new Thread(responseHandler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
