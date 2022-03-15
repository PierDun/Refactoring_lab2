package server.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.Writer;

@AllArgsConstructor
public class HelpCommand implements Command {

    private final Writer writer;

    @SneakyThrows
    @Override
    public void execute() {
        writer.write("Используемые команды:\n");
        writer.write("\tВведите одну из комманд:\n");
        writer.write("\t\t\"list\" - показать все элементы каталога\n");
        writer.write("\t\t\"search\" - найти элемент в каталоге\n");
        writer.write("\t\t\"add\" - добавить элемент в каталог\n");
        writer.write("\t\t\"del\" - удалить элемент из каталога\n");
        writer.write("\t\t\"help\" - справка\n");
        writer.write("\t\t\"quit\" - выход\n\n");
        writer.flush();
    }
}