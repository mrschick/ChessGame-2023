import javafx.scene.paint.Color;

public class Rook implements Piece {

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
    public boolean seek(Square sq) {
        return false;
    }

    @Override
    public void setPostion(String pos) {this.position = pos;}

    @Override
    public String getPostion() {return position;}
    public void setColor(Color color) {this.color = color;}
    @Override
    public Color getColor() {return color;}


}
