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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ControllerApi {

   // private String http = "http://sebastiendelbeportfolio.alwaysdata.net/";
    private String http = "http://localhost:8080/";
    public String get(String url) throws IOException {
        String source = "";
        URL oracle = new URL(http+url);

        HttpURLConnection urlConnect = (HttpURLConnection) oracle.openConnection();
        urlConnect.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        urlConnect.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            source +=inputLine;
        in.close();
        urlConnect.getInputStream().close();

        return source;


    }



    public int post(String url, String json) throws IOException {

       URL oracle = new URL(http+url);
       HttpURLConnection connection = (HttpURLConnection) oracle.openConnection();
       connection.setRequestMethod("POST");
       connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type","application/json");

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        httpRequestBodyWriter.write(json);
        httpRequestBodyWriter.close();
        connection.getOutputStream().close();
        connection.disconnect();

        return connection.getResponseCode();

    }


    public int delete(String s) throws IOException {
        URL url = new URL(http+s);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded" );
        httpCon.setRequestMethod("DELETE");
        httpCon.connect();

        return httpCon.getResponseCode();
    }


    public int put(String s , String json) throws IOException {
        URL url = new URL(http+s);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty("Content-Type","application/json");
        httpCon.setRequestMethod("PUT");

        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(json);
        out.close();
        httpCon.getInputStream();

        return httpCon.getResponseCode();
    }



}
