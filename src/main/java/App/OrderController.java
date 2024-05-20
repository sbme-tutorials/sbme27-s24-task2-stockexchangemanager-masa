
package App;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

import java.io.IOException;

public class OrderController extends Controller{


    private static Stage stage ;


    public static final ObservableList<Order> orderObservableList = FXCollections.observableArrayList();

    public static final ObservableList<Order> ownedAssetsObservableList = FXCollections.observableArrayList();


    @FXML
    private TableColumn<Order, String> orderTypeColumn;
    @FXML
    private TableColumn<Order, String> companySymbolColumn;

    @FXML
    private TableColumn<Order, Integer> numberOfOrderedStocksColumn;


    @FXML
    private TableColumn<Order, String> ownedCompanySymbolColumn;

    @FXML
    private TableColumn<Order, Integer> numberOfOwnedStocksColumn;

    @FXML
    private TableColumn<Order, Double> amountDepositedOrWithdrawnColumn;

    @FXML
    private TableColumn<Order, StringProperty> currentOrderStateColumn;

    @FXML
    private TableView<Order> tableViewOrders;

    @FXML
    private TableView<Order> tableViewOwnedAssets;

    public void initialize() {

        currUser = Data.Users.get(Data.TempID);
        companySymbolColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("companySymbol"));
        orderTypeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderType"));
        numberOfOrderedStocksColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("numberOfOrderedStocks"));
        amountDepositedOrWithdrawnColumn.setCellValueFactory(new PropertyValueFactory<Order, Double>("amountDepositedOrWithdrawn"));
        currentOrderStateColumn.setCellValueFactory(new PropertyValueFactory<Order, StringProperty>("currentOrderState"));
        ownedCompanySymbolColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("ownedCompanySymbols"));
        numberOfOwnedStocksColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("numberOfOwnedStocks"));


        tableViewOrders.setItems(orderObservableList);
        tableViewOwnedAssets.setItems(ownedAssetsObservableList);
    }


    public void setPrimaryStage() {
        OrderController.stage = currStage;
    }

    @FXML
    public void setPreviousScene() throws IOException {
        OrderController.stage.setScene(Account.getAccountScene());
        OrderController.stage.setTitle("Account");
//        display(currUser,"Account.fxml");
    }



}
