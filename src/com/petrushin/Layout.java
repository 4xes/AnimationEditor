package com.petrushin;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashSet;

/**
 * Created by Petrushin Alexey on 25.11.2014.
 */
public class Layout extends Application {

    public Button btnAdd;
    public Button btnDelete;
    public Button btnNext;
    public Button btnBack;
    public Button btnCopy;

    public Pane paneEditField;

    private Text textLayoutX;
    private Text textLayoutY;
    private Text textMouseX;
    private Text textMouseY;
    private Text textNewX;
    private Text textNewY;

    private static final double WIDTH_EF = 700;
    private static final double HEIGHT_EF = 500;
    private static final double MARGIN_EF = 10;

    private static final double RIGHT_EF = WIDTH_EF;
    private static final double BOTTOM_EF = HEIGHT_EF;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane border = new BorderPane();
        border.getStyleClass().add("back");


        HBox actionField = actionButtonBox();
        paneEditField = paneEditField(WIDTH_EF,HEIGHT_EF);
        border.setMargin(paneEditField, new Insets(MARGIN_EF));
        BoundeExample();
        border.setBottom(actionField);
        border.setTop(paneEditField);
        VBox infoVBox = getInfo();
        infoVBox.setLayoutX(WIDTH_EF-170);
        infoVBox.setLayoutY(20);
        paneEditField.getChildren().add(infoVBox);
        Scene scene = new Scene(border);
        scene.getStylesheets().add("com/petrushin/layout/css/style.css");
        primaryStage.setTitle("Animation Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox getInfo(){
        VBox vbox = new VBox();
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
    private void BoundeExample(){
        Anchor anchor1    = new Anchor(100, 100);
        Anchor anchor2      = new Anchor(300,   200);
        Anchor anchor3      = new Anchor(400,   200);

        Line line1 = new BoundLine(anchor1.getBoundX(), anchor1.getBoundY(), anchor2.getBoundX(), anchor2.getBoundY());
        Line line2 = new BoundLine(anchor3.getBoundX(), anchor3.getBoundY(), anchor2.getBoundX(), anchor2.getBoundY());
        Line line3 = new BoundLine(anchor1.getBoundX(), anchor1.getBoundY(), anchor3.getBoundX(), anchor3.getBoundY());

        paneEditField.getChildren().addAll(anchor1,anchor2,anchor3,line1, line2, line3);
    }

    public class Anchor extends Circle{
        private DoubleProperty boundX = new SimpleDoubleProperty();
        private DoubleProperty boundY = new SimpleDoubleProperty();

        public Anchor(double x, double y){
            super(x, y, 10);
            boundX.set(x);
            boundY.set(y);
            getStyleClass().add("anchor");
            boundX.bind(centerXProperty());
            boundY.bind(centerYProperty());
            enableDrag();
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                        if(mouseEvent.getClickCount() == 1){
                            System.out.println("Anchor left clicked");
                        }
                    }
                }
            });
        }

        public DoubleProperty getBoundX(){
            return boundX;
        }
        public DoubleProperty getBoundY(){
            return boundY;
        }

        private void setCoordinates(MouseEvent event, double newX, double newY){
            textLayoutX.setText("centerX: " + getCenterX());
            textLayoutY.setText("centerY: " + getCenterY());
            textMouseX.setText("mouseX: " + event.getX());
            textMouseY.setText("mouseY: " + event.getY());
            textNewX.setText("newX: " + newX);
            textNewY.setText("newX: " + newY);
        }
        private void enableDrag() {
            final Delta delta = new Delta();
            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    delta.x = getCenterX() - event.getSceneX();
                    delta.y = getCenterY() - event.getSceneY();
                    //toFront();
                    setCoordinates(event, getCenterX(), getCenterY());
                }
            });

            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    double newX = mouseEvent.getSceneX() + delta.x;
                    double newY = mouseEvent.getSceneY() + delta.y;
                    if(newX < 0){
                        newX = 0;
                    }
                    if(newY < 0){
                        newY = 0;
                    }
                    if(newX > RIGHT_EF){
                        newX = RIGHT_EF;
                    }
                    if(newY > BOTTOM_EF){
                        newY = BOTTOM_EF;
                    }
                    setCenterX(newX);
                    setCenterY(newY);

                    setCoordinates(mouseEvent, newX, newY);
                }
            });
        }
        private class Delta { double x, y; }
    }

    public class BoundLine extends Line{
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
            startXProperty().bind(startX);
            startYProperty().bind(startY);
            endXProperty().bind(endX);
            endYProperty().bind(endY);
            getStyleClass().add("bind-line");
            setMouseTransparent(true);

        }
    }

    public HBox actionButtonBox(){
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

    public Pane paneEditField(double width, double height){
        Pane pane = new Pane();
        pane.setPrefSize(width, height);

        pane.getStyleClass().add("edit-field");

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        Anchor anchor = new Anchor(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                        paneEditField.getChildren().add(anchor);

                    }
                }
            }
        });

        return pane;
    }
}
