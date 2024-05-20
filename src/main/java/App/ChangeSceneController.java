package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;


public class ChangeSceneController extends Controller {
    AccountController accountController=new AccountController();
    OrderController orderController = new OrderController();


    boolean CheckerUsername, CheckerPassword ;
    @FXML
    private TextField UserNameField;
    @FXML
    private TextField confirmUsernameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField currentPasswordField;

    @FXML
    private TextField ConfirmPasswordField;
    @FXML
    private Label LabelField;
    @FXML
    public static Label ShowName;

    public String TempForUsername;
//    Stage stage;

    public String TempForPassword,TempForCurrentPassword;



    public void initialize() {
        currUser = Data.Users.get(Data.TempID);
//        ShowName.setText(currUser.GetUsername());
    }



    @FXML
    public void SwitchToUserLogin(ActionEvent event) throws IOException {
        accountController.setPrimaryStage();
        orderController.setPrimaryStage();
        display("LoginScene.fxml");
    }

    @FXML
    public void SwitchToAdminLogin(ActionEvent event) throws IOException {
        display("AdminLogin.fxml");
    }

    @FXML
    public void LogIn(ActionEvent event) throws IOException {

        TempForUsername = UserNameField.getText();
        CheckerUsername = Pattern.matches("[A-Za-z]{8,}",TempForUsername);
        TempForPassword = PasswordField.getText();
        CheckerPassword = Pattern.matches("\\w{8,}",TempForPassword);
        if (CheckerUsername && CheckerPassword) {
            currUser = Data.VerifyLogin(TempForUsername, TempForPassword);
            if (currUser != null ) {
                display(currUser,"NormalUserScene.fxml");
                Account.setUserAccount(currUser.id);
            } else {
                LabelField.setText("Invalid User name or password ");
            }
        } else {
            LabelField.setText("username 8 or more characters only password 8 or more character or numbers");
        }

    }






    @FXML
    public void CreateNewAccount(ActionEvent event) throws IOException {

        display("createNewaccount.fxml");
    }

    @FXML
    public void AfterCreation(ActionEvent event) throws IOException {
        TempForUsername = UserNameField.getText();
        CheckerUsername = Pattern.matches("[A-Za-z]{8,}",TempForUsername);
        TempForPassword = PasswordField.getText();
        CheckerPassword = Pattern.matches("\\w{8,}",TempForPassword);
        if (CheckerUsername && CheckerPassword && TempForPassword.equals(ConfirmPasswordField.getText())) {
        if (Data.UsernameIsAvailable(TempForUsername)) {
            Data.setUsers(TempForUsername, TempForPassword);
            display("LoginScene.fxml");
        } else {
            LabelField.setText("Invalid User name Please Try Again");
        }
        }
        else {
        LabelField.setText("username 8 or more characters only password 8 or more characters or numbers");
    }

}

    @FXML
    public void AfterCancel(ActionEvent event) throws IOException {

        display("hello-view.fxml");
    }



    @FXML
    public void AdminLogin(ActionEvent event) throws IOException {
        TempForUsername = UserNameField.getText();
        CheckerUsername = Pattern.matches("[A-Za-z]{8,}",TempForUsername);
        TempForPassword = PasswordField.getText();
        CheckerPassword = Pattern.matches("\\w{8,}",TempForPassword);
        if (CheckerUsername && CheckerPassword) {
            currUser = Data.VerifyAdminLogin(TempForUsername, TempForPassword);
            if (currUser!=null) {
                display(currUser,"AdminScene.fxml");
            }
        }
        else{
            LabelField.setText("username 8 or more characters only password 8 or more character or numbers");
        }
    }

    @FXML
    public void ShowNameForAdmin(){
        LabelField.setText(Data.Admins.get(Data.TempID).GetUsername());
    }
    @FXML
    public  void change_name(ActionEvent event) throws IOException {
        TempForUsername = UserNameField.getText();
        CheckerUsername = Pattern.matches("[A-Za-z]{8,}",TempForUsername);
        TempForPassword = PasswordField.getText();
        if (Objects.equals(TempForPassword , Data.Users.get(Data.TempID).getPassword())){
            if (Objects.equals(TempForUsername,confirmUsernameField.getText()) && CheckerUsername){
                User.ChangeUsername(TempForUsername,"normal");

                display("LoginScene.fxml");
            }
            else {
                LabelField.setText("please check that pattern must be 8 character or more");
            }
        }
        else {
            LabelField.setText("please check New name equals confirm Name");
        }
    }
    @FXML
    public void change_password(ActionEvent event) throws IOException{
        TempForCurrentPassword = currentPasswordField.getText();
        TempForPassword = PasswordField.getText();
        CheckerPassword = Pattern.matches("\\w{8,}",TempForPassword);
        if (Objects.equals(TempForCurrentPassword , Data.Users.get(Data.TempID).getPassword())) {
            if (Objects.equals(TempForPassword, ConfirmPasswordField.getText()) && CheckerPassword) {
                User.ChangePassword(TempForCurrentPassword, TempForPassword, "normal");
                display("LoginScene.fxml");
            }
            else {
                LabelField.setText("please check New password equals confirm password");
            }
        }
        else {
            LabelField.setText("please check that pattern must be 8 character or more");
        }
    }
    public void back(ActionEvent event) throws IOException{
        display(currUser,"NormalUserScene.fxml");
    }



//    @FXML
//    public  void showName(String username){
//        ShowName.setText(username);
//    }


}
