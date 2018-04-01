/**
 * 
 */
package sample.com.projetAnnuel.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;
import java.lang.reflect.Field;
import java.text.Annotation;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

import javax.swing.text.LabelView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.com.projetAnnuel.annotation.PropertyLabel;
import sample.com.projetAnnuel.controller.Controller;
import sample.com.projetAnnuel.customField.AbstractCustomBuildForm;
import sample.com.projetAnnuel.model.Evenement;
/**
 * @author jeand
 *
 */
public class CreateEventFormView extends AbstractCustomBuildForm {

	@Override
	public void start(Stage stage) throws Exception {
		super.buildScreen(stage, "Creation evenement");
	}
}
