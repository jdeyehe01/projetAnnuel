package Controller;

import Model.Conference;
import annotation.BeanFromDataBase;
import annotation.ControllerAnnoation;
import annotation.Inject;
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
//import com.google.gson.Gson;
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

    @Inject
   private static String s;

    @FXML
    private ComboBox cbConference;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    @FXML
    public void signIn(ActionEvent event) throws IOException {

        System.out.println("Avant injection : " +s);
        ControllerAnnoation.inject();
        System.out.println("Apres injection : " +s);

        btnSave.setVisible(false);
        btnNext.setVisible(true);
        Conference conference = new Conference(tfName.getText(),tfDate.getValue(),tfTime.getText(),tfDesc.getText());

        ControllerApi api = new ControllerApi();

        Gson gson = new Gson();
        System.out.println("Object java to object json :" + gson.toJson(conference));

        //ControllerAnnoation.getLastBean(new String());

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

    @FXML
    public void updateConference() throws IOException {

        cbConference.setVisible(true);

        String s = new ControllerApi().get("http://localhost:8080/conference/getAll");
        System.out.println(s);

        Conference[] tabConference =  new Gson().fromJson(s, Conference[].class);

        List<Conference> listConference = Arrays.asList(tabConference);
        ArrayList<String> listIdName = new ArrayList<String>();

        for(Conference c : listConference){
            listIdName.add(c.getId() + "-" +c.getName());
        }

        cbConference.getItems().addAll(listIdName);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            this.updateConference();
           System.out.println(location.toString());



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
