import java.util.*;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ChessGUI extends Application {
	public void start(Stage primaryStage) {
    FlowPane testphase = new FlowPane(Orientation.VERTICAL);
    Scene testscene = new Scene(testphase, 800, 600);

    primaryStage.setScene(testscene);
    primaryStage.setTitle("ChessGame");
    primaryStage.show();
	}

	public static void StartGUI(String[] args) {
		launch(args);
	}
}
