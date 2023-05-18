import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public class Pawn implements Piece {

    private String position = "";
    //Possible moves and kills for a pawn piece
    private String move1 = "m1";
    private String move2 = "m2";
    private String kill1 = "k1";
    private String kill2 = "k2";
    private Color color;
    private int num_moves;
    private String imageAddress = "C:\\Users\\sharjeel\\ChessGame-2023\\src\\main\\pawn.png";

    @Override
    public boolean kill(Square sq) {
        if ((sq.isChessPiece()) && (sq.getPosition().equals(kill1) || (sq.getPosition().equals(kill2))))
            return true;
        else
            return false;
    }

    @Override
    public boolean move(Square sq) {
        //Checks if the move to square in the met-perimeter is a valid move
        if (!(sq.isChessPiece()) && (sq.getPosition().equals(move1)) || (sq.getPosition().equals(move2)))
            return true;
        else
            return false;
    }

    @Override
    public Map<String, String> seek() {
        availableMoves();
        availableKills();
        Map<String, String> map = new HashMap<String, String>();
        map.put(move1, "m");
        map.put(move2, "m");
        map.put(kill1, "k");
        map.put(kill2, "k");
        return map;

    }

    @Override
    public void setPosition(String pos) {this.position = pos;}

    @Override
    public String getPosition() {return position;}

    public void setColor(Color color) {this.color = color;}

    @Override
    public Color getColor() {return color;}
    public String getImageAddress(){return imageAddress;}

    public void incrementNumMoves(){
        num_moves++;
    }
    public int getNum_moves(){
        return num_moves;
    }

    //submethod to check all possible moves for pawn under idead situations
    private void availableMoves(){
        int row = position.charAt(1);

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
    private void availableKills(){
        int row = position.charAt(1);
        int column = position.charAt(0);

        if (color.equals(Color.BLACK)){
            if (row > 1){
                kill1 = String.valueOf(column - 1) + (row - 1);
                kill2 = String.valueOf(column + 1) + (row - 1);
            }
        }
        else if (color.equals(Color.WHITE)){
            if (row < 8){
                kill1 = String.valueOf(column - 1) + (row + 1);
                kill2 = String.valueOf(column + 1) + (row + 1);
            }
        }
    }

}
