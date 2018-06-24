package Controller;

import Model.Conference;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowView implements Initializable {

    @FXML
    private Accordion accordionView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.showConference();
    }


    public void showConference() {
        try {
            accordionView.getPanes().clear();
            String jsonConferences = new ControllerApi().get("conference/getAllByUser/1");
            String date = null;
            Conference[] tabConference = new Gson().fromJson(jsonConferences, Conference[].class);

            for (Conference c : tabConference) {
                TitledPane t = new TitledPane();
                t.setText(c.getName());

                VBox content = new VBox();
                content.getChildren().add(new Label("Description: " + c.getDescription()));
                if(c.getDate()!=null){
                    date = c.getDate().split("T")[0];
                }
                content.getChildren().add(new Label("Date: " + date));
                content.getChildren().add(new Label("Heure: " + c.gettime()));

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
