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
import javafx.scene.control.TextField;
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
    public void saveInBdd() throws IOException {

        ControllerApi api = new ControllerApi();
        String jsonConf = api.get("http://localhost:8080/conference/lastConf");

        Conference conference = new Gson().fromJson(jsonConf,Conference.class);

        Locate location = new Locate(tfName.getText(),tfAddress.getText(),Integer.parseInt(tfCityCode.getText()),tfCity.getText(), conference);

        String jsonLocate = new Gson().toJson(location);

        api.post("http://localhost:8080/locate",jsonLocate);

    }

    @FXML
    public void navigateTo(ActionEvent event) throws IOException {
        Parent createConf = FXMLLoader.load(getClass().getResource("../View/taskView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Créer une tâche ");

        stage.setScene(new Scene(createConf, createConf.getLayoutX(), createConf.getLayoutY()));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
