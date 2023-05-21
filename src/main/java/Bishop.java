import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;


public class Bishop implements Piece {

    private String position = "";
    private Color color;
    private int num_moves = 0;
    private ArrayList<String> list;

    public Bishop(Color color){
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
        File path = null;
        try {
            if (color.equals(Color.BLACK)) {
                path = new File(System.getProperty("user.dir") + "\\src\\main\\black_pieces\\bBishop.png");
            } else {
                path = new File(System.getProperty("user.dir") + "\\src\\main\\white_pieces\\wBishop.png");
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        return path.getAbsolutePath();
    }

    private void allLegalMoves(){
        int column = position.charAt(0);
        int row = Integer.parseInt(position.substring(1));

        for (int i = 0; i < 8; i++){
            list.add(String.valueOf((char) (column + i)) + (row + i));
            list.add(String.valueOf((char) (column + i)) + (row - i));
            list.add(String.valueOf((char) (column - i)) + (row + i));
            list.add(String.valueOf((char) (column - i)) + (row - i));
        }
    }


}
