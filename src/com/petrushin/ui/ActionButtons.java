package com.petrushin.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Petrushin Alexey on 02.12.2014.
 */
public class ActionButtons extends HBox{

    private Label labelNumberScene;

    private Button buttonBack;
    private Button buttonNext;
    private Button buttonCopy;
    private Button buttonDelete;

    public ActionButtons(){
        this.getStyleClass().add("action-field");

        labelNumberScene = new Label("Scene 1 of 1");
        labelNumberScene.getStyleClass().add("action-num-scene");

        Font iconFonts = FontMetrizeIcons.getFont(this, 25);
        buttonBack = new Button(FontMetrizeIcons.ARROW_LEFT);
        buttonBack.setFont(iconFonts);
        buttonBack.getStyleClass().add("action-button");


        buttonNext = new Button(FontMetrizeIcons.ARROW_RIGHT);
        buttonNext.setFont(iconFonts);
        buttonNext.getStyleClass().add("action-button");

        buttonDelete = new Button(FontMetrizeIcons.CROSS);
        buttonDelete.setFont(iconFonts);
        buttonDelete.getStyleClass().add("action-button");


        buttonBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("buttonBack");
            }
        });

        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("buttonNext");
            }
        });

        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("buttonDelete");
            }
        });

        getChildren().addAll(labelNumberScene, buttonBack, buttonNext, buttonDelete);
    }

}
