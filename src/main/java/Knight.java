import javafx.scene.paint.Color;
import java.io.File;
import java.util.ArrayList;


/**
 * Creates a Knight chess Piece Object using the interface Piece.java
 *
 * @author Sharjeel Zahid Mahmood
 */
public class Knight implements Piece {

    private String position;

    private Color color;
    private ArrayList<String> list;

    /**
     * Constructor for the Knight chess piece Object.
     *
     * @param color The Color of the Knight which can be Black or White.
     */
    public Knight(Color color) {
        list = new ArrayList<>();
        this.color = color;
    }

    /**
     * This method determines if this.Knight can kill the chess piece inside the parameter sq.
     * returns false otherwise.
     *
     * @param sq A square that might contain an enemy chess piece.
     * @return returns true if the this.Knight can legally kill the chess piece inside sq, false otherwise.
     */
    @Override
    public boolean kill(Square sq) {
        allLegalMoves();
        if (sq.isChessPiece() && !(sq.getColor().equals(this.color))) {
            for (String s : list) {
                if (s.equals(sq.getPosition()))
                    return true;
            }
        }
        return false;
    }

    /**
     * This method determines if this.Knight can move to the parameter sq.
     * returns false otherwise.
     *
     * @param sq A square that might not contain a chess piece and is a possible move.
     * @return returns true if the this.Knight can move to the parameter sq, false otherwise.
     */
    @Override
    public boolean move(Square sq) {
        allLegalMoves();
        if (!(sq.isChessPiece())) {
            for (String s : list) {
                if (s.equals(sq.getPosition()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Used to set the position of this.Knight
     * @param pos Conatains the position that is to be set as a String.
     */
    @Override
    public void setPosition(String pos) {
        this.position = pos;
    }

    /**
     * Used to get the color of this.Knight which can be Black or white.
     * @return returns the color of this.Knight
     */
    public Color getColor(){
        return this.color;
    }
    /**
     * Used to get a Knight Image to use inside a Square Object in a GUI.
     *
     * @return the address of a Knight chess piece image depending on the color of this.Knight
     */
    @Override
    public String getImageAddress() {
        File path = null;
        try {
            if (color.equals(Color.BLACK)) {
                path = new File("src/main/resources/black_pieces/knight.png");
            } else {
                path = new File("src/main/resources/white_pieces/knight.png");
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        return path.toURI().toString();
    }

    //Adds hypothetical and real chess board addresses to list used in kill,move and seek methods.
    //These addresses follow similar notation as a chess board address.Considering the moving pattern of a
    //chess piece, these are calculated in all four directions from the current position of the chess piece.
    /**
     * Determines all hypothetical and real moves and kills for this.Knight
     * Does not determine if the moves and kills are legal or illegal.
     */
    private void allLegalMoves() {
        list = new ArrayList<>();
        int row = Integer.parseInt(position.substring(1));
        int column = position.charAt(0);

        list.add(String.valueOf((char) (column + 2)) + (row + 1));
        list.add(String.valueOf((char) (column + 2)) + (row - 1));
        list.add(String.valueOf((char) (column + 1)) + (row + 2));
        list.add(String.valueOf((char) (column - 1)) + (row + 2));
        list.add(String.valueOf((char) (column - 2)) + (row + 1));
        list.add(String.valueOf((char) (column - 2)) + (row - 1));
        list.add(String.valueOf((char) (column + 1)) + (row - 2));
        list.add(String.valueOf((char) (column - 1)) + (row - 2));


    }
}
