package Controller;

import Model.Conference;
//import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.parser.JSONParser;
import sun.plugin.javascript.navig5.JSObject;

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

        Conference conference = new Conference(tfName.getText(),tfDate.getValue(),tfTime.getText(),tfDesc.getText());

        ControllerApi api = new ControllerApi();
        /*
        String jsonConf = api.get("http://localhost:8080/conference/lastConf");
        //System.out.println(test);
        JsonObject json = new JsonObject();


        Gson gson = new GsonBuilder().create();

        Conference c = gson.fromJson(jsonConf, Conference.class);
        */
       // Gson gson = new Gson();

        Json
       // json.pa


       // System.out.println("Object java to object json :" + gson.toJson(conference));
        //api.post("http://localhost:8080/conference/",gson.toJson(conference));

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
