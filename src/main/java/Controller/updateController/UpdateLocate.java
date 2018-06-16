package Controller.updateController;

import Controller.ControllerApi;
import Model.Conference;
import Model.Guest;
import Model.Locate;
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

public class UpdateLocate implements Initializable {

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
    private static Conference c;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNext;




    @FXML
    public void updateLocate() throws IOException, InstantiationException, IllegalAccessException {

        String url = "http://localhost:8080/conference/lastConf";
        cbListLocate.setVisible(true);

        ControllerAnnoation.getBean(url,this.getClass(),c);

        String allLocate = new ControllerApi().get("http://localhost:8080/locate/getAll/"+c.getId());
        System.out.println(allLocate);
        Locate[] tabLocate =  new Gson().fromJson(allLocate, Locate[].class);

        List<Locate> listLocate = Arrays.asList(tabLocate);
        ArrayList<String> listNameLocate = new ArrayList<String>();

        for(Locate locate : listLocate){
            listNameLocate.add(locate.getId()+"-"+locate.getName());
        }

        cbListLocate.getItems().addAll(listNameLocate);
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfName.clear();
        tfAddress.clear();
        tfCity.clear();
        tfCityCode.clear();

        String idLocate = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        String url = "http://localhost:8080/locate/getLocateById/"+idLocate;
        btnSave.setVisible(true);
        ControllerAnnoation.getBean(url,this.getClass(),l);

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

        new ControllerApi().put("http://localhost:8080/locate/update/"+l.getId(),jsonLocate);

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            this.updateLocate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
