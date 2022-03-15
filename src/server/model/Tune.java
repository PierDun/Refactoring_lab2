package server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Tune implements Serializable {
    private String name;
    private String author;

    public Tune () {}

    public void printInfo () {
        System.out.printf("%s - %s\n", author, name);
    }

    @Override
    public String toString () {
        return author + " - " + name;
    }
}