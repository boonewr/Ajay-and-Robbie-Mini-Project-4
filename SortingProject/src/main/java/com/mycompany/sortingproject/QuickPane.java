/**
 * Just testing ATM
 */
package com.mycompany.sortingproject;

import java.util.ArrayList;
import javafx.event.ActionEvent;

/**
 *
 * @author Ajay
 */
public class QuickPane extends ListPane {
    
    public QuickPane() {
        setDescription("Insertion sort is an algorithm that " + "\ncontinues to insert the appropriate sorted value with the rest of the sorted " + "\ndata step-by-step until the data is sorted");
        setName("Insertion Sort!");
        sizeField.setVisible(false);
    }
}
