import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
     //   Parent root = FXMLLoader.load(getClass().getResource("View/beforShowWelcomeView.fxml"));
   //     primaryStage.setTitle("Before Show - Accueil ");

        URL url = getClass().getResource("View/CreateConfView.fxml");
    Parent root = FXMLLoader.load(url);
       primaryStage.setTitle("Before Show - Créer une conférence ");

      // Parent root = FXMLLoader.load(getClass().getResource("View/createGuestView.fxml"));
    //   primaryStage.setTitle("Before Show - Ajouter un invité ");


       //Parent root = FXMLLoader.load(getClass().getResource("View/locateConfView.fxml"));
       //primaryStage.setTitle("Before Show - Ajouter un invité ");


       // Parent root = FXMLLoader.load(getClass().getResource("View/loginView.fxml"));
      // primaryStage.setTitle("Before Show - Login ");


       // Controller conroller = FXMLLoader.load(getClass().getResource("Controller/Controller.java"));
        primaryStage.setScene(new Scene(root, root.getLayoutX(), root.getLayoutY()));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
