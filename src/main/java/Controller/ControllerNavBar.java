package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerNavBar implements Initializable {


    @FXML
    private MenuBar navBar;

    /*
    Conference
     */

    @FXML
    public void showConference() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ShowView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Conférence ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }


    @FXML
    public void createConference() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/CreateConfView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Créer une conférence ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void updateConference(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateConfView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier une conférence ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void deleteConference(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateConfView.fxml"));
        Button btnDelete = (Button) root.lookup("#btnDelete");
        TextField tfN = (TextField) root.lookup("#tfName");
        DatePicker tfDate = (DatePicker) root.lookup("#tfDate");
        TextArea tfDesc = (TextArea) root.lookup("#tfDesc");
        TextField tfTime = (TextField) root.lookup("#tfTime");
        Label title = (Label) root.lookup("#labelTitleView");
        title.setText("Supprimer une conference");


        tfN.setEditable(false);
        tfDate.setEditable(false);
        tfDesc.setEditable(false);
        tfTime.setEditable(false);


        btnDelete.setVisible(true);

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Suprimmer une conférence ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }
/*

Guest
 */

    @FXML
    public void showGuest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ShowGuest.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Invités ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    public void createGuest() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/createGuestView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Ajouter un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void updateGuest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateGuest.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void deleteGuest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateGuest.fxml"));
        Button btnDelete = (Button) (root.lookup("#btnDelete"));
        btnDelete.setVisible(true);

        TextField tfLastName = (TextField) root.lookup("#tfLastName");
        TextField tfFirstName = (TextField) root.lookup("#tfFirstName");
        TextField tfEmail = (TextField) root.lookup("#tfEmail");

        tfFirstName.setEditable(false);
        tfLastName.setEditable(false);
        tfEmail.setEditable(false);

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Suprimmer un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    /*
    Task
     */
    @FXML
    public void showTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/ShowTask.fxml"));
            Stage stage = (Stage) navBar.getScene().getWindow();
            stage.close();
            stage.setTitle("Before Show - Tâches ");
            stage.setResizable(false);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    public void createTask() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/taskView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Ajouter un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void updateTask(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateTask.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void deleteTask(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateTask.fxml"));

        Button btnDelete = (Button) (root.lookup("#btnDelete"));
        btnDelete.setVisible(true);

        TextField titleField = (TextField) root.lookup("#tfTitle");
        TextField amountField = (TextField) root.lookup("#tfAmount");
        TextField timeField = (TextField) root.lookup("#tfTime");

        amountField.setEditable(false);
        titleField.setEditable(false);
        timeField.setEditable(false);

        amountField.setDisable(true);
        titleField.setDisable(true);
        timeField.setDisable(true);


        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Suprimmer une tâche ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }


    /*
    Locate
     */

    @FXML
    public void showLocate(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/ShowLocate.fxml"));
            Stage stage = (Stage) navBar.getScene().getWindow();
            stage.close();
            stage.setTitle("Before Show - Tâches ");
            stage.setResizable(false);

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void createLocate() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/locateConfView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Ajouter un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void updateLocate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateLocate.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void deleteLocate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateLocate.fxml"));

        Button btnDelete = (Button) (root.lookup("#btnDelete"));
        btnDelete.setVisible(true);

        TextField tfName = (TextField) root.lookup("#tfName");
        TextField tfAddress = (TextField) root.lookup("#tfAddress");
        TextField tfCity = (TextField) root.lookup("#tfCity");
        TextField tfCityCode = (TextField) root.lookup("#tfCityCode");

        tfName.setEditable(false);
        tfAddress.setEditable(false);
        tfCity.setEditable(false);
        tfCityCode.setEditable(false);

        tfName.setDisable(true);
        tfAddress.setDisable(true);
        tfCity.setDisable(true);
        tfCityCode.setDisable(true);


        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Suprimmer un lieu ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navBar.autosize();
    }
}
