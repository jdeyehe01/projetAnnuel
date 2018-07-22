package Controller;


import Annotation.BeanFromDataBase;
import Annotation.ControllerAnnotation;
import Model.Conference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class ControllerHome implements Initializable {
    @FXML
    private MenuBar menuBar;

    @FXML
    private ScrollPane fiveLast;

    @BeanFromDataBase
    private static Conference[] tabConf;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
           String url = "conference/getFiveLast/13";

            ControllerAnnotation.getBean(url,this.getClass(),tabConf);
            VBox vBox = new VBox();

            if(tabConf.length>0){
                for(Conference c : tabConf){
                    String confInfo = c.getId() + "-" + c.getName() + " le " + c.getDate().split("T")[0];
                    vBox.getChildren().add(new Label(confInfo));

                    fiveLast.setVisible(true);
                    }
                fiveLast.setContent(vBox);

            }else{
                fiveLast.setVisible(false);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void navigate(ActionEvent event) throws IOException {

        Parent createConference = FXMLLoader.load(getClass().getResource("../View/CreateConfView.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
       menuBar.prefWidthProperty().bind(stage.widthProperty());

        stage.close();
        stage.setTitle("Before Show - Créer une Conference ");
        stage.setScene(new Scene(createConference));
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
