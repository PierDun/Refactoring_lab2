package server.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import server.Catalog;
import server.model.Tune;

import java.io.Writer;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ListCommand implements Command {

    private final Writer writer;
    private final Catalog catalog;

    @SneakyThrows
    @Override
    public void execute() {
        writer.write("Все композиции в каталоге:\n");
        String tunes = catalog.all().stream()
                .map(Tune::toString)
                .collect(Collectors.joining("\n"));
        writer.write(tunes);
        writer.write("\n");
        writer.flush();
    }
}
