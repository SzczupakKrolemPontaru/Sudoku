package sudoku;

import java.io.Serializable;
import sudoku.exceptions.GetValueException;
import sudoku.exceptions.SetValueException;

interface SudokuSolver extends Serializable {
    void solve(SudokuBoard board) throws SetValueException, GetValueException;
}
