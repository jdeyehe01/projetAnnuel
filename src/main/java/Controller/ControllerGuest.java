package Controller;

import Model.Conference;
import Model.Guest;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGuest implements Initializable {

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfEmail;

    private ArrayList<Guest> listGuest ;


    @FXML
    public void clickNextButton() throws IOException {

        String jsonConf = new ControllerApi().get("http://localhost:8080/conference/lastConf");

        Conference conference = new Gson().fromJson(jsonConf,Conference.class);

        Guest guest = new Guest(tfFirstName.getText(),tfLastName.getText(),tfEmail.getText(),conference);
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
