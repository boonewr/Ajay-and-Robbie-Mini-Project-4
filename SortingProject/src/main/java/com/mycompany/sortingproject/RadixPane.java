/*
 */
package com.mycompany.sortingproject;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author wrboo
 */
public class RadixPane extends ListPane {
    Button sortButton = new Button("Sort");
    
    public RadixPane() {
        getChildren().add(sortButton);
        sortButton.relocate(400, 400);
        sortButton.setOnAction(this::processSort);
        
        sortButton.setVisible(true);
    }
    
    public void processSort(ActionEvent evt) {
        int max = MasterList.get(0);
        
        for (int i = 0; i < MasterList.size(); i++) {
            if (MasterList.get(i) > max) {
                max = MasterList.get(i);
            }
        }
        
        ArrayList<Integer> counting = new ArrayList<Integer>();
        for (int i = 0; i <= max; i++) {
            counting.add(0);
        }
        
        for (int i = 0; i < MasterList.size(); i++) {
            counting.set(MasterList.get(i), counting.get(i) + 1);
        }
        
        for (int i = 1; i < MasterList.size(); i++) {
            counting.set(i, counting.get(i) + counting.get(i - 1));
        }
        
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int i = 0; i < MasterList.size(); i++) {
            output.add(0);
        }
        
        for (int i = MasterList.size() - 1; i >= 0; i--) {
            output.set(counting.get(MasterList.get(i)) - 1, MasterList.get(i));
            counting.set(MasterList.get(i), counting.get(MasterList.get(i)) - 1);
        }
        
        for (int i = 0; i < MasterList.size(); i++) {
            MasterList.set(i, output.get(i));
        }
        updateListDisplay();
    }
    
}
