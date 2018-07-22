package Controller;

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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerBudget implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAmount;

    @FXML
    private ScrollPane listBudgetPan;

    private Conference conference;

    @FXML
    private VBox verticalBox;

   private ControllerApi api;
   private ArrayList<Budget> listBudget;



   private Label label;



    @FXML
    public void save() throws IOException {

        Budget budget = new Budget(tfName.getText(), Float.parseFloat(tfAmount.getText()), conference);

        String jsonBudget= new Gson().toJson(budget);

        verticalBox.getChildren().add(new Label(budget.getTitle()));

        listBudgetPan.setContent(verticalBox);
        listBudgetPan.setVisible(true);
        api.post("budget",jsonBudget);
    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            api = new ControllerApi();
            String jsonConf = api.get("conference/lastConf");
            conference = new Gson().fromJson(jsonConf,Conference.class);
            listBudget = new ArrayList<Budget>();

            if(verticalBox.getChildren().size() <=0){
                listBudgetPan.setVisible(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
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
        Parent createConf = FXMLLoader.load(getClass().getResource("View/beforeShowWelcomeView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Accueil ");

        stage.setResizable(false);
        stage.setScene(new Scene(createConf));
        stage.show();
    }
}