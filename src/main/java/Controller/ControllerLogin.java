package Controller;

import Model.User;
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
import javafx.scene.control.MenuBar;
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

    @FXML
    private TextField tfPw;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnNext;

    @FXML
    private Label lbInfoCheck;

    @FXML
    private Button btnEnrollement;




    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void signIn(javafx.event.ActionEvent actionEvent) throws IOException {

        User user = new User(tfEmail.getText(),tfPw.getText());
        String jsonUser = new Gson().toJson(user,User.class);
        String urlAuth = "user/auth";
       int code = new ControllerApi().post(urlAuth,jsonUser);
       if(code <= 200){
           System.out.println("L'utilisateur est connecté");
           btnNext.setVisible(true);

       }else{
           System.out.println("L'utilisateur est n'est pas connecté");

       }

    }

    @FXML
    public void navigateTo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/beforShowWelcomeView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        stage.close();
        stage.setTitle("Before Show - Accueil ");
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void signUp(){
        System.out.println("click");
        tfEmail.clear();
        tfPw.clear();
        btnNext.setVisible(false);
        btnSignUp.setVisible(false);
        btnSignIn.setVisible(false);
        btnEnrollement.setVisible(true);
    }

    @FXML
    public void enrollment() throws IOException {
        if(tfEmail.getText().isEmpty() || tfPw.getText().isEmpty()){
            return;
        }

        User user = new User(tfEmail.getText(),tfPw.getText());

        String jsonUser = new Gson().toJson(user,User.class);
        int responseCode = new ControllerApi().post("user/signUp" ,jsonUser );

        if(responseCode <=201){
            lbInfoCheck.setVisible(true);
            tfPw.clear();
            tfEmail.clear();

            btnEnrollement.setVisible(false);
            btnSignUp.setVisible(true);
            btnSignIn.setVisible(true);
        }


    }

}
