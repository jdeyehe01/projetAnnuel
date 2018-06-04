package Controller;

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

}
