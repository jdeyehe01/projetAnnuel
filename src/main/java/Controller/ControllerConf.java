package Controller;

import Model.Conference;

import Model.User;
import annotation.BeanFromDataBase;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

import java.net.URL;
import java.text.ParseException;

import java.util.ResourceBundle;
import javafx.stage.Stage;

public class ControllerConf implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfTime;

    @FXML
    private DatePicker tfDate;

    @FXML
    private TextArea tfDesc;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSave;

   @BeanFromDataBase
   private static User user;

    @FXML
    public void signIn(ActionEvent event) throws IOException, ParseException {

        btnSave.setVisible(false);
        btnNext.setVisible(true);


        Conference conference = new Conference(tfName.getText(),tfDate.getValue().toString(),tfTime.getText(),tfDesc.getText(),user);
        System.out.println(conference.getDate());
        ControllerApi api = new ControllerApi();

        Gson gson = new Gson();
        System.out.println(gson.toJson(conference));
        System.out.println("Object java to object json :" + gson.toJson(conference));

        api.post("http://localhost:8080/conference/",new Gson().toJson(conference));

    }

    @FXML
    public void navigateTo(ActionEvent event) throws IOException {
        Parent createConf = FXMLLoader.load(getClass().getResource("../View/locateConfView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Créer une conférence ");

        stage.setScene(new Scene(createConf, createConf.getLayoutX(), createConf.getLayoutY()));
        stage.show();

    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


   

}
