
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class Board extends GridPane {

    Color first = Color.rgb(133, 94, 66);
    Color second = Color.rgb(222, 184, 135);
    Color black = Color.BLACK;

    public Board() {
        setBackground(new Background(new BackgroundFill(Color.rgb(222, 184, 135), CornerRadii.EMPTY, Insets.EMPTY)));

        for (int i = 0; i < 8; i++) {
            int postion_number = 8;
            for (int j = 0; j < 8; j++) {
                char position_char = (char) (j+ 97);
                if (i % 2 != 0) {
                    Square sq = new Square();
                    sq.setPosition(String.valueOf(position_char) + postion_number);

                    if (j % 2 == 0)
                        sq.setColor(first);
                    else
                        sq.setColor(second);
                    add(sq, j + 1, i + 1);
                }
                else {
                    Square sq = new Square();
                    sq.setPosition(String.valueOf(position_char) + postion_number);

                    if (j % 2 == 0)
                        sq.setColor(second);
                    else
                        sq.setColor(first);
                    add(sq, j + 1, i + 1);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Character.toString((char) ('a' + i)));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            text.setFill(black);
            add(text, i + 1, 0);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
        }

        for (int i = 0; i < 8; i++) {
            Text text = new Text(Integer.toString(8 - i));
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            text.setFill(black);
            add(text, 0, i + 1);
            setHalignment(text, HPos.CENTER);
            setValignment(text, VPos.CENTER);
        }
    }
}