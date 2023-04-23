package sudoku;

import org.junit.jupiter.api.Test;
import sudoku.exceptions.SetValueException;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    // equals tests:

    @Test
    void testEqualsSameObj() {
        SudokuField field = new SudokuField();
        assertTrue(field.equals(field));
    }

    @Test
    void testEqualsNull() {
        SudokuField field = new SudokuField();
        assertFalse(field.equals(null));
    }

    @Test
    void equalsNotTheSameObjectsTest() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field1.setFieldValue(1);
        field2.setFieldValue(2);

        assertFalse(field1.equals(field2));
    }

    @Test
    void equalsDifferentObjectsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        SudokuField field1 = new SudokuField();

        assertFalse(field1.equals(sudokuBoard));
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {

        SudokuField sudokuField = new SudokuField();
        SudokuField cloneField = (SudokuField) sudokuField.clone();

        assertTrue(cloneField.equals(sudokuField) && sudokuField.equals(cloneField));

        sudokuField.setFieldValue(5);

        assertFalse(cloneField.equals(sudokuField) && sudokuField.equals(cloneField));
    }
    @Test
    public void compareToTest() throws NullPointerException {
        SudokuField sudokuField = new SudokuField();
        SudokuField sudokuFieldToCompare = new SudokuField();

        sudokuField.setFieldValue(7);
        sudokuFieldToCompare.setFieldValue(7);
        assertEquals(sudokuField.compareTo(sudokuFieldToCompare), 0);


        sudokuFieldToCompare.setFieldValue(3);
        assertTrue(sudokuField.compareTo(sudokuFieldToCompare) > 0);

        sudokuFieldToCompare.setFieldValue(9);
        assertTrue(sudokuField.compareTo(sudokuFieldToCompare) < 0);

        assertThrows(NullPointerException.class, () -> sudokuField.compareTo(null));
    }
}
