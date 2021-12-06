import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TuneCollection {
    Scanner input = null;
    private String path = "";
    ArrayList<Tune> catalog = new ArrayList<>();

    void setPath(String path) throws FileNotFoundException, NullPointerException {
        this.path = path;
        File file = new File(path);
        input = new Scanner(file);
    }

    void readElements(Scanner scanner) {
        scanner.useDelimiter(", ");
        Tune curTune;
        while(scanner.hasNextLine()) {
            curTune = new Tune();
            curTune.setAuthor(scanner.next());
            curTune.setName(scanner.next());
            catalog.add(curTune);
        }
    }

    void writeElements() {
        for (Tune tune: catalog) {
            tune.printInfo();
        }
    }

    void save() throws IOException {
        FileWriter output = new FileWriter(path);
        StringBuilder curStr = new StringBuilder();
        int i = 0;
        for (Tune tune: catalog) {
            if (i != catalog.size()) curStr.append(tune.getAuthor()).append(", ").append(tune.getName()).append("\n");
            else curStr.append(tune.getAuthor()).append(", ").append(tune.getName());
            i++;
        }
        output.write(curStr.toString());
        output.close();
    }

    void add (Tune tune) {
        catalog.add(tune);
    }

    void add (String author, String name) {
        catalog.add(new Tune(author, name));
    }

    void delete (Tune tune) {
        catalog.remove(tune);
    }

    void delete (String author, String name) {
        Tune tune;
        while (catalog.listIterator().hasNext()) {
            tune = catalog.listIterator().next();
            if (Objects.equals(tune.getName(), name) && Objects.equals(tune.getAuthor(), author))
                catalog.remove(tune);
        }
    }

    String search (String a) {
        Tune tune;
        while (catalog.listIterator().hasNext()) {
            tune = catalog.listIterator().next();
            if (Objects.equals(tune.getAuthor(), a)) return tune.toString();
            if (Objects.equals(tune.getName(), a)) return tune.toString();
        }
        return "Композиция не найдена";
    }

    @Override
    public String toString () {
        StringBuilder string = new StringBuilder("Все композиции в каталоге\n");
        for (Tune tune: catalog) {
            string.append(tune.toString()).append("\n");
        }
        string.append("----\n");
        return string.toString();
    }
}