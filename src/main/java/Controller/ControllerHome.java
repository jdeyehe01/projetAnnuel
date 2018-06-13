package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    public void navigate(ActionEvent event) throws IOException {

        Parent createConference = FXMLLoader.load(getClass().getResource("../View/CreateConfView.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Créer une Conference ");
        stage.setScene(new Scene(createConference, createConference.getLayoutX(), createConference.getLayoutY()));
        stage.show();
    }

    @FXML
    public void updateConference(ActionEvent event) throws IOException {
        Parent updateConf = FXMLLoader.load(getClass().getResource("../View/UpdateViews/updateConfView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier votre conference ");

        stage.setScene(new Scene(updateConf, updateConf.getLayoutX(), updateConf.getLayoutY()));
        stage.show();
    }


}
