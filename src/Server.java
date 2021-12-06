import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8128;

    public static void main (String[] args) {
        TuneCollection collection = new TuneCollection();
        ServerSocket serverSocket;
        Socket socket;

        Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Сохранение...");
            try {
                collection.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        try {
            collection.setPath("Jams.txt");
            collection.readElements(collection.input);
            collection.writeElements();
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Файл не найден.");
        }

        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("\nОжидание подключения...");
                socket = serverSocket.accept();
                new Thread(new ComThread(collection, socket)).start();
                System.out.println("Подключился клиент: " + socket.getInetAddress());
            }
        } catch (IOException e) {
            System.out.println("Не удалось создать сокет");
        }
    }
}