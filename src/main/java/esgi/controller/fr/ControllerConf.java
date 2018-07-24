package esgi.controller.fr;

import esgi.annotation.fr.ControllerAnnotation;
import esgi.model.fr.Conference;

import esgi.model.fr.User;
import esgi.annotation.fr.BeanFromDataBase;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.*;

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
   private User user;

    @FXML
    public void save() throws IOException, ParseException {

        btnSave.setVisible(false);
        btnNext.setVisible(true);


        Conference conference = new Conference(tfName.getText(),tfDate.getValue().toString(),tfTime.getText(),tfDesc.getText(),user);
        ControllerApi api = new ControllerApi();

        api.post("conference/"+user.getId(),new Gson().toJson(conference));

        new AlertMessage().notificationAndWait("La conférence " +conference.getName() + " a été créé");


    }

    @FXML
    public void navigateTo(ActionEvent event) throws IOException {
        Parent createConf = FXMLLoader.load(getClass().getResource("../View/createGuestView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Ajouter un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(createConf));
        stage.show();

    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            user = (User) new ControllerAnnotation().getBean("user/loggedInUser/" + ControllerLogin.emailCurrentUser, this.getClass(), User.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


   

}