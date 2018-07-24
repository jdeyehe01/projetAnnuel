package esgi.controller.fr;

import javafx.scene.control.Alert;

public class AlertMessage {

    public void notificationAndWait(String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
