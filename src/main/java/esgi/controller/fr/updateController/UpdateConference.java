package esgi.controller.fr.updateController;

import esgi.controller.fr.AlertMessage;
import esgi.controller.fr.ControllerApi;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Conference;
import esgi.model.fr.User;
import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UpdateConference extends ControllerInitConference implements Initializable {


    @BeanFromDataBase
    private Conference conference;


    @FXML
    private TextField tfName;

    @FXML
    private TextField tfTime;

    @FXML
    private DatePicker tfDate;

    @FXML
    private TextArea tfDesc;


    @FXML
    private Button btnSave;

    @FXML
    private ComboBox cbConference;

    @FXML
    private Button btnDelete;

    private AlertMessage alert;


    @FXML
    public void updateDataBase() throws IOException {

        if(tfName.getText().isEmpty() || tfTime.getText().isEmpty() ||tfDesc.getText().isEmpty() ||tfDate.getEditor().getText().isEmpty() ){
            alert.notificationAndWait("Tous les champs ne sont pas correctes");
            return;
        }

        conference.setName(tfName.getText());
        conference.setTime(tfTime.getText());
        conference.setDescription(tfDesc.getText());
        conference.setDate(tfDate.getValue().toString());

        String jsonConference = new Gson().toJson(conference, Conference.class);

        new ControllerApi().put("conference/update/" + conference.getId(), jsonConference);
        new AlertMessage().notificationAndWait("La conférence " +conference.getName() + " a été mis à jour");

        tfDate.getEditor().clear();
        tfDesc.clear();
        tfTime.clear();
        tfName.clear();

    }



    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        try {

            tfName.clear();
            tfTime.clear();
            tfDesc.clear();
            tfDate.getEditor().clear();


            String idConference = ((ComboBox) event.getSource()).getValue().toString().split("-")[0];

            String url = "conference/getById/" + idConference;
            btnSave.setVisible(true);
            conference = (Conference) new ControllerAnnotation().getBean(url, this.getClass(), Conference.class);

            tfName.setText(conference.getName());
            tfTime.setText(conference.getTime());
            tfDesc.setText(conference.getDescription());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = conference.getDate().split("T")[0];


            Date date = formatter.parse(dateInString);

            formatter = new SimpleDateFormat("dd/MM/yyyy");

            tfDate.getEditor().setText(formatter.format(date));

            btnSave.setDisable(false);

            if (btnDelete.isVisible()) {
                btnSave.setVisible(false);
            } else {
                btnSave.setVisible(true);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    @FXML
    public void delete(ActionEvent event) throws IOException, IllegalAccessException, InstantiationException {

        String idConference = cbConference.getValue().toString().split("-")[0];

        String url = "conference/deleteConference/" + idConference;
        int code = new ControllerApi().delete(url);


        cbConference = super.ComboBoxInitConference(cbConference);

        if(code == 200){
            alert.notificationAndWait("La conférence" + conference.getName() + " a été supprimé");
            tfDate.getEditor().clear();
            tfDesc.clear();
            tfTime.clear();
            tfName.clear();


        }else{
            alert.notificationAndWait("La conférence" + conference.getName() + " n'a été supprimé");

        }

    }

    public void initialize(URL location, ResourceBundle resources) {


        cbConference = super.ComboBoxInitConference(cbConference);
        alert = new AlertMessage();


    }
}
