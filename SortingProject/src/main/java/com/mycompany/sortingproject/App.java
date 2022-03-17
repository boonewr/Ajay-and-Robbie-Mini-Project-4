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
        RadixPane test = new RadixPane();
        Scene scene = new Scene(test, 640, 380);
        stage.setScene(scene);
        stage.show();
        test.ALIGN(scene.getHeight(), scene.getWidth());
        
        /*
        RadixPane test2 = new RadixPane();
        Scene scene2 = new Scene(test2, 640, 380);
        stage.setScene(scene2);
        stage.show();
        test.ALIGN(scene2.getHeight(), scene2.getWidth());
*/
    }

    public static void main(String[] args) {
        launch();
    }

}