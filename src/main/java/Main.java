import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        // Menu scene setup, with short explanation of the game and settings.
        FlowPane menuPane = new FlowPane(Orientation.VERTICAL);
        menuPane.setAlignment(Pos.TOP_CENTER);
        Scene menuScene = new Scene(menuPane, 1150, 700);
        Label menutxt = new Label("This is a 2D Chess Game.\n\n"
                            +"It has been created \n"
                            +"\n"
                            +"\n"
                            +"\n");

        int activeScheme = 0;
        ColorScheme[] schemes = new ColorScheme[3];
        schemes[0] = new ColorScheme(
                Color.rgb(133, 94, 66),
                Color.rgb(222, 184, 135),
                Color.rgb(189, 168, 145),
                Color.rgb(200, 200, 200)
        );

        FlowPane colorSchemes = new FlowPane();
        colorSchemes.getChildren().addAll(schemes[0]);

        Button start = new Button("Start Game");

        menuPane.getChildren().addAll(menutxt, colorSchemes, /*time,*/ start);


        // Game scene setup, with chessboard and current game info.
        FlowPane gamePane = new FlowPane();
        Scene gameScene = new Scene(gamePane, 1150, 700);
        start.setOnMouseClicked(evt -> {
            primaryStage.setScene(gameScene);
            gameScene.setFill(schemes[activeScheme].windowBg);
            gamePane.setBackground(new Background(new BackgroundFill(
                    schemes[activeScheme].windowBg,
                    new CornerRadii(10),
                    new Insets(10)
            )));
        });
        Board board = new Board(schemes[activeScheme]);
        FlowPane gameInfo = new FlowPane(Orientation.VERTICAL);
        gameInfo.setPadding(new Insets(30, 20, 30, 20));
        gameInfo.setVgap(10);

        FlowPane wPlayerInfo = new FlowPane();
        wPlayerInfo.setVgap(10);
        wPlayerInfo.setPadding(new Insets(30, 20, 30, 20));
        wPlayerInfo.setStyle("-fx-border-color: white;");
        Label wInfo = new Label("White player:\n\n");
        wPlayerInfo.getChildren().addAll(wInfo);

        FlowPane bPlayerInfo = new FlowPane();
        bPlayerInfo.setVgap(10);
        bPlayerInfo.setPadding(new Insets(30, 20, 30, 20));
        bPlayerInfo.setStyle("-fx-border-color: black;");
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