package com.petrushin;

import com.petrushin.shape.Anchor;
import com.petrushin.shape.BoundLine;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.HashSet;

/**
 * Created by Petrushin Alexey on 28.11.2014.
 */
public class Field extends Pane {
    public static final double WIDTH_EF = 700;
    public static final double HEIGHT_EF = 500;

    public static final double RIGHT_EF = WIDTH_EF;
    public static final double BOTTOM_EF = HEIGHT_EF;

    private BoundLine bindingLine;
    private Anchor bindingAnchor;

    Storage storage = new Storage();

    HashSet<Shape> frame = new HashSet<Shape>();
    int numOfFrame = 0;

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
    public void Next(){

    }

    public void test(){
        ObservableList<Node> list = getChildren();
        System.out.println(list.size());
        for(Node obj: list){
            System.out.println(obj);
        }
    }

    private void initEvents(){

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //if not dragged
                if(!event.isStillSincePress()){
                    return;
                }
                Object target = event.getTarget();
                if (event.getButton().equals(MouseButton.PRIMARY)) {

                    if (event.getClickCount() == 2 && bindingLine == null && !(target instanceof Anchor)) {

                        Anchor anchor = new Anchor(event.getX(), event.getY());
                        getChildren().add(anchor);
                    }
                    if (event.getClickCount() == 1) {

                        if (target instanceof Anchor) {
                            Anchor anchor = (Anchor) target;
                            if (bindingLine == null) {
                                startBinding(anchor, event.getX(), event.getY());
                            } else {
                                if (anchor == bindingAnchor) {
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
