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
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        // Menu scene setup, with short explanation of the game and settings.
        FlowPane menuPane = new FlowPane(Orientation.VERTICAL);
        menuPane.setAlignment(Pos.TOP_CENTER);
        menuPane.setVgap(10);
        Scene menuScene = new Scene(menuPane, 1150, 700);
        Label menutxt = new Label("This is a 2D Chess Game.\n\n"
                            +"\n"
                            +"\n"
                            +"\n"
                            +"To play, simply choose your preferred Color Scheme and time limit below and then hit 'Start Game':\n\n");

        int activeScheme = 0;
        ColorSchemes schemes = new ColorSchemes();
        FlowPane colorSchemes = new FlowPane();
        colorSchemes.getChildren().addAll(schemes);

        FlowPane setting1 = new FlowPane();
        TextField setting1_tf = new TextField("1");
        setting1.getChildren().addAll(new Label("Chosen color scheme: "), setting1_tf);

        Button start = new Button("Start Game");

        menuPane.getChildren().addAll(menutxt, colorSchemes, /*time,*/ setting1, /*setting2,*/ start);


        // Game scene setup, with chessboard and current game info.
        FlowPane gamePane = new FlowPane();
        Scene gameScene = new Scene(gamePane, 1150, 700);
        start.setOnMouseClicked(evt -> {
            primaryStage.setScene(gameScene);
            try {
                schemes.activeIdx = Integer.parseInt(setting1_tf.getText()) - 1;
                System.out.println("Selected scheme: "+schemes.activeIdx);
            } catch (NumberFormatException e) {
                schemes.activeIdx = 0;
                System.out.println("Unable to parse '"+setting1_tf.getText()+"' to integer");
            }
            gameScene.setFill(schemes.activeWindowBG());
            gamePane.setBackground(new Background(new BackgroundFill(
                    schemes.activeWindowBG(),
                    new CornerRadii(10),
                    new Insets(10)
            )));
        });
        Board board = new Board(schemes);
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