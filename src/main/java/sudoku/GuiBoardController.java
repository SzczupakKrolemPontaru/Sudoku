package sudoku;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import sudoku.exceptions.DaoException;
import sudoku.exceptions.GetElementException;
import sudoku.exceptions.GetValueException;
import sudoku.exceptions.GuiBuilderException;
import sudoku.exceptions.SetValueException;

public class GuiBoardController {
    @FXML
    private GridPane sudokuGrid = new GridPane();
    private SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard sudokuBoard = new SudokuBoard(solver);
    private SudokuBoard originalBoard;
    private SudokuBoard loadedSudokuBoard;
    private LevelEmptyFields levelEmptyFields = new LevelEmptyFields();
    private FileSudokuBoardDao fileSudokuBoardDao;
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public void initialize() throws SetValueException, GetElementException, GetValueException {
        if (GuiMenuController.getLoadedSudokuBoard() != null) {
            loadedSudokuBoard = GuiMenuController.getLoadedSudokuBoard();
            originalBoard = loadedSudokuBoard.clone();
            sudokuBoard = loadedSudokuBoard;
        } else {
            sudokuBoard.solveGame();
            originalBoard = sudokuBoard.clone();
            levelEmptyFields.readyBoard(sudokuBoard, GuiMenuController.getLevel());
        }
        fill();
    }

    public void fill() throws GetValueException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setText("");
                if (sudokuBoard.get(i,j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));
                sudokuGrid.add(textField, j, i);
            }
        }
    }

    public void pressedBackButton() throws IOException, GuiBuilderException {
        GuiStageSetup.buildStage("choosingLevel.fxml", bundle);
    }

    private void updateBoard() throws SetValueException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String sudokuFieldValue = ((TextField)
                        sudokuGrid.getChildren().get((i * 9) + j)).getText();
                if (!sudokuFieldValue.equals("")) {
                    sudokuBoard.set(i,j, Integer.parseInt(sudokuFieldValue));
                } else {
                    sudokuBoard.set(i,j,0);
                }
            }
        }
    }

    private boolean isBoardValid() {
        boolean isValid = true;
        for (int i = 0; i < 81; i++) {
            String sudokuFieldValue = ((TextField)
                    sudokuGrid.getChildren().get(i)).getText();
            if (sudokuFieldValue.equals("") || !(sudokuFieldValue.matches("[1-9]"))) {
                isValid = false;
            }
        }
        return isValid;
    }

    public void pressedCheckButton() throws SetValueException {
        if (!isBoardValid()) {
            PopOutWindow popOutWindow = new PopOutWindow();
            popOutWindow.messageBox("", bundle.getString("Board_Invalid"),
                    Alert.AlertType.INFORMATION);
            return;
        }
        updateBoard();
        PopOutWindow popOutWindow = new PopOutWindow();
        if (sudokuBoard.equals(originalBoard)) {
            popOutWindow.messageBox("", bundle.getString("Board_Check_Correct"),
                    Alert.AlertType.INFORMATION);
        } else {
            popOutWindow.messageBox("", bundle.getString("Board_Check_Incorrect"),
                    Alert.AlertType.INFORMATION);
        }
    }

    public void pressedSaveButton() throws SetValueException, DaoException {
        updateBoard();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                "Text Files", "*.txt"));
        try {
            File file = fileChooser.showSaveDialog(GuiStageSetup.getStage());
            fileSudokuBoardDao = new FileSudokuBoardDao(file.getAbsolutePath());
            fileSudokuBoardDao.write(sudokuBoard);
        } catch (NullPointerException e) {
            throw new RuntimeException();
        }
    }
}
