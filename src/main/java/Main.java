import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        // Menu scene setup, with short explanation of the game and settings.
        FlowPane menuPane = new FlowPane(Orientation.VERTICAL);
        Scene menuScene = new Scene(menuPane, 1150, 700);
        Label menutxt = new Label("This is a chess game\n"
                            +"\n"
                            +"\n"
                            +"\n"
                            +"\n");
        Button start = new Button("Start Game");
        menuPane.getChildren().addAll(menutxt, start);

        // Game scene setup, with chessboard and current game info.
        FlowPane gamePane = new FlowPane();
        Scene gameScene = new Scene(gamePane, 1150, 700);
        start.setOnMouseClicked(evt -> {
            primaryStage.setScene(gameScene);
        });
        Board board = new Board();
        FlowPane gameInfo = new FlowPane(Orientation.VERTICAL);
        gameInfo.setPadding(new Insets(30, 20, 30, 20));
        gameInfo.setVgap(10);

        FlowPane wPlayerInfo = new FlowPane();
        wPlayerInfo.setVgap(10);
        wPlayerInfo.setPadding(new Insets(30, 20, 30, 20));
        wPlayerInfo.setStyle("-fx-border-color: white");
        Label wInfo = new Label("White player:\n\n");
        wPlayerInfo.getChildren().addAll(wInfo);

        FlowPane bPlayerInfo = new FlowPane();
        bPlayerInfo.setVgap(10);
        bPlayerInfo.setPadding(new Insets(30, 20, 30, 20));
        wPlayerInfo.setStyle("-fx-border-color: black");
        Label bInfo = new Label("Black Player:\n\n");
        bPlayerInfo.getChildren().addAll(bInfo);

        gameInfo.getChildren().addAll(wPlayerInfo, bPlayerInfo);
        gamePane.getChildren().addAll(board, gameInfo);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("ChessGame");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}