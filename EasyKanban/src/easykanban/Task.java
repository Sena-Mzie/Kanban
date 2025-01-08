package easykanban;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;

/**
 *
 * @author st10230870
 */

class Task {
    private static final String DEVELOPER = "Developer";
    private String name, description, status, taskId;
    private int number, duration;

    public Task(String name, int number, String description, int duration, String developer, String status) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.duration = duration;
        this.status = status;
        createTaskID(developer);
    }

    private void createTaskID(String developer) {
        String developerInitials = developer.length() > 3 ? developer.substring(developer.length() - 3) : developer;
        this.taskId = name.substring(0, Math.min(2, name.length())) + ":" + number + ":" + developerInitials;
    }

    public String getTaskDetails() {
        return "Task: " + name + " | ID: " + taskId + " | Duration: " + duration + " hrs | Status: " + status;
    }

    public int getDuration() {
        return duration;
    }

    public String getDeveloper() {
        return DEVELOPER;
    }

    public String getName() {
        return name;
    }
}


