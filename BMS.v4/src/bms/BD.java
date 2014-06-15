package bms;

import bms.Bridge.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BD {

    public static Controller controller;
    public static Label lll;

    public Statement statement;
    public ResultSet rs;
    PreparedStatement pst;

    public String res;
    public ArrayList<Integer> ids1 = new ArrayList<Integer>();
    public ArrayList<String> info1 = new ArrayList<String>();


    public void delete () {
        try {
            pst = Controller.connect.prepareStatement("DELETE FROM bms_bridge WHERE bridge_id = ?");
            int i = Integer.parseInt(res);
            pst.setInt(1, i);
            pst.executeUpdate();
            makeList();
            lll.setText("Запис видаленно ! ");
            controller.idResours.setDisable(true);
            controller.idSmeta.setDisable(true);
            controller.idSaveXLS.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
            //MessageBox.show();
        }
    }

    public void makeList () {
        try {
            ids1.clear();
            info1.clear();
            Insetr.bd = this;
            statement = Controller.connect.createStatement();
            rs = statement.executeQuery("SELECT bridge_id, bridge_name FROM bms_bridge");
            while (rs.next()) {
                ids1.add(rs.getInt("bridge_id"));
                info1.add(" " + rs.getString("bridge_name"));
            }
            controller.ids = ids1;
            controller.info = info1;

            controller.gp = new GridPane();
            controller.gp.setVisible(true);
            controller.sp = new ScrollPane();
            controller.sp.setContent(controller.gp);
            controller.sp.setPrefSize(190,579);
            controller.sp.setLayoutY(40);
            controller.sp.setLayoutX(0);
            controller.idTreePane.setDisable(false);
            controller.idTreePane.getChildren().addAll(controller.gp, controller.sp);
            int i = 0;
            while (i < ids1.size()) {
                controller.gp.getRowConstraints().add(makeRow());
                controller.gp.getColumnConstraints().add(makeCol());
                final Label lb = new Label();
                lb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                        try {
                            for (Node node : controller.gp.getChildren()) {
                                ((Label) node).setStyle("-fx-background-color: null;");
                            }
                            lb.setStyle("-fx-background-color: #0066CC;");
                            res = "";
                            String str = lb.getText();
                            boolean t = false;
                            int count = 8;
                            while (!t) {
                                if (str.charAt(count) == ' ')
                                    t = true;
                                else {
                                    res += str.charAt(count);
                                }
                                count++;
                            }
                            BridgePasport BP = makeBP(Integer.parseInt(res));
                            Specific SP = makeSP(Integer.parseInt(res));
                            Deck D = makeDeck(Integer.parseInt(res));
                            SuperStructure SS = makeSS(Integer.parseInt(res));
                            RegulatoryStructure RS = makeRS(Integer.parseInt(res));
                            Support S = makeSupp(Integer.parseInt(res));
                            controller.bigBridge = new Bridge(BP, SP, D, SS, RS, S);
                            controller.bigBridge.smeta = makeSmeta(Integer.parseInt(res));
                            RT(Integer.parseInt(res));
                            controller.idResours.setDisable(false);
                            controller.idSmeta.setDisable(false);
                            controller.idSaveXLS.setDisable(false);
                            lll.setText("Перегляд даних, об'єкт - " + controller.bigBridge.getBridgePasport().name);
                            controller.cBridge();
                        } catch (Exception ignored) {
                            lll.setText("Дані відсутні або пошкодженні!");
                        }
                    }
                });
                lb.setText("     " + (char)9658 + "  " + controller.ids.get(i) + " " + controller.info.get(i));
                controller.gp.add(lb, 0, controller.gp.getRowConstraints().size() - 1);
                i++;
            }

        } catch (Exception ignored) {}
    }

    private RowConstraints makeRow() {
        RowConstraints row = new RowConstraints();
        row.setMaxHeight(40);
        return row;
    }

    private ColumnConstraints makeCol() {
        ColumnConstraints col = new ColumnConstraints();
        col.setMaxWidth(200);
        return col;
    }

    public ArrayList<SMETA> makeSmeta (int i) {
        try {
            ArrayList<SMETA> sss = new ArrayList<SMETA>();
            pst = Controller.connect.prepareStatement("SELECT * FROM SMETA WHERE bridge_id = ?");
            pst.setInt(1, i);
            rs = pst.executeQuery();
            while (rs.next()) {
                int n = rs.getInt("smeta_Num");
                String s1 = rs.getString("Element");
                String s2 = rs.getString("smeta_name");
                double ds1 = rs.getDouble("smeta_unit");
                double ds2 = rs.getDouble("smeta_numder");
                double ds3 = rs.getDouble("smeta_numberCost");
                double ds4 = rs.getDouble("smeta_unitWork");
                double ds5 = rs.getDouble("smeta_sumNumCost");
                double ds6 = rs.getDouble("smeta_sumUnitWork");
                double ds7 = rs.getDouble("smeta_total");
                sss.add(new SMETA(n, s1, s2, ds1, ds2, ds3, ds4, ds5, ds6, ds7));
            }
            return sss;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public BridgePasport makeBP(int i) {
        try {
            pst = Controller.connect.prepareStatement("SELECT * FROM bms_bridge WHERE bridge_id = ?");
            pst.setInt(1, i);
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("bridge_name");
                String bar = rs.getString("bridge_barrier");
                String cat = rs.getString("bridge_category");
                int l= rs.getInt("bridge_lines");
                String loc = rs.getString("bridge_locality");
                double dis = rs.getDouble("bridge_distanceToLocality");
                int in = rs.getInt("bridge_input");
                String com = rs.getString("bridge_company");
                int lt = rs.getInt("bridge_lifetime");
                String path = rs.getString("bridge_path");

                return new BridgePasport(name, bar, cat, l, loc, dis,
                        in, com, lt, path);}
        } catch (Exception ignored) {}
        return null;
    }

    public Specific makeSP (int i) {
        try {

            pst = Controller.connect.prepareStatement("SELECT * FROM bms_specification WHERE bms_specification.bridge_id = ?");
            pst.setInt(1, i);
            rs = pst.executeQuery();
            while (rs.next()) {
                String s1 = rs.getString("specific_length");
                int i1 = rs.getInt("specific_raodWidth");
                int i2 = rs.getInt("specific_leftSidewalk");
                int i3 = rs.getInt("specific_rightSidewalk");
                int i4 = rs.getInt("specific_fence");
                int i5 = rs.getInt("specific_walkfence");
                double d1 = rs.getDouble("specific_angle");
                int i6 = rs.getInt("specific_load");
                int i7 = rs.getInt("specific_fload");
                String i8 = rs.getString("specific_path");
                return new Specific(s1, i1, i2, i3, i4, i5, d1, i6, i7, i8);}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void RT (int i) {
        try {
            pst = Controller.connect.prepareStatement("SELECT * FROM bms_rating WHERE bms_rating.bridge_id = ?");
            pst.setInt(1, i);
            rs = pst.executeQuery();
            while (rs.next()) {
                int i1 = rs.getInt("rating_CategoryStatus");
                double r1 = rs.getDouble("rating_wear");
                double r2 = rs.getDouble("rating_capacity");
                String s1 = rs.getString("rating_safety");
                String s2 = rs.getString("rating_longevity");
                double r3 = rs.getDouble("rating_Deck");
                double r4 = rs.getDouble("rating_Superstructure");
                double r5 = rs.getDouble("rating_RegulatoryStructure");
                double r6 = rs.getDouble("rating_Support");
                controller.bigBridge.rating.categoruStatus = i1;
                controller.bigBridge.rating.wear = r1;
                controller.bigBridge.rating.capacity = r2;
                controller.bigBridge.rating.safety = s1;
                controller.bigBridge.rating.longevity = s2;
                controller.bigBridge.rating.rDeck = r3;
                controller.bigBridge.rating.rSuperStruct = r4;
                controller.bigBridge.rating.rRegulStruct = r5;
                controller.bigBridge.rating.rSupport = r6;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Deck makeDeck (int i) {
        try {
            rs.close();
            Statement statement = Controller.connect.createStatement();
            PreparedStatement pst = Controller.connect.prepareStatement("SELECT rating_id FROM bms_rating WHERE bridge_id = ?");
            pst.setInt(1, i);
            ResultSet rs = pst.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("rating_id");
            }
            pst = Controller.connect.prepareStatement("SELECT * FROM bms_deck WHERE rating_id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                double d1 = rs.getDouble("deck_coating");
                double d2 = rs.getDouble("deck_drainage");
                double d3 = rs.getDouble("deck_expensionJoins");
                double d4 = rs.getDouble("deck_fence");
                double d5 = rs.getDouble("deck_barrier");
                return new Deck(d1, d2, d3, d4, d5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public SuperStructure makeSS (int i) {
        try {
            Statement statement = Controller.connect.createStatement();
            PreparedStatement pst = Controller.connect.prepareStatement("SELECT rating_id FROM bms_rating WHERE bridge_id = ?");
            pst.setInt(1, i);
            ResultSet rs = pst.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("rating_id");
            }
            pst = Controller.connect.prepareStatement("SELECT * FROM bms_superstructure WHERE rating_id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                double dd1 = rs.getDouble("ss_crack");
                double dd2 = rs.getDouble("ss_powerCrack");
                double dd3 = rs.getDouble("ss_corroisn");
                double dd4 = rs.getDouble("ss_damageRust");
                double dd5 = rs.getDouble("ss_disSeam");
                double dd6 = rs.getDouble("ss_defBeam");
                double dd7 = rs.getDouble("ss_resBem");
                double dd8 = rs.getDouble("ss_crackBolt");
                return new SuperStructure (dd1, dd2, dd3, dd4, dd5, dd6, dd7, dd8);
            }
        } catch (Exception ignored) {}
        return null;
    }

    public RegulatoryStructure makeRS (int i) {
        try {
            Statement statement = Controller.connect.createStatement();
            PreparedStatement pst = Controller.connect.prepareStatement("SELECT rating_id FROM bms_rating WHERE bridge_id = ?");
            pst.setInt(1, i);
            ResultSet rs = pst.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("rating_id");
            }
            pst = Controller.connect.prepareStatement("SELECT * FROM bms_regulatory_structure WHERE rating_id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                double d1d = rs.getDouble("Damage");
                double d2d = rs.getDouble("rs_track");
                double d3d = rs.getDouble("rs_speedLimit");
                double d4d = rs.getDouble("rs_cone");
                return new RegulatoryStructure(d1d, d2d, d3d, d4d);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public Support makeSupp (int i) {
        try {
            Statement statement = Controller.connect.createStatement();
            PreparedStatement pst = Controller.connect.prepareStatement("SELECT rating_id FROM bms_rating WHERE bridge_id = ?");
            pst.setInt(1, i);
            ResultSet rs = pst.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("rating_id");
            }
            pst = Controller.connect.prepareStatement("SELECT * FROM bms_support WHERE rating_id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                double r1 = rs.getDouble("support_header");
                double r2 = rs.getDouble("support_bodySupport");
                double r3 = rs.getDouble("support_foundation");
                return new Support (r1, r2, r3);
            }
        } catch (Exception ignored) { }
        return null;
    }

}
