package sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.exceptions.GetElementException;
import sudoku.exceptions.GetValueException;
import sudoku.exceptions.SetValueException;

public class SudokuBoard implements Serializable, Cloneable {
    
    private final List<List<SudokuField>> board;
    private final SudokuSolver solver;
    private static Logger logger = LoggerFactory.getLogger(SudokuBoard.class);

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;

        board = new ArrayList<>(9);
        for (int i = 0; i < 9; ++i) {
            board.add(new ArrayList<>(9));
            for (int j = 0; j < 9; j++) {
                board.get(i).add(j, new SudokuField());
            }
        }
    }

    // Methods:

    public int get(int x, int y) throws GetValueException {
        if (x > 8 || x < 0 || y > 8 || y < 0) {
            logger.info(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("Exception"),
                    new GetValueException(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("GetValueException")));
            throw new GetValueException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GetValueException"));
        }
        return board.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int value) throws SetValueException {
        if (x > 8 || x < 0 || y > 8 || y < 0) {
            logger.info(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("Exception"),
                    new SetValueException(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("SetValueException")));
            throw new SetValueException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("SetValueException"));
        }
        board.get(x).get(y).setFieldValue(value);
    }

    /**
     * Check's if the board is correctly solved.
     * @return true if the board is correct
     */
    private boolean checkBoard() throws GetElementException {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                SudokuBox box = getBox(i, j);
                SudokuColumn col = getColumn(i);
                SudokuRow row = getRow(i);

                if (!box.verify() || !row.verify() || !col.verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuRow getRow(int y) throws GetElementException {
        if (y > 8 || y < 0) {
            logger.info(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("Exception"),
                    new GetElementException(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("GetRowException")));
            throw new GetElementException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GetRowException"));
        }
        SudokuRow row = new SudokuRow();
        for (int i = 0; i < 9; i++) {
            SudokuField field = new SudokuField();
            field.setFieldValue(board.get(i).get(y).getFieldValue());
            row.setFieldInElement(i,field);
        }
        return row;
    }

    /**
     * Gets SudokuColumn.
     * @param x SudokuColumn index
     * @return SudokuColumn
     * @throws GetElementException custom exception for SudokuElement
     */
    public SudokuColumn getColumn(int x) throws GetElementException {
        if (x > 8 || x < 0) {
            logger.info(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("Exception"),
                    new GetElementException(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("GetColumnException")));
            throw new GetElementException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GetColumnException"));
        }
        SudokuColumn col = new SudokuColumn();
        for (int i = 0; i < 9; i++) {
            SudokuField field = new SudokuField();
            field.setFieldValue(board.get(x).get(i).getFieldValue());
            col.setFieldInElement(i,field);
        }
        return col;
    }

    public SudokuBox getBox(int x, int y) throws GetElementException {
        if (x > 8 || x < 0 || y > 8 || y < 0) {
            logger.info(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("Exception"),
                    new GetElementException(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("GetBoxException")));
            throw new GetElementException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GetBoxException"));
        }
        SudokuBox box = new SudokuBox();
        int rowStart = x - x % 3;
        int colStart = y - y % 3;
        int index = 0;
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                SudokuField field = new SudokuField();
                field.setFieldValue(board.get(i).get(j).getFieldValue());
                box.setFieldInElement(index,field);
                index++;
            }
        }
        return box;
    }

    /**
     * Solves the sudoku with the chosen algorithm.
     * @throws SetValueException custom exception
     * @throws GetElementException custom exception
     * @throws GetValueException custom exception
     */
    public void solveGame() throws SetValueException, GetElementException, GetValueException {
        solver.solve(this);
        checkBoard();
    }

    //Additional methods:

    public int[][] getBoard() {
        int[][] copiedBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copiedBoard[i][j] = board.get(i).get(j).getFieldValue();
            }
        }
        return copiedBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;
        return new EqualsBuilder()
                .append(getBoard(), that.getBoard())
                .append(solver, that.solver)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getBoard())
                .append(solver)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("board", board)
                .append("solver", solver)
                .toString();
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard clonedSudokuBoard = new SudokuBoard(this.solver);
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    clonedSudokuBoard.set(i, j,
                            Collections.unmodifiableList(board).get(i).get(j).getFieldValue());
                }

            }
        } catch (SetValueException e) {
            throw new RuntimeException(e);
        }

        return clonedSudokuBoard;
    }
}
