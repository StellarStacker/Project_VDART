package myjavaproject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MyJavaProject extends Application {
        Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,title;
        TextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    @Override
    public void start(Stage primaryStage) {
        // Start the application with the login scene
        //Scene loginScene = createLoginPage(primaryStage);
        Scene viewscene=Viewpage(primaryStage);
        primaryStage.setTitle("Login Application");
       // primaryStage.setScene(loginScene);
        primaryStage.setScene(viewscene); 
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
    private Scene Viewpage(Stage PrimaryStage){
            
        AnchorPane viewLayout=new AnchorPane();
        
        title=createLabel("STUDENT DATA",420,20);
        
        Button b1=new Button("UPLOAD");
        b1.setLayoutX(430);
        b1.setLayoutY(560);
        l1=createLabel("Name",100,50);
        t1=createTextField("enter name",l1.getLayoutX(),90);
        
        l2=createLabel("Batch Number",l1.getLayoutX(),150);
        t2=createTextField("enter name",l1.getLayoutX(),l2.getLayoutY()+40);
        
        l3=createLabel("D.O.B",l1.getLayoutX(),l2.getLayoutY()+110);
        t3=createTextField("enter name",l1.getLayoutX(),l3.getLayoutY()+40);
        
        l4=createLabel("Department",l1.getLayoutX(),l3.getLayoutY()+110);
        t4=createTextField("enter name",l1.getLayoutX(),l4.getLayoutY()+40);
        
        l5=createLabel("Year",l1.getLayoutX(),l4.getLayoutY()+110);
        t5=createTextField("enter name",l1.getLayoutX(),l5.getLayoutY()+40);
        
        
        l6=createLabel("MA3354",650,110);
        t6=createTextField("enter name",l6.getLayoutX()+80,l6.getLayoutY()-5);
        
        l7=createLabel("CS3351",l6.getLayoutX(),l6.getLayoutY()+80);
        t7=createTextField("enter name",l7.getLayoutX()+80,l7.getLayoutY()-5);
        
        l8=createLabel("AD3491",l6.getLayoutX(),l7.getLayoutY()+80);
        t8=createTextField("enter name",l8.getLayoutX()+80,l8.getLayoutY()-5);
        
        l9=createLabel("CS3391",l6.getLayoutX(),l8.getLayoutY()+80);
       t9=createTextField("enter name",l6.getLayoutX()+80,l9.getLayoutY()-5);
        
        l10=createLabel("AD3351",l6.getLayoutX(),l9.getLayoutY()+80);
        t10=createTextField("enter name",l10.getLayoutX()+80,l10.getLayoutY()-5);
        
        l11=createLabel("CW3301",l6.getLayoutX(),l10.getLayoutY()+80);
        t11=createTextField("enter name",l11.getLayoutX()+80,l11.getLayoutY()-5);
        
        viewLayout.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,b1,title);
       
        
      
        return new Scene(viewLayout,1000,600);
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
