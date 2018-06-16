package Controller.updateController;

import Controller.ControllerApi;
import Model.Conference;
import Model.Locate;
import Model.Presentation;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UpdatePresentation implements Initializable {

    @BeanFromDataBase
    private static Conference c;


    @BeanFromDataBase
    private static Presentation presentation;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfAmount;

    @FXML
    private TextArea tfDesc;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox cbListPresentation;


    @FXML
    public void updateLocate() throws IOException, InstantiationException, IllegalAccessException {

        String url = "http://localhost:8080/conference/lastConf";
        cbListPresentation.setVisible(true);

        ControllerAnnoation.getBean(url,this.getClass(),c);

        String allPresentation = new ControllerApi().get("http://localhost:8080/presentation/getAllPresentationForConference/"+c.getId());
        Presentation[] tabPresentation =  new Gson().fromJson(allPresentation, Presentation[].class);

        List<Presentation> listPresentation = Arrays.asList(tabPresentation);
        ArrayList<String> listTitle = new ArrayList<String>();

        for(Presentation p : listPresentation){
            listTitle.add(p.getId()+"-"+p.getTitle());
        }

        cbListPresentation.getItems().addAll(listTitle);
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfTitle.clear();
        tfAmount.clear();
        tfDesc.clear();

        String idPresnetation = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        String url = "http://localhost:8080/presentation/getPresentationById/"+idPresnetation+"/"+c.getId();
        btnUpdate.setVisible(true);
        ControllerAnnoation.getBean(url,this.getClass(),presentation);

        tfTitle.setText(presentation.getTitle());
        tfAmount.setText(String.valueOf(presentation.getAmount()));
        tfDesc.setText(presentation.getDescription());

    }



    @FXML
    public void updateDataBase() throws IOException {
        presentation.setTitle(tfTitle.getText());
        presentation.setAmount(Float.parseFloat(tfAmount.getText()));
        presentation.setDescription(tfDesc.getText());

        String jsonPresentation = new Gson().toJson(presentation,Presentation.class);

        new ControllerApi().put("http://localhost:8080/presentation/updatePresentation/"+presentation.getId(),jsonPresentation);

        btnNext.setVisible(true);
    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {
        System.out.println("Entrer");
        // Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateViews/updateConfView.fxml"));
        Parent root  = FXMLLoader.load(getClass().getResource("../updateGuest.fxml"));

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
