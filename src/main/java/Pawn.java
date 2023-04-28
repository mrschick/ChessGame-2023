public class Pawn implements Piece {

    @Override
    public boolean kill(Piece p) {
        return false;
    }

    @Override
    public boolean move(Square s) {
        return false;
    }

    @Override
    public boolean seek(Piece p) {
        return false;
    }

}
