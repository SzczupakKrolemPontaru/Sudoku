package sudoku.exceptions;

public class GuiBuilderException extends SudokuException {
    public GuiBuilderException(String message) {
        super(message);
    }

    public GuiBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
