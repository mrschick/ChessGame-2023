import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Map;

public interface Piece {
    public boolean kill(Square sq);

    public boolean move(Square sq);


    public void setPosition(String pos);

    public void setColor(Color color);

    public Color getColor();

    public String getImageAddress();

}
