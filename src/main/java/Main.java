import Plugin.PluginManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

       // Parent root = FXMLLoader.load(getClass().getResource("View/loginView.fxml"));
       //primaryStage.setTitle("Before Show - Authentification ");


        Parent root = FXMLLoader.load(getClass().getResource("View/beforShowWelcomeView.fxml"));
        primaryStage.setTitle("Before Show - Accueil ");


        //URL url = getClass().getResource("View/CreateConfView.fxml");
        //Parent root = FXMLLoader.load(url);
       //primaryStage.setTitle("Before Show - Créer une conférence ");

      //Parent root = FXMLLoader.load(getClass().getResource("View/createGuestView.fxml"));
      //primaryStage.setTitle("Before Show - Ajouter un invité ");


        //URL url = getClass().getResource("View/locateConfView.fxml");
        //Parent root = FXMLLoader.load(url);
       //primaryStage.setTitle("Before Show - Ajouter un lieu ");


       // URL url = getClass().getResource("View/presentationConfView.fxml");
       // Parent root = FXMLLoader.load(url);
       //primaryStage.setTitle("Before Show - Ajouter une presentation ");

     /* URL url = getClass().getResource("View/taskView.fxml");
      Parent root = FXMLLoader.load(url);
       primaryStage.setTitle("Before Show - Ajouter une tâche ");
       */
/*
        URL url = getClass().getResource("View/budgetView.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Before Show - Ajouter un budget ");

*/
       // Parent root = FXMLLoader.load(getClass().getResource("View/loginView.fxml"));
      // primaryStage.setTitle("Before Show - Login ");


       /* URL url = getClass().getResource("View/UpdateConference.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Before Show - Ajouter un budget ");
        */


        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
        //PluginManager pm = new PluginManager();
        //pm.loadPlugins();
    }
}
