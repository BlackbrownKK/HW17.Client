import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientTest {

    private ServerSocket serverSocket;
    private DataOutputStream dataOutputStream;

    @Before
    public void server() throws IOException {
        serverSocket = new ServerSocket(8085); // created a new client/server socket connection
        new Thread(() -> {
            try {
                Socket socket = serverSocket.accept(); //  is used to accept the incoming request to the socket
                dataOutputStream = new DataOutputStream(socket.getOutputStream()); // creared new dataOutputStream
                dataOutputStream.writeUTF("Odessa"); // writes a string to output stream
            } catch (IOException e) {
                e.printStackTrace(); // to mark where mistake was
            }
        }).start(); //  starting
    }
    @Test
    public void connection() throws IOException {
        Socket client = new Socket("localhost",8085);  // created a new client to connect by Server
        DataInputStream clientIn = new DataInputStream(client.getInputStream()); // creared new dataInputStream
        Assert.assertEquals("Odessa", clientIn.readUTF());  // assert equals string
        client.close();
    }

    @After
    public void Close() throws IOException {
        serverSocket.close();
    }
}
