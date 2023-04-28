import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {

    Color black = Color.BLACK;
    Color white = Color.WHITE;

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    Square sq = new Square();
                    if (j % 2 == 0)
                        sq.setColor(black);
                    else
                        sq.setColor(white);
                    add(sq, j, i);
                } else {
                    Square sq = new Square();
                    if (j % 2 == 0)
                        sq.setColor(white);
                    else
                        sq.setColor(black);
                    add(sq, j, i);
                }
            }
        }

    }

}
