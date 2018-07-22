package Controller.showController;

import Controller.ControllerApi;
import Model.Conference;
import Model.Task;
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

public class ControllerShowTask extends ControllerInitShow implements Initializable  {

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
    public void initialisationTask(ActionEvent event) {
        try {
            VBox content = new VBox();

            accordionView.setVisible(true);
            accordionView.getPanes().clear();


            String idConference = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
            String url = "task/getAllTaskForConference/"+idConference;

            String jsonTask = new ControllerApi().get(url);

            Task[] tabTask = new Gson().fromJson(jsonTask, Task[].class);

            for (Task task : tabTask) {
                TitledPane t = new TitledPane();
                t.setText(task.getTitle());

                content.getChildren().add(new Label("Titre: " + task.getTitle()));
                content.getChildren().add(new Label("Durée: " + task.getDuration()));
                content.getChildren().add(new Label("Montant: " + task.getAmount()));

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