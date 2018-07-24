package esgi.controller.fr.showController;

import esgi.controller.fr.ControllerApi;
import esgi.model.fr.Budget;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowBudget extends ControllerInitConference implements Initializable {
    @FXML
    private Accordion accordionView;

    @FXML
    private ComboBox cbListConference;

    @FXML
    private Label lbAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbListConference = super.ComboBoxInitConference(cbListConference);
    }

    @FXML
    public void initialisationBudget(ActionEvent event) {

        try {
            float amount =0 ;
            VBox content = new VBox();

            accordionView.setVisible(true);
            accordionView.getPanes().clear();


            String idConference = ((ComboBox) event.getSource()).getValue().toString().split("-")[0];
            String url = "budget/getAllBudgetForConference/" + idConference;

            String jsonBudget = new ControllerApi().get(url);
            Budget[] tabBudget = new Gson().fromJson(jsonBudget, Budget[].class);

            for (Budget budget : tabBudget) {
                TitledPane t = new TitledPane();
                t.setText(budget.getTitle());

                content.getChildren().add(new Label("Nom: " + budget.getTitle()));
                content.getChildren().add(new Label("Montant: " + budget.getAmount()));
                amount += budget.getAmount();
                t.setContent(content);
                t.setExpanded(true);

                accordionView.getPanes().add(t);

            }

            accordionView.autosize();

            lbAmount.setText(amount + " €");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
