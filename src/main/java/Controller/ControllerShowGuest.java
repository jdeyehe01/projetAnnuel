package Controller;

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

public class ControllerShowGuest implements Initializable {

    @FXML
    private Accordion accordionView;

    @FXML
    private ComboBox cbListConference;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initialisationCb();
    }


    public void initialisationCb() {
        try {
            String jsonConferences = new ControllerApi().get("conference/getAllByUser/1");
            Conference[] tabConference = new Gson().fromJson(jsonConferences, Conference[].class);

            for (Conference c : tabConference) {
                cbListConference.getItems().add(c.getId()+"-"+c.getName());

            }

                cbListConference.autosize();

        } catch (IOException e) {
            e.printStackTrace();
        }


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
                t.setText(g.getfname());

                VBox content = new VBox();
                content.getChildren().add(new Label("First name: " + g.getfname()));
                content.getChildren().add(new Label("Last name: " + g.getlname()));
                content.getChildren().add(new Label("Email: " + g.getEmail()));

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
