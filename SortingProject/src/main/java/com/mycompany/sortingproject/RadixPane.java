/* To do: make it go step by step :)
 */
package com.mycompany.sortingproject;
import javafx.event.ActionEvent;

/**
 *
 * @author wrboo
 */
public class RadixPane extends ListPane {
    //Button sortButton = new Button("Sort");
    
    public RadixPane() {
        sortButton.setOnAction(this::processSort);
        setDescription("radix sort is fancy blah blah blah O(n)");
        setName("Radix Sort!");
    }
    
    public void processSort(ActionEvent evt) {
        /*
        int max = MasterList.get(0);
        System.out.println(MasterList.toString());
        
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
        for (int i = 0; i <= MasterList.size(); i++) {
            output.add(0);
        }
        
        for (int i = MasterList.size() - 1; i >= 0; i--) {
            output.set(counting.get(MasterList.get(i)) - 1, MasterList.get(i));
            counting.set(MasterList.get(i), counting.get(MasterList.get(i)) - 1);
        }
        
        for (int i = 0; i < MasterList.size(); i++) {
            MasterList.set(i, output.get(i));
        }
        
*/      
        int[] a = new int[MasterList.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = MasterList.get(i);
        }
        
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        
        int[] counting = new int[max + 1];

        for (int i = 0; i < a.length; i++) {
            counting[a[i]]++;
        }

        for (int i = 1; i < counting.length; i++) {
            counting[i] += counting[i - 1];
        }

        int[] output = new int[a.length];

        for (int i = a.length - 1; i >= 0; i--) {
            output[counting[a[i]] - 1] = a[i];
            counting[a[i]]--;
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = output[i];
        }
        
        
        for (int i = 0; i < a.length; i++) {
            MasterList.set(i, a[i]);
            updateListDisplay();
            //TimeUnit.SECONDS.
        }
        
        
        updateListDisplay();
    }
    
}
