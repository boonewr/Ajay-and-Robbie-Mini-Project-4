/**
 * Just testing ATM
 */
package com.mycompany.sortingproject;

import javafx.scene.control.Button;

/**
 *
 * @author wrboo
 */
public class QuickPane extends ListPane {
    Button test = new Button("TEST");
    
    public QuickPane() {
        getChildren().add(test);
        test.relocate(400, 0);
        //randomButton.setText("randomize");
        //test.relocate(this.getWidth(), this.getHeight());
    }
}
