import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This Class creates a Square object as a GUI rectangle object.
 *
 * @author Sharjeel Zahid Mahmood.
 */
public class Square extends StackPane {

    String position;
    Rectangle square = new Rectangle(75, 75);
    private boolean contains_chess_piece = false;
    private Piece chessPiece = null;
    private Image img;

    /**
     * This is a constructor to create a Square object as an empty chess square.
     */
    public Square(){
        getChildren().add(square);
        square.setStroke(Color.BLACK);
    }

    /**
     * This is a constructor to create a Square object that contains a chess piece.
     * @param chessPiece The chess piece that is to be added inside this.Square
     * @param <T> Could be any class that implements the Piece interface.
     */
    public <T extends Piece> Square(T chessPiece) {
        square.setFill(Color.GREEN);
        square.setStroke(Color.BLACK);
        getChildren().add(square);
        this.chessPiece = chessPiece;
        contains_chess_piece = true;
        img = new Image(this.chessPiece.getImageAddress());
        ImageView image = new ImageView();
        image.setImage(img);
        getChildren().add(image);
    }

    /**
     * This is a getter method for the position of this.Square
     * @return Returns the Chess Board postion of this.Square as a String.
     */
    public String getPosition() {
        return position;
    }

    /**
     * This is a setter method for the position of this.Square
     * @param pos Contains the Chess Board position of this.Square to be set.
     */
    public void setPosition(String pos) {
        this.position = pos;
        if (isChessPiece()){
            chessPiece.setPosition(pos);
        }
    }

    /**
     * Sets the color of the this.Square.
     * @param color the color of the square.
     */
    public void setColor(Paint color) {
        square.setFill(color);
    }

    /**
     * Gets the Color of this.Square depending on if its empty or contains a chess piece.
     * @return Return the color of the chess piece that this.Square contains, returns the color of the empty square otherwise.
     */
    public Color getColor(){
        if (isChessPiece())
            return chessPiece.getColor();
        else
            return (Color) square.getFill();
    }

    //Checks if square is a chess piece or a square

    /**
     * This method is used to check if this.Square contains a chess piece or is empty.
     * @return true if this.Square contains a chess piece, otherwise returns false.
     */
    public boolean isChessPiece(){
        if(contains_chess_piece)
            return true;
        else
            return false;
    }

    /**
     * This method is used to set the border stroke of this.Square.
     * @param color this is the color of the stroke to be set.
     */
    public void setStroke(Color color) {
        square.setStroke(color);
    }

    /**
     * This method is used to get the color of the border stroke of this.Square
     * @return Returns the Color of this.Square's stroke.
     */
    public Color getStroke(){return (Color) square.getStroke();}

    /**
     * This method is used to remove the chess Piece from this.Square and set the appropriate flags.
     */
    public void removePiece(){
        this.chessPiece = null;
        contains_chess_piece = false;
        getChildren().remove(1);
    }

    /**
     * This method is used to add a chess piece to this.Square and change the value of related variables.
     * @param chessPiece Contains the chess piece that is to be added to this.Square
     * @param <T>        makes the parameter a generic type so that any Class that implements the Piece interface
     *            can be passed as a parameter.
     */
    public <T extends Piece>void addPiece(T chessPiece){
        if (contains_chess_piece)
            getChildren().remove(1);
        setPiece(chessPiece);
        chessPiece.setPosition(position);
        img = new Image(this.chessPiece.getImageAddress());
        ImageView image = new ImageView();
        image.setImage(img);
        getChildren().add(image);
    }

    /**
     * This method is used to get the piece inside this.Square
     * @return Returns this.Square's chess piece.
     */
    public Piece getPiece(){
        return chessPiece;
    }

    private <T extends  Piece> void setPiece(T chessPiece) {
        this.chessPiece = chessPiece;
        this.contains_chess_piece = true;
    }
}
