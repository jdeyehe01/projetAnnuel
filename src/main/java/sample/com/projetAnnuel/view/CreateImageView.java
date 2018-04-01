package sample.com.projetAnnuel.view;

import javafx.stage.Stage;
import sample.com.projetAnnuel.customField.AbstractCustomBuildForm;
import sample.com.projetAnnuel.model.Image;

public class CreateImageView extends AbstractCustomBuildForm {

	@Override
	public void start(Stage stage) throws Exception {
		
		String title = "Nouvelle image";
		String desc = "Dans cette rubrique, veuillez entrer les differentes informations de votre image";
		
		super.buildScreen(stage, "Image", Image.class, title, desc);
		

	}

}
