package sudoku;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import sudoku.exceptions.DaoException;
import sudoku.exceptions.GuiBuilderException;
import sudoku.exceptions.GuiException;

public class GuiMenuController {

    private static Level level;
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private FileSudokuBoardDao fileSudokuBoardDao;
    private static SudokuBoard loadedSudokuBoard = null;


    public static Level getLevel() {
        return level;
    }

    public static SudokuBoard getLoadedSudokuBoard() {
        return loadedSudokuBoard;
    }

    public void pressedEasyButton() throws GuiException {
        try {
            level = Level.EASY;
            GuiStageSetup.buildStage("sudokuGrid.fxml", bundle);
        } catch (IOException | GuiBuilderException e) {
            throw new GuiException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GuiException"));
        }

    }

    public void pressedMediumButton() throws GuiException {
        try {
            level = Level.MEDIUM;
            GuiStageSetup.buildStage("sudokuGrid.fxml", bundle);
        } catch (IOException | GuiBuilderException e) {
            throw new GuiException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GuiException"));
        }
    }

    public void pressedHardButton() throws GuiException {
        try {
            level = Level.HARD;
            GuiStageSetup.buildStage("sudokuGrid.fxml", bundle);
        } catch (IOException | GuiBuilderException e) {
            throw new GuiException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GuiException"));
        }
    }

    public void pressedPolishLanguageButton() throws GuiException {
        try {
            Locale.setDefault(new Locale("pl"));
            bundle = ResourceBundle.getBundle("Language");
            GuiStageSetup.buildStage("choosingLevel.fxml", bundle);
        } catch (IOException | GuiBuilderException e) {
            throw new GuiException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GuiException"));
        }
    }

    public void pressedEnglishLanguageButton() throws GuiException {
        try {
            Locale.setDefault(new Locale("en"));
            bundle = ResourceBundle.getBundle("Language");
            GuiStageSetup.buildStage("choosingLevel.fxml", bundle);
        } catch (IOException | GuiBuilderException e) {
            throw new GuiException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GuiException"));
        }
    }

    public void pressedLoadButton() throws GuiException {
        try {
            String fileName;
            FileChooser fileChooser = new FileChooser();
            fileName = fileChooser.showOpenDialog(GuiStageSetup.getStage()).getName();
            fileSudokuBoardDao = new FileSudokuBoardDao(fileName);
            loadedSudokuBoard = fileSudokuBoardDao.read();
            GuiStageSetup.buildStage("sudokuGrid.fxml", bundle);
        } catch (IOException | GuiBuilderException | DaoException e) {
            throw new GuiException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GuiException"));
        }
    }

    public void pressedAuthorsButton() {
        if (bundle.getString("Button_Authors").equals("Autorzy")) {
            Authors_PL authorsPL = new Authors_PL();
            PopOutWindow popOutWindow = new PopOutWindow();
            popOutWindow.messageBox("Authorzy", (authorsPL.getObject("1")
                            + "\n" + (authorsPL.getObject("2"))),
                    Alert.AlertType.INFORMATION);
        } else {
            Authors_EN authorsEN = new Authors_EN();
            PopOutWindow popOutWindow = new PopOutWindow();
            popOutWindow.messageBox("Authors", (authorsEN.getObject("1")
                            + "\n" + (authorsEN.getObject("2"))),
                    Alert.AlertType.INFORMATION);
        }
    }

}

