package server;

import lombok.SneakyThrows;
import server.thread.ComThread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerApp {
    private ServerSocket serverSocket;
    private final Catalog tuneCatalog;
    private final ArrayList<ComThread> clientHandlers = new ArrayList<>();
    private boolean isRunning = true;

    public ServerApp(Catalog tuneCatalog) {
        this.tuneCatalog = tuneCatalog;
    }

    @SneakyThrows
    public void start(int port) {
        serverSocket = new ServerSocket(port);
        System.out.println("Сервер работает");
        while (isRunning) {
            serveClient(serverSocket.accept());
            System.out.println("Соединение установлено");
        }
    }

    private void serveClient(Socket clientSocket) {
        ComThread clientHandler = new ComThread(clientSocket, tuneCatalog);
        clientHandlers.add(clientHandler);
        new Thread(clientHandler).start();
    }

    @SneakyThrows
    public void stop() {
        tuneCatalog.save();
        isRunning = false;
        clientHandlers.forEach(ComThread::stop);
        serverSocket.close();
    }
}