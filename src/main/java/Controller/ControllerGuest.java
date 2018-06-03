package Controller;

import Model.Guest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGuest implements Initializable {

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfEmail;

    private ArrayList<Guest> listGuest ;


    @FXML
    public void clickNextButton(){
        Guest guest = new Guest(tfFirstName.getText(),tfLastName.getText(),tfAge.getText(),tfEmail.getText());
        listGuest.add(guest);

        System.out.println(guest);
    }

    @FXML
    public void clickAddGuest(Guest g){
        listGuest.add(g);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
