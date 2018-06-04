package Controller;

import Model.Budget;
import Model.Conference;
import Model.Task;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerBudget implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAmount;


    @FXML
    public void save() throws IOException {
        ControllerApi api = new ControllerApi();
        String jsonConf = api.get("http://localhost:8080/conference/lastConf");

        Conference conference = new Gson().fromJson(jsonConf,Conference.class);

        Budget budget = new Budget(tfName.getText(), Float.parseFloat(tfAmount.getText()), conference);

        String jsonBudget= new Gson().toJson(budget);

        System.out.println(jsonBudget);

        api.post("http://localhost:8080/budget",jsonBudget);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
