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

        var activeScheme = new Object() { // needs to be anonymous object to be accessed by Event Handlers without Java complaining
            int value = 0;
        };
        ColorScheme[] schemes = new ColorScheme[3];
        schemes[0] = new ColorScheme(
            Color.rgb(133, 94, 66),
            Color.rgb(222, 184, 135),
            Color.rgb(189, 168, 145),
            Color.rgb(200, 200, 200)
        );
        schemes[1] = new ColorScheme(
            Color.rgb(150, 150, 150),
            Color.rgb(237, 237, 237),
            Color.rgb(120, 120, 120),
            Color.rgb(200, 200, 200)
        );
        schemes[2] = new ColorScheme(
            Color.rgb(118, 150, 86),
            Color.rgb(238, 238, 210),
            Color.rgb(185, 144, 24),
            Color.rgb(255, 245, 200)
        );

        FlowPane colorSchemes = new FlowPane();
        colorSchemes.setHgap(10);
        colorSchemes.setVgap(10);
        for (ColorScheme cs : schemes) {
            colorSchemes.getChildren().addAll(cs);
        }
        TextField colorSchemeSelector = new TextField("1");
        colorSchemes.getChildren().add(colorSchemeSelector);

        Button start = new Button("Start Game");

        menuPane.getChildren().addAll(menutxt, colorSchemes, /*time,*/ start);


        // Game scene setup, with chessboard and current game info.
        FlowPane gamePane = new FlowPane();
        Scene gameScene = new Scene(gamePane, 1150, 700);
        var boardObj = new Object() { // needs to be anonymous object to be accessed by Event Handlers without Java complaining
            Board board = new Board(schemes[activeScheme.value]);
        };
        start.setOnMouseClicked(evt -> {
            primaryStage.setScene(gameScene);
            activeScheme.value = Integer.parseInt(colorSchemeSelector.getText()) - 1;
            if (activeScheme.value < 0) activeScheme.value = 0;
            if (activeScheme.value > 2) activeScheme.value = 2;
            System.out.println("activeScheme.value = "+activeScheme.value);
            boardObj.board = new Board(schemes[activeScheme.value]);
            gameScene.setFill(schemes[activeScheme.value].windowBg);
            gamePane.setBackground(new Background(new BackgroundFill(
                    schemes[activeScheme.value].windowBg,
                    new CornerRadii(10),
                    new Insets(10)
            )));
        });
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
        gamePane.getChildren().addAll(boardObj.board, gameInfo);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("ChessGame");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}