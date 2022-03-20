
package com.mycompany.sortingproject;

import java.util.ArrayList;
import javafx.event.ActionEvent;

public class SelectionPane extends ListPane {
    int step = 1;
    
    public SelectionPane() {
        setDescription("Selection sort is fancy blah blah blah O(n)");
        setName("Selection Sort!");
    }
    

    
    @Override
    protected void processSortButton(ActionEvent evt) {   
        // the size variable represents the size of the input ArrayList of MasterList
        // the selectionArray integer array is filled with the values of MasterList with the for-loop
        int size = MasterList.size();
        int[] selectionArray = new int[size];
        //ArrayList[] buckets = new ArrayList[10]; 
        for (int i = 0; i < size; i++) {
            selectionArray[i] = MasterList.get(i);
        }
        
        // Sorting via selection sort
        int minValIndex = 0;
        for (int i = 0; i < selectionArray.length; ++i) {
            minValIndex = i;
            // minValIndex is set to be equal to selectionArray at index i
            // if j is less than selectionArray at minValIndex, the value of minValIndex is updated with the current value of j
            for (int j = i + 1; j < selectionArray.length; ++j) {
                if (selectionArray[j] < selectionArray[minValIndex]) {
                    minValIndex = j;
                }
            }
            int temp = selectionArray[minValIndex];
            selectionArray[minValIndex] = selectionArray[i];
            selectionArray[i] = temp;
            
            // the nested for-loop merely updates minValIndex to get the index of the smallest value in the array, but it doesn't
            // swap values just yet -- for this, we return to the outside for-loop with index value i.
            // Here, we use a temp variable to swap selectionArray[i] with selectionArray[minValIndex]. Since j in the inner for-loop is based
            // on the value of i, it must run again once i is incremented in the outer loop, which finds the index of the next smallest value.
            System.out.println("Step " + step + ": ");
            for (int j = 0; j < selectionArray.length; ++j) {
                System.out.print(selectionArray[j] + ", ");
            }
            step++;
            if (step > MasterList.size()) {
                step = 1;
            }
            System.out.println();

            
        }
        // Once sorting is done, the GUI is updated
        for (int k = 0; k < size; ++k) {
            MasterList.set(k, selectionArray[k]);
        }
        System.out.println("Final Sorted List: ");
        updateListDisplay();
        
        
        
    }
    
    
}
