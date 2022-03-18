/**
 * Methods that MUST BE IMPLEMENTED: setName, setDescription,
 * attach processSort method to button in panel itself. sortButton is public
 * Remember: step through sort method 1 at a time. Create a nextButton event handler 
 * that sets sortButton visibility to false and somehow steps through the sort method.
 */
package com.mycompany.sortingproject;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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
     * enters values. Note: call the update method during and post-sort to
     * update the displayed list
     */
    public ArrayList<Integer> MasterList = new ArrayList<Integer>();
    public ArrayList<TextField> fieldList = new ArrayList<TextField>();

    private TextField sizeField = new TextField("Enter preffered list size");
    private Button randomButton = new Button("Randomize");
    private Button resetButton = new Button("Reset");
    public Button sortButton = new Button("Sort!");
    public Button nextButton = new Button("Next");
    private Text sortName = new Text("name of sort type goes here");
    private Text sortDescription = new Text("sortDescription goes here");

    public ListPane() {
        getChildren().addAll(sizeField, randomButton, sortDescription, sortName, resetButton, sortButton, nextButton);

        sizeField.setOnAction(this::processSizeField);
        randomButton.setOnAction(this::processRandomButton);
        resetButton.setOnAction(this::processResetButton);

        randomButton.setVisible(false);
        resetButton.setVisible(false);
        sortButton.setVisible(false);
        nextButton.setVisible(false);

        sortName.setFont(Font.font("Arial", 20));
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

    // add an interface with just this? Something to think about 
    //abstract public void processSort(ActionEvent evt);
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
        randomButton.relocate(sizeField.getLayoutX() + sizeField.getWidth() * 1.5, sizeField.getLayoutY());
        sortName.relocate(10, 10);
        sortDescription.relocate(10, 50);
        resetButton.relocate(randomButton.getLayoutX(), randomButton.getLayoutY() + randomButton.getHeight());
        sortButton.relocate(resetButton.getLayoutX(), resetButton.getLayoutY() + resetButton.getHeight());
        nextButton.relocate(sortButton.getLayoutX(), sortButton.getLayoutY());

        // Add whatever
        // I'm not dead set on any of this layout, change it to what you want or lmk any ideas
    }

    // Idea: use animations on all the field nodes to make them actually appear to move around. resetButton method instantly puts them back at all their positions 
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
