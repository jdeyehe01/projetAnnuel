package Controller.showController;

import Controller.ControllerApi;
import Model.Locate;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowLocate extends ControllerInitShow implements Initializable {

    @FXML
    private Accordion accordionView;

    @FXML
    private ComboBox cbListConference;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbListConference = super.ComboBoxInitConference(cbListConference);
    }

    @FXML
    public void initialisationLocate(ActionEvent event) {

        try {
            VBox content = new VBox();

            accordionView.setVisible(true);
            accordionView.getPanes().clear();


            String idConference = ((ComboBox) event.getSource()).getValue().toString().split("-")[0];
            String url = "locate/getAll/" + idConference;

            String jsonLocate = new ControllerApi().get(url);

            Locate[] tabLocate = new Gson().fromJson(jsonLocate, Locate[].class);

            for (Locate locate : tabLocate) {
                TitledPane t = new TitledPane();
                t.setText(locate.getName());

                content.getChildren().add(new Label("Nom: " + locate.getName()));
                content.getChildren().add(new Label("Adresse: " + locate.getAddress()));
                content.getChildren().add(new Label("Code postal: " + locate.getCityCode()));
                content.getChildren().add(new Label("Ville: " + locate.getCity()));

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
