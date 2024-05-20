package App;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
//    AccountController accountController=new AccountController();
//    OrderController orderController = new OrderController();

//    static User temp1 = Data.userFactory.GetUser(UserFactory.NORMAL);
    public static Stage stage;
//    static HelloController controller ;
//    Stock mystock = new Stock();
    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
//        Scene scene = new Scene(root, 600, 400);
//        stage.setScene(scene);
//        stage.show();

        HelloApplication.stage = stage;
//        accountController.setPrimaryStage(HelloApplication.stage);
//        orderController.setPrimaryStage(HelloApplication.stage);

        ChangeSceneController controller1 = new ChangeSceneController();
        controller1.display("hello-view.fxml");
        new Market("AAPL", 20.0, 12);
        new Market("Ahmed", 4321, 3224);

//        accountController.setPrimaryStage();
//        orderController.setPrimaryStage();
//        draw_chart(mystock,stage); sherifelgendy
    }

    public static void main(String[] args)
    {


        try{
            Data.initStockData();
            User currentAdmin = new Admin("mazenmazen", "mazenmazen");
            Data.Admins.put(currentAdmin.getId(), currentAdmin);
        }
        catch (Exception e) {
            System.out.println("Error initing stocks data");
            System.out.println(e);
        }

//        temp1.setUsername("sherifelgendy");
//        temp1.setPassword("sherifelgendy");

        launch(args);

    }

//    public static void draw_chart(Stock stock,Stage stage) throws IOException {
//         controller = new HelloController(stock);
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("D:\\SBME\\oop\\stock\\stock_exchange_manager\\UI\\hello-view.fxml"));
//        fxmlLoader.setController(controller);
//        Scene scene = new Scene(fxmlLoader.load(), 600, 600, Color.LIGHTBLUE);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
}
