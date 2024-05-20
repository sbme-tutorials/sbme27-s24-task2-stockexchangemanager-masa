package App;

import java.io.IOException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class AdminChangePassword extends Controller{
    @FXML
    private Button change;
    @FXML
    private Button cancel;
    @FXML
    private Label currName;
    @FXML
    private PasswordField OldPassword;
    @FXML
    private PasswordField NewPassword;
    @FXML
    private PasswordField ConfirmPassword;

    public void initialize() {
        Controller.currTitle = currStage.getTitle();
        currUser = Data.Admins.get(Data.TempID);
        currName.setText(currUser.GetUsername());
    }

    public void Change(ActionEvent e) throws IOException {
        String o_pass = OldPassword.getText();
        String n_pass = NewPassword.getText();
        String confirmPass = ConfirmPassword.getText();
        final boolean check1 = Pattern.matches("\\w{8,}",o_pass);
        final boolean check2 = Pattern.matches("\\w{8,}",n_pass);

        if (check1 && check2 && n_pass.equals(confirmPass) && !o_pass.equals(n_pass) && Data.Verify_AdminLogin(currUser.GetUsername(), o_pass)) {
            currUser.setPassword(n_pass);
            // System.out.println(currUser.getPassword());
            display(currUser, Controller.prevTitle + ".fxml");
            Controller.prevTitle = Controller.currTitle;
        } else {
            WarningMessage.show("WrongInput",
            "* Password must be 8 characters or more.\n" +
            "* Password must match with confirm password.\n" +
            "* New password should not match with current password.\n" +
            "* Check if the current password is correct."
            );
        }
    }

    public void Cancel(ActionEvent e) throws IOException {
        display(currUser, Controller.prevTitle + ".fxml");
        Controller.prevTitle = Controller.currTitle;
    }
}
