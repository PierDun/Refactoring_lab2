package server.thread;

import lombok.SneakyThrows;
import server.Catalog;
import server.CommandHandler;

import java.io.*;
import java.net.Socket;

public class ComThread implements Runnable{
    private final Socket socket;
    private final Reader reader;
    private final Writer writer;
    private final Catalog catalog;

    @SneakyThrows
    public ComThread(Socket socket, Catalog catalog) {
        this.socket = socket;
        this.catalog = catalog;
        writer = new OutputStreamWriter(socket.getOutputStream());
        reader = new InputStreamReader(socket.getInputStream());
    }

    @Override
    public void run() {
        CommandHandler commandHandler = new CommandHandler(reader, writer, catalog);
        try {
            commandHandler.executeCommand();
        } catch (IOException e) {
            System.out.println("Возникли проблемы при обработке комманды");
        }
        stop();
    }

    @SneakyThrows
    public void stop() {
        reader.close();
        writer.close();
        socket.close();
    }
}