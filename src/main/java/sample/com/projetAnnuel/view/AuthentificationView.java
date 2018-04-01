package sample.com.projetAnnuel.view;

import javafx.stage.Stage;
import sample.com.projetAnnuel.customField.AbstractCustomBuildForm;
import sample.com.projetAnnuel.model.Utilisateur;

public class AuthentificationView extends AbstractCustomBuildForm {

	@Override
	public void start(Stage stage) throws Exception {
		
		String title = "Login";
		String desc = "Veuillez vous connecter svp";
		
		super.buildScreen(stage, "Authentification", Utilisateur.class, title, desc);

	}

}
