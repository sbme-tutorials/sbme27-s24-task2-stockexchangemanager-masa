package App;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.util.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.io.IOException;

import java.time.LocalDateTime;

public class SessionController extends Controller {

    @FXML
    private Button end;
    @FXML
    private Button ApprovalSystem;
    @FXML
    private MenuItem changeName;
    @FXML
    private MenuItem changePassword;
    @FXML
    private Label adminId;
    @FXML
    private Label username;
    @FXML
    private Label SessionTime;
    @FXML
    private TitledPane viewProfile;
    @FXML
    private Button createNewStocks;

    public void initialize() {
        Controller.currTitle = currStage.getTitle();
        currUser = Data.Admins.get(Data.TempID);
        username.setText(currUser.GetUsername());
        adminId.setText(Objects.toString(currUser.getId()));
        viewProfile.setExpanded(false);

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->  
            SessionTime.setText(LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            ),
            new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void CreateNewStock(ActionEvent e) throws IOException {
        display(currUser, "CreateNewStocks.fxml");
        Controller.prevTitle = Controller.currTitle;
    }

    public void ChangeName(ActionEvent e) throws IOException {
        display(currUser, "AdminChangeName.fxml");
        Controller.prevTitle = Controller.currTitle;
    }

    public void ChangePassword(ActionEvent e) throws IOException {
        display(currUser, "AdminChangePassword.fxml");
        Controller.prevTitle = Controller.currTitle;
    }

    public void EndSession(ActionEvent e) throws IOException {
        display(currUser, "AdminScene.fxml");
        Controller.prevTitle = Controller.currTitle;
        try {
            Data.exportToCSV("CSV Data");
        } catch (IOException exception) {
            WarningMessage.show("Error", "Error while making a CSV file");
        }
    }

    public void GoToApprovalSystem(ActionEvent e) throws IOException {
        display(currUser, "ApprovalSystem.fxml");
        Controller.prevTitle = Controller.currTitle;
    }
}
