
package qmm;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class QMM extends Application {
    BorderPane Admin_Root;
Scene UserDashScene,Deposit_Scene,Withdraw_Scene,Transfer_Scene,Details_scene,Admin_Scene;
   
Label lblIcon , lblWelcome,lblChangeCountry,lblLoginCenterError,lblFNameError,lblLNameError,
            lblSignUpUsernameError,lblSignUpPassError,lblSignUpPassConfirmError,lblSignUpEmailError,lblSignUpEmpty,lblSignUpEmpty2
            ,lblSignUpTitle,lblUserDashUser,lblUserDashWelcome,lblPointsDisplay,lblBalanceDisplay,lblLastLoginDisplay,
            lblDepositBalance,lblDepositBalanceDisplay,lblWithdrawBalance,lblWithdrawBalanceDisplay,lblTransferBalance,
            lblTransferBalanceDisplay,lblTotalDepositDisplay,lblTotalWithdrawnDisplay,lblLastTransactionDisplay,
            lblDetailsId,lblDetailsFN,lblDetailsLN,lblDetailsEM,lblDetailsUN,lblDetailsCN,lblDetailsBC,lblDetailsPS,lblAdminWelcome;
    
    TextField txtUserOrAdmin,txtFName,txtLName,txtSignUpUsername,txtEmail,txtDepositAmmount,
            txtWithdrawAmount,txtTransferTo,txtTransferAmount;

    PasswordField pswPassword,pswSignUpPassword,pswSignUpPasswordConfirmation;

    Button btnLogin,btnSignUp,btnSignUpSignUp,btnBackToLogin; 

    ComboBox cmbCountry,cmbSignUpCountry;    

    ImageView imgViewChoosenCountry,imgViewSignUpChoosenCountry;     
    
    Image imgJordan,imgPalestine,imgLebanon,imgUAE;
  
    
    ListView userDashboardLeftLV,userWithdrawLeftLV,userDepositLeftLV,userTransferLeftLV,userDetailsLeftLV;
    
    
    
    String CHECK_USERNAME="SELECT 1 FROM users WHERE username=?";
    
      String CHECK_EMAIL="SELECT 1 FROM users WHERE email=?";
    
     String INSERT_USER = "INSERT INTO users (first_name, last_name, email, username, password, country, role, balance, points) VALUES (?, ?, ?, ?, ?, ?, 'user', 0, 0)";

   User loggedInUser= new User(0," "," "," "," "," "," ","", 0.0, 0,"");
    
    
    @Override
    public void start(Stage primaryStage) {
    lblUserDashUser = new Label("");
    lblPointsDisplay = new Label("");
    lblBalanceDisplay = new Label("");
    lblLastLoginDisplay = new Label("");
    lblTotalDepositDisplay= new Label("");
    lblTotalWithdrawnDisplay= new Label("");
    lblLastTransactionDisplay= new Label("");
    lblDetailsId = new Label("");
    lblDetailsFN = new Label("");
    lblDetailsLN = new Label("");
    lblDetailsEM = new Label("");
  lblDetailsUN = new Label("");
 lblDetailsCN = new Label("");
   lblDetailsBC = new Label("");
   lblDetailsPS = new Label("");
   /////////////////////////////////////////////////////////////login scene////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   lblIcon = new Label("QMM");
   lblIcon.setId("qmm-label");
lblWelcome = new Label("Welcome to QMM Bank App");
lblWelcome.setId("welcome-label");

lblChangeCountry = new Label("Change Country : ");
lblChangeCountry.getStyleClass().add("login-center-Label");

pswPassword= new PasswordField();
pswPassword.setPromptText("password");
pswPassword.setId("login-center-field");

btnLogin= new Button("Login");
btnLogin.getStyleClass().add("login-center-button");
btnLogin.setDefaultButton(true);
btnSignUp = new Button("Sign Up");
btnSignUp.getStyleClass().add("login-center-button");

txtUserOrAdmin = new TextField();
txtUserOrAdmin.setPromptText("user/Admin Name");
txtUserOrAdmin.setPrefSize(20, 15);
txtUserOrAdmin.setId("login-center-field");

cmbCountry = new ComboBox();
cmbCountry.getItems().addAll("Jordan", "Palestine", "Lebanon", "UAE");
cmbCountry.setValue("Jordan"); 
   cmbCountry.getStyleClass().add("country-cmb");

lblLoginCenterError = new Label("");
lblLoginCenterError.setTextFill(Color.RED);
lblLoginCenterError.setFont(Font.font(17));

 imgJordan = new Image(getClass().getResource("/images/jordan.png").toExternalForm());

 imgPalestine = new Image(getClass().getResource("/images/palestine.png").toExternalForm());

 imgLebanon = new Image(getClass().getResource("/images/lebanon.png").toExternalForm());

 imgUAE = new Image(getClass().getResource("/images/UAE.png").toExternalForm());


Image imgExit = new Image(getClass().getResource("/images/exit.png").toExternalForm());
ImageView imgViewExit= new ImageView(imgExit);
        imgViewExit.getStyleClass().add("icon-button");


Image imgUser = new Image(getClass().getResource("/images/user.png").toExternalForm());
ImageView imgViewUser= new ImageView(imgUser);
imgViewUser.getStyleClass().add("login-center-icon");

Image imgPass = new Image(getClass().getResource("/images/padlock.png").toExternalForm());
ImageView imgViewPass= new ImageView(imgPass);
imgViewPass.getStyleClass().add("login-center-icon");

 imgViewChoosenCountry = new ImageView(imgJordan);

   HBox login_top = new HBox();
Region spacer1 = new Region();
Region spacer2 = new Region();
HBox.setHgrow(spacer1, Priority.ALWAYS);
HBox.setHgrow(spacer2, Priority.ALWAYS);

login_top.getChildren().addAll(lblIcon, spacer1, lblWelcome, spacer2, imgViewExit);
login_top.setAlignment(Pos.CENTER_LEFT);
login_top.setPadding(new Insets(20));  
   login_top.setId("login-top");  
    login_top.setAlignment(Pos.CENTER);
   HBox boxCountry = new HBox(lblChangeCountry,cmbCountry);
   
        GridPane login_center = new GridPane();
        login_center.setId("login-center");
    login_center.add(imgViewChoosenCountry,0,0);
    login_center.add(boxCountry,1,0);
    login_center.add(imgViewUser, 0, 1);
    login_center.add(txtUserOrAdmin, 1, 1);
    login_center.add(imgViewPass, 0, 2);
    login_center.add(pswPassword, 1, 2);
    login_center.add(btnLogin, 0, 3);
    login_center.add(btnSignUp, 1, 3);
    login_center.add(lblLoginCenterError,1,4);
    
        BorderPane login_root = new BorderPane();
        login_root.setId("login-root");
        
    login_root.setTop(login_top);
    login_root.setCenter(login_center);


login_center.setHgap(10);
login_center.setVgap(10);
boxCountry.setSpacing(10);
imgViewUser.setFitWidth(24);
imgViewUser.setFitHeight(24);
imgViewPass.setFitWidth(24);
imgViewPass.setFitHeight(24);
imgViewExit.setFitWidth(25);
imgViewExit.setFitHeight(25);
imgViewChoosenCountry.setFitHeight(30);
imgViewChoosenCountry.setFitWidth(30);

    Scene Login_scene = new Scene(login_root,1100,600);
   Login_scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

   
   cmbCountry.setOnAction(e->{
 
   String choice = cmbCountry.getSelectionModel().getSelectedItem().toString();
   if (choice.equalsIgnoreCase("Jordan")){
       imgViewChoosenCountry.setImage(imgJordan);
   }
   else if (choice.equalsIgnoreCase("palestine")){
       imgViewChoosenCountry.setImage(imgPalestine);
   }
    else if (choice.equalsIgnoreCase("lebanon")){
       imgViewChoosenCountry.setImage(imgLebanon);
   }
      else {
       imgViewChoosenCountry.setImage(imgUAE);
   }
   });
   
   imgViewExit.setOnMousePressed(e->System.exit(0));
   
   
   btnLogin.setOnAction(e->{
    String username = txtUserOrAdmin.getText().trim();
    String password = pswPassword.getText();
String country = cmbCountry.getSelectionModel().getSelectedItem().toString();
    lblLoginCenterError.setText("");

    if (username.isEmpty() || password.isEmpty()) {
        lblLoginCenterError.setText("Username and password are required!");
        return;
    }

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
        PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ? AND country = ?");
        st.setString(1, username);
        st.setString(2, password);
        st.setString(3, country);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            loggedInUser = new User(
        rs.getInt("id"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        rs.getString("email"),
        rs.getString("username"),
        rs.getString("password"),
        rs.getString("country"),
        rs.getString("role"),
        rs.getDouble("balance"),
        rs.getInt("points"),
        rs.getString("last_login")
    ); 
            
            String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
            String lastLogin = rs.getString("last_login");
            String role = rs.getString("role");  
lblUserDashUser.setText(fullName);
            lblUserDashUser.getStyleClass().add("userdash-welcome-label");
lblPointsDisplay.setText(String.valueOf(loggedInUser.getPoints())+" pts");
lblBalanceDisplay.setText(String.valueOf(loggedInUser.getBalance())+" JOD");
lblLastLoginDisplay.setText(loggedInUser.getLastLogin());
PreparedStatement update = conn.prepareStatement("UPDATE users SET last_login = NOW() WHERE id = ?");
            update.setInt(1,loggedInUser.getId());
            update.executeUpdate();
            int userId = loggedInUser.getId();

try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");

   
    PreparedStatement lastTxStmt = con.prepareStatement(
        "SELECT * FROM transactions WHERE user_id = ? OR target_user_id = ? ORDER BY time DESC LIMIT 1"
    );
    lastTxStmt.setInt(1, userId);
    lastTxStmt.setInt(2, userId);

    ResultSet rsLastTx = lastTxStmt.executeQuery();
    if (rsLastTx.next()) {
        String type = rsLastTx.getString("type");
        double amount = rsLastTx.getDouble("amount");
        String date = rsLastTx.getString("time");

     
        if(type.equals("deposit")){
            lblLastTransactionDisplay.setText("Depositted: "+amount+" JOD");
        }
        else if(type.equals("withdraw")){
            lblLastTransactionDisplay.setText("Withdrawn: "+amount+" JOD");
        }
        else {
            lblLastTransactionDisplay.setText("transfer: "+amount+" JOD");
         }
           
        
         } else {
        lblLastTransactionDisplay.setText("No transactions yet");
       
    }

   PreparedStatement totalWithdrawStmt = conn.prepareStatement(
        "SELECT SUM(amount) AS total_withdrawn FROM transactions WHERE user_id = ? AND type = 'withdraw'"
    );
    totalWithdrawStmt.setInt(1, userId);
    ResultSet rsWithdraw = totalWithdrawStmt.executeQuery();
    if (rsWithdraw.next()) {
        double totalWithdrawn = rsWithdraw.getDouble("total_withdrawn");
        if (rsWithdraw.wasNull()) totalWithdrawn = 0.0;
        lblTotalWithdrawnDisplay.setText(String.format("%.2f", totalWithdrawn) + " JOD");
    }

    PreparedStatement totalDepositStmt = conn.prepareStatement(
        "SELECT SUM(amount) AS total_deposited FROM transactions WHERE user_id = ? AND type = 'deposit'"
    );
    totalDepositStmt.setInt(1, userId);
    ResultSet rsDeposit = totalDepositStmt.executeQuery();
    if (rsDeposit.next()) {
        double totalDeposited = rsDeposit.getDouble("total_deposited");
        if (rsDeposit.wasNull()) totalDeposited = 0.0;
        lblTotalDepositDisplay.setText(String.format("%.2f", totalDeposited) + " JOD");
    }

    conn.close();

} catch (SQLException ex) {
    System.out.println("Failed to load transaction summary: " + ex.getMessage());
}

loggedInUser.setLastLogin(java.time.LocalDateTime.now().toString());

            if (role.equalsIgnoreCase("admin")) {
                lblAdminWelcome.setText("Welcome, "+loggedInUser.getFirstName()+" "+loggedInUser.getLastName());
        primaryStage.setScene(Admin_Scene);
        primaryStage.setTitle(role);
} 
            else {
                    primaryStage.setScene(UserDashScene);
        primaryStage.setTitle("User Dashboard");

            }

        } else {
            lblLoginCenterError.setText("Invalid Username, password or country.");
        }

    }
    catch (Exception b) {
        lblLoginCenterError.setText("Database error: " + b.getMessage());
    }

    
    
    
    

    
   });
   
   
   ////////////////////////Sign Up Scene////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   txtFName= new TextField();
   txtFName.setPromptText("First name");
   txtFName.getStyleClass().add("signup-field");
   
   txtLName= new TextField();
   txtLName.setPromptText("Last name");
   txtLName.getStyleClass().add("signup-field");
   
   txtEmail= new TextField();
   txtEmail.setPromptText("Email");
   txtEmail.getStyleClass().add("signup-field");

   
   txtSignUpUsername= new TextField();
   txtSignUpUsername.setPromptText("Username");
   txtSignUpUsername.getStyleClass().add("signup-field");

   pswSignUpPassword = new PasswordField();
   pswSignUpPassword.setPromptText("New Password");
   pswSignUpPassword.getStyleClass().add("signup-field");
   
   pswSignUpPasswordConfirmation = new PasswordField();
  pswSignUpPasswordConfirmation.setPromptText("Confirm Password");
  pswSignUpPasswordConfirmation.getStyleClass().add("signup-field");
  
cmbSignUpCountry= new ComboBox();
cmbSignUpCountry.getItems().addAll("Jordan", "Palestine", "Lebanon", "UAE");
cmbSignUpCountry.setValue("Jordan"); 
cmbSignUpCountry.getStyleClass().add("signup-country-cmb");
cmbSignUpCountry.setPrefWidth(220);
Image imgName = new Image(getClass().getResource("/images/name.png").toExternalForm());
Image imgEmail = new Image(getClass().getResource("/images/email.png").toExternalForm());
   
   ImageView imgViewUserSignUp = new ImageView(imgUser);
   imgViewUserSignUp.getStyleClass().add("signup-icon");
   
   ImageView imgViewPassSignUp = new ImageView(imgPass);
imgViewPassSignUp.getStyleClass().add("signup-icon");
   
   ImageView imgViewFName = new ImageView(imgName);
   imgViewFName.getStyleClass().add("signup-icon");
   
   ImageView imgViewLName = new ImageView(imgName);
   imgViewLName.getStyleClass().add("signup-icon");
   
   ImageView imgViewPassConfirm = new ImageView(imgPass);
   imgViewPassConfirm.getStyleClass().add("signup-icon");
   
   ImageView imgViewEmail = new ImageView(imgEmail);
   imgViewEmail.getStyleClass().add("signup-icon");
   
   ImageView imgViewChoosenCountrySignUp = new ImageView(imgJordan);
   imgViewChoosenCountrySignUp.getStyleClass().add("signup-icon");
   
   
   lblSignUpTitle = new Label("Create Your Account");
   lblSignUpTitle.setId("signup-title");
   lblSignUpTitle.setAlignment(Pos.CENTER);
   lblSignUpTitle.setPadding(new Insets(20,0,10,0));
   
   
   btnSignUpSignUp = new Button("Sign Up");
   btnSignUpSignUp.getStyleClass().add("signup-button");
   
   btnBackToLogin = new Button("Back to Login");
   btnBackToLogin.getStyleClass().add("signup-button");
   
   lblFNameError= new Label("");
   lblFNameError.getStyleClass().add("signup-error");
   
   lblSignUpEmailError= new Label("");
   lblSignUpEmailError.getStyleClass().add("signup-error");
   
   lblLNameError= new Label("");
   lblLNameError.getStyleClass().add("signup-error");
   
   lblSignUpPassError= new Label("");
   lblSignUpPassError.getStyleClass().add("signup-error");
   
   lblSignUpPassConfirmError= new Label("");
   lblSignUpPassConfirmError.getStyleClass().add("signup-error");
   
   lblSignUpUsernameError= new Label("");
   lblSignUpUsernameError.getStyleClass().add("signup-error");
   
   lblSignUpEmpty= new Label("");
   lblSignUpEmpty2 = new Label("");
   HBox SUH1 = new HBox(50,imgViewFName,txtFName,lblFNameError);
   SUH1.setAlignment(Pos.CENTER);
   
   HBox SUH2= new HBox(50, imgViewLName,txtLName,lblLNameError);
   SUH2.setAlignment(Pos.CENTER);
   
   HBox SUH3= new HBox(50, imgViewEmail,txtEmail,lblSignUpEmailError);
 SUH3.setAlignment(Pos.CENTER);
   
   HBox SUH4= new HBox(50, imgViewUserSignUp,txtSignUpUsername,lblSignUpUsernameError);
   SUH4.setAlignment(Pos.CENTER);
   
   
   HBox SUH5= new HBox(50, imgViewPassSignUp,pswSignUpPassword,lblSignUpPassError);
  SUH5.setAlignment(Pos.CENTER);
   
   
   
   HBox SUH6= new HBox(50, imgViewPassConfirm,pswSignUpPasswordConfirmation,lblSignUpPassConfirmError);
  SUH6.setAlignment(Pos.CENTER);
   
   
   
   HBox SUH7= new HBox(50, imgViewChoosenCountrySignUp,cmbSignUpCountry,lblSignUpEmpty);
  SUH7.setAlignment(Pos.CENTER);
   
   
   
   HBox SUH8= new HBox(50,btnSignUpSignUp,btnBackToLogin,lblSignUpEmpty2);
   SUH8.setAlignment(Pos.CENTER);
   
   
   
   VBox SignUp_root= new VBox(20,lblSignUpTitle, SUH1,SUH2,SUH3,SUH4,SUH5,SUH6,SUH7,SUH8);
  BorderPane SignUp_Final_root = new BorderPane();
  
  SignUp_Final_root.setCenter(SignUp_root);
   SignUp_root.setAlignment(Pos.CENTER);
  
   SignUp_root.setPrefHeight(Double.MAX_VALUE);
SignUp_root.setPrefWidth(Double.MAX_VALUE);
   
   Scene SignUp_Scene = new Scene(SignUp_Final_root,1100,600);
   SignUp_Scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
SignUp_Final_root.setId("signup-root");
   
btnSignUp.setOnAction(e->{
  primaryStage.setScene(SignUp_Scene);
    primaryStage.setTitle("Sign Up");
   });
   
   
 imgViewFName.setFitWidth(25);   
imgViewFName.setFitHeight(25);
imgViewFName.setPreserveRatio(true); 
   
 imgViewLName.setFitWidth(25);   
imgViewLName.setFitHeight(25);
imgViewLName.setPreserveRatio(true); 
   
   
    imgViewChoosenCountrySignUp.setFitWidth(25);   
imgViewChoosenCountrySignUp.setFitHeight(25);
imgViewChoosenCountrySignUp.setPreserveRatio(true); 
   

    imgViewEmail.setFitWidth(25);   
imgViewEmail.setFitHeight(25);
imgViewEmail.setPreserveRatio(true); 
   

 imgViewPassSignUp.setFitWidth(25);   
imgViewPassSignUp.setFitHeight(25);
imgViewPassSignUp.setPreserveRatio(true); 
   

    imgViewPassConfirm.setFitWidth(25);   
imgViewPassConfirm.setFitHeight(25);
imgViewPassConfirm.setPreserveRatio(true); 
   


         imgViewUserSignUp.setFitWidth(25);   
imgViewUserSignUp.setFitHeight(25);
imgViewUserSignUp.setPreserveRatio(true); 
   

   btnBackToLogin.setOnAction(e->{
  primaryStage.setScene(Login_scene);
   primaryStage.setTitle("Login / Sign Up");
   });
   
   cmbSignUpCountry.setOnAction(e->{
   
   String choice = cmbSignUpCountry.getSelectionModel().getSelectedItem().toString();
   if (choice.equalsIgnoreCase("Jordan")){
       imgViewChoosenCountrySignUp.setImage(imgJordan);
   }
   else if (choice.equalsIgnoreCase("palestine")){
       imgViewChoosenCountrySignUp.setImage(imgPalestine);
   }
    else if (choice.equalsIgnoreCase("lebanon")){
       imgViewChoosenCountrySignUp.setImage(imgLebanon);
   }
      else {
       imgViewChoosenCountrySignUp.setImage(imgUAE);
   }
    

   });
   btnSignUpSignUp.setOnAction(e->{
    String first = txtFName.getText().trim();
    String last = txtLName.getText().trim();
    String email = txtEmail.getText().trim();
    String username = txtSignUpUsername.getText().trim();
    String password = pswSignUpPassword.getText();
    String confirm = pswSignUpPasswordConfirmation.getText();
    String country = cmbSignUpCountry.getSelectionModel().getSelectedItem().toString(); 
 
    
lblFNameError.setText("");
lblLNameError.setText("");
lblSignUpEmailError.setText("");
lblSignUpUsernameError.setText("");
lblSignUpPassError.setText("");
lblSignUpPassConfirmError.setText("");

    
    if (first.isEmpty()){
        lblFNameError.setText("First name is required !");
    return;
    }
            
            if(last.isEmpty()){
    lblLNameError.setText("Last name is required !");
    return;
                 }
                
             if(email.isEmpty()){
    lblSignUpEmailError.setText("Email is required !");
    return;
                  }
                    
    if(username.isEmpty()){
    lblSignUpUsernameError.setText("Username is required !");
    return;
         }
        
        if(password.isEmpty()){
    lblSignUpPassError.setText("Password is required !");
    return;
         }
            
            if(confirm.isEmpty()){
    lblSignUpPassConfirmError.setText("Confirm your password please !");
    return;
            }
                

if (!password.equals(confirm)) {
lblSignUpPassConfirmError.setText("PASSWORDS DO NOT MATCH !");
         return;
}

if (username.length() < 4) {
    lblSignUpUsernameError.setText("Username must be at least 4 characters !");
  return;
}

if (password.length() < 8) {
   lblSignUpPassError.setText("Password must be at least 8 characters !");
    return;
}

if (!email.contains("@") || !email.contains(".")) {
 lblSignUpEmailError.setText("Enter a valid email !");
    return;
}
  try{
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb","root","");
  PreparedStatement st = conn.prepareStatement(CHECK_USERNAME);
  st.setString(1, username);
  ResultSet checkUsernameResult = st.executeQuery();
  
  if(checkUsernameResult.next()){
      Alert alert = new Alert(AlertType.ERROR,"Username is already taken !");
      alert.show();
      return;
  }
  }  
catch(Exception ex){
      Alert alert = new Alert(AlertType.ERROR,"DB ERROR :"+ex.toString());
      alert.show();
      }
  
  
  try{
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb","root","");
  PreparedStatement st = conn.prepareStatement(CHECK_EMAIL);
  st.setString(1,email);
  ResultSet checkEmailResult = st.executeQuery();
  
  if(checkEmailResult.next()){
      Alert alert = new Alert(AlertType.ERROR,"Account with this email already exists !");
      alert.show();
      return;
  }
  
  
  
  }  
catch(Exception ex){
      Alert alert = new Alert(AlertType.ERROR,"DB ERROR :"+ex.toString());
      alert.show();
      }
    
  try {
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");

    PreparedStatement St = conn.prepareStatement(INSERT_USER);
    St.setString(1, first);
    St.setString(2, last);
    St.setString(3, email);
    St.setString(4, username);
    St.setString(5, password); 
    St.setString(6, country);

     St.executeUpdate();

 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account created successfully!");
alert.show();
txtFName.clear();
txtLName.clear();
txtEmail.clear();
txtSignUpUsername.clear();
pswSignUpPassword.clear();
pswSignUpPasswordConfirmation.clear();
  }
  
  catch (SQLException ex) {
    Alert alert = new Alert(Alert.AlertType.ERROR, "DB INSERT ERROR: " + ex.getMessage());
    alert.show();
}
   
   
   
   }
   );
   btnSignUpSignUp.setDefaultButton(true);
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
      /////////////////////////////////////////////////////////////////////welcome scene/////////////////////////////////////////////////////////////////////////////////////////////
        StackPane Welcome_root = new StackPane();
Welcome_root.setId("welcome-root");

Image imgLogo =  new Image(getClass().getResource("/images/logo.png").toExternalForm());
ImageView logo = new ImageView(imgLogo);
logo.setFitHeight(400);
logo.setPreserveRatio(true);


Welcome_root.getChildren().add(logo);



FadeTransition fadeIn = new FadeTransition(Duration.seconds(4), logo);
fadeIn.setFromValue(0);
fadeIn.setToValue(1);
fadeIn.play();

Scene welcomeScene = new Scene(Welcome_root, 1100, 600); 

PauseTransition pause = new PauseTransition(Duration.seconds(3.0));
pause.setOnFinished(e ->{
    primaryStage.setScene(Login_scene);
   primaryStage.setTitle("Login");
});  
pause.play();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////User dashboard///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Image imgLogout =  new Image(getClass().getResource("/images/logout.png").toExternalForm());
ImageView imgViewLogout = new ImageView(imgLogout);
imgViewLogout.setFitHeight(32);
imgViewLogout.setPreserveRatio(true);
imgViewLogout.getStyleClass().add("logout-icon");
imgViewLogout.setOnMousePressed(e->{ 
    
    Alert alert = new Alert(AlertType.CONFIRMATION,"Sure you want to logout ?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
  txtUserOrAdmin.clear();
  pswPassword.clear();
            primaryStage.setScene(Login_scene);
  primaryStage.setTitle("Login");
        }
        else{}
 
});

Image imgFeedBack = new Image(getClass().getResource("/images/feedback.png").toExternalForm());
ImageView imgViewFeedBack = new ImageView(imgFeedBack);
imgViewFeedBack.setFitHeight(32);
imgViewFeedBack.setPreserveRatio(true);
imgViewFeedBack.getStyleClass().add("feedback-icon");

imgViewFeedBack.setOnMousePressed(e->showFeedbackDialog(loggedInUser.getUsername()));

ImageView imgViewUserDashboardLogo = new ImageView(new Image(getClass().getResource("/images/userdashlogo.png").toExternalForm()));
imgViewUserDashboardLogo.setCursor(Cursor.HAND);
imgViewUserDashboardLogo.setOnMousePressed(e->{
primaryStage.setScene(UserDashScene);
userDashboardLeftLV.getSelectionModel().clearSelection();
});
imgViewUserDashboardLogo.setId("dashboard-logo");
imgViewUserDashboardLogo.setFitHeight(150);
imgViewUserDashboardLogo.setPreserveRatio(true);
HBox UserDashboardTop = new HBox();
UserDashboardTop.setAlignment(Pos.CENTER);

Region spacerLeft = new Region();
Region spacerRight = new Region();

HBox.setHgrow(spacerLeft, Priority.ALWAYS);
HBox.setHgrow(spacerRight, Priority.ALWAYS);


UserDashboardTop.getChildren().addAll(imgViewLogout,spacerLeft,imgViewUserDashboardLogo,spacerRight,imgViewFeedBack);
UserDashboardTop.setId("userdashboard-top");
UserDashboardTop.setPrefHeight(30);
UserDashboardTop.setPadding(new Insets(25, 15, 5, 25));
///////////////////////////////////////////////////

String[] UserLeftLVOptions = {"Deposit","","Withdraw","","Transfer","","Details","","Delete Account"};
 userDashboardLeftLV = new ListView();
userDashboardLeftLV.getItems().addAll((Object[])UserLeftLVOptions);

userDashboardLeftLV.setPrefWidth(180);
userDashboardLeftLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
userDashboardLeftLV.setOrientation(Orientation.VERTICAL);
userDashboardLeftLV.setId("dashboard-listview");

userDashboardLeftLV.setOnMousePressed(e->{
if(userDashboardLeftLV.getSelectionModel().getSelectedItem().toString().equals("Deposit")){
    primaryStage.setScene(Deposit_Scene);
    userDepositLeftLV.getSelectionModel().select(0);
    txtDepositAmmount.requestFocus();
    lblDepositBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");
userDashboardLeftLV.getSelectionModel().select(0);
}
if(userDashboardLeftLV.getSelectionModel().getSelectedItem().toString().equals("Withdraw")){
    primaryStage.setScene(Withdraw_Scene);
    userWithdrawLeftLV.getSelectionModel().select(2);
    txtWithdrawAmount.requestFocus();
    userDashboardLeftLV.getSelectionModel().select(2);
    lblWithdrawBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}

if(userDashboardLeftLV.getSelectionModel().getSelectedItem().toString().equals("Transfer")){
    primaryStage.setScene(Transfer_Scene);
    userWithdrawLeftLV.getSelectionModel().select(4);
    txtWithdrawAmount.requestFocus();
    userTransferLeftLV.getSelectionModel().select(4);
    lblTransferBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}

if(userDashboardLeftLV.getSelectionModel().getSelectedItem().toString().equals("Details")){
    primaryStage.setScene(Details_scene);
    txtWithdrawAmount.requestFocus();
    userDetailsLeftLV.getSelectionModel().select(6);
  lblDetailsId.setText("ID : "+loggedInUser.getId());
  lblDetailsFN.setText("First name : "+loggedInUser.getFirstName());
lblDetailsLN.setText("Last name : "+loggedInUser.getLastName());
lblDetailsEM.setText("Email : "+loggedInUser.getEmail());
lblDetailsUN.setText("Username : "+loggedInUser.getUsername());
lblDetailsCN.setText("Country : "+loggedInUser.getCountry());
lblDetailsBC.setText("Balance : "+loggedInUser.getBalance()+" JOD");
lblDetailsPS.setText("Points : "+loggedInUser.getPoints());
}


if(userDashboardLeftLV.getSelectionModel().getSelectedItem().toString().equals("Delete Account")){
    showDeleteAccountDialog(loggedInUser.getUsername(), primaryStage);
}



});
////////////////////////////////////////////////
lblUserDashWelcome = new Label("Welcome,");
lblUserDashWelcome.setAlignment(Pos.CENTER);
lblUserDashWelcome.getStyleClass().add("userdash-welcome-label");

VBox cardPoints = new VBox();
Label lblPoints = new Label("Points");
lblPoints.getStyleClass().add("dashboard-card-title");
lblPointsDisplay.getStyleClass().add("userdashboard-card-value");

Image imgPoints =  new Image(getClass().getResource("/images/points.png").toExternalForm());
ImageView imgViewPoints = new ImageView(imgPoints);
imgViewPoints.setFitHeight(30);
imgViewPoints.setPreserveRatio(true);

Button btnRedeemPoints = new Button("Redeem");
btnRedeemPoints.getStyleClass().add("userdash-redeem-button");

btnRedeemPoints.setOnAction(e->{
    if (loggedInUser.getPoints() >= 100) {
        int newPoints = loggedInUser.getPoints() - 100;
        double newBalance = loggedInUser.getBalance() + 1.0;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET points = ?, balance = ? WHERE id = ?");
            stmt.setInt(1, newPoints);
            stmt.setDouble(2, newBalance);
            stmt.setInt(3, loggedInUser.getId());
            stmt.executeUpdate();

            loggedInUser.setPoints(newPoints);
            loggedInUser.setBalance(newBalance);

            lblPointsDisplay.setText(newPoints + " pts");
            lblBalanceDisplay.setText(String.format("%.2f", newBalance) + " JOD");

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Redeemed");
            success.setHeaderText(null);
            success.setContentText("You have redeemed 100 points for 1 JOD!");
            success.showAndWait();

        } catch (SQLException ex) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText(null);
            error.setContentText("Failed to redeem: " + ex.getMessage());
            error.showAndWait();
        }

    } else {
        Alert notEnough = new Alert(Alert.AlertType.WARNING);
        notEnough.setTitle("Not Enough Points");
        notEnough.setHeaderText(null);
        notEnough.setContentText("You need at least 100 points to redeem.");
        notEnough.showAndWait();
    }


});


cardPoints.getChildren().addAll(imgViewPoints,lblPoints,lblPointsDisplay,btnRedeemPoints);
cardPoints.getStyleClass().add("userdashboard-card");

///////////////////////////////////////

VBox cardBalance = new VBox();
Label lblBalance = new Label("Balance");
lblBalance.getStyleClass().add("dashboard-card-title");
lblBalanceDisplay.getStyleClass().add("userdashboard-card-value");
Image imgBalance =  new Image(getClass().getResource("/images/wallet.png").toExternalForm());
ImageView imgViewBalance = new ImageView(imgBalance);
imgViewBalance.setFitHeight(30);
imgViewBalance.setPreserveRatio(true);

cardBalance.getChildren().addAll(imgViewBalance,lblBalance,lblBalanceDisplay);
cardBalance.getStyleClass().add("userdashboard-card");

VBox cardTransactions = new VBox();
Label lblLastTransaction = new Label("Last Transaction");
lblLastTransaction.getStyleClass().add("dashboard-card-title");
lblLastTransactionDisplay.getStyleClass().add("userdashboard-card-value");
Label lblTotalDeposit = new Label("Total Deposit");
lblTotalDeposit.getStyleClass().add("dashboard-card-title");

lblTotalDepositDisplay.getStyleClass().add("userdashboard-card-value");
Label lblTotalwithdrawn = new Label("Total Withdrawn");
lblTotalwithdrawn.getStyleClass().add("dashboard-card-title");
lblTotalWithdrawnDisplay.getStyleClass().add("userdashboard-card-value");


Image imgTrans =   new Image(getClass().getResource("/images/transactions.png").toExternalForm());
ImageView imgViewTrans = new ImageView(imgTrans);
imgViewTrans.setFitHeight(25);
imgViewTrans.setPreserveRatio(true);


cardTransactions.getChildren().addAll(imgViewTrans,lblLastTransaction,lblLastTransactionDisplay,lblTotalDeposit,
        lblTotalDepositDisplay,lblTotalwithdrawn,lblTotalWithdrawnDisplay);
cardTransactions.getStyleClass().add("userdashboard-card");




VBox cardLastLogin = new VBox();
Label lblLastLogin = new Label("Last Login");
lblLastLogin.getStyleClass().add("dashboard-card-title");
lblLastLoginDisplay.getStyleClass().add("userdashboard-card-value");
Image imgLastLogin =  new Image(getClass().getResource("/images/lastlogin.png").toExternalForm());
ImageView imgViewLastLogin = new ImageView(imgLastLogin);
imgViewLastLogin.setFitHeight(25);
imgViewLastLogin.setPreserveRatio(true);

cardLastLogin.getChildren().addAll(imgViewLastLogin,lblLastLogin,lblLastLoginDisplay);
cardLastLogin.getStyleClass().add("userdashboard-card");










HBox CardsBox = new HBox();
CardsBox.getChildren().addAll(cardPoints,cardBalance,cardTransactions,cardLastLogin);
CardsBox.getStyleClass().add("hbox-cards");
CardsBox.setAlignment(Pos.CENTER);
VBox UserDashCenter = new VBox();
UserDashCenter.getChildren().addAll(lblUserDashWelcome,lblUserDashUser,CardsBox);
UserDashCenter.setAlignment(Pos.CENTER);
BorderPane userDashRoot = new BorderPane();
userDashRoot.setTop(UserDashboardTop);
userDashRoot.setLeft(userDashboardLeftLV);
userDashRoot.setCenter(UserDashCenter);
userDashRoot.setId("Userdashboard-root");



 UserDashScene = new Scene(userDashRoot,1100, 600);
  UserDashScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////User_deposit_scene//////////////////////////////////////////////////////////////////////////////////////////////////////////

Image imgDepositLogout =  new Image(getClass().getResource("/images/logout.png").toExternalForm());
ImageView imgViewDepositLogout = new ImageView(imgLogout);
imgViewDepositLogout.setFitHeight(32);
imgViewDepositLogout.setPreserveRatio(true);
imgViewDepositLogout.getStyleClass().add("logout-icon");
imgViewDepositLogout.setOnMousePressed(e->{ 
    
    Alert alert = new Alert(AlertType.CONFIRMATION,"Sure you want to logout ?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
  txtUserOrAdmin.clear();
  pswPassword.clear();
            primaryStage.setScene(Login_scene);
  primaryStage.setTitle("Login");
        }
        else{}
 });
        


Image imgDepositFeedBack = new Image(getClass().getResource("/images/feedback.png").toExternalForm());
ImageView imgViewDepositFeedBack = new ImageView(imgFeedBack);
imgViewDepositFeedBack.setFitHeight(32);
imgViewDepositFeedBack.setPreserveRatio(true);
imgViewDepositFeedBack.getStyleClass().add("feedback-icon");
imgViewDepositFeedBack.setOnMousePressed(e->showFeedbackDialog(loggedInUser.getUsername()));

ImageView imgViewUserDepositLogo = new ImageView(new Image(getClass().getResource("/images/userdashlogo.png").toExternalForm()));
imgViewUserDepositLogo.setCursor(Cursor.HAND);

imgViewUserDepositLogo.setOnMousePressed(e->{
primaryStage.setScene(UserDashScene);
lblPointsDisplay.setText(String.valueOf(loggedInUser.getPoints())+" pts");
lblBalanceDisplay.setText(String.valueOf(loggedInUser.getBalance())+" JOD");
userDashboardLeftLV.getSelectionModel().clearSelection();
});


imgViewUserDepositLogo.setId("dashboard-logo");
imgViewUserDepositLogo.setFitHeight(150);
imgViewUserDepositLogo.setPreserveRatio(true);
HBox UserDepositTop = new HBox();
UserDepositTop.setAlignment(Pos.CENTER);

Region DepositspacerLeft = new Region();
Region DepositspacerRight = new Region();

HBox.setHgrow(DepositspacerLeft, Priority.ALWAYS);
HBox.setHgrow(DepositspacerRight, Priority.ALWAYS);


UserDepositTop.getChildren().addAll(imgViewDepositLogout,DepositspacerLeft,imgViewUserDepositLogo,DepositspacerRight,imgViewDepositFeedBack);
UserDepositTop.setId("userdashboard-top");
UserDepositTop.setPrefHeight(30);
UserDepositTop.setPadding(new Insets(25, 15, 5, 25));

//////////////////////////////////////////////////////

String[] UserDepositLeftLVOptions = {"Deposit","","Withdraw","","Transfer","","Details","","Delete Account"};
 userDepositLeftLV = new ListView();
userDepositLeftLV.getItems().addAll((Object[])UserDepositLeftLVOptions);

userDepositLeftLV.setPrefWidth(180);
userDepositLeftLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
userDepositLeftLV.setOrientation(Orientation.VERTICAL);
userDepositLeftLV.setId("dashboard-listview");

userDepositLeftLV.setOnMousePressed(e->{
if(userDepositLeftLV.getSelectionModel().getSelectedItem().toString().equals("Deposit")){
    primaryStage.setScene(Deposit_Scene);
    userDepositLeftLV.getSelectionModel().select(0);
    txtDepositAmmount.requestFocus();
    lblDepositBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}
if(userDepositLeftLV.getSelectionModel().getSelectedItem().toString().equals("Withdraw")){
    primaryStage.setScene(Withdraw_Scene);
    userWithdrawLeftLV.getSelectionModel().select(2);
    txtWithdrawAmount.requestFocus();
    lblWithdrawBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}

if(userDepositLeftLV.getSelectionModel().getSelectedItem().toString().equals("Transfer")){
    primaryStage.setScene(Transfer_Scene);
    txtTransferTo.requestFocus();
    userTransferLeftLV.getSelectionModel().select(4);
    lblTransferBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}


if(userDepositLeftLV.getSelectionModel().getSelectedItem().toString().equals("Details")){
    primaryStage.setScene(Details_scene);
    txtWithdrawAmount.requestFocus();
    userDetailsLeftLV.getSelectionModel().select(6);
    lblDetailsId.setText("ID : "+loggedInUser.getId());
  lblDetailsFN.setText("First name : "+loggedInUser.getFirstName());
lblDetailsLN.setText("Last name : "+loggedInUser.getLastName());
lblDetailsEM.setText("Email : "+loggedInUser.getEmail());
lblDetailsUN.setText("Username : "+loggedInUser.getUsername());
lblDetailsCN.setText("Country : "+loggedInUser.getCountry());
lblDetailsBC.setText("Balance : "+loggedInUser.getBalance()+" JOD");
lblDetailsPS.setText("Points : "+loggedInUser.getPoints());

}

if(userDepositLeftLV.getSelectionModel().getSelectedItem().toString().equals("Delete Account")){
    showDeleteAccountDialog(loggedInUser.getUsername(), primaryStage);
}

});
///////////////////////////////////////////////////////
lblDepositBalance = new Label("Balance : ");
lblDepositBalance.getStyleClass().add("deposit-label");
lblDepositBalanceDisplay = new Label(String.valueOf(loggedInUser.getBalance())+" JOD");
lblDepositBalanceDisplay.getStyleClass().add("deposit-value");             
HBox HboxDepositBalance = new HBox();
HboxDepositBalance.getChildren().addAll(lblDepositBalance,lblDepositBalanceDisplay);

Image imgDepositAmmount =  new Image(getClass().getResource("/images/deposit.png").toExternalForm());
ImageView imgViewDepositAmmount = new ImageView(imgDepositAmmount);
imgViewDepositAmmount.setFitHeight(40);
imgViewDepositAmmount.setPreserveRatio(true);

txtDepositAmmount= new TextField();
txtDepositAmmount.getStyleClass().add("deposit-field");

txtDepositAmmount.setPromptText("Deposit Amount");
Button btnDeposit = new Button("Deposit");
btnDeposit.getStyleClass().add("deposit-button");
HBox hbtnDeposit= new HBox(btnDeposit);
HBox HboxDepositAmmount = new HBox(10);
HboxDepositAmmount.getChildren().addAll(imgViewDepositAmmount,txtDepositAmmount);

VBox Deposit_Center = new VBox(60);
Deposit_Center.getChildren().addAll(HboxDepositBalance,HboxDepositAmmount,hbtnDeposit);
Deposit_Center.setPadding(new Insets(100));
HboxDepositBalance.setAlignment(Pos.CENTER);
HboxDepositAmmount.setAlignment(Pos.CENTER);
hbtnDeposit.setAlignment(Pos.CENTER);




BorderPane Deposit_Root= new BorderPane();
Deposit_Root.setLeft(userDepositLeftLV);
Deposit_Root.setTop(UserDepositTop);
Deposit_Root.setCenter(Deposit_Center);
Deposit_Root.setId("trans_root");
Deposit_Scene = new Scene(Deposit_Root,1100,600);
Deposit_Scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());


btnDeposit.setOnAction(e -> {
    String input = txtDepositAmmount.getText().trim();

    if (input.isEmpty()) {
       Alert alert = new Alert(AlertType.ERROR, "Please enter an amount.");
       alert.show();
       return;
    }

    double amount;
    try {
        amount = Double.parseDouble(input);
        if (amount <= 0) {
         Alert alert = new Alert(AlertType.ERROR, "Amount must be greater than zero.");
         alert.show();
         return;
        }
    } catch (NumberFormatException ex) {
       Alert alert = new Alert(AlertType.ERROR, "Invalid number format");
       alert.show();
        return;
    }

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");

        double newBalance = loggedInUser.getBalance() + amount;
        PreparedStatement updateUser = conn.prepareStatement("UPDATE users SET balance = ?, points = points + ? WHERE id = ?");
        int pointsEarned = (int)(amount / 10); 
        updateUser.setDouble(1, newBalance);
        updateUser.setInt(2, pointsEarned);
        updateUser.setInt(3, loggedInUser.getId());
        updateUser.executeUpdate();

       
        loggedInUser.setBalance(newBalance);
        loggedInUser.setPoints(loggedInUser.getPoints() + pointsEarned);

        
        PreparedStatement insertTransaction = conn.prepareStatement(
            "INSERT INTO transactions (user_id, amount, type, time) VALUES (?, ?, 'deposit', NOW())");
        insertTransaction.setInt(1, loggedInUser.getId());
        insertTransaction.setDouble(2, amount);
        insertTransaction.executeUpdate();

        lblDepositBalanceDisplay.setText(String.format("%.2f", newBalance)+" JOD");

        Alert alert = new Alert(AlertType.INFORMATION, "Deposit successful!");
    alert.setTitle("Success");
alert.setHeaderText(null);
        alert.show();
        txtDepositAmmount.clear();
    } catch (SQLException ex) {
        Alert alert = new Alert(AlertType.ERROR, "Database error : "+ex.toString());
    alert.show();
     }
});
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////User withdraw///////////////////////////////////////////////////////////////////////////////////////////////////////

Image imgWithdrawLogout = new Image(getClass().getResource("/images/logout.png").toExternalForm());
ImageView imgViewWithdrawLogout = new ImageView(imgWithdrawLogout);
imgViewWithdrawLogout.setFitHeight(32);
imgViewWithdrawLogout.setPreserveRatio(true);
imgViewWithdrawLogout.getStyleClass().add("logout-icon");
imgViewWithdrawLogout.setOnMousePressed(e->{       Alert alert = new Alert(AlertType.CONFIRMATION,"Sure you want to logout ?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get()==ButtonType.OK){
  txtUserOrAdmin.clear();
  pswPassword.clear();
            primaryStage.setScene(Login_scene);
  primaryStage.setTitle("Login");
        }
        else{}
 
});


Image imgWithdrawFeedBack = new Image(getClass().getResource("/images/feedback.png").toExternalForm());
ImageView imgViewWithdrawFeedBack = new ImageView(imgWithdrawFeedBack);
imgViewWithdrawFeedBack.setFitHeight(32);
imgViewWithdrawFeedBack.setPreserveRatio(true);
imgViewWithdrawFeedBack.getStyleClass().add("feedback-icon");
imgViewWithdrawFeedBack.setOnMousePressed(e->showFeedbackDialog(loggedInUser.getUsername()));

ImageView imgViewUserWithdrawLogo = new ImageView(new Image(getClass().getResource("/images/userdashlogo.png").toExternalForm()));
imgViewUserWithdrawLogo.setId("dashboard-logo");
imgViewUserWithdrawLogo.setFitHeight(150);
imgViewUserWithdrawLogo.setPreserveRatio(true);

imgViewUserWithdrawLogo.setCursor(Cursor.HAND);
imgViewUserWithdrawLogo.setOnMousePressed(e->{
primaryStage.setScene(UserDashScene);
lblPointsDisplay.setText(String.valueOf(loggedInUser.getPoints())+" pts");
lblBalanceDisplay.setText(String.valueOf(loggedInUser.getBalance())+" JOD");
userDashboardLeftLV.getSelectionModel().clearSelection();
});


HBox UserWithdrawTop = new HBox();
UserWithdrawTop.setAlignment(Pos.CENTER);
Region WithdrawSpacerLeft = new Region();
Region WithdrawSpacerRight = new Region();
HBox.setHgrow(WithdrawSpacerLeft, Priority.ALWAYS);
HBox.setHgrow(WithdrawSpacerRight, Priority.ALWAYS);
UserWithdrawTop.getChildren().addAll(imgViewWithdrawLogout, WithdrawSpacerLeft, imgViewUserWithdrawLogo, WithdrawSpacerRight, imgViewWithdrawFeedBack);
UserWithdrawTop.setId("userdashboard-top");
UserWithdrawTop.setPrefHeight(30);
UserWithdrawTop.setPadding(new Insets(25, 15, 5, 25));


String[] UserWithdrawLeftLVOptions = {"Deposit", "", "Withdraw", "", "Transfer", "", "Details", "", "Delete Account"};
 userWithdrawLeftLV = new ListView();
userWithdrawLeftLV.getItems().addAll((Object[]) UserWithdrawLeftLVOptions);
userWithdrawLeftLV.setPrefWidth(180);
userWithdrawLeftLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
userWithdrawLeftLV.setOrientation(Orientation.VERTICAL);
userWithdrawLeftLV.setId("dashboard-listview");


userWithdrawLeftLV.setOnMousePressed(e->{
if(userWithdrawLeftLV.getSelectionModel().getSelectedItem().toString().equals("Deposit")){
    primaryStage.setScene(Deposit_Scene);
    userDepositLeftLV.getSelectionModel().select(0);
    txtDepositAmmount.requestFocus();
    lblDepositBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}
if(userWithdrawLeftLV.getSelectionModel().getSelectedItem().toString().equals("Withdraw")){
    primaryStage.setScene(Withdraw_Scene);
    userWithdrawLeftLV.getSelectionModel().select(2);
    txtWithdrawAmount.requestFocus();
    lblWithdrawBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}

if(userWithdrawLeftLV.getSelectionModel().getSelectedItem().toString().equals("Transfer")){
    primaryStage.setScene(Transfer_Scene);
    txtTransferTo.requestFocus();
    userTransferLeftLV.getSelectionModel().select(4);
    lblTransferBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}


if(userWithdrawLeftLV.getSelectionModel().getSelectedItem().toString().equals("Details")){
    primaryStage.setScene(Details_scene);
    txtWithdrawAmount.requestFocus();
    userDetailsLeftLV.getSelectionModel().select(6);
    lblDetailsId.setText("ID : "+loggedInUser.getId());
  lblDetailsFN.setText("First name : "+loggedInUser.getFirstName());
lblDetailsLN.setText("Last name : "+loggedInUser.getLastName());
lblDetailsEM.setText("Email : "+loggedInUser.getEmail());
lblDetailsUN.setText("Username : "+loggedInUser.getUsername());
lblDetailsCN.setText("Country : "+loggedInUser.getCountry());
lblDetailsBC.setText("Balance : "+loggedInUser.getBalance()+" JOD");
lblDetailsPS.setText("Points : "+loggedInUser.getPoints());

}


if(userWithdrawLeftLV.getSelectionModel().getSelectedItem().toString().equals("Delete Account")){
    showDeleteAccountDialog(loggedInUser.getUsername(), primaryStage);
}


});



double latestBalance = 0.0;
try {
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
    PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM users WHERE id = ?");
    stmt.setInt(1, loggedInUser.getId());
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        latestBalance = rs.getDouble("balance");
        loggedInUser.setBalance(latestBalance); 
    }
} catch (SQLException ex) {
    System.out.println("Failed to refresh balance: " + ex);
}



Label lblWithdrawBalance = new Label("Balance : ");
lblWithdrawBalance.getStyleClass().add("deposit-label");
lblWithdrawBalanceDisplay = new Label(String.format("%.2f", latestBalance) + " JOD");
lblWithdrawBalanceDisplay.getStyleClass().add("deposit-value");

HBox HboxWithdrawBalance = new HBox(lblWithdrawBalance, lblWithdrawBalanceDisplay);
HboxWithdrawBalance.setAlignment(Pos.CENTER);


Image imgWithdrawAmount = new Image(getClass().getResource("/images/withdraw.png").toExternalForm());
ImageView imgViewWithdrawAmount = new ImageView(imgWithdrawAmount);
imgViewWithdrawAmount.setFitHeight(40);
imgViewWithdrawAmount.setPreserveRatio(true);

txtWithdrawAmount = new TextField();
txtWithdrawAmount.getStyleClass().add("deposit-field");
txtWithdrawAmount.setPromptText("Withdraw Amount");

HBox HboxWithdrawAmount = new HBox(10, imgViewWithdrawAmount, txtWithdrawAmount);
HboxWithdrawAmount.setAlignment(Pos.CENTER);


Button btnWithdraw = new Button("Withdraw");
btnWithdraw.getStyleClass().add("deposit-button");
HBox hbtnWithdraw = new HBox(btnWithdraw);
hbtnWithdraw.setAlignment(Pos.CENTER);


VBox Withdraw_Center = new VBox(60, HboxWithdrawBalance, HboxWithdrawAmount, hbtnWithdraw);
Withdraw_Center.setPadding(new Insets(100));


BorderPane Withdraw_Root = new BorderPane();
Withdraw_Root.setLeft(userWithdrawLeftLV);
Withdraw_Root.setTop(UserWithdrawTop);
Withdraw_Root.setCenter(Withdraw_Center);
Withdraw_Root.setId("trans_root");

Withdraw_Scene = new Scene(Withdraw_Root, 1100, 600);
Withdraw_Scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());


btnWithdraw.setOnAction(e -> {
    String input = txtWithdrawAmount.getText().trim();

    if (input.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an amount.");
        alert.show();
        return;
    }

    double amount;
    try {
        amount = Double.parseDouble(input);
        if (amount <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Amount must be greater than zero.");
            alert.show();
            return;
        }
    } catch (NumberFormatException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number format");
        alert.show();
        return;
    }

    if (amount > loggedInUser.getBalance()) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Insufficient balance.");
        alert.show();
        return;
    }

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");

        double newBalance = loggedInUser.getBalance() - amount;
        PreparedStatement updateUser = conn.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
        updateUser.setDouble(1, newBalance);
        updateUser.setInt(2, loggedInUser.getId());
        updateUser.executeUpdate();

        loggedInUser.setBalance(newBalance);

        PreparedStatement insertTransaction = conn.prepareStatement(
            "INSERT INTO transactions (user_id, amount, type, time) VALUES (?, ?, 'withdraw', NOW())");
        insertTransaction.setInt(1, loggedInUser.getId());
        insertTransaction.setDouble(2, amount);
        insertTransaction.executeUpdate();

        lblWithdrawBalanceDisplay.setText(String.format("%.2f", newBalance) + " JOD");

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Withdrawal successful!");
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.show();
        txtWithdrawAmount.clear();
    } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + ex.toString());
        alert.show();
    }
});
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////User Transfer Scene////////////////////////////////////////////////////////////////////////////////////////////

Image imgTransferLogout = new Image(getClass().getResource("/images/logout.png").toExternalForm());
ImageView imgViewTransferLogout = new ImageView(imgTransferLogout);
imgViewTransferLogout.setFitHeight(32);
imgViewTransferLogout.setPreserveRatio(true);
imgViewTransferLogout.getStyleClass().add("logout-icon");
imgViewTransferLogout.setOnMousePressed(e->{       Alert alert = new Alert(AlertType.CONFIRMATION,"Sure you want to logout ?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get()==ButtonType.OK){
  txtUserOrAdmin.clear();
  pswPassword.clear();
            primaryStage.setScene(Login_scene);
  primaryStage.setTitle("Login");
        }
        else{}
 
});


Image imgTransferFeedBack = new Image(getClass().getResource("/images/feedback.png").toExternalForm());
ImageView imgViewTransferFeedBack = new ImageView(imgTransferFeedBack);
imgViewTransferFeedBack.setFitHeight(32);
imgViewTransferFeedBack.setPreserveRatio(true);
imgViewTransferFeedBack.getStyleClass().add("feedback-icon");
imgViewTransferFeedBack.setOnMousePressed(e->showFeedbackDialog(loggedInUser.getUsername()));

ImageView imgViewUserTransferLogo = new ImageView(new Image(getClass().getResource("/images/userdashlogo.png").toExternalForm()));
imgViewUserTransferLogo.setId("dashboard-logo");
imgViewUserTransferLogo.setFitHeight(150);
imgViewUserTransferLogo.setPreserveRatio(true);
imgViewUserTransferLogo.setCursor(Cursor.HAND);

imgViewUserTransferLogo.setOnMousePressed(e->{
primaryStage.setScene(UserDashScene);
lblPointsDisplay.setText(String.valueOf(loggedInUser.getPoints())+" pts");
lblBalanceDisplay.setText(String.valueOf(loggedInUser.getBalance())+" JOD");
userDashboardLeftLV.getSelectionModel().clearSelection();
});

HBox UserTransferTop = new HBox();
UserTransferTop.setAlignment(Pos.CENTER);
Region TransferSpacerLeft = new Region();
Region TransferSpacerRight = new Region();
HBox.setHgrow(TransferSpacerLeft, Priority.ALWAYS);
HBox.setHgrow(TransferSpacerRight, Priority.ALWAYS);
UserTransferTop.getChildren().addAll(imgViewTransferLogout, TransferSpacerLeft, imgViewUserTransferLogo, TransferSpacerRight, imgViewTransferFeedBack);
UserTransferTop.setId("userdashboard-top");
UserTransferTop.setPadding(new Insets(25, 15, 5, 25));

String[] UserTransferLeftLVOptions = {"Deposit", "", "Withdraw", "", "Transfer", "", "Details", "", "Delete Account"};
 userTransferLeftLV = new ListView();
userTransferLeftLV.getItems().addAll((Object[]) UserTransferLeftLVOptions);
userTransferLeftLV.setPrefWidth(180);
userTransferLeftLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
userTransferLeftLV.getSelectionModel().select(4);
userTransferLeftLV.setOrientation(Orientation.VERTICAL);
userTransferLeftLV.setId("dashboard-listview");



userTransferLeftLV.setOnMousePressed(e->{
if(userTransferLeftLV.getSelectionModel().getSelectedItem().toString().equals("Deposit")){
    primaryStage.setScene(Deposit_Scene);
    userDepositLeftLV.getSelectionModel().select(0);
    txtDepositAmmount.requestFocus();
    lblDepositBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");
}
if(userTransferLeftLV.getSelectionModel().getSelectedItem().toString().equals("Withdraw")){
    primaryStage.setScene(Withdraw_Scene);
    userWithdrawLeftLV.getSelectionModel().select(2);
    txtWithdrawAmount.requestFocus();
    lblWithdrawBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}

if(userTransferLeftLV.getSelectionModel().getSelectedItem().toString().equals("Transfer")){
    primaryStage.setScene(Transfer_Scene);
    userWithdrawLeftLV.getSelectionModel().select(4);
    txtWithdrawAmount.requestFocus();
    userTransferLeftLV.getSelectionModel().select(4);
    lblTransferBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}


if(userTransferLeftLV.getSelectionModel().getSelectedItem().toString().equals("Details")){
    primaryStage.setScene(Details_scene);
    txtWithdrawAmount.requestFocus();
    userDetailsLeftLV.getSelectionModel().select(6);
    lblDetailsId.setText("ID : "+loggedInUser.getId());
  lblDetailsFN.setText("First name : "+loggedInUser.getFirstName());
lblDetailsLN.setText("Last name : "+loggedInUser.getLastName());
lblDetailsEM.setText("Email : "+loggedInUser.getEmail());
lblDetailsUN.setText("Username : "+loggedInUser.getUsername());
lblDetailsCN.setText("Country : "+loggedInUser.getCountry());
lblDetailsBC.setText("Balance : "+loggedInUser.getBalance()+" JOD");
lblDetailsPS.setText("Points : "+loggedInUser.getPoints());

}

if(userTransferLeftLV.getSelectionModel().getSelectedItem().toString().equals("Delete Account")){
    showDeleteAccountDialog(loggedInUser.getUsername(), primaryStage);
}


});




Image imgTransfer =  new Image(getClass().getResource("/images/transfer.png").toExternalForm());
ImageView imgViewTransfer = new ImageView(imgTransfer);
 imgViewTransfer.setFitHeight(65);
imgViewTransfer.setPreserveRatio(true);
HBox himgViewTransfer = new HBox(imgViewTransfer);
himgViewTransfer.setAlignment(Pos.CENTER);



double latesttBalance = 0.0;
try {
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
    PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM users WHERE id = ?");
    stmt.setInt(1, loggedInUser.getId());
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        latesttBalance = rs.getDouble("balance");
        loggedInUser.setBalance(latesttBalance); 
    }
} catch (SQLException ex) {
    System.out.println("Failed to refresh balance: " + ex);
}

lblTransferBalance = new Label("Balance : ");
lblTransferBalance.getStyleClass().add("deposit-label");
lblTransferBalanceDisplay = new Label(String.format("%.2f", loggedInUser.getBalance()) + " JOD");
lblTransferBalanceDisplay.getStyleClass().add("deposit-value");

HBox HboxTransferBalance = new HBox(lblTransferBalance, lblTransferBalanceDisplay);
HboxTransferBalance.setAlignment(Pos.CENTER);
 txtTransferTo = new TextField();
txtTransferTo.setPromptText("Recipient Username");
txtTransferTo.getStyleClass().add("deposit-field");

txtTransferAmount = new TextField();
txtTransferAmount.setPromptText("Amount to Transfer");
txtTransferAmount.getStyleClass().add("deposit-field");

Button btnTransfer = new Button("Transfer");
btnTransfer.getStyleClass().add("deposit-button");

HBox hboxTransferBtn = new HBox(btnTransfer);
hboxTransferBtn.setAlignment(Pos.CENTER);


VBox Transfer_Center = new VBox(30);
Transfer_Center.getChildren().addAll(himgViewTransfer,HboxTransferBalance, txtTransferTo, txtTransferAmount, hboxTransferBtn);
Transfer_Center.setPadding(new Insets(50,200,50,200));
Transfer_Center.setAlignment(Pos.CENTER);

BorderPane Transfer_Root = new BorderPane();
Transfer_Root.setLeft(userTransferLeftLV);
Transfer_Root.setTop(UserTransferTop);
Transfer_Root.setCenter(Transfer_Center);
Transfer_Root.setId("trans_root");

Transfer_Scene = new Scene(Transfer_Root, 1100, 600);
Transfer_Scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

btnTransfer.setOnAction(e -> {
    String recipientUsername = txtTransferTo.getText().trim();
    String inputAmount = txtTransferAmount.getText().trim();

    if (recipientUsername.isEmpty() || inputAmount.isEmpty()) {
        new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
        return;
    }

    if (recipientUsername.equals(loggedInUser.getUsername())) {
        new Alert(Alert.AlertType.ERROR, "You cannot transfer to yourself.").show();
        return;
    }

    double amount;
    try {
        amount = Double.parseDouble(inputAmount);
        if (amount <= 0) {
            new Alert(Alert.AlertType.ERROR, "Amount must be greater than 0.").show();
            return;
        }
        if (loggedInUser.getBalance() < amount) {
            new Alert(Alert.AlertType.ERROR, "Insufficient balance.").show();
            return;
        }
    } catch (NumberFormatException ex) {
        new Alert(Alert.AlertType.ERROR, "Invalid amount.").show();
        return;
    }

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");

        PreparedStatement findRecipient = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        findRecipient.setString(1, recipientUsername);
        ResultSet rs = findRecipient.executeQuery();

        if (!rs.next()) {
            new Alert(Alert.AlertType.ERROR, "Recipient not found.").show();
            return;
        }

        int recipientId = rs.getInt("id");
        double recipientBalance = rs.getDouble("balance") + amount;
        double newSenderBalance = loggedInUser.getBalance() - amount;

        
        PreparedStatement updateSender = conn.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
        updateSender.setDouble(1, newSenderBalance);
        updateSender.setInt(2, loggedInUser.getId());
        System.out.println(loggedInUser.getId());

        updateSender.executeUpdate();

        
        PreparedStatement updateRecipient = conn.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
        updateRecipient.setDouble(1, recipientBalance);
        updateRecipient.setInt(2, recipientId);
        updateRecipient.executeUpdate();

        
        PreparedStatement insertSenderTxn = conn.prepareStatement(
            "INSERT INTO transactions (user_id, amount, type, time,target_user_id) VALUES (?, ?, 'transfer', NOW(),?)");
        insertSenderTxn.setInt(1, loggedInUser.getId());
        insertSenderTxn.setDouble(2, amount);
        insertSenderTxn.setDouble(3, recipientId);
        insertSenderTxn.executeUpdate();

        
        loggedInUser.setBalance(newSenderBalance);
        lblTransferBalanceDisplay.setText(String.format("%.2f", newSenderBalance) + " JOD");

        new Alert(Alert.AlertType.INFORMATION, "Transfer successful!").show();
    } catch (SQLException ex) {
        new Alert(Alert.AlertType.ERROR, "Database error: " + ex).show();
    }
});
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////user details scene//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Image imgDetalisLogout =  new Image(getClass().getResource("/images/logout.png").toExternalForm());
ImageView imgViewDetailsLogout = new ImageView(imgLogout);
imgViewDetailsLogout.setFitHeight(32);
imgViewDetailsLogout.setPreserveRatio(true);
imgViewDetailsLogout.getStyleClass().add("logout-icon");
imgViewDetailsLogout.setOnMousePressed(e->{       Alert alert = new Alert(AlertType.CONFIRMATION,"Sure you want to logout ?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get()==ButtonType.OK){
  txtUserOrAdmin.clear();
  pswPassword.clear();
            primaryStage.setScene(Login_scene);
  primaryStage.setTitle("Login");
        }
        else{}
 
});


Image imgDetailsFeedBack = new Image(getClass().getResource("/images/feedback.png").toExternalForm());
ImageView imgViewDetailsFeedBack = new ImageView(imgFeedBack);
imgViewDetailsFeedBack.setFitHeight(32);
imgViewDetailsFeedBack.setPreserveRatio(true);
imgViewDetailsFeedBack.getStyleClass().add("feedback-icon");
imgViewDetailsFeedBack.setOnMousePressed(e->showFeedbackDialog(loggedInUser.getUsername()));

ImageView imgViewUserDetailsLogo = new ImageView(new Image(getClass().getResource("/images/userdashlogo.png").toExternalForm()));
imgViewUserDetailsLogo.setCursor(Cursor.HAND);

imgViewUserDetailsLogo.setOnMousePressed(e->{
primaryStage.setScene(UserDashScene);
lblPointsDisplay.setText(String.valueOf(loggedInUser.getPoints())+" pts");
lblBalanceDisplay.setText(String.valueOf(loggedInUser.getBalance())+" JOD");
userDashboardLeftLV.getSelectionModel().clearSelection();
});


imgViewUserDetailsLogo.setId("dashboard-logo");
imgViewUserDetailsLogo.setFitHeight(150);
imgViewUserDetailsLogo.setPreserveRatio(true);
HBox UserDetailsTop = new HBox();
UserDetailsTop.setAlignment(Pos.CENTER);

Region DetailsspacerLeft = new Region();
Region DetailsspacerRight = new Region();

HBox.setHgrow(DetailsspacerLeft, Priority.ALWAYS);
HBox.setHgrow(DetailsspacerRight, Priority.ALWAYS);


UserDetailsTop.getChildren().addAll(imgViewDetailsLogout,DetailsspacerLeft,imgViewUserDetailsLogo,DetailsspacerRight,imgViewDetailsFeedBack);
UserDetailsTop.setId("userdashboard-top");
UserDetailsTop.setPrefHeight(30);
UserDetailsTop.setPadding(new Insets(25, 15, 5, 25));

//////////////////////////////////////////////////////

String[] UserDetailsLeftLVOptions = {"Deposit","","Withdraw","","Transfer","","Details","","Delete Account"};
 userDetailsLeftLV = new ListView();
userDetailsLeftLV.getItems().addAll((Object[])UserDetailsLeftLVOptions);

userDetailsLeftLV.setPrefWidth(180);
userDetailsLeftLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
userDetailsLeftLV.setOrientation(Orientation.VERTICAL);
userDetailsLeftLV.setId("dashboard-listview");

userDetailsLeftLV.setOnMousePressed(e->{
if(userDetailsLeftLV.getSelectionModel().getSelectedItem().toString().equals("Deposit")){
    primaryStage.setScene(Deposit_Scene);
    userDepositLeftLV.getSelectionModel().select(0);
    txtDepositAmmount.requestFocus();
    lblDepositBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}
if(userDetailsLeftLV.getSelectionModel().getSelectedItem().toString().equals("Withdraw")){
    primaryStage.setScene(Withdraw_Scene);
    userWithdrawLeftLV.getSelectionModel().select(2);
    txtWithdrawAmount.requestFocus();
    lblWithdrawBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}

if(userDetailsLeftLV.getSelectionModel().getSelectedItem().toString().equals("Transfer")){
    primaryStage.setScene(Transfer_Scene);
    txtTransferTo.requestFocus();
    userTransferLeftLV.getSelectionModel().select(4);
    lblTransferBalanceDisplay.setText(String.format("%.2f", loggedInUser.getBalance())+" JOD");

}


if(userDetailsLeftLV.getSelectionModel().getSelectedItem().toString().equals("Details")){
    primaryStage.setScene(Details_scene);
    txtWithdrawAmount.requestFocus();
    userDetailsLeftLV.getSelectionModel().select(6);
    lblDetailsId.setText("ID : "+loggedInUser.getId());
  lblDetailsFN.setText("First name : "+loggedInUser.getFirstName());
lblDetailsLN.setText("Last name : "+loggedInUser.getLastName());
lblDetailsEM.setText("Email : "+loggedInUser.getEmail());
lblDetailsUN.setText("Username : "+loggedInUser.getUsername());
lblDetailsCN.setText("Country : "+loggedInUser.getCountry());
lblDetailsBC.setText("Balance : "+loggedInUser.getBalance()+" JOD");
lblDetailsPS.setText("Points : "+loggedInUser.getPoints());

}
if(userDetailsLeftLV.getSelectionModel().getSelectedItem().toString().equals("Delete Account")){
    showDeleteAccountDialog(loggedInUser.getUsername(), primaryStage);
}

});

/////////////////////////////////////
 lblDetailsId = new Label();
 lblDetailsFN = new Label();
 lblDetailsLN = new Label();
 lblDetailsEM = new Label();
 lblDetailsUN = new Label();
 lblDetailsCN = new Label();
 lblDetailsBC = new Label();
 lblDetailsPS = new Label();

 
 lblDetailsId.setId("label-id");        lblDetailsId.getStyleClass().add("details-label");
lblDetailsFN.setId("label-fn");        lblDetailsFN.getStyleClass().add("details-label");
lblDetailsLN.setId("label-ln");        lblDetailsLN.getStyleClass().add("details-label");
lblDetailsEM.setId("label-email");     lblDetailsEM.getStyleClass().add("details-label");
lblDetailsUN.setId("label-un");        lblDetailsUN.getStyleClass().add("details-label");
lblDetailsCN.setId("label-country");   lblDetailsCN.getStyleClass().add("details-label");
lblDetailsBC.setId("label-balance");   lblDetailsBC.getStyleClass().add("details-label");
lblDetailsPS.setId("label-pass");      lblDetailsPS.getStyleClass().add("details-label");

 
ToggleButton themeToggle = new ToggleButton("🌙 Dark Mode");
themeToggle.setSelected(false); 

themeToggle.setOnAction(e -> {
    if (themeToggle.isSelected()) {
        themeToggle.setText("☀ Light Mode");
        UserDashScene.getStylesheets().clear();
        UserDashScene.getStylesheets().add(getClass().getResource("/styles/dark.css").toExternalForm());
        
        Deposit_Scene.getStylesheets().clear();
        Deposit_Scene.getStylesheets().add(getClass().getResource("/styles/dark.css").toExternalForm());
        
        Withdraw_Scene.getStylesheets().clear();
        Withdraw_Scene.getStylesheets().add(getClass().getResource("/styles/dark.css").toExternalForm());
       
        
        Transfer_Scene.getStylesheets().clear();
        Transfer_Scene.getStylesheets().add(getClass().getResource("/styles/dark.css").toExternalForm());
        
        Details_scene.getStylesheets().clear();
        Details_scene.getStylesheets().add(getClass().getResource("/styles/dark.css").toExternalForm());
        
        
        
    } else {
        themeToggle.setText("🌙 Dark Mode");
        UserDashScene.getStylesheets().clear();
        UserDashScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        
        
        Deposit_Scene.getStylesheets().clear();
        Deposit_Scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        
        Withdraw_Scene.getStylesheets().clear();
        Withdraw_Scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
       
        
        Transfer_Scene.getStylesheets().clear();
        Transfer_Scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        
        Details_scene.getStylesheets().clear();
        Details_scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        
        
        
    }
});
themeToggle.getStyleClass().add("toggle-button");
 
VBox DetailsCenter = new VBox();
DetailsCenter.getChildren().addAll(lblDetailsId,lblDetailsFN,lblDetailsLN,lblDetailsEM,lblDetailsUN,lblDetailsCN,lblDetailsBC,lblDetailsPS,themeToggle);
DetailsCenter.setId("details-card");
BorderPane Details_Root = new BorderPane();
Details_Root.setTop(UserDetailsTop);
Details_Root.setLeft(userDetailsLeftLV);
Details_Root.setCenter(DetailsCenter);
Details_Root.setId("details-root");
Details_scene = new Scene(Details_Root,1100,600);


Details_scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
///////////////////////////////////////////Admin_Scene///////////////////////////////////////////////////////////////////////////////////////////////////////////
lblAdminWelcome = new Label("Welcome, Admin");
Label  lblAdminPanel = new Label("Admin Panel");
Button btnAdminLogout = new Button("Logout");
Button btnAdminUsers = new Button("Users");
Button btnAdminTransactions = new Button("Transactions");
Button btnAdminFeedbacks = new Button("Feedbacks");
lblAdminWelcome.setFont(Font.font("New Times Roman",14));
lblAdminPanel.setFont(Font.font("New Times Roman",14));
btnAdminLogout.setFont(Font.font("New Times Roman",14));
btnAdminLogout.setOnAction(e->System.exit(0));
btnAdminTransactions.setFont(Font.font("New Times Roman",14));
btnAdminUsers.setFont(Font.font("New Times Roman",14));
btnAdminFeedbacks.setFont(Font.font("New Times Roman",14));
btnAdminUsers.setOnAction(e->showUsersTable());

btnAdminFeedbacks.setPrefWidth(150);
        btnAdminTransactions.setPrefWidth(150);
btnAdminUsers.setPrefWidth(150);

btnAdminTransactions.setOnAction(e->showTransactionsTable());
btnAdminFeedbacks.setOnAction(e->showFeedbacksTable());
HBox Admin_Top = new HBox(100);
Admin_Top.setAlignment(Pos.CENTER);
Admin_Top.getChildren().addAll(lblAdminWelcome,lblAdminPanel,btnAdminLogout);

VBox Admin_Left = new VBox(40);
Admin_Left.getChildren().addAll(btnAdminUsers,btnAdminTransactions,btnAdminFeedbacks);


 Admin_Root = new BorderPane();
Admin_Root.setTop(Admin_Top);
Admin_Root.setLeft(Admin_Left);

 Admin_Scene = new Scene(Admin_Root,1100,600);




















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
primaryStage.setScene(welcomeScene);
    primaryStage.setTitle("Welcome");
    primaryStage.setResizable(false);
    primaryStage.show();
   
    
    }
    
    










    public static void main(String[] args) {
        launch(args);
    }
   
    
    
    
    
    
    public void showFeedbackDialog(String username) {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Feedback");
    dialog.setHeaderText("Please share your thoughts with us.");
    
   
    dialog.getDialogPane().getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
    dialog.getDialogPane().getStyleClass().add("custom-dialog");

    ButtonType submitButtonType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

    TextArea feedbackArea = new TextArea();
    feedbackArea.setPromptText("Type your feedback here...");
    feedbackArea.setWrapText(true);
    feedbackArea.getStyleClass().add("custom-textarea");

    dialog.getDialogPane().setContent(feedbackArea);

    
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == submitButtonType) {
            return feedbackArea.getText();
        }
        return null;
    });

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(feedback -> {
        if (!feedback.trim().isEmpty()) {
            insertFeedbackIntoDB(username, feedback);
         
            Alert alert = new Alert(AlertType.INFORMATION, "Thank you for your feedback");
            alert.show();
        } else {
        Alert alert = new Alert(AlertType.WARNING, "Feedback can't be empty !");
        alert.show();
        }
    });
}

    
    public void insertFeedbackIntoDB(String username, String message) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
    String sql = "INSERT INTO feedback (username, message) VALUES (?, ?)";
PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, message);
        
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        Alert alert = new Alert(AlertType.WARNING, "Failed to save feedback !");
        alert.show();
         }
}

    
public void showDeleteAccountDialog(String username, Stage primaryStage) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Account");
    alert.setHeaderText("Are you sure you want to delete your account?");
    alert.setContentText("This action cannot be undone.");

    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
    dialogPane.getStyleClass().add("custom-dialog");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        if (deleteAccountFromDB(username)) {
         Alert alertt = new Alert(AlertType.INFORMATION,"Your account has been deleted. App is closed");
         alertt.show();
         primaryStage.close(); 
        } else {
        Alert alerttt = new Alert(AlertType.ERROR,"An error occurred while deleting the account.");
        alerttt.show();
        }
    }
}
    
    public boolean deleteAccountFromDB(String username) {
    String query = "DELETE FROM users WHERE username = ?";
    try  {
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
   PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        int rows = stmt.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
 
 


private void showUsersTable() {
    TableView<User> table = new TableView<>();

    TableColumn<User, Integer> idCol = new TableColumn<>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<User, String> nameCol = new TableColumn<>("Name");
    nameCol.setCellValueFactory(data -> new SimpleStringProperty(
        data.getValue().getFirstName() + " " + data.getValue().getLastName()
    ));

    TableColumn<User, String> usernameCol = new TableColumn<>("Username");
    usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

    TableColumn<User, String> emailCol = new TableColumn<>("Email");
    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

    TableColumn<User, Double> balanceCol = new TableColumn<>("Balance");
    balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));

    TableColumn<User, Integer> pointsCol = new TableColumn<>("Points");
    pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));

    TableColumn<User, Void> deleteCol = new TableColumn<>("Delete");
    deleteCol.setCellFactory(col -> {
        TableCell<User, Void> cell = new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");

            {
                deleteBtn.setOnAction(e -> {
                    User user = getTableView().getItems().get(getIndex());
                    deleteUser(user); 
                    table.getItems().remove(user);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteBtn);
                }
            }
        };
        return cell;
    });

    table.getColumns().addAll(idCol, nameCol, usernameCol, emailCol, balanceCol, pointsCol, deleteCol);

    table.setItems(FXCollections.observableArrayList(fetchUsersFromDB()));

    Admin_Root.setCenter(table);
}

private List<User> fetchUsersFromDB() {
    List<User> users = new ArrayList<>();
    String sql = "SELECT id, first_name, last_name, username, email, balance, points FROM users WHERE role = 'user'";

    try {
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
  PreparedStatement stmt = conn.prepareStatement(sql); 
        ResultSet rs = stmt.executeQuery(); 
        while (rs.next()) {
            users.add(new User(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                   rs.getString("username"), 
                 rs.getString("email"),
                rs.getDouble("balance"),
                rs.getInt("points")
                 ));
        }
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

    return users;
}


private void deleteUser(User user) {
    String sql = "DELETE FROM users WHERE id = ?";

    try{ 
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
        PreparedStatement stmt = conn.prepareStatement(sql); 
        stmt.setInt(1, user.getId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



private void showTransactionsTable() {
    TableView<Transaction> transactionsTable = new TableView<>();

    TableColumn<Transaction, Integer> idCol = new TableColumn<>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Transaction, Integer> userIdCol = new TableColumn<>("User ID");
    userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

    TableColumn<Transaction, String> typeCol = new TableColumn<>("Type");
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

    TableColumn<Transaction, Double> amountCol = new TableColumn<>("Amount");
    amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

    TableColumn<Transaction, Integer> targetUserIdCol = new TableColumn<>("Target User ID");
    targetUserIdCol.setCellValueFactory(new PropertyValueFactory<>("targetUserId"));

    TableColumn<Transaction, String> timeCol = new TableColumn<>("Time");
    timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

    transactionsTable.getColumns().addAll(idCol, userIdCol, typeCol, amountCol, targetUserIdCol, timeCol);

    
    ObservableList<Transaction> data = FXCollections.observableArrayList();
    String query = "SELECT id, user_id, type, amount, target_user_id, time FROM transactions";

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
        PreparedStatement ps = conn.prepareStatement(query); 
    
         ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Transaction t = new Transaction(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("type"),
                rs.getDouble("amount"),
                rs.getInt("target_user_id"),
                rs.getString("time")
            );
            data.add(t);
        }

    }
    catch (SQLException e) {
        e.printStackTrace();
    }

    transactionsTable.setItems(data);

    
    Admin_Root.setCenter(transactionsTable);
}


private void showFeedbacksTable() {
    TableView<Feedback> table = new TableView<>();

    TableColumn<Feedback, Integer> idCol = new TableColumn<>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Feedback, String> usernameCol = new TableColumn<>("Username");
    usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

    TableColumn<Feedback, String> messageCol = new TableColumn<>("Message");
    messageCol.setCellValueFactory(new PropertyValueFactory<>("message"));

    TableColumn<Feedback, String> submittedAtCol = new TableColumn<>("Submitted At");
    submittedAtCol.setCellValueFactory(new PropertyValueFactory<>("submittedAt"));

    table.getColumns().addAll(idCol, usernameCol, messageCol, submittedAtCol);


    ObservableList<Feedback> feedbacks = FXCollections.observableArrayList();
    try {
    String query = "SELECT id, username, message, submitted_at FROM feedback";    
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qmmdb", "root", "");
        PreparedStatement ps = conn.prepareStatement(query); 
    
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query); 

        while (rs.next()) {
            feedbacks.add(new Feedback(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("message"),
                rs.getString("submitted_at")
            ));
        }
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

    table.setItems(feedbacks);

    Admin_Root.setCenter(table);
}
   
}
