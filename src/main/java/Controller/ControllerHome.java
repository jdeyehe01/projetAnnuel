package Controller;

import Model.Conference;
import annotation.BeanFromDataBase;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    private ComboBox cbConference;

    @FXML
    private Button btnUpdate;

   /* @BeanFromDataBase(url = "http://localhost:8080/conference/getAll" , className = Conference.class)
    private static List*/






    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    public void navigate(ActionEvent event) throws IOException {

        Parent createConf = FXMLLoader.load(getClass().getResource("../View/CreateConfView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Conference ");

        stage.setScene(new Scene(createConf, createConf.getLayoutX(), createConf.getLayoutY()));
        stage.show();


    }


}
