package bms;

import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class SaveExcel {

    Bridge BBB;

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

            for (int rowNum = 0; rowNum < 21; rowNum++){
                myRow = mySheet.createRow(rowNum);
                for (int cellNum = 0; cellNum < 10 ; cellNum++){
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

                out.close();
            }catch(Exception e){ e.printStackTrace();}
        } catch (Exception ignored) {
            System.out.println("K/O/");}
    }

    private String[][] preapreDataToWriteToExcel() {
        String[][] excelData = new String[21][10];

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

        excelData[3][0] = "Технічні дані";

        excelData[4][0] = "Довжина";
        excelData[4][1] = "Ширина проїжджої частини";
        excelData[4][2] = "Ширина лівого тротуару";
        excelData[4][3] = "Ширина правого тротуару";
        excelData[4][4] = "Огорожа";
        excelData[4][5] = "Перила";
        excelData[4][6] = "Кут нахилу";
        excelData[4][7] = "Вантажопідйомність";

        excelData[5][0] = BBB.getSpecific().length;
        excelData[5][1] = String.valueOf(BBB.getSpecific().roadWidth);
        excelData[5][2] = String.valueOf(BBB.getSpecific().leftWalk);
        excelData[5][3] = String.valueOf(BBB.getSpecific().rightWalk);
        excelData[5][4] = String.valueOf(BBB.getSpecific().fence);
        excelData[5][5] = String.valueOf(BBB.getSpecific().walkFence);
        excelData[5][6] = String.valueOf(BBB.getSpecific().angle);
        excelData[5][7] = String.valueOf(BBB.getSpecific().load);

        excelData[6][0] = "Експлуатаційні дані";

        excelData[7][0] = "Категорія";
        excelData[7][1] = "Оцінка зносу споруди";
        excelData[7][2] = "Зниження вантажопідйомності";
        excelData[7][3] = "Вантажопідйомність";
        excelData[7][4] = "Швидкість руху";
        excelData[7][5] = "Термін експлуатації";

        excelData[8][0] = String.valueOf(BBB.getRating().categoruStatus);
        excelData[8][1] = Math.rint(BBB.getRating().wear*100)/100 + "%";
        excelData[8][2] = Math.rint(BBB.getRating().capacity*100)/100 + "%";
        excelData[8][3] = String.valueOf(BBB.getSpecific().fLoad);
        excelData[8][4] = BBB.getRating().safety;
        excelData[8][5] = BBB.getRating().longevity;

        excelData[9][0] = "Оцінка мостового полотна " + Math.rint(BBB.getRating().rDeck*100)/100 + "%";

        excelData[10][0] = "Покриття";
        excelData[10][1] = "Система водовідведення";
        excelData[10][2] = "Деформаційні шви";
        excelData[10][3] = "Огорожі";
        excelData[10][4] = "Тротуари";

        excelData[11][0] = Math.rint(BBB.getDeck().coating*100)/100 + "%";
        excelData[11][1] = Math.rint(BBB.getDeck().drainage*100)/100 + "%";
        excelData[11][2] = Math.rint(BBB.getDeck().expensionJoins*100)/100 + "%";
        excelData[11][3] = Math.rint(BBB.getDeck().fence*100)/100 + "%";
        excelData[11][4] = Math.rint(BBB.getDeck().sidewalk*100)/100 + "%";

        excelData[12][0] = "Оцінка прогонових споруд " + Math.rint(BBB.getRating().rSuperStruct*100)/100 + "%";

        excelData[13][0] = "Тріщини";
        excelData[13][1] = "Силові тріщини";
        excelData[13][2] = "Корозія балок";
        excelData[13][3] = "Пошкодження антикорозійного покриття";
        excelData[13][4] = "Розірвані шви зварювання";
        excelData[13][5] = "Місцеві погнутості балок";
        excelData[13][6] = "Залишкові прогини балок";
        excelData[13][7] = "Тріщини в болтак.гайках";

        excelData[14][0] = Math.rint(BBB.getSuperStructure().crack*100)/100 + "%";
        excelData[14][1] = Math.rint(BBB.getSuperStructure().powerCrack*100)/100 + "%";
        excelData[14][2] = Math.rint(BBB.getSuperStructure().corrosion*100)/100 + "%";
        excelData[14][3] = Math.rint(BBB.getSuperStructure().damageRust*100)/100 + "%";
        excelData[14][5] = Math.rint(BBB.getSuperStructure().defBeam*100)/100 + "%";
        excelData[14][6] = Math.rint(BBB.getSuperStructure().resBeam*100)/100 + "%";
        excelData[14][7] = Math.rint(BBB.getSuperStructure().crackBolt*100)/100 + "%";

        excelData[15][0] = "Оцінка регуляційних споруд " + Math.rint(BBB.getRating().rRegulStruct*100)/100 + "%";

        excelData[16][0] = "Пошкоджень кріплень підходів";
        excelData[16][1] = "Колії на поверхні";
        excelData[16][2] = "Зниження швидкості";
        excelData[16][3] = "Втрати конуса";

        excelData[17][0] = Math.rint(BBB.getRegulatoryStructure().areaDamege*100)/100 + "%";
        excelData[17][1] = Math.rint(BBB.getRegulatoryStructure().track*100)/100 + "%";
        excelData[17][2] = Math.rint(BBB.getRegulatoryStructure().speedLimit*100)/100 + "%";
        excelData[17][3] = Math.rint(BBB.getRegulatoryStructure().cone*100)/100 + "%";

        excelData[18][0] = "Оцінка опори " + Math.rint(BBB.getRating().rSupport*100)/100 +"%";

        excelData[19][0] = "Пошкоджень кріплень підходів";
        excelData[19][1] = "Колії на поверхні";
        excelData[19][2] = "Зниження швидкості";

        excelData[20][0] = Math.rint(BBB.getSupport().header*100)/100 + "%";
        excelData[20][1] = Math.rint(BBB.getSupport().bodySupport*100)/100 + "%";
        excelData[20][2] = Math.rint(BBB.getSupport().foundation*100)/100 + "%";

        return excelData;
    }
}
