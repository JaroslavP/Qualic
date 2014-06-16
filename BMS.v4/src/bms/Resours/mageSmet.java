package bms.Resours;

import bms.Bridge.SMETA;
import bms.Controller;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.ResourceBundle;

public class mageSmet implements Initializable {

    public Label idTotal;
    public Button idFindCost;
    public static Controller controller;
    public Button idAdd;
    public GridPane idGrid;
    public AnchorPane idAP;
    public ScrollPane idScroll;
    public Pane idPane;
    public Button idCloseSmeta;
    public Pane idP;
    int count = 0;
    ArrayList<SMETA> smetas;


    public void SmetaClose() {
        try {
            ((Stage) idCloseSmeta.getScene().getWindow()).close();
        } catch (Exception ignored) {

        }
    }

    public void findCost() {
        try {
            controller.bigBridge.smeta.clear();
            for (Node node : idGrid.getChildren()) {
                int i = Integer.parseInt(((TextField)(((HBox) node).getChildren().get(0))).getText());
                String el = ((TextField)(((HBox) node).getChildren().get(1))).getText();
                String n = ((TextField)(((HBox) node).getChildren().get(2))).getText();
                double d1 = 0;
                try {
                    d1 = Double.parseDouble(((TextField)(((HBox) node).getChildren().get(3))).getText());
                }catch (Exception ignored) {}
                double d2 = 0;
                try {
                    d2 = Double.parseDouble(((TextField)(((HBox) node).getChildren().get(4))).getText());
                }catch (Exception ignored) {}
                double d3 = 0;
                try {
                    d3 = Double.parseDouble(((TextField)(((HBox) node).getChildren().get(5))).getText());
                }catch (Exception ignored) {}
                double d4 = 0;
                try {
                    d4 = Double.parseDouble(((TextField)(((HBox) node).getChildren().get(6))).getText());
                }catch (Exception ignored) {}
                double d5 = d2 * d3;
                ((TextField) (((HBox) node).getChildren().get(7))).setText(String.valueOf(d5));
                double d6 = d1 * d4;
                ((TextField) (((HBox) node).getChildren().get(8))).setText(String.valueOf(d6));
                double d7 = d5 + d6;
                ((TextField) (((HBox) node).getChildren().get(9))).setText(String.valueOf(d7));
                controller.bigBridge.smeta.add(new SMETA(i, el, n, d1, d2, d3, d4, d5, d6, d7));
            }
            double sum = 0;
            for (SMETA smeta : controller.bigBridge.smeta) {
                sum += smeta.total;
            }
            idTotal.setText(String.valueOf((sum)));
        } catch (Exception ignored) {
            MessageBox.show(idCloseSmeta.getScene().getWindow(), "Помилка при розрахунку,\n перевірте правельність введених даних!", "Warning", MessageBox.ICON_WARNING);
        }
    }


    public void added() {
        idGrid.getRowConstraints().add(makeRow());
        idGrid.getColumnConstraints().add(makeCol());
        idGrid.add(makeRows(), 0, idGrid.getRowConstraints().size() - 1);
    }

    private RowConstraints makeRow() {
        RowConstraints row = new RowConstraints();
        row.setMaxHeight(50);
        return row;
    }

    private ColumnConstraints makeCol() {
        ColumnConstraints col = new ColumnConstraints();
        col.setMaxWidth(113);
        return col;
    }

    private HBox makeRows() {
        HBox hBox = new HBox();
        TextField f1 = new TextField(Integer.toString(count += 1));
        f1.setMinWidth(30);
        f1.setMinHeight(10);
        f1.setEditable(false);
        hBox.getChildren().add(f1);
        TextField f2 = new TextField();
        f2.setMinWidth(100);
        f2.setMinHeight(10);
        hBox.getChildren().add(f2);
        TextField f3 = new TextField();
        f3.setMinWidth(70);
        f3.setMinHeight(10);
        hBox.getChildren().add(f3);
        TextField f4 = new TextField();
        f4.setMinWidth(90);
        f4.setMinHeight(10);
        hBox.getChildren().add(f4);
        TextField f5 = new TextField();
        f5.setMinWidth(100);
        f5.setMinHeight(10);
        hBox.getChildren().add(f5);
        TextField f6 = new TextField();
        f6.setMinWidth(80);
        f6.setMinHeight(10);
        hBox.getChildren().add(f6);
        TextField f7 = new TextField();
        f7.setMinWidth(110);
        f7.setMinHeight(10);
        hBox.getChildren().add(f7);
        TextField f8 = new TextField();
        f8.setMinWidth(110);
        f8.setMinHeight(10);
        f8.setEditable(false);
        hBox.getChildren().add(f8);
        TextField f9 = new TextField();
        f9.setMinWidth(95);
        f9.setMinHeight(10);
        f9.setEditable(false);
        hBox.getChildren().add(f9);
        TextField f0 = new TextField();
        f0.setMinWidth(95);
        f0.setMinHeight(10);
        f0.setEditable(false);
        hBox.getChildren().add(f0);
        return hBox;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HBox hBox = new HBox();
        TextField f1 = new TextField("№");
        f1.setMinWidth(30);
        f1.setMinHeight(10);
        f1.setEditable(false);
        hBox.getChildren().add(f1);
        TextField f2 = new TextField("Елемент мосту");
        f2.setMinWidth(100);
        f2.setMinHeight(10);
        f2.setEditable(false);
        hBox.getChildren().add(f2);
        TextField f3 = new TextField("Матеріал");
        f3.setEditable(false);
        f3.setMinWidth(70);
        f3.setMinHeight(10);
        hBox.getChildren().add(f3);
        TextField f4 = new TextField("Одиниці (м^2)");
        f4.setMinWidth(90);
        f4.setMinHeight(10);
        f4.setEditable(false);
        hBox.getChildren().add(f4);
        TextField f5 = new TextField("Кількість (кг/шт)");
        f5.setMinWidth(100);
        f5.setMinHeight(10);
        f5.setEditable(false);
        hBox.getChildren().add(f5);
        TextField f6 = new TextField("Варітсть од.");
        f6.setMinWidth(80);
        f6.setMinHeight(10);
        f6.setEditable(false);
        hBox.getChildren().add(f6);
        TextField f7 = new TextField("Варітсть од. робіт");
        f7.setMinWidth(110);
        f7.setMinHeight(10);
        f7.setEditable(false);
        hBox.getChildren().add(f7);
        TextField f8 = new TextField("Сумма матеріалів");
        f8.setMinWidth(110);
        f8.setMinHeight(10);
        f8.setEditable(false);
        hBox.getChildren().add(f8);
        TextField f9 = new TextField("Сумма робіт");
        f9.setMinWidth(95);
        f9.setMinHeight(10);
        f9.setEditable(false);
        hBox.getChildren().add(f9);
        TextField f0 = new TextField("Загальна вартість");
        f0.setMinWidth(95);
        f0.setMinHeight(10);
        f0.setEditable(false);
        hBox.setMaxWidth(113);
        hBox.getChildren().add(f0);
        idP.getChildren().addAll(hBox);
        idGrid = new GridPane();
        idGrid.setVisible(true);
        idScroll = new ScrollPane();
        idScroll.setContent(idGrid);
        idScroll.setPrefSize(895,529);
        idPane.getChildren().addAll(idGrid, idScroll);
        if (Controller.categiryForGraf.smeta.size() != 0) {
            for (int i = 0; i < Controller.categiryForGraf.smeta.size() ; i++) {
                added();
            }
            int ccc = 0;
            for (Node node : idGrid.getChildren()) {
                ((TextField)(((HBox) node).getChildren().get(1))).setText(Controller.categiryForGraf.smeta.get(ccc).BridgeElement);
                ((TextField)(((HBox) node).getChildren().get(2))).setText(Controller.categiryForGraf.smeta.get(ccc).Name);
                ((TextField)(((HBox) node).getChildren().get(3))).setText(Double.toString(Controller.categiryForGraf.smeta.get(ccc).unit));
                ((TextField)(((HBox) node).getChildren().get(4))).setText(String.valueOf(Controller.categiryForGraf.smeta.get(ccc).number));
                ((TextField)(((HBox) node).getChildren().get(5))).setText(String.valueOf(Controller.categiryForGraf.smeta.get(ccc).numberCost));
                ((TextField)(((HBox) node).getChildren().get(6))).setText(String.valueOf(Controller.categiryForGraf.smeta.get(ccc).unitWork));
                ((TextField)(((HBox) node).getChildren().get(7))).setText(String.valueOf(Controller.categiryForGraf.smeta.get(ccc).sumNumbercost));
                ((TextField)(((HBox) node).getChildren().get(8))).setText(String.valueOf(Controller.categiryForGraf.smeta.get(ccc).sumUnitWork));
                ((TextField)(((HBox) node).getChildren().get(9))).setText(String.valueOf(Controller.categiryForGraf.smeta.get(ccc).total));
                ccc++;
            }
            double sum = 0;
            for (SMETA smeta : Controller.categiryForGraf.smeta) {
                sum += smeta.total;
            }
            idTotal.setText(String.valueOf((sum)));
        }
    }
}