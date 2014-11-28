package com.petrushin;

import com.petrushin.figures.Anchor;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Created by Petrushin Alexey on 28.11.2014.
 */
public class Field extends Pane {
    public static final double WIDTH_EF = 700;
    public static final double HEIGHT_EF = 500;
    public static final double MARGIN_EF = 10;

    public static final double RIGHT_EF = WIDTH_EF;
    public static final double BOTTOM_EF = HEIGHT_EF;

    public Field(){
        super();
        setPrefSize(WIDTH_EF, HEIGHT_EF);
        getStyleClass().add("edit-field");

        initEvents();
    }

    private void initEvents(){
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        Anchor anchor = new Anchor(mouseEvent.getX(), mouseEvent.getY());
                        getChildren().add(anchor);
                    }
                }
            }
        });
    }
}
