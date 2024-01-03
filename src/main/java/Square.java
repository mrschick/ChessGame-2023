import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

//import java.util.Stack;

public class Square extends StackPane {

    String position;
    Rectangle square = new Rectangle(75, 75);
    private boolean contains_chess_piece = false;
    private Piece chessPiece = null;
    private Image img;

    //Constructor to use Square object as a chess square.
    public Square(){
        getChildren().add(square);
        square.setStroke(Color.BLACK);
    }

    //Constructor to use Square object as a chess piece.
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


    public String getPosition() {
        return position;
    }

    public void setPosition(String pos) {
        this.position = pos;
        if (isChessPiece()){
            chessPiece.setPosition(pos);
        }
    }

    public void setColor(Paint color) {
        square.setFill(color);
    }

    public Color getColor(){
        if (isChessPiece())
            return chessPiece.getColor();
        else
            return (Color) square.getFill();
    }

    //Checks if square is a chess piece or a square
    public boolean isChessPiece(){
        if(contains_chess_piece)
            return true;
        else
            return false;
    }

    public void setStroke(Color color) {
        square.setStroke(color);
    }
    public Color getStroke(){return (Color) square.getStroke();}

    public <T extends Piece> void replacePiece(T chessPiece){
        this.chessPiece = chessPiece;
        this.chessPiece.setPosition(getPosition());
        this.img = new Image(chessPiece.getImageAddress());
        ImageView image = new ImageView();
        image.setImage(img);
        getChildren().remove(1);
        getChildren().add(image);
    }

    public void removePiece(){
        this.chessPiece = null;
        contains_chess_piece = false;
        getChildren().remove(1);
    }

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

    public Piece getPiece(){
        return chessPiece;
    }

    public <T extends  Piece> void setPiece(T chessPiece) {
        this.chessPiece = chessPiece;
        this.contains_chess_piece = true;
    }

    public void setContains_chess_piece(boolean f){
        contains_chess_piece = f;
    }
}
