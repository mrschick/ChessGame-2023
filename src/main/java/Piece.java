import javafx.scene.paint.Color;

public interface Piece {
    public boolean kill(Piece p);

    public boolean move(Square s);

    public boolean seek(Piece p);

    public void setPostion(String pos);

    public String getPostion();

    public void setColor(Color color);

    public Color getColor();

}
