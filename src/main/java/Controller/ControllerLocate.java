package Controller;

import Model.Conference;
import Model.Locate;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
