import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Library {
    private BookList bookList;
    private int bookCount;

    public Library() {
        loadLibrary();
    }

    // GETTERS
    public int getBookCount() {
        return bookCount;
    }

    /* Estático porque só existe uma Library mesmo. */
    private static Path getLibraryPath() {
        Path resDirectory = Paths.get("src", "main", "resources");
        return resDirectory.resolve(Constants.LIBRARY_FILE_NAME);
    }

    // SETTERS
    //...


    // METHODS
    private void createLibrary() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Garantindo que o diretorio existe.
            Files.createDirectories(Library.getLibraryPath().getParent());

            BookList bookList = new BookList();
            objectMapper.writeValue(Library.getLibraryPath().toFile(), bookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean doesLibraryExist() {
        File file = new File(Library.getLibraryPath().toString());
        return file.exists();
    }

    private void loadLibrary() {
        if (!doesLibraryExist()) {
            createLibrary();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Path filePath = getLibraryPath().getParent().resolve(Constants.LIBRARY_FILE_NAME);

            bookList = objectMapper.readValue(filePath.toFile(), BookList.class);
            bookCount = bookList.getBookList().size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLibrary() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(Library.getLibraryPath().toFile(), bookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBook(String fileName){

        Book book = new Book();
        book.setName(fileName);
        book.setPath(Library.getLibraryPath().getParent().resolve("Books").resolve(fileName+".txt"));
        this.bookList.addBook(book);
        this.bookCount = this.bookList.getBookList().size();
        saveLibrary();
    }

    public void removeBook(String fileName) {
        for (int i = 0; i < bookList.getBookList().size(); i++) {
            if (bookList.getBookList().get(i).getName().equals(fileName)) {
                bookList.getBookList().remove(i);
                bookCount = bookList.getBookList().size();
                saveLibrary();
                return;
            }
        }
    }

    public Book getBook(String fileName) {
        for (int i = 0; i < bookList.getBookList().size(); i++) {
            if (bookList.getBookList().get(i).getName().equals(fileName)) {
                return bookList.getBookList().get(i);
            }
        }
        return null;
    }

    public void printLibrary(){
        System.out.println("▓▓▓▓▓▓ LIVROS ▓▓▓▓▓▓\n");
        for (int i = 0; i < bookList.getBookList().size(); i++) {
            System.out.printf("%d. %s%n", i + 1, bookList.getBookList().get(i).getName());
        }
        System.out.println();
        System.out.println();
    }

    public String toString() {
        return "Library{" +
                "bookList=" + bookList +
                ", bookCount=" + bookCount +
                '}';
    }
}
