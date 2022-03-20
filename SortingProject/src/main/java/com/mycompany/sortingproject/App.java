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

    
    /**
     * The last step can be making the tab interface thingy with all the options in it, as well as the 'home page' with the basic description
     * for now just uncomment whatever block you need to test the sort method panel you're working on
     * @param stage 
     */
    @Override
    public void start(Stage stage) {
        RadixPane test = new RadixPane();
        // SelectionPane test = new SelectionPane();
        // InsertionPane test = new InsertionPane();
        Scene scene = new Scene(test, 640, 380);
        stage.setScene(scene);
        stage.show();
        test.ALIGN(scene.getHeight(), scene.getWidth());
        
        /*
        QuickPane test = new QuickPane();
        Scene scene = new Scene(test, 640, 380);
        stage.setScene(scene);
        stage.show();
        test.ALIGN(scene.getHeight(), scene.getWidth());
        */
        
        /*
        [...]Pane test = new [...]Pane();
        Scene scene = new Scene(test, 640, 380);
        stage.setScene(scene);
        stage.show();
        test.ALIGN(scene.getHeight(), scene.getWidth());
        */
    }

    public static void main(String[] args) {
        launch();
    }

}
