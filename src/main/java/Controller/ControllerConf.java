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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
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




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}
