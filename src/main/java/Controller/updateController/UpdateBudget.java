package Controller.updateController;

import Controller.ControllerApi;
import Model.Conference;
import Model.Budget;
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

public class UpdateBudget implements Initializable {
    @BeanFromDataBase
    private static Conference c;

    @BeanFromDataBase
    private static Budget budget;


    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAmount;

    @FXML
    private ComboBox cbListBudget;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnNext;


    @FXML
    public void updateBudget() throws IOException, InstantiationException, IllegalAccessException {

        String url = "http://localhost:8080/conference/lastConf";
        cbListBudget.setVisible(true);

        ControllerAnnotation.getBean(url,this.getClass(),c);

        String allBudget = new ControllerApi().get("http://localhost:8080/budget/getAllBudgetForConference/"+c.getId());
        Budget[] tabBudget =  new Gson().fromJson(allBudget, Budget[].class);

        List<Budget> listBudget = Arrays.asList(tabBudget);
        ArrayList<String> listTitle = new ArrayList<String>();

        for(Budget b : listBudget){
            listTitle.add(b.getId()+"-"+b.gettitle());
        }

        cbListBudget.getItems().addAll(listTitle);
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfName.clear();
        tfAmount.clear();

        String idBudget = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        String url = "http://localhost:8080/budget/getBudgetById/"+idBudget+"/"+c.getId();
        btnUpdate.setVisible(true);
        ControllerAnnotation.getBean(url,this.getClass(),budget);

        tfName.setText(budget.getTitle());
        tfAmount.setText(String.valueOf(budget.getAmount()));

    }

    @FXML
    public void updateDataBase() throws IOException {
        budget.setTitle(tfName.getText());
        budget.setAmount(Float.parseFloat(tfAmount.getText()));

        String jsonBudget = new Gson().toJson(budget,Budget.class);

        new ControllerApi().put("http://localhost:8080/budget/updateBudget/"+budget.getId(),jsonBudget);

        btnNext.setVisible(true);
    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {
        System.out.println("Entrer");
        // Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateViews/updateConfView.fxml"));
        Parent root  = FXMLLoader.load(getClass().getResource("../updateGuest.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier une budget ");

        stage.setScene(new Scene(root, root.getLayoutX(), root.getLayoutY()));
        stage.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            this.updateBudget();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

