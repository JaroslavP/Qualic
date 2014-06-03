package bms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNewObject implements Initializable{
    // PASSPORT
    public ComboBox<String> idCategory;
    public ComboBox<Integer> idLines;
    public ComboBox<Integer> idInput;
    public ComboBox<Integer> idLifetime;
    public ComboBox<Integer> idRepair;
    public TextField idBridgeName;
    public TextField idBarrier;
    public TextField idLocality;
    public TextField idDistance;
    public TextField idCompany;
    public Button idChooserPic;
    public Label idPic;
    // SPECIFICATION
    public TextField idLenght;
    public TextField idRoadWidth;
    public TextField idLeftWidth;
    public TextField idRightWidth;
    public ComboBox<Integer> idFence;
    public ComboBox<Integer> idWalkFence;
    public TextField idLoad;
    public Button idChooserShema;
    public Label idShema;

    public void BlockPasportData() {
        idCategory.setDisable(true);   idLines.setDisable(true);
        idInput.setDisable(true);      idLifetime.setDisable(true);
        idRepair.setDisable(true);     idBridgeName.setEditable(false);
        idBarrier.setEditable(false);  idLocality.setEditable(false);
        idDistance.setEditable(false); idCompany.setEditable(false);
        idChooserPic.setDisable(true);
    }

    public void ReleasePasportData() {
        idCategory.setDisable(false); idLines.setDisable(false);
        idInput.setDisable(false);    idLifetime.setDisable(false);
        idRepair.setDisable(false);   idBridgeName.setEditable(true);
        idBarrier.setEditable(true);  idLocality.setEditable(true);
        idDistance.setEditable(true); idCompany.setEditable(true);
        idChooserPic.setDisable(false);
    }

    public void BlockSpecification() {
        idFence.setDisable(true);       idWalkFence.setDisable(true);
        idLines.setEditable(false);     idRoadWidth.setEditable(false);
        idLeftWidth.setEditable(false); idRightWidth.setEditable(false);
        idLoad.setEditable(false);      idChooserShema.setDisable(true);
    }

    public void ReleaseSpecification() {
        idFence.setDisable(false);     idWalkFence.setDisable(false);
        idLines.setEditable(true);     idRoadWidth.setEditable(true);
        idLeftWidth.setEditable(true); idRightWidth.setEditable(true);
        idLoad.setEditable(true);      idChooserShema.setDisable(false);
    }

    public void ChoseeShema(ActionEvent actionEvent) {
    }

    public void ChoosePic(ActionEvent actionEvent) {
    }

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
    }
}
