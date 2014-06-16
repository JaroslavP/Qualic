package bms.Resours;

import bms.Bridge.*;
import bms.Controller;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionController {

    public TextField idLogin;
    public PasswordField idPassword;
    public TextField idIP;
    public TextField idPort;
    public Button idOK;
    public Button idClose;
    public static Label lll;
    public static Controller controller;

       public void sendConnectionData() {
        try {
            Class.forName("org.postgresql.Driver");
            Controller.connect = DriverManager.getConnection("jdbc:postgresql://"+idIP.getText()+
                ":"+idPort.getText(), idLogin.getText(), idPassword.getText());
            controller.bd.makeList();
            controller.idConn.setDisable(true);
            controller.idSaveBD.setDisable(false);
            controller.idSaveXLS.setDisable(false);
            controller.idDeleteRecord.setDisable(false);
            controller.idDiscon.setDisable(false);
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