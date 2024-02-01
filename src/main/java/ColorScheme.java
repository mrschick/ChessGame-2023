import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorScheme extends GridPane {
    final float squaresize = 30;

    Color dark;
    Color light;
    Color boardBg;
    Color windowBg;

    public ColorScheme(Color dark, Color light, Color bBg, Color wBg) {
        this.dark = dark;
        this.light = light;
        this.boardBg = bBg;
        this.windowBg = wBg;

        initLabels();
    }

    public void initLabels() {
        this.add(new Label("Dark Squares "), 0, 0);
        this.add(new Rectangle(squaresize, squaresize, dark), 1, 0);
        this.add(new Label("Light Squares "), 0, 1);
        this.add(new Rectangle(squaresize, squaresize, light), 1, 1);
        this.add(new Label("Board Background "), 0, 2);
        this.add(new Rectangle(squaresize, squaresize, boardBg), 1, 2);
        this.add(new Label("Window Background "), 0, 3);
        this.add(new Rectangle(squaresize, squaresize, windowBg), 1, 3);
    }
}
