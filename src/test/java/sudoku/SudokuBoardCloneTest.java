package sudoku;

import org.junit.jupiter.api.Test;
import sudoku.exceptions.GetElementException;
import sudoku.exceptions.GetValueException;
import sudoku.exceptions.SetValueException;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardCloneTest {

    @Test
    void cloneBoardTest() throws SetValueException, GetElementException, GetValueException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoardRepository sudokuBoardRepository = new SudokuBoardRepository(sudokuBoard);
        SudokuBoard secondSudokuBoard = sudokuBoardRepository.createInstance();

        assertTrue(secondSudokuBoard.equals(sudokuBoard) && sudokuBoard.equals(secondSudokuBoard));

        sudokuBoard.solveGame();

        assertFalse(secondSudokuBoard.equals(sudokuBoard) && sudokuBoard.equals(secondSudokuBoard));

    }
}