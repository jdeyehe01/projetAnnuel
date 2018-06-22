package Controller;

import Model.Conference;
import Model.Presentation;
import Model.Task;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTask implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfTime;
    @FXML
    private TextField tfAmount;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNext;

    @FXML
    public void save() throws IOException {
        btnSave.setVisible(false);
        btnNext.setVisible(true);
        ControllerApi api = new ControllerApi();
        String jsonConf = api.get("http://localhost:8080/conference/lastConf");

        Conference conference = new Gson().fromJson(jsonConf,Conference.class);

        Task task = new Task(tfTitle.getText(),Float.parseFloat(tfAmount.getText()),tfTime.getText(),conference);

        String jsonTask = new Gson().toJson(task,Task.class);

        api.post("http://localhost:8080/task",jsonTask);
    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {

        Parent createConf = FXMLLoader.load(getClass().getResource("../View/budgetView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Budget ");

        stage.setResizable(false);

        stage.setScene(new Scene(createConf));
        stage.show();


    }


    public void initialize(URL location, ResourceBundle resources) {

    }
}
