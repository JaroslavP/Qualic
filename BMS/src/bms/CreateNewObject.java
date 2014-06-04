package bms;

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
    public Button closeButton;
    boolean P = false;
    boolean S = false;
    // PASSPORT
    public ComboBox<String> idCategory;     public TextField idBridgeName;
    public ComboBox<Integer> idLines;       public TextField idBarrier;
    public ComboBox<Integer> idInput;       public TextField idLocality;
    public ComboBox<Integer> idLifetime;    public TextField idDistance;
    public ComboBox<Integer> idRepair;      public TextField idCompany;
    public String pathPic;                  public Button idReadyPasport;
    public Button idChooserPic;
    // SPECIFICATION
    public TextField idLenght;              public TextField idAngle;
    public TextField idRoadWidth;           public TextField idLoad;
    public TextField idLeftWidth;           public TextField idFLoad;
    public TextField idRightWidth;          public Button idChooserShema;
    public ComboBox<Integer> idFence;       public String pathShema;
    public ComboBox<Integer> idWalkFence;   public Button idReadySpecification;
    // DECK
    public ComboBox<String> idKoliynist;
    public ComboBox<String> idNapluvy;
    public ComboBox<String> idDamage;
    public ComboBox<String> idVizd;
    public CheckBox SV1;    public CheckBox SV2;
    public CheckBox SV3;    public CheckBox SV4;
    public CheckBox SV5;    public CheckBox DH1;
    public CheckBox DH2;    public CheckBox DH3;
    public CheckBox DH4;    public CheckBox DH5;
    public CheckBox O1;     public CheckBox O2;
    public CheckBox O3;     public CheckBox O4;
    public CheckBox T1;     public CheckBox T2;
    public CheckBox T3;     public CheckBox T4;
    //SUPERSTRUCTURE
    public ComboBox<String> ss1;    public CheckBox ss4;
    public ComboBox<String> ss2;    public CheckBox ss5;
    public ComboBox<String> ss3;    public CheckBox ss6;
    public CheckBox ss7;            public CheckBox ss8;
    public CheckBox ss9;
    public ComboBox<String> RS4;
    // SUPPORT
    public ComboBox<String> Rg1;    public CheckBox Rg4;
    public ComboBox<String> Rg2;    public CheckBox Rg5;
    public ComboBox<String> Rg3;    public CheckBox Rg6;
    public CheckBox to1;            public CheckBox to2;
    public CheckBox to3;            public CheckBox to4;
    public CheckBox to5;            public CheckBox to6;
    public CheckBox to7;            public CheckBox f1;
    public CheckBox f2;             public CheckBox f3;
    public CheckBox f4;             public CheckBox f5;
    // REGULATORY STRUCTURE
    public ComboBox<String> RS1;
    public ComboBox<String> RS2;
    public ComboBox<String> RS3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCategory.getItems().addAll("Магістраль", "Загального призначення", "Звичайні");
        idLines.getItems().addAll(1,2,3,4,5,6,7,8);
        List<Integer> years = new ArrayList<Integer>();
        for (int i = 1950; i <= 2014; i++) {
            years.add(i);
        }
        ObservableList<Integer> integerObservableList = FXCollections.observableArrayList(years);
        idInput.getItems().addAll(integerObservableList);
        idLifetime.getItems().addAll(integerObservableList);
        idRepair.getItems().addAll(integerObservableList);
        idFence.getItems().addAll(0,1,2,3);
        idWalkFence.getItems().addAll(0,1,2,3,4);
        idKoliynist.getItems().addAll("до 3см", "до 5см", "до 8см");
        idNapluvy.getItems().addAll("до 3см", "до 5см", "до 8см");
        idDamage.getItems().addAll("до 1см, 5%", "до 3см, 10%", "до 5см, 25%", "до 8-10см, 40-50%");
        idVizd.getItems().addAll("Невеликі просадки", "Провалювання");
        RS1.getItems().addAll("непевищує 20%", "непевищує 50%", "понад 50%");
        RS2.getItems().addAll("1.5-2см, понад 12м", "3-5см, 5-12м", "3-5см, 2-5м", "понад 6см, 0.5-3м");
        RS3.getItems().addAll("на 10%", "на 30%", "на 50%", "рух 5-10км/год");
        RS4.getItems().addAll("до 5%", "5-10%", "понад 12%");
        Rg1.getItems().addAll("0.5-1.5мм, до 0.4м", "1-1.5мм, 0.4-0,8м", "більше 1.5мм, 0.8м");
        Rg2.getItems().addAll("до 0.3мм", "0.3-0.5мм", "0.5-1.5");
        Rg3.getItems().addAll("pH 11", "pH 10", "pH 9", "pH 8", "pH 7");
        ss1.getItems().addAll("0.1-0.2мм", "0.2-0.3мм", "0.3-0.5мм");
        ss2.getItems().addAll("до 0.3мм", "до 0.5мм", "0.7мм");
        ss3.getItems().addAll("2%", "4%", "6%", "10%");

    }

    public void createBridge() {
        if (!P && !S) {
            MessageBox.show(createButton.getScene().getWindow(),
                    "Необхідно підтвердити паспортні та технічні дані ! ! !",
                    "Помилка", MessageBox.ICON_ERROR);
        } else {
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
            String C = idCategory.getValue();
            if ((I < E) && (B.length() >= 1) && (C.length() > 3) ) {
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
            if ((b > 0) && (bb > 0 ) && (b > bb)) {
                S = true;
                idFence.setDisable(true);       idWalkFence.setDisable(true);
                idLenght.setEditable(false);    idRoadWidth.setEditable(false);
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
        idLenght.setEditable(true);    idRoadWidth.setEditable(true);
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
        ss9.setDisable(true);
    }

    public void ReleaseSuper() {
        ss1.setDisable(false);   ss2.setDisable(false);
        ss3.setDisable(false);   ss4.setDisable(false);
        ss5.setDisable(false);   ss6.setDisable(false);
        ss7.setDisable(false);   ss8.setDisable(false);
        ss9.setDisable(false);
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
