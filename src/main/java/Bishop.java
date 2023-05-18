import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bishop implements Piece {

    private String position = "";
    private Color color;
    private int num_moves = 0;
    private ArrayList<String> list;

    @Override
    public boolean kill(Square sq) {
        return true;
    }

    @Override
    public boolean move(Square sq) {
        return false;
    }

    @Override
    public ArrayList<String> seek() {
        return list;
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
