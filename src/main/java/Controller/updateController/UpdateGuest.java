package Controller.updateController;

import Controller.ControllerApi;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateGuest implements Initializable {


    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNext;

    @BeanFromDataBase
    private static Guest guest;

    @BeanFromDataBase
    private static Conference c;

    @FXML
    private ComboBox cbListGuest;

    @FXML
    public void updateGuest() throws IOException, InstantiationException, IllegalAccessException {

        String url = "http://localhost:8080/conference/lastConf";
        cbListGuest.setVisible(true);

        ControllerAnnotation.getBean(url,this.getClass(),c);

        String allGuest = new ControllerApi().get("http://localhost:8080/guest/getAllGuest/"+c.getId());

        Guest[] tabGuest =  new Gson().fromJson(allGuest, Guest[].class);


        List<Guest> listGuest = Arrays.asList(tabGuest);
        ArrayList<String> listNameGuset = new ArrayList<String>();

        for(Guest guest : listGuest){
            listNameGuset.add(guest.getId()+"-"+guest.getlname()+ " " +guest.getfname());
        }

        cbListGuest.getItems().addAll(listNameGuset);
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfFirstName.clear();
        tfLastName.clear();
        tfEmail.clear();

        String idGuest = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        String url = "http://localhost:8080/guest/guestById/"+idGuest;
        btnSave.setVisible(true);
        ControllerAnnotation.getBean(url,this.getClass(),guest);

        tfFirstName.setText(guest.getfname());
        tfLastName.setText(guest.getlname());
        tfEmail.setText(guest.getEmail());

    }



    @FXML
    public void updateDataBase() throws IOException {
        String id = guest.getId();
        guest.setlname(tfLastName.getText());
        guest.setEmail(tfEmail.getText());
        guest.setfname(tfFirstName.getText());

        String jsonGuest = new Gson().toJson(guest,Guest.class);

        new ControllerApi().put("http://localhost:8080/guest/update/"+guest.getId(),jsonGuest);

        btnNext.setVisible(true);
    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {
        System.out.println("Entrer");
       // Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateViews/updateConfView.fxml"));
        Parent root  = FXMLLoader.load(getClass().getResource("../updateGuest.fxml"));

        System.out.println("Sortie");

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier une presentation ");

        stage.setScene(new Scene(root, root.getLayoutX(), root.getLayoutY()));
        stage.show();

    }


    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.updateGuest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
