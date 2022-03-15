package server.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import server.model.Tune;
import server.Catalog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SearchCommand implements Command {

    private final Reader reader;
    private final Writer writer;
    private final Catalog catalog;

    @Override
    @SneakyThrows
    public void execute() {
        BufferedReader br = new BufferedReader(reader);
        writer.write("Введите название композции для поиска:\n");
        writer.flush();
        String searchStr = br.readLine();
        List<String> composition = catalog.all().stream().map(Tune::toString).filter(c -> c.contains(searchStr)).collect(Collectors.toList());
        if (composition.size() == 0) {
            writer.write("Композиция не найдена.\n");
        } else {
            composition.forEach(c -> {
                try {
                    writer.write(String.format("%s\n", c));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}