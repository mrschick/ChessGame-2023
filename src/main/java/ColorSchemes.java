import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorSchemes extends GridPane {
    final float squaresize = 30;

    public int activeIdx = 0;

    Color[] dark = new Color[3];
    Color[] light = new Color[3];
    Color[] boardBg = new Color[3];
    Color[] windowBg = new Color[3];

    public ColorSchemes() {
        dark[0] = Color.rgb(133, 94, 66);
        light[0] = Color.rgb(222, 184, 135);
        boardBg[0] = Color.rgb(189, 168, 145);
        windowBg[0] = Color.rgb(200, 200, 200);

        dark[1] = Color.rgb(72, 151, 64);
        light[1] = Color.rgb(241, 232, 191);
        boardBg[1] = Color.rgb(218, 200, 91);
        windowBg[1] = Color.rgb(230, 230, 230);

        dark[2] = Color.rgb(40, 40, 40);
        light[2] = Color.WHITE;
        boardBg[2] = Color.rgb(180, 180, 180);
        windowBg[2] = Color.rgb(230, 230, 230);

        //schemes[0].setOnMouseClicked(evt -> {  });

        ColumnConstraints[] cols = new ColumnConstraints[2];
        this.setHgap(30);

        this.add(new Label("Dark Squares "), 0, 1);
        this.add(new Label("Light Squares "), 0, 2);
        this.add(new Label("Board Background "), 0, 3);
        this.add(new Label("Window Background "), 0, 4);
        this.add(new Label("1"), 1, 0);
        this.add(new Label("2"), 2, 0);
        this.add(new Label("3"), 3, 0);
        for (int i = 0; i < 3; i++) {
            this.add(new Rectangle(squaresize, squaresize, dark[i]),i+1, 1);
            this.add(new Rectangle(squaresize, squaresize, light[i]),i+1, 2);
            this.add(new Rectangle(squaresize, squaresize, boardBg[i]),i+1, 3);
            this.add(new Rectangle(squaresize, squaresize, windowBg[i]),i+1, 4);
        }
    }

    public Color activeDark() {
        return this.dark[activeIdx];
    }
    public Color activeLight() {
        return this.light[activeIdx];
    }
    public Color activeBoardBG() {
        return this.boardBg[activeIdx];
    }
    public Color activeWindowBG() {
        return this.windowBg[activeIdx];
    }
}
