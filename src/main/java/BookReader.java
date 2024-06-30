import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class BookReader {
    private final Book book;
    private int currentPosition;
    private final int lastPosition;
    private ArrayList<String> positions;

    public BookReader(Book openedBook) {
        this.book = openedBook;
        this.currentPosition = 0;

        dividePositions();

        this.lastPosition = this.positions.size() - 1;
    }

    // GETTERS
    public Book getBook() {
        return book;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    // SETTER
    private void setCurrentPosition(int currentPosition) {
        if (currentPosition >= 0 && currentPosition <= this.lastPosition) {
            this.currentPosition = currentPosition;
        }
        this.currentPosition = currentPosition;
    }

    // METHODS

    public void dividePositions() {
        this.positions = new ArrayList<>();
        String filePath = this.book.getPath().toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder position = new StringBuilder();
            int linesInPosition = 0;

            while ((line = reader.readLine()) != null) {
                if (linesInPosition == 10) {
                    this.positions.add(position.toString());
                    position = new StringBuilder();
                    linesInPosition = 0;
                }
                position.append(line).append("\n");
                linesInPosition++;
            }

            if (this.positions.getLast().contentEquals(position)) {
                this.positions.add(position.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextPosition() {
        if (this.currentPosition < this.lastPosition) {
            setCurrentPosition(this.currentPosition + 1);
        }
    }

    public void previousPosition()
    {
        setCurrentPosition(this.currentPosition - 1);
    }

    public void sendCursorToPosition(int pos) {
        setCurrentPosition(pos);
    }

    public void printCurrentPosition() {
        System.out.println("Posição atual: " + this.currentPosition + "/" + this.lastPosition);
        System.out.println(this.positions.get(this.currentPosition));
    }
}
