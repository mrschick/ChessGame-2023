import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

/**
 * Creates a Bishop chess Piece Object using the interface Piece.java
 *
 * @author Sharjeel Zahid Mahmood
 */
public class Bishop implements Piece {

    private String position = "";
    private Color color;;
    private ArrayList<String> list;

    /**
     * Constructor for the Bishop chess piece Object.
     *
     * @param color The Color of the Bishop which can be Black or White.
     */
    public Bishop(Color color){
        list = new ArrayList<>();
        this.color = color;
    }

    /**
     * This method determines if this.Bishop can kill the chess piece inside the parameter sq.
     * returns false otherwise.
     *
     * @param sq A square that might contain an enemy chess piece.
     * @return returns true if the this.Bishop can legally kill the chess piece inside sq, false otherwise.
     */
    @Override
    public boolean kill(Square sq) {
        allLegalMoves();
        if (sq.isChessPiece() && !(sq.getColor().equals(this.color))){
            for (String s : list) {
                if (s.equals(sq.getPosition()))
                    return true;
            }
        }
        return false;
    }

    /**
     * This method determines if this.Bishop can move to the parameter sq.
     * returns false otherwise.
     *
     * @param sq A square that might not contain a chess piece and is a possible move.
     * @return returns true if the this.Bishop can move to the parameter sq, false otherwise.
     */
    @Override
    public boolean move(Square sq) {
        allLegalMoves();
        if (!(sq.isChessPiece())){
            for (String s : list) {
                if (s.equals(sq.getPosition()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Used to set the position of this.Bishop
     * @param pos Conatains the position that is to be set as a String.
     */
    @Override
    public void setPosition(String pos) {this.position = pos;}

    /**
     * Used to get the color of this.Bishop which can be Black or white.
     * @return returns the color of this.Bishop
     */
    public Color getColor(){
        return this.color;
    }


    /**
     * Used to get a Bishop Image to use inside a Square Object in a GUI.
     *
     * @return the address of a Bishop chess piece image depending on the color of this.Bishop
     */
    @Override
    public String getImageAddress() {
        File path = null;
        try {
            if (color.equals(Color.BLACK)) {
                path = new File("src/main/resources/black_pieces/bishop.png");
            } else {
                path = new File("src/main/resources/white_pieces/bishop.png");
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        return path.toURI().toString();
    }

    /**
     * Determines all hypothetical and real moves and kills for this.Bishop
     * Does not determine if the moves and kills are legal or illegal.
     */
    private void allLegalMoves(){
        list = new ArrayList<>();
        int column = position.charAt(0);
        int row = Integer.parseInt(position.substring(1));

        for (int i = 0; i < 8; i++){
            list.add(String.valueOf((char) (column + i)) + (row + i));
            list.add(String.valueOf((char) (column + i)) + (row - i));
            list.add(String.valueOf((char) (column - i)) + (row + i));
            list.add(String.valueOf((char) (column - i)) + (row - i));
        }
    }


}
