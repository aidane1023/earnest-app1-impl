/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Objects;

public class TodoListApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TodoListManager.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Todo List Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public HashMap<Integer, String[]> todoLists;

    public void loadListButton(HashMap<Integer, String[]> todoLists) {
        //Open file selection fxml

        //Clear current list
        //add items from previously saved list to current display
    }

    public void saveListButton(HashMap<Integer, String[]> todoLists) {
        //Open TodoListSaveList fxml

        //Clear current list
        //Clear display
    }

    public void displayChoicePicker() {
        //The dropdown will display 3 options: all, complete, incomplete

        //Based on selection, gui will present items meeting selection in current list view
    }
}