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

public class Controller extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void trigger(TextArea textArea) {
        String preMin1 = "";
        String preMin2 = "";
        String preMin3 = "";
        String preMin4 = "";
        String preMin5 = "";
        String preMin6 = "";

        String preDay1 = "";
        String preDay2 = "";
        String preDay3 = "";
        String preDay4 = "";
        String preDay5 = "";
        String preDay6 = "";

        Runnable runnable = new Runnable() {
            public void run() {
                textArea.setText(Math.random() + "");
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, 5, TimeUnit.SECONDS);

    }
    public void fetchDateData(String date) throws Exception {
        try {
            Connection conn = Jsoup.connect("http://www.tx-ffc.com/txffc/kj-" + date + ".html");
            conn.timeout(3000);
            Document doc = conn.get();

            Elements els = null;
            els = doc.select(".klist .kj_list").select(".kj_code");
            DigLotteryProc(els, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DigLotteryProc(Elements els, String date) {
        for (Element kjTitleElement : els) {
            Elements kjElements = kjTitleElement.select(".kj_title");
            for (Element row : kjElements) {

                String kjTime = row.getElementsByClass("a1").text();
                String curNo = row.getElementsByClass("a2").text();
                String kjNo = row.getElementsByClass("a3").text();
                try {
                    String no = date + "-" + curNo.substring(0, curNo.length() - 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String getPreMinNo(String curNo) {
        Integer no = Integer.valueOf(curNo);

        if (1 == no) {
            return "1440";
        }
        return "";

    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("腾讯分分智能出号系统,稳定方案请联系qq:352560380");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300, Color.WHITE);
        int x = 100;
        int y = 100;

        Text text = new Text(x, y, "000 009");
        TextArea textArea = new TextArea();
        textArea.setText("等待中...");

        //text.setFill(Color.rgb(red, green, blue, .99));
        root.getChildren().add(text);
        root.getChildren().add(textArea);

        primaryStage.setScene(scene);
        primaryStage.show();
        trigger(textArea);
    }
}