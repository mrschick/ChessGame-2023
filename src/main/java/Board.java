
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

import javax.swing.plaf.ColorUIResource;
import java.util.List;

public class Board extends GridPane {

    Color dark;
    Color light;
    Color background;

    private Text timerText;
    private Timeline timeline;
    private int remainingTime = 10;

    public Board(ColorSchemes colorScheme) {
        this.dark = colorScheme.activeDark();
        this.light = colorScheme.activeLight();
        System.out.println("Dark: "+this.dark.toString()+"\nLight: "+this.light.toString());
        this.background = colorScheme.activeBoardBG();
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
            bSQ.setPosition(String.valueOf((char) (column + 97)) + 7);
            System.out.println(bSQ.getPosition());
            bSQ.setOnMouseEntered(e->seekEventListener(e, bpawn));
            bSQ.setOnMouseExited(e -> resetSeekEventListener(e, bpawn));
            Square wSQ = new Square(wpawn);
            wSQ.setOnMouseEntered(e-> seekEventListener(e, wpawn));
            wSQ.setOnMouseExited(e -> resetSeekEventListener(e, wpawn));
            wSQ.setPosition(String.valueOf((char) (column + 97)) + 2);
            add(wSQ, column + 1, 7);
            add(bSQ, column + 1, 2);
        }

        //Adds Rook pieces to the Chess Board
        Rook bRook1 = new Rook(Color.BLACK);
        Square sq = new Square(bRook1);
        sq.setPosition("a" +  8);
        sq.setOnMouseEntered(e->seekEventListener(e, bRook1));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, bRook1));
        add(sq, 1, 1);

        Rook bRook2 = new Rook(Color.BLACK);
        sq = new Square(bRook2);
        sq.setPosition("h" + 8);
        sq.setOnMouseEntered(e->seekEventListener(e, bRook2));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, bRook2));
        add(sq, 8, 1);

        Rook wRook1 = new Rook(Color.WHITE);
        sq = new Square(wRook1);
        sq.setPosition("a" + 1);
        sq.setOnMouseEntered(e->seekEventListener(e, wRook1));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, wRook1));
        add(sq, 1, 8);

        Rook wRook2 = new Rook(Color.WHITE);
        sq = new Square(wRook2);
        sq.setPosition("h" + 1);
        sq.setOnMouseEntered(e->seekEventListener(e, wRook2));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, wRook2));
        add(sq, 8, 8);

        //adds the Knights to the Chess Board
        Knight bKnight1 = new Knight(Color.BLACK);
        sq = new Square(bKnight1);
        sq.setPosition("b" + 8);
        sq.setOnMouseEntered(e->seekEventListener(e, bKnight1));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, bKnight1));
        add(sq, 2, 1);

        Knight bKnight2 = new Knight(Color.BLACK);
        sq = new Square(bKnight2);
        sq.setPosition("g" + 8);
        sq.setOnMouseEntered(e->seekEventListener(e, bKnight2));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, bKnight2));
        add(sq, 7, 1);

        Knight wKnight1 = new Knight(Color.WHITE);
        sq = new Square(wKnight1);
        sq.setPosition("b" + 1);
        sq.setOnMouseEntered(e->seekEventListener(e, wKnight1));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, wKnight1));
        add(sq, 2, 8);

        Knight wKnight2 = new Knight(Color.WHITE);
        sq = new Square(wKnight2);
        sq.setPosition("g" + 1);
        sq.setOnMouseEntered(e->seekEventListener(e, wKnight2));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, wKnight2));
        add(sq, 7, 8);

        //adds the Bishops to the Chess Board
        Bishop bBishop1 = new Bishop(Color.BLACK);
        sq = new Square(bBishop1);
        sq.setPosition("c" + 8);
        sq.setOnMouseEntered(e->seekEventListener(e, bBishop1));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, bBishop1));
        add(sq, 3, 1);

        Bishop bBishop2 = new Bishop(Color.BLACK);
        sq = new Square(bBishop2);
        sq.setPosition("f" + 8);
        sq.setOnMouseEntered(e->seekEventListener(e, bBishop2));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, bBishop2));
        add(sq, 6, 1);

        Bishop wBishop1 = new Bishop(Color.WHITE);
        sq = new Square(wBishop1);
        sq.setPosition("c" + 1);
        sq.setOnMouseEntered(e->seekEventListener(e, wBishop1));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, wBishop1));
        add(sq, 3, 8);

        Bishop wBishop2 = new Bishop(Color.WHITE);
        sq = new Square(wBishop2);
        sq.setPosition("f" + 1);
        sq.setOnMouseEntered(e->seekEventListener(e, wBishop2));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, wBishop2));
        add(sq, 6, 8);

        //adds the Queens to the Chess Board
        Queen bQueen = new Queen(Color.BLACK);
        sq = new Square(bQueen);
        sq.setPosition("d" + 8);
        sq.setOnMouseEntered(e->seekEventListener(e, bQueen));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, bQueen));
        add(sq, 4, 1);

        Queen wQueen = new Queen(Color.WHITE);
        sq = new Square(wQueen);
        sq.setPosition("e" + 1);
        sq.setOnMouseEntered(e->seekEventListener(e, wQueen));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, wQueen));
        add(sq, 5, 8);

        //adds the Kings to the Chess Board
        King bKing = new King(Color.BLACK);
        sq = new Square(bKing);
        sq.setPosition("e" + 8);
        sq.setOnMouseEntered(e->seekEventListener(e, bKing));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, bKing));
        add(sq, 5, 1);

        King wKing = new King(Color.WHITE);
        sq = new Square(wKing);
        sq.setPosition("d" + 1);
        sq.setOnMouseEntered(e->seekEventListener(e, wKing));
        sq.setOnMouseExited(e -> resetSeekEventListener(e, wKing));
        add(sq, 4, 8);

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
            if (node instanceof Square && (chessPiece.kill( (Square) node) || chessPiece.move( (Square) node) )){
                ((Square) node).setStroke(Color.YELLOW);
            }
        }
    }

    private void resetSeekEventListener(MouseEvent event, Piece chessPiece){
        List<Node> nodes = getChildren();
        for (Node node: nodes){
            if (node instanceof Square && (chessPiece.kill( (Square) node) || chessPiece.move( (Square) node) )) {
                ((Square) node).setStroke(Color.BLACK);
            }
        }
    }
}