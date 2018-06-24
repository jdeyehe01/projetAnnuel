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

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
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

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void deleteGuest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/updateConfView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Suprimmer un un invité ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();

    }

    /*
    Task
     */
    @FXML
    public void showTask(ActionEvent event){
        try {
            Parent  root = FXMLLoader.load(getClass().getResource("../View/ShowTask.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navBar.autosize();
    }
}
