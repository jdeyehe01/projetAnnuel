package Controller;

import Controller.showController.ControllerInitConference;
import Model.Conference;
import Model.Guest;
import Annotation.BeanFromDataBase;
import Annotation.ControllerAnnotation;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGuest extends ControllerInitConference implements Initializable {

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfEmail;

    @FXML
    private VBox verticalBox;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnNewGuest;

    @FXML
    private Button btnSave;

    @FXML
    private ScrollPane scrollPaneGuest;

    @BeanFromDataBase
    private static Conference conference;

    @FXML
    private ComboBox cbListConference;

    @BeanFromDataBase
    private static Guest guest;

    private VBox content;


    @FXML
    public void save() throws IOException {

        Guest guest = new Guest(tfFirstName.getText(), tfLastName.getText(), tfEmail.getText(), conference);
        String jsonGuest = new Gson().toJson(guest);
        int responseCode = new ControllerApi().post("guest/" + conference.getId(), jsonGuest);
        if (responseCode <= 201) {
            verticalBox.getChildren().add(new Label(tfFirstName.getText() + "-" + tfLastName.getText()));
            scrollPaneGuest.setContent(verticalBox);
            scrollPaneGuest.setVisible(true);
            verticalBox.setVisible(true);
            btnNext.setVisible(true);
        }


    }

    @FXML
    public void newGuest() throws IOException {

        this.save();
        tfLastName.clear();
        tfEmail.clear();
        tfFirstName.clear();
    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/locateConfView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Ajouter un lieu ");

        stage.setResizable(false);
        stage.setScene(new Scene(root));

        stage.show();

    }

    @FXML
    public void initConferenceBean() {

        try {
            String idConference = cbListConference.getValue().toString().split("-")[0];
            String url = "conference/conferenceById/" + idConference;
            new ControllerAnnotation().getBean(url, this.getClass(), conference);
            tfLastName.setDisable(false);
            tfEmail.setDisable(false);
            tfFirstName.setDisable(false);
            btnNewGuest.setDisable(false);
            btnNext.setDisable(false);
            btnSave.setDisable(false);


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public void initialize(URL location, ResourceBundle resources) {


        cbListConference = super.ComboBoxInitConference(cbListConference);
        content = new VBox();


    }
}
