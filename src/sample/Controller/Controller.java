package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.*;
import javafx.scene.control.TextField;
import sample.Model.User;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    public void doClick(javafx.event.ActionEvent actionEvent) {
        System.out.println("Toto");

        User user = new User(0,tfEmail.getText(),tfPw.getText());

        System.out.println(user);



    }
}
