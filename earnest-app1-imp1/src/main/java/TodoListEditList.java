/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TodoListEditList extends TodoListApplication {
    public String[] items;
    private String selectedItem;

    @FXML
    public void addItem(String[] items) {
        //Open TodoListItems page from fxml files
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TodoListItem.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error opening page.");
        }
    }
    public String[] deleteItem(String[] items, String selectedItem) {
        //Remove selectedItem from items string
        //Clear selectedItem String

        //Update display in gui
        //return updated items array
        return null;
    }
    public String[] editItem(String[] items, String selectedItem) {
        //Open TodoListItems page from fxml files

        //Update display in gui
        //Return any changes made to items array
        return null;
    }

    public void clearList(String[] items) {
        //Clear contents of array
        //Begin array from scratch back at 0

        //Clear display in gui
    }

}
