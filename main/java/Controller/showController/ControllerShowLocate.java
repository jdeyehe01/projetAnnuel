package Controller.showController;

import Controller.ControllerApi;
import Model.Locate;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowLocate extends ControllerInitConference implements Initializable {

    @FXML
    private Accordion accordionView;

    @FXML
    private ComboBox cbListConference;

    private WebView wMap;

    public void showMap() {
            Group root = new Group();
            wMap.autosize();
            root.getChildren().add(wMap);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Before Show - Map");
            stage.setScene(new Scene(root , 800,600));
            stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbListConference = super.ComboBoxInitConference(cbListConference);
        wMap = new WebView();
    }

    public void initActinBtnShowMap(Locate l, Button btn){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String[] address = l.getAddress().split(" ");
                StringBuffer url = new StringBuffer("https://www.google.fr/maps/place/");
                for(String s : address){
                    url.append(s);
                    url.append("+");
                }
                url.append(l.getCityCode()+"+"+l.getCity());
                wMap.getEngine().load(url.toString());
                showMap();
            }
        });

    }

    @FXML
    public void initialisationLocate(ActionEvent event) {
        try {
            accordionView.setVisible(true);
            accordionView.getPanes().clear();
            String idConference = ((ComboBox) event.getSource()).getValue().toString().split("-")[0];
            String url = "locate/getAll/" + idConference;
            String jsonLocate = new ControllerApi().get(url);
            Locate[] tabLocate = new Gson().fromJson(jsonLocate, Locate[].class);

            for (Locate locate : tabLocate) {
                TitledPane t = new TitledPane();
                VBox content = new VBox();
                Button btnMap = new Button("Afficher la carte");
                t.setText(locate.getName());
                content.getChildren().add(new Label("Nom: " + locate.getName()));
                content.getChildren().add(new Label("Adresse: " + locate.getAddress()));
                content.getChildren().add(new Label("Code postal: " + locate.getCityCode()));
                content.getChildren().add(new Label("Ville: " + locate.getCity()));
                initActinBtnShowMap(locate,btnMap);
                content.getChildren().add(btnMap);

                t.setContent(content);
                t.setExpanded(true);
                accordionView.getPanes().add(t);
            }
            accordionView.autosize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
