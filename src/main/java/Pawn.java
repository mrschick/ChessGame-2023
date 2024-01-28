import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

public class Pawn implements Piece {

    private String position;
    //Possible moves and kills for a pawn piece
    private String move1 = "m1";
    private String move2 = "m2";
    private String kill1 = "k1";
    private String kill2 = "k2";
    private Color color;

    public Pawn(Color color){
        this.color = color;
    }
    @Override
    public boolean kill(Square sq) {
        allLegalKills();
        if ((sq.isChessPiece()) && !(sq.getColor().equals(this.color))){
            return sq.getPosition().equals(kill1) || (sq.getPosition().equals(kill2));
        }
        return false;
    }

    @Override
    public boolean move(Square sq) {
        //Checks if the move to square in the met-perimeter is a valid move
        allLegalMoves();
        if (!(sq.isChessPiece())) {
            if ((sq.getPosition().equals(move1)) || (sq.getPosition().equals(move2)))
                return true;
        }
        return false;
    }

    @Override
    public void setPosition(String pos) {
        position = pos;
    }

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

    //sub method to check all possible moves for pawn under ideal situations
    private void allLegalMoves(){
        int row = Integer.parseInt(position.substring(1));
        move2 = "";

        move2 = "";

        //Checks available moves for Black pawns
        if (color.equals(Color.BLACK)){
            if (row == 7){
                move1 = String.valueOf(position.charAt(0)) + (row - 1);
                move2 = String.valueOf(position.charAt(0)) + (row - 2);
            }
            else if (row > 1){
                move1 = String.valueOf(position.charAt(0)) + (row - 1);
            }
        }

        //Checks available moves for white pawns
        if (color.equals(Color.WHITE)){
            if (row <= 2){
                move1 = String.valueOf(position.charAt(0)) + (row + 1);
                move2 = String.valueOf(position.charAt(0)) + (row + 2);
            }
            else if (row < 8){
                move1 = String.valueOf(position.charAt(0)) + (row + 1);
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
