package esgi.controller.fr.updateController;

import esgi.controller.fr.AlertMessage;
import esgi.controller.fr.ControllerApi;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Conference;
import esgi.model.fr.Budget;
import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import com.google.gson.Gson;
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

public class UpdateBudget extends ControllerInitConference implements Initializable {

    @BeanFromDataBase
    private Budget budget;


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
    private Button btnDelete;


    @FXML
    public void updateBudget() throws IOException, InstantiationException, IllegalAccessException {

        if(cbListBudget.getItems().size()>0){
            cbListBudget.setDisable(false);

        }
        String idConference = cbListConference.getValue().toString().split("-")[0];
        String allBudget = new ControllerApi().get("budget/getAllBudgetForConference/" + idConference);
        Budget[] tabBudget = new Gson().fromJson(allBudget, Budget[].class);

        List<Budget> listBudget = Arrays.asList(tabBudget);
        ArrayList<String> listTitle = new ArrayList<String>();

        for (Budget b : listBudget) {
            listTitle.add(b.getId() + "-" + b.getTitle());
        }

        cbListBudget.getItems().setAll(listTitle);


    }


    @FXML
    public void initialisationForm() throws InstantiationException, IllegalAccessException {

        tfName.clear();
        tfAmount.clear();
        String idConference = cbListConference.getValue().toString().split("-")[0];
        String idBudget = cbListBudget.getValue().toString().split("-")[0];
        String url = "budget/getBudgetById/" + idBudget + "/" + idConference;
        btnUpdate.setVisible(true);

        budget = (Budget) new ControllerAnnotation().getBean(url, this.getClass(), Budget.class);
        System.out.println(budget.toString());
        tfName.setText(budget.getTitle());
        tfAmount.setText(String.valueOf(budget.getAmount()));

        cbListBudget.setDisable(false);


        if (btnDelete.isVisible()) {
            btnUpdate.setVisible(false);
        } else {
            btnUpdate.setVisible(true);
            btnUpdate.setDisable(false);
        }

    }

    @FXML
    public void updateDataBase() throws IOException {
        try {
            budget.setTitle(tfName.getText());
            budget.setAmount(Float.parseFloat(tfAmount.getText()));

            String jsonBudget = new Gson().toJson(budget, Budget.class);

            new ControllerApi().put("budget/updateBudget/" + budget.getId(), jsonBudget);

            updateBudget();
            new AlertMessage().notificationAndWait("Le budget " +budget.getTitle() + " a été mis à jour");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void deleteBudget() throws IOException, IllegalAccessException, InstantiationException {

        String idBudget = cbListBudget.getValue().toString().split("-")[0];

        String url = "budget/deleteBudgetById/" + idBudget;
        int code = new ControllerApi().delete(url);

        this.updateBudget();
        tfName.clear();
        tfAmount.clear();

        updateBudget();
        if (code <= 200) {
            System.out.println("Delete !");
        } else {
            System.out.println("No delete");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbListConference = super.ComboBoxInitConference(cbListConference);

    }


}

