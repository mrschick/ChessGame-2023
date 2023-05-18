import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Knight implements Piece {

    private String position = "";
    private String imageAddress = "C:\\Users\\sharjeel\\ChessGame-2023\\src\\main\\Knight.png";
    private Color color;
    private ArrayList<String> list;

    public Knight(){
        list = new ArrayList();
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

    //returns an ArrayList of hypothetical and real chess board addresses.
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

    //Adds hypothetical and real chess board addresses to list used in kill,move and seek methods.
    //These addresses follow similar notation as a chess board address.Considering the moving pattern of a
    //chess piece, these are calculated in all four directions from the current position of the chess piece.
    private void allLegalMoves() {
        int row = Integer.parseInt(position.substring(1));
        int column = position.charAt(0);

        list.add(String.valueOf((char) (column + 2)) + (row + 1));
        list.add(String.valueOf((char) (column + 2)) + (row - 1));
        list.add(String.valueOf((char) (column + 1)) + (row + 2));
        list.add(String.valueOf((char) (column - 1)) + (row + 2));
        list.add(String.valueOf((char) (column - 2)) + (row + 1));
        list.add(String.valueOf((char) (column - 2)) + (row - 1));
        list.add(String.valueOf((char) (column + 1)) + (row - 2));
        list.add(String.valueOf((char) (column - 1)) + (row - 2));


    }

}
