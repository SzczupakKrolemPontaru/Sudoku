package sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class SudokuElement implements Cloneable {

    private final List<SudokuField> fields = Arrays.asList(new SudokuField[9]);

    public SudokuElement() {
    }

    // Methods:

    /**
     * Verifies if the sudoku element is correct.
     *
     * @return true if there's no repetitions - the HashSet's size is 9
     */
    public boolean verify() {
        HashSet<Integer> setField = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            setField.add(fields.get(i).getFieldValue());
        }

        return setField.size() == 9;
    }

    //Additional:

    public void setFieldInElement(int loc, SudokuField field) {
        this.fields.set(loc,field);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuElement element = (SudokuElement) o;

        return new EqualsBuilder()
                .append(fields, element.fields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fields)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fields", fields)
                .toString();
    }


}

