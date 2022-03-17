/*
 */
package com.mycompany.sortingproject;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author wrboo
 */
public class ListPane extends Pane {
    Random rand = new Random();
    int size = 0;

    /**
     * The displayed list. Updated with the random button or when the user enters values. Note: call the update method during and post-sort to update the displayed list
     */
    ArrayList<Integer> MasterList = new ArrayList<Integer>();
    ArrayList<TextField> fieldList = new ArrayList<TextField>();
    
    TextField sizeField = new TextField("Enter size");
    Button randomButton = new Button("Randomize");

    public ListPane() {
        sizeField.setOnAction(this::processSize);
        randomButton.setOnAction(this::processRandom);
        getChildren().addAll(sizeField, randomButton);
        randomButton.relocate(300, 0);
        randomButton.setVisible(false);
        //setTop(sizeField);
        //setTop(randomButton);
    }

    public void processRandom(ActionEvent evt) {
        for (int i = 0; i < MasterList.size(); i++) {
            MasterList.set(i, rand.nextInt(10) + 1);
        }
        updateListDisplay();
    }

    public void processSize(ActionEvent evt) {
        size = (Integer.parseInt(sizeField.getText()));
        for (int i = 0; i < size; i++) {
            TextField field = new TextField("" + i);
            fieldList.add(field);
            MasterList.add(0);
        }

        for (int i = 0; i < fieldList.size(); i++) {
            fieldList.get(i).relocate(100, (i * 25 + 100));
            getChildren().add(fieldList.get(i));
        }

        randomButton.setVisible(true);
    }

    /**
     * Accepts height and width automatically from App. Uses these to relocate relevant elements relative to each other. Good for compatibility with different window sizes
     * @param h height of scene
     * @param w width of scene
     */
    public void ALIGN(double h, double w) {
        sizeField.relocate((w - sizeField.getWidth()) / 2, (h - sizeField.getHeight()) / 10);
        randomButton.relocate(sizeField.getLayoutX() + sizeField.getWidth() * 1.5, sizeField.getLayoutY());
        // Add whatever
    }

    /**
     * Updates the displayed boxes with whatever is in MasterList
     */
    public void updateListDisplay() {
        for (int i = 0; i < MasterList.size(); i++) {
            fieldList.get(i).setText("" + MasterList.get(i));
        }
        System.out.println(MasterList.toString());
    }

}
