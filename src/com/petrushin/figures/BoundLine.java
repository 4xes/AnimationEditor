package com.petrushin.figures;

import com.petrushin.Field;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Created by Petrushin Alexey on 28.11.2014.
 */
public class BoundLine extends Line{
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
    }
    //for delete from anchor
    public void delete(Anchor who){
        unbind();
        //delete self only in second anchor
        if(who == start && end != who && end != null){
            end.unbind(this);
        }
        if(who == end && start != who && start !=null){
            start.unbind(this);
        }
        Pane pane = (Pane) this.getParent();
        pane.getChildren().remove(this);
    }

    //for delete self
    public void delete(){
        unbind();
        //delete self in anchors
        if(start != null){
            start.unbind(this);
        }
        if(end != null){
            end.unbind(this);
        }
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
