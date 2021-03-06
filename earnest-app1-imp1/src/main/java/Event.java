/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 aidan earnest
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String description;
    private LocalDate dueDate;
    private boolean complete;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getComplete() {
        return complete;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Event(boolean complete, LocalDate dueDate, String description) {
        this.setComplete(complete);
        this.setDueDate(dueDate);
        this.setDescription(description);
    }

    @Override
    public String toString() {
        //Format how date appears
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //Format list view
        String output;
        if (!this.getComplete()) {
            output = "Incomplete   ";
        } else {
            output = "Complete   ";
        }
        if (this.getDueDate() == null) {
            output = output + this.description;
        } else {
            String date = (formatter.format(this.getDueDate()));
            output = output + date + "   " + this.description;
        }
        return output;
    }
}
