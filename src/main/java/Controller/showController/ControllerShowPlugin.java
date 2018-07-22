package Controller.showController;

import Controller.ControllerApi;
import Model.Conference;
import Model.Guest;
import Plugin.PluginManager;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowPlugin implements Initializable {

    @FXML
    private Accordion accordionView;

    @FXML
    private ComboBox cbListPlugin;
    
    public static int pluginSelected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	if(Plugin.PluginManager.plugins == null) {
    		loadPlugins();
    	} else {
    		Plugin.PluginManager.plugins.forEach(plugin -> cbListPlugin.getItems().add(plugin.getName()));
    	}
    		
    }
    
    @FXML
    public void loadPlugins() {
    	PluginManager pm = new PluginManager();
    	pm.loadPlugins();
    	
    	cbListPlugin.getItems().clear();
    	pm.plugins.forEach(plugin -> cbListPlugin.getItems().add(plugin.getName()));
    }
    

    @FXML
    public void usePlugin(ActionEvent event) throws IOException {
    	
    	Parent root;
    	this.pluginSelected = cbListPlugin.getSelectionModel().getSelectedIndex();
    	
    	if(PluginManager.plugins.get(pluginSelected).getName().equals("Plugin CSV") ) {
    		root = FXMLLoader.load(getClass().getResource("/View/pluginCSV.fxml"));
    	} else {
    		root = FXMLLoader.load(getClass().getResource("/View/pluginDOC.fxml"));
    	}
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
        stage.setTitle("Before Show - Plugin");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

    }
}
