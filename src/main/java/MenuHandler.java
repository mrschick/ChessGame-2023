import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MenuHandler {

    // Alert creation with INFORMATION type, title and header text.
    public void handlePawnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The Pawn");
        alert.setHeaderText("When a game begins, each side starts with eight pawns. "
                + "The pawn is the least powerful. If it is a pawn's first move, it can move forward one or two squares.");
        alert.showAndWait();
    }

    // Alert creation with INFORMATION type, title and header text.
    public void handleBishopAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The Bishop");
        alert.setHeaderText(
                "A bishop can move diagonally as many squares as it likes, as long as it is not blocked by its own pieces or an occupied square.");
        alert.showAndWait();
    }
    
    // Alert creation with INFORMATION type, title and header text.
    public void handleKnightAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The Knight");
        alert.setHeaderText(
                "Each side starts with two knights,the knight moves in an L-shape.The knight can capture only what it lands on, not what it jumps over!");
        alert.showAndWait();
    }
    // Alert creation with INFORMATION type, title and header text.
    public void handleRookAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The Rook");
        alert.setHeaderText(
                "Each side starts with two rooks,it can move as many squares as it likes left or right horizontally, or as many squares as it likes up or down vertically.");
        alert.showAndWait();
    }
    // Alert creation with INFORMATION type, title and header text.
    public void handleQueenAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The Queen");
        alert.setHeaderText(
                "The queen is the most powerful chess piece!It can move as many squares as it likes left or right horizontally,"
                        + " or as many squares as it likes up or down vertically,can also move as many squares as it likes diagonally.");
        alert.showAndWait();
    }
    
    // Alert creation with INFORMATION type, title and header text.
    public void handleKingAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The King");
        alert.setHeaderText(
                "The king is the most important chess piece. Remember, the goal of a game of chess is to checkmate the king!"
                        + "The king is not a very powerful piece, as it can only move (or capture) one square in any direction.");
        alert.showAndWait();
    }


    // second menu member
    // Alert creation with INFORMATION type, title and header text.
    public void handleMember1(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Members");
        alert.setHeaderText("This Java project was finalized with the contributions of the following group members: Sharjeel Zahid, Fabio Schick, and Ridvan Plluzhina.");
        alert.showAndWait();
    }

    }

    
