package App;

import java.io.IOException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminGeneratorController extends Controller{
    private AdminGenerator adminGenerator;
    private UserFactory userFactory;

    @FXML
    private Button create;
    @FXML
    private Button cancel;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField ConfirmPassword;

    public void initialize() {
        Controller.currTitle = currStage.getTitle();
        currUser = Data.Admins.get(Data.TempID);
        adminGenerator = AdminGenerator.getAdminGenerator();
        userFactory = new UserFactory();
    }
    public void Create(ActionEvent e) throws IOException {
        String name = Username.getText();
        String pass = Password.getText();
        String confirmPass = ConfirmPassword.getText();
        final boolean checkUsername = Pattern.matches("[A-Za-z]+",name);
        final boolean checkPassword = Pattern.matches("\\w{8,}",pass);

        if (checkUsername && checkPassword && pass.equals(confirmPass) &&
        Data.AdminNameIsAvailable(name)) {
            adminGenerator.add_admin(userFactory.GetUser(UserFactory.ADMIN, name, pass));
            display(currUser, Controller.prevTitle + ".fxml");
            Controller.prevTitle = Controller.currTitle;
        } else {
            WarningMessage.show("WrongInput",
            "* Name must contain only word characters.\n" +
            "* Password must be 8 characters or more.\n" +
            "* Password must match with confirm password.\n" +
            "* This admin might be exist."
            );
        }
    }
    public void Cancel(ActionEvent e) throws IOException {
        display(currUser, Controller.prevTitle + ".fxml");
        Controller.prevTitle = Controller.currTitle;
    }
}
