package com.petrushin.shape;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Created by Petrushin Alexey on 28.11.2014.
 */
public class BoundLine extends Line{
    public Anchor getStart() {
        return start;
    }

    public Anchor getEnd() {
        return end;
    }

    private Anchor start;
    private Anchor end;

    public BoundLine(Anchor start){
        this.start = start;
        this.start.bind(this);

        getStyleClass().add("bind-line");
        //Binding property
        bind();
    }



    public BoundLine(Anchor start, Anchor end) {
        this.start = start;
        this.end = end;
        this.start.bind(this);
        this.end.bind(this);

        getStyleClass().add("bind-line");
        //Binding property
        bind();
    }
    public void bindEnd(Anchor bindAnchor){
        this.end = bindAnchor;
        this.end.bind(this);
        bind();
    }
    //for deleteFromParent from anchor
    public void deleteFromParent(Anchor who){
        unbind();
        if(who == start && end != who && end != null){
            end.unbind(this);
        }
        if(who == end && start != who && start !=null){
            start.unbind(this);
        }
        start = null;
        end = null;
        Pane pane = (Pane) this.getParent();
        pane.getChildren().remove(this);
    }

    public void beFree(){
        unbind();
        //deleteFromParent self in anchors
        if(start != null){
            start.unbind(this);
            start = null;
        }
        if(end != null){
            end.unbind(this);
            end = null;
        }
    }

    //for deleteFromParent self
    public void deleteFromParent(){
        unbind();
        beFree();
        //deleteFromParent self in anchors
        Pane pane = (Pane) this.getParent();
        pane.getChildren().remove(this);
    }

    private void bind(){
        if(start != null){
            startXProperty().bind(start.getBoundX());
            startYProperty().bind(start.getBoundY());
        }
        if(end != null) {
            endXProperty().bind(end.getBoundX());
            endYProperty().bind(end.getBoundY());
        }
    }



    public void unbind(){
        startXProperty().unbind();
        startYProperty().unbind();
        endXProperty().unbind();
        endYProperty().unbind();
    }

}
