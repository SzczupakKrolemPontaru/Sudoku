package sudoku.exceptions;

public class SetValueException extends SudokuException {
    public SetValueException(String message) {
        super(message);
    }

    public SetValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
