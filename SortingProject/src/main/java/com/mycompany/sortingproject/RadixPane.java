/* To do: make it go step by step :)
 */
package com.mycompany.sortingproject;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author wrboo
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
    
    /*
    @Override
    protected void processSortButton(ActionEvent evt) {        
        int size = MasterList.size();
        int[] array = new int[size];
        ArrayList[] buckets = new ArrayList[10]; 
        for (int i = 0; i < size; i++)
            array[i] = MasterList.get(i);
        
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> buck = new ArrayList();
            buckets[i] = buck;
        }
        
        maxDigits = 0;
        // Find the max length in digits
        for (int i = 0; i < size; i++) {
            if (getMaxDigits(array[i]) > maxDigits)
                maxDigits = getMaxDigits(array[i]);
        }
        
        power = 1;
        for (int i = 0; i < maxDigits; i++) {
            for (int j = 0; j < size; j++) {
                bucketIndex = Math.abs(array[j] / power) % 10;
                buckets[bucketIndex].add(array[j]);
            }
            arrayIndex = 0;
            for (int j = 0; j < 10; j++) {
                for (int n = 0; n < buckets[j].size(); n++) {
                    System.out.println(buckets[j].get(n));
                    array[arrayIndex] = (int)buckets[j].get(n);
                    arrayIndex++;
                }
                
            }
            power *= 10;
            
            for (int j = 0; j < 10; j++) {
                for (int n = 0; n < size; n++) {
                    buckets[j].clear();
                }
            }
        }
        
        for (int i = 0; i < size; i++) {
            MasterList.set(i, array[i]);
        }
        updateListDisplay();
    }
    
     */
    static int maxDigits, digits, power = 1, bucketIndex, arrayIndex, temp, counter = 0;
    static ArrayList<TextField> bucketFieldList = new ArrayList<>();
    static ArrayList<Integer> ogList = new ArrayList<>();

    @Override
    protected void processSortButton(ActionEvent evt) {
        //int maxDigits;

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

                stepDescription.setText("Step 2:\nNext, the number of digits in the numebr with the most digits in the list must be found. For instance, if the list had the number one million in it, the max number of digits would be 7. This figure determines how many times the numbers in the original list are pulled out and placed into the buckets.");

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

                bucketFieldList.clear();
                for (int i = 0; i < 10; i++) {
                    TextField bucket = new TextField("empty");
                    bucket.setEditable(true);
                    bucket.setVisible(true);
                    bucket.relocate(500, 200 + 25 * i);
                    getChildren().add(bucket);
                    bucketFieldList.add(bucket);
                }

                //power = 1;
                if (counter < maxDigits) {
                    for (int j = 0; j < size; j++) {
                        bucketIndex = Math.abs(array[j] / power) % 10;
                        buckets[bucketIndex].add(array[j]);
                    }
                    arrayIndex = 0;
                    for (int j = 0; j < 10; j++) {
                        for (int n = 0; n < buckets[j].size(); n++) {
                            //System.out.println(buckets[j].get(n));
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

    private int getMaxDigits(int number) {
        if (number == 0) {
            return 1;
        }

        digits = 0;
        while (number != 0) {
            digits++;
            number /= 10;
        }
        return digits;
    }

    static int temp2 = 0;
    static String text = "";
    
    private void updateBucketsDisplay(ArrayList[] a) {
        System.out.println("Update bucket display running");

        bucketFieldList.clear();
        for (int i = 0; i < 10; i++) {
            TextField bucket = new TextField("empty");
            bucket.setEditable(true);
            bucket.setVisible(true);
            bucket.relocate(500, 200 + 25 * i);
            getChildren().add(bucket);
            bucketFieldList.add(bucket);
        }

        for (int i = 0; i < a.length; i++) {
            text = "";
            for (int j = 0; j < a[i].size(); j++) {
                //bucketFieldList.get(i).setText("" + a[i].get(j) + ", ");
                TextField bucket = new TextField("");

                /*
                if (j == a[i].size()) {
                    bucket.setText(bucket.getText() + a[i].get(j) + "");
                } else {
                    for (int n = 0; n < j; n++) {
                        bucket.setText(bucket.getText() + a[i].get(j) + ", ");
                    }
                }
                */

                //text = "";
                /*
                for (int n = 0; n < j; n++) {
                    text += a[i].get(j) + ", ";
                    }
*/
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
        temp2++;

    }

}
