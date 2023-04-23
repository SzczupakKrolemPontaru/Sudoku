package sudoku;

public class FileSudokuBoardFactory {

    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

}
