package com.petrushin;

import com.petrushin.shape.Anchor;
import com.petrushin.shape.BoundLine;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Petrushin Alexey on 25.11.2014.
 */
public class App extends Application {

    private BorderPane borderPane;

    private Button btnAdd;
    private Button btnDelete;
    private Button btnNext;
    private Button btnBack;
    private Button btnCopy;
    private Button btnClear;
    private Button btnTest;

    private HBox actions;
    private VBox hint;
    private Field field;

    private Text textHint;
    private static boolean flagHint = false;


    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane = border();
        hint = hint();

        textHint = textHint();
        borderPane.getChildren().add(textHint);

        field = new Field();
        borderPane.setTop(field);
        borderPane.setMargin(field, new Insets(20, 10, 10, 10));

        actions = actions();
        borderPane.setBottom(actions);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("com/petrushin/layout/css/style.css");
        primaryStage.setTitle("Animation Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private BorderPane border(){
        BorderPane border = new BorderPane();
        border.getStyleClass().add("back");
        return border;
    }

    private Text textHint(){
        textHint = new Text(550,14,"show hint");
        textHint.getStyleClass().add("text-hint");
        textHint.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(flagHint){
                    borderPane.getChildren().remove(hint);
                    textHint.setText("show hint");
                }else{
                    borderPane.getChildren().add(hint);
                    textHint.setText("hide hint");
                }
                flagHint = !flagHint;
            }
        });
        return textHint;
    }

    private void BoundExample(){
        //Example 1
        Anchor anchor1 = new Anchor(100, 100);
        Anchor anchor2 = new Anchor(300,   200);
        Anchor anchor3 = new Anchor(400,   200);

        Line line1 = new BoundLine(anchor1, anchor2);
        Line line2 = new BoundLine(anchor3, anchor2);
        Line line3 = new BoundLine(anchor1, anchor3);

        field.getChildren().addAll(line1, line2, line3, anchor1,anchor2,anchor3);

        //Example 2
        Anchor anchor4 = new Anchor(340, 300);

        field.getChildren().addAll(anchor4);
    }

    private VBox hint(){
        VBox vbox = new VBox();
        vbox.setLayoutX(550);
        vbox.setLayoutY(30);
        vbox.setSpacing(8);

        Text textHintAddAnchor = new Text("Add point: double click");
        Text textHintAddLine = new Text("Add line: left click on point");
        Text textHintDeleteElem = new Text("Delete: right click");
        Text textHintStartBind = new Text("Stop bind: right click");
        vbox.getChildren().addAll(textHintAddAnchor, textHintAddLine, textHintDeleteElem,textHintStartBind);

        return vbox;
    }

    public HBox actions(){
        HBox hbox = new HBox();
        hbox.getStyleClass().add("action-field");

        Label actionNumberScene = new Label("Scene 1     ");
        actionNumberScene.getStyleClass().add("action-num-scene");

        btnAdd = new Button("Add");
        btnDelete = new Button("Delete");
        btnDelete.setDisable(true);
        btnNext = new Button("Next");
        btnNext.setDisable(true);
        btnBack = new Button("Back");
        btnBack.setDisable(true);
        btnCopy = new Button("Copy");
        btnClear = new Button("Clear");
        btnClear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        btnTest = new Button("Test");
        btnTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                field.test();
            }
        });

        hbox.getChildren().addAll(actionNumberScene, btnAdd, btnDelete, btnNext, btnBack, btnCopy, btnClear,btnTest);
        return hbox;
    }



    public static void main(String argv[]){
        launch(argv);
    }
}
