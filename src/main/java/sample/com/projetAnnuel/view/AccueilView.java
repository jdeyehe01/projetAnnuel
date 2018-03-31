package sample.com.projetAnnuel.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccueilView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Before Show");
        Group root = new Group();
        Scene scene = new Scene(root,500,500, Color.LIGHTBLUE);
        BorderPane border = new BorderPane();

        border.prefHeightProperty().bind(scene.heightProperty());
        border.prefWidthProperty().bind(scene.widthProperty());


        this.builHome(border);

        root.getChildren().add(border);
        primaryStage.setScene(scene);
        primaryStage.show();



    }


    private void buildTop(BorderPane top){

        String urlImg = "./sample/com/projetAnnuel/Images/logo.jpg";
        Image image = new Image(urlImg);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);
        top.setTop(imageView);

    }

    private void buildCenter(BorderPane center){

        TilePane tilePane = new TilePane();
        tilePane.setVgap(4);
        tilePane.setHgap(4);

        tilePane.setOrientation(Orientation.VERTICAL);

        String title = "Bienvenue sur Before Show !";
        String description = "Before Show est une plateforme de gestion d'inscription et d'organisation d'évènements. Que cela soit pour un mariage ou encore un anniversaire, la gestion de vos invités est un élément essentiel. Avec Before Show , envoyez vos invitation et collecter les réponses en quelques étapes. \n Et bien plus encore.";
        Text titleTxt = new Text(title);
        Text descriptionTxt = new Text(description);

        Font f = Font.font("lobster",20);
        titleTxt.setFont(f);
        descriptionTxt.setFont(f);

        tilePane.getChildren().addAll(titleTxt,descriptionTxt);

        center.setCenter(tilePane);

    }


    private void buildBottom(BorderPane pane){

        TilePane tilePane = new TilePane();
        tilePane.setVgap(4);
        tilePane.setHgap(4);

        tilePane.setOrientation(Orientation.VERTICAL);

        Button createEventBtn = new Button("Créer un évènement");
        Button updateEventBtn = new Button("Modifier un évènement");
        Button listEventBtn = new Button ("Liste des évènements");

        tilePane.getChildren().add(createEventBtn);
        tilePane.getChildren().add(updateEventBtn);
        tilePane.getChildren().add(listEventBtn);

        pane.setBottom(tilePane);

    }

    private void builHome(BorderPane pane){

        buildTop(pane);
        buildCenter(pane);
        buildBottom(pane);
    }

}
