package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ControllerApi {

    public String get(String url) throws IOException {
        String source = "";
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            source +=inputLine;
        in.close();


        return source;


    }



    public void post(String url, String json) throws IOException {

       URL oracle = new URL(url);
       HttpURLConnection connection = (HttpURLConnection) oracle.openConnection();
       connection.setRequestMethod("POST");
       connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type","application/json");

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        httpRequestBodyWriter.write(json);
        httpRequestBodyWriter.close();
        connection.disconnect();

        System.out.println(connection.getResponseCode());

    }

    @FXML
    public void navigate(ActionEvent event) throws IOException {

        Parent createConf = FXMLLoader.load(getClass().getResource("../View/CreateConfView.fxml"));

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setTitle("Before Show - Conference ");

        stage.setScene(new Scene(createConf, createConf.getLayoutX(), createConf.getLayoutY()));
        stage.show();


    }

}
