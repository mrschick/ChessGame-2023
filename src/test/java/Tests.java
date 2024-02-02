import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

public class Tests {

    JFXPanel jfxPanel = new JFXPanel();
    ColorScheme colorScheme= new ColorScheme(
            Color.rgb(133, 94, 66),
            Color.rgb(222, 184, 135),
            Color.rgb(189, 168, 145),
            Color.rgb(200, 200, 200)
    );

    Board chessBoard = new Board(colorScheme);
    Square[][] squares = chessBoard.getSquares();

    @Test
    void testSquarePositions(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                String expected_pos = String.valueOf((char)(97 + j)) + ((8 - i));
                Assertions.assertEquals(expected_pos, squares[i][j].getPosition());
            }
        }
    }

    @Test
    void testSquareColors(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8;j++){
                if (i < 2){
                    Assertions.assertEquals(Color.BLACK, squares[i][j].getColor());
                }
                else if (i >= 6){
                    Assertions.assertEquals(Color.WHITE, squares[i][j].getColor());
                }
                else{
                    System.out.println(i +" "+  j);
                    Assertions.assertNotEquals(Color.BLACK, squares[i][j].getColor());
                    Assertions.assertNotEquals(Color.WHITE, squares[i][j].getColor());
                }
            }
        }
    }

    @Test
    void testPawnMoves(){
        //Black Pawns
        Assertions.assertTrue(squares[1][0].getPiece().move(squares[2][0]));
        Assertions.assertTrue(squares[1][0].getPiece().move(squares[3][0]));
        Assertions.assertTrue(squares[1][1].getPiece().move(squares[2][1]));
        Assertions.assertTrue(squares[1][1].getPiece().move(squares[3][1]));
        Assertions.assertTrue(squares[1][2].getPiece().move(squares[2][2]));
        Assertions.assertTrue(squares[1][2].getPiece().move(squares[3][2]));
        Assertions.assertTrue(squares[1][3].getPiece().move(squares[2][3]));
        Assertions.assertTrue(squares[1][3].getPiece().move(squares[3][3]));
        Assertions.assertTrue(squares[1][4].getPiece().move(squares[2][4]));
        Assertions.assertTrue(squares[1][4].getPiece().move(squares[3][4]));
        Assertions.assertTrue(squares[1][5].getPiece().move(squares[2][5]));
        Assertions.assertTrue(squares[1][5].getPiece().move(squares[3][5]));
        Assertions.assertTrue(squares[1][6].getPiece().move(squares[2][6]));
        Assertions.assertTrue(squares[1][6].getPiece().move(squares[3][6]));
        Assertions.assertTrue(squares[1][7].getPiece().move(squares[2][7]));
        Assertions.assertTrue(squares[1][7].getPiece().move(squares[3][7]));

        //White Pawns
        Assertions.assertTrue(squares[6][0].getPiece().move(squares[4][0]));
        Assertions.assertTrue(squares[6][0].getPiece().move(squares[5][0]));
        Assertions.assertTrue(squares[6][1].getPiece().move(squares[4][1]));
        Assertions.assertTrue(squares[6][1].getPiece().move(squares[5][1]));
        Assertions.assertTrue(squares[6][2].getPiece().move(squares[4][2]));
        Assertions.assertTrue(squares[6][2].getPiece().move(squares[5][2]));
        Assertions.assertTrue(squares[6][3].getPiece().move(squares[4][3]));
        Assertions.assertTrue(squares[6][3].getPiece().move(squares[5][3]));
        Assertions.assertTrue(squares[6][4].getPiece().move(squares[4][4]));
        Assertions.assertTrue(squares[6][4].getPiece().move(squares[5][4]));
        Assertions.assertTrue(squares[6][5].getPiece().move(squares[4][5]));
        Assertions.assertTrue(squares[6][5].getPiece().move(squares[5][5]));
        Assertions.assertTrue(squares[6][6].getPiece().move(squares[4][6]));
        Assertions.assertTrue(squares[6][6].getPiece().move(squares[5][6]));
        Assertions.assertTrue(squares[6][7].getPiece().move(squares[4][7]));
        Assertions.assertTrue(squares[6][7].getPiece().move(squares[5][7]));
    }

    @Test
    void testKnightMoves(){
        //Black Knights
        Assertions.assertTrue(squares[0][1].getPiece().move(squares[2][0]));
        Assertions.assertTrue(squares[0][1].getPiece().move(squares[2][2]));
        Assertions.assertTrue(squares[0][6].getPiece().move(squares[2][7]));
        Assertions.assertTrue(squares[0][6].getPiece().move(squares[2][5]));

        //Black Knights kills not allowed on friendly squares
        Assertions.assertFalse(squares[0][1].getPiece().kill(squares[1][3]));
        Assertions.assertFalse(squares[0][6].getPiece().kill(squares[1][4]));

        //White Knights
        Assertions.assertTrue(squares[7][1].getPiece().move(squares[5][0]));
        Assertions.assertTrue(squares[7][1].getPiece().move(squares[5][2]));
        Assertions.assertTrue(squares[7][6].getPiece().move(squares[5][5]));
        Assertions.assertTrue(squares[7][6].getPiece().move(squares[5][7]));

        //White Knights kills not allowed on friendly squares
        Assertions.assertFalse(squares[7][1].getPiece().kill(squares[6][3]));
        Assertions.assertFalse(squares[7][6].getPiece().kill(squares[6][4]));
    }

    @Test
    void testMoveConfig(){
        Assertions.assertFalse(squares[2][0].isChessPiece());
        Assertions.assertTrue(squares[2][0].getPiece() == null);
        Assertions.assertFalse(squares[2][0].getColor().equals(Color.BLACK));
        Assertions.assertTrue(squares[1][0].getPiece() != null);

        //Pawn on a7 moves to a6
        squares[2][0].addPiece(squares[1][0].getPiece());
        squares[1][0].removePiece();

        Assertions.assertTrue(squares[2][0].isChessPiece());
        Assertions.assertFalse(squares[2][0].getPiece() == null);
        Assertions.assertTrue(squares[2][0].getColor().equals(Color.BLACK));
        Assertions.assertFalse(squares[1][0].getPiece() != null);

        //resetting the board
        squares[1][0].addPiece(squares[2][0].getPiece());
        squares[2][0].removePiece();

    }

    @Test
    void testKillConfig(){
        //.kill() return false on a6 for the black Knight on b8.
        Assertions.assertFalse(squares[0][1].getPiece().kill(squares[2][0]));
        Assertions.assertTrue(squares[0][1].getPiece().move(squares[2][0]));

        //A white pawn is placed on a6
        squares[2][0].addPiece(squares[6][0].getPiece());

        //.kill() should return true on a6 for the black Knight on b8.
        Assertions.assertTrue(squares[0][1].getPiece().kill(squares[2][0]));
        Assertions.assertFalse(squares[0][1].getPiece().move(squares[2][0]));

        //resetting the board
        squares[2][0].removePiece();

    }

}