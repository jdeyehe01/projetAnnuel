package Controller.updateController;

import Controller.ControllerApi;
import Controller.showController.ControllerInitConference;
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

public class UpdateBudget extends ControllerInitConference implements Initializable {
    @BeanFromDataBase
    private static Conference c;

    @BeanFromDataBase
    private static Budget budget;


    @FXML
    private ComboBox cbListConference;

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
    private Button btnDelete;


    @FXML
    public void updateBudget() throws IOException, InstantiationException, IllegalAccessException {

        cbListBudget.setVisible(true);
        String idConference = cbListConference.getValue().toString().split("-")[0];
        String allBudget = new ControllerApi().get("budget/getAllBudgetForConference/"+idConference);
        Budget[] tabBudget =  new Gson().fromJson(allBudget, Budget[].class);

        List<Budget> listBudget = Arrays.asList(tabBudget);
        ArrayList<String> listTitle = new ArrayList<String>();

        for(Budget b : listBudget){
            listTitle.add(b.getId()+"-"+b.getTitle());
        }

        cbListBudget.getItems().addAll(listTitle);
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {

        tfName.clear();
        tfAmount.clear();

        String idBudget = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        String url = "budget/getBudgetById/"+idBudget+"/"+c.getId();
        btnUpdate.setVisible(true);
       new  ControllerAnnotation().getBean(url,this.getClass(),budget);

        tfName.setText(budget.getTitle());
        tfAmount.setText(String.valueOf(budget.getAmount()));

    }

    @FXML
    public void updateDataBase() throws IOException {
        budget.setTitle(tfName.getText());
        budget.setAmount(Float.parseFloat(tfAmount.getText()));

        String jsonBudget = new Gson().toJson(budget,Budget.class);

        new ControllerApi().put("budget/updateBudget/"+budget.getId(),jsonBudget);

        btnNext.setVisible(true);
    }

    @FXML
    public void deleteBudget() throws IOException, IllegalAccessException, InstantiationException {

            String idBudget = cbListBudget.getValue().toString().split("-")[0];

            String url = "budget/deleteBudgetById/"+idBudget;
            int code = new ControllerApi().delete(url);

            this.updateBudget();
            tfName.clear();
            tfAmount.clear();



            if(code <=200) {
                System.out.println("Delete !");
            }
            else {
                System.out.println("No delete");
            }

        }


    @FXML
    public void navigate(ActionEvent event) throws IOException {
        System.out.println("Entrer");
        Parent root  = FXMLLoader.load(getClass().getResource("../updateGuest.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier une budget ");

        stage.setScene(new Scene(root, root.getLayoutX(), root.getLayoutY()));
        stage.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //this.updateBudget();
        cbListConference = super.ComboBoxInitConference(cbListConference);

        try {
            new ControllerAnnotation().getBean("conference/lastConf",this.getClass(),c);
            System.out.println(c.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }



}

