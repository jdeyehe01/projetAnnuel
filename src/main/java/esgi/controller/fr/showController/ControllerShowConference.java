package esgi.controller.fr.showController;

import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import esgi.controller.fr.ControllerApi;
import esgi.controller.fr.ControllerLogin;
import esgi.model.fr.Conference;
import com.google.gson.Gson;
import esgi.model.fr.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowConference implements Initializable {

    @FXML
    private Accordion accordionView;

    @BeanFromDataBase
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            user = (User) new ControllerAnnotation().getBean("user/loggedInUser/"+ControllerLogin.emailCurrentUser,this.getClass(),User.class);
            this.showConference();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }


    public void showConference() {
        try {
            accordionView.getPanes().clear();

            String jsonConferences = new ControllerApi().get("conference/getAllByUser/1");
            String date = null;
            Conference[] tabConference = new Gson().fromJson(jsonConferences, Conference[].class);

            for (Conference c : tabConference) {
                TitledPane t = new TitledPane();
                t.setText(c.getName());

                VBox content = new VBox();
                content.getChildren().add(new Label("Description: " + c.getDescription()));
                if(c.getDate()!=null){
                    date = c.getDate().split("T")[0];
                }
                content.getChildren().add(new Label("Date: " + date));
                content.getChildren().add(new Label("Heure: " + c.gettime()));

                t.setContent(content);
                t.setExpanded(true);

                accordionView.getPanes().add(t);

            }

             accordionView.autosize();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}