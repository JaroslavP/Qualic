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
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public static Bridge categiryForGraf;

    public Bridge bigBridge;
    public MenuItem idNewBridge;
    public Label idStatusString;
    public static Connection connect;
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
    // EXP
    public Label idStan;    public Label idWear;
    public Label idMove;    public Label idLife;
    public Label idLd;      public Text idText;
    public TitledPane idDeckInfo;
    public Label idd1;      public Label idd2;
    public Label idd3;      public Label idd4;
    public Label idd5;
    public TitledPane idSSInfo;
    public Label idSS1;     public Label idSS2;
    public Label idSS3;     public Label idSS4;
    public Label idSS5;     public Label idSS6;
    public Label idSS7;     public Label idSS8;
    public TitledPane idRSInfo;
    public Label idRS1;     public Label idRS2;
    public Label idRS3;     public Label idRS4;
    public TitledPane idSuppInfo;
    public Label idS1;      public Label idS2;
    public Label idS3;
    // MENU
    public MenuItem idLoadXLS;
    public MenuItem idLoadBD;
    public MenuItem idSaveXLS;
    public MenuItem idSaveBD;
    public MenuItem idResours;
    public MenuItem idEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idStatusString.setText("!: Немає даних для відображення.");
        idMainTab.setDisable(true);
    }

    public void LoadXLS() {

    }

    public void LoadBD() {
    }

    public void SaveXLS() {
    }

    public void SaveBD() {
    }

    public void out() {
        System.exit(0);
    }

    public void Resours() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML/Graf.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Залишковий ресурс");
        stage.setScene(new Scene(root, 739, 429));
        stage.setResizable(false);
        stage.show();
    }

    public void Edit() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML/EditObject.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Редагування");
        stage.setScene(new Scene(root, 690, 540));
        stage.setResizable(false);
        EditObject.Edit = this;
        stage.show();
    }

    public void makeSmeta(ActionEvent actionEvent) {
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

    public void disconnectDB(){
        try {
            connect.close();
            idStatusString.setText("!: Зєднання з БД відключенно");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cBridge() {
        bigBridge.createRating();
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
        idrp.setText(String.valueOf(2014));
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
        // CATEGORY
        String str = "";
        idText.setFont(Font.font("Times NewRoman", 16));
        if (bigBridge.getRating().categoruStatus == 1) {
            str = "I";
            idText.setText("Ведуться планові обстеження та догляд ");
        } else if (bigBridge.getRating().categoruStatus == 2) {
            str = "II";
            idText.setText("Ведуться планові обстеження, догляд та поточні ремонти без обмеження руху ");
        } else if (bigBridge.getRating().categoruStatus == 3) {
            str = "III";
            idText.setText("Ведуться планові обстеження, скорочуються терміни між періодичними оглядами, " +
                    "виконуються поточні ремонти. За необхідності, обмежується швидкість руху ");
        } else if (bigBridge.getRating().categoruStatus == 4) {
            str = "IV";
            idText.setText("Ведуться обстеження за спеціальним графіком, виконується капітальний ремонт. " +
                    "Відповідно до дефектів конструкцій обмежується рух транспортних засобів за вагою, швидкістю" +
                    " та габаритними параметрами. За необхідності, розробляються спеціальні заходи із забезпечення " +
                    "безаварійної експлуатації моста");
        } else if (bigBridge.getRating().categoruStatus == 5) {
            str = "V";
            idText.setText("Ведеться постійний нагляд та контроль за виконанням обмежень руху з залученням " +
                    "спеціалізованої організації. Терміново вирішується питання про реконструкцію споруди або про її закриття. " +
                    "Вживаються тимчасові заходи до запобігання аварії");
        }
        idStan.setText("Міст перебуває в " + str + " експлуатаційному стані");
        idWear.setText("Загальна оцінка зносу " + Math.rint(bigBridge.getRating().wear*100)/100 + "%");
        idMove.setText(bigBridge.getRating().safety);
        idLife.setText("Термін експлуатації " + bigBridge.getRating().longevity);
        idLd.setText("Зниження вантажопідйомності " + Math.rint(bigBridge.getRating().capacity*100)/100 + "%");
        idDeckInfo.setExpanded(true);
        idDeckInfo.setText("Оцінка мостового полотна " + Math.rint(bigBridge.getRating().rDeck*100)/100 + "%");
            idd1.setText("Покриття " +  Math.rint(bigBridge.getDeck().coating*100)/100 + "%");
            idd2.setText("Ситема водовідведення " + Math.rint(bigBridge.getDeck().drainage*100)/100 + "%");
            idd3.setText("Деформаційні шви " + Math.rint(bigBridge.getDeck().expensionJoins*100)/100 + "%");
            idd4.setText("Огожі " + Math.rint(bigBridge.getDeck().fence*100)/100 + "%");
            idd5.setText("Тротуари " + Math.rint(bigBridge.getDeck().sidewalk*100)/100 + "%");
        idSSInfo.setText("Оцінка прогонових споруд " + Math.rint(bigBridge.getRating().rSuperStruct*100)/100 + "%");
            idSS1.setText("Тріщини у розрягнутому бетоні " + Math.rint(bigBridge.getSuperStructure().crack*100)/100 + "%");
            idSS2.setText("Силові тріщини з рокриттям " + Math.rint(bigBridge.getSuperStructure().powerCrack*100)/100 + "%");
            idSS3.setText("Корозія поверхні балок " + Math.rint(bigBridge.getSuperStructure().corrosion*100)/100 + "%");
            idSS4.setText("Пошкодження антикорозійного покриття " + Math.rint(bigBridge.getSuperStructure().damageRust*100)/100 + "%");
            idSS5.setText("Розірванні шви зварювання " + Math.rint(bigBridge.getSuperStructure().disSeam*100)/100 + "%");
            idSS6.setText("Місцеві погнутості балок " + Math.rint(bigBridge.getSuperStructure().defBeam*100)/100 + "%");
            idSS7.setText("Залишкові прогини балок " + Math.rint(bigBridge.getSuperStructure().resBeam*100)/100 + "%");
            idSS8.setText("Трищини в болтах/гайках " + Math.rint(bigBridge.getSuperStructure().crackBolt*100)/100 + "%");
        idSuppInfo.setText("Оцінка опори " + Math.rint(bigBridge.getRating().rSupport*100)/100 + "%");
            idS1.setText("Ригель " +  Math.rint(bigBridge.getSupport().header*100)/100 + "%");
            idS2.setText("Тіло опори " +  Math.rint(bigBridge.getSupport().bodySupport*100)/100 + "%");
            idS3.setText("Фундамент " +  Math.rint(bigBridge.getSupport().foundation*100)/100 + "%");
        idRSInfo.setText("Оцінка регуляційних споруд " +  Math.rint(bigBridge.getRating().rRegulStruct*100)/100 + "%");
            idRS1.setText("Пошкодженн кріплень підходів " +  Math.rint(bigBridge.getRegulatoryStructure().areaDamege*100)/100 + "%");
            idRS2.setText("Колії на поверхні покриття " +  Math.rint(bigBridge.getRegulatoryStructure().track*100)/100 + "%");
            idRS3.setText("Зниження швидкості руху " +  Math.rint(bigBridge.getRegulatoryStructure().speedLimit*100)/100 + "%");
            idRS4.setText("Втрати конусом об'єму " +  Math.rint(bigBridge.getRegulatoryStructure().cone*100)/100 + "%");

        categiryForGraf = bigBridge;
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