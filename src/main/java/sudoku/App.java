package sudoku;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;
import sudoku.exceptions.GuiBuilderException;

public class App extends Application {
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    @Override
    public void start(Stage primaryStage) throws IOException, GuiBuilderException {
        GuiStageSetup.buildStage(primaryStage, "choosingLevel.fxml", "Sudoku", bundle);
    }

    public static void main(String[] args) {
        launch();
    }
}
