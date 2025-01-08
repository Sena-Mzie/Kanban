
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class EasyKabanUnitTest {

    private LoginApp loginApp;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea outputArea;
    private JButton loginButton;
    private JButton registerButton;

    @BeforeEach
    public void setUp() {
        loginApp = new LoginApp();
        usernameField = mock(JTextField.class);
        passwordField = mock(JPasswordField.class);
        outputArea = mock(JTextArea.class);
        loginButton = mock(JButton.class);
        registerButton = mock(JButton.class);

        // Injecting mocks into the actual object
        loginApp.usernameField = usernameField;
        loginApp.passwordField = passwordField;
        loginApp.outputArea = outputArea;
        loginApp.loginButton = loginButton;
        loginApp.registerButton = registerButton;
    }

    @Test
    public void testRegistrationWithValidCredentials() {
        when(usernameField.getText()).thenReturn("user_");
        when(passwordField.getPassword()).thenReturn("Password1!".toCharArray());

        // Simulate button click
        registerButton.doClick();

        verify(outputArea).setText("User registered successfully.");
    }

    @Test
    public void testRegistrationWithInvalidUsername() {
        when(usernameField.getText()).thenReturn("user");
        when(passwordField.getPassword()).thenReturn("Password1!".toCharArray());

        // Simulate button click
        registerButton.doClick();

        verify(outputArea).setText("Username must contain an underscore and be no more than 5 characters long.");
    }

    @Test
    public void testLoginWithCorrectCredentials() {
        loginApp.storedUsername = "user_";
        loginApp.storedPassword = "Password1!";

        when(usernameField.getText()).thenReturn("user_");
        when(passwordField.getPassword()).thenReturn("Password1!".toCharArray());

        // Simulate button click
        loginButton.doClick();

        // Assuming you handle what happens on successful login within the actionPerformed
        verify(outputArea, never()).setText("Username or password incorrect, please try again.");
    }

    @Test
    public void testLoginWithIncorrectCredentials() {
        loginApp.storedUsername = "user_";
        loginApp.storedPassword = "Password1!";

        when(usernameField.getText()).thenReturn("user_");
        when(passwordField.getPassword()).thenReturn("WrongPassword".toCharArray());

        // Simulate button click
        loginButton.doClick();

        verify(outputArea).setText("Username or password incorrect, please try again.");
    }
    
    // Add more tests as needed for TaskAdding and other functionality
}
