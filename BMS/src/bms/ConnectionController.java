package bms;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.DriverManager;

public class ConnectionController {

    public TextField idLogin;
    public PasswordField idPassword;
    public TextField idIP;
    public TextField idPort;
    public Button idOK;
    public Button idClose;
    public static Label lll;

    public void sendConnectionData() {
        try {
            Class.forName("org.postgresql.Driver");
            Controller.connect = DriverManager.getConnection("jdbc:postgresql://"+idIP.getText()+
                ":"+idPort.getText(), idLogin.getText(), idPassword.getText());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        ((Stage)idOK.getScene().getWindow()).close();
        lll.setText("!: Підключення до БД - вдало");
    }

    public void closeConnectionWindow() {
        ((Stage)idClose.getScene().getWindow()).close();
    }
}
