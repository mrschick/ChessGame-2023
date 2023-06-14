import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

public class Pawn implements Piece {

    private String position;
    private ArrayList<String> list;
    //Possible moves and kills for a pawn piece
    private String move1 = "m1";
    private String move2 = "m2";
    private String kill1 = "k1";
    private String kill2 = "k2";
    private Color color;
    private int num_moves;

    public Pawn(Color color){
        list = new ArrayList<>();
        this.color = color;
    }
    @Override
    public boolean kill(Square sq) {
        if ((sq.isChessPiece()) && !(sq.getColor().equals(this.color))){
            return (sq.getPosition().equals(kill1) || (sq.getPosition().equals(kill2)));
        }
        return false;
    }

    @Override
    public boolean move(Square sq) {
        //Checks if the move to square in the met-perimeter is a valid move
        if (!(sq.isChessPiece()))
            return ((sq.getPosition().equals(move1)) || (sq.getPosition().equals(move2)));
        return false;
    }

    @Override
    public ArrayList<String> seek() {
        allLegalMoves();
        allLegalKills();
        list.add(move1);
        list.add(move2);
        list.add(kill1);
        list.add(kill2);
        return list;
    }

    @Override
    public void setPosition(String pos) {
        position = pos;
        System.out.println(position);
        System.out.println(pos);
    }

    @Override
    public String getPosition() {return position;}

    public void setColor(Color color) {this.color = color;}

    @Override
    public Color getColor() {return color;}
    public String getImageAddress(){
        File path = null;
        try {
            if (color.equals(Color.BLACK)) {
                path = new File("src/main/resources/black_pieces/pawn.png");
            } else {
                path = new File("src/main/resources/white_pieces/pawn.png");
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
        return path.toURI().toString();}

    public void incrementNumMoves(){
        num_moves++;
    }
    public int getNum_moves(){
        return num_moves;
    }

    //sub method to check all possible moves for pawn under ideal situations
    private void allLegalMoves(){
        int row = Integer.parseInt(position.substring(1));
        System.out.println(row);

        //Checks available moves for Black pawns
        if (color.equals(Color.BLACK)){
            if (num_moves == 0){
                move1 = String.valueOf(position.charAt(0)) + (row - 1);
                move2 = String.valueOf(position.charAt(0)) + (row - 2);
            }
            else if (row > 1){
                move1 = String.valueOf(position.charAt(0) + (row - 1));
            }
        }

        //Checks available moves for white pawns
        if (color.equals(Color.WHITE)){
            if (num_moves == 0){
                move1 = String.valueOf(position.charAt(0)) + (row + 1);
                move2 = String.valueOf(position.charAt(0)) + (row + 2);
            }
            else if (row < 8){
                move1 = String.valueOf(position.charAt(0) + (row + 1));
            }
        }
    }

    //submethod to find all available kills for pawn under ideal conditions
    private void allLegalKills(){
        int row = Integer.parseInt(position.substring(1));
        int column = position.charAt(0);

        if (color.equals(Color.BLACK)){
            if (row > 1){
                kill1 = String.valueOf((char)(column - 1)) + (row - 1);
                kill2 = String.valueOf((char)(column + 1)) + (row - 1);
            }
        }
        else if (color.equals(Color.WHITE)){
            if (row < 8){
                kill1 = String.valueOf((char)(column - 1)) + (row + 1);
                kill2 = String.valueOf((char)(column + 1)) + (row + 1);
            }
        }
    }

}
