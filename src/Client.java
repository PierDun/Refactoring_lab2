import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8128;
    private static final String HOST = "localhost";

    public static void main (String[] args) {
        Socket socket;
        DataOutputStream out = null;
        DataInputStream in = null;

        try {
            socket = new Socket(HOST, PORT);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу");
        }

        System.out.println("Введите одну из комманд:\n" +
                "   \"list\" чтобы показать все элементы каталога\n" +
                "   \"search\" чтобы найти элемент в каталоге\n" +
                "   \"add\" чтобы добавить элемент в каталог\n" +
                "   \"del\" чтобы удалить элемент из каталога\n" +
                "   \"quit\" для выхода\n");

        Scanner a = new Scanner(System.in);
        String author = "";
        String name = "";
        StringBuilder request = new StringBuilder();

        while (true) {
            System.out.println("Введите комманду");
            String command = a.nextLine();
            switch (command) {
                case ("quit"):
                    System.exit(0);
                    break;
                default:
                    request.append(command);
                    if (!command.equals("list")) {
                        System.out.println("Введите имя автора");
                        author = a.nextLine();
                        request.append(", ").append(author);
                        System.out.println("Введите название композиции");
                        request.append(", ").append(name);
                        name = a.nextLine();
                    }

                    try {
                        out.writeUTF(request.toString());
                        out.flush();
                        System.out.println(in.readUTF());

                    } catch (IOException e) {
                        System.out.println("Не удалось связаться с сервером");
                    }
                    break;
            }
        }
    }
}