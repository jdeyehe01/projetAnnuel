package Controller;

import Model.Conference;
import Model.Locate;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLocate implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfCityCode;

    @FXML
    private ScrollPane listLocate;

    @FXML
    private VBox contentLocate;


    @FXML
    public void saveInBdd() throws IOException {

        ControllerApi api = new ControllerApi();
        String jsonConf = api.get("conference/lastConf");

        Conference conference = new Gson().fromJson(jsonConf,Conference.class);

        Locate location = new Locate(tfName.getText(),tfAddress.getText(),Integer.parseInt(tfCityCode.getText()),tfCity.getText(), conference);

        String jsonLocate = new Gson().toJson(location);

        api.post("locate/",jsonLocate);

        contentLocate.getChildren().add(new Label(tfName.getText()));
        listLocate.setContent(contentLocate);
    }

    @FXML
    public void newLocate() throws IOException {
        this.saveInBdd();

        tfAddress.clear();
        tfCity.clear();
        tfCityCode.clear();
        tfName.clear();


    }




    public void initialize(URL location, ResourceBundle resources) {

    }
}
