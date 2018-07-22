package Controller.showController;

import Controller.ControllerApi;
import Model.Conference;
import com.google.gson.Gson;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public abstract class ControllerInitConference {
    public ComboBox ComboBoxInitConference(ComboBox cb) {
        try {
            String jsonConferences = new ControllerApi().get("conference/getAllByUser/13");
            Conference[] tabConference = new Gson().fromJson(jsonConferences, Conference[].class);

            for (Conference c : tabConference) {
                cb.getItems().add(c.getId() + "-" + c.getName());

            }

            cb.autosize();
            return cb;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return cb;
    }


}
