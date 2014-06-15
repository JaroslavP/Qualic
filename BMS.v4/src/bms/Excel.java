package bms;

import bms.Bridge.*;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Excel {

    private Bridge excelBridge;
    private String xlsFile;
    public static Controller controller;

    public Excel() {
        try {
            xlsFile = exFile();
            excelBridge = Creation();
        } catch (IOException e) {
            System.out.println("F A I L");
        }
    }

    private Bridge Creation() throws IOException {
        try {
            InputStream in = new FileInputStream(xlsFile);
            HSSFWorkbook wb = new HSSFWorkbook(in);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;

            row = sheet.getRow(2);
            ArrayList<String> passport = new ArrayList<String>();
            for (int i = 0; i < 10; i++) {
                passport.add(String.valueOf(row.getCell(i)));
            }
            BridgePasport BP = makeBridgePasport(passport);

            row = sheet.getRow(5);
            ArrayList<String> specific = new ArrayList<String>();
            for (int i = 0; i < 10; i++) {
                specific.add(String.valueOf(row.getCell(i)));
            }
            Specific BS = makeSpecific(specific);

            ArrayList<String> deck = new ArrayList<String>();
            row = sheet.getRow(7);
            for (int i = 0; i < 4; i++)
                deck.add(String.valueOf(row.getCell(i)));
            row = sheet.getRow(8);
            for (int i = 0; i < 5; i++)
                deck.add(String.valueOf(row.getCell(i)));
            row = sheet.getRow(9);
            for (int i = 0; i < 5; i++)
                deck.add(String.valueOf(row.getCell(i)));
            row = sheet.getRow(10);
            for (int i = 0; i < 4; i++)
                deck.add(String.valueOf(row.getCell(i)));
            row = sheet.getRow(11);
            for (int i = 0; i < 4; i++)
                deck.add(String.valueOf(row.getCell(i)));
            Deck D = makeDeck(deck);

            row = sheet.getRow(13);
            ArrayList<String> ss = new ArrayList<String>();
            for (int i = 0; i < 8; i++) {
                ss.add(String.valueOf(row.getCell(i)));
            }
            SuperStructure SS = makeSuperStructure(ss);

            ArrayList<String> supp = new ArrayList<String>();
            row = sheet.getRow(15);
            for (int i = 0; i < 6; i++)
                supp.add(String.valueOf(row.getCell(i)));
            row = sheet.getRow(16);
            for (int i = 0; i < 7; i++)
                supp.add(String.valueOf(row.getCell(i)));
            row = sheet.getRow(17);
            for (int i = 0; i < 5; i++)
                supp.add(String.valueOf(row.getCell(i)));
            Support Supp = makeSupport(supp);

            row = sheet.getRow(19);
            ArrayList<String> rs = new ArrayList<String>();
            for (int i = 0; i < 4; i++) {
                rs.add(String.valueOf(row.getCell(i)));
            }
            RegulatoryStructure RS = makeRS(rs);

            return new Bridge(BP, BS, D, SS, RS, Supp);
        } catch (Exception ignored) { }
        return null;
    }

    public RegulatoryStructure makeRS(ArrayList<String> rs) {
        double a = 0; double b = 0;
        double c = 0; double d = 0;
        try {
            if (rs.get(0).equals("неперевищує 20%"))
                a = 1;
            else if (rs.get(0).equals("неперевищує 50%"))
                a = 2;
            else if (rs.get(0).equals("понад 50%"))
                a = 4.5;
        } catch (Exception ignored) {}
        try {
            if (rs.get(1).equals("1.5-2см, понад 12м"))
                b = 1;
            else if (rs.get(1).equals("3-5см, 5-12м"))
                b = 3.5;
            else if (rs.get(1).equals("3-5см, 2-5м"))
                b = 9.8;
            else if (rs.get(1).equals("понад 6см, 0.5-3м"))
                b = 20.4;
        } catch (Exception ignored) {}
        try {
            if (rs.get(2).equals("до 10%"))
                c = 1;
            else if (rs.get(2).equals("до 30%"))
                c = 3.5;
            else if (rs.get(2).equals("до 50%"))
                c = 9.8;
            else if (rs.get(2).equals("рух 5-10км/год"))
                c = 20.4;
        } catch (Exception ignored) {}
        try {
            if (rs.get(3).equals("до 5%"))
                d = 2.5;
            else if (rs.get(3).equals("5-10%"))
                d = 8.8;
            else if (rs.get(3).equals("понад 12%"))
                d = 19.4;
        } catch (Exception ignored) {}
        return new RegulatoryStructure(a, b, c, d);
    }

    public Support makeSupport(ArrayList<String> supp) {
        double hhh = 0;
        double bbb = 0;
        double fff = 0;
        //HEADER
        try {
            if (supp.get(0).equals("0.5-1.5мм, до 0.4м"))
                hhh += 2;
            else if (supp.get(0).equals("1-1.5мм, 0.4-0,8м"))
                hhh += 8.3;
            else if (supp.get(0).equals("більше 1.5мм, 0.8м"))
                hhh += 18.9;
        } catch (Exception ignored) {}
        try {
            if (supp.get(1).equals("до 0.3мм"))
                hhh += 2;
            else if (supp.get(1).equals("0.3-0.5мм"))
                hhh += 8.3;
            else if (supp.get(1).equals("0.5-1.5мм"))
                hhh += 18.9;
        } catch (Exception ignored) {}
        try {
            if (supp.get(2).equals("pH 11"))
                hhh += 1;
            else if (supp.get(2).equals("pH 10"))
                hhh += 2.5;
            else if (supp.get(2).equals("pH 9"))
                hhh += 4.5;
            else if (supp.get(2).equals("pH 8"))
                hhh += 10.8;
            else if (supp.get(2).equals("pH 7"))
                hhh += 21.4;
        } catch (Exception ignored) {}
        if (supp.get(3).equals("Так")) hhh += 1.5;
        if (supp.get(4).equals("Так")) hhh += 2;
        if (supp.get(5).equals("Так")) hhh += 2;
        // BODYSUPPORT
        if (supp.get(6).equals("Так")) bbb += 1;     if (supp.get(7).equals("Так")) bbb += 1.5;
        if (supp.get(8).equals("Так")) bbb += 1.5;   if (supp.get(9).equals("Так")) bbb += 10;
        if (supp.get(10).equals("Так")) bbb += 19;    if (supp.get(11).equals("Так")) bbb += 16;
        if (supp.get(12).equals("Так")) bbb += 16;
        // FOUNDATION
        if (supp.get(13).equals("Так")) fff += 4;     if (supp.get(14).equals("Так")) fff += 10;
        if (supp.get(15).equals("Так")) fff += 9.5;   if (supp.get(16).equals("Так")) fff += 9.5;
        if (supp.get(17).equals("Так")) fff += 32;
        return new Support(hhh/3.0, bbb/3.0, fff/3.0);
    }

    public SuperStructure makeSuperStructure(ArrayList<String> ss) {
        double a = 0;   double b = 0;
        double c = 0;   double d = 0;
        double e = 0;   double f = 0;
        double g = 0;   double h = 0;
        try {
            if (ss.get(0).equals("0.1-0.2мм"))
                a += 0.5;
            else if (ss.get(0).equals("0.2-0.3мм"))
                a += 1.25;
            else if (ss.get(0).equals("0.3-0.5мм"))
                a += 4.25;
        } catch (Exception ignored) {}
        try {
            if (ss.get(1).equals("до 0.3мм"))
                b += 0.75;
            else if (ss.get(1).equals("до 0.5мм"))
                b += 3.75;
            else if (ss.get(1).equals("до 0.7мм"))
                b += 9.75;
        } catch (Exception ignored) {}
        try {
            if (ss.get(2).equals("2%"))
                c += 0.75;
            else if (ss.get(2).equals("4%"))
                c += 3.75;
            else if (ss.get(2).equals("6%"))
                c += 9.75;
            else if (ss.get(2).equals("10%"))
                c += 19.35;
        } catch (Exception ignored) {}
        if (ss.get(3).equals("Так")) d = 0.5;
        if (ss.get(4).equals("Так")) e = 9.6;
        if (ss.get(5).equals("Так")) f = 0.75;
        if (ss.get(6).equals("Так")) g = 9.6;
        if (ss.get(7).equals("Так")) h = 6;
        return new SuperStructure(a,b,c,d,e,f,g,h);
    }

    public Deck makeDeck(ArrayList<String> deck) {
        double coat = 0;
        double drai = 0;
        double expJoins = 0;
        double fen = 0;
        double side = 0;
        try {
            if (deck.get(0).equals("до 3см"))
                coat += 0.43;
            else if (deck.get(0).equals("до 5см"))
                coat += 1.43;
            else if (deck.get(0).equals("до 8см"))
                coat += 3.8;
        } catch (Exception ignored) {}
        try {
            if (deck.get(1).equals("до 3см"))
                coat += 0.43;
            else if (deck.get(1).equals("до 5см"))
                coat += 1.43;
            else if (deck.get(1).equals("до 8см"))
                coat += 3.8;
        } catch (Exception ignored) {}
        try {
            if (deck.get(2).equals("до 1см, 5%"))
                coat += 0.43;
            else if (deck.get(2).equals("до 3см, 10%"))
                coat += 1.43;
            else if (deck.get(2).equals("до 5см, 25%"))
                coat += 3.8;
            else if (deck.get(2).equals("до 8-10см, 40-50%"))
                coat += 11.8;
        } catch (Exception ignored) {}
        try {
            if (deck.get(3).equals("Невеликі просадки"))
                coat += 1;
            else if (deck.get(3).equals("Провалювання"))
                coat += 9;
        } catch (Exception ignored) {}
        if (deck.get(4).equals("Так")) drai += 0.42;      if (deck.get(9).equals("Так")) expJoins += 1;
        if (deck.get(5).equals("Так")) drai += 1;         if (deck.get(10).equals("Так")) expJoins += 1;
        if (deck.get(6).equals("Так")) drai += 1;         if (deck.get(11).equals("Так")) expJoins += 1;
        if (deck.get(7).equals("Так")) drai += 2.37;      if (deck.get(12).equals("Так")) expJoins += 2.37;
        if (deck.get(8).equals("Так")) drai += 2.37;      if (deck.get(13).equals("Так")) expJoins += 8;
        if (deck.get(14).equals("Так")) fen += 0.42;        if (deck.get(18).equals("Так")) side += 0.42;
        if (deck.get(15).equals("Так")) fen += 0.42;        if (deck.get(19).equals("Так")) side += 1;
        if (deck.get(16).equals("Так")) fen += 1;           if (deck.get(20).equals("Так")) side += 2.37;
        if (deck.get(17).equals("Так")) fen += 2.37;        if (deck.get(21).equals("Так")) side += 8;
        return new Deck(coat, drai, expJoins, fen, side);
    }

    public Specific makeSpecific(ArrayList<String> specific) {
        String aa = ""; int bb = 0;
        int cc = 0;     int dd = 0;
        int ee = 0;     int ff = 0;
        double gg = 0;  int hh = 0;
        int ii = 0;     String jj = "";
        try { aa = specific.get(0); } catch (Exception ignored) {}
        try { bb = (int)(Double.parseDouble(specific.get(1))); } catch (Exception ignored) {}
        try { cc = (int)(Double.parseDouble(specific.get(2))); } catch (Exception ignored) {}
        try { dd = (int)(Double.parseDouble(specific.get(3))); } catch (Exception ignored) {}
        try { ee = (int)(Double.parseDouble(specific.get(4))); } catch (Exception ignored) {}
        try { ff = (int)(Double.parseDouble(specific.get(5))); } catch (Exception ignored) {}
        try { gg = Double.parseDouble(specific.get(6)); } catch (Exception ignored) {}
        try { hh = (int) Double.parseDouble(specific.get(7)); } catch (Exception ignored) {}
        try { ii = (int) Double.parseDouble(specific.get(8)); } catch (Exception ignored) {}
        try { jj = specific.get(9); } catch (Exception ignored) {}
        return new Specific(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj);
    }

    private BridgePasport makeBridgePasport(ArrayList<String> passport) {
        String n = "";
        String b = "";
        String c = "";
        int l = 1;
        String loc = "";
        double d = 0;
        int i = 1950;
        String cp = "";
        int lt = 1951;
        String p = "";
        try { n = passport.get(0); } catch (Exception ignored) {}
        try { b = passport.get(1); } catch (Exception ignored) {}
        try { c = passport.get(2); } catch (Exception ignored) {}
        try { l = (int) ((Double.parseDouble(passport.get(3)))%10); } catch (Exception ignored) {}
        try { loc = passport.get(4); } catch (Exception ignored) {}
        try { d = Double.parseDouble(passport.get(5)); } catch (Exception ignored) {}
        try { i = (int) (Double.parseDouble(passport.get(6))%10000); } catch (Exception ignored) {}
        try { cp = passport.get(7); } catch (Exception ignored) {}
        try { lt = (int) (Double.parseDouble(passport.get(8))%10000); } catch (Exception ignored) {}
        try { p = passport.get(9); } catch (Exception ignored) {}
        return new BridgePasport(n, b, c, l, loc, d, i, cp, lt, p);
    }

    public String exFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Завантаження з файлу");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MS Office Excel", "*.xls"));
            String userDiroctoryString = System.getProperty("user.home");
            File userDiroctory = new File(userDiroctoryString);
            if (!userDiroctory.canRead())
                userDiroctory = new File("c:/");
            fileChooser.setInitialDirectory(userDiroctory);
            File choosenFile = fileChooser.showOpenDialog(null);
            String pathPic;
            if (choosenFile != null)
                pathPic = choosenFile.getPath();
            else pathPic = null;
            return pathPic;
        } catch (Exception ignored) {

        }
        return null;
    }

    public Bridge getExcelBridge() {
        return excelBridge;
    }
}
