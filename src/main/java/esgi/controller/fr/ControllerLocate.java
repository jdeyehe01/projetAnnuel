package esgi.controller.fr;

import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
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
    private TextField tfCityCode;

    @FXML
    private ScrollPane listLocate;

    @FXML
    private VBox contentLocate;

    @FXML
    private Button btnNext;

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
            String idConf = cbListConference.getValue().toString().split("-")[0];
            conference = (Conference) new ControllerAnnotation().getBean("conference/conferenceById/" + idConf, this.getClass(), Conference.class);
            Locate location = new Locate(tfName.getText(), tfAddress.getText(), Integer.parseInt(tfCityCode.getText()), tfCity.getText(), conference);

            String jsonLocate = new Gson().toJson(location,Locate.class);

            int codeResponse = new ControllerApi().post("locate/" + conference.getId(), jsonLocate);

            if (codeResponse <= 201) {
                btnNext.setVisible(true);
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
        btnNext.setDisable(false);
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


    @FXML
    public void navigateTo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/taskView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Ajouter une tâche ");

        stage.setResizable(false);
        stage.setScene(new Scene(root));

        stage.show();

    }


}
