import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.animation.Timeline;
import java.util.*;


public class Board extends StackPane {

    Color dark, light, background;
    private Square clickedSquare = null;
    private int clickCount = 0, row_num = 0, column_num = 0;
    private Square[][] Squares = new Square[8][8];
    private final Color p_move = Color.GOLD;
    private final Color p_kill = Color.DARKGREEN;
    private final Color p_check = Color.RED;
    private boolean white_turn = false;
    private Square wKingsq, bKingsq;
    private int bKing_row, bKing_col;
    boolean wKing_checked, bKing_checked;
    private Rook wRook1, bRook1;
    private Bishop bBishop1, wBishop1;
    private Queen bQueen, wQueen;
    private Knight wKnight1, bKnight1;
    private GridPane gridPane;
    private Button bishop_Button, knight_Button, queen_Button, rook_Button;
    private VBox vBox;

    Map<Square, Integer> threats_Wking = new HashMap<>();
    Map<Square, Integer> threats_Bking = new HashMap<>();

    //Constructor for the Chess Board
    public Board(ColorScheme colorScheme) {

        gridPane = new GridPane();
        this.dark = colorScheme.dark;
        this.light = colorScheme.light;
        this.background = colorScheme.boardBg;
        setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));

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
        bRook1 = new Rook(Color.BLACK);
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

        wRook1 = new Rook(Color.WHITE);
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
        bKnight1 = new Knight(Color.BLACK);
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

        wKnight1 = new Knight(Color.WHITE);
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
        bBishop1 = new Bishop(Color.BLACK);
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

        wBishop1 = new Bishop(Color.WHITE);
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
        bQueen = new Queen(Color.BLACK);
        Square bQueensq = new Square(bQueen);
        bQueensq.setPosition("d" + 8);
        bQueensq.setOnMouseClicked(this::moveKillmethod);
        bQueensq.setColor(dark);
        Squares[0][3] = bQueensq;

        wQueen = new Queen(Color.WHITE);
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

        //Adds empty Squares to the table Squares[][]
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

        //Adds the elements of 2d array Squares to the Gridpane with correct Positioning
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gridPane.add(Squares[i][j], j + 1, i + 1);
            }
        }


        for (int i = 0; i < 8; i++) {
            Text text = new Text(Character.toString((char) ('a' + i)));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            text.setFill(Color.BLACK);
            gridPane.add(text, i + 1, 0);
            gridPane.setHalignment(text, HPos.CENTER);
            gridPane.setValignment(text, VPos.CENTER);
        }

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Integer.toString(8 - i));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            text.setFill(Color.BLACK);
            gridPane.add(text, 0, i + 1);
            gridPane.setHalignment(text, HPos.CENTER);
            gridPane.setValignment(text, VPos.CENTER);
        }
        getChildren().add(gridPane);
    }

    /*This method suggests possible Chess piece moves and handles the movement of pieces on Chess Board.
    It keeps an account of MouseClicks by a user and displays the possible moves on the first MouseClick
    or moves the chess piece on the second MouseClick.
     */
    private void moveKillmethod(MouseEvent event) {
        clickCount++;
        //On the first MouseClick
        if (clickCount == 1) {
            clickedSquare = (Square) event.getSource();
            getIndexSquares(clickedSquare);
            if (clickedSquare != null && clickedSquare.isChessPiece()) {
                //On White player's turn
                if (white_turn && clickedSquare.getColor().equals(Color.WHITE)) {
                    showPieceMoves(clickedSquare, row_num, column_num);
                    if (wKing_checked) {
                        wKingsq.setStroke(Color.RED);
                    }
                }
                //On black player's turn
                else if (!(white_turn) && clickedSquare.getColor().equals(Color.BLACK)) {
                    showPieceMoves(clickedSquare, row_num, column_num);
                    if (bKing_checked) {
                        bKingsq.setStroke(Color.RED);
                    }
                } else
                    clickCount--;
            } else {
                clickCount--;
            }
        }
        //On the second MouseClick.
        else if (clickCount == 2) {
            if (clickedSquare != null && clickedSquare.isChessPiece()) {
                //loops to iterate over the entire table Squares[][]
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (event.getSource() instanceof Square) {
                            //If the move is not canceled by clicking on the same piece twice
                            if (!(clickedSquare.equals(event.getSource()))) {
                                if (event.getSource().equals(Squares[i][j])) {
                                    //If the destination Square is a ChessPiece and the move is valid, a pieceKill will be implemented
                                    if (Squares[i][j].isChessPiece()) {
                                        if (clickedSquare.getPiece().kill(Squares[i][j]) && Squares[i][j].getStroke().equals(p_kill)) {
                                            Squares[i][j].replacePiece(clickedSquare.getPiece());
                                            clickedSquare.removePiece();
                                            update_threat_list(Squares[i][j]);
                                            update_kingSq_helper(Squares[i][j]);
                                            check_for_promotion(Squares[i][j], i);
                                            white_turn = !white_turn;
                                        }
                                    }
                                    //If the destination Square is not a ChessPiece and the move is valid, a pieceMove will be implemented
                                    else if (!(Squares[i][j].isChessPiece())) {
                                        if (clickedSquare.getPiece().move(Squares[i][j]) && Squares[i][j].getStroke().equals(p_move)) {
                                            Squares[i][j].addPiece(clickedSquare.getPiece());
                                            clickedSquare.removePiece();
                                            update_kingSq_helper(Squares[i][j]);
                                            update_threat_list(Squares[i][j]);
                                            check_for_promotion(Squares[i][j], i);
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
            //In case of invalid First or Second MouseClick, the clickcount is reset to zero.
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

    //An auxiliary method to get the indexes of a Square in the Squares[][]
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

    /*Method to show all possible moves for a chess piece.
    Checks all squares in Squares[][] for a possible kill or move. Starts searching from the neighbouring squares
    and gradually spreads out to all Diagnol and Cardinal directions using the possible move and kill directions of the clickedSquare.
    When encounters a square with a chessPiece, determines if it is an ally or an enemy, then stops searching ahead in
    that directions. Uses booleans to keep track of what direction has been exhausted.
    Reduces time complexity by selectively iterating over the board, only choosing directions relative
     to the clickedSquare.
     */
    private void showPieceMoves(Square clickedSquare, int row_num, int column_num) {
        Piece piece = clickedSquare.getPiece();
        Color color = piece.getColor();
        if (color.equals(Color.BLACK) && bKing_checked) {
            bKingsq.setStroke(p_check);
        } else if (color.equals(Color.WHITE) && wKing_checked) {
            wKingsq.setStroke(p_check);
        }
        //Possible Moves for Knights since their movements aren't necessarily prohibited by blocking chess Pieces.
        if (piece instanceof Knight) {
            for (int i = -2 ;i <=2; i++) {
                for (int j = -2; j <= 2; j++) {
                    try{
                        if ((i == -2 || i == 2) && (j == -1 || j == 1)){
                            if (Squares[row_num + i][column_num + j].isChessPiece()) {
                                if (piece.kill(Squares[row_num + i][column_num + j])){
                                    if (valid_move(clickedSquare, Squares[row_num + i][column_num + j]))
                                        Squares[row_num + i][column_num + j].setStroke(p_kill);
                                }
                            } else if (piece.move(Squares[row_num + i][column_num + j])) {
                                if (valid_move(clickedSquare, Squares[row_num + i][column_num + j]))
                                    Squares[row_num + i][column_num + j].setStroke(p_move);
                            }
                        }
                        else if ((i == -1 || i == 1) && (j == -2 || j == 2)) {
                            if (Squares[row_num + i][column_num + j].isChessPiece()) {
                                if (piece.kill(Squares[row_num + i][column_num + j])){
                                    if (valid_move(clickedSquare, Squares[row_num + i][column_num + j]))
                                        Squares[row_num + i][column_num + j].setStroke(p_kill);
                                }
                            } else if (piece.move(Squares[row_num + i][column_num + j])) {
                                if (valid_move(clickedSquare, Squares[row_num + i][column_num + j]))
                                    Squares[row_num + i][column_num + j].setStroke(p_move);
                            }
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                    }
                }
            }
        }
        else{
            //booleans for all diagnol and cardinal directions.
            boolean t_left = false, t = false, t_right = false;
            boolean right = false, left = false;
            boolean b_left = false, b = false, b_right = false;
            //loop to iterate over next 8 possible Squares in all directions.
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    //Checks in the top left direction if the clickedSquare can legally move towards top left.
                    if (!t_left && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)) {
                        // try block to catch when an out of bound index call on Squares[][]
                        try {
                            //If the square being iterated over is a chess piece, then checks if it can be killed and
                            //is a valid move otherwise if not a valid move stops looking further this direction
                            if (Squares[row_num - j][column_num - j].isChessPiece()) {
                                if (piece.kill(Squares[row_num - j][column_num - j])){
                                    if (valid_move(clickedSquare, Squares[row_num - j][column_num - j]))
                                        Squares[row_num - j][column_num - j].setStroke(p_kill);
                                }
                                t_left = true;
                            }
                            //if not a chess piece, checks if it is a possible move for the clickedSquare.
                            else if (piece.move(Squares[row_num - j][column_num - j])) {
                                if (valid_move(clickedSquare, Squares[row_num - j][column_num - j]))
                                    Squares[row_num - j][column_num - j].setStroke(p_move);
                            }
                        }
                        //this exception means we have reached the end of the board and we don't need to look any
                        //further, hence sets the boolean t_left to true to not execute this block of code in
                        // the next iteration.
                        catch (ArrayIndexOutOfBoundsException e) {
                            t_left = true;
                        }
                    }
                    //Checks in the top direction if the clickedSquare can legally move towards the top.
                    if (!t && (piece instanceof Queen || piece instanceof Rook || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num - j][column_num].isChessPiece()) {
                                if (piece.kill(Squares[row_num - j][column_num])){
                                    if (valid_move(clickedSquare, Squares[row_num - j][column_num]))
                                        Squares[row_num - j][column_num].setStroke(p_kill);
                                }
                                t = true;
                            } else if (piece.move(Squares[row_num - j][column_num])) {
                                if (valid_move(clickedSquare, Squares[row_num - j][column_num]))
                                    Squares[row_num - j][column_num].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            t = true;
                        }
                    }
                    //Checks in the top right direction if the clickedSquare can legally move towards the top right.
                    if (!t_right && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num - j][column_num + j].isChessPiece()) {
                                if (piece.kill(Squares[row_num - j][column_num + j])){
                                    if (valid_move(clickedSquare, Squares[row_num - j][column_num + j]))
                                        Squares[row_num - j][column_num + j].setStroke(p_kill);
                                }
                                t_right = true;
                            } else if (piece.move(Squares[row_num - j][column_num + j])) {
                                if (valid_move(clickedSquare, Squares[row_num - j][column_num + j]))
                                    Squares[row_num - j][column_num + j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            t_right = true;
                        }
                    }
                    //Checks in the right direction if the clickedSquare can legally move towards the right.
                    if (!right && (piece instanceof Queen || piece instanceof King || piece instanceof Rook)) {
                        try {
                            if (Squares[row_num][column_num + j].isChessPiece()) {
                                if (piece.kill(Squares[row_num][column_num + j])){
                                    if (valid_move(clickedSquare, Squares[row_num][column_num + j]))
                                        Squares[row_num][column_num + j].setStroke(p_kill);
                                }
                                right = true;
                            } else if (piece.move(Squares[row_num][column_num + j])) {
                                if (valid_move(clickedSquare, Squares[row_num][column_num + j]))
                                    Squares[row_num][column_num + j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            right = true;
                        }
                    }
                    //Checks in the left direction if the clickedSquare can legally move towards the left.
                    if (!left && (piece instanceof Queen || piece instanceof King || piece instanceof Rook)) {
                        try {
                            if (Squares[row_num][column_num - j].isChessPiece()) {
                                if (piece.kill(Squares[row_num][column_num - j])){
                                    if (valid_move(clickedSquare, Squares[row_num][column_num - j]))
                                        Squares[row_num][column_num - j].setStroke(p_kill);
                                }
                                left = true;
                            } else if (piece.move(Squares[row_num][column_num - j])) {
                                if (valid_move(clickedSquare, Squares[row_num][column_num - j]))
                                    Squares[row_num][column_num - j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            left = true;
                        }
                    }
                    //Checks in the bottom left direction if the clickedSquare can legally move towards the bottom left.
                    if (!b_left && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num + j][column_num - j].isChessPiece()) {
                                if (piece.kill(Squares[row_num + j][column_num - j])){
                                    if (valid_move(clickedSquare, Squares[row_num + j][column_num - j]))
                                        Squares[row_num + j][column_num - j].setStroke(p_kill);
                                }
                                b_left = true;
                            } else if (piece.move(Squares[row_num + j][column_num - j])) {
                                if (valid_move(clickedSquare, Squares[row_num + j][column_num - j]))
                                    Squares[row_num + j][column_num - j].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            b_left = true;
                        }
                    }
                    //Checks in the bottom direction if the clickedSquare can legally move towards the bottom.
                    if (!b && (piece instanceof Queen || piece instanceof King || piece instanceof Rook || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num + j][column_num].isChessPiece()) {
                                if (piece.kill(Squares[row_num + j][column_num])){
                                    if (valid_move(clickedSquare, Squares[row_num + j][column_num]))
                                        Squares[row_num + j][column_num].setStroke(p_kill);
                                }
                                b = true;
                            } else if (piece.move(Squares[row_num + j][column_num])) {
                                if (valid_move(clickedSquare, Squares[row_num + j][column_num]))
                                    Squares[row_num + j][column_num].setStroke(p_move);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            b = true;
                        }
                    }
                    //Checks in the bottom right direction if the clickedSquare can legally move towards the bottom right.
                    if (!b_right && (piece instanceof Queen || piece instanceof Bishop || piece instanceof King || piece instanceof Pawn)) {
                        try {
                            if (Squares[row_num + j][column_num + j].isChessPiece()) {
                                if (piece.kill(Squares[row_num + j][column_num + j])){
                                    if (valid_move(clickedSquare, Squares[row_num + j][column_num + j]))
                                        Squares[row_num + j][column_num + j].setStroke(p_kill);
                                }
                                b_right = true;
                            } else if (piece.move(Squares[row_num + j][column_num + j])) {
                                if (valid_move(clickedSquare, Squares[row_num + j][column_num + j]))
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
    Keeps a track of the possible and real threats to both Kings. Adds these threats in an ArrayList and iterates over
    them after each enemy move to either add or remove a threat, or to put the king under Check.
     */
    private void update_threat_list(Square sq) {
        if (sq != null && sq.getColor().equals(Color.BLACK)) {
            if (sq.isChessPiece() && sq.getPiece().kill(wKingsq) && !threats_Wking.containsKey(sq)) {
                if (sq.getPiece() instanceof Knight) {
                    threats_Wking.put(sq, 0);
                    wKing_checked = true;
                }
                else {
                    threats_Wking.put(sq, -1);
                    int dir = check_direction(sq, threats_Wking.get(sq));
                    threats_Wking.put(sq, dir);
                }
            }

            boolean check = false;
            // Using a list to remove to avoid ConcurrentModificationException.
            ArrayList<Square> toRemove = new ArrayList<>();
            for (var entry : threats_Wking.entrySet()) {
                if (entry.getKey().isChessPiece() && entry.getKey().getPiece().kill(wKingsq)) {
                    if (king_under_check(entry.getKey(), entry.getValue(), false)) {
                        check = true;
                    }
                } else {
                    toRemove.add(entry.getKey());
                }
            }
            toRemove.forEach(k -> threats_Wking.remove(k));
            wKing_checked = check;
            if (wKing_checked){
                isaCheckMate(Color.WHITE);
            }
        }
        //if the threat to be added is White then its added as a threat to the black king.
        else if (sq != null && sq.getColor().equals(Color.WHITE)){
            if (sq.isChessPiece() && sq.getPiece().kill(bKingsq) && !threats_Bking.containsKey(sq)){
                //if instance of knight, no need to calculate check direction, as it is a direct check
                if (sq.getPiece() instanceof Knight) {
                    threats_Bking.put(sq, 0);
                    bKing_checked = true;
                }
                else {
                    threats_Bking.put(sq, -1);
                    int dir = check_direction(sq, threats_Bking.get(sq));
                    threats_Bking.put(sq, dir);
                }
            }

            boolean check = false;
            ArrayList<Square> toRemove = new ArrayList<>();
            for (var entry : threats_Bking.entrySet()){
                if (entry.getKey().isChessPiece() && entry.getKey().getPiece().kill(bKingsq)) {
                    if (king_under_check(entry.getKey(), entry.getValue(), false)) {
                        check = true;
                    }
                } else {
                    toRemove.add(entry.getKey());
                }
            }
            toRemove.forEach(k -> threats_Bking.remove(k));
            bKing_checked = check;
            if (bKing_checked){
                isaCheckMate(Color.BLACK);
            }
        }
    }

        /*An auxiliary method that allows or disallows a move depending on if the move puts your King in Check or
        if it gets out your already checked King from the check. For this method moves the pieces around to test
        for validity and resets the pieces to their original configuration after.
         */
        private boolean valid_move(Square origin, Square target){
            //saves the configuration of both squares for a later reset.
            Piece t_piece = target.getPiece();
            Piece o_piece = origin.getPiece();
            //makes the move
            target.addPiece(o_piece);
            origin.removePiece();
            boolean isKing = false;

        //If Selected piece is the King
        //This helps prohibit moving the King into a Check
        if (o_piece instanceof King){
            isKing = true;
            update_kingSq_helper(target);
        }

            //if the clickedSquare piece is Black.
            if (o_piece.getColor().equals(Color.BLACK)){
                //If the moving piece is a king, this code prevents it from walking into a check by an enemy piece
                // that was not in its threat list previously
                if (isKing){
                    for (int i = 0; i < 8; i++){
                        for (int j = 0; j < 8; j++){
                            //Finds an enemy piece that is a threat to the king after the move.
                            if (Squares[i][j].isChessPiece() && Squares[i][j].getColor().equals(Color.WHITE)){
                                if (Squares[i][j].getPiece().kill(bKingsq)) {
                                    //adds the new threat to the threat list.
                                    if (Squares[i][j].getPiece() instanceof Knight)
                                        threats_Bking.put(Squares[i][j], check_direction(Squares[i][j], 0));
                                    else
                                        threats_Bking.put(Squares[i][j], check_direction(Squares[i][j], -1));
                                }
                            }
                        }
                    }
                }
                //iterates over each threat to see if any of them still threatens or newly threatens their enemy king.
                for (var  entry : threats_Bking.entrySet()){
                    //If ally king is still in check after move return false to indicate non-valid move.
                    if (king_under_check(entry.getKey(), entry.getValue(), isKing)) {
                        //Board reset to original configuration.
                        origin.addPiece(o_piece);
                        if (t_piece == null)
                            target.removePiece();
                        else
                            target.addPiece(t_piece);
                        update_kingSq_helper(origin);
                        return false;
                    }
                }
            }
            //If the clickedSquare piece is White.
            else{
                //If the moving piece is a king, this code prevents it from walking into a check by an enemy piece
                // that was not in its threat list previously
                if (isKing){
                    for (int i = 0; i < 8; i++){
                        for (int j = 0; j < 8; j++){
                            if (Squares[i][j].isChessPiece() && Squares[i][j].getColor().equals(Color.BLACK)){
                                if (Squares[i][j].getPiece().kill(bKingsq)) {
                                    if (Squares[i][j].getPiece() instanceof Knight)
                                        threats_Wking.put(Squares[i][j], check_direction(Squares[i][j], 0));
                                    else
                                        threats_Wking.put(Squares[i][j], check_direction(Squares[i][j], -1));
                                }
                            }
                        }
                    }
                }
                for (var  entry : threats_Wking.entrySet()){
                    if (king_under_check(entry.getKey(), entry.getValue(), isKing)) {
                        origin.addPiece(o_piece);
                        if (t_piece == null)
                            target.removePiece();
                        else
                            target.addPiece(t_piece);
                        update_kingSq_helper(origin);
                        return false;
                    }
                }
            }
            //Resetting to original configuration.
            origin.addPiece(o_piece);
            if (t_piece == null)
                target.removePiece();
            else
                target.addPiece(t_piece);
            update_kingSq_helper(origin);
            return true;
        }

    //An auxiliary method that checks the Cardinal or Diagnol direction from which a threat threatens a King.
    //Uses a numbering system of 8 to denote these directions, starting with North-west as 1 and
    // increasing by 1 clockwise.
    private int check_direction(Square sq, int direction) {
        Square enemy_King;
        if (sq.getColor().equals(Color.BLACK))
            enemy_King = wKingsq;
        else
            enemy_King = bKingsq;

        int check_direction = -1;
        if (direction == -1) {
            getIndexSquares(enemy_King);
            int eKing_row = row_num, eKing_column = column_num;
            getIndexSquares(sq);
            //r_diff and c_diff are greater than 0 if the king has a bigger row_num and column_num than a threat.
            // The combination of >,< and = between these differences is used to assign attacking direction to a threat.
            int r_diff = row_num - eKing_row, c_diff = column_num - eKing_column;
            //Comments indicate the direction in which the Attacking piece is present relative to King.
            //Top Right
            if (r_diff < 0 && c_diff < 0)
                check_direction = 1;
                //Top
            else if (r_diff < 0 && c_diff == 0)
                check_direction = 2;
                //Top Left
            else if (r_diff < 0 && c_diff > 0)
                check_direction = 3;
                //Right
            else if (r_diff == 0 && c_diff > 0)
                check_direction = 4;
                //Bottom Right
            else if (r_diff > 0 && c_diff > 0)
                check_direction = 5;
                //Bottom
            else if (r_diff > 0 && c_diff == 0)
                check_direction = 6;
                //Bottom Left
            else if (r_diff > 0 && c_diff < 0)
                check_direction = 7;
                //Left
            else if (r_diff == 0 && c_diff < 0)
                check_direction = 8;
        }
        return check_direction;
    }

    //An auxiliary method that checks if a threat has put a king under check.
    private boolean king_under_check(Square sq, int direction, boolean isKing) {
        getIndexSquares(sq);
        Square enemy_King;
        //Choosing the enemyKing bases on clickedSquare piece's color
        if (sq.getColor().equals(Color.BLACK))
            enemy_King = wKingsq;
        else
            enemy_King = bKingsq;
        if (isKing){
            if (sq.getPiece().kill(enemy_King)){
                int dir = check_direction(sq, -1);
                return king_under_check(sq, dir, false);
            }
            else
                return false;
        }

        //Since a Knight can move over pieces, its kill method return if true, always means a check on the enemy king.
        if (sq.getPiece() instanceof Knight) {
            if (sq.getPiece().kill(enemy_King))
                return true;
        } else {
            //Uses a for loop to iterate over at max 8 squares in the check direction to find if there is a chess piece
            // in between the king or not. With this a check can be determined.
            for (int i = 1; i <= 8; i++) {
                try {
                    switch (direction) {
                        //Comments indicate the direction in which the enemy King might be relative to the attacking piece.
                        //Top Right
                        case 1:
                            if (Squares[row_num + i][column_num + i].isChessPiece()) {
                                return Squares[row_num + i][column_num + i].equals(enemy_King);
                            }
                            break;
                        //Bottom
                        case 2:
                            if (Squares[row_num + i][column_num].isChessPiece()) {
                                return Squares[row_num + i][column_num].equals(enemy_King);
                            }
                            break;
                        //Bottom Left
                        case 3:
                            if (Squares[row_num + i][column_num - i].isChessPiece()) {
                                return Squares[row_num + i][column_num - i].equals(enemy_King);
                            }
                            break;
                        //Left
                        case 4:
                            if (Squares[row_num][column_num - i].isChessPiece()) {
                                return Squares[row_num][column_num - i].equals(enemy_King);
                            }
                            break;
                        //Top Left
                        case 5:
                            if (Squares[row_num - i][column_num - i].isChessPiece()) {
                                return Squares[row_num - i][column_num - i].equals(enemy_King);
                            }
                            break;
                        //Top
                        case 6:
                            if (Squares[row_num - i][column_num].isChessPiece()) {
                                return Squares[row_num - i][column_num].equals(enemy_King);
                            }
                            break;
                        //Top Right
                        case 7:
                            if (Squares[row_num - i][column_num + i].isChessPiece()) {
                                return Squares[row_num - i][column_num + i].equals(enemy_King);
                            }
                            break;
                        //Right
                        case 8:
                            if (Squares[row_num][column_num + i].isChessPiece()) {
                                return Squares[row_num][column_num + i].equals(enemy_King);
                            }
                            break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    return false;
                }
            }
        }
        return false;
    }

    // Promotes any pawn that reaches the opposite end of the chess board to a Queen.
    private void check_for_promotion(Square sq, int row){
        if (sq.getPiece() instanceof Pawn){
            if (sq.getColor().equals(Color.BLACK) && row == 7){
                check_for_promotion_helper(sq);
            }
            else if (sq.getColor().equals(Color.WHITE) && row == 0){
                check_for_promotion_helper(sq);
            }
        }

    }

    //This method creates new window to ask the player to select a piece they want to promote their pawn to.
    private void check_for_promotion_helper(Square sq){
        //Flowpane to display promotion options.
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        try {
            //Importing Chesspiece images to display them on the Buttons
            Image rook, bishop, queen, knight;
            //If pawn is Black
            if (sq.getColor().equals(Color.BLACK)) {
                rook = new Image(bRook1.getImageAddress());
                bishop = new Image(bBishop1.getImageAddress());
                queen = new Image(bQueen.getImageAddress());
                knight = new Image(bKnight1.getImageAddress());
            }
            //If pawn is White
            else {
                rook = new Image(wRook1.getImageAddress());
                bishop = new Image(wBishop1.getImageAddress());
                queen = new Image(wQueen.getImageAddress());
                knight = new Image(wKnight1.getImageAddress());
            }
            //Creating promotion Buttons
            rook_Button = new Button();
            rook_Button.setOnMouseClicked(e -> promotion(e, sq));
            ImageView r_view = new ImageView(rook);
            rook_Button.setGraphic(r_view);

            bishop_Button = new Button();
            bishop_Button.setOnMouseClicked(e -> promotion(e, sq));
            ImageView b_view = new ImageView(bishop);
            bishop_Button.setGraphic(b_view);

            knight_Button = new Button();
            knight_Button.setOnMouseClicked(e -> promotion(e, sq));
            ImageView k_view = new ImageView(knight);
            knight_Button.setGraphic(k_view);

            queen_Button = new Button();
            queen_Button.setOnMouseClicked(e -> promotion(e, sq));
            ImageView q_view = new ImageView(queen);
            queen_Button.setGraphic(q_view);
            //buttons added to flowpane
            pane.getChildren().addAll(rook_Button, queen_Button, bishop_Button, knight_Button);
            //Vbox to contain a display text and the flowpane
            vBox = new VBox(10);
            Text txt = new Text("Select the Piece You want to Promote to");
            txt.setFill(Color.DARKRED);
            txt.setFont(Font.font("Comic Sans MS", 25));
            vBox.getChildren().add(txt);
            vBox.getChildren().add(pane);
            vBox.setMaxSize(500, 160);
            vBox.setMinSize(500, 150);
            vBox.setAlignment(Pos.CENTER);
            vBox.setBackground(new Background(
                    new BackgroundFill(Color.rgb(180,133,63),
                            new CornerRadii(10),
                            Insets.EMPTY)));
            BorderStroke borderStroke = new BorderStroke(
                    Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    new BorderWidths(3)
            );
            vBox.setBorder(new Border(borderStroke));
            gridPane.setDisable(true);
            getChildren().add(vBox);

        } catch (NullPointerException n) {
            n.printStackTrace();
        }
    }

    //If a king is under check, check all possible moves for all the chess pieces of that color.If it finds a possible
    //move, then returns. Otherwise, ends the game.
    private void isaCheckMate(Color color){
        setStrokeBlack();
        //First 2 nested loops to find and iterate over all ally chess pieces of the king under Check.
        for (int i = 0 ; i < 8; i++){
            for (int j = 0; j < 8; j++){
                //When an ally is found
                if (Squares[i][j].isChessPiece() && Squares[i][j].getColor().equals(color)){
                    showPieceMoves(Squares[i][j],i, j);
                    //These 2 nested loops are used to iterate over Squares[][] and
                    // see if a valid move is available for Square[i][j].
                    for (int k = 0 ; k < 8; k++){
                        for (int l = 0; l < 8; l++) {
                            //when move is found
                            if (Squares[k][l].getStroke().equals(p_move) || Squares[k][l].getStroke().equals(p_kill)){
                                setStrokeBlack();
                                return;
                            }
                        }
                    }
                    setStrokeBlack();
                }
            }
        }

        //If move is not found, the defending player is under Checkmate. This code creates
        // a new VBox pane to display a Game Over prompt.
        try{
            VBox pane = new VBox();
            Text txt = new Text("CHECK MATE");
            txt.setFill(Color.DARKRED);
            txt.setFont(Font.font("Comic Sans MS", 30));
            Text txt2;
            if (color.equals(Color.BLACK)){
                txt2 = new Text("BLACK WINS !!!");
                txt2.setFill(Color.BLACK);
            }
            else{
                txt2 = new Text("WHITE WINS !!!");
                txt2.setFill(Color.WHITE);
            }
            txt2.setFont(Font.font("Comic Sans MS" , 30));
            pane.setAlignment(Pos.CENTER);
            pane.getChildren().add(txt);
            pane.getChildren().add(txt2);
            pane.setMaxSize(300, 100);
            pane.setMinSize(300, 100);

            pane.setBackground(new Background(
                    new BackgroundFill(Color.rgb(180,133,63),
                            new CornerRadii(10),
                            Insets.EMPTY)));
            BorderStroke borderStroke = new BorderStroke(
                    Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(10),
                    new BorderWidths(3)
            );
            pane.setBorder(new Border(borderStroke));
            gridPane.setDisable(true);
            getChildren().add(pane);

        }
        catch (NullPointerException e){
        }
    }

    //If a pawn reaches the opposite end, this method promotes it to a Chess piece selected by the Player.
    private void promotion(MouseEvent event, Square sq){
        Color color = sq.getPiece().getColor();
        Piece piece = null;
        gridPane.setDisable(true);
        if (event.getSource().equals(queen_Button)){
            piece = new Queen(color);
            getChildren().remove(vBox);
            gridPane.setDisable(false);
        }
        else if (event.getSource().equals(bishop_Button)){
            piece = new Bishop(color);
            getChildren().remove(vBox);
            gridPane.setDisable(false);

        }
        else if (event.getSource().equals(rook_Button)){
            piece = new Rook(color);
            getChildren().remove(vBox);
            gridPane.setDisable(false);

        }
        else if (event.getSource().equals(knight_Button)){
            piece = new Knight(color);
            getChildren().remove(vBox);
            gridPane.setDisable(false);

        }
        if (piece != null){
            sq.addPiece(piece);
        }
    }

    //A helper method to update the location of a king piece if it was involved in a move.
    private void update_kingSq_helper(Square sq){
        if (sq.getPiece() instanceof King){
            if (sq.getColor().equals(Color.BLACK))
                bKingsq = sq;
            else
                wKingsq = sq;
        }
    }
    //A helper method to set the Strokes of all Squares on the Board to Black.
    private void setStrokeBlack(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Squares[i][j].setStroke(Color.BLACK);
            }
        }
    }
}

