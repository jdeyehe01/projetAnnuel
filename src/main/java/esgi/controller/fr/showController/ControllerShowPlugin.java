package esgi.controller.fr.showController;

import esgi.plugin.fr.PluginManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
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
    	if(PluginManager.plugins == null) {
    		loadPlugins();
    	} else {
    		PluginManager.plugins.forEach(plugin -> cbListPlugin.getItems().add(plugin.getName()));
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

    	this.pluginSelected = cbListPlugin.getSelectionModel().getSelectedIndex();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/View/pluginView.fxml"));
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
        stage.setTitle("Before Show - Plugin");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

    }
}
