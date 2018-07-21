package Controller.updateController;

import Controller.ControllerApi;
import Controller.showController.ControllerInitConference;
import Model.Conference;
import Model.User;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class UpdateConference extends ControllerInitConference implements Initializable {


    @BeanFromDataBase
    private static Conference conference;

    @BeanFromDataBase
    private static User user;

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
    public void updateConference() throws IOException, InstantiationException, IllegalAccessException {

        tfName.clear();
        tfTime.clear();
        tfDesc.clear();
        tfDate.getEditor().clear();
        cbConference.getItems().clear();
        String url = "user/lastUser";
        new ControllerAnnotation().getBean(url,this.getClass(),user);

        cbConference.setVisible(true);

        String allConference = new ControllerApi().get("conference/getAllByUser/"+user.getId());
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

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void updateDataBase() throws IOException {
        conference.setName(tfName.getText());
        conference.setTime(tfTime.getText());
        conference.setDescription(tfDesc.getText());
        conference.setDate(tfDate.getValue().toString());

        String jsonConference = new Gson().toJson(conference,Conference.class);

        new ControllerApi().put("conference/update/"+conference.getId(),jsonConference);

        btnNext.setVisible(true);
    }



    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfName.clear();
        tfTime.clear();
        tfDesc.clear();
        tfDate.getEditor().clear();


        String idConference = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        System.out.println("id: "+idConference);

        String url = "conference/getById/"+idConference;
        btnSave.setVisible(true);
        new ControllerAnnotation().getBean(url,this.getClass(),conference);

        cbConference = super.ComboBoxInitConference(cbConference);


    }


    @FXML
    public void delete(ActionEvent event) throws IOException, IllegalAccessException, InstantiationException {

        String idConference = cbConference.getValue().toString().split("-")[0];

        String url ="conference/deleteConference/"+idConference;
        int code = new ControllerApi().delete(url);

        this.updateConference();


        if(code <= 200){
            System.out.println("Delete ! ");

        }else{
            System.out.println("no delete ! ");

        }
    }
    public void initialize(URL location, ResourceBundle resources) {

        try {
           this.updateConference();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
