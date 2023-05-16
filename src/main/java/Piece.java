import javafx.scene.paint.Color;

public interface Piece {
    public boolean kill(Square sq);

    public boolean move(Square sq);

    public boolean seek(Square sq);

    public void setPostion(String pos);

    public String getPostion();

    public void setColor(Color color);

    public Color getColor();

}
