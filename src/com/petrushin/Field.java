package com.petrushin;

import com.petrushin.shape.Anchor;
import com.petrushin.shape.BoundLine;
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

    private BoundLine bindingLine;
    private Anchor bindingAnchor;




    EventHandler<MouseEvent> handlerOnMouseEnteredTarget = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Object target = event.getTarget();
            if(event.getTarget() instanceof Anchor){
                //enteredAnchor = (Anchor) target;

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
        bindingAnchor = anchor;
        bindingLine.toBack();
        anchor.toFront();
        addEventHandler(MouseEvent.MOUSE_MOVED, handlerOnMouseMoved);
        addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, handlerOnMouseEnteredTarget);

    }

    //if clicked to field
    public void endBindingWithNewAnchor(double x, double y){

    }


    public void cancelBinding(){
        if(bindingLine != null){
            removeEventHandler(MouseEvent.MOUSE_MOVED, handlerOnMouseMoved);
            bindingLine.delete();
            bindingLine = null;

        }
    }

    public void endBinding(Anchor anchor){
        System.out.println("endBinding");
        removeEventHandler(MouseEvent.MOUSE_MOVED, handlerOnMouseMoved);
        bindingLine.bindEnd(anchor);
        bindingLine = null;
        bindingAnchor = null;
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
            public void handle(MouseEvent event) {
                if(!event.isStillSincePress()){
                    return;
                }
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2 && bindingLine == null) {
                        Anchor anchor = new Anchor(event.getX(), event.getY());
                        getChildren().add(anchor);
                    }
                    if (event.getClickCount() == 1) {
                        Object target = event.getTarget();
                        if (target instanceof Anchor) {
                            Anchor anchor = (Anchor) target;
                            if (bindingLine == null) {
                                startBinding(anchor, event.getX(), event.getY());
                            } else {
                                System.out.println(anchor + " " + bindingAnchor);
                                if (anchor == bindingAnchor) {
                                    System.out.println(anchor);
                                    cancelBinding();
                                } else {
                                    System.out.println(bindingAnchor.size() + " " + anchor.size());
                                    if (bindingAnchor.size() == 1 || anchor.size() == 0) {
                                        endBinding(anchor);
                                    } else if (!anchor.haveCommonLines(bindingAnchor)) {
                                        endBinding(anchor);
                                    }
                                }
                            }
                        }
                    }
                }
                if (event.getButton().equals(MouseButton.SECONDARY)) {
                    Object target = event.getTarget();
                    if (bindingLine == null) {

                        if (target instanceof Anchor) {
                            ((Anchor) target).delete();
                        } else if (target instanceof BoundLine) {
                            ((BoundLine) target).delete();
                        }

                    } else if (bindingLine != null) {
                        cancelBinding();
                    }
                }

            }
        });


    }
}
