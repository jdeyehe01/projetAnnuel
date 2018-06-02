package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Model.Conference;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ControllerConf implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfTime;

    @FXML
    private DatePicker tfDate;

    @FXML
    private TextArea tfDesc;


    @FXML
    public void signIn(javafx.event.ActionEvent actionEvent) throws IOException {

        Conference conference = new Conference(tfName.getText(),tfDate.dayCellFactoryProperty(),tfTime.getText(),tfDesc.getText());

        ControllerApi api = new ControllerApi();
        String test = api.get("http://localhost:8080/conference/lastConf");

        Gson gson = new GsonBuilder().create();

         Conference c = gson.fromJson(test, Conference.class);
        System.out.println(c);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
