package bms;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Controller {

    public void createNewObject() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML/createObject.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Новий міст");
        stage.setScene(new Scene(root, 690, 540));
        stage.setResizable(false);
        stage.show();
    }

    public void connectDB() throws InterruptedException {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML/connectionWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Підключення БД");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ConnectionController.lll = idStaеtsString;
        stage.show();
    }

    public void disconnectDB(){
        try {
            connect.close();
            idStaеtsString.setText("!: Зєднання з БД відключенно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pane idToolbar;
    public Pane idStaеtsPane;
    public MenuItem idNewBridge;
    public Label idStaеtsString;
    public static Connection connect;
}
