package Controller.updateController;

import Controller.ControllerApi;
import Controller.showController.ControllerInitConference;
import Model.Conference;
import Model.Locate;
import Annotation.BeanFromDataBase;
import Annotation.ControllerAnnotation;
import Model.User;
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

public class UpdateLocate extends ControllerInitConference implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfCityCode;

    @FXML
    private ComboBox cbListLocate;

    @BeanFromDataBase
     private static Locate l;


    @BeanFromDataBase
    private static User user;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNext;

    @FXML
    private ComboBox cbListConference;

    @FXML
    private Button btnDelete;


    @FXML
    public void updateLocate() throws IOException, InstantiationException, IllegalAccessException {
        cbListLocate.getItems().clear();

        String idConference = cbListConference.getValue().toString().split("-")[0];

        String allLocate = new ControllerApi().get("locate/getAll/"+idConference);
        System.out.println(allLocate);
        Locate[] tabLocate =  new Gson().fromJson(allLocate, Locate[].class);

        List<Locate> listLocate = Arrays.asList(tabLocate);
        ArrayList<String> listNameLocate = new ArrayList<String>();

        for(Locate locate : listLocate){
            listNameLocate.add(locate.getId()+"-"+locate.getName());
        }

        cbListLocate.getItems().addAll(listNameLocate);

        if(cbListLocate.getItems().isEmpty()){
            cbListLocate.setDisable(true);
        }else{
            cbListLocate.setDisable(false);
        }
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfName.clear();
        tfAddress.clear();
        tfCity.clear();
        tfCityCode.clear();

        String idLocate = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        String url = "locate/getLocateById/"+idLocate;

        if(btnDelete.isVisible()){
            btnSave.setVisible(false);
        }else{
            btnSave.setVisible(true);
        }



        new ControllerAnnotation().getBean(url,this.getClass(),l);

        tfName.setText(l.getName());
        tfAddress.setText(l.getAddress());
        tfCity.setText(l.getCity());
        tfCityCode.setText(String.valueOf(l.getCityCode()));

    }



    @FXML
    public void updateDataBase() throws IOException {
        int id = l.getId();
        l.setName(tfName.getText());
        l.setAddress(tfAddress.getText());
        l.setCity(tfCity.getText());
        l.setCityCode(Integer.parseInt(tfCityCode.getText()));

        String jsonLocate = new Gson().toJson(l,Locate.class);

        new ControllerApi().put("locate/update/"+l.getId(),jsonLocate);

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

    @FXML
    public void deleteTask() throws IOException, IllegalAccessException, InstantiationException {
        String idTask = cbListLocate.getValue().toString().split("-")[0];

        String url = "task/deleteTaskById/"+idTask;
        int code = new ControllerApi().delete(url);

        this.updateLocate();
        tfName.clear();
        tfAddress.clear();
        tfCity.clear();
        tfCityCode.clear();


        if(code <=200) {
            System.out.println("Delete !");
        }
        else {
            System.out.println("No delete");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


           // this.initListConference();
            cbListConference = super.ComboBoxInitConference(cbListConference);
            this.cbListLocate.setDisable(true);


    }

}
