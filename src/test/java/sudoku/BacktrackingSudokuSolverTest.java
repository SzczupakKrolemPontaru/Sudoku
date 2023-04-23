package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void backtrackingSudokuSolverEqualsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuSolver solver1 = new BacktrackingSudokuSolver();

        SudokuBoard board = new SudokuBoard(solver);

        assertFalse(solver.equals(null));
        assertFalse(solver.equals(board));
        assertTrue(solver.equals(solver1));
    }
}