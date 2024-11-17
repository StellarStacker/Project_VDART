import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JavaProject extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Start the application with the login scene
        Scene loginScene = createLoginPage(primaryStage);
        primaryStage.setTitle("Login Application");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private Scene createLoginPage(Stage primaryStage) {

        // UI Components for Login Page

	//Image image=new Image("d:/usericon.jpeg");
        TextField usernameField = createTextField("Username", 230, 340);
        PasswordField passwordField = createPasswordField("Password", 230, 420);
        Button loginButton = createButton("LOGIN", 280, 500);

        // Set Login Button Action
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticateUser(username, password)) {
                primaryStage.setScene(createSearchPage(primaryStage));
            } else {
                showAlert("Login Failed", "Invalid username or password. Please try again.");
            }
        });

        // Layout and Scene Setup
	
        AnchorPane loginLayout = new AnchorPane();
        loginLayout.getChildren().addAll(usernameField, passwordField, loginButton);
        return new Scene(loginLayout, 600, 700);
    }


    private Scene createSearchPage(Stage primaryStage) {

	TextField t1=new TextField("Enter batch number ");
        Label welcomeLabel = createLabel("Welcome to the Search Page!", 200, 200);
        Button logoutButton = createButton("Logout", 260, 300);

        // Set Logout Button Action
        logoutButton.setOnAction(event -> primaryStage.setScene(createLoginPage(primaryStage)));

        // Layout and Scene Setup
        AnchorPane searchLayout = new AnchorPane();
        searchLayout.getChildren().addAll(welcomeLabel, logoutButton,t1);
        return new Scene(searchLayout, 600, 700);
    }


    private boolean authenticateUser(String username, String password) {

        return "admin".equals(username) && "admin".equals(password);
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper Methods for UI Components


    private Button createButton(String text, double x, double y) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

 
    private TextField createTextField(String promptText, double x, double y) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        return textField;
    }

 
    private PasswordField createPasswordField(String promptText, double x, double y) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        passwordField.setLayoutX(x);
        passwordField.setLayoutY(y);
        return passwordField;
    }


    private Label createLabel(String text, double x, double y) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
