import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {

        //Background color for the main page afte running code.
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        VBox menuPane = new VBox(50); 
        menuPane.setBackground(background);
        menuPane.setAlignment(Pos.TOP_CENTER);
        Scene menuScene = new Scene(menuPane, 1150, 700);

      
        //Creation of Menubar, located at the top of the window.
        MenuBar menuBar = new MenuBar();
        Menu logicMenu = new Menu("How to play?");
        Menu infoMenu = new Menu ("Information");

        //First menu containing the informations about each chess piece.
        MenuItem pawnMenuItem = new MenuItem("Pawn Information");
        MenuItem bishopMenuItem = new MenuItem("Bishop Information");
        MenuItem knightMenuItem = new MenuItem("Knight Information");
        MenuItem rookMenuItem = new MenuItem("Rook Information");
        MenuItem queenMenuItem = new MenuItem("Queen Information");
        MenuItem kingMenuItem = new MenuItem("King Information");

        
        pawnMenuItem.setOnAction(new MenuHandler()::handlePawnAction);
        bishopMenuItem.setOnAction(new MenuHandler()::handleBishopAction);
        knightMenuItem.setOnAction(new MenuHandler()::handleKnightAction);
        rookMenuItem.setOnAction(new MenuHandler()::handleRookAction);
        queenMenuItem.setOnAction(new MenuHandler()::handleQueenAction);
        kingMenuItem.setOnAction(new MenuHandler()::handleKingAction);

        //Second menu basic info about the group
        MenuItem infoMenuItem1 = new MenuItem("Group Members");
        MenuItem infoMenuItem2 = new MenuItem("Pawn Information");
        MenuItem infoMenuItem3 = new MenuItem("Pawn Information");
        MenuItem infoMenuItem4 = new MenuItem("Pawn Information");
        MenuItem infoMenuItem5 = new MenuItem("Pawn Information");

        infoMenuItem1.setOnAction(new MenuHandler()::handleMember1);
        infoMenuItem2.setOnAction(null);
        infoMenuItem3.setOnAction(null);
        infoMenuItem4.setOnAction(null);
        infoMenuItem5.setOnAction(null);


         //Items of first menu to be shown on the menubar
        logicMenu.getItems().addAll(
                pawnMenuItem,
                bishopMenuItem,
                knightMenuItem,
                rookMenuItem,
                queenMenuItem,
                kingMenuItem
        );
         
        //Items of second menu to be shown on the menubar
        infoMenu.getItems().addAll(
            infoMenuItem1
                
        );

        menuBar.getMenus().add(logicMenu);
        menuBar.getMenus().add(infoMenu);


        //Text and its position in window.
        Label menutxt = new Label("Welcome to Chess Game");
        menutxt.setMaxWidth(Double.MAX_VALUE);
        menutxt.setAlignment(Pos.CENTER);

        // Font size, color and family.
        Font font = Font.font("Arial", 35); 
        menutxt.setFont(font);
        menutxt.setTextFill(Color.BLACK);

        int activeScheme = 0;
        ColorScheme[] schemes = new ColorScheme[3];
        schemes[0] = new ColorScheme(
                Color.rgb(133, 94, 66),
                Color.rgb(222, 184, 135),
                Color.rgb(189, 168, 145),
                Color.rgb(200, 200, 200)
        );

        FlowPane colorSchemes = new FlowPane();
        colorSchemes.setAlignment(Pos.CENTER);
        colorSchemes.getChildren().addAll(schemes[0]);

        Button start = new Button("Start Game");
        FlowPane buttonPane = new FlowPane(start);
        buttonPane.setAlignment(Pos.CENTER);

        menuPane.getChildren().addAll(menuBar, menutxt, colorSchemes, buttonPane);

        
        
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