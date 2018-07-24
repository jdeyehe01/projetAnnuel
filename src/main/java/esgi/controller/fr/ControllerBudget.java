package esgi.controller.fr;

import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import esgi.annotation.fr.NumberValue;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Budget;
import esgi.model.fr.Conference;
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

public class ControllerBudget extends ControllerInitConference implements Initializable {

    @FXML
    private TextField tfName;

    @NumberValue
    @FXML
    private TextField tfAmount;

    @FXML
    private ScrollPane listBudgetPan;

    @FXML
    private Button btnAddBudget;

    @FXML
    private Button btnSave;

    @BeanFromDataBase
    private Conference conference;

    @FXML
    private VBox content;

    private ControllerApi api;
    private ArrayList<Budget> listBudget;

    private AlertMessage alert;


    @FXML
    private ComboBox cbListConference;

    @FXML
   public void initFormCreate(){
        tfAmount.setDisable(false);
        tfName.setDisable(false);
        btnAddBudget.setDisable(false);
        btnSave.setDisable(false);
    }

    @FXML
    public void save() throws IOException {
        try {

            if(new ControllerAnnotation().isNumber(this.getClass(),tfAmount) || tfAmount.getText().isEmpty() || tfName.getText().isEmpty()){
                alert.notificationAndWait("Tous les champs ne sont pas correcte");
            }
            String idConference = cbListConference.getValue().toString().split("-")[0];
            String url = "conference/conferenceById/" + idConference;

            conference = (Conference) new ControllerAnnotation().getBean(url, this.getClass(), Conference.class);
            Budget budget = new Budget(tfName.getText(), Float.parseFloat(tfAmount.getText()), conference);

            String jsonBudget = new Gson().toJson(budget);
            content.getChildren().add(new Label(budget.getTitle()));
            listBudgetPan.setContent(content);
            listBudgetPan.setVisible(true);
            api.post("budget/"+idConference, jsonBudget);

            alert.notificationAndWait("Le budget " +budget.getTitle() + " a été créé");


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public void initialize(URL location, ResourceBundle resources) {

        cbListConference = super.ComboBoxInitConference(cbListConference);
        api = new ControllerApi();
        listBudget = new ArrayList<Budget>();
        content = new VBox(1);
        alert = new AlertMessage();
        if (content.getChildren().size() <= 0) {
            listBudgetPan.setVisible(false);
        }


    }


    @FXML
    public void newBudget() throws IOException {

        this.save();
        tfName.clear();
        tfAmount.clear();

    }
}
