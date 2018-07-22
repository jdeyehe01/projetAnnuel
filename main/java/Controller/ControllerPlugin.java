package Controller;

import Model.Conference;
import Model.Guest;
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
	
	private int pluginSelected = Controller.showController.ControllerShowPlugin.pluginSelected;
	

    @FXML
    public void save() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	
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
    				//String jsonGuest = new Gson().toJson("");
	    			for(Guest guest : guests) {
	    					//guest.setConf(cbListConf.getSelectionModel().getSelectedItem());
	            	        String jsonGuest = new Gson().toJson(guest);
	            	        
	    					System.out.println(cbListConf.getSelectionModel().getSelectedItem().toString());
	    			}
	    			//String jsonGuest = new Gson().toJson(guests);
	    			//new ControllerApi().post("guest", jsonGuest);
	    			//System.out.println(jsonGuest);
    			} 
				catch(Exception e) {
					result = false;
				}
    			labelMsg.setText("L'import des " + guests.size() + " invités a été effectué avec succès.");
    		}
    		
    		if(!runPlugin || result) {
    			labelMsg.setText("Une erreur est survenue durant l'import, veuillez vérifier le fichier CSV.");
    		}
    	} 
    	else {
    		labelMsg.setText("Vous devez sélectionner un fichier CSV avant de pouvoir importer.");
    	}
    	
    }
    
    @FXML
    public void addFile(ActionEvent event) {
    	
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
    	
    	File file = fc.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
    	
    	if(file != null) {
    		tfPath.setText(file.getAbsolutePath());
    		labelMsg.setText("");
    	}	

    }
    
    @FXML
    public void initConf() {
    	cbListConf = super.ComboBoxInitConference(cbListConf);
    }

    public void initialize(URL location, ResourceBundle resources) {    	
        labelTitleView.setText(PluginManager.plugins.get(pluginSelected).getName());
        labelDescription.setText(PluginManager.plugins.get(pluginSelected).getDescription());
    }
}
