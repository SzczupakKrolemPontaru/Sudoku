package sudoku;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.exceptions.GuiBuilderException;

public class GuiStageSetup {
    private static Stage stage;
    private static Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    private static void setStage(Stage stage) {
        GuiStageSetup.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    private static Parent loadFxml(String fxml, ResourceBundle bundle) throws GuiBuilderException {
        try {
            return new FXMLLoader(GuiStageSetup.class.getResource(fxml), bundle).load();
        } catch (IOException e) {
            logger.info(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("Exception"),
                    new GuiBuilderException(BundleManager
                            .getInstance()
                            .getBundle()
                            .getString("GuiBuilderException")));
            throw new GuiBuilderException(BundleManager
                    .getInstance()
                    .getBundle()
                    .getString("GuiBuilderException"));
        }
    }

    public static void buildStage(String filePath, ResourceBundle bundle)
            throws IOException, GuiBuilderException {
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.sizeToScene();
        stage.show();
    }

    public static void buildStage(Stage stage,
                                  String filePath,
                                  String title, ResourceBundle bundle) throws GuiBuilderException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.show();
    }


}
