package sudoku;

public class SudokuBoardRepository implements Repository<SudokuBoard> {

    private final SudokuBoard sudokuBoard;

    public SudokuBoardRepository(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    @Override
    public SudokuBoard createInstance() {
        return sudokuBoard.clone();
    }
}
