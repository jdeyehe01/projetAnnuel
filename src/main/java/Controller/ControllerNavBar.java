package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class ControllerNavBar implements Initializable {


    @FXML
    private MenuBar navBar;

    @FXML
    public void showConference() throws IOException {
        Parent createConf = FXMLLoader.load(getClass().getResource("../View/ShowView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Conférence ");
        stage.setResizable(false);

        stage.setScene(new Scene(createConf));
        stage.show();

    }


    @FXML
    public void createConference() throws IOException {
        Parent createConf = FXMLLoader.load(getClass().getResource("../View/CreateConfView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Créer une conférence ");
        stage.setResizable(false);

        stage.setScene(new Scene(createConf));
        stage.show();

    }

    @FXML
    public void updateConference(ActionEvent event) throws IOException {
        Parent createConf = FXMLLoader.load(getClass().getResource("../View/updateConfView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier une conférence ");
        stage.setResizable(false);

        stage.setScene(new Scene(createConf));
        stage.show();

    }

    @FXML
    public void deleteConference(ActionEvent event) throws IOException {
        Parent createConf = FXMLLoader.load(getClass().getResource("../View/updateConfView.fxml"));

        Stage stage = (Stage) navBar.getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Suprimmer une conférence ");
        stage.setResizable(false);

        stage.setScene(new Scene(createConf));
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
