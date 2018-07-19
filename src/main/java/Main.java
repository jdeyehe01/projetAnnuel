import Plugin.PluginManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("View/loginView.fxml"));
       primaryStage.setTitle("Before Show - Authentification ");

/*
        Parent root = FXMLLoader.load(getClass().getResource("View/beforShowWelcomeView.fxml"));
        primaryStage.setTitle("Before Show - Accueil ");
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
