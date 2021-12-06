import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ComThread extends Thread{
    ComThread(TuneCollection collection, Socket socket) {
        this.socket = socket;
        handler = new CommandHandler(collection);
    }

    private final Socket socket;
    private final CommandHandler handler;

    @Override
    public void run() {
        try {
            if(!socket.isClosed()) {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Сервер работает...");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    String line = in.readUTF();
                    out.writeUTF(handler.executeCommand(line));
                } catch (EOFException | StringIndexOutOfBoundsException | SocketException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println("Клиент" + socket.getInetAddress() + "отключился");
        }
    }
}
