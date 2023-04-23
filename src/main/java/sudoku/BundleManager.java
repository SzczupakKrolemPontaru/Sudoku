package sudoku;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleManager {

    private static BundleManager INSTANCE;
    private Locale locale = new Locale("pl");
    private ResourceBundle bundle = ResourceBundle.getBundle("file", locale);

    public BundleManager() {

    }

    public static BundleManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new BundleManager();
        }

        return INSTANCE;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
