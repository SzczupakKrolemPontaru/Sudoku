package sudoku.exceptions;

public class GetElementException extends SudokuException {
    public GetElementException(String message) {
        super(message);
    }

    public GetElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
