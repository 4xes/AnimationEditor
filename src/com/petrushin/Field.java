package com.petrushin;

import com.petrushin.figures.Anchor;
import com.petrushin.figures.BoundLine;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Created by Petrushin Alexey on 28.11.2014.
 */
public class Field extends Pane {
    public static final double WIDTH_EF = 700;
    public static final double HEIGHT_EF = 500;
    public static final double MARGIN_EF = 10;

    public static final double RIGHT_EF = WIDTH_EF;
    public static final double BOTTOM_EF = HEIGHT_EF;

    private BoundLine bindingLine;
    private Anchor fromBinding;
    private Anchor enteredAnchor;

    EventHandler<MouseEvent> handlerOnMouseEnteredTarget = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Object target = event.getTarget();
            if(event.getTarget() instanceof Anchor){
                enteredAnchor = (Anchor) target;

            };
        }
    };



    EventHandler<MouseEvent> handlerOnMouseMoved = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(bindingLine != null){
                double x = event.getX();
                double y = event.getY();

                //within the boundaries
                if( x < 0)
                    x = 0;
                if(y < 0)
                    y = 0;
                if(x > RIGHT_EF)
                    x = RIGHT_EF;
                if(y > BOTTOM_EF)
                    y = BOTTOM_EF;

                bindingLine.setEndX(x);
                bindingLine.setEndY(y);
            }
        }
    };

    public void startBinding(Anchor anchor, double x, double y){
        bindingLine = new BoundLine(anchor);
        bindingLine.setEndX(x);
        bindingLine.setEndY(y);
        getChildren().add(bindingLine);
        anchor.toFront();
        addEventHandler(MouseEvent.MOUSE_MOVED, handlerOnMouseMoved);
        addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, handlerOnMouseEnteredTarget);
    }

    public void cancelBinding(){
        if(bindingLine != null){
            bindingLine.delete();
            bindingLine = null;
            removeEventHandler(MouseEvent.MOUSE_MOVED, handlerOnMouseMoved);
        }
    }


    public Field(){
        super();
        setPrefSize(WIDTH_EF, HEIGHT_EF);
        getStyleClass().add("edit-field");

        initEvents();
        //delete default focus
        requestFocus();
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
                    if(mouseEvent.getClickCount() == 1){
                        Object target = mouseEvent.getTarget();
                        if(target instanceof Anchor){
                            startBinding((Anchor)target, mouseEvent.getX(), mouseEvent.getY());
                        }
                    }
                }
            }
        });


    }
}
