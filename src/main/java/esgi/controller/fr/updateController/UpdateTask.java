package esgi.controller.fr.updateController;

import esgi.controller.fr.AlertMessage;
import esgi.controller.fr.ControllerApi;
import esgi.controller.fr.showController.ControllerInitConference;
import esgi.model.fr.Task;
import esgi.annotation.fr.BeanFromDataBase;
import esgi.annotation.fr.ControllerAnnotation;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateTask extends ControllerInitConference implements Initializable {

    @BeanFromDataBase
   private Task task;

    @FXML
    private ComboBox cbListConference;


    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfTime;

    @FXML
    private ComboBox cbListTask;

    @FXML
    private Button btnUpdate;


    @FXML
    private Button btnDelete;


    @FXML
    public void updateTask() throws IOException, InstantiationException, IllegalAccessException {
        String idConference = cbListConference.getValue().toString().split("-")[0];
        cbListTask.setVisible(true);

        if (cbListTask.getItems().size() > 0) {
            cbListTask.getProperties().clear();

        }

        String allTask = new ControllerApi().get("task/getAllTaskForConference/" + idConference);
        Task[] tabTask = new Gson().fromJson(allTask, Task[].class);

        List<Task> listTask = Arrays.asList(tabTask);
        ArrayList<String> listTitle = new ArrayList<String>();

        for (Task t : listTask) {
            listTitle.add(t.getId() + "-" + t.getTitle());
        }

        cbListTask.getItems().addAll(listTitle);


    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfTitle.clear();
        tfTime.clear();
        String idConference = cbListConference.getValue().toString().split("-")[0];

        String idTask = cbListTask.getValue().toString().split("-")[0];
        String url = "task/getTaskById/" + idTask + "/" + idConference;

        if (btnDelete.isVisible()) {
            btnUpdate.setVisible(false);
        } else {
            btnUpdate.setVisible(true);
        }
        task = (Task) new ControllerAnnotation().getBean(url, this.getClass(), Task.class);

        tfTitle.setText(task.getTitle());
        tfTime.setText(task.getDuration());

    }


    @FXML
    public void updateDataBase() throws IOException {
        try {
            if( tfTime.getText().isEmpty() || tfTitle.getText().isEmpty() ){
                new AlertMessage().notificationAndWait("Tous les champs ne sont pas correctes");
                return;
            }
            task.setTitle(tfTitle.getText());
            task.setDuration(tfTime.getText());

            String jsonTask = new Gson().toJson(task, Task.class);

            new ControllerApi().put("task/updateTask/" + task.getId(), jsonTask);
            updateTask();
            new AlertMessage().notificationAndWait("La tâche " +task.getTitle() + " a été mis à jour");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void deleteTask() throws IOException, IllegalAccessException, InstantiationException {

        String idTask = cbListTask.getValue().toString().split("-")[0];

        String url = "task/deleteTaskById/" + idTask;
        int code = new ControllerApi().delete(url);

        this.updateTask();
        tfTitle.clear();
        tfTime.clear();

        if(code == 200){
            new AlertMessage().notificationAndWait("La tâche " + task.getTitle() + " a été supprimé");
        }else{
            new AlertMessage().notificationAndWait("La tâche " + task.getTitle() + " n'a pas été supprimé");

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbListConference = super.ComboBoxInitConference(cbListConference);

    }
}

