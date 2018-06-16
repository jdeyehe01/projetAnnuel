package Controller.updateController;

import Controller.ControllerApi;
import Model.Conference;
import annotation.BeanFromDataBase;
import annotation.ControllerAnnoation;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.text.html.ObjectView;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.*;

public class UpdateConference implements Initializable {


    @BeanFromDataBase
    private static Conference conference;

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

    @FXML
    private ComboBox cbConference;


    @FXML
    public void updateConference() throws IOException {

        cbConference.setVisible(true);

        String allConference = new ControllerApi().get("http://localhost:8080/conference/getAll");
        System.out.println(allConference);
        Conference[] tabConference =  new Gson().fromJson(allConference, Conference[].class);

        List<Conference> listConference = Arrays.asList(tabConference);
        ArrayList<String> listIdName = new ArrayList<String>();

        for(Conference c : listConference){
            listIdName.add(c.getId() + "-" +c.getName());
        }

        cbConference.getItems().addAll(listIdName);


    }


    @FXML
    public void navigate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../updateGuest.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier les informations sur un invité ");

        stage.setScene(new Scene(root, root.getLayoutX(), root.getLayoutY()));
        stage.show();
    }

    @FXML
    public void updateDataBase() throws IOException {
        conference.setName(tfName.getText());
        conference.setTime(tfTime.getText());
        conference.setDescription(tfDesc.getText());
        conference.setDate(tfDate.getValue().toString());

        String jsonConference = new Gson().toJson(conference,Conference.class);

        new ControllerApi().put("http://localhost:8080/conference/update/"+conference.getId(),jsonConference);

        btnNext.setVisible(true);
    }



    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfName.clear();
        tfTime.clear();
        tfDesc.clear();

        String idConference = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        String url = "http://localhost:8080/conference/getById/"+idConference;
        btnSave.setVisible(true);
        ControllerAnnoation.getBean(url,this.getClass(),conference);

        tfName.setText(conference.getName());
        tfTime.setText(conference.getTime());
        tfDesc.setText(conference.getDescription());
        tfDate.setValue(LocalDate.parse(conference.getDate().split("T")[0]));


    }
    public void initialize(URL location, ResourceBundle resources) {

        try {
           this.updateConference();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
