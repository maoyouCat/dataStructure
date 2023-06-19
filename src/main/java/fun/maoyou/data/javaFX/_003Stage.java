package fun.maoyou.data.javaFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 * https://docs.oracle.com/javafx/2/api/index.html
 */
public class _003Stage extends Application {

    private boolean isFull;


    @Override
    public void start(Stage primaryStage) throws Exception {
        //设置窗口decoration
        primaryStage.setTitle("JAVAFX-Stage");
//        primaryStage.setAlwaysOnTop(true);
        primaryStage.getIcons().add(new Image("/fun/maoyou/data/javaFX/static/code.png"));
        //设置窗口最大化
        primaryStage.setMaximized(false);
        //设置是否可以调节窗口大小
//        primaryStage.setResizable(false);
        //设置stage的宽带和高度
      /*  primaryStage.setWidth(300);
        primaryStage.setHeight(75);*/

        //设置全屏。注意必须存在Scene才有效果
//        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(new Group()));
        primaryStage.show();
        Thread.sleep(2000);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        primaryStage.setOpacity(0.5);
        primaryStage.setX(-1920);
        primaryStage.setY(0);
        isFull = primaryStage.isFullScreen();
        //高度监听器
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("观察对象:" + observable);
                System.out.println("我的窗口还是全平吗:" + isFull);
                System.out.println("旧高度," + oldValue);
                System.out.println("新高度," + newValue);
            }
        });
    }
}
