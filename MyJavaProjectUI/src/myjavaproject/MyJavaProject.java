package myjavaproject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MyJavaProject extends Application {
        Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,title;
        TextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
        TextArea t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22;
        boolean promptedbycreate=false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Start the application with the login scene
        //Scene searchScene = createSearchPage(primaryStage);
        
        Scene loginscene=createLoginPage(primaryStage);
        primaryStage.setTitle("TEAM VDART SAAS");
        primaryStage.setScene(loginscene);
        //primaryStage.setScene(Entrypage(primaryStage));
        primaryStage.show();
    }

        private Scene createLoginPage(Stage primaryStage){
            
        // UI Components for Login Page
         AnchorPane loginLayout = new AnchorPane();
        loginLayout.setStyle("-fx-background-color: #DCC2E0;");
	Image image=new Image("file:d:\\usericon3.jpg");
        ImageView iv=new ImageView(image);
        iv.setLayoutX(417);
        iv.setLayoutY(120);
        iv.setFitHeight(205);
        iv.setFitWidth(155);
        
        TextField usernameField = createTextField("Username", 420, 350);
        usernameField.setPrefWidth(150);
        PasswordField passwordField = createPasswordField("Password", 420, 400);
        passwordField.setPrefWidth(149);
        
        CheckBox c1=new CheckBox("Show password");
        c1.setFont(Font.font("Arial",FontWeight.MEDIUM,15));
        c1.setLayoutX(passwordField.getLayoutX()+8);
        c1.setLayoutY(passwordField.getLayoutY()+47);
        
        Button loginButton = createButton("LOGIN", 460, 495);
        loginButton.setFont(fontify(loginButton.getText()));
        
        loginButton.setStyle("-fx-background-color: #007BFF;");
           
        TextField sh=new TextField();
        sh.setPromptText("Password");
        sh.setLayoutX(passwordField.getLayoutX());
        sh.setLayoutY(passwordField.getLayoutY());
        sh.setVisible(false);
        // Set Login Button Action
        c1.setOnAction(event->{
            if(c1.isSelected()){
                sh.setText(passwordField.getText());
                sh.setVisible(true);
                passwordField.setVisible(false);
            }else{
                passwordField.setText(sh.getText());
                passwordField.setVisible(true);
                sh.setVisible(false);  
            }
        });
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(event -> {
            
            
            String username = usernameField.getText();
            System.out.println("pass"+passwordField.getText());
            System.out.println("textfield"+sh.getText());
            String password = (!(passwordField.getText().equals("")))||(!(passwordField.getText().equals(""))) ? passwordField.getText():sh.getText(); 
            //minor bug rectified as pervious line                                                                                                                                     
            //where showpassword box if first checked leads to a empty string.                                                                                                                                  
            //so just used a ternary operator to assign the actual hidden string
             System.out.println("Recieved String is : "+password);
            if (authenticateUser(username, password)) {
                try {
                    primaryStage.setScene(createSearchPage(primaryStage));
                } catch (Exception ex) {
                    Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                showAlert("Login Failed", "Invalid username or password. Please try again.");
            }
        });

        // Layout and Scene Setup
	
        loginLayout.getChildren().addAll(usernameField, passwordField, loginButton,c1,sh,iv);
        return new Scene(loginLayout, 1000, 600);
    }


    private Scene createSearchPage(Stage primaryStage) throws Exception{

	
        AnchorPane searchLayout = new AnchorPane();
        searchLayout.setStyle("-fx-background-color: #DADDF0;");
        TextField searchfield=createTextField("Enter batch number ",400,300);
        searchfield.setPrefSize(200,30);
        Label welcomeLabel = createLabel("WELCOME TO SEARCH PAGE",360,230);
        welcomeLabel.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD,15));
        welcomeLabel.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD,20));
                
                
        
        Button search=createButton("SEARCH",400,370);
        search.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD,15));
        Button logoutButton = createButton("Back",40,50);
        logoutButton.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD,15));
        Button create=createButton("CREATE",510,370);
        create.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD,15));
        // Set Logout Button Action
        logoutButton.setOnAction(event -> primaryStage.setScene(createLoginPage(primaryStage)));
        
        
        search.setOnAction(event->{
            System.out.println(searchfield.getText());
            if(!searchfield.getText().equals("")){
            try {
                    int isbatch=Integer.parseInt(searchfield.getText());
                    Backend ob=new Backend();
                    int batch=Integer.parseInt(searchfield.getText());
                    ob.getdata(batch);
                    primaryStage.setScene(ViewPage(primaryStage,ob.getobject()));//redirecting to viewpage
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            }catch(NumberFormatException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Type Error");
                alert.setContentText("Kindly enter in batch number format only ");
                alert.showAndWait();
            }
            }else{
                showAlert("Empty field","Kindly make sure you enter batchnumber before hitting search");
            }
        });
        create.setOnKeyPressed(event->{
            if(event.getCode()==KeyCode.ENTER){
             promptedbycreate=true;
            primaryStage.setScene(Entrypage(primaryStage));
            }
        });
        create.setOnAction(event->{
            primaryStage.setScene(Entrypage(primaryStage));
        });
        // Layout and Scene Setup
        
       
        searchLayout.getChildren().addAll(welcomeLabel,search,logoutButton,searchfield,create);
        Scene scene=new Scene(searchLayout,1000,600);
        scene.setOnKeyPressed(event->{
            switch(event.getCode()){
                case ESCAPE:
                    primaryStage.setScene(createLoginPage(primaryStage));
                    break;
                case ENTER:
                    try{
                        if(searchfield.getText().equals("")){
                           showAlert("Empty field ","Kindly make sure you are enter batch number before hitting search");
                        }else{
                            int isbatch=Integer.parseInt(searchfield.getText());
                            if(promptedbycreate){
                                promptedbycreate=false;
                                System.out.println("Already prompted by create");
                            }else if(!searchfield.getText().equals("")){ 
                                System.out.println("Handling scene level enter key for search");
                            try {
                                Backend ob=new Backend();
                                int batch=Integer.parseInt(searchfield.getText());
                                ob.getdata(batch);
                                primaryStage.setScene(ViewPage(primaryStage,ob.getobject()));//redirecting to viewpage
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
                            }finally{
                                promptedbycreate=false;
                            }
                            }else{
                           showAlert("Empty field","Kindly make sure you enter batchnumber before hitting search");
                            }
                        }
                    }catch(NumberFormatException e){
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Type Error");
                            alert.setContentText("Kindly enter in batch number format only ");
                            alert.showAndWait();
                    }
                    break;
                    default:
                        System.out.println("Default case handed");
                        break;
            }
        });
        return scene;
    }
 private Scene ViewPage(Stage primaryStage,GetStudent rob ) {
    AnchorPane entryLayout = new AnchorPane();
    entryLayout.setStyle("-fx-background-color: #FFC5BF;");
    // Title label
    Label title = createLabel("STUDENT DATA", 400, 20);

    // Upload button
    Button b1 = new Button("CLOSE");
    b1.setLayoutX(430);
    b1.setLayoutY(560);

    // Labels and TextAreas
    Label l1 = createLabel("Name", 100, 50);
    TextArea t1 = createTextArea(rob.getname(), l1.getLayoutX(), 90);
    t1.setPrefWidth(t1.getPrefWidth()+5);

    Label l2 = createLabel("Batch Number", l1.getLayoutX(), 150);
    TextArea t2 = createTextArea(String.valueOf(rob.getbatchno()), l1.getLayoutX(), l2.getLayoutY() + 40);

    Label l3 = createLabel("D.O.B", l1.getLayoutX(), l2.getLayoutY() + 110);
    TextArea t3 = createTextArea(rob.getdob(), l1.getLayoutX(), l3.getLayoutY() + 40);

    Label l4 = createLabel("Department", l1.getLayoutX(), l3.getLayoutY() + 110);
    TextArea t4 = createTextArea(rob.getdepartment(), l1.getLayoutX(), l4.getLayoutY() + 40);

    Label l5 = createLabel("Year", l1.getLayoutX(), l4.getLayoutY() + 110);
    TextArea t5 = createTextArea(String.valueOf(rob.getyear()), l1.getLayoutX(), l5.getLayoutY() + 40);

    // For subjects
    Label l6 = createLabel("MA3354", 650, 110);
    TextArea t6 = createTextArea(String.valueOf(rob.getmaths()), l6.getLayoutX() + 80, l6.getLayoutY() - 5);

    Label l7 = createLabel("CS3351", l6.getLayoutX(), l6.getLayoutY() + 80);
    TextArea t7 = createTextArea(String.valueOf(rob.getdpco()), l7.getLayoutX() + 80, l7.getLayoutY() - 5);

    Label l8 = createLabel("AD3491", l6.getLayoutX(), l7.getLayoutY() + 80);
    TextArea t8 = createTextArea(String.valueOf(rob.getfdsa()), l8.getLayoutX() + 80, l8.getLayoutY() - 5);

    Label l9 = createLabel("CS3391", l6.getLayoutX(), l8.getLayoutY() + 80);
    TextArea t9 = createTextArea(String.valueOf(rob.getoop()), l9.getLayoutX() + 80, l9.getLayoutY() - 5);

    Label l10 = createLabel("AD3351", l6.getLayoutX(), l9.getLayoutY() + 80);
    TextArea t10 = createTextArea(String.valueOf(rob.getdaa()), l10.getLayoutX() + 80, l10.getLayoutY() - 5);

    Label l11 = createLabel("CW3301", l6.getLayoutX(), l10.getLayoutY() + 80);
    TextArea t11 = createTextArea(String.valueOf(rob.geteco()), l11.getLayoutX() + 80, l11.getLayoutY() - 5);
    
    l1.setFont(fontify(l1.getText()));
    l2.setFont(fontify(l2.getText()));
    l3.setFont(fontify(l3.getText()));
    l4.setFont(fontify(l4.getText()));
    l5.setFont(fontify(l5.getText()));
    l6.setFont(fontify(l6.getText()));
    l7.setFont(fontify(l7.getText()));
    l8.setFont(fontify(l8.getText()));
    l9.setFont(fontify(l9.getText()));
    l10.setFont(fontify(l10.getText()));
    l11.setFont(fontify(l11.getText()));
    title.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD,20));

    
    b1.setDefaultButton(true);
    // Adding all elements to the layout
    entryLayout.getChildren().addAll(
        l1, t1, l2, t2, l3, t3, l4, t4, l5, t5,
        l6, t6, l7, t7, l8, t8, l9, t9, l10, t10, l11, t11,
        b1, title
    );
    b1.setOnAction(event->{
        try {
            primaryStage.setScene(createSearchPage(primaryStage));
        } catch (Exception ex) {
            Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    Scene viewscene=new Scene(entryLayout, 1000, 600);
    
    viewscene.setOnKeyPressed(event->{
      if(event.getCode()==KeyCode.ESCAPE){
          System.out.println("Escaped pressed");
          b1.fire();
      }
      if(event.getCode()==KeyCode.ENTER){
          System.out.println("Enter pressed");
          b1.fire();
      }
      
    });
    return viewscene;
 }

    private Scene Entrypage(Stage primaryStage){
            
        AnchorPane viewLayout=new AnchorPane();
        viewLayout.setStyle("-fx-background-color: #9FCFE1;");
        title=createLabel("STUDENT DATA",420,20);
        
        Button b1=new Button("UPLOAD");
        Button b2=createButton("BACK",50,80);
        
        b1.setStyle("-fx-background-color: #FF5773;");
        
        b1.setLayoutX(443);
        b1.setLayoutY(560);
        
        l1=createLabel("NAME",100+50,50);
        t1=createTextField("enter name",l1.getLayoutX(),90);
        
        l2=createLabel("BATCH NUMBER",l1.getLayoutX(),150);
        t2=createTextField("Enter Batchnumber",l1.getLayoutX(),l2.getLayoutY()+40);
        
        l3=createLabel("D.O.B",l1.getLayoutX(),l2.getLayoutY()+110);
        t3=createTextField("Enter DOB",l1.getLayoutX(),l3.getLayoutY()+40);
        
        l4=createLabel("DEPARTMENT",l1.getLayoutX(),l3.getLayoutY()+110);
        t4=createTextField("Enter Department",l1.getLayoutX(),l4.getLayoutY()+40);
        
        l5=createLabel("YEAR",l1.getLayoutX(),l4.getLayoutY()+110);
        t5=createTextField("Enter Year",l1.getLayoutX(),l5.getLayoutY()+40);
        
        
        l6=createLabel("MA3354",650,110);
        t6=createTextField("Enter Maths mark",l6.getLayoutX()+80,l6.getLayoutY()-5);
        
        l7=createLabel("CS3351",l6.getLayoutX(),l6.getLayoutY()+80);
        t7=createTextField("Enter DPCO marks",l7.getLayoutX()+80,l7.getLayoutY()-5);
        
        l8=createLabel("AD3491",l6.getLayoutX(),l7.getLayoutY()+80);
        t8=createTextField("Enter FDSA Marks",l8.getLayoutX()+80,l8.getLayoutY()-5);
        
        l9=createLabel("CS3391",l6.getLayoutX(),l8.getLayoutY()+80);
       t9=createTextField("Enter DAA",l6.getLayoutX()+80,l9.getLayoutY()-5);
        
        l10=createLabel("AD3351",l6.getLayoutX(),l9.getLayoutY()+80);
        t10=createTextField("Enter DAA",l10.getLayoutX()+80,l10.getLayoutY()-5);
        
        l11=createLabel("CW3301",l6.getLayoutX(),l10.getLayoutY()+80);
        t11=createTextField("Enter Economics mark",l11.getLayoutX()+80,l11.getLayoutY()-5);
        
        //Converting enchancing all label
        l1.setFont(fontify(l1.getText()));
        l2.setFont(fontify(l2.getText()));
        l3.setFont(fontify(l3.getText()));
        l4.setFont(fontify(l4.getText()));
        l5.setFont(fontify(l5.getText()));
        l6.setFont(fontify(l6.getText()));
        l7.setFont(fontify(l7.getText()));
        l8.setFont(fontify(l8.getText()));
        l9.setFont(fontify(l9.getText()));
        l10.setFont(fontify(l10.getText()));
        l11.setFont(fontify(l11.getText()));
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD,17));
        
        b1.setOnAction(event->{
            try {
                if(!isEmpty()){
                
                SetStudent ob=new SetStudent(t1.getText(),Integer.parseInt(t2.getText()),t3.getText(),t4.getText(),Integer.parseInt(t5.getText()),
                        Integer.parseInt(t6.getText()),Integer.parseInt(t7.getText()),Integer.parseInt(t8.getText()),
                        Integer.parseInt(t9.getText()),Integer.parseInt(t10.getText()),Integer.parseInt(t11.getText())
                    );
                }else
                    showAlert("kindly fill all boxes","Empty box");
            } catch (SQLException ex) {
                Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       b2.setOnAction(event->{
            try {
                primaryStage.setScene(createSearchPage(primaryStage));
            } catch (Exception ex) {
                Logger.getLogger(MyJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            }
       });
        viewLayout.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,b1,b2,title);
        
        return new Scene(viewLayout,1000,600);
    }
    private boolean authenticateUser(String username, String password) {

        return "admin".equals(username) && "admin".equals(password);
    }


    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void showInimation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
    private TextArea createTextArea(String text, double x, double y) {
        TextArea ta1 = new TextArea(text);
        ta1.setLayoutX(x);
        ta1.setLayoutY(y);
        ta1.setPrefWidth(100);
        ta1.setPrefHeight(25);
        ta1.setEditable(false);
        return ta1;
    }
    private Font fontify(String s)
    {
      return Font.font("Arial", FontWeight.BOLD,15);
    }
    
    private boolean isEmpty() {
      TextField arr[]={t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11};
      for(TextField i :arr){
          if(i.getText().equals(""))
              return true;
      }
      return false;
    }
    
    //Driver
      public static void main(String[] args) {
        launch(args);
    }

}
