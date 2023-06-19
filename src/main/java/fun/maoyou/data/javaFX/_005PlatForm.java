package fun.maoyou.data.javaFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class _005PlatForm extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("外部>>>>>" + Thread.currentThread().getName());
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            System.out.println("内部任务>>>>>" + Thread.currentThread().getName());
            int i = 0;
       /*     while (i < 10) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
                System.out.println("内部任务,>>>>>>>");
            }*/
        });
        primaryStage.show();

        int i = 0;
        while (i < 10) {
            i++;
            System.out.println("外部任务,>>>>>>>");
        }
    }

}
