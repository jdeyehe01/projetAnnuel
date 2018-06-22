package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerHome implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*try {
          Process p = Runtime.getRuntime().exec( "cmd.exe /c ipconfig" );
            java.io.BufferedReader out = new java.io.BufferedReader( new java.io.InputStreamReader( p.getInputStream() ) );

            System.out.println(out.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }


    @FXML
    public void navigate(ActionEvent event) throws IOException {

        Parent createConference = FXMLLoader.load(getClass().getResource("../View/CreateConfView.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Créer une Conference ");
        stage.setScene(new Scene(createConference, createConference.getLayoutX(), createConference.getLayoutY()));
        stage.show();





    }

    @FXML
    public void updateConference(ActionEvent event) throws IOException {
        //Parent updateConf = FXMLLoader.load(getClass().getResource("../View/updateConfView.fxml"));

        Parent root = FXMLLoader.load(getClass().getResource("../View/updateConfView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier votre conference ");

        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
