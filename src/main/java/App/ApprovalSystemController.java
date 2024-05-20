package App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import java.io.IOException;

public class ApprovalSystemController extends Controller{
    public static ObservableList<Order> OrderOL = FXCollections.observableArrayList();

    @FXML
    private Button approve;
    @FXML
    private Button reject;
    @FXML
    private Button back;
    @FXML
    private MenuItem changeName;
    @FXML
    private MenuItem changePassword;
    @FXML
    private Label username;
    @FXML
    private Label adminID;
    @FXML
    private TitledPane viewProfile;
    @FXML
    private TableView<Order> orders;
    @FXML
    private TableColumn<Order, String> usernameList;
    @FXML
    private TableColumn<Order, String> type;
    @FXML
    private TableColumn<Order, Double> amount;

    public void initialize() {
        Controller.currTitle = currStage.getTitle();
        currUser = Data.Admins.get(Data.TempID);
        viewProfile.setExpanded(false);
        // usernameList.setCellValueFactory(new PropertyValueFactory<Order, String>("usernameList"));
        // type.setCellValueFactory(new PropertyValueFactory<Order, String>("type"));
        // amount.setCellValueFactory(new PropertyValueFactory<Order, Double>("amount"));
        // orders.setItems(OrderOL);
    }

    public void Approve(ActionEvent e) {}

    public void Reject(ActionEvent e) {}

    public void Back(ActionEvent e) throws IOException {
        display(currUser, Controller.prevTitle + ".fxml");
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
}
