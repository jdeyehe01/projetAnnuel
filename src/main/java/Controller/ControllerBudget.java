package Controller;

import Annotation.BeanFromDataBase;
import Annotation.ControllerAnnotation;
import Controller.showController.ControllerInitConference;
import Model.Budget;
import Model.Conference;
import Model.Task;
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

    @FXML
    private TextField tfAmount;

    @FXML
    private ScrollPane listBudgetPan;

    @FXML
    private Button btnAddBudget;

    @FXML
    private Button btnSave;


    @FXML
    private Button btnNext;

    @BeanFromDataBase
    private static Conference conference;

    @FXML
    private VBox content;

    private ControllerApi api;
    private ArrayList<Budget> listBudget;


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
            btnNext.setVisible(true);
            String idConference = cbListConference.getValue().toString().split("-")[0];
            String url = "conference/conferenceById/" + idConference;

            ControllerAnnotation.getBean(url, this.getClass(), conference);
            Budget budget = new Budget(tfName.getText(), Float.parseFloat(tfAmount.getText()), conference);

            String jsonBudget = new Gson().toJson(budget);
            content.getChildren().add(new Label(budget.getTitle()));
            listBudgetPan.setContent(content);
            listBudgetPan.setVisible(true);
            api.post("budget/"+idConference, jsonBudget);


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

    @FXML
    public void navigate(ActionEvent event) throws IOException {
        Parent createConf = FXMLLoader.load(getClass().getResource("../View/beforeShowWelcomeView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Accueil ");

        stage.setResizable(false);
        stage.setScene(new Scene(createConf));
        stage.show();
    }
}
