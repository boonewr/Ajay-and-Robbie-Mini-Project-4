/* 
 */
package com.mycompany.sortingproject;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author Robbie
 */
public class RadixPane extends ListPane {

    int step = 1;
    Text stepDescription = new Text("step description goes here");
    Text maxDigitsText = new Text("Max digits = ");
    Text powerText = new Text("Digit: ");

    public RadixPane() {
        setDescription("Radix sort is a unique sorting algoritm because it does not use any logical comparisons. This allows for blazingly fast sorts at high efficiency. The time complexity is only O(nd) where d is the maximum number of digits, as an inner loop with complexity O(n) has to run for every digit.\nHowever, radix sort has the drawback of only working for integers.");
        setName("Radix Sort!");

        stepDescription.setVisible(false);
        stepDescription.setWrappingWidth(250);
        stepDescription.relocate(200, 450);

        maxDigitsText.relocate(300, 400);
        maxDigitsText.setVisible(false);
        
        powerText.relocate(300, 420);
        powerText.setVisible(false);

        getChildren().addAll(stepDescription, maxDigitsText, powerText);
    }
    
    /**
     * Does everything the method of the same name in ListPane does, except also resets the step counter here in RadixPane to allow for subsequent runs.
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
        power = 1;
        maxDigits = 0;
        digits = 0;
        counter = 0;
    }
    
    static int maxDigits, digits, power = 1, bucketIndex, arrayIndex, temp, counter = 0;
    static ArrayList<TextField> bucketFieldList = new ArrayList<>();
    static ArrayList<Integer> ogList = new ArrayList<>();

    /**
     * All the sorting takes place here. Sorting steps are handled by a switch case statement that advances the sort depending on the value of the int variable 'step'. Each step has a branch that iterates step at the end of the it, allowing the sort to literally be stepped through. This approach, however, is not efficient and used purely for illustrative purposes. To keep sorting consistent it has to be restarted for every step, creating a lot of wasted processing.
     * I learned this approach to radix sorting from zyBook chapter 25.7, and based the algorithm off the skeleton code they provide. 
     * @param evt 
     */
    @Override
    protected void processSortButton(ActionEvent evt) {

        switch (step) {
            case 1:
                sortButton.setText("Next");
                Integer size = MasterList.size();
                int[] array = new int[size];
                ArrayList[] buckets = new ArrayList[10];
                for (int i = 0; i < size; i++) {
                    array[i] = MasterList.get(i);
                }

                for (int i = 0; i < 10; i++) {
                    ArrayList<Integer> buck = new ArrayList();
                    buckets[i] = buck;
                }
                stepDescription.setText("Step 1:\nWe start by creating a two dimensional array of 10 'buckets'\nEach bucket contains an ArrayList");
                stepDescription.setVisible(true);

                for (int i = 0; i < 10; i++) {
                    TextField bucket = new TextField("empty");
                    bucket.setEditable(false);
                    bucket.setVisible(true);
                    bucket.relocate(500, 200 + 25 * i);
                    getChildren().add(bucket);
                    bucketFieldList.add(bucket);
                }
                step++;

                //size = null;
                //array = null;
                break;

            case 2:
                // Can only reassign the existing ones. This will have to all be ran through at every step!
                size = MasterList.size();
                array = new int[size];
                buckets = new ArrayList[10];
                for (int i = 0; i < size; i++) {
                    array[i] = MasterList.get(i);
                }

                for (int i = 0; i < 10; i++) {
                    ArrayList<Integer> buck = new ArrayList();
                    buckets[i] = buck;
                }

                maxDigits = 0;
                // Find the max length in digits
                for (int i = 0; i < size; i++) {
                    if (getMaxDigits(array[i]) > maxDigits) {
                        maxDigits = getMaxDigits(array[i]);
                    }
                }

                maxDigitsText.setText("Digits: " + maxDigits);
                maxDigitsText.setVisible(true);

                stepDescription.setText("Step 2:\nNext, the number of digits in the number with the most digits in the list must be found. For instance, if the list had the number one million in it, the max number of digits would be 7. This figure determines how many times the numbers in the original list are pulled out and placed into the buckets.");

                step++;
                break;

            case 3:
                powerText.setVisible(true);
                powerText.setText("Digit = " + power + "'s");
                
                size = MasterList.size();
                array = new int[size];
                buckets = new ArrayList[10];
                for (int i = 0; i < size; i++) {
                    array[i] = MasterList.get(i);
                }

                // Creates an Array of 'buckets', each bucket being an empty ArrayList. Because the buckets each correspond to a number 0 - 9, there will always be 10 of them
                for (int i = 0; i < 10; i++) {
                    ArrayList<Integer> buck = new ArrayList();
                    buckets[i] = buck;
                }

                // Radix sort relies heavily on digit spaces in the given elements to sort. This for loop calls the getMaxDigits() method to find
                // The greatest number of digits in any element in the given list of numbers
                maxDigits = 0;
                // Find the max length in digits
                for (int i = 0; i < size; i++) {
                    if (getMaxDigits(array[i]) > maxDigits) {
                        maxDigits = getMaxDigits(array[i]);
                    }
                }

                // Refreshes the displayed list of buckets
                bucketFieldList.clear();
                for (int i = 0; i < 10; i++) {
                    TextField bucket = new TextField("empty");
                    bucket.setEditable(true);
                    bucket.setVisible(true);
                    bucket.relocate(500, 200 + 25 * i);
                    getChildren().add(bucket);
                    bucketFieldList.add(bucket);
                }

                // starts with the ones digit, and assigns each element in the original list into a bucket based on this digit
                // once all elements are in their proper buckets, they are pulled back out, into the original array, now sorted according to the first digit.
                // the digit being looked at, through the power variable, is multiplied by 10, moving from 1's to 10's to 100's etc
                // Now that the first digit has been sorted, elements are placed into buckets according to their 10's digit, and pulled back 
                // Back in the original array they are now sorted according to their 1's and 10's digit
                // This process repeats for the max number of digits in any element in the list
                // Once the last digit is processed all numbers will have been sorted
                // Here, counter functions in place of a for loop for the sake of user interactivity
                if (counter < maxDigits) {
                    for (int j = 0; j < size; j++) {
                        bucketIndex = Math.abs(array[j] / power) % 10;
                        buckets[bucketIndex].add(array[j]);
                    }
                    arrayIndex = 0;
                    for (int j = 0; j < 10; j++) {
                        for (int n = 0; n < buckets[j].size(); n++) {
                            array[arrayIndex] = (int) buckets[j].get(n);
                            arrayIndex++;
                        }

                    }
                    
                    power *= 10;
                    
                    updateBucketsDisplay(buckets);

                    for (int j = 0; j < 10; j++) {
                        for (int n = 0; n < size; n++) {
                            buckets[j].clear();
                        }
                    }

                    counter++;

                    if (counter >= maxDigits) {
                        step++;
                    }

                }
                for (int i = 0; i < size; i++) {
                    MasterList.set(i, array[i]);
                }
                updateListDisplay();

                stepDescription.setText("Step 3:\nThis is where the meat of the sorting happens. Every number in the list is assigned to a bucket based on the current digit being examined, and then pulled back into the original list in the order of this digit. This process starts with the ones digit, and then moves left to the tens, then hundreds.");
                break;

                
            case 4:
                // By now the sorting is done and the displayed buckets are updated one last time
                stepDescription.setText("Step 4:\nPulled from the buckets for the final time the original list is now sorted without any logical operations!");
                bucketFieldList.clear();
                for (int i = 0; i < 10; i++) {
                    TextField bucket = new TextField("empty");
                    bucket.setEditable(true);
                    bucket.setVisible(true);
                    bucket.relocate(500, 200 + 25 * i);
                    getChildren().add(bucket);
                    bucketFieldList.add(bucket);
                }
                maxDigitsText.setVisible(false);
                powerText.setVisible(false);
                break;

            default:
                System.out.println("What");
                break;
        }
    }

    /**
     * Helper method for sorting. Inspired by the zyBook chapter 25.7 on Radix sort. 
     * @param number input number
     * @return digit count of input number
     */
    private int getMaxDigits(int number) {
        if (number == 0) {
            return 1;
        }

        // starts with 0 digits, for every time the number can be divided by 10 and still have value digits is iterated
        digits = 0;
        while (number != 0) {
            digits++;
            number /= 10;
        }
        return digits;
    }

    static int temp2 = 0;
    static String text = "";
    
    /**
     * Goes through the input list and updates the secondary displayed list of buckets on the right during the sorting steps. Works for illustrative purposes but is not the most efficient.
     * @param a Array of ArrayLists buckets from radix sorting algorithm
     */
    private void updateBucketsDisplay(ArrayList[] a) {
        System.out.println("Update bucket display running");

        // refreshes the list and display of displayed buckets
        bucketFieldList.clear();
        for (int i = 0; i < 10; i++) {
            TextField bucket = new TextField("empty");
            bucket.setEditable(true);
            bucket.setVisible(true);
            bucket.relocate(500, 200 + 25 * i);
            getChildren().add(bucket);
            bucketFieldList.add(bucket);
        }

        // goes through the list of displayed buckets and updates the text they display
        for (int i = 0; i < a.length; i++) {
            text = "";
            for (int j = 0; j < a[i].size(); j++) {
                TextField bucket = new TextField("");

                if (j == a[i].size())
                    text += a[i].get(j);
                else 
                    text += a[i].get(j) + ", ";
                bucket.setText(text);
                
                bucket.setEditable(true);
                bucket.setVisible(true);
                bucket.relocate(500, 200 + 25 * i);
                getChildren().add(bucket);
                bucketFieldList.set(i, bucket);
            }
        }

        for (TextField bucket : bucketFieldList) {
            bucket.setEditable(false);
        }

    }

}
