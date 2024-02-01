import javafx.scene.paint.Color;

public interface Piece {

    /**
     * This method determines if the implementing class Object can kill the chess piece inside the parameter sq.
     * returns false otherwise.
     *
     * @param sq A square that might contain an enemy chess piece.
     * @return returns true if the implementing class Object can legally kill the chess piece inside sq, false otherwise.
     */
    boolean kill(Square sq);

    /**
     * This method determines if the implementing Chess Class Object can move to the parameter sq.
     * returns false otherwise.
     *
     * @param sq A square that might not contain a chess piece and is a possible move.
     * @return returns true if the the implementing Chess Class Object can move to the parameter sq, false otherwise.
     */
    boolean move(Square sq);

    /**
     * Used to set the position of the implementing Class Object
     * @param pos Conatains the position that is to be set as a String.
     */
    void setPosition(String pos);

    /**
     * Used to get the color of the implementing Class Object which can be Black or white.
     * @return returns the color of the implementing Class Object
     */
    Color getColor();

    /**
     * Used to get a chess piece Image to use inside a Square Object in a GUI.
     *
     * @return the address of a chess piece image depending on the color of the implementing Class Object.
     */
    String getImageAddress();
}
