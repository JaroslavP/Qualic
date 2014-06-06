package bms;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNewObject implements Initializable{

    public Button createButton;
    public static Controller control;
    public Button closeButton;
    boolean P = false;
    boolean S = false;
    // PASSPORT
    public TextField idBridgeName;          public ComboBox<Integer> idInput;
    public TextField idBarrier;             public TextField idCompany;
    public ComboBox<String> idCategory;     public ComboBox<Integer> idLifetime;
    public ComboBox<Integer> idLines;       public ComboBox<Integer> idRepair;
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
    public ComboBox<String> idKoliynist;    public ComboBox<String> idNapluvy;
    public ComboBox<String> idDamage;       public ComboBox<String> idVizd;
    public CheckBox SV1;                    public CheckBox SV2;
    public CheckBox SV3;                    public CheckBox SV4;
    public CheckBox SV5;                    public CheckBox DH1;
    public CheckBox DH2;                    public CheckBox DH3;
    public CheckBox DH4;                    public CheckBox DH5;
    public CheckBox O1;                     public CheckBox O2;
    public CheckBox O3;                     public CheckBox O4;
    public CheckBox T1;                     public CheckBox T2;
    public CheckBox T3;                     public CheckBox T4;
    //SUPERSTRUCTURE
    public ComboBox<String> ss1;            public CheckBox ss4;
    public ComboBox<String> ss2;            public CheckBox ss5;
    public ComboBox<String> ss3;            public CheckBox ss6;
    public CheckBox ss7;                    public CheckBox ss8;
    // SUPPORT
    public ComboBox<String> Rg1;            public CheckBox Rg4;
    public ComboBox<String> Rg2;            public CheckBox Rg5;
    public ComboBox<String> Rg3;            public CheckBox Rg6;
    public CheckBox to1;                    public CheckBox to2;
    public CheckBox to3;                    public CheckBox to4;
    public CheckBox to5;                    public CheckBox to6;
    public CheckBox to7;                    public CheckBox f1;
    public CheckBox f2;                     public CheckBox f3;
    public CheckBox f4;                     public CheckBox f5;
    // REGULATORY STRUCTURE
    public ComboBox<String> RS1;            public ComboBox<String> RS2;
    public ComboBox<String> RS3;            public ComboBox<String> RS4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCategory.getItems().addAll("Магістраль", "Загального призначення", "Звичайні");
        idLines.getItems().addAll(1,2,3,4,5,6,7,8);
        List<Integer> years = new ArrayList<Integer>();
        List<Integer> yearss = new ArrayList<Integer>();
        for (int i = 1950; i <= 2014; i++) {
            years.add(i); }
        for (int i = 1951; i <= 2114; i++) {
            yearss.add(i); }
        ObservableList<Integer> integerObservableList = FXCollections.observableArrayList(years);
        ObservableList<Integer> iOL = FXCollections.observableArrayList(yearss);
        idInput.getItems().addAll(integerObservableList);
        idLifetime.getItems().addAll(iOL);
        idRepair.getItems().addAll(integerObservableList);
        idFence.getItems().addAll(0,1,2,3);
        idWalkFence.getItems().addAll(0,1,2,3,4);
        idKoliynist.getItems().addAll("до 3см", "до 5см", "до 8см");
        idNapluvy.getItems().addAll("до 3см", "до 5см", "до 8см");
        idDamage.getItems().addAll("до 1см, 5%", "до 3см, 10%", "до 5см, 25%", "до 8-10см, 40-50%");
        idVizd.getItems().addAll("Невеликі просадки", "Провалювання");
        RS1.getItems().addAll("неперевищує 20%", "неперевищує 50%", "понад 50%");
        RS2.getItems().addAll("1.5-2см, понад 12м", "3-5см, 5-12м", "3-5см, 2-5м", "понад 6см, 0.5-3м");
        RS3.getItems().addAll("до 10%", "до 30%", "до 50%", "рух 5-10км/год");
        RS4.getItems().addAll("до 5%", "5-10%", "понад 12%");
        Rg1.getItems().addAll("0.5-1.5мм, до 0.4м", "1-1.5мм, 0.4-0,8м", "більше 1.5мм, 0.8м");
        Rg2.getItems().addAll("до 0.3мм", "0.3-0.5мм", "0.5-1.5мм");
        Rg3.getItems().addAll("pH 11", "pH 10", "pH 9", "pH 8", "pH 7");
        ss1.getItems().addAll("0.1-0.2мм", "0.2-0.3мм", "0.3-0.5мм");
        ss2.getItems().addAll("до 0.3мм", "до 0.5мм", "до 0.7мм");
        ss3.getItems().addAll("2%", "4%", "6%", "10%");
        idLength.getItems().addAll("менше 21м", "22-28м","29-35м","36-42м","43-62м","63-83м","більше 84м");
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
        int r = 1950;
        String p = "";
        try { n = idBridgeName.getText(); } catch (Exception ignored) {}
        try { b = idBridgeName.getText(); } catch (Exception ignored) {}
        try { c = idCategory.getValue(); } catch (Exception ignored) {}
        try { l = idLines.getValue(); } catch (Exception ignored) {}
        try { loc = idLocality.getText(); } catch (Exception ignored) {}
        try { d = Double.parseDouble(idDistance.getText()); } catch (Exception ignored) {}
        try { i = idInput.getValue(); } catch (Exception ignored) {}
        try { cp = idCompany.getText(); } catch (Exception ignored) {}
        try { lt = idLifetime.getValue(); } catch (Exception ignored) {}
        try { r = idRepair.getValue(); } catch (Exception ignored) {}
        try { p = pathPic; } catch (Exception ignored) {}
        return new BridgePasport(n, b, c, l, loc, d, i, cp, lt, r , p);
    }

    public Specific makeSpecific () {
        String aa = ""; int bb = 0;
        int cc = 0;     int dd = 0;
        int ee = 0;     int ff = 0;
        double gg = 0;  int hh = 0;
        int ii = 0;     String jj = "";
        try { aa = idLength.getValue(); } catch (Exception ignored) {}
        try { bb = Integer.parseInt(idRoadWidth.getText()); } catch (Exception ignored) {}
        try { cc = Integer.parseInt(idLeftWidth.getText()); } catch (Exception ignored) {}
        try { dd = Integer.parseInt(idRightWidth.getText()); } catch (Exception ignored) {}
        try { ee = idFence.getValue(); } catch (Exception ignored) {}
        try { ff = idWalkFence.getValue(); } catch (Exception ignored) {}
        try { gg = Double.parseDouble(idAngle.getText()); } catch (Exception ignored) {}
        try { hh = Integer.parseInt(idLoad.getText()); } catch (Exception ignored) {}
        try { ii = Integer.parseInt(idFLoad.getText()); } catch (Exception ignored) {}
        try { jj = pathShema; } catch (Exception ignored) {}
        return new Specific(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj);
    }

    public Deck makeDeck () {
        double coat = 0;
        double drai = 0;
        double expJoins = 0;
        double fen = 0;
        double side = 0;
        try {
            if (idKoliynist.getValue().equals("до 3см"))
                coat += 0.43;
            else if (idKoliynist.getValue().equals("до 5см"))
                coat += 1.43;
            else if (idKoliynist.getValue().equals("до 8см"))
                coat += 3.8;
        } catch (Exception ignored) {}
        try {
            if (idNapluvy.getValue().equals("до 3см"))
                coat += 0.43;
            else if (idNapluvy.getValue().equals("до 5см"))
                coat += 1.43;
            else if (idNapluvy.getValue().equals("до 8см"))
                coat += 3.8;
        } catch (Exception ignored) {}
        try {
            if (idDamage.getValue().equals("до 1см, 5%"))
                coat += 0.43;
            else if (idDamage.getValue().equals("до 3см, 10%"))
                coat += 1.43;
            else if (idDamage.getValue().equals("до 5см, 25%"))
                coat += 3.8;
            else if (idDamage.getValue().equals("до 8-10см, 40-50%"))
                coat += 11.8;
        } catch (Exception ignored) {}
        try {
            if (idVizd.getValue().equals("Невеликі просадки"))
                coat += 1;
            else if (idVizd.getValue().equals("Провалювання"))
                coat += 9;
        } catch (Exception ignored) {}
        if (SV1.isSelected()) drai += 0.42;      if (DH1.isSelected()) expJoins += 1;
        if (SV2.isSelected()) drai += 1;         if (DH2.isSelected()) expJoins += 1;
        if (SV3.isSelected()) drai += 1;         if (DH3.isSelected()) expJoins += 1;
        if (SV4.isSelected()) drai += 2.37;      if (DH4.isSelected()) expJoins += 2.37;
        if (SV5.isSelected()) drai += 2.37;      if (DH5.isSelected()) expJoins += 8;
        if (O1.isSelected()) fen += 0.42;        if (T1.isSelected()) side += 0.42;
        if (O2.isSelected()) fen += 0.42;        if (T1.isSelected()) side += 1;
        if (O3.isSelected()) fen += 1;           if (T1.isSelected()) side += 2.37;
        if (O4.isSelected()) fen += 2.37;        if (T1.isSelected()) side += 8;
        return new Deck(coat, drai, expJoins, fen, side);
    }

    public SuperStructure makeSuperStructure () {
        double a = 0;   double b = 0;
        double c = 0;   double d = 0;
        double e = 0;   double f = 0;
        double g = 0;   double h = 0;
        try {
            if (ss1.getValue().equals("0.1-0.2мм"))
                a += 0.5;
            else if (ss1.getValue().equals("0.2-0.3мм"))
                a += 1.25;
            else if (ss1.getValue().equals("0.3-0.5мм"))
                a += 4.25;
        } catch (Exception ignored) {}
        try {
            if (ss2.getValue().equals("до 0.3мм"))
                b += 0.75;
            else if (ss2.getValue().equals("до 0.5мм"))
                b += 3.75;
            else if (ss2.getValue().equals("до 0.7мм"))
                b += 9.75;
        } catch (Exception ignored) {}
        try {
            if (ss3.getValue().equals("2%"))
                c += 0.75;
            else if (ss3.getValue().equals("4%"))
                c += 3.75;
            else if (ss3.getValue().equals("6%"))
                c += 9.75;
            else if (ss3.getValue().equals("10%"))
                c += 19.35;
        } catch (Exception ignored) {}
        if (ss4.isSelected()) d = 0.5;
        if (ss5.isSelected()) e = 9.6;
        if (ss6.isSelected()) f = 0.75;
        if (ss7.isSelected()) g = 9.6;
        if (ss8.isSelected()) h = 6;
        return new SuperStructure(a,b,c,d,e,f,g,h);
    }

    public Support makeSupport () {
        double hhh = 0;
        double bbb = 0;
        double fff = 0;
        //HEADER
        try {
            if (Rg1.getValue().equals("0.5-1.5мм, до 0.4м"))
                hhh += 2;
            else if (Rg1.getValue().equals("1-1.5мм, 0.4-0,8м"))
                hhh += 8.3;
            else if (Rg1.getValue().equals("більше 1.5мм, 0.8м"))
                hhh += 18.9;
        } catch (Exception ignored) {}
        try {
            if (Rg2.getValue().equals("до 0.3мм"))
                hhh += 2;
            else if (Rg2.getValue().equals("0.3-0.5мм"))
                hhh += 8.3;
            else if (Rg2.getValue().equals("0.5-1.5мм"))
                hhh += 18.9;
        } catch (Exception ignored) {}
        try {
            if (Rg3.getValue().equals("pH 11"))
                hhh += 1;
            else if (Rg3.getValue().equals("pH 10"))
                hhh += 2.5;
            else if (Rg3.getValue().equals("pH 9"))
                hhh += 4.5;
            else if (Rg3.getValue().equals("pH 8"))
                hhh += 10.8;
            else if (Rg3.getValue().equals("pH 7"))
                hhh += 21.4;
        } catch (Exception ignored) {}
        if (Rg4.isSelected()) hhh += 1.5;
        if (Rg5.isSelected()) hhh += 2;
        if (Rg6.isSelected()) hhh += 2;
        // BODYSUPPORT
        if (to1.isSelected()) bbb += 1;     if (to2.isSelected()) bbb += 1.5;
        if (to3.isSelected()) bbb += 1.5;   if (to4.isSelected()) bbb += 10;
        if (to5.isSelected()) bbb += 19;    if (to6.isSelected()) bbb += 16;
        if (to7.isSelected()) bbb += 16;
        // FOUNDATION
        if (f1.isSelected()) fff += 4;     if (f2.isSelected()) fff += 10;
        if (f3.isSelected()) fff += 9.5;   if (f4.isSelected()) fff += 9.5;
        if (f5.isSelected()) fff += 32;
        return new Support(hhh/3.0, bbb/3.0, fff/3.0);
    }

    public RegulatoryStructure makeRS () {
        double a = 0; double b = 0;
        double c = 0; double d = 0;
        try {
            if (RS1.getValue().equals("неперевищує 20%"))
                a = 1;
            else if (RS1.getValue().equals("неперевищує 50%"))
                a = 2;
            else if (RS1.getValue().equals("понад 50%"))
                a = 4.5;
        } catch (Exception ignored) {}
        try {
            if (RS2.getValue().equals("1.5-2см, понад 12м"))
                b = 1;
            else if (RS2.getValue().equals("3-5см, 5-12м"))
                b = 3.5;
            else if (RS2.getValue().equals("3-5см, 2-5м"))
                b = 9.8;
            else if (RS2.getValue().equals("понад 6см, 0.5-3м"))
                b = 20.4;
        } catch (Exception ignored) {}
        try {
            if (RS3.getValue().equals("до 10%"))
                c = 1;
            else if (RS3.getValue().equals("до 30%"))
                c = 3.5;
            else if (RS3.getValue().equals("до 50%"))
                c = 9.8;
            else if (RS3.getValue().equals("рух 5-10км/год"))
                c = 20.4;
        } catch (Exception ignored) {}
        try {
            if (RS4.getValue().equals("до 5%"))
                d = 2.5;
            else if (RS4.getValue().equals("5-10%"))
                d = 8.8;
            else if (RS4.getValue().equals("понад 12%"))
                d = 19.4;
        } catch (Exception ignored) {}
        return new RegulatoryStructure(a, b, c, d);
    }

    public void sendBridge() {
        if (!P || !S) {
            MessageBox.show(createButton.getScene().getWindow(),
                    "Необхідно підтвердити паспортні та технічні дані ! ! !",
                    "Помилка", MessageBox.ICON_ERROR);
        } else {
            control.bigBridge = new Bridge(makeBP(), makeSpecific(), makeDeck(),
                    makeSuperStructure(), makeRS(), makeSupport());
            control.cBridge();
            ((Stage)createButton.getScene().getWindow()).close();
        }
    }

    public void closeForm() {
        ((Stage)closeButton.getScene().getWindow()).close();
    }

    public void ChoosePic() {
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
    }

    public void ChoseeShema() {
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
                idRepair.setDisable(true);          idBridgeName.setEditable(false);
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
        idRepair.setDisable(false);   idBridgeName.setEditable(true);
        idBarrier.setEditable(true);  idLocality.setEditable(true);
        idDistance.setEditable(true); idCompany.setEditable(true);
        idChooserPic.setDisable(false);
    }

    public void BlockSpecification() {
        try {
            int b = Integer.parseInt(idLoad.getText());
            int bb = Integer.parseInt(idFLoad.getText());
            if ((b > 0) && (bb > 0) && (b > bb) && (!idLength.getValue().equals(idLength.getPromptText()))) {
                S = true;
                idFence.setDisable(true);       idWalkFence.setDisable(true);
                idLength.setDisable(true);    idRoadWidth.setEditable(false);
                idLeftWidth.setEditable(false); idRightWidth.setEditable(false);
                idLoad.setEditable(false);      idAngle.setEditable(false);
                idChooserShema.setDisable(true);}
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
        idAngle.setEditable(true);
    }

    public void blockDeck() {
        idKoliynist.setDisable(true);   idNapluvy.setDisable(true);
        idDamage.setDisable(true);      idVizd.setDisable(true);
        SV1.setDisable(true);    SV2.setDisable(true);
        SV3.setDisable(true);    SV4.setDisable(true);
        SV5.setDisable(true);    DH1.setDisable(true);
        DH2.setDisable(true);    DH3.setDisable(true);
        DH4.setDisable(true);    DH5.setDisable(true);
        O1.setDisable(true);     O2.setDisable(true);
        O3.setDisable(true);     O4.setDisable(true);
        T1.setDisable(true);     T2.setDisable(true);
        T3.setDisable(true);     T4.setDisable(true);
    }

    public void ReleaseDeck() {
        idKoliynist.setDisable(false);   idNapluvy.setDisable(false);
        idDamage.setDisable(false);      idVizd.setDisable(false);
        SV1.setDisable(false);    SV2.setDisable(false);
        SV3.setDisable(false);    SV4.setDisable(false);
        SV5.setDisable(false);    DH1.setDisable(false);
        DH2.setDisable(false);    DH3.setDisable(false);
        DH4.setDisable(false);    DH5.setDisable(false);
        O1.setDisable(false);     O2.setDisable(false);
        O3.setDisable(false);     O4.setDisable(false);
        T1.setDisable(false);     T2.setDisable(false);
        T3.setDisable(false);     T4.setDisable(false);
    }

    public void blockSuper() {
        ss1.setDisable(true);   ss2.setDisable(true);
        ss3.setDisable(true);   ss4.setDisable(true);
        ss5.setDisable(true);   ss6.setDisable(true);
        ss7.setDisable(true);   ss8.setDisable(true);
    }

    public void ReleaseSuper() {
        ss1.setDisable(false);   ss2.setDisable(false);
        ss3.setDisable(false);   ss4.setDisable(false);
        ss5.setDisable(false);   ss6.setDisable(false);
        ss7.setDisable(false);   ss8.setDisable(false);
    }

    public void blockSupport() {
        Rg1.setDisable(true);   Rg4.setDisable(true);
        Rg2.setDisable(true);   Rg5.setDisable(true);
        Rg3.setDisable(true);   Rg6.setDisable(true);
        to1.setDisable(true);   to2.setDisable(true);
        to3.setDisable(true);   to4.setDisable(true);
        to5.setDisable(true);   to6.setDisable(true);
        to7.setDisable(true);   f1.setDisable(true);
        f2.setDisable(true);    f3.setDisable(true);
        f4.setDisable(true);    f5.setDisable(true);
    }

    public void ReleaseSupport() {
        Rg1.setDisable(false);   Rg4.setDisable(false);
        Rg2.setDisable(false);   Rg5.setDisable(false);
        Rg3.setDisable(false);   Rg6.setDisable(false);
        to1.setDisable(false);   to2.setDisable(false);
        to3.setDisable(false);   to4.setDisable(false);
        to5.setDisable(false);   to6.setDisable(false);
        to7.setDisable(false);   f1.setDisable(false);
        f2.setDisable(false);    f3.setDisable(false);
        f4.setDisable(false);    f5.setDisable(false);
    }

    public void blockRegulatoryStructure() {
        RS1.setDisable(true);   RS2.setDisable(true);
        RS3.setDisable(true);   RS4.setDisable(true);
    }

    public void ReleaseRegulatorystructure() {
        RS1.setDisable(false);   RS2.setDisable(false);
        RS3.setDisable(false);   RS4.setDisable(false);
    }
}
