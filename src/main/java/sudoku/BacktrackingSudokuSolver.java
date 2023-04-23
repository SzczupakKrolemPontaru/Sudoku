package sudoku;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sudoku.exceptions.GetValueException;
import sudoku.exceptions.SetValueException;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {
    @Override
    public void solve(SudokuBoard board) throws SetValueException, GetValueException {
        fillBoxs(board);
        fillEmptyCells(board);
    }

    /**
     * Fills three 3x3 boxes diagonally (from upper left to lower right) with random numbers.
     * Creates a "base" to solve the rest of the board.
     */
    private void fillBoxs(SudokuBoard board) throws SetValueException, GetValueException {
        int randomNumber;
        for (int z = 0; z < 9; z += 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int row = i + z;
                    int col = j + z;
                    do {
                        randomNumber = (int)(Math.random() * 9 + 1);
                    } while (!checkAssignment(board, randomNumber, row, col));
                    board.set(row,col,randomNumber);
                }
            }
        }
    }

    /**
     * Fills the remaining empty cells recursively, one by one.
     * Uses backtracking if a wrong number is assigned to a cell.
     * @return false if no empty cells are found
     */
    private boolean fillEmptyCells(SudokuBoard board) throws SetValueException, GetValueException {
        int emptyRow = 0;
        int emptyCol = 0;
        if (checkBoardSolved(board)) {
            return true;
        }

        // Finds an empty cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i,j) == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
        }

        // Tries assigning a number to a cell.
        // If nothing fits, zeroes the cell and goes back to the previous one.
        for (int i = 1; i <= 9; i++) {
            if (checkAssignment(board, i, emptyRow, emptyCol)) {
                board.set(emptyRow,emptyCol,i);
                if (fillEmptyCells(board)) {
                    return true;
                }
                board.set(emptyRow,emptyCol,0);
            }
        }

        return false;
    }

    /**
     * Checks if the board is already solved (looks for zeroes).
     * @param board sudoku board
     * @return true if solved
     */
    private boolean checkBoardSolved(SudokuBoard board) throws GetValueException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i,j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if it's OK to assign a number to a cell.
     * @param board sudoku board
     * @param num number to assign
     * @param row row of the board
     * @param col column of the board
     * @return true if the assignment is possible
     */
    private boolean checkAssignment(SudokuBoard board,
                                    int num,
                                    int row,
                                    int col) throws GetValueException {
        // Checks in a...
        // ... row
        for (int i = 0; i < 9; i++) {
            if (board.get(row,i) == num) {
                return false;
            }
        }

        // ... column
        for (int i = 0; i < 9; i++) {
            if (board.get(i, col) == num) {
                return false;
            }
        }

        // ... 3x3 Box
        int sqrt = 3;
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;

        for (int i = 0; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                if (board.get(i + rowStart,j + colStart) == num) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return new EqualsBuilder()
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
