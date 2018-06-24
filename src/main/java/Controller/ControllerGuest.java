package Controller;

import Model.Conference;
import Model.Guest;
import Annotation.BeanFromDataBase;
import Annotation.ControllerAnnotation;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    @FXML
    private VBox verticalBox;

    @FXML
    private ScrollPane scrollPaneGuest;

    private ArrayList<Guest> listGuest ;

    @BeanFromDataBase
    private static Conference conference;

    @BeanFromDataBase
    private static Guest guest;


    @FXML
    public void save() throws IOException {

       /* String jsonConf = new ControllerApi().get("conference/lastConf");

        Conference conference = new Gson().fromJson(jsonConf,Conference.class);
        */

        Guest guest = new Guest(tfFirstName.getText(),tfLastName.getText(),tfEmail.getText(),conference);
        System.out.println(guest);
        String jsonGuest = new Gson().toJson(guest);
        System.out.println(jsonGuest);
        new ControllerApi().post("guest",jsonGuest);


    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/presentationConfView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Ajouter un invité ");

        stage.setResizable(false);
        stage.setScene(new Scene(root));

        stage.show();

        //this.sendMail();

    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            String url = "conference/lastConf";
            ControllerAnnotation.getBean(url,this.getClass(),conference);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
