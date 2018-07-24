package esgi.controller.fr.updateController;

import esgi.annotation.fr.NumberValue;
import esgi.controller.fr.AlertMessage;
import esgi.controller.fr.ControllerApi;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Locate;
import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import esgi.model.fr.User;
import com.google.gson.Gson;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    @NumberValue
    private TextField tfCityCode;

    @FXML
    private ComboBox cbListLocate;

    @BeanFromDataBase
     private Locate locate;


    @BeanFromDataBase
    private User user;

    @FXML
    private Button btnSave;


    @FXML
    private ComboBox cbListConference;

    @FXML
    private Button btnDelete;


    @FXML
    public void updateLocate() throws IOException, InstantiationException, IllegalAccessException {

        if(cbListLocate.getItems().size()>0){
            cbListLocate.getProperties().clear();
        }
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

       locate = (Locate) new ControllerAnnotation().getBean(url,this.getClass(),Locate.class);

        tfName.setText(locate.getName());
        tfAddress.setText(locate.getAddress());
        tfCity.setText(locate.getCity());
        tfCityCode.setText(String.valueOf(locate.getCityCode()));

    }



    @FXML
    public void updateDataBase() throws IOException {

        if(!new ControllerAnnotation().isNumber(this.getClass(),tfCityCode) || tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfCity.getText().isEmpty() || tfCityCode.getText().isEmpty() ){
            new AlertMessage().notificationAndWait("Tous les champs ne sont pas correctes");
            return;
        }
        locate.getId();
        locate.setName(tfName.getText());
        locate.setAddress(tfAddress.getText());
        locate.setCity(tfCity.getText());
        locate.setCityCode(Integer.parseInt(tfCityCode.getText()));

        String jsonLocate = new Gson().toJson(locate,Locate.class);

        new ControllerApi().put("locate/update/"+locate.getId(),jsonLocate);
        new AlertMessage().notificationAndWait("Le lieu " +locate.getName() + " a été mis à jour");


    }

    @FXML
    public void deleteLocate() throws IOException, IllegalAccessException, InstantiationException {
        String idLocate = cbListLocate.getValue().toString().split("-")[0];

        String url = "locate/deleteLocateById/"+idLocate;
        int code = new ControllerApi().delete(url);

        this.updateLocate();
        tfName.clear();
        tfAddress.clear();
        tfCity.clear();
        tfCityCode.clear();
        updateLocate();

        if(code == 200){
            new AlertMessage().notificationAndWait("Le lieu" + locate.getName() + " a été supprimé ");

        }else{
            if(code == 200){
                new AlertMessage().notificationAndWait("Le lieu" + locate.getName() + " n'a été supprimé ");

            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


           // this.initListConference();
            cbListConference = super.ComboBoxInitConference(cbListConference);
            this.cbListLocate.setDisable(true);


    }



}
