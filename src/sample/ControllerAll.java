package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//任三
public class ControllerAll extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void trigger(TextArea textArea) {
       Runnable runnable = new Runnable() {
            public void run() {
                TecentOnlinePrizeService tecentOnlinePrizeService = new TecentOnlinePrizeService(textArea);
                try {
                    tecentOnlinePrizeService.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
       new Thread(runnable).start();
         /*
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);*/

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("腾讯分分智能出号系统,稳定方案请联系qq:352560380");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300, Color.WHITE);
        int x = 100;
        int y = 100;

        Text text = new Text(x, y, "000 009");
        TextArea text00Area = new TextArea();
        text00Area.setText("等待中...");

        //text.setFill(Color.rgb(red, green, blue, .99));
        root.getChildren().add(text);
        root.getChildren().add(text00Area);

        primaryStage.setScene(scene);
        primaryStage.show();
        trigger(text00Area);

    }
}