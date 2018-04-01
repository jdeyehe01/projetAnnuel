package sample.com.projetAnnuel.view;

import javafx.stage.Stage;
import sample.com.projetAnnuel.customField.AbstractCustomBuildForm;
import sample.com.projetAnnuel.model.Personne;

public class PersonneViewForm extends AbstractCustomBuildForm {

	@Override
	public void start(Stage stage) throws Exception {
		
		String title = "Nouveau invit�";
		String des = "Dans cette rubrique, veuillez entrer les differentes informations de votre invit� ";
		
		
		super.buildScreen(stage, "Ajouter Personne" , Personne.class , title , des);
	}

}
