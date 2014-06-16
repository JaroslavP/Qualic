package bms.Resours;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class About {
    public Button idOK;

    public void bye(ActionEvent actionEvent) {
        ((Stage) idOK.getScene().getWindow()).close();
    }
}
