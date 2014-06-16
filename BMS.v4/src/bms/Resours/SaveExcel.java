package bms.Resours;

import bms.Bridge.Bridge;
import bms.Bridge.SMETA;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class SaveExcel {

    public static Label lll;
    private Bridge BBB;
    private int count;

    public SaveExcel (Bridge bbb) {
        BBB = bbb;
        save();
    }

    private void save() {
        try {
            String [][] excelData = preapreDataToWriteToExcel();
            HSSFWorkbook myWorkBook = new HSSFWorkbook();
            HSSFSheet mySheet = myWorkBook.createSheet();
            HSSFRow myRow = null;
            HSSFCell myCell = null;

            for (int rowNum = 0; rowNum < count+1; rowNum++){
                myRow = mySheet.createRow(rowNum);
                for (int cellNum = 0; cellNum < 11 ; cellNum++){
                    myCell = myRow.createCell(cellNum);
                    myCell.setCellValue(excelData[rowNum][cellNum]);
                }
            }
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Збереження даних");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MS Office Excel", "*.xls"));
                String userDiroctoryString = System.getProperty("user.home");
                File userDiroctory = new File(userDiroctoryString);
                if (!userDiroctory.canRead())
                    userDiroctory = new File("c:/");
                fileChooser.setInitialDirectory(userDiroctory);
                File choosenFile = fileChooser.showSaveDialog(null);
                FileOutputStream out = new FileOutputStream(choosenFile.getAbsolutePath());
                myWorkBook.write(out);
                lll.setText("Дані збережено");
                out.close();
            }catch(Exception e){ e.printStackTrace();}
        } catch (Exception ignored) {
            System.out.println("K/O/");}
    }

    private String[][] preapreDataToWriteToExcel() {
        String[][] excelData = new String[1000][11];

        excelData[0][0] = "Паспорті дані";

        excelData[1][0] = "Назва";
        excelData[1][1] = "Перешкода";
        excelData[1][2] = "Категорія";
        excelData[1][3] = "К-ть смуг руху";
        excelData[1][4] = "Населенний пунк";
        excelData[1][5] = "Відстань";
        excelData[1][6] = "Рік вводу";
        excelData[1][7] = "Буд. компанія";
        excelData[1][8] = "Закінчення експлуатації";
        excelData[1][9] = "Останній огляд";

        excelData[2][0] = BBB.getBridgePasport().name;
        excelData[2][1] = BBB.getBridgePasport().barrier;
        excelData[2][2] = BBB.getBridgePasport().Category;
        excelData[2][3] = String.valueOf(BBB.getBridgePasport().lines);
        excelData[2][4] = BBB.getBridgePasport().locality;
        excelData[2][5] = String.valueOf(BBB.getBridgePasport().distance);
        excelData[2][6] = String.valueOf(BBB.getBridgePasport().input);
        excelData[2][7] = BBB.getBridgePasport().company;
        excelData[2][8] = String.valueOf(BBB.getBridgePasport().lifetime);
        excelData[2][9] = "2014";

        excelData[3][0] = "";
        excelData[4][0] = "Технічні дані";

        excelData[5][0] = "Довжина";
        excelData[5][1] = "Ширина проїжджої частини";
        excelData[5][2] = "Ширина лівого тротуару";
        excelData[5][3] = "Ширина правого тротуару";
        excelData[5][4] = "Огорожа";
        excelData[5][5] = "Перила";
        excelData[5][6] = "Кут нахилу";
        excelData[5][7] = "Вантажопідйомність";

        excelData[6][0] = BBB.getSpecific().length;
        excelData[6][1] = String.valueOf(BBB.getSpecific().roadWidth);
        excelData[6][2] = String.valueOf(BBB.getSpecific().leftWalk);
        excelData[6][3] = String.valueOf(BBB.getSpecific().rightWalk);
        excelData[6][4] = String.valueOf(BBB.getSpecific().fence);
        excelData[6][5] = String.valueOf(BBB.getSpecific().walkFence);
        excelData[6][6] = String.valueOf(BBB.getSpecific().angle);
        excelData[6][7] = String.valueOf(BBB.getSpecific().load);

        excelData[7][0] = "";
        excelData[8][0] = "Експлуатаційні дані";

        excelData[9][0] = "Категорія";
        excelData[9][1] = "Оцінка зносу споруди";
        excelData[9][2] = "Зниження вантажопідйомності";
        excelData[9][3] = "Вантажопідйомність";
        excelData[9][4] = "Швидкість руху";
        excelData[9][5] = "Термін експлуатації";

        excelData[10][0] = String.valueOf(BBB.getRating().categoruStatus);
        excelData[10][1] = Math.rint(BBB.getRating().wear * 100) / 100 + "%";
        excelData[10][2] = Math.rint(BBB.getRating().capacity * 100) / 100 + "%";
        excelData[10][3] = String.valueOf(BBB.getSpecific().fLoad);
        excelData[10][4] = BBB.getRating().safety;
        excelData[10][5] = BBB.getRating().longevity;

        excelData[11][0] = "";
        excelData[12][0] = "Оцінка мостового полотна " + Math.rint(BBB.getRating().rDeck * 100) / 100 + "%";

        excelData[13][0] = "Покриття";
        excelData[13][1] = "Система водовідведення";
        excelData[13][2] = "Деформаційні шви";
        excelData[13][3] = "Огорожі";
        excelData[13][4] = "Тротуари";

        excelData[14][0] = Math.rint(BBB.getDeck().coating * 100) / 100 + "%";
        excelData[14][1] = Math.rint(BBB.getDeck().drainage * 100) / 100 + "%";
        excelData[14][2] = Math.rint(BBB.getDeck().expensionJoins * 100) / 100 + "%";
        excelData[14][3] = Math.rint(BBB.getDeck().fence * 100) / 100 + "%";
        excelData[14][4] = Math.rint(BBB.getDeck().sidewalk * 100) / 100 + "%";

        excelData[15][0] = "";
        excelData[16][0] = "Оцінка прогонових споруд " + Math.rint(BBB.getRating().rSuperStruct * 100) / 100 + "%";

        excelData[17][0] = "Тріщини";
        excelData[17][1] = "Силові тріщини";
        excelData[17][2] = "Корозія балок";
        excelData[17][3] = "Пошкодження антикорозійного покриття";
        excelData[17][4] = "Розірвані шви зварювання";
        excelData[17][5] = "Місцеві погнутості балок";
        excelData[17][6] = "Залишкові прогини балок";
        excelData[17][7] = "Тріщини в болтак.гайках";

        excelData[18][0] = Math.rint(BBB.getSuperStructure().crack * 100) / 100 + "%";
        excelData[18][1] = Math.rint(BBB.getSuperStructure().powerCrack * 100) / 100 + "%";
        excelData[18][2] = Math.rint(BBB.getSuperStructure().corrosion * 100) / 100 + "%";
        excelData[18][3] = Math.rint(BBB.getSuperStructure().damageRust * 100) / 100 + "%";
        excelData[18][5] = Math.rint(BBB.getSuperStructure().defBeam * 100) / 100 + "%";
        excelData[18][6] = Math.rint(BBB.getSuperStructure().resBeam * 100) / 100 + "%";
        excelData[18][7] = Math.rint(BBB.getSuperStructure().crackBolt * 100) / 100 + "%";

        excelData[19][0] = "";
        excelData[20][0] = "Оцінка регуляційних споруд " + Math.rint(BBB.getRating().rRegulStruct * 100) / 100 + "%";

        excelData[21][0] = "Пошкоджень кріплень підходів";
        excelData[21][1] = "Колії на поверхні";
        excelData[21][2] = "Зниження швидкості";
        excelData[21][3] = "Втрати конуса";

        excelData[22][0] = Math.rint(BBB.getRegulatoryStructure().areaDamege * 100) / 100 + "%";
        excelData[22][1] = Math.rint(BBB.getRegulatoryStructure().track * 100) / 100 + "%";
        excelData[22][2] = Math.rint(BBB.getRegulatoryStructure().speedLimit * 100) / 100 + "%";
        excelData[22][3] = Math.rint(BBB.getRegulatoryStructure().cone * 100) / 100 + "%";

        excelData[23][0] = "";
        excelData[24][0] = "Оцінка опори " + Math.rint(BBB.getRating().rSupport * 100) / 100 + "%";

        excelData[25][0] = "Пошкоджень кріплень підходів";
        excelData[25][1] = "Колії на поверхні";
        excelData[25][2] = "Зниження швидкості";

        excelData[26][0] = Math.rint(BBB.getSupport().header * 100) / 100 + "%";
        excelData[26][1] = Math.rint(BBB.getSupport().bodySupport * 100) / 100 + "%";
        excelData[26][2] = Math.rint(BBB.getSupport().foundation * 100) / 100 + "%";

        excelData[27][0] = "";
        excelData[28][0] = "СМЕТА";

        excelData[29][0] = "№";
        excelData[29][1] = "Елемент мосту";
        excelData[29][2] = "Матеріал";
        excelData[29][3] = "Одиниці (м^2)";
        excelData[29][4] = "К-ть (кг/шт)";
        excelData[29][5] = "Вартість од.";
        excelData[29][6] = "Вартість од. робіт";
        excelData[29][7] = "Сумма матеріалів";
        excelData[29][8] = "Сумма робіт";
        excelData[29][9] = "Загальна";

        count = 30;
        double Total = 0;
        for (SMETA smeta : BBB.smeta) {
            excelData[count][0] = String.valueOf(smeta.Num);
            excelData[count][1] = String.valueOf(smeta.BridgeElement);
            excelData[count][2] = String.valueOf(smeta.Name);
            excelData[count][3] = String.valueOf(smeta.unit);
            excelData[count][4] = String.valueOf(smeta.number);
            excelData[count][5] = String.valueOf(smeta.numberCost);
            excelData[count][6] = String.valueOf(smeta.unitWork);
            excelData[count][7] = String.valueOf(smeta.sumNumbercost);
            excelData[count][8] = String.valueOf(smeta.sumUnitWork);
            excelData[count][9] = String.valueOf(smeta.total);
            Total += smeta.total;
            count++;
        }

        count++;
        excelData[count][0] = "Загальна " + Total;

        return excelData;
    }
}