package bms.Resours;

import bms.Bridge.Bridge;
import bms.Bridge.BridgePasport;
import bms.Bridge.Specific;
import bms.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditObject implements Initializable{

    public Button createButton;
    public static Controller Edit;
    public Button closeButton;
    boolean P = false;
    boolean S = false;
    // PASSPORT
    public TextField idBridgeName;          public ComboBox<Integer> idInput;
    public TextField idBarrier;             public TextField idCompany;
    public ComboBox<String> idCategory;     public ComboBox<Integer> idLifetime;
    public ComboBox<Integer> idLines;
    public TextField idLocality;            public String pathPic;
    public TextField idDistance;            public Button idReadyPasport;
    public Button idChooserPic;
    // SPECIFICATION
    public ComboBox<String> idLength;       public TextField idAngle;
    public TextField idRoadWidth;           public TextField idLoad;
    public TextField idLeftWidth;           public TextField idFLoad;
    public TextField idRightWidth;          public String pathShema;
    public ComboBox<Integer> idFence;       public Button idChooserShema;
    public ComboBox<Integer> idWalkFence;   public Button idReadySpecification;
    // DECK


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCategory.getItems().addAll("Магістраль 120км/год", "Загальні 90км/год", "Звичайні 60км/год");
        idLines.getItems().addAll(1,2,3,4,5,6,7,8);
        List<Integer> years = new ArrayList<Integer>();
        List<Integer> yearss = new ArrayList<Integer>();
        for (int i = 1950; i <= 2014; i++) {
            years.add(i); }
        for (int i = 2015; i <= 2114; i++) {
            yearss.add(i); }
        ObservableList<Integer> integerObservableList = FXCollections.observableArrayList(years);
        ObservableList<Integer> iOL = FXCollections.observableArrayList(yearss);
        idInput.getItems().addAll(integerObservableList);
        idLifetime.getItems().addAll(iOL);
        idFence.getItems().addAll(0,1,2,3);
        idWalkFence.getItems().addAll(0,1,2,3,4);

        Bridge Edit = Controller.categiryForGraf;

        idBridgeName.setText(Edit.getBridgePasport().name);
        idBarrier.setText(Edit.getBridgePasport().barrier);
        idCategory.setValue(Edit.getBridgePasport().Category);
        idLines.setValue(Edit.getBridgePasport().lines);
        idLocality.setText(Edit.getBridgePasport().locality);
        idDistance.setText(String.valueOf(Edit.getBridgePasport().distance));
        idInput.setValue(Edit.getBridgePasport().input);
        idCompany.setText(Edit.getBridgePasport().company);
        idLifetime.setValue(Edit.getBridgePasport().lifetime);
        pathPic = Edit.getBridgePasport().imgPath;

        idLength.setValue(Edit.getSpecific().length);
        idFence.setValue(Edit.getSpecific().fence);
        idWalkFence.setValue(Edit.getSpecific().walkFence);
        idRoadWidth.setText(String.valueOf(Edit.getSpecific().roadWidth));
        idLeftWidth.setText(String.valueOf(Edit.getSpecific().leftWalk));
        idRightWidth.setText(String.valueOf(Edit.getSpecific().roadWidth));
        idAngle.setText(String.valueOf(Edit.getSpecific().angle));
        idLoad.setText(String.valueOf(Edit.getSpecific().load));
        idFLoad.setText(String.valueOf(Edit.getSpecific().fLoad));
        pathShema = Edit.getSpecific().pathShema;
    }

    public BridgePasport makeBP () {
        String n = "";
        String b = "";
        String c = "";
        int l = 1;
        String loc = "";
        double d = 0;
        int i = 1950;
        String cp = "";
        int lt = 1951;
        try { n = idBridgeName.getText(); } catch (Exception ignored) {}
        try { b = idBarrier.getText(); } catch (Exception ignored) {}
        try { c = idCategory.getValue(); } catch (Exception ignored) {}
        try { l = idLines.getValue(); } catch (Exception ignored) {}
        try { loc = idLocality.getText(); } catch (Exception ignored) {}
        try { d = Double.parseDouble(idDistance.getText()); } catch (Exception ignored) {}
        try { i = idInput.getValue(); } catch (Exception ignored) {}
        try { cp = idCompany.getText(); } catch (Exception ignored) {}
        try { lt = idLifetime.getValue(); } catch (Exception ignored) {}
        return new BridgePasport(n, b, c, l, loc, d, i, cp, lt, pathPic);
    }

    public Specific makeSpecific () {
        String aa = ""; int bb = 0;
        int cc = 0;     int dd = 0;
        int ee = 0;     int ff = 0;
        double gg = 0;  int hh = 0;
        int ii = 0;
        try { aa = idLength.getValue(); } catch (Exception ignored) {}
        try { bb = Integer.parseInt(idRoadWidth.getText()); } catch (Exception ignored) {}
        try { cc = Integer.parseInt(idLeftWidth.getText()); } catch (Exception ignored) {}
        try { dd = Integer.parseInt(idRightWidth.getText()); } catch (Exception ignored) {}
        try { ee = idFence.getValue(); } catch (Exception ignored) {}
        try { ff = idWalkFence.getValue(); } catch (Exception ignored) {}
        try { gg = Double.parseDouble(idAngle.getText()); } catch (Exception ignored) {}
        try { hh = Integer.parseInt(idLoad.getText()); } catch (Exception ignored) {}
        try { ii = Integer.parseInt(idFLoad.getText()); } catch (Exception ignored) {}
        return new Specific(aa, bb, cc, dd, ee, ff, gg, hh, ii, pathShema);
    }



    public void sendBridge() {
        if (!P || !S) {
            MessageBox.show(createButton.getScene().getWindow(),
                    "Необхідно підтвердити паспортні та технічні дані ! ! !",
                    "Помилка", MessageBox.ICON_ERROR);
        } else {
            //Edit.bigBridge = new Bridge(makeBP(), makeSpecific(), makeDeck(),
                   // makeSuperStructure(), makeRS(), makeSupport());
            Edit.cBridge();
            Edit.idStatusString.setText("!: Об'єкт успішно створенно.");
            ((Stage)createButton.getScene().getWindow()).close();
        }
    }

    public void closeForm() {
        ((Stage)closeButton.getScene().getWindow()).close();
    }

    public void ChoosePic() throws MalformedURLException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Зображення");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png"));
            String userDirectoryStr = System.getProperty("user.home");
            File userDirictory = new File(userDirectoryStr);
            if (!userDirictory.canRead())
                userDirictory = new File("c:/");
            fileChooser.setInitialDirectory(userDirictory);
            File choosenFile = fileChooser.showOpenDialog(null);
            if (choosenFile != null)
                pathPic = choosenFile.getPath();
            else pathPic = null;
            String res = "file:/";
            for (int i = 0; i < pathPic.length(); i++) {
                if (pathPic.charAt(i) == 92) {
                    res += "/";
                } else res += pathPic.charAt(i);
            }
            pathPic = res;
            Image img = new Image(res);
            double width = img.getWidth();
            double height = img.getHeight();
            if ((width > 800) || (height > 300)) {
                MessageBox.show(idChooserPic.getScene().getWindow(),
                        "Зображення має некоректні розміри !",
                        "Помилка", MessageBox.ICON_ERROR);
            }
        } catch (Exception ignored) {}
    }

    public void ChoseeShema() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Схема");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png"));
            String userDiroctoryString = System.getProperty("user.home");
            File userDiroctory = new File(userDiroctoryString);
            if (!userDiroctory.canRead())
                userDiroctory = new File("c:/");
            fileChooser.setInitialDirectory(userDiroctory);
            File choosenFile = fileChooser.showOpenDialog(null);
            if (choosenFile != null) {
                pathShema = choosenFile.getPath();
            } else pathShema = null;
            String res = "file:/";
            for (int i = 0; i < pathShema.length(); i++) {
                if (pathShema.charAt(i) == 92) {
                    res += "/";
                } else res += pathShema.charAt(i);
            }
            pathShema = res;
            Image img = new Image(res);
            double width = img.getWidth();
            double height = img.getHeight();
            if ((width > 800) || (height > 300)) {
                MessageBox.show(idChooserShema.getScene().getWindow(),
                        "Зображення має некоректні розміри !",
                        "Помилка", MessageBox.ICON_ERROR);
            }
        } catch (Exception ignored) {}
    }

    public void BlockPasportData() {
        try {
            int I = idInput.getValue();
            int E = idLifetime.getValue();
            String B = idBridgeName.getText();
            if ((I < E) && (B.length() >= 1) && (!idCategory.getValue().equals(idCategory.getPromptText()))) {
                P = true;
                idCategory.setDisable(true);        idLines.setDisable(true);
                idInput.setDisable(true);           idLifetime.setDisable(true);
                idBridgeName.setEditable(false);
                idBarrier.setEditable(false);       idLocality.setEditable(false);
                idDistance.setEditable(false);      idCompany.setEditable(false);
                idChooserPic.setDisable(true);
            } else {
                MessageBox.show(idReadyPasport.getScene().getWindow(),
                        "Перевірте заповнення параметрів обов'язкових для введення",
                        "Помилка", MessageBox.ICON_ERROR);
            }
        } catch (Exception e) {
            MessageBox.show(idReadyPasport.getScene().getWindow(),
                    "Перевірте заповнення параметрів обов'язкових для введення",
                    "Помилка", MessageBox.ICON_ERROR);
        }
    }

    public void ReleasePasportData() {
        P = false;
        idCategory.setDisable(false); idLines.setDisable(false);
        idInput.setDisable(false);    idLifetime.setDisable(false);
        idBridgeName.setEditable(true);
        idBarrier.setEditable(true);  idLocality.setEditable(true);
        idDistance.setEditable(true); idCompany.setEditable(true);
        idChooserPic.setDisable(false);
    }

    public void BlockSpecification() {
        try {
            int b = Integer.parseInt(idLoad.getText());
            int bb = Integer.parseInt(idFLoad.getText());
            if ((b > 0) && (bb > 0) && (b >= bb) && (!idLength.getValue().equals(idLength.getPromptText()))) {
                S = true;
                idFence.setDisable(true);        idWalkFence.setDisable(true);
                idLength.setDisable(true);       idRoadWidth.setEditable(false);
                idLeftWidth.setEditable(false);  idRightWidth.setEditable(false);
                idLoad.setEditable(false);       idAngle.setEditable(false);
                idChooserShema.setDisable(true); idFLoad.setEditable(false);}
            else  MessageBox.show(idReadySpecification.getScene().getWindow(),
                    "Перевірте парвельність вводу обов'язковіх параметрів",
                    "Помилка", MessageBox.ICON_ERROR);
        } catch (Exception e) {
            MessageBox.show(idReadySpecification.getScene().getWindow(),
                    "Перевірте парвельність вводу обов'язковіх параметрів",
                    "Помилка", MessageBox.ICON_ERROR);
        }
    }

    public void ReleaseSpecification() {
        S = false;
        idFence.setDisable(false);     idWalkFence.setDisable(false);
        idLength.setDisable(false);    idRoadWidth.setEditable(true);
        idLeftWidth.setEditable(true); idRightWidth.setEditable(true);
        idLoad.setEditable(true);      idChooserShema.setDisable(false);
        idAngle.setEditable(true);     idFLoad.setEditable(true);
    }
}