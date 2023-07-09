
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
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

import java.util.List;

public class Board extends GridPane {

    Color dark;
    Color light;
    Color background;

    private Text timerText;
    private Timeline timeline;
    private int remainingTime = 10;

    public Board(ColorScheme colorScheme) {
        this.dark = colorScheme.dark;
        this.light = colorScheme.light;
        this.background = colorScheme.boardBg;
        setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));

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
        timerText.setFill(Color.BLACK);
        add(timerText, 8, 9);
        setHalignment(timerText, HPos.CENTER);
        setValignment(timerText, VPos.CENTER);

        for (int column = 0; column < 8; column++){
            Pawn bpawn = new Pawn(Color.BLACK);
            Pawn wpawn = new Pawn(Color.WHITE);
            Square bSQ = new Square(bpawn);
            bSQ.setPosition(String.valueOf((char) (column + 97)) + 2);
            System.out.println(bSQ.getPosition());
            bSQ.setOnMouseClicked(evt->seekEventListener(evt, bpawn));
            Square wSQ = new Square(wpawn);
            wSQ.setPosition(String.valueOf((char) (column + 97)) + 7);
            add(wSQ, column + 1, 7);
            add(bSQ, column + 1, 2);
        }

        //Adds Rook pieces to the Chess Board
        Square sq = new Square(new Rook(Color.BLACK));
        sq.setPosition(String.valueOf((char) 97) +  8);
        add(sq, 1, 1);
        sq = new Square(new Rook(Color.BLACK));
        sq.setPosition(String.valueOf((char) (104)) + 8);
        add(sq, 8, 1);
        sq = new Square(new Rook(Color.WHITE));
        sq.setPosition(String.valueOf((char) (97)) + 1);
        add(sq, 1, 8);
        sq = new Square(new Rook(Color.WHITE));
        sq.setPosition(String.valueOf((char) (104)) + 8);
        add(sq, 8, 8);

        //adds the Knights to the Chess Board
        sq = new Square(new Knight(Color.BLACK));
        sq.setPosition(String.valueOf((char) (98)) + 8);
        add(sq, 2, 1);
        sq = new Square(new Knight(Color.BLACK));
        sq.setPosition(String.valueOf((char) (103)) + 8);
        add(sq, 7, 1);
        sq = new Square(new Knight(Color.WHITE));
        sq.setPosition(String.valueOf((char) (98)) + 1);
        add(sq, 2, 8);
        sq = new Square(new Knight(Color.WHITE));
        sq.setPosition(String.valueOf((char) (103)) + 1);
        add(sq, 7, 8);

        //adds the Bishops to the Chess Board
        sq = new Square(new Bishop(Color.BLACK));
        sq.setPosition(String.valueOf((char) (99)) + 8);
        add(sq, 3, 1);
        sq = new Square(new Bishop(Color.BLACK));
        sq.setPosition(String.valueOf((char) (102)) + 8);
        add(sq, 6, 1);
        sq = new Square(new Bishop(Color.WHITE));
        sq.setPosition(String.valueOf((char) (99)) + 1);
        add(sq, 3, 8);
        sq = new Square(new Bishop(Color.WHITE));
        sq.setPosition(String.valueOf((char) (102)) + 1);
        add(sq, 6, 8);

        //adds the Queens to the Chess Board
        sq = new Square(new Queen(Color.BLACK));
        sq.setPosition(String.valueOf((char) (100)) + 8);
        add(sq, 4, 1);
        sq = new Square(new Queen(Color.WHITE));
        sq.setPosition(String.valueOf((char) (101)) + 1);
        add(sq, 4, 8);

        //adds the Kings to the Chess Board
        sq = new Square(new King(Color.BLACK));
        sq.setPosition(String.valueOf((char) (101)) + 8);
        add(sq, 5, 1);
        sq = new Square(new King(Color.WHITE));
        sq.setPosition(String.valueOf((char) (100)) + 1);
        add(sq, 5, 8);

        int postion_number = 6;
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                char position_char = (char) (j + 97);
                if (i % 2 != 0) {
                    sq = new Square();
                    sq.setPosition(String.valueOf(position_char) + postion_number);
                    System.out.println(sq.getPosition());

                    if (j % 2 == 0)
                        sq.setColor(dark);
                    else
                        sq.setColor(light);
                    add(sq, j + 1, i + 1);
                } else {
                    sq = new Square();
                    sq.setPosition(String.valueOf(position_char) + postion_number);
                    System.out.println(sq.getPosition());

                    if (j % 2 == 0)
                        sq.setColor(light);
                    else
                        sq.setColor(dark);
                    add(sq, j + 1, i + 1);
                }
            }
            postion_number--;
        }


        for (int i = 0; i < 8; i++) {
            Text text = new Text(Character.toString((char) ('a' + i)));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            text.setFill(Color.BLACK);
            add(text, i + 1, 0);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
        }

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Integer.toString(8 - i));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            text.setFill(Color.BLACK);
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

    private void seekEventListener(MouseEvent event, Piece chessPiece) {

        List<Node> nodes = getChildren();

        for (Node node: nodes){
            if (node instanceof Square && ((Square) node).isChessPiece() /*&& (chessPiece.kill( (Square) node) || chessPiece.move( (Square) node) )*/) {
                System.out.println("event");
                int i = GridPane.getRowIndex(node);
                int j = GridPane.getColumnIndex(node);
                ((Square) node).setStroke();
                getChildren().remove(i, j);
                ((Square) node).setColor(Color.BLACK);
                add(node, i ,j);
            }
        }
        getChildren().remove(1, 2);

    }
}