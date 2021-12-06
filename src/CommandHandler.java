import java.util.Scanner;

public class CommandHandler {
    CommandHandler (TuneCollection catalog) {
        this.collection = catalog;
    }

    TuneCollection collection;

    String executeCommand (String request) {
        Scanner scanner = new Scanner(request);
        String command = "";
        String name;
        String author;
        scanner.useDelimiter(", ");
        command = scanner.next();

        switch (command) {
            case ("list"):
                return collection.toString();
            case ("search"):
                return collection.search(scanner.next());
            case ("add"):
                author = scanner.next();
                name = scanner.next();
                collection.add(author, name);
                return "Композиция " + name + " добавлена в каталог";
            case ("del"):
                author = scanner.next();
                name = scanner.next();
                collection.delete(author, name);
                return "Композиция " + name + " удалена из каталога";
            default:
                return "Неизвестная команда";
        }
    }
}