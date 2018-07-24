package esgi.controller.fr;

import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import esgi.annotation.fr.NumberValue;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Conference;
import esgi.model.fr.Locate;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLocate extends ControllerInitConference implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    @NumberValue
    private TextField tfCityCode;

    @FXML
    private ScrollPane listLocate;

    @FXML
    private VBox contentLocate;

    @FXML
    private ComboBox cbListConference;

    @FXML
    private Button btnAddLocate;

    @BeanFromDataBase
    private Conference conference;

    @FXML
    private Button btnSave;

    @FXML
    public void saveInBdd() throws IOException {
        try {
            if(!new ControllerAnnotation().isNumber(this.getClass(),tfCityCode) || tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfCity.getText().isEmpty() || tfCityCode.getText().isEmpty() ){
                new AlertMessage().notificationAndWait("Tous les champs ne sont pas correctes");
                return;
            }

            String idConf = cbListConference.getValue().toString().split("-")[0];
            conference = (Conference) new ControllerAnnotation().getBean("conference/conferenceById/" + idConf, this.getClass(), Conference.class);
            Locate location = new Locate(tfName.getText(), tfAddress.getText(), Integer.parseInt(tfCityCode.getText()), tfCity.getText(), conference);

            String jsonLocate = new Gson().toJson(location,Locate.class);

            int codeResponse = new ControllerApi().post("locate/" + conference.getId(), jsonLocate);

            if (codeResponse == 200) {
                contentLocate.getChildren().add(new Label(tfName.getText()));
                listLocate.setContent(contentLocate);
                new AlertMessage().notificationAndWait("Le lieu " + location.getName() + " a été créé");

            }else{
                System.out.println(codeResponse);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initFormCreate() {
        tfName.setDisable(false);
        tfCity.setDisable(false);
        tfCityCode.setDisable(false);
        tfAddress.setDisable(false);
        btnSave.setDisable(false);
        btnAddLocate.setDisable(false);
    }


    @FXML
    public void newLocate() throws IOException {
        contentLocate.getChildren().add(new Label(tfName.getText() + " - " + tfCity.getText()));
        listLocate.setContent(contentLocate);
        this.saveInBdd();

        tfAddress.clear();
        tfCity.clear();
        tfCityCode.clear();
        tfName.clear();
    }


    public void initialize(URL location, ResourceBundle resources) {
        cbListConference = super.ComboBoxInitConference(cbListConference);

    }




}
