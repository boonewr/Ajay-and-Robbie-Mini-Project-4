/**
 * Methods that MUST BE IMPLEMENTED: setName, setDescription,
 * Override processSortButton in the sort pane, the event handler is already attached to the button
 * Remember: step through sort method 1 at a time. The idea I'm going with is have a switch statement 
 * in the overriding processSortButton method that starts with an int = 1, does step 1, step++, now runs 
 * branch 2 when the button is pressed, step++, now runs branch 3, etc etc.
 * Note that however you implement it make sure to use the setText() method on sortButton to make it say
 * "Next" in the first branch
 */
package com.mycompany.sortingproject;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Robbie and Ajay!
 */
public class ListPane extends Pane {

    Random rand = new Random();
    int size = 0;

    /**
     * The displayed list. Updated with the random button or when the user
     * enters values. Note: call the update method updateListDisplay() during
     * and post-sort to update the displayed list
     */
    public ArrayList<Integer> MasterList = new ArrayList<Integer>();
    public ArrayList<TextField> fieldList = new ArrayList<TextField>();

    protected TextField sizeField = new TextField("Enter preferred list size");
    private Button randomButton = new Button("Randomize");
    private Button resetButton = new Button("Reset");
    protected Button sortButton = new Button("Sort! Press Enter on the last input box");
    private Text sortName = new Text("name of sort type goes here");
    private Text sortDescription = new Text("sortDescription goes here");

    public ListPane() {
        getChildren().addAll(sizeField, randomButton, sortDescription, sortName, resetButton, sortButton);

        sizeField.setOnAction(this::processSizeField);
        randomButton.setOnAction(this::processRandomButton);
        resetButton.setOnAction(this::processResetButton);
        sortButton.setOnAction(this::processSortButton);
        

        randomButton.setVisible(false);
        resetButton.setVisible(false);
        sortButton.setVisible(false);

        sortName.setFont(Font.font("Arial", 20));
    }

    protected void processSortButton(ActionEvent evt) {
        System.out.println("Override it");
    }
    

    // Event handlers
    /**
     * Goes through the master list and sets all values to zero. Then calls
     * updateListDisplay()
     *
     * @param evt
     */
    private void processResetButton(ActionEvent evt) {
        for (int i = 0; i < MasterList.size(); i++) {
            MasterList.set(i, 0);
        }
        updateListDisplay();
    }

    /**
     * Fills the master list with random values
     *
     * @param evt
     */
    private void processRandomButton(ActionEvent evt) {
        for (int i = 0; i < MasterList.size(); i++) {
            MasterList.set(i, rand.nextInt(10) + 1);
        }
        updateListDisplay();
    }

    /**
     * The first thing the user will interact with. Parses an integer value from
     * String input and displays an empty list of that length. Might be
     * convoluted but all the text fields that can then accept individual
     * indices of the list are all in an ArrayList together for easy access
     *
     * @param evt
     */
    private void processSizeField(ActionEvent evt) {
        size = (Integer.parseInt(sizeField.getText()));
        for (int i = 0; i < size; i++) {
            TextField field = new TextField("");
            field.setOnAction(this::processField);
            fieldList.add(field);
            MasterList.add(0);
        }

        for (int i = 0; i < fieldList.size(); i++) {
            fieldList.get(i).relocate(100, (i * 25 + 100));
            getChildren().add(fieldList.get(i));
        }

        randomButton.setVisible(true);
        resetButton.setVisible(true);
        sortButton.setVisible(true);
    }


    private void processField(ActionEvent evt) {
        for (int i = 0; i < MasterList.size(); i++) {
            MasterList.set(i, Integer.parseInt(fieldList.get(i).getText()));
        }
    }

    // housekeeping 
    /**
     * Accepts height and width automatically from App. Uses these to relocate
     * relevant elements relative to each other. Good for compatibility with
     * hypothetical different window sizes
     *
     * @param h height of scene
     * @param w width of scene
     */
    public void ALIGN(double h, double w) {
        sizeField.relocate((w - sizeField.getWidth()) / 2, (h - sizeField.getHeight()) / 10);
        randomButton.relocate(sizeField.getLayoutX() + sizeField.getWidth() * 1.5, sizeField.getLayoutY() + 50);
        sortName.relocate(10, 10);
        sortDescription.relocate(10, 50);
        resetButton.relocate(randomButton.getLayoutX(), (randomButton.getLayoutY() + 50) + randomButton.getHeight());
        sortButton.relocate(resetButton.getLayoutX(), (resetButton.getLayoutY() + 50) + resetButton.getHeight());

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

    /**
     * Sets the displayed name on the sort on the pane
     *
     * @param name
     */
    public void setName(String name) {
        sortName.setText(name);
    }

    /**
     * Sets the displayed description of the sort on the pane
     *
     * @param desc
     */
    public void setDescription(String desc) {
        sortDescription.setText(desc);
    }

}
