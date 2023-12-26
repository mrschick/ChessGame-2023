
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


public class Board extends GridPane {

    Color dark;
    Color light;
    Color background;

    private Text timerText;
    private Timeline timeline;
    private int remainingTime = 10;
    private Square clickedSqaure = null;
    private int clickCount = 0, row_num = 0, column_num = 0;
    private Square[][] Squares = new Square[8][8];
    private Color p_move = Color.YELLOW, p_kill = Color.GREEN, p_check = Color.RED;
    private boolean white_turn = false;

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
        for (int column = 0; column < 8; column++){
            Pawn bpawn = new Pawn(Color.BLACK);
            Pawn wpawn = new Pawn(Color.WHITE);

            Square bSQ = new Square(bpawn);
            bSQ.setPosition(String.valueOf((char) (column + 97)) + 7);
            bSQ.setOnMouseClicked(this::moveKillmethod);

            Square wSQ = new Square(wpawn);
            wSQ.setPosition(String.valueOf((char) (column + 97)) + 2);
            wSQ.setOnMouseClicked(this::moveKillmethod);

            if (column % 2 == 0){
                bSQ.setColor(dark);
                wSQ.setColor(light);
            }
            else{
                bSQ.setColor(light);
                wSQ.setColor(dark);
            }
            Squares[6][column] = wSQ;
            Squares[1][column] = bSQ;
        }

        //Adds Rook Squares to the table Squares[][]
        Rook bRook1 = new Rook(Color.BLACK);
        Square bRooksq1 = new Square(bRook1);
        bRooksq1.setPosition("a" +  8);
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
        Square bKnightsq2= new Square(bKnight2);
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
        wQueensq.setPosition("e" + 1);
        wQueensq.setOnMouseClicked(this::moveKillmethod);
        wQueensq.setColor(dark);
        Squares[7][4] = wQueensq;

        //adds the King Squares to table Squares[][]
        King bKing = new King(Color.BLACK);
        Square bKingsq = new Square(bKing);
        bKingsq.setPosition("e" + 8);
        bKingsq.setOnMouseClicked(this::moveKillmethod);
        bKingsq.setColor(light);
        Squares[0][4] = bKingsq;

        King wKing = new King(Color.WHITE);
        Square wKingsq = new Square(wKing);
        wKingsq.setPosition("d" + 1);
        System.out.println(wKingsq.getPosition());
        wKingsq.setOnMouseClicked(this::moveKillmethod);
        wKingsq.setColor(light);
        Squares[7][3] = wKingsq;

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

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
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
    private void moveKillmethod(MouseEvent event){
        clickCount++;
        if (clickCount == 1){
            clickedSqaure = (Square) event.getSource();
            getIndexSquares(clickedSqaure);
            if (clickedSqaure != null && clickedSqaure.isChessPiece()){
                if (white_turn && clickedSqaure.getPiece().getColor().equals(Color.WHITE))
                    showPieceMoves(clickedSqaure.getPiece(), row_num, column_num);
                else if (!(white_turn) && clickedSqaure.getPiece().getColor().equals(Color.BLACK)) {
                    showPieceMoves(clickedSqaure.getPiece(), row_num, column_num);
                }
                else
                    clickCount--;
            }
            else{
                clickCount--;
            }
        }
        else if (clickCount == 2){
            if (clickedSqaure != null && clickedSqaure.isChessPiece()) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (event.getSource() instanceof Square){
                            if (!(clickedSqaure.equals(event.getSource()))) {
                                if (event.getSource().equals(Squares[i][j])) {
                                    if (Squares[i][j].isChessPiece()){
                                        if (clickedSqaure.getPiece().kill(Squares[i][j]) && Squares[i][j].getStroke().equals(p_kill)) {
                                            Squares[i][j].replacePiece(clickedSqaure.getPiece());
                                            clickedSqaure.setPiece(null);
                                            clickedSqaure.setContains_chess_piece(false);
                                            clickedSqaure.removePiece();
                                            white_turn = !white_turn;
                                        }
                                    } else if (!(Squares[i][j].isChessPiece())) {
                                        if (clickedSqaure.getPiece().move(Squares[i][j]) && Squares[i][j].getStroke().equals(p_move)) {
                                            Squares[i][j].addPiece(clickedSqaure.getPiece());
                                            clickedSqaure.setPiece(null);
                                            clickedSqaure.setContains_chess_piece(false);
                                            clickedSqaure.removePiece();
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
                    boolean self = ((Square) event.getSource()).getPiece().equals(clickedSqaure.getPiece());
                    if (((Square) event.getSource()).getPiece().getColor().equals(clickedSqaure.getPiece().getColor()) && !self) {
                        moveKillmethod(event);
                    } else {
                        clickedSqaure = null;
                    }
                }
                catch (NullPointerException e){
                }
            }
        }
    }

    private void getIndexSquares(Square clickedSqaure){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (Squares[i][j].equals(clickedSqaure)){
                    row_num = i;
                    column_num = j;
                    break;
                }
            }
        }

    }

    //Submethod to show possible moves for a chess piece.
    private void showPieceMoves(Piece piece, int row_num, int column_num){
        boolean t_left = false, t = false, t_right = false;
        boolean right = false, left = false;
        boolean b_left = false, b = false, b_right = false;
        for (int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++){
                if (piece instanceof  Knight){
                    if (piece.kill(Squares[i - 1][j - 1])){
                        Squares[i - 1][j - 1].setStroke(Color.GREEN);
                    }
                    else if (piece.move(Squares[i - 1][j - 1])){
                        Squares[i - 1][j - 1].setStroke(Color.YELLOW);
                    }
                }
                else{
                    if (!t_left && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)){
                        try {
                            if (Squares[row_num - j][column_num - j].isChessPiece()){
                                if (Squares[row_num - j][column_num - j].getPiece().getColor().equals(piece.getColor()))
                                    t_left = true;
                            }
                            if (piece.kill(Squares[row_num - j][column_num - j])){
                                Squares[row_num - j][column_num - j].setStroke(p_kill);
                                t_left = true;
                            }
                            else if(piece.move(Squares[row_num - j][column_num - j])) {
                                Squares[row_num - j][column_num - j].setStroke(p_move);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            t_left = true;
                        }
                    }

                    if (!t && (piece instanceof Queen || piece instanceof Rook || piece instanceof King || piece instanceof Pawn)){

                        try{
                            if (Squares[row_num - j][column_num].isChessPiece()){
                                if (Squares[row_num - j][column_num].getPiece().getColor().equals(piece.getColor()))
                                    t = true;
                            }
                            if (piece.kill(Squares[row_num - j][column_num])){
                                Squares[row_num - j][column_num].setStroke(p_kill);
                                t = true;
                            }
                            else if(piece.move(Squares[row_num - j][column_num]))
                                Squares[row_num - j][column_num].setStroke(p_move);
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            t = true;
                        }
                    }

                    if (!t_right && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)){
                        try{
                            if (Squares[row_num - j][column_num + j].isChessPiece()){
                                if (Squares[row_num - j][column_num + j].getPiece().getColor().equals(piece.getColor()))
                                    t_right = true;
                            }
                            if (piece.kill(Squares[row_num - j][column_num + j])){
                                Squares[row_num - j][column_num + j].setStroke(p_kill);
                                t_right = true;
                            }
                            else if(piece.move(Squares[row_num -j][column_num + j]))
                                Squares[row_num - j][column_num + j].setStroke(p_move);
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            t_right = true;
                        }
                    }

                    if (!right && (piece instanceof Queen || piece instanceof King || piece instanceof Rook)){
                        try {
                            if (Squares[row_num][column_num + j].isChessPiece()){
                                if (Squares[row_num][column_num + j].getPiece().getColor().equals(piece.getColor()))
                                    right = true;
                            }
                            if (piece.kill(Squares[row_num][column_num + j])){
                                Squares[row_num][column_num + j].setStroke(p_kill);
                                right = true;
                            }
                            else if(piece.move(Squares[row_num][column_num + j]))
                                Squares[row_num][column_num + j].setStroke(p_move);
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            right = true;
                        }
                    }

                    if (!left && (piece instanceof Queen || piece instanceof King || piece instanceof Rook)){
                        try {
                            if (Squares[row_num][column_num - j].isChessPiece()){
                                if (Squares[row_num][column_num - j].getPiece().getColor().equals(piece.getColor()))
                                    left = true;
                            }
                            if (piece.kill(Squares[row_num][column_num - j])){
                                Squares[row_num][column_num - j].setStroke(p_kill);
                                left = true;
                            }
                            else if(piece.move(Squares[row_num][column_num - j]))
                                Squares[row_num][column_num - j].setStroke(p_move);
                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            left = true;
                        }
                    }

                    if (!b_left && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King  || piece instanceof Pawn)){
                        try {
                            if (Squares[row_num + j][column_num - j].isChessPiece()){
                                if (Squares[row_num + j][column_num - j].getPiece().getColor().equals(piece.getColor()))
                                    b_left = true;
                            }
                            if (piece.kill(Squares[row_num  + j][column_num - j])){
                                Squares[row_num + j][column_num - j].setStroke(p_kill);
                                b_left = true;
                            }
                            else if(piece.move(Squares[row_num + j][column_num - j])) {
                                Squares[row_num + j][column_num - j].setStroke(p_move);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            b_left = true;
                        }
                    }

                    if (!b && (piece instanceof Queen || piece instanceof King || piece instanceof Rook || piece instanceof Pawn)){
                        try{
                            if (Squares[row_num + j][column_num].isChessPiece()){
                                if (Squares[row_num + j][column_num].getPiece().getColor().equals(piece.getColor())) {
                                    b = true;
                                }
                            }
                            if (piece.kill(Squares[row_num + j][column_num])){
                                Squares[row_num + j][column_num].setStroke(p_kill);
                                b = true;
                            }
                            else if(piece.move(Squares[row_num + j][column_num])) {
                                Squares[row_num + j][column_num].setStroke(p_move);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            b = true;
                        }
                    }

                    if (!b_right && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King  || piece instanceof Pawn)){
                        try {
                            if (Squares[row_num + j][column_num + j].isChessPiece()){
                                if (Squares[row_num + j][column_num + j].getPiece().getColor().equals(piece.getColor()))
                                    b_right = true;
                            }
                            if (piece.kill(Squares[row_num + j][column_num + j])) {
                                Squares[row_num + j][column_num + j].setStroke(p_kill);
                                b_right = true;
                            }
                            else if (piece.move(Squares[row_num + j][column_num + j])) {
                                Squares[row_num + j][column_num + j].setStroke(p_move);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            b_right = true;
                        }
                    }
                }

            }
        }
    }
}