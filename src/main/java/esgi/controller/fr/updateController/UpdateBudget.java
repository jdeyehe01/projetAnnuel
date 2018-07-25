package esgi.controller.fr.updateController;

import esgi.annotation.fr.NumberValue;
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

    @NumberValue
    @FXML
    private TextField tfAmount;

    @FXML
    private ComboBox cbListBudget;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    private AlertMessage alert;

    /*
           On récupère toutes les conférences qu'un utilisateur a pu créer puis on les place dans une combobox. Cela nous permet dans la suite de récupérer tous les budgets d'une conférence en fonction de son id

     */
    @FXML
    public void updateBudget() throws IOException, InstantiationException, IllegalAccessException {
        cbListBudget.getItems().clear();
        if (cbListBudget.getItems().size() == 0) {
            cbListBudget.setDisable(false);

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
    }

    /*
        Nous initalisont les champs du formulaire budget avec les informations de la base de données avec le budget que l'utilisateur à choisi de modifier
     */

    @FXML
    public void initialisationForm() throws InstantiationException, IllegalAccessException {

        tfName.clear();
        tfAmount.clear();
        if(cbListBudget.getItems().size() != 0) {
            String idConference = cbListConference.getValue().toString().split("-")[0];
            String idBudget = cbListBudget.getValue().toString().split("-")[0];
            String url = "budget/getBudgetById/" + idBudget + "/" + idConference;
            btnUpdate.setVisible(true);

            budget = (Budget) new ControllerAnnotation().getBean(url, this.getClass(), Budget.class);
            System.out.println(budget.toString());
            tfName.setText(budget.getTitle());
            tfAmount.setText(String.valueOf(budget.getAmount()));

            cbListBudget.setDisable(false);

        }
        if (btnDelete.isVisible()) {
            btnUpdate.setVisible(false);
        } else {
            btnUpdate.setVisible(true);
            btnUpdate.setDisable(false);
        }

    }

    /*
        Cette fonction est lancé au clique du bouton modifier. Elle permet d'effectué une requette http de type put pour modifier un budget dans la base de données en fonction de son id
     */

    @FXML
    public void updateDataBase() throws IOException {
        try {

            if (tfName.getText().isEmpty() || tfAmount.getText().isEmpty() || new ControllerAnnotation().isNumber(this.getClass(), tfAmount)) {
                alert.notificationAndWait("Tous les champs ne sont pas correctes");
                return;
            }
            budget.setTitle(tfName.getText());
            budget.setAmount(Float.parseFloat(tfAmount.getText()));

            String jsonBudget = new Gson().toJson(budget, Budget.class);

            new ControllerApi().put("budget/updateBudget/" + budget.getId(), jsonBudget);

            updateBudget();
            tfName.clear();
            tfAmount.clear();
            alert.notificationAndWait("Le budget " + budget.getTitle() + " a été mis à jour");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    /*
        La fonction est lancé au clique du bouton supprimer. Elle permet d'effectué une requête http de type delete et ainsi de supprimer le budget dans la base de données en fonction de son id
     */

    @FXML
    public void deleteBudget() throws IOException, IllegalAccessException, InstantiationException {

        String idBudget = cbListBudget.getValue().toString().split("-")[0];

        String url = "budget/deleteBudgetById/" + idBudget;
        int code = new ControllerApi().delete(url);

        this.updateBudget();
        tfName.clear();
        tfAmount.clear();

        updateBudget();

        if (code == 200) {
            alert.notificationAndWait("Le budget " + budget.getTitle() + " a été supprimé");

        } else {
            alert.notificationAndWait("Le budget " + budget.getTitle() + " n'a pas été supprimé");

        }

    }

    /*
        On initialise la liste des conférences qui sont stocké dans la base ded données au chargement de la page
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbListConference = super.ComboBoxInitConference(cbListConference);
        alert = new AlertMessage();

    }


}

