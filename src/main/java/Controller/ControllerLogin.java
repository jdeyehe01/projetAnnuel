package Controller;

import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    @FXML
    private Button btnConnexon;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPw;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void signIn(javafx.event.ActionEvent actionEvent) {

        User user = new User(0,tfEmail.getText(),tfPw.getText());

        System.out.println("L'utilisateur est connecté");
    }
}
