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

        ColumnConstraints[] cols = new ColumnConstraints[2];

        this.add(new Text("Dark Squares "), 0, 0);
        this.add(new Rectangle(squaresize, squaresize, dark), 1, 0);
        this.add(new Text("Light Squares "), 0, 1);
        this.add(new Rectangle(squaresize, squaresize, light), 1, 1);
        this.add(new Text("Board Background "), 0, 2);
        this.add(new Rectangle(squaresize, squaresize, bBg), 1, 2);
        this.add(new Text("Window Background "), 0, 3);
        this.add(new Rectangle(squaresize, squaresize, wBg), 1, 3);
    }
}
