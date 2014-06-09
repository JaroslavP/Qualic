package bms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Bridge bigBridge;
    public MenuItem idNewBridge;
    public Label idStatusString;
    public static Connection connect;
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    final LineChart<Number,Number> idGraf = new LineChart<Number,Number>(xAxis, yAxis);


    public TabPane idMainTab;
    // PASSPORT DATA
    public ImageView idPassportPicture;
    public Label idpn;      public Label idbr;
    public Label idct;      public Label idln;
    public Label idlc;      public Label iddl;
    public Label idiy;      public Label idcm;
    public Label idlt;      public Label idrp;
    public Label idNoPic;
    // SPECIFICATION
    public ImageView idSpecificationShema;
    public Label idlen;     public Label idroad;
    public Label idlroad;   public Label idrroad;
    public Label idfen;     public Label idwfen;
    public Label idangl;    public Label idload;
    public Label idNoShema;
    // VIEW
    public MenuItem idToolBar;      public MenuItem idStatusBar;
    public Pane idToolBarPane;      public Pane idStatusBarPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idStatusString.setText("!: Немає даних для відображення.");
        idMainTab.setDisable(true);
    }

    public void createBridge() {
        //qqq.setText(bigBridge.getSpecific().pathShema);

    }

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
        ConnectionController.lll = idStatusString;
        stage.show();
    }

    public void showDB(ActionEvent actionEvent) {
    }

    public void disconnectDB(){
        try {
            connect.close();
            idStatusString.setText("!: Зєднання з БД відключенно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cBridge() {
        idStatusString.setText("!: Об'єкт успішно створенно.");
        idMainTab.setDisable(false);
        // PASSPORT DATA
        idpn.setText(bigBridge.getBridgePasport().name);
        if (bigBridge.getBridgePasport().barrier.equals(""))
            idbr.setText("Інформація відсутня");
        else
            idbr.setText(bigBridge.getBridgePasport().barrier);
        idct.setText(bigBridge.getBridgePasport().Category);
        idln.setText(String.valueOf(bigBridge.getBridgePasport().lines));
        if (bigBridge.getBridgePasport().locality.equals(""))
            idlc.setText("Інформація відсутня");
        else
            idlc.setText(bigBridge.getBridgePasport().locality);
        if (idlc.getText().equals("Інформація відсутня"))
            iddl.setText("Інформація відсутня");
        else
            iddl.setText(String.valueOf(bigBridge.getBridgePasport().distance));
        idiy.setText(String.valueOf(bigBridge.getBridgePasport().input));
        if (bigBridge.getBridgePasport().company.equals(""))
            idcm.setText("Інформація відсутня");
        else
            idcm.setText(bigBridge.getBridgePasport().company);
        idlt.setText(String.valueOf(bigBridge.getBridgePasport().lifetime));
        if (bigBridge.getBridgePasport().repair == 1950)
            idrp.setText("Інформація відсутня");
        else
            idrp.setText(String.valueOf(bigBridge.getBridgePasport().repair));
        try {
            idPassportPicture.setImage(new Image(bigBridge.getBridgePasport().imgPath));
        } catch (Exception ignored) {
            idNoPic.setText("Зображення відсутнє !");
        }
        // SPECIFICATION
        idlen.setText(bigBridge.getSpecific().length);
        if (bigBridge.getSpecific().roadWidth == 0)
            idroad.setText("Інформація відсутня");
        else
            idroad.setText(String.valueOf(bigBridge.getSpecific().roadWidth));
        if (bigBridge.getSpecific().leftWalk == 0)
            idlroad.setText("Інформація відсутня");
        else
            idlroad.setText(String.valueOf(bigBridge.getSpecific().leftWalk));
        if (bigBridge.getSpecific().rightWalk == 0)
            idrroad.setText("Інформація відсутня");
        else
            idrroad.setText(String.valueOf(bigBridge.getSpecific().rightWalk));
        if (bigBridge.getSpecific().fence == 0)
            idfen.setText("Інформація відсутня");
        else
            idfen.setText(String.valueOf(bigBridge.getSpecific().fence));
        if (bigBridge.getSpecific().walkFence == 0)
            idwfen.setText("Інформація відсутня");
        else
            idwfen.setText(String.valueOf(bigBridge.getSpecific().walkFence));
        if (bigBridge.getSpecific().angle == 0)
            idangl.setText("Інформація відсутня");
        else
            idangl.setText(String.valueOf(bigBridge.getSpecific().angle));
        idload.setText(String.valueOf(bigBridge.getSpecific().load));
        try {
            idSpecificationShema.setImage(new Image(bigBridge.getSpecific().pathShema));
        } catch (Exception ignored) {
            idNoShema.setText("Зображення відсутнє !");
        }
    }

    public void showToolBar() {
        if (idToolBarPane.isVisible()) {
            idToolBar.setText("Панель інструментів");
            idToolBarPane.setVisible(false);
            idToolBarPane.setDisable(true);
            idMainTab.setLayoutY(25);
        } else {
            idToolBarPane.setDisable(false);
            idToolBar.setText("+ Панель інструментів");
            idMainTab.setLayoutY(59);
            idToolBarPane.setVisible(true);
        }
    }

    public void showStatusBar() {
        if (idStatusBarPane.isVisible()) {
            idStatusBar.setText("Рядок стану");
            idStatusBarPane.setVisible(false);
            idStatusBarPane.setDisable(true);
        }else {
            idStatusBar.setText("+ Рядок стану");
            idStatusBarPane.setVisible(true);
            idStatusBarPane.setDisable(false);
        }
    }
}
