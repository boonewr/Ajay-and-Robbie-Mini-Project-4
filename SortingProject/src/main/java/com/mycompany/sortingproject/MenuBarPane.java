
package com.mycompany.sortingproject;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ajay
 */

public class MenuBarPane extends BorderPane {
    private MenuBar menuBar;
    private Menu paneMenu;
    private MenuItem exitItem;
    private RadioMenuItem rpItem, spItem, ipItem, hpItem, qpItem;
    private RadixPane rpPane;
    private SelectionPane spPane;
    private InsertionPane ipPane;
    private HomePane hpPane;
    private QuickPane qpPane;
    
    public MenuBarPane() {
        // Instantiate new pane objects and align them with the method in ListPane
        rpPane = new RadixPane();
        rpPane.ALIGN(800, 600);
        spPane = new SelectionPane();
        spPane.ALIGN(800, 600);
        ipPane = new InsertionPane();
        ipPane.ALIGN(800, 600);
        hpPane = new HomePane();
        hpPane.ALIGN(800, 600);
        qpPane = new QuickPane();
        qpPane.ALIGN(800, 600);
        
        StackPane sp = new StackPane();
        sp.getChildren().addAll(hpPane, rpPane, spPane, ipPane, qpPane);
        setCenter(sp);
        
        // have all panes but the home pane (hpPane) invisible
        rpPane.setVisible(false);
        spPane.setVisible(false);
        ipPane.setVisible(false);
        qpPane.setVisible(false);
        
        // Creating labels for the pages and adding them to the menu
        menuBar = new MenuBar();
        paneMenu = new Menu("Sort Select");
        menuBar.getMenus().add(paneMenu);
        exitItem = new MenuItem("Exit");
        rpItem = new RadioMenuItem("Radix Sort");
        spItem = new RadioMenuItem("Selection Sort");
        ipItem = new RadioMenuItem("Insertion Sort");
        hpItem = new RadioMenuItem("Home Page");
        qpItem = new RadioMenuItem("Quick Sort");
        hpItem.setSelected(true);
        paneMenu.getItems().addAll(hpItem, rpItem, spItem, ipItem, qpItem, exitItem);
        
        // Create a toggle group and put all items in ther
        ToggleGroup sortToggle = new ToggleGroup();
        hpItem.setToggleGroup(sortToggle);
        rpItem.setToggleGroup(sortToggle);
        spItem.setToggleGroup(sortToggle);
        ipItem.setToggleGroup(sortToggle);
        qpItem.setToggleGroup(sortToggle);
        
        // Give each item an event listener
        hpItem.setOnAction(this::processMenu);
        rpItem.setOnAction(this::processMenu);
        spItem.setOnAction(this::processMenu);
        ipItem.setOnAction(this::processMenu);
        qpItem.setOnAction(this::processMenu);
        exitItem.setOnAction(this::processMenu);
        
        setTop(menuBar);
        
    }
    
    public void processMenu(ActionEvent evt) {
        // The item MenuItem object takes ActionEvent input
        
        MenuItem item = (MenuItem)evt.getSource();
        
        // Initially have all panes' visibility false, but change when the item object selects the right one
        hpPane.setVisible(false);
        rpPane.setVisible(false);
        spPane.setVisible(false);
        ipPane.setVisible(false);
        qpPane.setVisible(false);
        
        if (item == exitItem) {
            System.exit(0);
        } else if (item == rpItem) {
            rpPane.setVisible(true);
        } else if (item == spItem) {
            spPane.setVisible(true);
        } else if (item == ipItem) {
            ipPane.setVisible(true);
        } else if (item == hpItem) {
            hpPane.setVisible(true);
        } else if (item == qpItem) {
            qpPane.setVisible(true);
        }
    }
}
