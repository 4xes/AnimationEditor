package com.petrushin.ui;

import com.petrushin.JavaFxApplication;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Petrushin Alexey on 02.12.2014.
 */
public class Hint extends VBox {

    private Text buttonHint;

    private static boolean flagHint = false;

    private static VBox vboxHint;

    public Hint(){
        this.setLayoutX(550);
        this.setLayoutY(30);
        this.setSpacing(8);

        Text textHintAddAnchor = new Text(
                "Add point: double click\n" +
                "Add line: left click on point\n" +
                "Delete: right click\n" +
                "Stop bind: right click\n" +
                "Key A - back, B - next\n" +
                "Key Q - fastback, R - fastnext");

        vboxHint = this;

        Font iconFonts = FontMetrizeIcons.getFont(this, 20);
        buttonHint = new Text(FontMetrizeIcons.QUESTION);
        buttonHint.setFont(iconFonts);
        buttonHint.getStyleClass().add("text-hint");

        getChildren().addAll(textHintAddAnchor);

        buttonHint.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (flagHint)
                    JavaFxApplication.getInstance().getBorderPane().getChildren().remove(vboxHint);
                else
                    JavaFxApplication.getInstance().getBorderPane().getChildren().add(vboxHint);
                flagHint = !flagHint;
            }
        });

        buttonHint.toFront();
        buttonHint.setLayoutX(699);
        buttonHint.setLayoutY(19);
        JavaFxApplication.getInstance().getBorderPane().getChildren().add(buttonHint);
    }
}
