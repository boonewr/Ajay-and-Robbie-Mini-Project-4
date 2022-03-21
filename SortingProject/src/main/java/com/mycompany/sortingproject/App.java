package com.mycompany.sortingproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 * @authors Robbie and Ajay
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) {
        HomePane home = new HomePane();
        Scene homeScene = new Scene(home, 740, 400);
        Stage homeStage = new Stage();
        homeStage.setScene(homeScene);
        home.ALIGN(homeScene.getHeight(), homeScene.getWidth());
        
        RadixPane radix = new RadixPane();
        Scene radixScene = new Scene(radix, 740, 400);
        Stage radixStage = new Stage();
        radixStage.setScene(radixScene);
        radix.ALIGN(radixScene.getHeight(), radixScene.getWidth());
        
        SelectionPane select = new SelectionPane();
        Scene selectionScene = new Scene(select, 740, 400);
        Stage selectionStage = new Stage();
        selectionStage.setScene(selectionScene);
        select.ALIGN(selectionScene.getHeight(), selectionScene.getWidth());
        
        InsertionPane insert = new InsertionPane();
        Scene insertionScene = new Scene(insert, 740, 400);
        Stage insertionStage = new Stage();
        insertionStage.setScene(insertionScene);
        insert.ALIGN(insertionScene.getHeight(), insertionScene.getWidth());
        
        QuickPane quick = new QuickPane();
        Scene quickScene = new Scene(quick, 740, 400);
        Stage quickStage = new Stage();
        quickStage.setScene(quickScene);
        quick.ALIGN(quickScene.getHeight(), quickScene.getWidth());
        
        MenuBarPane menu = new MenuBarPane();
        Scene menuScene = new Scene(menu, 740, 400);
        Stage menuStage = new Stage();
        menuStage.setScene(menuScene);
        menuStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
