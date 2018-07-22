package Controller;

import Model.Conference;
import Model.Locate;
import Model.Presentation;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPresentation implements Initializable {

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfAmount;

    @FXML
    private TextArea tfDesc;

    @FXML
    private void save() throws IOException {

        ControllerApi api = new ControllerApi();
        String jsonConf = api.get("conference/lastConf");

        Conference conference = new Gson().fromJson(jsonConf,Conference.class);

        Presentation presentation = new Presentation(tfTitle.getText(),Float.parseFloat(tfAmount.getText()),tfDesc.getText(),conference);

        String jsonPresentation = new Gson().toJson(presentation);

        api.post("presentation",jsonPresentation);

    }


    public void initialize(URL location, ResourceBundle resources) {

    }
}