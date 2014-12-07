package com.petrushin.shape;

import com.petrushin.Field;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Anchor extends Circle implements Shape{
    private DoubleProperty boundX = new SimpleDoubleProperty();
    private DoubleProperty boundY = new SimpleDoubleProperty();

    final Delta delta = new Delta();

    private Set<BoundLine> boundLines = new HashSet<BoundLine>();

    public int size(){
        return boundLines.size();
    }

    public boolean haveCommonLines(Anchor anchor){
        Iterator<BoundLine> iterator = anchor.boundLines.iterator();

        while(iterator.hasNext()){
            if(boundLines.contains(iterator.next())){
                return true;
            }
        }
        return false;
    }

    public Anchor(double x, double y){
        super(x, y, 10);
        getStyleClass().add("anchor");
        boundX.set(x);
        boundY.set(y);

        boundX.bind(centerXProperty());
        boundY.bind(centerYProperty());
        setEvents();
    }



    public void unbind(BoundLine line){
        boundLines.remove(line);
    }
    public void bind(BoundLine line){
        boundLines.add(line);
    }

    public void beFree(){
        Iterator<BoundLine> iterator = boundLines.iterator();
        while (iterator.hasNext()) {
            BoundLine element = iterator.next();
            element.deleteFromParent(this);
            iterator.remove();
        }
        unsetEvents();
    }

    public void deleteFromParent(){
        Iterator<BoundLine> iterator = boundLines.iterator();
        while (iterator.hasNext()) {
            BoundLine element = iterator.next();
            element.deleteFromParent(this);
            iterator.remove();
        }

        Pane pane = (Pane) this.getParent();
        pane.getChildren().remove(this);
    }

    public DoubleProperty getBoundX(){
        return boundX;
    }
    public DoubleProperty getBoundY(){
        return boundY;
    }

    EventHandler<MouseEvent> mousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            delta.x = getCenterX() - event.getSceneX();
            delta.y = getCenterY() - event.getSceneY();
            toFront();
        }
    };

    EventHandler<MouseEvent> mouseDragged = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            double x = event.getSceneX() + delta.x;
            double y = event.getSceneY() + delta.y;
            if(x < 0)
                x = 0;
            if(y < 0)
                y = 0;
            if(x > Field.RIGHT_EF)
                x = Field.RIGHT_EF;
            if(y > Field.BOTTOM_EF)
                y = Field.BOTTOM_EF;
            setCenterX(x);
            setCenterY(y);
        }
    };

    public void setEvents() {
        addEventFilter(MouseEvent.MOUSE_PRESSED, mousePressed);
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDragged);
    }

    public void unsetEvents(){
        removeEventFilter(MouseEvent.MOUSE_PRESSED, mousePressed);
        removeEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDragged);
    }



    private class Delta { double x, y; }
}
