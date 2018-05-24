package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
     //   Parent root = FXMLLoader.load(getClass().getResource("View/beforShowWelcomeView.fxml"));
   //     primaryStage.setTitle("Before Show - Accueil ");


    //    Parent root = FXMLLoader.load(getClass().getResource("View/CreateConfView.fxml"));
  //      primaryStage.setTitle("Before Show - Créer une conférence ");

    //    Parent root = FXMLLoader.load(getClass().getResource("View/createGuestView.fxml"));
     //   primaryStage.setTitle("Before Show - Ajouter un invité ");


     //   Parent root = FXMLLoader.load(getClass().getResource("View/locateConfView.fxml"));
     //   primaryStage.setTitle("Before Show - Ajouter un invité ");


        Parent root = FXMLLoader.load(getClass().getResource("View/loginView.fxml"));
        primaryStage.setTitle("Before Show - Login ");


       // Controller conroller = FXMLLoader.load(getClass().getResource("Controller/Controller.java"));
        primaryStage.setScene(new Scene(root, 629, 424));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
