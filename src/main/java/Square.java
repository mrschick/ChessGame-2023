import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//import java.util.Stack;

public class Square extends StackPane {

    String position;
    Rectangle square = new Rectangle(75, 75);
    private boolean contains_chess_piece = false;
    public Piece chessPiece = null;

    //Constructor to use Square object as a chess square.
    public Square() {
        getChildren().add(square);
        square.setStroke(Color.BLACK);
    }

    //Constructor to use Squaer object as a chess piece.
    public <T extends Piece> Square(T chessPiece) {
        this.chessPiece = chessPiece;
        contains_chess_piece = true;
        Image img = new Image(this.chessPiece.getImageAddress());
        ImageView image = new ImageView();
        image.setImage(img);
        getChildren().add(image);
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String pos) {
        this.position = pos;
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

}
