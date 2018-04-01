package sample.com.projetAnnuel.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.com.projetAnnuel.customField.AbstractCustomBuildForm;
import javafx.scene.layout.VBox;


public class AccueilView extends AbstractCustomBuildForm {
    @Override
    public void start(Stage primaryStage) throws Exception {
    	primaryStage.setFullScreen(true);
        primaryStage.setTitle("Before Show -  Accueil ");
        Group root = new Group();
        BorderPane border = new BorderPane();
        
        
        this.builHome(border);

        root.getChildren().add(border);
        Scene scene = new Scene(root,primaryStage.getMaxHeight(),primaryStage.getMaxWidth(), Color.LIGHTBLUE);
        border.prefHeightProperty().bind(scene.heightProperty());
        border.prefWidthProperty().bind(scene.widthProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }


    public void buildTop(BorderPane top){
    	
    	GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

        String urlImg = "./sample/com/projetAnnuel/Images/logo.jpg";
        Image image = new Image(urlImg);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);
        
        grid.add(imageView, 0, 0);
		top.setTop(grid);
        

    }

    public void buildCenter(BorderPane center){

        GridPane grid = new GridPane();
		int row = 0;
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));

		TextField tf = null;
		Font font = Font.font("lobster",75);

		String title = "Bienvenue sur Before Show !";
		String des = "Before Show est une plateforme de gestion d'inscription et d'organisation d'evenements. \n Que cela soit pour un mariage ou encore un anniversaire, la gestion de vos invites est un element essentiel. \n Avec Before Show , envoyez vos invitation et collecter les reponses en quelques etapes. \n Et bien plus encore.";


		Label labelTitle = new Label(title);
		labelTitle.setAlignment(Pos.CENTER);
		Label labelDesc = new Label(des);

		labelTitle.setFont(font);
		font = Font.font("lobster", 33);
		labelDesc.setFont(font);

		grid.add(labelTitle, 2 , row);
		grid.setAlignment(Pos.CENTER_RIGHT);
		row++;
		grid.add(labelDesc, 2, row);
		row++;

        center.setCenter(grid);

    }


    public void buildBottom(BorderPane pane){
		Font font = Font.font("lobster",30);
		
		VBox verticalLayout = new VBox();
		verticalLayout.setPadding(new Insets(15));
		verticalLayout.setSpacing(15);
		verticalLayout.setAlignment(Pos.BOTTOM_CENTER);

        Button createEventBtn = new Button("Creer un Evenement");
        Button updateEventBtn = new Button("Modifier un evenement");
        Button listEventBtn = new Button ("Liste des evenements");
        
        
		createEventBtn.setFont(font);
		updateEventBtn.setFont(font);
		listEventBtn.setFont(font);
		
		verticalLayout.getChildren().addAll(createEventBtn,updateEventBtn,listEventBtn);
		
		pane.setBottom(verticalLayout);
    }

    private void builHome(BorderPane pane){

        buildTop(pane);
        buildCenter(pane);
        buildBottom(pane);
    }

}
