package Controller;

import Model.Conference;
import Model.Presentation;
import Model.Task;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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
    public void save() throws IOException {
        ControllerApi api = new ControllerApi();
        String jsonConf = api.get("http://localhost:8080/conference/lastConf");

        Conference conference = new Gson().fromJson(jsonConf,Conference.class);

        Task task = new Task(tfTitle.getText(),Float.parseFloat(tfAmount.getText()),tfTime.getText(),conference);

        String jsonTask = new Gson().toJson(task);

        System.out.println(jsonTask);

        api.post("http://localhost:8080/task",jsonTask);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
