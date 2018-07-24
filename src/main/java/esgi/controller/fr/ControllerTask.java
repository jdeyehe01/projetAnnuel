package esgi.controller.fr;

import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Conference;
import esgi.model.fr.Task;
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

public class ControllerTask extends ControllerInitConference implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfTime;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNext;

    @FXML
    private ComboBox cbListConference;

    @FXML
    private Button btnAddTask;

    @FXML
    private ScrollPane scrollPaneTask;

    private VBox content;

    @BeanFromDataBase
    private Conference conference;

    /*

        Enregistre
     */
    @FXML
    public void save() throws IOException {
        try {
            String idConference = cbListConference.getValue().toString().split("-")[0];
            String url = "conference/conferenceById/" + idConference;


            conference = (Conference) new ControllerAnnotation().getBean(url, this.getClass(), Conference.class);
            btnSave.setVisible(false);
            btnNext.setVisible(true);
            ControllerApi api = new ControllerApi();
            Task task = new Task(tfTitle.getText(), tfTime.getText(), conference);
            String jsonTask = new Gson().toJson(task, Task.class);

            api.post("task/"+conference.getId(), jsonTask);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {

        Parent createConf = FXMLLoader.load(getClass().getResource("../View/budgetView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Ajouter un budget ");

        stage.setResizable(false);

        stage.setScene(new Scene(createConf));
        stage.show();

    }

    @FXML
    public void initFormTask() {
        tfTitle.setDisable(false);
        tfTime.setDisable(false);
        btnNext.setDisable(false);
        btnSave.setDisable(false);
        btnAddTask.setDisable(false);
        btnSave.setDisable(false);
    }


    public void initialize(URL location, ResourceBundle resources) {
        cbListConference = super.ComboBoxInitConference(cbListConference);
        content = new VBox();
    }

    @FXML
    public void addNewTask() {
        content.getChildren().add(new Label(tfTitle.getText() + " " + tfTime.getText()));
        scrollPaneTask.setContent(content);
    }
}
