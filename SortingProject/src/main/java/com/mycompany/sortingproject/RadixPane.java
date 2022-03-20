/* To do: make it go step by step :)
 */
package com.mycompany.sortingproject;
import java.util.ArrayList;
import javafx.event.ActionEvent;

/**
 *
 * @author wrboo
 */
public class RadixPane extends ListPane {
    int step = 1;
    
    public RadixPane() {
        setDescription("radix sort is fancy blah blah blah O(n)");
        setName("Radix Sort!");
    }
    
    /*
    @Override
    protected void processSortButton(ActionEvent evt) {
        switch (step) {
            case 1:
                System.out.println("Step 1");
                step++;
                break;
            case 2: 
                System.out.println("Step 2");
                step++;
                break;
            case 3: 
                System.out.println("Step 3");
                step++;
                break;
            default:
                System.out.println("What");
                break;
        }
    }
    */    

    int maxDigits;
    int digits;
    int power;
    int bucketIndex;
    int arrayIndex;
    int temp;
    
    @Override
    protected void processSortButton(ActionEvent evt) {        
        int size = MasterList.size();
        int[] array = new int[size];
        ArrayList[] buckets = new ArrayList[10]; 
        for (int i = 0; i < size; i++)
            array[i] = MasterList.get(i);
        
        for (int i =0; i < 10; i++) {
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
    
    private int getMaxDigits(int number) {
        if (number == 0) return 1;
        
        digits = 0;
        while (number != 0) {
            digits++;
            number /= 10;
        }
        return digits;
    }
    
    
}
