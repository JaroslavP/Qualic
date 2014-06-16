package bms.Resours;

import bms.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Graf implements Initializable {

    List<Double> saveStage = new ArrayList<Double>();
    public Button idRepair;
    public Button idGrafClose;
    final NumberAxis xAxis = new NumberAxis(2014, Controller.categiryForGraf.getBridgePasport().lifetime, 5);
    final NumberAxis yAxis = new NumberAxis(0.95,1,0.01);
    LineChart<Number, Number> Graph = new LineChart<Number, Number>(xAxis,yAxis);
    public AnchorPane idGrafPane;
    public static int category;
    XYChart.Series series;

    public void GrafClose() {
        ((Stage) idGrafClose.getScene().getWindow()).close();
        Controller.G = Graph;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Controller.categiryForGraf.getRating().categoruStatus == 1)
            idRepair.setDisable(true);
        saveStage.add(0.75); saveStage.add(0.8525);
        saveStage.add(1.2597); saveStage.add(1.6657);
        saveStage.add(2.0987);

        category = Controller.categiryForGraf.getRating().categoruStatus - 1;
        double chAlfa = saveStage.get(category);
        double zAlfa = (2014 - Controller.categiryForGraf.getBridgePasport().input);
        double Alfa = chAlfa / zAlfa;

        double chendAlfa = saveStage.get(4);
        double zendAlfa = (Controller.categiryForGraf.getBridgePasport().lifetime - Controller.categiryForGraf.getBridgePasport().input);
        double endAlfa = chendAlfa / zendAlfa;

        int beginYear = 2014;
        int endYear = Controller.categiryForGraf.getBridgePasport().lifetime;
        double step = (endAlfa - Alfa)/(endYear - beginYear);

        xAxis.setLabel("Роки");
        yAxis.setLabel("Надійність");

        series = new XYChart.Series();
        series.setName("Залишковий ресурс " + (category+1));

        Graph.getData().clear();
        Graph.setCreateSymbols(false);
        Graph.setAlternativeRowFillVisible(false);
        Graph.setAnimated(true);
        Graph.setTitle("Залишковий ресурс моста " + Controller.categiryForGraf.getBridgePasport().name);

        int i = 2014;
        double P = 1;
        while (P >= 0.9584) {
            int t = i - Controller.categiryForGraf.getBridgePasport().input;
            P = 1 - 0.008333 * Math.pow((Alfa * t), 5) * Math.pow(Math.E, ((-1) * Alfa * t));
            series.getData().add(new XYChart.Data(i, P));
            Alfa += step;
            i++;
        }
        Graph.getData().add(series);
        Graph.setLayoutY(29);
        Graph.setPrefSize(735,400);
        idGrafPane.getChildren().addAll(Graph);
    }

    public void Repair() {
        if (category == 0) {
            idRepair.setDisable(true);
            MessageBox.show(idRepair.getScene().getWindow(), "Гфік відновлення до І експлуатаційного стану уже побудовано!",
                    "Попередження", MessageBox.ICON_WARNING);
        }
        else {
            category -= 1;
            double chAlfa = saveStage.get(category);
            double zAlfa = (2014 - Controller.categiryForGraf.getBridgePasport().input);
            double Alfa = chAlfa / zAlfa;

            double chendAlfa = saveStage.get(4);
            double zendAlfa = (Controller.categiryForGraf.getBridgePasport().lifetime - Controller.categiryForGraf.getBridgePasport().input);
            double endAlfa = chendAlfa / zendAlfa;

            int beginYear = 2014;
            int endYear = Controller.categiryForGraf.getBridgePasport().lifetime;
            double step = (endAlfa - Alfa)/(endYear - beginYear);

            series = new XYChart.Series();
            series.setName("Залишковий ресурс " + (category+1));

            int i = 2014;
            double P = 1;
            while (P >= 0.9584) {
                int t = i - Controller.categiryForGraf.getBridgePasport().input;
                P = 1 - 0.008333 * Math.pow((Alfa * t), 5) * Math.pow(Math.E, ((-1) * Alfa * t));
                series.getData().add(new XYChart.Data(i, P));
                Alfa += step;
                i++;
            }
            Graph.setPrefSize(735,400);
            Graph.getData().add(series);
        }
    }
}