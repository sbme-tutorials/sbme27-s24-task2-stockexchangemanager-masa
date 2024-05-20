package App;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SessionSceneController extends Controller implements Initializable {

    @FXML
    private RadioButton candleId,lineId;
    @FXML
    private TitledPane profileVeiw;

    @FXML
    private ListView <String> companyList;
    @FXML
    private Label usernameField , UserId , checkerLabel;

    private String selectedItem;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currUser = Data.Users.get(Data.TempID);
        companyList.getItems().addAll(Data.stockData.keySet());
        companyList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedItem = companyList.getSelectionModel().getSelectedItem();

            }
        });
        profileVeiw.setExpanded(false);
        checkerLabel.setVisible(false);
        setInformation();
    }

    public void displayChart(ActionEvent event) {
        if (candleId.isSelected() && selectedItem!=null){
          CandlestickGraph graph = new CandlestickGraph(selectedItem,Data.CSVDirectory, 1200,700);

            Scene scene =new Scene( graph.graph(), 1000,700);
            currStage.setScene(scene);
            currStage.show();
        }
        else if (lineId.isSelected() && selectedItem!=null){
            checkerLabel.setVisible(true);
        }
        else
        {
            checkerLabel.setVisible(true);
        }
    }



    public void setInformation(){
        usernameField.setText(Data.Users.get(Data.TempID).username);  ;
        UserId.setText(String.valueOf((Data.Users.get(Data.TempID).getId())));
    }



    @FXML
    public void changeName(ActionEvent event) throws IOException {
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
