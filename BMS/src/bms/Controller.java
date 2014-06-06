package bms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Controller {

    public AnchorPane idAP;

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
        CreateNewObject.control = this;
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

    public Label qqq;
    public Bridge bigBridge;
    public Pane idToolbar;
    public Pane idStaеtsPane;
    public MenuItem idNewBridge;
    public Label idStaеtsString;
    public static Connection connect;
    public TextField TA;
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    final LineChart<Number,Number> idGraf = new LineChart<Number,Number>(xAxis, yAxis);

    public void cBridge() {
        bigBridge.createRating();
        double aaa = bigBridge.getRating().wear;
        TA.setText("asdfgsadfgjdsfnjg");
        qqq.setText(String.valueOf(aaa));
    }

    public void createBridge() {
        //qqq.setText(Double.toString(bigBridge.getSupport().header));

    }

    public void grafff() {
        idGraf.setTitle("qqqqqq");
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        double U = bigBridge.getRating().wear;
        for (int i = 0; i < 100; i++) {
            U = U/58;
            series.getData().add(new XYChart.Data(i, U));
        }
        idGraf.getData().add(series);
        idAP.getChildren().add(idGraf);

    }
}
