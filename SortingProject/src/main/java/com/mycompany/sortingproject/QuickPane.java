package com.mycompany.sortingproject;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @author Robbie
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
    
    /**
     * Does everything the method of the same name in ListPane does, except also resets the step counter here in QuickPane to allow for subsequent runs.
     * @param evt 
     */
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
    
    /**
     * Sorting algorithm adopted from the in class example from the SortingAlogithms project
     * @param evt 
     */
    @Override
    protected void processSortButton(ActionEvent evt) {
        // two lists are used for illustrative purposes
        // similar to radix sort the most consistent method of splitting the sort into steps was running through the whole algorithm up to a certain point for
        // every step. Arrays a, c, and later b, are to maintain continuity with the original list of numbers across steps.
        a = new int[MasterList.size()];
        for (int i = 0; i < MasterList.size(); i++) {
            a[i] = MasterList.get(i);
        }
        c = new int[MasterList.size()];
        for (int i = 0; i < MasterList.size(); i++) {
            c[i] = MasterList.get(i);
        }

        
        switch (step) {
            // finds the pivot
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

            // sorts pivot and first/last values 
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

            // places the pivot at the end
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
                
                // Finds middle element
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
                
                while (!done) {
                    // Move from the left until we find an element greater than the pivot
                    while (a[indexFromLeft] < pivotValue) {
                        indexFromLeft++;
                    }
                    // Now move from the right until we find an element less than the pivot
                    while (a[indexFromRight] > pivotValue) {
                        indexFromRight--;
                    }
                    // If left and right points have not crossed, swap the elements they point to
                    if (indexFromLeft < indexFromRight) {
                        swapElements(a, indexFromLeft, indexFromRight);
                        indexFromLeft++;
                        indexFromRight--;
                    } else {
                        done = true;
                    }
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

    /**
     * Transplanted from SortingAlgorithms. Swaps given elements in a given array
     * @param a
     * @param i
     * @param j 
     */
    public static void swapElements(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * It might seem strange to have the whole method again, but to have a step-by-step display the original method gets cut up so severely it can no longer recursively call itself; therefore, a fresh version of the method is required to finish the sort
     * @param a input array
     * @param first 
     * @param last 
     */
    public static void quickSort(int[] a, int first, int last) {
        if (last - first > 3) {
            int mid = first + (last - first) / 2;
            if (a[first] > a[mid]) {
                swapElements(a, first, mid);
            }
            if (a[mid] > a[last - 1]) {
                swapElements(a, mid, last - 1);
            }
            if (a[first] > a[mid]) {
                swapElements(a, first, mid);
            }
            swapElements(a, mid, last - 1);
            int pivotValue = a[last - 1];

            int indexFromLeft = first + 1;
            int indexFromRight = last - 2;
            boolean done = false;

            while (!done) {
                while (a[indexFromLeft] < pivotValue) {
                    indexFromLeft++;
                }
                while (a[indexFromRight] > pivotValue) {
                    indexFromRight--;
                }
                if (indexFromLeft < indexFromRight) {
                    swapElements(a, indexFromLeft, indexFromRight);
                    indexFromLeft++;
                    indexFromRight--;
                } else {
                    done = true;
                }
            }
            swapElements(a, last - 1, indexFromLeft);
            quickSort(a, first, indexFromLeft);
            quickSort(a, indexFromLeft, last);
        } else {
            insertionSort(a, first, last);
        }
    }

    /**
     * Helper method for the sort. Because quicksort isn't effective on small lists, I transplanted this from SortingAlgorithms from class to lend a hand.
     * @param a
     * @param first
     * @param last 
     */
    public static void insertionSort(int[] a, int first, int last) {
        for (int i = first + 1; i < last; i++) {
            int next = a[i];
            int iFill = i - 1;
            while (iFill >= 0 && next < a[iFill]) {
                a[iFill + 1] = a[iFill];
                iFill--;
            }
            a[iFill + 1] = next;
        }
    }
}
