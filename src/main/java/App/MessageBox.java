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

public class MessageBox {

    public static String title;
    public static String message;
    private static  Stage stageAlert;

    private static Stage stageInput;

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
        try {
            label.setText(message);
            btnOK.setOnAction(e -> stageAlert.close());
        } catch (Exception exception) {}
        try {
            errorMessage.setVisible(false);
        } catch (Exception exception) {}
    }

    public static void show(String title, String message) throws IOException {
        MessageBox.stageAlert = new Stage();
        MessageBox.title = title;
        MessageBox.message = message;

        stageAlert.setMinWidth(300);
        stageAlert.setMinHeight(120);
        stageAlert.initModality(Modality.APPLICATION_MODAL);
        stageAlert.setTitle(title);
        stageAlert.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(MessageBox.class.getResource("messageBox.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stageAlert.setScene(scene);
        stageAlert.showAndWait();
    }

    public static void showAndHandleInput(String title) throws IOException {

        MessageBox.stageInput = new Stage();
        MessageBox.title = title;

        stageInput.setMinWidth(300);
        stageInput.setMinHeight(120);
        stageInput.initModality(Modality.APPLICATION_MODAL);
        stageInput.setTitle(title);
        stageInput.setResizable(false);
        stageInput.setOnCloseRequest(e -> {enteredAmount = 0;});

        FXMLLoader fxmlLoader = new FXMLLoader(MessageBox.class.getResource("WithdrawAndDepositMessageBox.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        stageInput.setScene(scene);
        stageInput.showAndWait();
    }



    public boolean isPositiveDouble(TextField textField, Label errorMessage) throws IOException {
        if (!textField.getText().isBlank()) {
            try {
                NumberValidator.validateNumber(Double.parseDouble(textField.getText()));
                return true;
            } catch (NumberFormatException exception) {
                errorMessage.setText("Invalid Amount");
            } catch (NegativeNumberException exception) {
                errorMessage.setText("Negative Number Not Allowedx`");
            }
        }
        else {
            errorMessage.setText("Empty Field");
            errorMessage.setVisible(true);
        }
        return (false);
    }

    private void checkBalance() throws IOException {
        flag = true;
        if (AccountController.withdraw_deposit) {
            NumberValidator.validateAmountWithdrawn(Double.parseDouble(amount.getText()));
        }
    }

    @FXML
    public void validateAmount() throws IOException {
        errorMessage.setVisible(!isPositiveDouble(amount, errorMessage));
    }

    @FXML
    public void checkEmptyField() throws IOException {
        if (amount.getText().isBlank()) {
            errorMessage.setText("Empty Field");
            errorMessage.setVisible(true);
        }
    }


    @FXML
    public void confirmOrder() throws IOException {
        if (!errorMessage.isVisible()) {
            checkBalance();
            if (flag) {
                enteredAmount = (Double.parseDouble(amount.getText()));
                stageInput.close();
            }
        }
    }
}
