package sudoku;

public class SudokuRow extends SudokuElement {

    public Object clone() {
        SudokuRow row = new SudokuRow();
        SudokuField field = new SudokuField();

        for (int i = 1; i <= 9; i++) {
            field.setFieldValue(i);
            row.setFieldInElement(i - 1,field);
        }
        return row;
    }

}
