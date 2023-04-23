package sudoku;

import org.junit.jupiter.api.Test;
import sudoku.exceptions.GetElementException;
import sudoku.exceptions.GetValueException;
import sudoku.exceptions.SetValueException;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    // Tests if two sudoku boards are different.
    @Test
    void differentBoardsTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoardTest1 = new SudokuBoard(solver);
        int [][] savedBoardTest = sudokuBoardTest1.getBoard();

        sudokuBoardTest1.solveGame();

        assertFalse(Arrays.deepEquals(sudokuBoardTest1.getBoard(), savedBoardTest));
    }

    // Tests if the numbers are in range.
    @Test
    void sudokuRulesTestRange() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertFalse(sudokuBoard.getBoard()[i][j] <= 0 && sudokuBoard.getBoard()[i][j] > 9);
            }
        }
    }

    // Methods tests:

    @Test
    void getTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        assertEquals(sudokuBoard.getBoard()[1][1], sudokuBoard.get(1,1));
    }

    @Test
    void setTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();
        sudokuBoard.set(1,1,0);

        assertEquals(sudokuBoard.get(1,1),0);

        assertThrows(SetValueException.class, () -> sudokuBoard.set(9,1,9));
        assertThrows(SetValueException.class, () -> sudokuBoard.set(-1,1,9));

        assertThrows(SetValueException.class, () -> sudokuBoard.set(1,9,9));
        assertThrows(SetValueException.class, () -> sudokuBoard.set(1,-1,9));

        assertThrows(SetValueException.class, () -> sudokuBoard.set(9,9,9));
        assertThrows(SetValueException.class, () -> sudokuBoard.set(-1,-1,9));

    }

    // checkBoard tests:

    @Test
    void checkBoardTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        try {
            Method privateMethod = SudokuBoard.class.getDeclaredMethod("checkBoard");
            privateMethod.setAccessible(true);
            assertTrue((Boolean)privateMethod.invoke(sudokuBoard));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkBoardIncorrectDueToRepetitionInRowTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        try {
            Method privateMethod = SudokuBoard.class.getDeclaredMethod("checkBoard");
            privateMethod.setAccessible(true);

            assertTrue((Boolean)privateMethod.invoke(sudokuBoard));

            sudokuBoard.set(0,0,sudokuBoard.get(0,0));
            sudokuBoard.set(0,6,sudokuBoard.get(0,0));

            assertFalse((Boolean)privateMethod.invoke(sudokuBoard));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkBoardIncorrectDueToRepetitionInColTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        try {
            Method privateMethod = SudokuBoard.class.getDeclaredMethod("checkBoard");
            privateMethod.setAccessible(true);

            assertTrue((Boolean)privateMethod.invoke(sudokuBoard));

            sudokuBoard.set(0,0,sudokuBoard.get(0,0));
            sudokuBoard.set(6,0,sudokuBoard.get(0,0));

            assertFalse((Boolean)privateMethod.invoke(sudokuBoard));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkBoardIncorrectDueToRepetitionInBoxTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        try {
            Method privateMethod = SudokuBoard.class.getDeclaredMethod("checkBoard");
            privateMethod.setAccessible(true);

            assertTrue((Boolean)privateMethod.invoke(sudokuBoard));

            sudokuBoard.set(1,1,sudokuBoard.get(0,3));
            sudokuBoard.set(2,2,sudokuBoard.get(0,3));

            assertFalse((Boolean)privateMethod.invoke(sudokuBoard));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkBoardIncorrectDueToRepetitionInColBoxTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        try {
            Method privateMethod = SudokuBoard.class.getDeclaredMethod("checkBoard");
            privateMethod.setAccessible(true);

            assertTrue((Boolean)privateMethod.invoke(sudokuBoard));

            sudokuBoard.set(6,0,sudokuBoard.get(0,3));
            sudokuBoard.set(1,1,sudokuBoard.get(0,3));
            sudokuBoard.set(2,2,sudokuBoard.get(0,3));

            assertFalse((Boolean)privateMethod.invoke(sudokuBoard));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkBoardIncorrectDueToRepetitionInRowBoxTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        try {
            Method privateMethod = SudokuBoard.class.getDeclaredMethod("checkBoard");
            privateMethod.setAccessible(true);

            assertTrue((Boolean)privateMethod.invoke(sudokuBoard));

            sudokuBoard.set(1,1,sudokuBoard.get(0,3));
            sudokuBoard.set(2,2,sudokuBoard.get(0,3));
            sudokuBoard.set(0,6,sudokuBoard.get(0,3));

            assertFalse((Boolean)privateMethod.invoke(sudokuBoard));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void checkBoardIncorrectDueToRepetitionInRowColTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);

        sudokuBoard.solveGame();

        try {
            Method privateMethod = SudokuBoard.class.getDeclaredMethod("checkBoard");
            privateMethod.setAccessible(true);

            assertTrue((Boolean)privateMethod.invoke(sudokuBoard));

            sudokuBoard.set(0,0,sudokuBoard.get(0,3));
            sudokuBoard.set(0,6,sudokuBoard.get(0,3));
            sudokuBoard.set(6,0,sudokuBoard.get(0,3));

            assertFalse((Boolean)privateMethod.invoke(sudokuBoard));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // equals tests:

    @Test
    void equalsExactlyTheSameObjectTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();

        assertTrue(sudokuBoard.equals(sudokuBoard));
    }

    @Test
    void equalsTheSameObjectsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(solver);

        assertTrue(sudokuBoard.equals(sudokuBoard2));
    }

    @Test
    void equalsNotTheSameObjectsTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();
        SudokuBoard sudokuBoard2 = new SudokuBoard(solver);
        sudokuBoard2.solveGame();

        assertFalse(sudokuBoard.equals(sudokuBoard2));
    }

    @Test
    void equalsDifferentObjectsTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();

        SudokuColumn column = new SudokuColumn();

        assertFalse(sudokuBoard.equals(column));
    }

    // hashCode tests:

    @Test
    void hashCodeTheSameTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(solver);

        assertEquals(sudokuBoard.hashCode(), sudokuBoard2.hashCode());
    }

    @Test
    void hashCodeDifferentTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();
        SudokuBoard sudokuBoard2 = new SudokuBoard(solver);
        sudokuBoard2.solveGame();

        assertNotEquals(sudokuBoard.hashCode(), sudokuBoard2.hashCode());
    }

    // equals & hashCode tests - integrity:

    @Test
    void equalsTrueHashCodeTrueTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(solver);

        assertTrue(sudokuBoard.equals(sudokuBoard2));
        assertEquals(sudokuBoard.hashCode(), sudokuBoard2.hashCode());
    }

    @Test
    void equalsFalseHashCodeFalseTest() throws SetValueException, GetElementException, GetValueException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();
        SudokuBoard sudokuBoard2 = new SudokuBoard(solver);
        sudokuBoard2.solveGame();

        assertFalse(sudokuBoard.equals(sudokuBoard2));
        assertNotEquals(sudokuBoard.hashCode(), sudokuBoard2.hashCode());
    }

    //toString tests:

    @Test
    void toStringTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        String testString = sudokuBoard.toString();

        assertEquals(testString, sudokuBoard.toString());
    }

    @Test
    public void cloneTest() throws SetValueException, GetElementException, GetValueException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard cloneBoard = (SudokuBoard) sudokuBoard.clone();

        assertTrue(cloneBoard.equals(sudokuBoard) && sudokuBoard.equals(cloneBoard));

        sudokuBoard.solveGame();

        assertFalse(cloneBoard.equals(sudokuBoard) && sudokuBoard.equals(cloneBoard));

    }

    @Test
    public void getColumnExceptionThrow() throws SetValueException, GetElementException, GetValueException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertThrows(GetElementException.class, () -> sudokuBoard.getColumn(9));
        assertThrows(GetElementException.class, () -> sudokuBoard.getColumn(-9));
    }

    @Test
    public void getRowExceptionThrow() throws SetValueException, GetElementException, GetValueException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertThrows(GetElementException.class, () -> sudokuBoard.getRow(9));
        assertThrows(GetElementException.class, () -> sudokuBoard.getRow(-9));
    }

    @Test
    public void getBoxExceptionThrow() throws SetValueException, GetElementException, GetValueException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertThrows(GetElementException.class, () -> sudokuBoard.getBox(9,1));
        assertThrows(GetElementException.class, () -> sudokuBoard.getBox(1,9));

        assertThrows(GetElementException.class, () -> sudokuBoard.getBox(1,-1));
        assertThrows(GetElementException.class, () -> sudokuBoard.getBox(-1,1));

        assertThrows(GetElementException.class, () -> sudokuBoard.getBox(-1,-1));
        assertThrows(GetElementException.class, () -> sudokuBoard.getBox(9,9));
    }

    @Test
    public void getExceptionThrow() throws SetValueException, GetElementException, GetValueException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertThrows(GetValueException.class, () -> sudokuBoard.get(9,1));
        assertThrows(GetValueException.class, () -> sudokuBoard.get(1,9));

        assertThrows(GetValueException.class, () -> sudokuBoard.get(1,-1));
        assertThrows(GetValueException.class, () -> sudokuBoard.get(-1,1));

        assertThrows(GetValueException.class, () -> sudokuBoard.get(-1,-1));
        assertThrows(GetValueException.class, () -> sudokuBoard.get(9,9));
    }
    @Test
    public void setExceptionThrow() throws SetValueException, GetElementException, GetValueException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertThrows(SetValueException.class, () -> sudokuBoard.set(9,1,1));
        assertThrows(SetValueException.class, () -> sudokuBoard.set(1,9,1));

        assertThrows(SetValueException.class, () -> sudokuBoard.set(1,-1,1));
        assertThrows(SetValueException.class, () -> sudokuBoard.set(-1,1,1));

        assertThrows(SetValueException.class, () -> sudokuBoard.set(-1,-1,1));
        assertThrows(SetValueException.class, () -> sudokuBoard.set(9,9,1));
    }

}

