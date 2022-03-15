package server;

import server.model.Tune;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Catalog {
    Scanner input = null;
    private String path = "";
    CopyOnWriteArrayList<Tune> catalog = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<Tune> all() {
        return catalog;
    }

    public void setPath() throws FileNotFoundException, NullPointerException {
        this.path = "Jams.txt";
        File file = new File("Jams.txt");
        input = new Scanner(file);
    }

    public void readElements() {
        String line;
        Tune curTune;
        while(input.hasNextLine()) {
            line = input.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(", ");
            curTune = new Tune();
            curTune.setAuthor(lineScanner.next());
            curTune.setName(lineScanner.next());
            catalog.add(curTune);
        }
    }

    public void save() throws IOException {
        FileWriter output = new FileWriter(path);
        StringBuilder curStr = new StringBuilder();
        int i = 0;
        for (Tune tune: catalog) {
            if (i < catalog.size()) curStr.append(tune.getAuthor()).append(", ").append(tune.getName()).append("\n");
            else curStr.append(tune.getAuthor()).append(", ").append(tune.getName());
            i++;
        }
        output.write(curStr.toString());
        output.close();
    }

    public boolean add (Tune tune) { return catalog.add(tune); }

    public void add (String author, String name) {
        catalog.add(new Tune(author, name));
    }

    public boolean delete (Tune tune) {
        return catalog.remove(tune);
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