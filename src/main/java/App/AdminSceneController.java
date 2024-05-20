package App;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.util.Map;

public class AdminSceneController extends Controller implements Initializable{
    private String selectedUser;
    private String selectedAdmin;
    private AdminGenerator adminGenerator;
    private Admin currAdmin;
    Stage user_stage;

    @FXML
    private TitledPane viewProfile;
    @FXML
    private Button logout;
    @FXML
    private Button startSession;
    @FXML
    private Button createNewUser;
    @FXML
    private Button createNewAdmin;
    @FXML
    private Button delete;
    @FXML
    private Button retrieveUser;
    @FXML
    private MenuItem changeName;
    @FXML
    private MenuItem changePassword;
    @FXML
    private TextField nameToRetrieve;
    @FXML
    private ListView<String> admins;
    @FXML
    private ListView<String> users;
    @FXML
    private Label username;
    @FXML
    private Label adminID;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Controller.currTitle = currStage.getTitle();
        currUser = Data.Admins.get(Data.TempID);
        currAdmin = (Admin) currUser;
        username.setText(currUser.GetUsername());
        adminID.setText(Objects.toString(currUser.getId()));
        viewProfile.setExpanded(false);
        add_items(Data.Admins, admins);
        add_items(Data.Users, users);
        try {
            DeleteSelected(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        adminGenerator = AdminGenerator.getAdminGenerator();
    }


    public void LogOut(ActionEvent e) throws IOException {
        // display("hello-view.fxml");
        display("temp.fxml");
    }


    public void RetrieveUser(ActionEvent e) throws IOException {
        // System.out.println(Data.Users.size());
        // User deleted = new NormalUser("ahmed", "123456789");
        // Data.DeletedUsers.put(deleted.getId(), deleted);

        String usernameRetrieve = nameToRetrieve.getText();
        User OldUser = currAdmin.retrieveUser(usernameRetrieve);
        users.getItems().add(OldUser.GetUsername());

        // System.out.println(Data.Users.size());
    }


    public void DeleteSelected(ActionEvent e) throws IOException {
        users.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedUser =  users.getSelectionModel().getSelectedItem();
            }
        });
        admins.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedAdmin =  admins.getSelectionModel().getSelectedItem();
            }
        });
        if (selectedAdmin != null) {
            User admin = getAdmin(selectedAdmin);
            adminGenerator.remove_admin(admin);
            admins.getItems().remove(selectedAdmin);
            if (Data.Admins.size() == 0) {
                WarningMessage.show("LastAdmin", "Create at least one Admin");
                display("hello-view.fxml");
                // display("Temp.fxml");
            }
        }
        if (selectedUser != null) {
            currAdmin.deleteUser(selectedUser);
            users.getItems().remove(selectedUser);
        }
    }


    private User getAdmin(String username) {
        for (Map.Entry<Integer, User> set : Data.Admins.entrySet()) {
            if (set.getValue().GetUsername().equals(username)) {
                return set.getValue();
            }
        }
        return null;
    }


    public void CreateNewAdmin(ActionEvent e) throws IOException {
        display(currUser, "AdminCreation.fxml");
        Controller.prevTitle = Controller.currTitle;
    }


    public void CreateNewUser(ActionEvent e) throws IOException {
        display(currUser, "AdminToCreateNewUser.fxml");
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


    public void StartSession(ActionEvent e) throws IOException {
        display(currUser, "Session.fxml");
        Controller.prevTitle = Controller.currTitle;
    }


    public void add_items(Map<Integer, User> list, ListView<String> listView) {
        for (Map.Entry<Integer, User> set : list.entrySet()) {
            listView.getItems().add(set.getValue().GetUsername());
        }
    }
}
