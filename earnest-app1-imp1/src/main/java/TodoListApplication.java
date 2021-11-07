/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class TodoListApplication extends javafx.application.Application {
    private boolean completionState = false;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TodoListEditor.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Todo List Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //First row controls
    @FXML
    Button saveListButton;
    @FXML
    Button loadListButton;
    @FXML
    ChoiceBox<java.io.Serializable> visibility;

    //Properties of event
    @FXML
    TextField description;
    @FXML
    DatePicker dueDate;
    @FXML
    Button completion;

    //Event controls
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    Button editButton;

    //Clear list
    @FXML
    Button clearButton;

    //List view manager
    @FXML
    ListView<Event> eventList = new ListView<>();

    ObservableList<Event> list = FXCollections.observableArrayList();
    ObservableList<Integer> selectedIndices = eventList.getSelectionModel().getSelectedIndices();

    public void saveList(ActionEvent actionEvent) {
        //Open TodoListSaveList fxml
        openFXML("TodoListSaveItem.fxml", "Todo List Save");

        //Clear current list
        //Clear display
    }

    public void loadList(HashMap<Integer, String[]> todoLists) {
        //Open file selection fxml

        //Clear current list
        //add items from previously saved list to current display
    }

    public void displayChoicePicker() {
        //The dropdown will display 3 options: all, complete, incomplete
        ;
        visibility.getItems().add("Incomplete");
        visibility.getItems().add("Completed");

        visibility.setValue(visibility.getItems().add("All Events"));

        //Based on selection, gui will present items meeting selection in current list view
    }

    private void openFXML(String file, String name) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(file));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle(name);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeCompletion(ActionEvent actionEvent) {
        //Swap completion state of event
        completionState = !completionState;

    }

    public void newEvent(ActionEvent actionEvent) {
        //Add event to list view
            list.add(new Event(completionState, dueDate.getValue(), description.getText()));
            eventList.setItems(list);

        //refresh item components
        refresh();
    }

    public void deleteEvent(ActionEvent actionEvent) {
        //Ensure an event is selected
        try {
            list.remove(eventList.getSelectionModel().getSelectedItem());
        } catch(Exception e) {
            System.out.println("No selection");
        }
        //Remove event from list
    }

    public void editEvent(ActionEvent actionEvent) {
        //Fill event fields
        dueDate.setValue(list.get(eventList.getSelectionModel().getSelectedIndex()).getDueDate());
        description.setText(list.get(eventList.getSelectionModel().getSelectedIndex()).getDescription());
    }

    public void clearList(ActionEvent actionEvent) {
        //Clear all events from list
        list.remove(0,list.size());
        //Update display
        eventList.setItems(list);
    }

    private void refresh() {
        dueDate.setValue(null);
        description.setText(null);
    }

}