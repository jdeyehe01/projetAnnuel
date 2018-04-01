package sample.com.projetAnnuel.view;

import javafx.stage.Stage;
import sample.com.projetAnnuel.customField.AbstractCustomBuildForm;
import sample.com.projetAnnuel.model.Budget;

public class CreateBudget extends AbstractCustomBuildForm {

	@Override
	public void start(Stage stage) throws Exception {
		
		String title = "Nouveau budget";
		String desc = "Dans cette rubrique, veuillez entrer les differentes informations de votre budget";
		
		super.buildScreen(stage, "Budget", Budget.class, title, desc);
	}

}
