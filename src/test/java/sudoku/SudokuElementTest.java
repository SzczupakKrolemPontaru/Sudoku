package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.exceptions.GetElementException;
import sudoku.exceptions.GetValueException;
import sudoku.exceptions.SetValueException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SudokuElementTest {

    @Test
    void sudokuRulesTestGetRow() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        for (int i = 0; i < 9; i++) {
            assertTrue(sudokuBoard.getRow(i).verify());
        }
    }

    // Tests if a value is repeated in a column
    @Test
    void sudokuRulesTestGetColumn() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        for (int i = 0; i < 9; i++) {
            assertTrue(sudokuBoard.getColumn(i).verify());
        }
    }

    // Tests if a value is repeated in a 3x3 box
    @Test
    void sudokuRulesTestBox() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(sudokuBoard.getBox(i,j).verify());
            }
        }
    }

    // verify tests:

    @Test
    void sudokuElementVerifyIncorrectDueToRepetitionInRowTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();
        sudokuBoard.set(0,0,sudokuBoard.get(3,0));
        sudokuBoard.set(0,3,sudokuBoard.get(3,0));
        sudokuBoard.set(0,6,sudokuBoard.get(3,0));
        assertFalse(sudokuBoard.getRow(0).verify());

    }

    @Test
    void sudokuElementVerifyIncorrectDueToRepetitionInColTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        sudokuBoard.set(0,0,sudokuBoard.get(3,0));
        sudokuBoard.set(3,0,sudokuBoard.get(3,0));
        sudokuBoard.set(6,0,sudokuBoard.get(3,0));
        assertFalse(sudokuBoard.getColumn(0).verify());

    }

    @Test
    void sudokuElementVerifyIncorrectDueToRepetitionInBoxTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        sudokuBoard.set(0,0,sudokuBoard.get(3,0));
        sudokuBoard.set(1,1,sudokuBoard.get(3,0));
        sudokuBoard.set(2,2,sudokuBoard.get(3,0));
        assertFalse(sudokuBoard.getBox(0,0).verify());
    }

    // equals tests:

    @Test
    void testEqualsSameObj() {
        SudokuElement column = new SudokuColumn();
        assertTrue(column.equals(column));
    }

    @Test
    void testEqualsNull() {
        SudokuElement column = new SudokuColumn();
        assertFalse(column.equals(null));
    }

    @Test
    void equalsTheSameObjectsTest() throws GetElementException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        assertTrue(sudokuBoard.getColumn(0)
                .equals(sudokuBoard.getColumn(1)));
    }

    @Test
    void equalsNotTheSameObjectsTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();

        assertFalse(sudokuBoard.getColumn(0)
                .equals(sudokuBoard.getColumn(1)));

    }

    @Test
    void equalsDifferentObjectsTest() throws GetElementException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        assertFalse(sudokuBoard.getColumn(0)
                .equals(sudokuBoard.getRow(0)));
        assertFalse(sudokuBoard.getColumn(0).equals(sudokuBoard));
    }

    // hashCode tests:

    @Test
    void hashCodeTheSameTest() throws GetElementException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        assertEquals(sudokuBoard.getColumn(0).hashCode(),
                sudokuBoard.getColumn(1).hashCode());
    }

    @Test
    void hashCodeDifferentTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();

        assertNotEquals(sudokuBoard.getColumn(0).hashCode(),
                sudokuBoard.getColumn(1).hashCode());
    }

    // equals & hashCode tests - integrity:

    @Test
    void equalsTrueHashCodeMustBeTrueTest() throws GetElementException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        assertTrue(sudokuBoard.getColumn(0)
                .equals(sudokuBoard.getColumn(1)));
        assertEquals(sudokuBoard.getColumn(0).hashCode(),
                sudokuBoard.getColumn(1).hashCode());
    }

    @Test
    void equalsFalseHashCodeMayBeTrueTest() throws GetElementException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        assertFalse(sudokuBoard.getColumn(0)
                .equals(sudokuBoard.getRow(0)));
        assertEquals(sudokuBoard.getColumn(0).hashCode(),
                sudokuBoard.getRow(0).hashCode());
    }

    @Test
    void equalsFalseHashCodeMustBeFalseTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();

        assertFalse(sudokuBoard.getColumn(0)
                .equals(sudokuBoard.getColumn(1)));
        assertNotEquals(sudokuBoard.getColumn(0).hashCode(),
                sudokuBoard.getColumn(1).hashCode());
    }

    @Test
    void hashCodeFalseEqualsMustBeFalseTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();

        assertNotEquals(sudokuBoard.getColumn(0).hashCode(),
                sudokuBoard.getColumn(1).hashCode());
        assertFalse(sudokuBoard.getColumn(0)
                .equals(sudokuBoard.getColumn(1)));
    }

    @Test
    void hashCodeTrueEqualsMayBeTrueTest() throws GetElementException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        assertEquals(sudokuBoard.getColumn(1).hashCode(),
                sudokuBoard.getColumn(2).hashCode());
        assertTrue(sudokuBoard.getColumn(1)
                .equals(sudokuBoard.getColumn(2)));
    }

    @Test
    void hashCodeTrueEqualsMayBeFalseTest() throws GetElementException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        assertEquals(sudokuBoard.getColumn(0).hashCode(),
                sudokuBoard.getRow(0).hashCode());
        assertFalse(sudokuBoard.getColumn(0)
                .equals(sudokuBoard.getRow(0)));
    }

    //toString tests:

    @Test
    void toStringTest() {
        SudokuElement column = new SudokuColumn();
        String testString = column.toString();
        assertEquals(testString, column.toString());
    }

    @Test
    void cloneTest() {
        SudokuRow sudokuRow = new SudokuRow();
        SudokuColumn sudokuColumn = new SudokuColumn();
        SudokuBox sudokuBox  = new SudokuBox();
        SudokuField field = new SudokuField();
        for (int i = 1; i <= 9; i++){
            field.setFieldValue(i);
            sudokuRow.setFieldInElement(i - 1, field);
            sudokuColumn.setFieldInElement(i - 1, field);
            sudokuBox.setFieldInElement(i - 1, field);
        }
        SudokuRow cloneSudokuRow = (SudokuRow) sudokuRow.clone();
        SudokuColumn cloneSudokuColumn = (SudokuColumn) sudokuColumn.clone();
        SudokuBox cloneSudokuBox = (SudokuBox) sudokuBox.clone();

        assertTrue(cloneSudokuRow.equals(sudokuRow) && sudokuRow.equals(cloneSudokuRow));
        assertTrue(cloneSudokuColumn.equals(sudokuColumn) && sudokuColumn.equals(cloneSudokuColumn));
        assertTrue(cloneSudokuBox.equals(sudokuBox) && sudokuBox.equals(cloneSudokuBox));

        field.setFieldValue(9);
        sudokuRow.setFieldInElement(0, field);
        sudokuColumn.setFieldInElement(0, field);
        sudokuBox.setFieldInElement(0, field);

    }



}
