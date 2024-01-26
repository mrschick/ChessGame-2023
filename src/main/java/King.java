import javafx.scene.paint.Color;
import java.io.File;
import java.util.ArrayList;

public class King implements Piece {

    private String position = "";
    private Color color;
    private ArrayList<String> list;

    public King(Color color){
        list = new ArrayList<>();
        this.color = color;
    }

    @Override
    public boolean kill(Square sq) {
        allLegalMoves();
        if (sq.isChessPiece() && !(sq.getColor().equals(this.color))){
            for (String s : list) {
                if (s.equals(sq.getPosition()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean move(Square sq) {
        allLegalMoves();
        if (!(sq.isChessPiece()) && !(goesIntoCheck())){
            for (String s : list) {
                if (s.equals(sq.getPosition()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void setPosition(String pos) {this.position = pos;}

    @Override
    public void setColor(Color color) {this.color = color;}

    @Override
    public Color getColor() {return color;}

    @Override
    public String getImageAddress() {
        File path = null;
        try {
            if (color.equals(Color.BLACK)) {
                path = new File("src/main/resources/black_pieces/king.png");
            } else {
                path = new File("src/main/resources/white_pieces/king.png");
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        return path.toURI().toString();
    }

    private void allLegalMoves(){
        int column = position.charAt(0);
        int row = Integer.parseInt(position.substring(1));

        list.add(String.valueOf((char)(column)) + (row + 1));
        list.add(String.valueOf((char)(column)) + (row - 1));
        list.add(String.valueOf((char)(column + 1)) + (row));
        list.add(String.valueOf((char)(column + 1)) + (row + 1));
        list.add(String.valueOf((char)(column + 1)) + (row - 1));
        list.add(String.valueOf((char)(column - 1)) + (row));
        list.add(String.valueOf((char)(column - 1)) + (row - 1));
        list.add(String.valueOf((char)(column - 1)) + (row + 1));

    }

    private boolean goesIntoCheck(){
        return false;
    }


}