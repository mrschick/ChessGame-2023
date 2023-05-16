
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

//import java.util.Stack;

public class Square extends StackPane {

    String position;
<<<<<<< Updated upstream
    Rectangle square = new Rectangle(80, 80);
=======
    Rectangle square = new Rectangle(75, 75);
    private boolean contains_chess_piece = false;
    private Piece chessPiece = null;
>>>>>>> Stashed changes

    public Square() {
        getChildren().add(square);
        square.setStroke(Color.BLACK);
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

}