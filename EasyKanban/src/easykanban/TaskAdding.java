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

//Collecting tasks
class TaskAdding extends JFrame {
    private ArrayList<Task> tasks = new ArrayList<>();
    private JTextField taskNameInput, taskDurationInput, taskDescriptionInput;
    private JComboBox<String> taskStatusCombo;
    private JTextArea taskDetailsArea;
    private JButton deleteTaskButton, displayReportButton;

    //Creating a JFrame
    public TaskAdding() {
        setTitle("Task Adding Application");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setupInputPanel();
        setupTaskDisplay();
        setupButtons();
    }

    private void setupInputPanel() {
        JPanel inputPanel = new JPanel();
        taskNameInput = new JTextField(10);
        taskDurationInput = new JTextField(5);
        taskDescriptionInput = new JTextField(20);
        taskStatusCombo = new JComboBox<>(new String[]{"To Do", "Doing", "Done"});

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> addTask());

        inputPanel.add(new JLabel("Task Name:"));
        inputPanel.add(taskNameInput);
        inputPanel.add(new JLabel("Duration (Hours):"));
        inputPanel.add(taskDurationInput);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(taskDescriptionInput);
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(taskStatusCombo);
        inputPanel.add(addButton);
        add(inputPanel);
    }

    //A task display area
    private void setupTaskDisplay() {
        taskDetailsArea = new JTextArea(10, 40);
        taskDetailsArea.setEditable(false);
        add(new JScrollPane(taskDetailsArea));
    }

    private void setupButtons() {
        //Button to delete
        deleteTaskButton = new JButton("Delete Task");
        deleteTaskButton.addActionListener(e -> deleteTask());
        add(deleteTaskButton);

        //Button to display
        displayReportButton = new JButton("Display Report");
        displayReportButton.addActionListener(e -> displayReport());
        add(displayReportButton);

        //Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }

    //function to add tasks
    private void addTask() {
        String name = taskNameInput.getText();
        String description = taskDescriptionInput.getText();
        int duration;
        try {
            duration = Integer.parseInt(taskDurationInput.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for duration");
            return;
        }
        String status = (String) taskStatusCombo.getSelectedItem();

        if (description.length() > 50) {
            JOptionPane.showMessageDialog(this, "Please enter a task description of less than 50 characters");
            return;
        }

        Task newTask = new Task(name, tasks.size() + 1, description, duration, "Developer", status);
        tasks.add(newTask);
        taskDetailsArea.append(newTask.getTaskDetails() + "\n");
    }

    //Function to delete task
    private void deleteTask() {
        String taskName = JOptionPane.showInputDialog(this, "Enter the task name to delete:");
        Task toRemove = null;
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                toRemove = task;
                break;
            }
        }
        if (toRemove != null) {
            tasks.remove(toRemove);
            refreshTaskDisplay();
            JOptionPane.showMessageDialog(this, "Task deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Task not found.");
        }
    }

    //function to display
    private void displayReport() {
        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            report.append(task.getTaskDetails()).append("\n");
        }
        if (report.length() > 0) {
            JOptionPane.showMessageDialog(this, report.toString(), "Task Report", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No tasks to display.", "Task Report", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void refreshTaskDisplay() {
        taskDetailsArea.setText("");
        for (Task task : tasks) {
            taskDetailsArea.append(task.getTaskDetails() + "\n");
        }
    }
}

