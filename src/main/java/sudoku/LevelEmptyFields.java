package sudoku;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import sudoku.exceptions.SetValueException;

public class LevelEmptyFields {

    private Set<Point> cords = new HashSet<Point>();

    public SudokuBoard readyBoard(SudokuBoard board, Level level) throws SetValueException {
        Random random = new Random();

        while (cords.size() != level.fields) {
            Point point = new Point();
            point.x = random.nextInt(9);
            point.y = random.nextInt(9);
            cords.add(point);
        }

        for (Point point : cords) {
            board.set(point.x, point.y, 0);
        }

        return board;
    }

}
