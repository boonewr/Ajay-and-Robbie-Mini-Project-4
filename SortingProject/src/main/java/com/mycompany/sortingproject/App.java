package com.mycompany.sortingproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        QuickPane test = new QuickPane();
        Scene scene = new Scene(test, 640, 380);
        stage.setScene(scene);
        stage.show();
        //scene.getRoot().setStyle(STYLESHEET_CASPIAN);
    }

    public static void main(String[] args) {
        launch();
    }

}