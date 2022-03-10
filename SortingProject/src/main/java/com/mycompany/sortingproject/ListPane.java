/*
 */
package com.mycompany.sortingproject;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    ArrayList<Integer> MasterList = new ArrayList<Integer>();
    ArrayList<TextField> fieldList = new ArrayList<TextField>();

    TextField sizeField = new TextField("Enter size");
    Button randomButton = new Button("Randomize");

    public ListPane() {
        sizeField.setOnAction(this::processSize);
        randomButton.setOnAction(this::processRandom);
        getChildren().addAll(sizeField, randomButton);
        randomButton.relocate(300, 0);
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
    }

    /*
    // when sort is pressed, go through fieldList and pull all values into masterlist
    EventHandler<ActionEvent> typeEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
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
        }
    };
     */
    public void updateListDisplay() {
        for (int i = 0; i < MasterList.size(); i++) {
            fieldList.get(i).setText("" + MasterList.get(i));
        }
        System.out.println(MasterList.toString());
    }

}
