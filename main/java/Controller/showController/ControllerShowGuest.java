package Controller.showController;

import Controller.ControllerApi;
import Model.Conference;
import Model.Guest;
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

public class ControllerShowGuest extends ControllerInitConference implements Initializable {

    @FXML
    private Accordion accordionView;

    @FXML
    private ComboBox cbListConference;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initialisationCb();
    }


    public void initialisationCb() {

      cbListConference = super.ComboBoxInitConference(cbListConference);

    }

    @FXML
    public void initialisationGuest(ActionEvent event) {
        try {
            accordionView.setVisible(true);
            accordionView.getPanes().clear();

            String idConference = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
            String url = "guest/getAllGuest/"+idConference;
            String jsonGuest = new ControllerApi().get(url);
            Guest[] tabGuest = new Gson().fromJson(jsonGuest, Guest[].class);

            for (Guest g : tabGuest) {
                TitledPane t = new TitledPane();
                VBox content = new VBox();

                t.setText(g.getfname());

                content.getChildren().add(new Label("First name: " + g.getfname()));
                content.getChildren().add(new Label("Last name: " + g.getlname()));
                content.getChildren().add(new Label("Email: " + g.getEmail()));

                t.setContent(content);


                accordionView.getPanes().add(t);

            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
