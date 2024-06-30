import java.nio.file.Path;

public class Book {
    private String bookName;
    private Path bookPath;

    // GETTERS
    public String getName() {
        return bookName;
    }

    public Path getPath() {
        return bookPath;
    }

    // SETTERS
    public void setName(String bookName) {
        this.bookName = bookName;
    }

    public void setPath(Path bookPath) {
        this.bookPath = bookPath;
    }

    // METHODS

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookPath='" + bookPath + '\'' +
                '}';
    }
}
