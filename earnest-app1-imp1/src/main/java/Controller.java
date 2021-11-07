import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private boolean completionState = false;
    private boolean editorGate = false;

    //First row controls
    @FXML
    Button saveListButton;
    @FXML
    Button loadButton;
    @FXML
    ChoiceBox<String> viewDropdown;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewDropdown.getItems().add("All");
        viewDropdown.getItems().add("Completed");
        viewDropdown.getItems().add("Incomplete");

        viewDropdown.setValue("All");
    }

    public void openSave(ActionEvent actionEvent) {
        //Open TodoListSaveList fxml
        openFXML("TodoListSaveItem.fxml", "Todo List Save");
    }
    public void saveList(ActionEvent actionEvent) {
        //Save contents of list to a textFile
        //File name and location are pulled from gui

        //Clear current list
        //Clear display
    }

    public void openLoad(ActionEvent actionEvent) {
        //Open TodoListLoadList fxml
        openFXML("TodoListLoadList.fxml", "Todo List Search");
    }
    public void loadList(HashMap<Integer, String[]> todoLists) {
        //Open file selection fxml

        //Clear current list
        //add items from previously saved list to current display
    }

    public void displayChoicePicker(ActionEvent actionEvent) {
        ObservableList<Event> complete = FXCollections.observableArrayList();
        ObservableList<Event> incomplete = FXCollections.observableArrayList();
        //The dropdown will display 3 options: all, complete, incomplete
        //Upon each state visible list changes

        //Based on selection, gui will present items meeting selection in current list view
        if(Objects.equals(viewDropdown.getValue(), "Incomplete")) {
            for (Event event : list) {
                completionState = eventList.getSelectionModel().getSelectedItem().getComplete();
                if (!completionState) {
                    //fill fields
                    dueDate.setValue(event.getDueDate());
                    description.setText(event.getDescription());
                    //Update List
                    incomplete.add(new Event(false, dueDate.getValue(), description.getText()));
                }
            }
            eventList.setItems(incomplete);
        }
        if(Objects.equals(viewDropdown.getValue(), "Completed")) {
            for (Event event : list) {
                completionState = eventList.getSelectionModel().getSelectedItem().getComplete();
                if (completionState) {
                    //fill fields
                    dueDate.setValue(event.getDueDate());
                    description.setText(event.getDescription());
                    //Update List
                    complete.add(new Event(true, dueDate.getValue(), description.getText()));
                }
            }
            eventList.setItems(complete);
        }
        if(Objects.equals(viewDropdown.getValue(), "All")) {
            eventList.setItems(list);
        }
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
        completionState = eventList.getSelectionModel().getSelectedItem().getComplete();
        completionState = !completionState;
        //fill fields
        dueDate.setValue(list.get(eventList.getSelectionModel().getSelectedIndex()).getDueDate());
        description.setText(list.get(eventList.getSelectionModel().getSelectedIndex()).getDescription());
        //Update List
        list.set(eventList.getSelectionModel().getSelectedIndex(),new Event(completionState, dueDate.getValue(), description.getText()));
        //Adjust which display list
        eventList.setItems(list);
        refresh();

    }

    public void newEvent(ActionEvent actionEvent) {
        //Add event to list view
        if (!editorGate) {
            list.add(new Event(completionState, dueDate.getValue(), description.getText()));
            eventList.setItems(list);
        }
        else {
            list.set(eventList.getSelectionModel().getSelectedIndex(),new Event(completionState, dueDate.getValue(), description.getText()));
            eventList.setItems(list);
            editorGate = false;
        }

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
        editorGate = true;
    }

    public void clearList(ActionEvent actionEvent) {
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

}
