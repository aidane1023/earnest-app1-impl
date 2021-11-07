/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    private boolean completionState = false;
    private boolean editorGate = false;
    List<String> strings = new ArrayList<>();

    //First row controls
    @FXML
    Button saveListButton;
    @FXML
    ChoiceBox<String> viewDropdown = new ChoiceBox<>();
    @FXML
    Button showButton;

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

    //Save components
    @FXML
    TextField fileName;
    @FXML
    TextField fileLocation;

    //List view manager
    @FXML
    ListView<Event> eventList = new ListView<>();

    ObservableList<Event> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewDropdown.getItems().add("All");
        viewDropdown.getItems().add("Completed");
        viewDropdown.getItems().add("Incomplete");

        this.viewDropdown.setValue("All");
    }

    public void newEvent(ActionEvent actionEvent) {
        //Add event to list view
        if (list.size() <= 100) {
            description.setText(description.getText());
            if (!description.getText().isEmpty()) {
                if (!editorGate) {
                    list.add(new Event(completionState, dueDate.getValue(), description.getText()));
                    eventList.setItems(list);
                } else {
                    list.set(eventList.getSelectionModel().getSelectedIndex(), new Event(completionState, dueDate.getValue(), description.getText()));
                    eventList.setItems(list);
                    editorGate = false;
                }

                //refresh item components
                refresh();
            }
        }
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
        try {
            dueDate.setValue(list.get(eventList.getSelectionModel().getSelectedIndex()).getDueDate());
            description.setText(list.get(eventList.getSelectionModel().getSelectedIndex()).getDescription());
            editorGate = true;
        } catch (Exception e) {
            System.out.println("No Selection");
        }
    }

    public void changeCompletion(ActionEvent actionEvent) {
        //Swap completion state of event
        try {
            completionState = eventList.getSelectionModel().getSelectedItem().getComplete();
            completionState = !completionState;
            //fill fields
            dueDate.setValue(list.get(eventList.getSelectionModel().getSelectedIndex()).getDueDate());
            description.setText(list.get(eventList.getSelectionModel().getSelectedIndex()).getDescription());
            //Update List
            list.set(eventList.getSelectionModel().getSelectedIndex(), new Event(completionState, dueDate.getValue(), description.getText()));
            //Adjust which display list
            eventList.setItems(list);
            refresh();
        } catch (Exception e) {
            System.out.println("No Selection");
        }

    }

    public void displayChoicePicker(ActionEvent actionEvent) {
        ObservableList<Event> complete = FXCollections.observableArrayList();
        ObservableList<Event> incomplete = FXCollections.observableArrayList();
        //The dropdown will display 3 options: all, complete, incomplete
        //Upon each state visible list changes

        //Based on selection, gui will present items meeting selection in current list view
        if(!Objects.equals(viewDropdown.getValue(), "All")) {
            for (Event event : list) {
                completionState = event.getComplete();
                dueDate.setValue(event.getDueDate());
                description.setText(event.getDescription());
                if (!completionState) {
                    //Update List
                    incomplete.add(new Event(false, dueDate.getValue(), description.getText()));
                } else {
                    //Update List
                    complete.add(new Event(true, dueDate.getValue(), description.getText()));
                }
                //Update displayed list
                if (Objects.equals(viewDropdown.getValue(), "Incomplete")) {
                    eventList.setItems(incomplete);
                } else {
                    eventList.setItems(complete);
                }
            }
        }
        if(Objects.equals(viewDropdown.getValue(), "All")) {
            eventList.setItems(list);
        }

        refresh();
    }

    public void saveList(ActionEvent actionEvent) {
        //Write to file
        writeFile();

        //Empty components for display
        emptyList();
    }

    public void clearList(ActionEvent actionEvent) {
        //Clear list from Manager page
        emptyList();
    }

    public void emptyList() {
        //Clear all events from list
        list.remove(0, list.size());
        //Update display
        eventList.setItems(list);
        viewDropdown.setValue("All");
    }

    private void refresh() {
        dueDate.setValue(null);
        description.setText(null);
    }

    public void writeFile() {
        //Convert to string
        for (Event event : list) {
            strings.add(event.toString());
        }
        System.out.println(strings.toString());

        //Write to file

        try {
            FileWriter writer = new FileWriter(fileLocation.getText() +"\\"+ fileName.getText() + ".txt");
            for (String str : strings) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }
}