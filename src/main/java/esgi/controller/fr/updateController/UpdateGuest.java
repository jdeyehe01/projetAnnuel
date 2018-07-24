package esgi.controller.fr.updateController;

import esgi.annotation.fr.StringValue;
import esgi.controller.fr.AlertMessage;
import esgi.controller.fr.ControllerApi;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Conference;
import esgi.model.fr.Guest;
import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import esgi.model.fr.User;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateGuest extends ControllerInitConference implements Initializable {


    @FXML
    @StringValue
    private TextField tfLastName;

    @FXML
    @StringValue
    private TextField tfFirstName;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox cbListConference;

    @BeanFromDataBase
    private Guest guest;


    @FXML
    private ComboBox cbListGuest;

    @FXML
    private Button btnDelete;

    @FXML
    public void updateGuest() throws IOException, InstantiationException, IllegalAccessException {
        cbListGuest.setDisable(false);

        if (cbListGuest.getItems().size() > 0) {
            cbListGuest.getProperties().clear();
        }

        String idConference = cbListConference.getValue().toString().split("-")[0];

        String allGuest = new ControllerApi().get("guest/getAllGuest/" + idConference);

        Guest[] tabGuest = new Gson().fromJson(allGuest, Guest[].class);


        List<Guest> listGuest = Arrays.asList(tabGuest);
        ArrayList<String> listNameGuset = new ArrayList<String>();

        for (Guest guest : listGuest) {
            listNameGuset.add(guest.getId() + "-" + guest.getlname() + " " + guest.getfname());
        }

        cbListGuest.getItems().addAll(listNameGuset);
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfFirstName.clear();
        tfLastName.clear();
        tfEmail.clear();

        String idGuest = ((ComboBox) event.getSource()).getValue().toString().split("-")[0];
        String url = "guest/guestById/" + idGuest;
        btnSave.setVisible(true);
        guest = (Guest) new ControllerAnnotation().getBean(url, this.getClass(), Guest.class);

        tfFirstName.setText(guest.getfname());
        tfLastName.setText(guest.getlname());
        tfEmail.setText(guest.getEmail());

        if (btnDelete.isVisible()) {
            btnSave.setVisible(false);
        } else {
            btnSave.setVisible(true);
        }


    }


    @FXML
    public void updateDataBase() throws IOException {

        try {
            if (new ControllerAnnotation().isString(this.getClass(), tfFirstName) || new ControllerAnnotation().isString(this.getClass(), tfFirstName) || tfEmail.getText().isEmpty()) {
                new AlertMessage().notificationAndWait("Les champs ne sont pas correctes");
                return;
            }
            guest.setlname(tfLastName.getText());
            guest.setEmail(tfEmail.getText());
            guest.setfname(tfFirstName.getText());

            String jsonGuest = new Gson().toJson(guest, Guest.class);

            new ControllerApi().put("guest/update/" + guest.getId(), jsonGuest);
            this.updateGuest();
            new AlertMessage().notificationAndWait("L'invité " + guest.getfname() + " a été mis à jour");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void deleteGuest() throws IOException {
        try {
            String idGuest = cbListGuest.getValue().toString().split("-")[0];
            String url = "guest/deleteGuest/" + idGuest;
            this.updateGuest();

            int responseCode = new ControllerApi().delete(url);
            if(responseCode == 200){
                new AlertMessage().notificationAndWait("L'invité " + guest.getfname() + " a été supprimé");

            }else{
                new AlertMessage().notificationAndWait("L'invité " + guest.getfname() + " n'a pas été supprimé");

            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public void initialize(URL location, ResourceBundle resources) {
        cbListConference = super.ComboBoxInitConference(cbListConference);
    }
}
