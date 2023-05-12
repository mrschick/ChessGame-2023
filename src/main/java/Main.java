import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {

        Board board = new Board();
        MenuBar menuBar = new MenuBar();

        MenuItem menuItem1 = new MenuItem("Information");
        Menu menu = new Menu("Menu");

        MenuItem menuItem2 = new MenuItem("The Pawn");
        menuItem2.setOnAction(event -> {
            MenuHandler menuHandler = new MenuHandler();
            menuHandler.handlePawnAction(event);
        });

        MenuItem menuItem3 = new MenuItem("The Bishop");
        menuItem3.setOnAction(event -> {
            MenuHandler menuHandler = new MenuHandler();
            menuHandler.handleBishopAction(event);
        });

        MenuItem menuItem4 = new MenuItem("The Knight");
        menuItem4.setOnAction(event -> {
            MenuHandler menuHandler = new MenuHandler();
            menuHandler.handleKnightAction(event);
        });

        MenuItem menuItem5 = new MenuItem("The Rook");
        menuItem5.setOnAction(event -> {
            MenuHandler menuHandler = new MenuHandler();
            menuHandler.handleRookAction(event);
        });

        MenuItem menuItem6 = new MenuItem("The Queen");
        menuItem6.setOnAction(event -> {
            MenuHandler menuHandler = new MenuHandler();
            menuHandler.handleQueenAction(event);
        });

        MenuItem menuItem7 = new MenuItem("The King");
        menuItem7.setOnAction(event -> {
            MenuHandler menuHandler = new MenuHandler();
            menuHandler.handleKingAction(event);
        });

        menu.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6, menuItem7);
        menuBar.getMenus().add(menu);

        BorderPane borderPane = new BorderPane(board, menuBar, null, null, null);
        borderPane.setStyle("-fx-background-color: rgb(222, 184, 135);");
        MenuHandler menuHandler = new MenuHandler();
        menuItem1.setOnAction(event -> menuHandler.handleInformationAction(event));

        Scene scene = new Scene(borderPane, 1100, 780);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess 2D");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}