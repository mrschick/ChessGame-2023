
import javafx.geometry.HPos;
import javafx.geometry.VPos;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Board extends GridPane {

    Color dark;
    Color light;
    Color background;

    private Text timerText;
    private Timeline timeline;
    private int remainingTime = 10;
    private Square clickedSquare = null;
    private int clickCount = 0, row_num = 0, column_num = 0;
    private Square[][] Squares = new Square[8][8];
    private Color p_move = Color.YELLOW, p_kill = Color.GREEN, p_check = Color.RED;
    private boolean white_turn = false;
    private Square wKingsq;
    private int wKing_row, wKing_col;
    private Square bKingsq;
    private int bKing_row, bKing_col;
    boolean wKing_checked, bKing_checked;
    Map<Square, int> threats_WKing = new HashMap<>();
    Map<Square, int> threats_Bking = new HashMap<>();

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

        //adds Pawn Squares to table Squares[][].
        for (int column = 0; column < 8; column++) {
            Pawn bpawn = new Pawn(Color.BLACK);
            Pawn wpawn = new Pawn(Color.WHITE);

            Square bSQ = new Square(bpawn);
            bSQ.setPosition(String.valueOf((char) (column + 97)) + 7);
            bSQ.setOnMouseClicked(this::moveKillmethod);

            Square wSQ = new Square(wpawn);
            wSQ.setPosition(String.valueOf((char) (column + 97)) + 2);
            wSQ.setOnMouseClicked(this::moveKillmethod);

            if (column % 2 == 0) {
                bSQ.setColor(dark);
                wSQ.setColor(light);
            } else {
                bSQ.setColor(light);
                wSQ.setColor(dark);
            }
            Squares[6][column] = wSQ;
            Squares[1][column] = bSQ;
        }

        //Adds Rook Squares to the table Squares[][]
        Rook bRook1 = new Rook(Color.BLACK);
        Square bRooksq1 = new Square(bRook1);
        bRooksq1.setPosition("a" + 8);
        bRooksq1.setOnMouseClicked(this::moveKillmethod);
        bRooksq1.setColor(light);
        Squares[0][0] = bRooksq1;

        Rook bRook2 = new Rook(Color.BLACK);
        Square bRooksq2 = new Square(bRook2);
        bRooksq2.setPosition("h" + 8);
        bRooksq2.setOnMouseClicked(this::moveKillmethod);
        bRooksq2.setColor(dark);
        Squares[0][7] = bRooksq2;

        Rook wRook1 = new Rook(Color.WHITE);
        Square wRooksq1 = new Square(wRook1);
        wRooksq1.setPosition("a" + 1);
        wRooksq1.setOnMouseClicked(this::moveKillmethod);
        wRooksq1.setColor(dark);
        Squares[7][0] = wRooksq1;

        Rook wRook2 = new Rook(Color.WHITE);
        Square wRooksq2 = new Square(wRook2);
        wRooksq2.setPosition("h" + 1);
        wRooksq2.setOnMouseClicked(this::moveKillmethod);
        wRooksq2.setColor(light);
        Squares[7][7] = wRooksq2;

        //adds the Knight Squares to table Squares[][]
        Knight bKnight1 = new Knight(Color.BLACK);
        Square bKnightsq1 = new Square(bKnight1);
        bKnightsq1.setPosition("b" + 8);
        bKnightsq1.setOnMouseClicked(this::moveKillmethod);
        bKnightsq1.setColor(dark);
        Squares[0][1] = bKnightsq1;

        Knight bKnight2 = new Knight(Color.BLACK);
        Square bKnightsq2 = new Square(bKnight2);
        bKnightsq2.setPosition("g" + 8);
        bKnightsq2.setOnMouseClicked(this::moveKillmethod);
        bKnightsq2.setColor(light);
        Squares[0][6] = bKnightsq2;

        Knight wKnight1 = new Knight(Color.WHITE);
        Square wKnightsq1 = new Square(wKnight1);
        wKnightsq1.setPosition("b" + 1);
        wKnightsq1.setOnMouseClicked(this::moveKillmethod);
        wKnightsq1.setColor(light);
        Squares[7][1] = wKnightsq1;

        Knight wKnight2 = new Knight(Color.WHITE);
        Square wKnightsq2 = new Square(wKnight2);
        wKnightsq2.setPosition("g" + 1);
        wKnightsq2.setOnMouseClicked(this::moveKillmethod);
        wKnightsq2.setColor(dark);
        Squares[7][6] = wKnightsq2;

        //adds the Bishop Squares to table Squares[][]
        Bishop bBishop1 = new Bishop(Color.BLACK);
        Square bBishopsq1 = new Square(bBishop1);
        bBishopsq1.setPosition("c" + 8);
        bBishopsq1.setOnMouseClicked(this::moveKillmethod);
        bBishopsq1.setColor(light);
        Squares[0][2] = bBishopsq1;

        Bishop bBishop2 = new Bishop(Color.BLACK);
        Square bBishopsq2 = new Square(bBishop2);
        bBishopsq2.setPosition("f" + 8);
        bBishopsq2.setOnMouseClicked(this::moveKillmethod);
        bBishopsq2.setColor(dark);
        Squares[0][5] = bBishopsq2;

        Bishop wBishop1 = new Bishop(Color.WHITE);
        Square wBishopsq1 = new Square(wBishop1);
        wBishopsq1.setPosition("c" + 1);
        wBishopsq1.setOnMouseClicked(this::moveKillmethod);
        wBishopsq1.setColor(dark);
        Squares[7][2] = wBishopsq1;

        Bishop wBishop2 = new Bishop(Color.WHITE);
        Square wBishopsq2 = new Square(wBishop2);
        wBishopsq2.setPosition("f" + 1);
        wBishopsq2.setOnMouseClicked(this::moveKillmethod);
        wBishopsq2.setColor(light);
        Squares[7][5] = wBishopsq2;

        //adds the Queen Squares to table Squares[][]
        Queen bQueen = new Queen(Color.BLACK);
        Square bQueensq = new Square(bQueen);
        bQueensq.setPosition("d" + 8);
        bQueensq.setOnMouseClicked(this::moveKillmethod);
        bQueensq.setColor(dark);
        Squares[0][3] = bQueensq;

        Queen wQueen = new Queen(Color.WHITE);
        Square wQueensq = new Square(wQueen);
        wQueensq.setPosition("d" + 1);
        wQueensq.setOnMouseClicked(this::moveKillmethod);
        wQueensq.setColor(light);
        Squares[7][3] = wQueensq;

        //adds the King Squares to table Squares[][]
        King bKing = new King(Color.BLACK);
        bKingsq = new Square(bKing);
        bKingsq.setPosition("e" + 8);
        bKingsq.setOnMouseClicked(this::moveKillmethod);
        bKingsq.setColor(light);
        Squares[0][4] = bKingsq;
        bKing_row = 0;
        bKing_col = 4;

        King wKing = new King(Color.WHITE);
        wKingsq = new Square(wKing);
        wKingsq.setPosition("e" + 1);
        wKingsq.setOnMouseClicked(this::moveKillmethod);
        wKingsq.setColor(dark);
        Squares[7][4] = wKingsq;
        wKing_row = 7;
        wKing_col = 3;

        //Adds plain Squares to the table Squares[][]
        int postion_number = 6;
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                char position_char = (char) (j + 97);
                if (i % 2 != 0) {
                    Square sq = new Square();
                    sq.setPosition(String.valueOf(position_char) + postion_number);
                    sq.setOnMouseClicked(this::moveKillmethod);
                    System.out.println(sq.getPosition());

                    if (j % 2 == 0)
                        sq.setColor(dark);
                    else
                        sq.setColor(light);
                    Squares[i][j] = sq;
                } else {
                    Square sq = new Square();
                    sq.setPosition(String.valueOf(position_char) + postion_number);
                    sq.setOnMouseClicked(this::moveKillmethod);

                    if (j % 2 == 0)
                        sq.setColor(light);
                    else
                        sq.setColor(dark);
                    Squares[i][j] = sq;
                }
            }
            postion_number--;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                add(Squares[i][j], j + 1, i + 1);
            }
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

    //This method suggests possible Chess piece moves and handles the movement of pieces on Chess Board.
    private void moveKillmethod(MouseEvent event) {
        clickCount++;
        if (clickCount == 1) {
            clickedSquare = (Square) event.getSource();
            getIndexSquares(clickedSquare);
            if (clickedSquare != null && clickedSquare.isChessPiece()) {
                if (white_turn && clickedSquare.getPiece().getColor().equals(Color.WHITE)) {
                    showPieceMoves(clickedSquare.getPiece(), row_num, column_num);
                    System.out.println("\n" + "WKing checked" + wKing_checked);
                    if (wKing_checked) {
                        //showMoveswhileChecked(clickedSquare, row_num, column_num);
                        wKingsq.setStroke(Color.RED);
                    }
                } else if (!(white_turn) && clickedSquare.getPiece().getColor().equals(Color.BLACK)) {
                    showPieceMoves(clickedSquare.getPiece(), row_num, column_num);
                    System.out.println("\n" + "BKing checked" + bKing_checked);
                    if (bKing_checked) {
                        bKingsq.setStroke(Color.RED);
                        //showMoveswhileChecked(clickedSquare, row_num, column_num);
                    }
                } else
                    clickCount--;
            } else {
                clickCount--;
            }
        } else if (clickCount == 2) {
            if (clickedSquare != null && clickedSquare.isChessPiece()) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (event.getSource() instanceof Square) {
                            if (!(clickedSquare.equals(event.getSource()))) {
                                if (event.getSource().equals(Squares[i][j])) {
                                    if (Squares[i][j].isChessPiece()) {
                                        if (clickedSquare.getPiece().kill(Squares[i][j]) && Squares[i][j].getStroke().equals(p_kill)) {
                                            Squares[i][j].replacePiece(clickedSquare.getPiece());
                                            set_CheckedKing(Squares[i][j]);
                                            clickedSquare.removePiece();
                                            update_kingSq_helper(Squares[i][j]);
                                            white_turn = !white_turn;
                                        }
                                    } else if (!(Squares[i][j].isChessPiece())) {
                                        if (clickedSquare.getPiece().move(Squares[i][j]) && Squares[i][j].getStroke().equals(p_move)) {
                                            Squares[i][j].addPiece(clickedSquare.getPiece());
                                            set_CheckedKing(Squares[i][j]);
                                            clickedSquare.removePiece();
                                            update_kingSq_helper(Squares[i][j]);
                                            white_turn = !white_turn;
                                        }
                                    }
                                }
                            }
                        }
                        Squares[i][j].setStroke(Color.BLACK);
                    }
                }
            }
            clickCount = 0;
            if (event.getSource() instanceof Square && ((Square) event.getSource()).isChessPiece()) {
                try {
                    boolean self = ((Square) event.getSource()).getPiece().equals(clickedSquare.getPiece());
                    if (((Square) event.getSource()).getPiece().getColor().equals(clickedSquare.getPiece().getColor()) && !self) {
                        moveKillmethod(event);
                    } else {
                        clickedSquare = null;
                    }
                } catch (NullPointerException e) {
                }
            }
        }
    }

    private void getIndexSquares(Square clickedSquare) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Squares[i][j].equals(clickedSquare)) {
                    row_num = i;
                    column_num = j;
                    break;
                }
            }
        }

    }

    //Submethod to show possible moves for a chess piece.
    private void showPieceMoves(Piece piece, int row_num, int column_num) {
        boolean k_checked = false;
        Color color = piece.getColor();
        if (color.equals(Color.BLACK) && bKing_checked) {
            k_checked = true;
            bKingsq.setStroke(p_check);
        } else if (color.equals(Color.WHITE) && wKing_checked) {
            System.out.println("\n" + "in spm ");
            k_checked = true;
            wKingsq.setStroke(p_check);
        }
        boolean t_left = false, t = false, t_right = false;
        boolean right = false, left = false;
        boolean b_left = false, b = false, b_right = false;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (piece instanceof Knight) {
                    if (piece.kill(Squares[i - 1][j - 1])) {
                        Squares[i - 1][j - 1].setStroke(p_kill);
                    } else if (piece.move(Squares[i - 1][j - 1])) {
                        Squares[i - 1][j - 1].setStroke(p_move);
                    }
                } else {
                    if (!t_left && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num - j][column_num - j].isChessPiece()) {
                                if (piece.kill(Squares[row_num - j][column_num - j])) {
                                    Squares[row_num - j][column_num - j].setStroke(p_kill);
                                }
                                t_left = true;
                            } else if (piece.move(Squares[row_num - j][column_num - j])) {
                                Squares[row_num - j][column_num - j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            t_left = true;
                        }
                    }

                    if (!t && (piece instanceof Queen || piece instanceof Rook || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num - j][column_num].isChessPiece()) {
                                if (piece.kill(Squares[row_num - j][column_num])) {
                                    Squares[row_num - j][column_num].setStroke(p_kill);
                                }
                                t = true;
                            } else if (piece.move(Squares[row_num - j][column_num])) {
                                Squares[row_num - j][column_num].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            t = true;
                        }
                    }

                    if (!t_right && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num - j][column_num + j].isChessPiece()) {
                                if (piece.kill(Squares[row_num - j][column_num + j])) {
                                    Squares[row_num - j][column_num + j].setStroke(p_kill);
                                }
                                t_right = true;
                            } else if (piece.move(Squares[row_num - j][column_num + j])) {
                                Squares[row_num - j][column_num + j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            t_right = true;
                        }
                    }

                    if (!right && (piece instanceof Queen || piece instanceof King || piece instanceof Rook)) {
                        try {
                            if (Squares[row_num][column_num + j].isChessPiece()) {
                                if (piece.kill(Squares[row_num][column_num + j])) {
                                    Squares[row_num][column_num + j].setStroke(p_kill);
                                }
                                right = true;
                            } else if (piece.move(Squares[row_num][column_num + j])) {
                                Squares[row_num][column_num + j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            right = true;
                        }
                    }

                    if (!left && (piece instanceof Queen || piece instanceof King || piece instanceof Rook)) {
                        try {
                            if (Squares[row_num][column_num - j].isChessPiece()) {
                                if (piece.kill(Squares[row_num][column_num - j])) {
                                    Squares[row_num][column_num - j].setStroke(p_kill);
                                }
                                left = true;
                            } else if (piece.move(Squares[row_num][column_num - j])) {
                                Squares[row_num][column_num - j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            left = true;
                        }
                    }

                    if (!b_left && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num + j][column_num - j].isChessPiece()) {
                                if (piece.kill(Squares[row_num + j][column_num - j])) {
                                    Squares[row_num + j][column_num - j].setStroke(p_kill);
                                }
                                b_left = true;
                            } else if (piece.move(Squares[row_num + j][column_num - j])) {
                                Squares[row_num + j][column_num - j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            b_left = true;
                        }
                    }

                    if (!b && (piece instanceof Queen || piece instanceof King || piece instanceof Rook || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num + j][column_num].isChessPiece()) {
                                if (piece.kill(Squares[row_num + j][column_num])) {
                                    Squares[row_num + j][column_num].setStroke(p_kill);
                                }
                                b = true;
                            } else if (piece.move(Squares[row_num + j][column_num])) {
                                Squares[row_num + j][column_num].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            b = true;
                        }
                    }

                    if (!b_right && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num + j][column_num + j].isChessPiece()) {
                                if (piece.kill(Squares[row_num + j][column_num + j])) {
                                    Squares[row_num + j][column_num + j].setStroke(p_kill);
                                }
                                b_right = true;
                            } else if (piece.move(Squares[row_num + j][column_num + j])) {
                                Squares[row_num + j][column_num + j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            b_right = true;
                        }
                    }
                }

            }
        }
    }

    /*
    private void showMoveswhileChecked(Square sq, int row_num, int column_num) {
        System.out.println("iside showCheckedMoves");
        System.out.println("wking threats size" + threats_WKing.size());
        int array_size = 0;
        Color cl = sq.getPiece().getColor();
        if (cl.equals(Color.BLACK))
            array_size = threats_Bking.size();
        else
            array_size = threats_WKing.size();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square threat = null;
                if (sq.getPiece() instanceof King) {
                    if (Squares[i][j].getStroke().equals(p_kill)) {
                        if (Squares[i][j].getColor().equals(Color.BLACK) && threats_WKing.contains(Squares[i][j])) {
                            threat = Squares[i][j];
                            threats_WKing.remove(Squares[i][j]);
                        }

                        Piece p = Squares[i][j].getPiece();
                        Squares[i][j].replacePiece(sq.getPiece());
                        sq.removePiece();
                        if (cl.equals(Color.BLACK)) {
                            set_CheckedKing(null, Color.WHITE);
                            if (bKing_checked)
                                Squares[i][j].setStroke(Color.BLACK);
                            if (threat != null)
                                threats_Bking.add(threat);
                        } else {
                            set_CheckedKing(null, Color.BLACK);
                            if (wKing_checked)
                                Squares[i][j].setStroke(Color.BLACK);
                            if (threat != null)
                                threats_WKing.add(threat);

                        }
                        sq.addPiece(Squares[i][j].getPiece());
                        Squares[i][j].addPiece(p);
                    } else if (Squares[i][j].getStroke().equals(p_move)) {
                        if (Squares[i][j].getColor().equals(Color.BLACK) && threats_WKing.contains(Squares[i][j])) {
                            threat = Squares[i][j];
                            threats_WKing.remove(Squares[i][j]);
                        }

                        Squares[i][j].addPiece(sq.getPiece());
                        sq.removePiece();
                        if (cl.equals(Color.BLACK)) {
                            set_CheckedKing(null, Color.WHITE);
                            if (bKing_checked)
                                Squares[i][j].setStroke(Color.BLACK);
                            if (threat != null)
                                threats_Bking.add(threat);
                        } else {
                            set_CheckedKing(null, Color.BLACK);
                            if (wKing_checked)
                                Squares[i][j].setStroke(Color.BLACK);
                            if (threat != null)
                                threats_WKing.add(threat);
                        }
                    }

                    sq.addPiece(Squares[i][j].getPiece());
                    Squares[i][j].removePiece();
                } else {
                    if (Squares[i][j].getStroke().equals(p_kill)) {
                        System.out.println("iside showChecked moves kill.");
                        if (Squares[i][j].getColor().equals(Color.BLACK) && threats_WKing.contains(Squares[i][j])) {
                            threat = Squares[i][j];
                            threats_WKing.remove(Squares[i][j]);
                        } else if (Squares[i][j].getColor().equals(Color.WHITE) && threats_Bking.contains(Squares[i][j])) {
                            threat = Squares[i][j];
                            threats_Bking.remove(Squares[i][j]);
                        }

                        Piece piece = Squares[i][j].getPiece();
                        Squares[i][j].replacePiece(sq.getPiece());
                        sq.removePiece();


                        if (cl.equals(Color.BLACK)) {
                            set_CheckedKing(null, Color.WHITE);
                            if (bKing_checked)
                                Squares[i][j].setStroke(Color.BLACK);
                            if (threat != null)
                                threats_Bking.add(threat);
                        } else if (cl.equals(Color.WHITE)) {
                            System.out.println("iside showCheckedMoves kill col.white.");
                            set_CheckedKing(null, Color.BLACK);
                            System.out.println("iside showCheckedMoves kill col.white wKingchecked. " + wKing_checked);
                            if (wKing_checked)
                                Squares[i][j].setStroke(Color.BLACK);
                            if (threat != null)
                                threats_WKing.add(threat);
                        }

                        sq.addPiece(Squares[i][j].getPiece());
                        Squares[i][j].setPiece(piece);
                    } else if (Squares[i][j].getStroke().equals(p_move)) {
                        System.out.println("INside showMovesChecked move");
                        if (Squares[i][j].getColor().equals(Color.BLACK) && threats_WKing.contains(Squares[i][j])) {
                            System.out.println("INside showMovesChecked move threat remove" + threat);
                            threat = Squares[i][j];
                            threats_WKing.remove(Squares[i][j]);
                        } else if (Squares[i][j].getColor().equals(Color.WHITE) && threats_Bking.contains(Squares[i][j])) {
                            threat = Squares[i][j];
                            threats_Bking.remove(Squares[i][j]);
                        }
                        //System.out.println("move while checked: 2 " + Squares[i][j].getPosition());
                        Squares[i][j].addPiece(sq.getPiece());
                        sq.removePiece();

                        if (cl.equals(Color.BLACK)) {
                            System.out.println("threats wKing 1 :" + threats_WKing.size());
                            set_CheckedKing(null, Color.WHITE);
                            System.out.println("move while checked: 2 " + Squares[i][j].getPosition());
                            System.out.println(bKing_checked);
                            if (bKing_checked)
                                Squares[i][j].setStroke(Color.BLACK);
                            if (threat != null) {
                                System.out.println("INside showMovesChecked move threat add" + threat);
                                threats_Bking.add(threat);
                            }
                        } else if (cl.equals(Color.WHITE)) {
                            System.out.println("threats wKing 1 :" + threats_WKing.size());
                            Square thrseat = threats_WKing.get(0);
                            ArrayList<Square> threats_copy;
                            set_CheckedKing(null, Color.BLACK);
                            System.out.println("move while checked: 3 " + Squares[i][j].getPosition());
                            System.out.println(wKing_checked + "threat Size" + threats_WKing.size());
                            if (wKing_checked)
                                Squares[i][j].setStroke(Color.BLACK);
                            else
                                threats_WKing.add(thrseat);
                            System.out.println("threats wKing 2 :" + threats_WKing.size());
                            if (threat != null) {
                                threats_WKing.add(threat);
                                System.out.println("INside showMovesChecked move threat add" + threat + "\n");
                            }
                        }
                        sq.addPiece(Squares[i][j].getPiece());
                        Squares[i][j].removePiece();
                        //System.out.println("move while checked: 4");
                    }
                }
                if (cl.equals(Color.BLACK))
                    bKing_checked = true;
                else
                    wKing_checked = true;
            }
        }

    }



    private void set_CheckedKing(Square sq, Color col) {
        boolean WKChecked = false;
        boolean BKChecked = false;
        System.out.println("INside set_checkedKing Black");
        System.out.println("INside set_checkedKing Black size = " + threats_WKing.size());
        if (!(sq == null) && !threats_WKing.contains(sq)) {
            System.out.println("added to the threat list.");
            threats_WKing.add(sq);
        }
        for (int i = 0; i < threats_WKing.size(); i++) {
            System.out.println("inside the loop for the threat list");
            System.out.println("inside the loop for the threat list wking size 1: " + threats_WKing.size());
            if (check_for_Check(threats_WKing.get(i)))
                WKChecked = true;
            else {
                System.out.println("inside the loop for the threat list threat removed");
                threats_WKing.remove(i);
            }
        }
        System.out.println("inside the loop for the threat list wking size  2 :" + threats_WKing.size());
        wKing_checked = WKChecked;
        if (!(sq == null) && !threats_Bking.contains(sq)) {
            threats_Bking.add(sq);
        }
        for (int i = 0; i < threats_Bking.size(); i++) {
            if (check_for_Check(threats_Bking.get(i)))
                BKChecked = true;
            else
                threats_Bking.remove(i);
        }
        bKing_checked = BKChecked;
        System.out.println("return for check_for_check" + WKChecked);
    }

     */

    private void set_CheckedKing(Square sq) {
        if (sq != null && sq.getColor().equals(Color.BLACK)){
            if (sq.getPiece().kill(wKingsq) && !threats_WKing.containsKey(sq)){
                if (sq.getPiece() instanceof Knight) {
                    threats_WKing.put(sq, 0);
                    wKing_checked = true;
                    return;
                }
                threats_WKing.put(sq, -1);
                int dir = check_direction(sq , threats_WKing.get(sq));
                threats_WKing.put(sq, dir);
            }

        }
        else if (sq != null && sq.getColor().equals(Color.WHITE)){
            if (sq.getPiece().kill(bKingsq) && !threats_Bking.containsKey(sq)){
                if (sq.getPiece() instanceof Knight) {
                    threats_Bking.put(sq, 0);
                    bKing_checked = true;
                    return;
                }
                threats_Bking.put(sq, -1);
                int dir = check_direction(sq, threats_Bking.get(sq));
                threats_Bking.put(sq, dir);
            }
        }

        boolean WKChecked = false;
        boolean BKChecked = false;
        System.out.println("INside set_checkedKing Black");
        System.out.println("INside set_checkedKing Black size = " + threats_WKing.size());

    }

    /*
    private boolean check_for_Check(Square sq){
        if (sq.isChessPiece()) {
            Square enemy_King;
            if (sq.getColor().equals(Color.BLACK))
                enemy_King = wKingsq;
            else
                enemy_King = bKingsq;
            //if (sq.getPiece().kill(enemy_King)) ArrayList.add(sq.getPiece());

            if (sq.getPiece() instanceof Knight) {
                if (sq.getPiece().kill(enemy_King))
                    return true;
                else
                    return false;
            } else {
                if (sq.getPiece().kill(enemy_King)) {
                    int check_direction = 0;
                    getIndexSquares(enemy_King);
                    int eKing_row = row_num, eKing_column = column_num;
                    getIndexSquares(sq);
                    int r_diff = row_num - eKing_row, c_diff = column_num - eKing_column;
                    if (r_diff < 0 && c_diff < 0)
                        check_direction = 1;
                    else if (r_diff < 0 && c_diff == 0)
                        check_direction = 2;
                    else if (r_diff < 0 && c_diff > 0)
                        check_direction = 3;
                    else if (r_diff == 0 && c_diff > 0)
                        check_direction = 4;
                    else if (r_diff > 0 && c_diff > 0)
                        check_direction = 5;
                    else if (r_diff > 0 && c_diff == 0)
                        check_direction = 6;
                    else if (r_diff > 0 && c_diff < 0)
                        check_direction = 7;
                    else if (r_diff == 0 && c_diff < 0)
                        check_direction = 8;

                    System.out.println("Check Direction: " + check_direction);
                    for (int i = 1; i <= 8; i++) {
                        System.out.println("i: " + i);
                        try {
                            switch (check_direction) {
                                case 1:
                                    if (Squares[row_num + i][column_num + i].isChessPiece()) {
                                        return Squares[row_num + i][column_num + i].equals(enemy_King);
                                    }
                                    break;
                                case 2:
                                    if (Squares[row_num + i][column_num].isChessPiece()) {
                                        return Squares[row_num + i][column_num].equals(enemy_King);
                                    }
                                    break;
                                case 3:
                                    System.out.println("Case 3:");
                                    System.out.println("Is piece : " + (row_num - i) + (column_num + i) + Squares[row_num - i][column_num + i].isChessPiece());
                                    System.out.println("Is enemy King: " + Squares[row_num - i][column_num + i].equals(enemy_King));
                                    if (Squares[row_num + i][column_num - i].isChessPiece()) {
                                        return Squares[row_num + i][column_num - i].equals(enemy_King);
                                    }
                                    break;
                                case 4:
                                    if (Squares[row_num][column_num - i].isChessPiece()) {
                                        return Squares[row_num][column_num - i].equals(enemy_King);
                                    }
                                    break;
                                case 5:
                                    if (Squares[row_num - i][column_num - i].isChessPiece()) {
                                        return Squares[row_num - i][column_num - i].equals(enemy_King);
                                    }
                                    break;
                                case 6:
                                    if (Squares[row_num - i][column_num].isChessPiece()) {
                                        return Squares[row_num - i][column_num].equals(enemy_King);
                                    }
                                    break;
                                case 7:
                                    if (Squares[row_num - i][column_num + i].isChessPiece()) {
                                        return Squares[row_num - i][column_num].equals(enemy_King);
                                    }
                                    break;
                                case 8:
                                    if (Squares[row_num][column_num + i].isChessPiece()) {
                                        return Squares[row_num][column_num + i].equals(enemy_King);
                                    }
                                    break;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("threw a index out of bound");
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }


     */
    private int check_direction(Square sq, int direction) {
        Square enemy_King;
        boolean check = false;
        if (sq.getColor().equals(Color.BLACK))
            enemy_King = wKingsq;
        else
            enemy_King = bKingsq;
        //if (sq.getPiece().kill(enemy_King)) ArrayList.add(sq.getPiece())

        int check_direction = -1;
        if (direction == -1) {
            getIndexSquares(enemy_King);
            int eKing_row = row_num, eKing_column = column_num;
            getIndexSquares(sq);
            int r_diff = row_num - eKing_row, c_diff = column_num - eKing_column;
            if (r_diff < 0 && c_diff < 0)
                check_direction = 1;
            else if (r_diff < 0 && c_diff == 0)
                check_direction = 2;
            else if (r_diff < 0 && c_diff > 0)
                check_direction = 3;
            else if (r_diff == 0 && c_diff > 0)
                check_direction = 4;
            else if (r_diff > 0 && c_diff > 0)
                check_direction = 5;
            else if (r_diff > 0 && c_diff == 0)
                check_direction = 6;
            else if (r_diff > 0 && c_diff < 0)
                check_direction = 7;
            else if (r_diff == 0 && c_diff < 0)
                check_direction = 8;
        }
        return check_direction;
    }

    private boolean king_under_check(Square sq, int direction) {
        Square enemy_King;
        if (sq.getColor().equals(Color.BLACK))
            enemy_King = wKingsq;
        else
            enemy_King = bKingsq;

        if (sq.getPiece() instanceof Knight) {
            if (sq.getPiece().kill(enemy_King))
                return true;
        } else {
            //System.out.println("Check Direction: " + direction);
            for (int i = 1; i <= 8; i++) {
                //System.out.println("i: " + i);
                try {
                    switch (direction) {
                        case 1:
                            if (Squares[row_num + i][column_num + i].isChessPiece()) {
                                return Squares[row_num + i][column_num + i].equals(enemy_King);
                            }
                            break;
                        case 2:
                            if (Squares[row_num + i][column_num].isChessPiece()) {
                                return Squares[row_num + i][column_num].equals(enemy_King);
                            }
                            break;
                        case 3:
                            System.out.println("Case 3:");
                            System.out.println("Is piece : " + (row_num - i) + (column_num + i) + Squares[row_num - i][column_num + i].isChessPiece());
                            System.out.println("Is enemy King: " + Squares[row_num - i][column_num + i].equals(enemy_King));
                            if (Squares[row_num + i][column_num - i].isChessPiece()) {
                                return Squares[row_num + i][column_num - i].equals(enemy_King);
                            }
                            break;
                        case 4:
                            if (Squares[row_num][column_num - i].isChessPiece()) {
                                return Squares[row_num][column_num - i].equals(enemy_King);
                            }
                            break;
                        case 5:
                            if (Squares[row_num - i][column_num - i].isChessPiece()) {
                                return Squares[row_num - i][column_num - i].equals(enemy_King);
                            }
                            break;
                        case 6:
                            if (Squares[row_num - i][column_num].isChessPiece()) {
                                return Squares[row_num - i][column_num].equals(enemy_King);
                            }
                            break;
                        case 7:
                            if (Squares[row_num - i][column_num + i].isChessPiece()) {
                                return Squares[row_num - i][column_num].equals(enemy_King);
                            }
                            break;
                        case 8:
                            if (Squares[row_num][column_num + i].isChessPiece()) {
                                return Squares[row_num][column_num + i].equals(enemy_King);
                            }
                            break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("threw a index out of bound");
                    return false;
                }
            }
        }
        return false;
    }

    private void update_kingSq_helper(Square sq){
        if (sq.getPiece() instanceof King){
            if (sq.getColor().equals(Color.BLACK))
                wKingsq = sq;
            else
                bKingsq = sq;
        }
    }
}
