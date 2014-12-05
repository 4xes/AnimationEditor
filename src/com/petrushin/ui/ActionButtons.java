package com.petrushin.ui;

import com.petrushin.JavaFxApplication;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Petrushin Alexey on 02.12.2014.
 */
public class ActionButtons extends HBox{

    private Text textNumberScene;

    private static Text buttonBack;

    public Text getTextNumberScene(){
        return textNumberScene;
    }

    public ActionButtons(){
        this.getStyleClass().add("action-field");

        Text buttonNext;
        Text buttonNew;
        Text buttonCopy;
        Text buttonDelete;
        Text buttonImport;
        Text buttonExport;

        textNumberScene = new Text("Scene 1/1");
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
                System.out.println("buttonBack");
                JavaFxApplication.getInstance().getStorage().back();
                JavaFxApplication.getInstance().getStorage().refreshNumFrame(textNumberScene);
            }
        });

        buttonNext.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buttonNext");
                JavaFxApplication.getInstance().getStorage().next();
                JavaFxApplication.getInstance().getStorage().refreshNumFrame(textNumberScene);
            }
        });

        buttonNew.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buttonNew");
                JavaFxApplication.getInstance().getStorage()._new();
                JavaFxApplication.getInstance().getStorage().refreshNumFrame(textNumberScene);
            }
        });

        buttonCopy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buttonCopy");
                JavaFxApplication.getInstance().getStorage().copy();
                JavaFxApplication.getInstance().getStorage().refreshNumFrame(textNumberScene);
            }
        });

        buttonDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buttonDelete");
                JavaFxApplication.getInstance().getStorage().delete();
                JavaFxApplication.getInstance().getStorage().refreshNumFrame(textNumberScene);
            }
        });

        buttonImport.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buttonImport");
                JavaFxApplication.getInstance().getStorage().print();
                JavaFxApplication.getInstance().getStorage().refreshNumFrame(textNumberScene);
            }
        });

        buttonExport.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("buttonExport");
            }
        });


        getChildren().addAll(textNumberScene, buttonBack, buttonNext, buttonNew, buttonCopy, buttonDelete, buttonImport, buttonExport);
    }

}
