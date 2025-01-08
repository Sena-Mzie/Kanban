package easykanban;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author st10230870
 */

class LoginApp extends JFrame {
    //Declaring variables
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton registerButton;
    private JButton loginButton;
    private JTextArea outputArea;

    private String storedUsername;
    private String storedPassword;
    private String storedFirstName;
    private String storedLastName;

    public LoginApp() {
        //Creating the JFrame
        setTitle("Login Application");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterAction());
        add(registerButton);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());
        add(loginButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));
    }

    //Function to check the username
    private boolean checkUserName(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    //Fuction to check the password
    private boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[0-9].*") &&
               password.matches(".*[!@#$%^&*()].*");
    }

    //Function to define the action for the Register button
    private class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();

            if (!checkUserName(username)) {
                outputArea.setText("Username must contain an underscore and be no more than 5 characters long.");
                return;
            }

            if (!checkPasswordComplexity(password)) {
                outputArea.setText("Password must be at least 8 characters with a capital letter, a number, and a special character.");
                return;
            }

            storedUsername = username;
            storedPassword = password;
            storedFirstName = firstName;
            storedLastName = lastName;

            outputArea.setText("User registered successfully.");
        }
    }

     //Function to define the action for the Login button
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                JOptionPane.showMessageDialog(null, "Welcome to EasyKanBan");
                new TaskAdding().setVisible(true); //Continues to the rest of the application
                LoginApp.this.setVisible(false);
            } else {
                outputArea.setText("Username or password incorrect, please try again.");
            }
        }
    }
}