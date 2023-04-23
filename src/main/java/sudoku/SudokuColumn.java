package sudoku;

public class SudokuColumn extends SudokuElement {

    public Object clone() {
        SudokuColumn column = new SudokuColumn();
        SudokuField field = new SudokuField();

        for (int i = 1; i <= 9; i++) {
            field.setFieldValue(i);
            column.setFieldInElement(i - 1,field);
        }
        return column;
    }

}
