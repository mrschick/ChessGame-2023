import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

/**
 * Creates a Pawn chess Piece Object using the interface Piece.java
 *
 * @author Sharjeel Zahid Mahmood
 */
public class Pawn implements Piece {

    private String position;
    //Possible moves and kills for a pawn piece
    private String move1 = "m1";
    private String move2 = "m2";
    private String kill1 = "k1";
    private String kill2 = "k2";
    private Color color;

    /**
     * Constructor for the Pawn chess piece Object.
     *
     * @param color The Color of the pawn which can be Black or White.
     */
    public Pawn(Color color){
        this.color = color;
    }

    /**
     * This method determines if this.Pawn can kill the chess piece inside the parameter sq.
     * returns false otherwise.
     *
     * @param sq A square that might contain an enemy chess piece.
     * @return returns true if the this.Pawn can legally kill the chess piece inside sq, false otherwise.
     */
    @Override
    public boolean kill(Square sq) {
        allLegalKills();
        if ((sq.isChessPiece()) && !(sq.getColor().equals(this.color))){
            return sq.getPosition().equals(kill1) || (sq.getPosition().equals(kill2));
        }
        return false;
    }

    /**
     * This method determines if this.Pawn can move to the parameter sq.
     * returns false otherwise.
     *
     * @param sq A square that might not contain a chess piece and is a possible move.
     * @return returns true if the this.Pawn can move to the parameter sq, false otherwise.
     */
    @Override
    public boolean move(Square sq) {
        //Checks if the move to square in the met-perimeter is a valid move
        allLegalMoves();
        if (!(sq.isChessPiece())) {
            if ((sq.getPosition().equals(move1)) || (sq.getPosition().equals(move2)))
                return true;
        }
        return false;
    }


    /**
     * Used to set the position of this.Pawn
     * @param pos Conatains the position that is to be set as a String.
     */

    @Override
    public void setPosition(String pos) {
        position = pos;
    }

    /**
     * Used to get the color of this.Pawn which can be Black or white.
     * @return returns the color of this.Pawn
     */
    public Color getColor(){
        return this.color;
    }


    /**
     * Used to get a Pawn Image to use inside a Square Object in a GUI.
     *
     * @return the address of a Pawn chess piece image depending on the color of this.Pawn
     */
    @Override
    public String getImageAddress(){
        File path = null;
        try {
            if (color.equals(Color.BLACK)) {
                path = new File("src/main/resources/black_pieces/pawn.png");
            } else {
                path = new File("src/main/resources/white_pieces/pawn.png");
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        return path.toURI().toString();}

    /**
     * Determines all hypothetical and real moves for this.Pawn
     * Does not determine if the movesare legal or illegal.
     */
    private void allLegalMoves(){
        int row = Integer.parseInt(position.substring(1));
        move2 = "";

        move2 = "";

        //Checks available moves for Black pawns
        if (color.equals(Color.BLACK)){
            if (row == 7){
                move1 = String.valueOf(position.charAt(0)) + (row - 1);
                move2 = String.valueOf(position.charAt(0)) + (row - 2);
            }
            else if (row > 1){
                move1 = String.valueOf(position.charAt(0)) + (row - 1);
            }
        }

        //Checks available moves for white pawns
        if (color.equals(Color.WHITE)){
            if (row <= 2){
                move1 = String.valueOf(position.charAt(0)) + (row + 1);
                move2 = String.valueOf(position.charAt(0)) + (row + 2);
            }
            else if (row < 8){
                move1 = String.valueOf(position.charAt(0)) + (row + 1);
            }
        }
    }

    /**
     * Determines all hypothetical and realkills for this.Pawn
     * Does not determine if the kills are legal or illegal.
     */
    private void allLegalKills(){
        int row = Integer.parseInt(position.substring(1));
        int column = position.charAt(0);

        if (color.equals(Color.BLACK)){
            if (row > 1){
                kill1 = String.valueOf((char)(column - 1)) + (row - 1);
                kill2 = String.valueOf((char)(column + 1)) + (row - 1);
            }
        }
        else if (color.equals(Color.WHITE)){
            if (row < 8){
                kill1 = String.valueOf((char)(column - 1)) + (row + 1);
                kill2 = String.valueOf((char)(column + 1)) + (row + 1);
            }
        }
    }

}
