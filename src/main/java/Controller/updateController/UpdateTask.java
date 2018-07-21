package Controller.updateController;

import Controller.ControllerApi;
import Controller.showController.ControllerInitConference;
import Model.Conference;
import Model.Task;
import Annotation.BeanFromDataBase;
import Annotation.ControllerAnnotation;
import Model.User;
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
    private static Conference c;

    @BeanFromDataBase
    private static Task task;

    @BeanFromDataBase
    private static User user;

    @FXML
    private ComboBox cbListConference;


    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfAmount;

    @FXML
    private TextField tfTime;

    @FXML
    private ComboBox cbListTask;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnDelete;


    @FXML
    public void updateLocate() throws IOException, InstantiationException, IllegalAccessException {
        cbListTask.getItems().clear();
        String idConference = cbListConference.getValue().toString().split("-")[0];
        cbListTask.setVisible(true);

        String allTask = new ControllerApi().get("task/getAllTaskForConference/" + idConference);
        Task[] tabTask = new Gson().fromJson(allTask, Task[].class);

        List<Task> listTask = Arrays.asList(tabTask);
        ArrayList<String> listTitle = new ArrayList<String>();

        for (Task t : listTask) {
            listTitle.add(t.getId() + "-" + t.getTitle());
        }

        cbListTask.getItems().addAll(listTitle);


        if(cbListTask.getItems().isEmpty()){
            cbListTask.setDisable(true);
        }else{
            cbListTask.setDisable(false);
        }
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfTitle.clear();
        tfAmount.clear();
        tfTime.clear();
        String idConference = cbListConference.getValue().toString().split("-")[0];
        if (cbListTask.isDisable()){
            return;
        }
        String idTask = ((ComboBox) event.getSource()).getValue().toString().split("-")[0];
        String url = "task/getTaskById/" + idTask + "/" + idConference;

        if(btnDelete.isVisible()){
            btnUpdate.setVisible(false);
        }else{
            btnUpdate.setVisible(true);
        }
       new  ControllerAnnotation().getBean(url, this.getClass(), task);

        tfTitle.setText(task.getTitle());
        tfAmount.setText(String.valueOf(task.getAmount()));
        tfTime.setText(task.getDuration());

    }


    @FXML
    public void updateDataBase() throws IOException {
        task.setTitle(tfTitle.getText());
        task.setAmount(Float.parseFloat(tfAmount.getText()));
        task.setDuration(tfTime.getText());


        String jsonTask = new Gson().toJson(task, Task.class);

        new ControllerApi().put("task/updateTask/" + task.getId(), jsonTask);

        btnNext.setVisible(true);
    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {
        System.out.println("Entrer");
        // Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateViews/updateConfView.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../updateGuest.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier une task ");

        stage.setScene(new Scene(root, root.getLayoutX(), root.getLayoutY()));
        stage.show();

    }

    @FXML
    public void deleteTask() throws IOException, IllegalAccessException, InstantiationException {

        String idTask = cbListTask.getValue().toString().split("-")[0];

        String url = "task/deleteTaskById/"+idTask;
        int code = new ControllerApi().delete(url);

        this.updateLocate();
        tfTitle.clear();
        tfAmount.clear();
        tfTime.clear();

        if(code <=200) {
            System.out.println("Delete !");
        }
        else {
            System.out.println("No delete");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbListConference = super.ComboBoxInitConference(cbListConference);

    }
}

