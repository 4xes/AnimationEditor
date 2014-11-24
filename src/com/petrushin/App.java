package com.petrushin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Created by apetr_000 on 24.11.2014.
 */
public class App extends Application {


    private static final int WIDTH_SCENE = 700;
    private static final int HEIGHT_SCENE = 550;

    private Text textX;
    private Text textY;
    private Text textLayoutX;
    private Text textLayoutY;
    public class Delta{
        public double x;
        public double y;
    }
    final Delta delta = new Delta();

    private int currentScene = 1;
    private int sizeScene = 1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Animation Editor");

        Group root = new Group();

        textX = new Text("x: ");
        textX.setX(WIDTH_SCENE - 150);
        textX.setY(HEIGHT_SCENE - 70);
        root.getChildren().add(textX);

        textY = new Text("y: ");
        textY.setX(WIDTH_SCENE - 150);
        textY.setY(HEIGHT_SCENE - 50);
        root.getChildren().add(textY);

        textLayoutX = new Text("Layout delta x: ");
        textLayoutX.setX(WIDTH_SCENE - 150);
        textLayoutX.setY(HEIGHT_SCENE - 30);
        root.getChildren().add(textLayoutX);

        textLayoutY = new Text("Layout delta y: ");
        textLayoutY.setX(WIDTH_SCENE - 150);
        textLayoutY.setY(HEIGHT_SCENE - 10);
        root.getChildren().add(textLayoutY);



        for(int i = 0; i < 6; i++){
            final Circle circle = new Circle();
            circle.setCenterY(50 + i*20);
            circle.setCenterX(50 + i * 20);
            circle.setRadius(10);

            circle.setFill(Color.AQUA);
            circle.setStroke(Color.BROWN);

            circle.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    delta.x = circle.getCenterX() - event.getX();
                    delta.y = circle.getCenterY() - event.getY();
                    setCoordinates(event);
                }
            });
            circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setCoordinates(event);
                }
            });
            circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setCoordinates(event);
                    circle.setCenterX(event.getSceneX() + delta.x);
                    circle.setCenterY(event.getSceneY() + delta.y);
                }
            });

            root.getChildren().add(circle);
        }

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BOTTOM_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        final Text actionTarget = new Text("Scene 1");
        grid.add(actionTarget, 0,0);

        Button btnAdd = new Button();
        btnAdd.setText("Add");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentScene++;
                sizeScene++;
                actionTarget.setText("Scene " + currentScene + " of " + sizeScene);
            }
        });
        grid.add(btnAdd,1,0);

        Button btnDelete = new Button();
        btnDelete.setText("Delete");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(sizeScene > 1){
                    currentScene--;
                    sizeScene--;
                }
                actionTarget.setText("Scene " + currentScene + " of " + sizeScene);
            }
        });
        grid.add(btnDelete,2,0);

        Button btnNext = new Button();
        btnNext.setText("Next");
        btnNext.setDisable(true);
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actionTarget.setText("Next");
            }
        });
        grid.add(btnNext,3,0);

        Button btnBack = new Button();
        btnBack.setText("Back");
        btnBack.setDisable(true);
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actionTarget.setText("Back");
            }
        });
        grid.add(btnBack,4,0);
        grid.setLayoutX(20);
        grid.setLayoutY(HEIGHT_SCENE -80);

        Line line = new Line();
        line.setStartX(100);
        line.setStartY(100);
        line.setEndX(200);
        line.setEndY(200);

        root.getChildren().add(line);
        root.getChildren().add(grid);
        Scene scene = new Scene(root,WIDTH_SCENE, HEIGHT_SCENE);
        scene.getStylesheets().add(App.class.getResource("Animation.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void setCoordinates(MouseEvent event){
        textX.setText("x: " + String.valueOf(event.getSceneX()));
        textY.setText("y: " + String.valueOf(event.getSceneY()));
        textLayoutX.setText("Layout delta x: " + String.valueOf(delta.x));
        textLayoutY.setText("Layout delta y: " + String.valueOf(delta.y));
    }

    public static void main(String args[]){
        launch(args);
    }

}
