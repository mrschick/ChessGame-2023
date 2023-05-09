
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Board extends GridPane {

    Color first = Color.rgb(133, 94, 66);
    Color second = Color.rgb(222, 184, 135);
    Color black = Color.BLACK;

    private Text timerText;
    private Timeline timeline;
    private int remainingTime = 10;

    public Board() {
        setBackground(new Background(new BackgroundFill(Color.rgb(222, 184, 135), CornerRadii.EMPTY, Insets.EMPTY)));

        // this code adds a Start Button
        Button startButton = new Button("Start");
        add(startButton, 1, 9);
        // setMargin(startButton, new Insets(0, 0, 0, 15));
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTimer();
            }
        });

        // this code adds a timer text, in the corner of the Chessboard.
        timerText = new Text("00:10");
        timerText.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        timerText.setFill(black);
        add(timerText, 8, 9);
        setHalignment(timerText, HPos.CENTER);
        setValignment(timerText, VPos.CENTER);

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

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Character.toString((char) ('a' + i)));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            text.setFill(black);
            add(text, i + 1, 0);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
        }

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Integer.toString(8 - i));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            text.setFill(black);
            add(text, 0, i + 1);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
        }
    }

    // this code decrements the Remaining time every second.
    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingTime--;
            updateTimerText();
            if (remainingTime <= 0) {
                stopTimer();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // this code stops the Timeline that was started by startTimer.
    // and creates a new Stage once the timer is finished with a text on it.
    private void stopTimer() {
        timeline.stop();
        remainingTime = 10;
        updateTimerText();
        Stage stage = new Stage();
        Text message = new Text("START for playing again!");
        message.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        message.setFill(Color.WHITE);
        VBox root = new VBox(message);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(133, 94, 66), null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
        Scene scene = new Scene(root, 300, 40);
        stage.setScene(scene);
        stage.show();
    }

    // this code updates the timer on the screen.
    private void updateTimerText() {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        timerText.setText(String.format("%d:%02d", minutes, seconds));
    }
}