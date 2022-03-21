package com.mycompany.sortingproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 * @author Robbie
 */
public class App extends Application {

    
    /**
     * The last step can be making the tab interface thingy with all the options in it, as well as the 'home page' with the basic description
     * for now just un-comment whatever block you need to test the sort method panel you're working on
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        HomePane home = new HomePane();
        Scene homeScene = new Scene(home, 740, 400);
        Stage homeStage = new Stage();
        homeStage.setScene(homeScene);
        //homeStage.show();
        home.ALIGN(homeScene.getHeight(), homeScene.getWidth());
        
        RadixPane radix = new RadixPane();
        Scene radixScene = new Scene(radix, 740, 400);
        Stage radixStage = new Stage();
        radixStage.setScene(radixScene);
        //radixStage.show();
        radix.ALIGN(radixScene.getHeight(), radixScene.getWidth());
        
        SelectionPane select = new SelectionPane();
        Scene selectionScene = new Scene(select, 740, 400);
        Stage selectionStage = new Stage();
        selectionStage.setScene(selectionScene);
        //selectionStage.show();
        select.ALIGN(selectionScene.getHeight(), selectionScene.getWidth());
        
        InsertionPane insert = new InsertionPane();
        Scene insertionScene = new Scene(insert, 740, 400);
        Stage insertionStage = new Stage();
        insertionStage.setScene(insertionScene);
        //insertionStage.show();
        insert.ALIGN(insertionScene.getHeight(), insertionScene.getWidth());
        
        QuickPane quick = new QuickPane();
        Scene quickScene = new Scene(quick, 740, 400);
        Stage quickStage = new Stage();
        quickStage.setScene(quickScene);
        //insertionStage.show();
        quick.ALIGN(quickScene.getHeight(), quickScene.getWidth());
        
        MenuBarPane menu = new MenuBarPane();
        Scene menuScene = new Scene(menu, 740, 400);
        Stage menuStage = new Stage();
        menuStage.setScene(menuScene);
        menuStage.show();
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
