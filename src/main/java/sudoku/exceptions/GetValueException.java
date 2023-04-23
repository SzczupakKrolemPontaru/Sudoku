package sudoku.exceptions;

public class GetValueException extends SudokuException {
    public GetValueException(String message) {
        super(message);
    }

    public GetValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
