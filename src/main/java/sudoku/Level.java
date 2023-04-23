package sudoku;

public enum Level {
    EASY(1),
    MEDIUM(30),
    HARD(40);

    public final int fields;

    Level(int fields) {
        this.fields = fields;
    }
}