package sudoku.exceptions;

public class ElementException extends SudokuException {
    public ElementException(String message) {
        super(message);
    }

    public ElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
