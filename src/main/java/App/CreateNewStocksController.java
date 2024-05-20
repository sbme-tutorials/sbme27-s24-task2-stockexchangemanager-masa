package App;

import java.io.IOException;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateNewStocksController extends Controller{
    private String symbol;
    private double price;

    @FXML
    private TextField CompanySymbol;
    @FXML
    private TextField StockPrice;
    @FXML
    private Button create;
    @FXML
    private Button cancel;

    public void initialize() {
        Controller.currTitle = currStage.getTitle();
        currUser = Data.Admins.get(Data.TempID);
    }

    public void Create(ActionEvent e) throws IOException {
        try {
            symbol = CompanySymbol.getText();
            price = Integer.parseInt(StockPrice.getText());
            final boolean checkSymbol = Pattern.matches("[A-Za-z]+", symbol);

            if (checkSymbol) {
                Data.addSymbol(symbol, price);
                display(currUser, Controller.prevTitle + ".fxml");
                Controller.prevTitle = Controller.currTitle;
            } else {
                WarningMessage.show("Wrong Input", "* Symbol must contain only word characters.");
            }
        } catch (Exception exception){
            System.out.println(exception);
            WarningMessage.show("Invalid Input", "Price and number of stocks must be integers");
        }
    }

    public void Cancel(ActionEvent e) throws IOException {
        display(currUser, Controller.prevTitle + ".fxml");
        Controller.prevTitle = Controller.currTitle;
    }
}
