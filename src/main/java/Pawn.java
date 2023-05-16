import javafx.scene.paint.Color;

public class Pawn implements Piece {

    private String position = "";
    private Color color;
    private int num_moves;

    @Override
    public boolean kill(Square sq) {
        return false;
    }

    @Override
    public boolean move(Square sq) {
        String available_moves1 = "";
        String available_moves2 = "";

        int row = position.charAt(1);

        //Checks available moves for Black pawns
        if (color.equals(Color.BLACK)){
            if (num_moves == 0){
                available_moves1 = String.valueOf(position.charAt(0)) + (row - 1);
                available_moves2 = String.valueOf(position.charAt(0)) + (row - 2);
            }
            else if (row > 1){
                available_moves1 = String.valueOf(position.charAt(0) + (row - 1));
            }
        }

        //Checks available moves for white pawns
        if (color.equals(Color.WHITE)){
            if (num_moves == 0){
                available_moves1 = String.valueOf(position.charAt(0)) + (row + 1);
                available_moves2 = String.valueOf(position.charAt(0)) + (row + 2);
            }
            else if (row < 8){
                available_moves1 = String.valueOf(position.charAt(0) + (row + 1));
            }
        }

        //Checks if the move to square in the met-perimeter is a valid move
        if (!(sq.isChessPiece()) && sq.getPosition().equals(available_moves1) || sq.getPosition().equals(available_moves2))
            return true;
        else
            return false;
    }

    @Override
    public boolean seek(Square sq) {
        return false;
    }

    @Override
    public void setPostion(String pos) {this.position = pos;}

    @Override
    public String getPostion() {return position;}

    public void setColor(Color color) {this.color = color;}

    @Override
    public Color getColor() {return color;}

    public void incrementNum_moves(){
        num_moves++;
    }
    public int getNum_moves(){
        return num_moves;
    }

}
