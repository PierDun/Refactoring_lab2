package client;

import java.io.*;

public class Client {
    private static final int PORT = 8128;
    private static final String HOST = "localhost";

    public static void main (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ClientApp client = new ClientApp(HOST, PORT);
        while (client.isRunning) {
            String str = reader.readLine().trim();
            client.sendMessage(str);
        }
        client.stopConnection();
    }
}