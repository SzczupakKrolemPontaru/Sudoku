package sudoku;

public class SudokuBox extends SudokuElement {

    public Object clone() {
        SudokuBox box = new SudokuBox();
        SudokuField field = new SudokuField();

        for (int i = 1; i <= 9; i++) {
            field.setFieldValue(i);
            box.setFieldInElement(i - 1,field);
        }
        return box;
    }

}
