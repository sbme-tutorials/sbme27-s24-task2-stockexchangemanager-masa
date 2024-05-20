package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayTransactionController extends Controller implements Initializable {
    @FXML
    private TitledPane profileVeiw;

    @FXML
    private Label usernameField , UserId;

//    public void initialize() {
//        profileVeiw.setExpanded(false);
//        setInformation();
//    }
@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileVeiw.setExpanded(false);
        setInformation();
    }

    public void setInformation(){
        usernameField.setText(Data.Users.get(Data.TempID).username);  ;
        UserId.setText(String.valueOf((Data.Users.get(Data.TempID).getId())));
    }

    @FXML
    public void changeName(ActionEvent event) throws IOException {
//        ShowStage("ChangeName.fxml", event);
        display(currUser,"ChangeName.fxml");
    }


    @FXML
    public void changeToAccount(ActionEvent event) throws IOException {
//        ShowStage("Market.fxml",event);
        display(currUser,"Market.fxml");
    }
    @FXML
    public void back(ActionEvent event) throws IOException{
//        ShowStage("NormalUserScene.fxml",event);
        display(currUser,"NormalUserScene.fxml");
    }
    @FXML
    public void changePassword(ActionEvent event) throws IOException{
//        ShowStage("ChangePassword.fxml",event);
        display(currUser,"ChangePassword.fxml");

    }

}
