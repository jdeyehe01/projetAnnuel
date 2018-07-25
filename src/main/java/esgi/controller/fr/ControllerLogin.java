package esgi.controller.fr;

import esgi.annotation.fr.ControllerAnnotation;
import esgi.annotation.fr.PasswordValue;
import esgi.model.fr.User;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField tfEmail;

    @PasswordValue
    @FXML
    private TextField tfPw;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lbInfoCheck;

    @FXML
    private Button btnEnrollement;

    private AlertMessage notification;

    public static String emailCurrentUser;

    public ControllerLogin() {
    }


    public void initialize(URL location, ResourceBundle resources) {
        notification = new AlertMessage();
    }


    @FXML
    public void signIn(ActionEvent event) throws IOException {


        User user = new User(tfEmail.getText(), tfPw.getText());
        String jsonUser = new Gson().toJson(user, User.class);
        String urlAuth = "user/auth";
        int code = new ControllerApi().post(urlAuth, jsonUser);
        if (code == 200) {
            emailCurrentUser = tfEmail.getText();
            navigateTo(event);
        } else {
            notification.notificationAndWait("La combinaison email/mot de passe est incorrecte");

        }

    }

    public void navigateTo(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/View/beforeShowWelcomeView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();
        stage.setTitle("Before Show - Accueil ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        tfEmail.clear();
        tfPw.clear();
        btnSignUp.setVisible(false);
        btnSignIn.setVisible(false);
        btnEnrollement.setVisible(true);
    }

    @FXML
    public void enrollment() throws IOException {
        if (tfEmail.getText().isEmpty() || tfPw.getText().isEmpty() || new ControllerAnnotation().verifyPassword(this.getClass(),tfPw)) {

            notification.notificationAndWait("Veuillez remplir tous les champs et respecter les critère");
            return;
        }

        User user = new User(tfEmail.getText(), tfPw.getText());

        String jsonUser = new Gson().toJson(user, User.class);
        int responseCode = new ControllerApi().post("user/signUp", jsonUser);

        if (responseCode == 200) {
            lbInfoCheck.setVisible(true);
            tfPw.clear();
            tfEmail.clear();

            btnEnrollement.setVisible(false);
            btnSignUp.setVisible(true);
            btnSignIn.setVisible(true);
        }
    }

}
