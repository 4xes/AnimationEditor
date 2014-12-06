package com.petrushin;

import com.petrushin.ui.ActionButtons;
import com.petrushin.ui.Hint;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Petrushin Alexey on 25.11.2014.
 */
public class JavaFxApplication extends Application {

    private static JavaFxApplication instance;

    private BorderPane borderPane;

    private Field field;

    private Storage storage;

    private StringProperty numFrame;

    public static JavaFxApplication getInstance(){
        if(instance == null){
            throw new RuntimeException("Before getting an instance of the JavaFX application, execute the main-method in this class first. It launches the FX application.");
        }
        return instance;
    }

    public Field getField(){
        return field;
    }

    public BorderPane getBorderPane(){
        return borderPane;
    }

    public Storage getStorage(){
        return storage;
    }

    public StringProperty numFrameProperty() {
        return numFrame;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;

        init(stage);

        stage.show();
    }


    private void init(Stage stage){
        stage.setTitle("Animation Editor");

        numFrame = new SimpleStringProperty("Scene 1/1");
        storage = new Storage();

        field = new Field();

        borderPane = new BorderPane();
        borderPane.setTop(field);
        borderPane.setMargin(field, new Insets(20, 10, 10, 10));
        borderPane.setBottom(new ActionButtons());

        new Hint();
        new ActionButtons();

        Scene scene = new Scene(borderPane);

        scene.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getText().toLowerCase().equals("a")){
                    storage.back();
                }else if (event.getText().toLowerCase().equals("d")) {
                    storage.next();
                }
                System.out.println("Key Released: " + event.getText() + " " + event.getEventType());
            }
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getText().toLowerCase().equals("q")){
                    storage.back();
                }else if (event.getText().toLowerCase().equals("e")) {
                    storage.next();
                }
                System.out.println("Key Released: " + event.getText() + " " + event.getEventType());
            }
        });

        scene.getStylesheets().add("resources/style.css");
        stage.setScene(scene);
    }


    public static void main(String argv[]){
        launch(argv);
    }
}
