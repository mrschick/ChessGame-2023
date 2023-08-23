import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

public class Rook implements Piece {

    private String position = "";
    private Color color;
    private ArrayList<String> list;

    public Rook(Color color){
        list = new ArrayList<>();
        this.color = color;
    }
    @Override
    public boolean kill(Square sq) {
        allLegalMoves();
        if (sq.isChessPiece()  && !(sq.getColor().equals(this.color))){
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
        if (!(sq.isChessPiece())){
            for (String s : list) {
                if (s.equals(sq.getPosition()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> seek() {
        allLegalMoves();
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
        File path = null;
        try {
            if (color.equals(Color.BLACK)) {
                path = new File("src/main/resources/black_pieces/rook.png");
            } else {
                path = new File("src/main/resources/white_pieces/rook.png");
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        return path.toURI().toString();
    }

    private void allLegalMoves(){
        list = new ArrayList<>();
        int row = Integer.parseInt(position.substring(1));
        int column = position.charAt(0);

        for (int i = 1; i < 8; i++){
            list.add(String.valueOf((char) (column)) + (row + i));
            list.add(String.valueOf((char) (column)) + (row - i));
            list.add(String.valueOf((char) (column + i)) + row);
            list.add(String.valueOf((char) (column - i)) + row);
        }
    }


}
