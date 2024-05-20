package App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.control.Label;

import java.io.IOException;

public class WarningMessage {

    public static String title;
    public static String message;
    private static  Stage stageAlert;
    @FXML
    Label label;
    @FXML
    Button btnOK;

    @FXML
    Label errorMessage;
    @FXML
    TextField amount;

    public static boolean flag = true;

    static double enteredAmount;

    /**
     *
     */
    public void initialize() {
            label.setText(message);
            btnOK.setOnAction(e -> stageAlert.close());
    }

    public static void show(String title, String message) throws IOException {
                WarningMessage.stageAlert = new Stage();
                WarningMessage.title = title;
                WarningMessage.message = message;

        stageAlert.setMinWidth(300);
        stageAlert.setMinHeight(120);
        stageAlert.initModality(Modality.APPLICATION_MODAL);
        stageAlert.setTitle(title);
        stageAlert.setResizable(false);

                FXMLLoader fxmlLoader = new FXMLLoader(WarningMessage.class.getResource("messageBox.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

        stageAlert.setScene(scene);
        stageAlert.showAndWait();
    }

}
