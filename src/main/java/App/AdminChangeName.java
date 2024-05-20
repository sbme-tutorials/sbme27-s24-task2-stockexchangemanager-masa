package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.regex.Pattern;

public class AdminChangeName extends Controller{
    @FXML
    private Button change;
    @FXML
    private Button cancel;
    @FXML
    private Label currName;
    @FXML
    private TextField newName;
    @FXML
    private PasswordField password;
    @FXML
    private TextField ConfirmName;

    public void initialize() {
        Controller.currTitle = currStage.getTitle();
        currUser = Data.Admins.get(Data.TempID);
        currName.setText(currUser.GetUsername());
    }

    public void Change(ActionEvent e) throws IOException {
        String name = newName.getText();
        String pass = password.getText();
        String confirmName = ConfirmName.getText();
        final boolean checkUsername = Pattern.matches("[A-Za-z]+", name);

        // System.out.println(checkUsername);
        // System.out.println(name.equals(confirmName));
        // System.out.println(Data.VerifyAdminLogin(currUser.getUsername(), pass));
        if (checkUsername && name.equals(confirmName) && Data.Verify_AdminLogin(currUser.GetUsername(), pass)) {
            currUser.setUsername(name);
            display(currUser, Controller.prevTitle + ".fxml");
            Controller.prevTitle = Controller.currTitle;
        } else {
            WarningMessage.show("WrongInput",
            "* Name must contain only word characters.\n" +
            "* Name must match with confirm name.\n" +
            "* Check if the password is correct."
            );
        }
    }

    public void Cancel(ActionEvent e) throws IOException {
        display(currUser, Controller.prevTitle + ".fxml");
        Controller.prevTitle = Controller.currTitle;
    }
}
