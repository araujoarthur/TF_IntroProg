import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookList {
    private final List<Book> bookList;

    public BookList() {
        bookList = new ArrayList<>();
    }

    // GETTERS
    public List<Book> getBookList() {
        return bookList;
    }

    // METHODS

    public void addBook(Book book) {
        bookList.add(book);
    }

    @Override
    public String toString() {
        return "BookList{" +
                "bookList=" + bookList +
                '}';
    }
}
