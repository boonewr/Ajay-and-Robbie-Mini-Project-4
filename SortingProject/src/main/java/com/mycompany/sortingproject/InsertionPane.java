
package com.mycompany.sortingproject;

import java.util.ArrayList;
import javafx.event.ActionEvent;

/**
 *
 * @author Ajay
 */

public class InsertionPane extends ListPane {
    int step = 1;
    
    public InsertionPane() {
        setDescription("Insertion sort is an algorithm that " + "\ncontinues to insert the appropriate sorted value with the rest of the sorted " + "\ndata step-by-step until the data is sorted. " + "\nRuntime: 0(N^2), Steps: N-1");
        setName("Insertion Sort!");
    }
    
    @Override
    protected void processSortButton(ActionEvent evt) {   
        // the size variable represents the size of the input ArrayList of MasterList
        // the selectionArray integer array is filled with the values of MasterList with the for-loop
        int size = MasterList.size();
        int[] insertionArray = new int[size];
        //ArrayList[] buckets = new ArrayList[10]; 
        for (int i = 0; i < size; i++) {
            insertionArray[i] = MasterList.get(i);
        }
        
        for (int i = 1; i < insertionArray.length; i++) {
            // store the current value of i in the insertVal variable so it can be inserted later
            // store the previous value of i in the insertIndex variable for index purposes
            int insertVal = insertionArray[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < insertionArray[insertIndex]) {
                // while insertIndex is at least 0 AND insertVal is less than the value preceding it in insertionArray, continue the while loop for insertion
                // set the value of insertionArray after insertIndex to equal insertionArray at insertIndex-- effectively inserting it to the succeding value in the array; then increment insertIndex
                insertionArray[insertIndex + 1] = insertionArray[insertIndex];
                insertIndex--;
            }
            
            // set the value of insertionArray after insertIndex to equal insertVal, finishing the insertion
            insertionArray[insertIndex + 1] = insertVal;
            
            // Printing the steps
            System.out.println("Step " + step + ": ");
            for (int j = 0; j < insertionArray.length; ++j) {
                System.out.print(insertionArray[j] + ", ");
            }
            step++;
            System.out.println();
        }   
        step = 1;
        // Once sorting is done, the GUI is updated
        for (int k = 0; k < size; ++k) {
            MasterList.set(k, insertionArray[k]);
        }
        System.out.println("Final Sorted List: ");
        updateListDisplay();
        
        
        
    }
    
}
