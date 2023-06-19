package fun.maoyou.data.javaFX;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class _004Modality extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Stage> stages = new ArrayList<>();
        Stage s1 = new Stage();
        s1.initStyle(StageStyle.DECORATED);
        s1.setTitle("StageStyle.DECORATED");

/*
        Stage s2 = new Stage();
        s2.initStyle(StageStyle.TRANSPARENT);
        s2.setTitle("StageStyle.TRANSPARENT");
        Stage s3 = new Stage();
        s3.initStyle(StageStyle.UNDECORATED);
        s3.setTitle("StageStyle.UNDECORATED");
        Stage s4 = new Stage();
        s4.initStyle(StageStyle.UNIFIED);
        s4.setTitle("StageStyle.UNIFIED");
*/

        Stage s5 = new Stage();
        s5.initOwner(s1);
        s5.initModality(Modality.WINDOW_MODAL);
        s5.initStyle(StageStyle.UTILITY);
        s5.setTitle("StageStyle.UTILITY");
        stages.add(s1);
   /*     stages.add(s2);
        stages.add(s3);
        stages.add(s4);*/
        stages.add(s5);
        for (Stage stage : stages) {
            stage.show();
        }
    }
}
