package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowView implements Initializable {

    @FXML
    private Pane windowCreateConference;

    @FXML
    public void start(ActionEvent event) throws IOException {
        //Parent updateConf = FXMLLoader.load(getClass().getResource("../View/updateConfView.fxml"));
/*
        Parent root = FXMLLoader.load(getClass().getResource("../View/ShowView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        root.getChildrenUnmodifiable().add(new Label("Jean"));
        stage.close();
        stage.setTitle("Before Show - Conference ");

        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();
*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

    windowCreateConference.getChildren().add(new Label("Jean"));

    }
}
