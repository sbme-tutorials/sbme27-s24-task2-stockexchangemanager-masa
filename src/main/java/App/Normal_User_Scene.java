package App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import java.io.IOException;



public class Normal_User_Scene extends Controller  {
    @FXML
    private TitledPane profileVeiw;
    @FXML
    private Label usernameField ,UserId;
//    @FXML
//    private ChoiceBox<String> choiceBox;
    @FXML
    private Label LabelField;


//    FXMLLoader loader = new FXMLLoader();

//    private String chosedOption;
//    private String username ;

//    private final String[] Options = {"DisplayTransactionHistory", "ExportStockHistory" , "Be premium for 10$" };


    //     private Account account ;   //until Account class created
    @FXML
    private Button bePremium;
    private static boolean premium = false;
    // private ArrayList<Order> transactions = new ArrayList<Order>(); until order class created
//    StockSubject stocksubject;


    public void initialize() {
        currUser = Data.Users.get(Data.TempID);
        if (Data.PremiumUsers.containsValue(currUser)){
            bePremium.setText("unsubscribe");
        }
//        bePremium.setOnAction(event -> bePremium());

        LabelField.setVisible(false);
    }


    @FXML
    public void AfterCancel(ActionEvent event) throws IOException {
//        ShowStage("hello-view.fxml",event);
        display("hello-view.fxml");

    }

//    @FXML
//    public void ShowName(){
//        LabelField.setText(Data.Users.get(Data.getUserIndex()).GetUsername());
//    }



//    @FXML
//    public  void changeScene(ActionEvent event){
//        chosedOption = choiceBox.getValue();
//        switch (chosedOption){
//            case  "DisplayTransactionHistory":
//                //                    ShowStage("ExportHistory.fxml",event);
//                display("ExportHistory.fxml");
//                break;
//            case  "ExportStockHistory":
////                    ShowStage("ExportHistory.fxml",event);
//                display("ExportHistory.fxml");
//                break;
//            case  "Be premium for 10$" :
//                //                    ShowStage("ExportHistory.fxml",event);
//                display("ExportHistory.fxml");
//                break;
//        }
//
//


     public void bePremium(ActionEvent event)throws IOException {

        if (!premium  && Account.getUserBalanceUsingIndex(currUser.id) > 10 ){
            premium = true;
            LabelField.setVisible(true);
            bePremium.setText("unsubscribe");
            Account.setUserBalanceUsingIndex(currUser.id);
           Data.PremiumUsers.put(currUser.id,currUser);

        }

        else if (Data.PremiumUsers.containsValue(currUser))  {
            Data.PremiumUsers.remove(currUser.id);
            premium = false;
            LabelField.setVisible(false);
            bePremium.setText("be premium 10$");
        }
    }

    @FXML
    public void changeToMarket(ActionEvent event) throws IOException{
//        ShowStage("Market.fxml",event);
//        display("Market.fxml");
        currStage.setScene(Market.getMarketScene());

    }
    @FXML
    public void changeToAccount(ActionEvent event) throws IOException{
//        ShowStage("Market.fxml",event);
//        display("Market.fxml");

        currStage.setScene((Account.getAccountScene()));
    }

    @FXML
    public void changeName(ActionEvent event) throws IOException{
//        ShowStage("ChangeName.fxml",event);
        display(currUser,"ChangeName.fxml");

    }
    @FXML
    public void changePassword(ActionEvent event) throws IOException{
//        ShowStage("ChangePassword.fxml",event);
        display(currUser,"ChangePassword.fxml");
    }

    @FXML
    public void JoinSession(ActionEvent event) throws IOException{
//        ShowStage("SessionScene.fxml",event);
        display(currUser,"SessionScene.fxml");
    }

    public static Scene getNormalScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Normal_User_Scene.class.getResource("NormalUserScene.fxml"));
        return (new Scene(fxmlLoader.load()));
    }

}
