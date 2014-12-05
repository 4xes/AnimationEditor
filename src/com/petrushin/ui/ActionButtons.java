package com.petrushin.ui;

import com.petrushin.JavaFxApplication;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Petrushin Alexey on 02.12.2014.
 */
public class ActionButtons extends HBox{

    public ActionButtons(){
        this.getStyleClass().add("action-field");

        Text buttonNext;
        Text buttonBack;
        Text buttonNew;
        Text buttonCopy;
        Text buttonDelete;
        Text buttonImport;
        Text buttonExport;

        Text textNumberScene  = new Text();
        textNumberScene.textProperty().bind(JavaFxApplication.getInstance().numFrameProperty());

        textNumberScene.getStyleClass().add("action-num-scene");
        Font iconFonts = FontMetrizeIcons.getFont(this, 25);
        buttonBack = new Text(FontMetrizeIcons.ARROW_LEFT);
        buttonBack.setFont(iconFonts);
        buttonBack.getStyleClass().add("action-button");

        buttonNext = new Text(FontMetrizeIcons.ARROW_RIGHT);
        buttonNext.setFont(iconFonts);
        buttonNext.getStyleClass().add("action-button");

        buttonNew = new Text(FontMetrizeIcons.NEW);
        buttonNew.setFont(iconFonts);
        buttonNew.getStyleClass().add("action-button");

        buttonCopy = new Text(FontMetrizeIcons.COPY);
        buttonCopy.setFont(iconFonts);
        buttonCopy.getStyleClass().add("action-button");

        buttonDelete = new Text(FontMetrizeIcons.CROSS);
        buttonDelete.setFont(iconFonts);
        buttonDelete.getStyleClass().add("action-button");

        buttonImport = new Text(FontMetrizeIcons.IMPORT);
        buttonImport.setFont(iconFonts);
        buttonImport.getStyleClass().add("action-button");

        buttonExport = new Text(FontMetrizeIcons.EXPORT);
        buttonExport.setFont(iconFonts);
        buttonExport.getStyleClass().add("action-button");

        buttonBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JavaFxApplication.getInstance().getStorage().back();
            }
        });

        buttonNext.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JavaFxApplication.getInstance().getStorage().next();
            }
        });

        buttonNew.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JavaFxApplication.getInstance().getStorage()._new();
            }
        });

        buttonCopy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JavaFxApplication.getInstance().getStorage().copy();
            }
        });

        buttonDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JavaFxApplication.getInstance().getStorage().delete();
            }
        });

        buttonImport.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buttonImport, not support yet");
            }
        });

        buttonExport.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buttonExport, not support yet");
            }
        });


        getChildren().addAll(textNumberScene, buttonBack, buttonNext, buttonNew, buttonCopy, buttonDelete, buttonImport, buttonExport);
    }

}
