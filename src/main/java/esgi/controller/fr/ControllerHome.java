package esgi.controller.fr;


import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import esgi.model.fr.Conference;
import esgi.model.fr.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerHome extends ControllerLogin implements Initializable {
    @FXML
    private MenuBar menuBar;

    @FXML
    private ScrollPane fiveLast;

    @BeanFromDataBase
    private User user;

    @BeanFromDataBase
    private Conference[] tabConf;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ControllerAnnotation controllerA = new ControllerAnnotation();
            user = (User) controllerA.getBean("user/loggedInUser/" + ControllerLogin.emailCurrentUser, this.getClass(), User.class);

            String url = "conference/getFiveLast/" + user.getId();

            tabConf = (Conference[]) controllerA.getBean(url, this.getClass(), Conference[].class);
            VBox vBox = new VBox();

            if (tabConf.length > 0) {
                for (Conference c : tabConf) {
                    String confInfo = c.getId() + "-" + c.getName() + " le " + c.getDate().split("T")[0];
                    vBox.getChildren().add(new Label(confInfo));

                    fiveLast.setVisible(true);
                }
                fiveLast.setContent(vBox);

            } else {
                fiveLast.setVisible(false);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

}
