package sample.com.projetAnnuel.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.com.projetAnnuel.annotation.PropertyLabel;
import sample.com.projetAnnuel.model.User;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class Main extends Application {
    private int cpt = 0;
    private int dec;
    @Override
    public void start(Stage primaryStage) throws Exception{

        AccueilView a = new AccueilView();
        a.start(primaryStage);
}



    public static void main(String[] args) {
        launch(args);
    }




    private void buildFom(Field[] fields ,Group root){

//        Field[] fields = User.class.getDeclaredFields();

        for(Field f : fields){
            if(f.isAnnotationPresent(PropertyLabel.class)){
                PropertyLabel plabel = f.getDeclaredAnnotation(PropertyLabel.class);
                System.out.println(plabel.value());

                Label label = new Label(plabel.value());
                label.setLayoutX(100 + dec);
                label.setLayoutY(100 + dec);

                root.getChildren().add(label);

                dec += 30;

            }else{
                System.out.println("L'annotation n'est pas presente");
            }
        }
    }
}
