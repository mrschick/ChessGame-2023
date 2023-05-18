import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Knight implements Piece {

    private String position = "";
    private Color color;

    @Override
    public boolean kill(Square sq) {
        return false;
    }

    @Override
    public boolean move(Square sq) {
        return false;
    }

    @Override
    public Map<String, String> seek() {
        Map<String, String> map = new HashMap<>();
        return map;
    }

    @Override
    public void setPosition(String pos) {this.position = pos;}

    @Override
    public String getPosition() {return position;}

    public void setColor(Color color) {this.color = color;}

    @Override
    public Color getColor() {return color;}

    @Override
    public String getImageAddress() {
        return null;
    }

}
