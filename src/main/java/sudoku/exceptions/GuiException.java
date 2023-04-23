package sudoku.exceptions;

public class GuiException extends SudokuException {
    public GuiException(String message) {
        super(message);
    }

    public GuiException(String message, Throwable cause) {
        super(message, cause);
    }
}
