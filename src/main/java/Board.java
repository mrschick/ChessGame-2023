
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.Animation;

public class Board extends GridPane {

    Color first = Color.rgb(133, 94, 66);
    Color second = Color.rgb(222, 184, 135);
    Color black = Color.BLACK;
    private Text timerText1, timerText2;
    private Timeline timeline1, timeline2;
    private int remainingTime1 = 07, remainingTime2 = 05;

    public Board() {

        for (int i = 0; i < 8; i++) {
            int postion_number = 8;
            for (int j = 0; j < 8; j++) {
                char position_char = (char) (j + 97);
                if (i % 2 != 0) {
                    Square sq = new Square();
                    sq.setPosition(String.valueOf(position_char) + postion_number);

                    if (j % 2 == 0)
                        sq.setColor(first);
                    else
                        sq.setColor(second);
                    add(sq, j + 1, i + 1);
                } else {
                    Square sq = new Square();
                    sq.setPosition(String.valueOf(position_char) + postion_number);

                    if (j % 2 == 0)
                        sq.setColor(second);
                    else
                        sq.setColor(first);
                    add(sq, j + 1, i + 1);
                }
            }
        }
        Button startButton1 = new Button("Start Game");
        startButton1.setTranslateX(153);
        startButton1.setTranslateY(695);
        add(startButton1, 0, 0);

        startButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTimer1();
                startTimer2();
            }
        });

        Button pauseButton1 = new Button("Pause Game");
        add(pauseButton1, 0, 0);
        pauseButton1.setTranslateX(420);
        pauseButton1.setTranslateY(695);

        pauseButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetTimer1();
            }

            private void resetTimer1() {
                if (timeline1 != null && timeline1.getStatus() == Animation.Status.RUNNING) {
                    timeline1.pause();
                    timeline2.pause();
                }
            }
        });

        Button resetButton = new Button("Reset Game");
        resetButton.setTranslateX(685);
        resetButton.setTranslateY(695);
        add(resetButton, 0, 0);

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                remainingTime1 = 8;
                remainingTime2 = 6;
                timeline1.pause();
                timeline1.getKeyFrames().clear();
                timeline2.pause();
                timeline2.getKeyFrames().clear();
                startTimer1();
                startTimer2();
            }
        });

        timerText1 = new Text("00:07");
        timerText1.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        timerText1.setFill(Color.rgb(255, 255, 255));
        timerText1.setTranslateX(850);
        timerText1.setTranslateY(165);
        getChildren().add(timerText1);

        Text aboveText = new Text("White's time:");
        aboveText.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        aboveText.setFill(Color.rgb(255, 255, 255));
        aboveText.setTranslateX(timerText1.getTranslateX());
        aboveText.setTranslateY(timerText1.getTranslateY() - 30);
        getChildren().add(aboveText);

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(85);
        rectangle.setWidth(150);
        rectangle.setTranslateX(845);
        rectangle.setTranslateY(150);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        rectangle.setFill(null);
        getChildren().add(rectangle);

        timerText2 = new Text("00:05");
        timerText2.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        timerText2.setFill(black);
        timerText2.setTranslateX(850);
        timerText2.setTranslateY(545);
        getChildren().add(timerText2);

        Text aboveText1 = new Text("Black's time:");
        aboveText1.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        aboveText1.setFill(black);
        aboveText1.setTranslateX(timerText2.getTranslateX());
        aboveText1.setTranslateY(timerText2.getTranslateY() - 30);
        getChildren().add(aboveText1);

        Rectangle rectangle1 = new Rectangle();
        rectangle1.setHeight(80);
        rectangle1.setWidth(150);
        rectangle1.setTranslateX(845);
        rectangle1.setTranslateY(535);
        rectangle1.setStroke(Color.BLACK);
        rectangle1.setStrokeWidth(3);
        rectangle1.setFill(null);
        getChildren().add(rectangle1);

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setHeight(80);
        rectangle2.setWidth(150);
        rectangle2.setTranslateX(845);
        rectangle2.setTranslateY(250);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setStrokeWidth(3);
        rectangle2.setFill(null);
        getChildren().add(rectangle2);

        Rectangle rectangle3 = new Rectangle();
        rectangle3.setHeight(80);
        rectangle3.setWidth(150);
        rectangle3.setTranslateX(845);
        rectangle3.setTranslateY(430);
        rectangle3.setStroke(Color.BLACK);
        rectangle3.setStrokeWidth(3);
        rectangle3.setFill(null);
        getChildren().add(rectangle3);

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Character.toString((char) ('a' + i)));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
            text.setFill(black);
            add(text, i + 1, 0);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
            text.setTranslateY(30);

        }
        for (int i = 0; i < 8; i++) {
            Text text = new Text(Character.toString((char) ('a' + i)));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
            text.setFill(black);
            add(text, i + 1, 9);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);

        }

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Integer.toString(8 - i));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
            text.setFill(black);
            add(text, 0, i + 1);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
            text.setTranslateX(68);
        }

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Integer.toString(8 - i));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
            text.setFill(black);
            add(text, 9, i + 1);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
        }

    }

    private void startTimer1() {
        timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingTime1--;
            updateTimerText1();
            updateTimerText2();
            if (remainingTime1 <= 0) {
                stopTimer1();
                stopTimer2();
            }
        }));
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();
    }

    private void startTimer2() {
        timeline2 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingTime2--;
            updateTimerText2();
            updateTimerText1();
            if (remainingTime2 <= 0) {
                stopTimer2();
                stopTimer1();

            }
        }));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
    }

    private void stopTimer1() {
        timeline1.stop();
        remainingTime1 = 07;
        updateTimerText1();
        updateTimerText2();
        Text message = new Text("White is out of time! Black won.");
        message.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        message.setFill(Color.WHITE);
        VBox root = new VBox(message);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(133, 94, 66),
                null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
        Stage stage = new Stage();
        Scene scene = new Scene(root, 380, 40);
        stage.setScene(scene);
        stage.show();
    }

    private void stopTimer2() {
        timeline2.stop();
        remainingTime2 = 05;
        updateTimerText2();
        updateTimerText1();
        Stage stage = new Stage();
        Text message = new Text("Black is out of Time! White won");
        message.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        message.setFill(Color.BLACK);
        VBox root = new VBox(message);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(133, 94, 66),
                null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
        Scene scene = new Scene(root, 380, 40);
        stage.setScene(scene);
        stage.show();
    }

    private void updateTimerText1() {
        int minutes = remainingTime1 / 60;
        int seconds = remainingTime1 % 60;
        timerText1.setText(String.format("%d:%02d", minutes, seconds));
    }

    private void updateTimerText2() {
        int minutes = remainingTime2 / 60;
        int seconds = remainingTime2 % 60;
        timerText2.setText(String.format("%d:%02d", minutes, seconds));
    }

}
