
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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Board extends GridPane {

    Color first = Color.rgb(133, 94, 66);
    Color second = Color.rgb(222, 184, 135);
    Color black = Color.BLACK;

    private Text timerText1, timerText2;
    private Timeline timeline1, timeline2;
    private int remainingTime1 = 10, remainingTime2 = 10;

    public Board() {
        setBackground(new Background(new BackgroundFill(Color.rgb(222, 184, 135), CornerRadii.EMPTY, Insets.EMPTY)));

        // this code adds a Start Button for timer1
        Button startButton1 = new Button("White Player");
        add(startButton1, 1, 10);
        startButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTimer1();
            }
        });

        // this code adds a timer text1, in the corner of the Chessboard.
        timerText1 = new Text("00:10");
        timerText1.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        timerText1.setFill(Color.rgb(255, 255, 255));
        add(timerText1, 1, 11);
        setHalignment(timerText1, HPos.CENTER);
        setValignment(timerText1, VPos.CENTER);

        // this code adds a Pause Button for timer1
        Button pauseButton1 = new Button("Next Player");
        add(pauseButton1, 2, 10);
        pauseButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pauseTimer1();
            }

            private void pauseTimer1() {
                if (timeline1 != null && timeline1.getStatus() == Animation.Status.RUNNING) {
                    timeline1.pause();
                }
            }
        });

        // this code adds a Start Button for timer2
        Button startButton2 = new Button("Black Player");
        add(startButton2, 7, 10);
        startButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTimer2();
            }
        });

        // this code adds a timer text2, in the corner of the Chessboard.
        timerText2 = new Text("00:10");
        timerText2.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        timerText2.setFill(black);
        add(timerText2, 8, 11);
        setHalignment(timerText2, HPos.CENTER);
        setValignment(timerText2, VPos.CENTER);

        // this code adds a Pause Button for timer2
        Button pauseButton2 = new Button("Next Player");
        add(pauseButton2, 8, 10);
        pauseButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pauseTimer2();
            }

            private void pauseTimer2() {
                if (timeline2 != null && timeline2.getStatus() == Animation.Status.RUNNING) {
                    timeline2.pause();
                }
            }
        });

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
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
            text.setFill(black);
            add(text, i + 1, 0);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
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
        }

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Integer.toString(8 - i));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
            text.setFill(black);
            add(text, 9, i + 1); // add to column 1, row i-7
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
        }

    }

    // this code decrements the Remaining time every second.
    private void startTimer1() {
        timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingTime1--;
            updateTimerText1();
            if (remainingTime1 <= 0) {
                stopTimer1();
            }
        }));
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();
    }

    private void startTimer2() {
        timeline2 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingTime2--;
            updateTimerText2();
            if (remainingTime2 <= 0) {
                stopTimer2();
            }
        }));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
    }

    // this code stops the Timeline that was started by startTimer.
    // and creates a new Stage once the timer is finished with a text on it.
    private void stopTimer1() {
        timeline1.stop();
        remainingTime1 = 10;
        updateTimerText1();
        Stage stage = new Stage();
        Text message = new Text("White is out of time!");
        message.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        message.setFill(Color.WHITE);
        VBox root = new VBox(message);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(133, 94, 66), null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
        Scene scene = new Scene(root, 250, 40);
        stage.setScene(scene);
        stage.show();
    }

    private void stopTimer2() {
        timeline2.stop();
        remainingTime2 = 10;
        updateTimerText2();
        Stage stage = new Stage();
        Text message = new Text("Black is out of Time!");
        message.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        message.setFill(Color.WHITE);
        VBox root = new VBox(message);
        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(133, 94, 66), null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
        Scene scene = new Scene(root, 250, 40);
        stage.setScene(scene);
        stage.show();
    }

    // this code updates the timer on the screen.
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