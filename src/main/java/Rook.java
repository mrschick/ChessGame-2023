import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rook implements Piece {

    private String position = "";
    private String imageAddress = "C:\\Users\\sharjeel\\ChessGame-2023\\src\\main\\rook.png";
    private Color color;
    private ArrayList<String> list;

    public Rook(){
        list = new ArrayList<>();
    }
    @Override
    public boolean kill(Square sq) {
        allLegalMoves();
        if (sq.isChessPiece()){
            for (int i = 0; i < 8; i++){
                if (list.get(i).equals(sq.getPosition()))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    @Override
    public boolean move(Square sq) {
        allLegalMoves();
        if (!(sq.isChessPiece())){
            for (int i = 0; i < 8; i++){
                if (list.get(i).equals(sq.getPosition()))
                    return true;
                else
                    return false;
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
        return imageAddress;
    }

    private void allLegalMoves(){
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
