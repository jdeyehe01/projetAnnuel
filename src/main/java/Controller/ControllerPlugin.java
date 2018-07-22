package Controller;

import Model.Budget;
import Model.Conference;
import Model.Guest;
import Model.Locate;
import Model.Task;
import Plugin.IPlugin;
import Plugin.PluginManager;
import Annotation.BeanFromDataBase;
import Annotation.ControllerAnnotation;
import Controller.showController.ControllerInitShow;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPlugin extends ControllerInitShow implements Initializable {
	
	
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
	
	private int pluginSelected = Controller.showController.ControllerShowPlugin.pluginSelected;
	

	/**
	 * Cette m�thode est appeler lorsque l'on clique sur importer ou exporter les donn�es dans le plugin.
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
    		labelMsg.setText("Vous devez s�lectionner une conf�rence avant de pouvoir " + btnSave.getText() + ".");

    	}

    }
    
    /**
     * M�thode permettant d'importer les invit�s via le plugin.
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
    			labelMsg.setText("L'import des " + guests.size() + " invit�s a �t� effectu� avec succ�s.");
    		}
    		
    		if(!runPlugin || !result) {
    			labelMsg.setText("Une erreur est survenue durant l'import, veuillez v�rifier le fichier CSV.");
    		}
    	} 
    	else {
    		labelMsg.setText("Vous devez s�lectionner un fichier CSV avant de pouvoir importer.");
    	}
    }
    
    /**
     * M�thode permettant d'exporter les infos des conf�rences via le plugin.
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
    		System.out.println("run : " + runPlugin);
    		
    		if(runPlugin) {
    			labelMsg.setText("La conf�rence a �t� export�e avec succ�s.");
    		}
    		
    		if(!runPlugin || !result) {
    			labelMsg.setText("Une erreur est survenue durant l'export, veuillez v�rifier le dossier ou le fichier de destination.");
    		}
    	}
    	else {
    		labelMsg.setText("Vous devez sauvegarder le fichier sous format '.doc' avant de pouvoir exporter.");
    	}
    }
    
    /**
     * Cette m�thode est appeler pour choisir un fichier avec le composant File Chooser.
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
