import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        Board board = new Board();
        Scene scene = new Scene(board, 675, 690);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess 2D");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}