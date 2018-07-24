package esgi.controller.fr;

import com.sun.istack.internal.NotNull;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Budget;
import esgi.model.fr.Conference;
import esgi.model.fr.Guest;
import esgi.model.fr.Locate;
import esgi.model.fr.Task;
import esgi.plugin.fr.IPlugin;
import esgi.plugin.fr.PluginManager;

import esgi.controller.fr.showController.ControllerShowPlugin;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPlugin extends ControllerInitConference implements Initializable {
	
	
	@FXML
	private Label labelTitleView;
	
	@FXML

	private Label labelDescription;
	
	@FXML
	private TextField tfPath;
	
	@FXML
	private Label labelMsg;
	
	@FXML
	private ComboBox cbListConf;
	
	@FXML
	private Button btnSave;
	
	private String idConference;
	
	private int pluginSelected = ControllerShowPlugin.pluginSelected;
	

	/**
	 * Cette méthode est appeler lorsque l'on clique sur importer ou exporter les données dans le plugin.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
    @FXML
    public void save() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
    	
    	IPlugin myPlugin = PluginManager.plugins.get(pluginSelected);
    	
    	if(idConference != null) {
    		if(myPlugin.getName().equals("Plugin CSV")) {
        		saveForCSV();
        	} else {
        		saveForDoc();
        	}
    	} else {
    		labelMsg.setText("Vous devez sélectionner une conférence avant de pouvoir " + btnSave.getText() + ".");

    	}

    }
    
    /**
     * Méthode permettant d'importer les invités via le plugin.
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public void saveForCSV() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	boolean result = true;
    	
    	if(tfPath.getText().trim().length() > 1 && tfPath.getText().endsWith(".csv")) {
    		
        	IPlugin myPlugin = PluginManager.plugins.get(pluginSelected);
        	
    		Method setPath = myPlugin.getClass().getMethod("setPath", String.class);
    		Method getListOfGuest = myPlugin.getClass().getMethod("getListOfGuest");
    		ArrayList<Guest> guests = (ArrayList<Guest>) getListOfGuest.invoke(myPlugin);
        	
    		setPath.invoke(myPlugin, tfPath.getText());
    		boolean runPlugin = myPlugin.runPlugin();
    		
    		if(runPlugin) {
    			try {
	    			for(Guest guest : guests) {
	    				String jsonGuest = new Gson().toJson(guest);
		    			new ControllerApi().post("guest", jsonGuest);
	    			}
    			} 
				catch(Exception e) {
					result = false;
				}
    			labelMsg.setText("L'import des " + guests.size() + " invités a été effectué avec succès.");
    		}
    		
    		if(!runPlugin || !result) {
    			labelMsg.setText("Une erreur est survenue durant l'import, veuillez vérifier le fichier CSV.");
    		}
    	} 
    	else {
    		labelMsg.setText("Vous devez sélectionner un fichier CSV avant de pouvoir importer.");
    	}
    }
    
    /**
     * Méthode permettant d'exporter les infos des conférences via le plugin.
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IOException
     */
    public void saveForDoc() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
    	boolean result = true;
    	
    	if(tfPath.getText().trim().length() > 1 && tfPath.getText().endsWith(".doc")) {
    		
    		IPlugin myPlugin = PluginManager.plugins.get(pluginSelected);
    		
    		String jsonConf = new ControllerApi().get("conference/getById/" + idConference);
            Conference conf = new Gson().fromJson(jsonConf, Conference.class);
    		
            String jsonBudget = new ControllerApi().get("budget/getAllBudgetForConference/" + idConference);
            Budget[] tabBudget = new Gson().fromJson(jsonBudget, Budget[].class);
            
            String jsonGuest = new ControllerApi().get("guest/getAllGuest/" + idConference);
            Guest[] tabGuest = new Gson().fromJson(jsonGuest, Guest[].class);
            
            String jsonTask = new ControllerApi().get("task/getAllTaskForConference/" + idConference);
            Task[] tabTask = new Gson().fromJson(jsonTask, Task[].class);
            
            String jsonLocate = new ControllerApi().get("locate/getAll/" + idConference);
            Locate[] tabLocate = new Gson().fromJson(jsonLocate, Locate[].class);
    		
    		Method setPath = myPlugin.getClass().getMethod("setPath", String.class);
    		Method setBudget = myPlugin.getClass().getMethod("setListOfBudget", Budget[].class);
    		Method setConference = myPlugin.getClass().getMethod("setConference", Conference.class);
    		Method setListOfGuest = myPlugin.getClass().getMethod("setListOfGuest", Guest[].class);
    		Method setListOfTask = myPlugin.getClass().getMethod("setListOfTask", Task[].class);
    		Method setLocate = myPlugin.getClass().getMethod("setListOfLocate", Locate[].class);
        	
    		setPath.invoke(myPlugin, tfPath.getText());
    		setBudget.invoke(myPlugin, new Object[]{tabBudget});
    		setConference.invoke(myPlugin, conf);
    		setListOfGuest.invoke(myPlugin, new Object[]{tabGuest});
    		setListOfTask.invoke(myPlugin, new Object[]{tabTask});
    		setLocate.invoke(myPlugin, new Object[]{tabLocate});
    		
    		boolean runPlugin = myPlugin.runPlugin();

    		if(runPlugin) {
    			labelMsg.setText("La conférence a été exportée avec succès.");
    		}
    		
    		if(!runPlugin || !result) {
    			labelMsg.setText("Une erreur est survenue durant l'export, veuillez vérifier le dossier ou le fichier de destination.");
    		}
    	}
    	else {
    		labelMsg.setText("Vous devez sauvegarder le fichier sous format '.doc' avant de pouvoir exporter.");
    	}
    }
    
    /**
     * Cette méthode est appelée pour choisir un fichier avec le composant File Chooser.
     * @param event
     */
    @FXML
    public void addFile(ActionEvent event) {
    	
    	FileChooser fc = new FileChooser();
    	IPlugin myPlugin = PluginManager.plugins.get(pluginSelected);
    	File file = null;
    	
    	if(myPlugin.getName().equals("Plugin CSV")) {
    		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        	
        	file = fc.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
        	
    	} else {
    		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Document File", "*.doc"));
        	
        	file = fc.showSaveDialog(((Stage)((Node) event.getSource()).getScene().getWindow()));
    	}
    	
    	if(file != null) {
    		tfPath.setText(file.getAbsolutePath());
    		labelMsg.setText("");
    	}	

    }
    
    @FXML
    public void initConf(ActionEvent event) {
    	idConference = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
    }

    public void initialize(URL location, ResourceBundle resources) {    
    	IPlugin myPlugin = PluginManager.plugins.get(pluginSelected);
    	
        labelTitleView.setText(PluginManager.plugins.get(pluginSelected).getName());
        labelDescription.setText(PluginManager.plugins.get(pluginSelected).getDescription());
        
        cbListConf = super.ComboBoxInitConference(cbListConf);
        
        if(myPlugin.getName().equals("Plugin CSV")) {
        	btnSave.setText("Importer");
        } else {
        	btnSave.setText("Exporter");
        }
    }
}
