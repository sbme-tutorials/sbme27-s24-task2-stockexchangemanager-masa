package App;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller {

    protected final Stage currStage = HelloApplication.stage;
    protected Scene currScene;
    protected Parent root;
    protected User currUser;
    protected static String prevTitle;
    protected static String currTitle;

    public void display(User user, String sceneName) throws IOException {
        Data.TempID = user.getId();

        try {
            root = FXMLLoader.load(getClass().getResource(sceneName));
        } catch (IOException e) {
            WarningMessage.show("Error", "FXML root File Exception");
        }
        currScene = new Scene(root);

        String[] title = sceneName.split("\\.", 2);
        currStage.setTitle(title[0]);
        // currStage.getIcons().add(image);
        currStage.setScene(currScene);
        currStage.setResizable(false);
        currStage.show();
    }

    public void display(String sceneName) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource(sceneName));
        } catch (IOException e) {
            WarningMessage.show("Error", "FXML root File Exception");
        }
        currScene = new Scene(root);

        String[] title = sceneName.split("\\.", 2);
        currStage.setTitle(title[0]);
        // currStage.getIcons().add(image);
        currStage.setScene(currScene);
        currStage.setResizable(false);
        currStage.show();
    }

}
