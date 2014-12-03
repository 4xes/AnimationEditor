package com.petrushin;

import com.petrushin.ui.ActionButtons;
import com.petrushin.ui.Hint;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;

        init(stage);

        stage.show();
    }


    private void init(Stage stage){
        stage.setTitle("Animation Editor");

        storage = new Storage();

        field = new Field();

        borderPane = new BorderPane();
        borderPane.setTop(field);
        borderPane.setMargin(field, new Insets(20, 10, 10, 10));
        borderPane.setBottom(new ActionButtons());

        new Hint();
        new ActionButtons();

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("resources/style.css");
        stage.setScene(scene);
    }


    public static void main(String argv[]){
        launch(argv);
    }
}
