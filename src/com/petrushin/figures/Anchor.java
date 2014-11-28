package com.petrushin.figures;

import com.petrushin.Field;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Anchor extends Circle {
    private DoubleProperty boundX = new SimpleDoubleProperty();
    private DoubleProperty boundY = new SimpleDoubleProperty();

    private Set<BoundLine> boundLines = new HashSet<BoundLine>();

    public Anchor(double x, double y){
        super(x, y, 10);
        getStyleClass().add("anchor");
        boundX.set(x);
        boundY.set(y);

        boundX.bind(centerXProperty());
        boundY.bind(centerYProperty());
        initEvents();
    }

    public void unbind(BoundLine line){
        boundLines.remove(line);
    }
    public void bind(BoundLine line){
        boundLines.add(line);
    }

    public void delete(){
        System.out.println(boundLines.size());
        Iterator<BoundLine> iterator = boundLines.iterator();
        while (iterator.hasNext()) {
            BoundLine element = iterator.next();
            element.delete(this);
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

    private void initEvents() {
        final Delta delta = new Delta();

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                delta.x = getCenterX() - event.getSceneX();
                delta.y = getCenterY() - event.getSceneY();
                //move up
                toFront();
            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.SECONDARY)){
                    if(event.getClickCount() == 1){
                        delete();
                    }
                }
            }
        });

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double newX = event.getSceneX() + delta.x;
                double newY = event.getSceneY() + delta.y;
                if(newX < 0){
                    newX = 0;
                }
                if(newY < 0){
                    newY = 0;
                }
                if(newX > Field.RIGHT_EF){
                    newX = Field.RIGHT_EF;
                }
                if(newY > Field.BOTTOM_EF){
                    newY = Field.BOTTOM_EF;
                }
                setCenterX(newX);
                setCenterY(newY);
            }
        });
    }

    private class Delta { double x, y; }
}
