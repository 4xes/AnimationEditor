package com.petrushin;

import com.petrushin.figures.Anchor;
import com.petrushin.figures.BoundLine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Petrushin Alexey on 25.11.2014.
 */
public class App extends Application {

    public Button btnAdd;
    public Button btnDelete;
    public Button btnNext;
    public Button btnBack;
    public Button btnCopy;

    public Pane field;

    private Text textLayoutX;
    private Text textLayoutY;
    private Text textMouseX;
    private Text textMouseY;
    private Text textNewX;
    private Text textNewY;

    public static boolean binding = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane border = new BorderPane();
        border.getStyleClass().add("back");


        HBox footerBox = footerBox();
        VBox rightBox = rightBox();


        field = new Field();
        field.getChildren().add(rightBox);
        border.setTop(field);
        border.setMargin(field, new Insets(Field.MARGIN_EF));

        border.setBottom(footerBox);

        BoundExample();
        Scene scene = new Scene(border);
        scene.getStylesheets().add("com/petrushin/layout/css/style.css");
        primaryStage.setTitle("Animation Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void BoundExample(){
        Anchor anchor1    = new Anchor(100, 100);
        Anchor anchor2      = new Anchor(300,   200);
        Anchor anchor3      = new Anchor(400,   200);

        Line line1 = new BoundLine(anchor1, anchor2);
        Line line2 = new BoundLine(anchor3, anchor2);
        Line line3 = new BoundLine(anchor1, anchor3);

        field.getChildren().addAll(line1, line2, line3,anchor1,anchor2,anchor3);
    }

    private VBox rightBox(){
        VBox vbox = new VBox();
        vbox.setLayoutX(Field.WIDTH_EF - 170);
        vbox.setLayoutY(20);

        vbox.setPadding(new Insets(10.0));
        vbox.setSpacing(8);

        textLayoutX = new Text("layoutX: ");
        textLayoutX.setFill(Color.WHITE);
        textLayoutY = new Text("LayoutY: ");
        textLayoutY.setFill(Color.WHITE);

        textMouseX = new Text("mouseX: ");
        textMouseX.setFill(Color.WHITE);
        textMouseY = new Text("mouseY: ");
        textMouseY.setFill(Color.WHITE);

        textNewX = new Text("newX: ");
        textNewX.setFill(Color.WHITE);
        textNewY = new Text("newY: ");
        textNewY.setFill(Color.WHITE);

        Text textHintAddAnchor = new Text("Add point: double click");
        textHintAddAnchor.setFill(Color.CYAN);
        Text textHintAddLine = new Text("Add line: right click on point");
        textHintAddLine.setFill(Color.CYAN);
        Text textHintDeleteElem = new Text("Delete elem.: 2x right click");
        textHintDeleteElem.setFill(Color.CYAN);
        vbox.getChildren().addAll(textLayoutX, textLayoutY, textMouseX, textMouseY, textNewX, textNewY, textHintAddAnchor, textHintAddLine, textHintDeleteElem);

        return vbox;
    }

    public HBox footerBox(){
        HBox hbox = new HBox();
        hbox.getStyleClass().add("action-field");

        Label actionNumberScene = new Label("Scene 1");
        actionNumberScene.getStyleClass().add("action-num-scene");

        btnAdd = new Button("Add");
        btnDelete = new Button("Delete");
        btnDelete.setDisable(true);
        btnNext = new Button("Next");
        btnNext.setDisable(true);
        btnBack = new Button("Back");
        btnBack.setDisable(true);
        btnCopy = new Button("Copy");
        hbox.getChildren().addAll(actionNumberScene, btnAdd, btnDelete, btnNext, btnBack, btnCopy);
        return hbox;
    }
}
