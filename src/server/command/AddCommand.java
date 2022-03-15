package server.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import server.model.Tune;
import server.Catalog;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Writer;

@AllArgsConstructor
public class AddCommand implements Command {

    private final Reader reader;
    private final Writer writer;
    private final Catalog catalog;

    @Override
    @SneakyThrows
    public void execute() {
        BufferedReader br = new BufferedReader(reader);
        writer.write("Введите имя автора:\n");
        writer.flush();
        String author = br.readLine();
        writer.write("Введите название композиции:\n");
        writer.flush();
        String name = br.readLine();

        Tune tune = new Tune(author, name);
        if (catalog.add(tune)) {
            writer.write("Композиция добавлена\n");
        } else {
            writer.write("Возникла ошибка при добавлении композиции.\n");
        }
        writer.flush();
    }
}