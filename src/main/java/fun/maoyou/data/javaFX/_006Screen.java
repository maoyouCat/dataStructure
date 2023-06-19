package fun.maoyou.data.javaFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class _006Screen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Screen primary = Screen.getPrimary();


        Rectangle2D bounds = primary.getBounds();
        System.out.println(bounds);
        System.out.println(primary.getVisualBounds());

        ObservableList<Screen> screens = Screen.getScreens();
        /*一块主屏一块副屏*/
        for (Screen screen : screens) {
            System.out.println(screen);
            System.out.println("DPI: " + screen.getDpi());
        }
        Platform.exit();
    }
}
