import java.io.Serializable;

public class Tune implements Serializable {
    private String name;
    private String author;

    Tune (String name, String author) {
        this.name = name;
        this.author = author;
    }

    Tune () {}

    void setName (String name) {
        this.name = name;
    }

    void setAuthor (String author) {
        this.author = author;
    }

    String getName () {
        return name;
    }

    String getAuthor () {
        return author;
    }

    void printInfo () {
        System.out.printf("%s - %s", author, name);
    }

    @Override
    public String toString () {
        return getAuthor() + " - " + getName();
    }
}