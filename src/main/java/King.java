import javafx.scene.paint.Color;

public class King implements Piece {

    private String position = "";
    Color color;

    @Override
    public boolean kill(Square sq) {
        return false;
    }

    @Override
    public boolean move(Square sq) {
        return false;
    }

    @Override
    public boolean seek(Square sq) {
        return false;
    }

    @Override
    public void setPostion(String pos) {this.position = pos;}

    @Override
    public String getPostion() {return position;}

    @Override
    public void setColor(Color color) {this.color = color;}

    @Override
    public Color getColor() {return color;}

}