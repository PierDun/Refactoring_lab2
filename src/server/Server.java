package server;

import java.io.FileNotFoundException;

public class Server {
    private static final int PORT = 8128;

    public static void main (String[] args) {
        Catalog catalog = new Catalog();
        try {
            catalog.setPath();
        } catch (FileNotFoundException NullPointerException) {
            System.out.println("Не найден файл Jams.txt\n" +
                    "Создайте необходимый файл.");
        }
        catalog.readElements();
        ServerApp server = new ServerApp(catalog);
        server.start(PORT);
    }
}