package com.petrushin.ui;

import com.petrushin.JavaFxApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by Petrushin Alexey on 02.12.2014.
 */
public class Hint extends VBox {

    private Button buttonHint;

    private static boolean flagHint = false;

    private static VBox vboxHint;

    public Hint(){
        this.setLayoutX(550);
        this.setLayoutY(30);
        this.setSpacing(8);


        Text textHintAddAnchor = new Text("Add point: double click");
        Text textHintAddLine = new Text("Add line: left click on point");
        Text textHintDeleteElem = new Text("Delete: right click");
        Text textHintStartBind = new Text("Stop bind: right click");

        vboxHint = this;


        buttonHint = new Button();
        buttonHint.getStyleClass().add("text-hint");

        getChildren().addAll(textHintAddAnchor, textHintAddLine, textHintDeleteElem, textHintStartBind);

        buttonHint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (flagHint) {
                    JavaFxApplication.getInstance().getBorderPane().getChildren().remove(vboxHint);
                    buttonHint.setText("show hint");
                } else {
                    JavaFxApplication.getInstance().getBorderPane().getChildren().add(vboxHint);
                    buttonHint.setText("hide hint");
                }
                flagHint = !flagHint;
            }
        });


        buttonHint.toFront();
        JavaFxApplication.getInstance().getBorderPane().getChildren().add(buttonHint);
    }
}
