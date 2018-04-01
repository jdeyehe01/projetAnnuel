package sample.com.projetAnnuel.customField;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.com.projetAnnuel.annotation.PropertyLabel;
import sample.com.projetAnnuel.controller.Controller;
import sample.com.projetAnnuel.model.Evenement;


public abstract class AbstractCustomBuildForm extends Application {


	public void buildScreen(Stage stage , String viewName) {

		stage.setFullScreen(true);
		stage.setTitle("Before Show - "+ viewName);
		Group root = new Group();
		BorderPane border = new BorderPane();

		this.build(border);

		root.getChildren().add(border);
		Scene scene = new Scene(root,stage.getMaxHeight(),stage.getMaxHeight(), Color.LIGHTBLUE);
		border.prefHeightProperty().bind(scene.heightProperty());
		border.prefWidthProperty().bind(scene.widthProperty());
		stage.setScene(scene);
		stage.show();

	}


	public void build(BorderPane b) {
		this.buildForm(b);
		this.buildTop(b);
		this.buildBottom(b);
	}

	public void buildBottom(BorderPane bottom) {

		GridPane grid = new GridPane();
		int row = 0;
		grid.setAlignment(Pos.BOTTOM_RIGHT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15));

		Font font = Font.font("lobster",35);
		Button nextBtn = new Button("Suivant");

		nextBtn.setFont(font);

		grid.add(nextBtn, 0, 0);

		bottom.setBottom(grid);
	}

	public void buildForm(BorderPane rigth) {
		GridPane grid = new GridPane();
		int row = 0;
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));

		TextField tf = null;
		Font font = Font.font("lobster",45);

		String title = "Nouveau evenement";
		String des = "Dans cette rubrique, veuillez entrer les differentes informations de votre evenement";

		Label labelTitle = new Label(title);
		labelTitle.setAlignment(Pos.CENTER);
		Label labelDesc = new Label(des);

		labelTitle.setFont(font);
		font = Font.font("lobster", 25);
		labelDesc.setFont(font);

		grid.add(labelTitle, 2 , row);
		grid.setAlignment(Pos.CENTER);
		row++;
		grid.add(labelDesc, 2, row);
		row++;

		Controller controller = new Controller();

		ArrayList<Field> fields = controller.getFields(Evenement.class);

		for(Field f : fields) {
			PropertyLabel propertyLabel =  f.getAnnotation(PropertyLabel.class);
			Label label = new Label(propertyLabel.value());
			label.setFont(font);

			if(f.getType() != boolean.class){
				tf = new TextField();
				tf.setFont(font);
				label.setLabelFor(tf);
			}
			HBox horizontalLayout = new HBox();
			horizontalLayout.setPadding(new Insets(15,15,15,15));
			horizontalLayout.setSpacing(15);
			horizontalLayout.setAlignment(Pos.CENTER);
			tf.setAlignment(Pos.CENTER);
			horizontalLayout.getChildren().add(label);
			horizontalLayout.getChildren().add(tf);
			grid.add(label, 0, row);
			grid.add(tf, 2, row);
			row++;
		}

		rigth.setCenter(grid);

	}


	public void buildTop(BorderPane center) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		String urlImg = "./sample/com/projetAnnuel/Images/logo.jpg";
		Image image = new Image(urlImg);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(80);
		imageView.setPreserveRatio(true);

		grid.add(imageView, 1, 1);
		center.setTop(grid);
	}


}
