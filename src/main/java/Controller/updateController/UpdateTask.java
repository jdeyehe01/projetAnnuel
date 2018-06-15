package Controller.updateController;

import Controller.ControllerApi;
import Model.Conference;
import Model.Task;
import Model.Task;
import annotation.BeanFromDataBase;
import annotation.ControllerAnnoation;
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

public class UpdateTask implements Initializable {

    @BeanFromDataBase
    private static Conference c;

    @BeanFromDataBase
    private static Task task;


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
    public void updateLocate() throws IOException, InstantiationException, IllegalAccessException {

        String url = "http://localhost:8080/conference/lastConf";
        cbListTask.setVisible(true);

        ControllerAnnoation.getBean(url,this.getClass(),c);

        String allTask = new ControllerApi().get("http://localhost:8080/task//getAllTaskForConference/"+c.getId());
        Task[] tabTask =  new Gson().fromJson(allTask, Task[].class);

        List<Task> listTask = Arrays.asList(tabTask);
        ArrayList<String> listTitle = new ArrayList<String>();

        for(Task t : listTask){
            listTitle.add(t.getId()+"-"+t.getTitle());
        }

        cbListTask.getItems().addAll(listTitle);
    }


    @FXML
    public void initialisationForm(ActionEvent event) throws InstantiationException, IllegalAccessException {
        tfTitle.clear();
        tfAmount.clear();
        tfTime.clear();

        String idTask = ((ComboBox)event.getSource()).getValue().toString().split("-")[0];
        String url = "http://localhost:8080/task/getTaskById/"+idTask+"/"+c.getId();
        btnUpdate.setVisible(true);
        ControllerAnnoation.getBean(url,this.getClass(),task);

        tfTitle.setText(task.getTitle());
        tfAmount.setText(String.valueOf(task.getAmount()));
        tfTime.setText(task.getDuration());

    }



    @FXML
    public void updateDataBase() throws IOException {
        task.setTitle(tfTitle.getText());
        task.setAmount(Float.parseFloat(tfAmount.getText()));
        task.setDuration(tfTime.getText());

        String jsonTask = new Gson().toJson(task,Task.class);

        new ControllerApi().post("http://localhost:8080/task/updateTask/"+task.getId(),jsonTask);

        btnNext.setVisible(true);
    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {
        System.out.println("Entrer");
        // Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateViews/updateConfView.fxml"));
        Parent root  = FXMLLoader.load(getClass().getResource("../updateGuest.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Modifier une task ");

        stage.setScene(new Scene(root, root.getLayoutX(), root.getLayoutY()));
        stage.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            this.updateLocate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

