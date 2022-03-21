/**
 * Just testing ATM
 */
package com.mycompany.sortingproject;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @author wrboo
 */
public class QuickPane extends ListPane {

    Text stepDescription = new Text("step description goes here");
    Text pivotText = new Text("Pivot: ");

    public QuickPane() {
        setName("QuickSort");
        setDescription("By using a pivot quicksort is much faster than the other traditional sorts, insertion and selection.");
        getChildren().addAll(stepDescription, pivotText);

        stepDescription.setVisible(false);
        stepDescription.setWrappingWidth(250);
        stepDescription.relocate(200, 450);
    }
    
    @Override
    protected void processResetButton(ActionEvent evt) {
        for (int i = 0; i < MasterList.size(); i++) {
            MasterList.set(i, 0);
        }
        updateListDisplay();
        step = 1;
        sortButton.setText("Sort!");
    }

    static int step = 1;
    static int[] a, c;
    static int first, last, mid, pivotValue;
    
    @Override
    protected void processSortButton(ActionEvent evt) {
        a = new int[MasterList.size()];
        for (int i = 0; i < MasterList.size(); i++) {
            a[i] = MasterList.get(i);
        }
        c = new int[MasterList.size()];
        for (int i = 0; i < MasterList.size(); i++) {
            c[i] = MasterList.get(i);
        }

        switch (step) {
            case 1:
                first = 0;
                last = a.length;
                if (last - first > 3) {
                    // find the middle element
                    mid = first + (last - first) / 2;
                }

                stepDescription.setText("Step 1:\nA 'pivot' is found at the middle of the targeted array");
                pivotText.setText("Pivot: " + mid);
                stepDescription.setVisible(true);
                pivotText.setVisible(true);
                step++;
                sortButton.setText("Next");
                break;

            case 2:
                if (a[first] > a[mid]) {
                    swapElements(a, first, mid);
                }
                if (a[mid] > a[last - 1]) {
                    swapElements(a, mid, last - 1);
                }
                if (a[first] > a[mid]) {
                    swapElements(a, first, mid);
                }
                for (int i = 0; i < a.length; i++) {
                    MasterList.set(i, a[i]);
                }
                updateListDisplay();

                stepDescription.setText("Step 2:\nThe pivot, first, and last values are sorted, and their positions are updated");
                step++;
                break;

            case 3:
                swapElements(a, mid, last - 1);
                pivotValue = a[last - 1];
                for (int i = 0; i < a.length; i++) {
                    MasterList.set(i, a[i]);
                }
                updateListDisplay();
                stepDescription.setText("Step 3:\nThe pivot value is placed at the end");
                step++;
                break;
            case 4:
                for (int i = 0; i < a.length; i++){
                    a[i] = c[i];
                }
                
                // What's the middle element?
                int mid = a.length / 2;
                // Sort the first middle and last elements
                if (a[first] > a[mid]) {
                    swapElements(a, first, mid);
                }
                if (a[mid] > a[last - 1]) {
                    swapElements(a, mid, last - 1);
                }
                if (a[first] > a[mid]) {
                    swapElements(a, first, mid);
                }
                // Move the pivot to the end
                swapElements(a, mid, last - 1);
                pivotValue = a[last - 1];

                // Start from both sides and work inwards
                int indexFromLeft = first + 1;
                int indexFromRight = last - 1;
                boolean done = false; // this becomes true once all the elements are positioned relative to the pivot
                int temp = 0;
                
                while (temp < 100) {
                    // Move from the left until we find an element greater than the pivot
                    while (a[indexFromLeft] < pivotValue) {
                        indexFromLeft++;
                    }
                    // Now move from the right until we find an element less than the pivot
                    while (a[indexFromRight] > pivotValue) {
                        indexFromRight--;
                    }
                    // Provided that the left and right pointers have not crossed, swap those elements
                    if (indexFromLeft < indexFromRight) {
                        swapElements(a, indexFromLeft, indexFromRight);
                        indexFromLeft++;
                        indexFromRight--;
                    } else {
                        done = true;
                    }
                    temp++;
                }
                swapElements(a, last - 1, indexFromLeft);

                for (int i = 0; i < a.length; i++) {
                    MasterList.set(i, a[i]);
                }
                updateListDisplay();
                stepDescription.setText("Step 4:\nAll elements are positioned relative to the pivot, with lower elements being on the left and higher ones on the right.");
                step++;
                break;
            case 5:
                int[] b = new int[MasterList.size()];
                for (int i = 0; i < b.length; i++) {
                    b[i] = MasterList.get(i);
                }

                stepDescription.setText("Step 5:\nThe process is repeated recursively on each side of the pivot, creating smaller and smaller cases. Once each case is only 3 elements long, insertion sort is used.");
                quickSort(b, 0, b.length);
                for (int i = 0; i < a.length; i++) {
                    MasterList.set(i, b[i]);
                }
                updateListDisplay();
        }

    }

    public static void swapElements(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void quickSort(int[] a, int first, int last) {
        // Only do quickSort for more than three array elements
        if (last - first > 3) {
            // What's the middle element?
            int mid = first + (last - first) / 2;
            // Sort the first middle and last elements
            if (a[first] > a[mid]) {
                swapElements(a, first, mid);
            }
            if (a[mid] > a[last - 1]) {
                swapElements(a, mid, last - 1);
            }
            if (a[first] > a[mid]) {
                swapElements(a, first, mid);
            }
            // Move the pivot to the end
            swapElements(a, mid, last - 1);
            int pivotValue = a[last - 1];

            // Start from both sides and work inwards
            int indexFromLeft = first + 1;
            int indexFromRight = last - 2;
            boolean done = false; // this becomes true once all the elements are positioned relative to the pivot

            while (!done) {
                // Move from the left until we find an element greater than the pivot
                while (a[indexFromLeft] < pivotValue) {
                    indexFromLeft++;
                }
                // Now move from the right until we find an element less than the pivot
                while (a[indexFromRight] > pivotValue) {
                    indexFromRight--;
                }
                // Provided that the left and right pointers have not crossed, swap those elements
                if (indexFromLeft < indexFromRight) {
                    swapElements(a, indexFromLeft, indexFromRight);
                    indexFromLeft++;
                    indexFromRight--;
                } else {
                    done = true;
                }
            }
            // Once the pointers cross, move the pivot into the correct locaiton
            swapElements(a, last - 1, indexFromLeft);
            // Let's use quickSort to sort each subarray on either side of the pivot
            quickSort(a, first, indexFromLeft); // Should be left - 1 and left + 1, respectively. Something is up with the indexing
            quickSort(a, indexFromLeft, last);
        } else {
            insertionSort(a, first, last);
        }
    }

    public static void insertionSort(int[] a, int first, int last) {
        // Start at index first + 1
        for (int i = first + 1; i < last; i++) {
            // Store the value that we'll insert
            int next = a[i];
            // Start searching backwards for where we're going to insert next
            int iFill = i - 1;
            while (iFill >= 0 && next < a[iFill]) {
                // As long as this is true, move the iFill element up one to make space 
                a[iFill + 1] = a[iFill];
                iFill--;
            }
            // when we're done, we know where our element belongs
            a[iFill + 1] = next;
        }
    }
}
