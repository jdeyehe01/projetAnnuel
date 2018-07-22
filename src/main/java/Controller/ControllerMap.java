package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMap implements Initializable {

    @FXML
    private WebView wMap;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        wMap.getEngine().load("https://www.google.fr/maps/place/12+Avenue+du+Président+Kennedy,+75016+Paris");

    }
}
