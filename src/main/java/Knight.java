import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

public class Knight implements Piece {

    private String position = "";
    private Color color;
    private ArrayList<String> list;

    public Knight(Color color){
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
        if (!(sq.isChessPiece())){
            for (String s : list) {
                if (s.equals(sq.getPosition()))
                    return true;
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
        File path = null;
        try {
            if (color.equals(Color.BLACK)) {
                path = new File(System.getProperty("user.dir") + "\\src\\main\\black_pieces\\bKnight.png");
            } else {
                path = new File(System.getProperty("user.dir") + "\\src\\main\\white_pieces\\wKnight.png");
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        return path.getAbsolutePath();
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
