package bms.Resours;

import bms.Controller;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insetr {

    public static Controller controller;
    public static BD bd;
    public static Label lll;

    public void insert () throws SQLException {
        Controller.connect.setAutoCommit(false);
        PreparedStatement pst = Controller.connect.prepareStatement("INSERT INTO bms_bridge (bridge_name, bridge_barrier," +
                "bridge_category, bridge_lines, bridge_locality," +
                "bridge_distanceToLocality, bridge_input, bridge_company," +
                "bridge_lifetime, bridge_lastInspection, bridge_path) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pst.setString(1, controller.bigBridge.getBridgePasport().name);
        pst.setString(2, controller.bigBridge.getBridgePasport().barrier);
        pst.setString(3, controller.bigBridge.getBridgePasport().Category);
        pst.setInt(4, controller.bigBridge.getBridgePasport().lines);
        pst.setString(5, controller.bigBridge.getBridgePasport().locality);
        pst.setDouble(6, controller.bigBridge.getBridgePasport().distance);
        pst.setInt(7, controller.bigBridge.getBridgePasport().input);
        pst.setString(8, controller.bigBridge.getBridgePasport().company);
        pst.setInt(9, controller.bigBridge.getBridgePasport().lifetime);
        pst.setInt(10, 2014);
        pst.setString(11, Controller.categiryForGraf.getBridgePasport().imgPath);
        pst.execute();
        Controller.connect.commit();

        pst = Controller.connect.prepareStatement("SELECT bms_bridge.bridge_id FROM bms_bridge WHERE bms_bridge.bridge_name = ?");
        pst.setString(1, Controller.categiryForGraf.getBridgePasport().name);
        ResultSet rs = pst.executeQuery();
        int id = 0;
        while (rs.next()){
            if (rs.getInt("bridge_id") > id)
                id = rs.getInt("bridge_id");
        }
        pst = Controller.connect.prepareStatement("INSERT INTO bms_specification (bridge_id, specific_length, specific_raodWidth," +
                "specific_leftSidewalk, specific_rightSidewalk, specific_fence," +
                "specific_walkfence, specific_angle, specific_load, specific_fLoad," +
                "specific_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pst.setInt(1, id);
        pst.setString(2, Controller.categiryForGraf.getSpecific().length);
        pst.setInt(3, Controller.categiryForGraf.getSpecific().roadWidth);
        pst.setInt(4, Controller.categiryForGraf.getSpecific().leftWalk);
        pst.setInt(5, Controller.categiryForGraf.getSpecific().rightWalk);
        pst.setInt(6, Controller.categiryForGraf.getSpecific().fence);
        pst.setInt(7, Controller.categiryForGraf.getSpecific().walkFence);
        pst.setDouble(8, Controller.categiryForGraf.getSpecific().angle);
        pst.setInt(9, Controller.categiryForGraf.getSpecific().load);
        pst.setInt(10, Controller.categiryForGraf.getSpecific().fLoad);
        pst.setString(11, Controller.categiryForGraf.getSpecific().pathShema);
        pst.execute();
        Controller.connect.commit();

       if (Controller.categiryForGraf.smeta != null)
        for (int i = 0; i < Controller.categiryForGraf.smeta.size(); i++) {
            pst = Controller.connect.prepareStatement("SELECT bms_bridge.bridge_id FROM bms_bridge WHERE bms_bridge.bridge_name = ?");
            pst.setString(1, Controller.categiryForGraf.getBridgePasport().name);
            rs = pst.executeQuery();
            id = 0;
            while (rs.next()){
                if (rs.getInt("bridge_id") > id)
                    id = rs.getInt("bridge_id");
            }
            pst = Controller.connect.prepareStatement("INSERT INTO SMETA (bridge_id, smeta_Num, Element," +
                    "smeta_name, smeta_unit, smeta_numder," +
                    "smeta_numberCost, smeta_unitWork, smeta_sumNumCost, " +
                    "smeta_sumUnitWork, smeta_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, id);
            pst.setInt(2, Controller.categiryForGraf.smeta.get(i).Num);
            pst.setString(3, Controller.categiryForGraf.smeta.get(i).BridgeElement);
            pst.setString(4, Controller.categiryForGraf.smeta.get(i).Name);
            pst.setDouble(5, Controller.categiryForGraf.smeta.get(i).unit);
            pst.setDouble(6, Controller.categiryForGraf.smeta.get(i).number);
            pst.setDouble(7, Controller.categiryForGraf.smeta.get(i).numberCost);
            pst.setDouble(8, Controller.categiryForGraf.smeta.get(i).unitWork);
            pst.setDouble(9, Controller.categiryForGraf.smeta.get(i).sumNumbercost);
            pst.setDouble(10, Controller.categiryForGraf.smeta.get(i).sumUnitWork);
            pst.setDouble(11, Controller.categiryForGraf.smeta.get(i).total);
            pst.execute();
            Controller.connect.commit();
        }

        pst = Controller.connect.prepareStatement("SELECT bms_bridge.bridge_id FROM bms_bridge WHERE bms_bridge.bridge_name = ?");
        pst.setString(1, Controller.categiryForGraf.getBridgePasport().name);
        rs = pst.executeQuery();
        id = 0;
        while (rs.next()){
            if (rs.getInt("bridge_id") > id)
                id = rs.getInt("bridge_id");
        }
        pst = Controller.connect.prepareStatement("INSERT INTO  bms_rating (bridge_id, rating_CategoryStatus, rating_wear," +
                "rating_capacity, rating_safety, rating_longevity, rating_Deck, rating_Superstructure, rating_RegulatoryStructure," +
                "rating_Support) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pst.setInt(1, id);
        pst.setInt(2, Controller.categiryForGraf.getRating().categoruStatus);
        pst.setDouble(3, Controller.categiryForGraf.getRating().wear);
        pst.setDouble(4, Controller.categiryForGraf.getRating().capacity);
        pst.setString(5, Controller.categiryForGraf.getRating().safety);
        pst.setString(6, Controller.categiryForGraf.getRating().longevity);
        pst.setDouble(7, Controller.categiryForGraf.getRating().rDeck);
        pst.setDouble(8, Controller.categiryForGraf.getRating().rSuperStruct);
        pst.setDouble(9, Controller.categiryForGraf.getRating().rRegulStruct);
        pst.setDouble(10, Controller.categiryForGraf.getRating().rSupport);
        pst.execute();
        Controller.connect.commit();

        pst = Controller.connect.prepareStatement("SELECT bms_rating.rating_id FROM bms_rating WHERE bms_rating.rating_CategoryStatus = ?");
        pst.setInt(1, Controller.categiryForGraf.getRating().categoruStatus);
        rs = pst.executeQuery();
        id = 0;
        while (rs.next()){
            if (rs.getInt("rating_id") > id)
                id = rs.getInt("rating_id");
        }
        pst = Controller.connect.prepareStatement("INSERT INTO bms_deck (rating_id, deck_coating, deck_drainage," +
                "deck_expensionJoins, deck_fence, deck_barrier) VALUES (?, ?, ?, ?, ?, ?)");
        pst.setInt(1, id);
        pst.setDouble(2, Controller.categiryForGraf.getDeck().coating);
        pst.setDouble(3, Controller.categiryForGraf.getDeck().drainage);
        pst.setDouble(4, Controller.categiryForGraf.getDeck().expensionJoins);
        pst.setDouble(5, Controller.categiryForGraf.getDeck().fence);
        pst.setDouble(6, Controller.categiryForGraf.getDeck().sidewalk);
        pst.execute();
        Controller.connect.commit();

        pst = Controller.connect.prepareStatement("SELECT bms_rating.rating_id FROM bms_rating WHERE bms_rating.rating_CategoryStatus = ?");
        pst.setInt(1, Controller.categiryForGraf.getRating().categoruStatus);
        rs = pst.executeQuery();
        id = 0;
        while (rs.next()){
            if (rs.getInt("rating_id") > id)
                id = rs.getInt("rating_id");
        }
        pst = Controller.connect.prepareStatement("INSERT INTO bms_regulatory_structure (rating_id, Damage, rs_track," +
                "rs_speedLimit, rs_cone) VALUES (?, ?, ?, ?, ?)");
        pst.setInt(1, id);
        pst.setDouble(2, Controller.categiryForGraf.getRegulatoryStructure().areaDamege);
        pst.setDouble(3, Controller.categiryForGraf.getRegulatoryStructure().track);
        pst.setDouble(4, Controller.categiryForGraf.getRegulatoryStructure().speedLimit);
        pst.setDouble(5, Controller.categiryForGraf.getRegulatoryStructure().cone);
        pst.execute();
        Controller.connect.commit();

        pst = Controller.connect.prepareStatement("SELECT bms_rating.rating_id FROM bms_rating WHERE bms_rating.rating_CategoryStatus = ?");
        pst.setInt(1, Controller.categiryForGraf.getRating().categoruStatus);
        rs = pst.executeQuery();
        id = 0;
        while (rs.next()){
            if (rs.getInt("rating_id") > id)
                id = rs.getInt("rating_id");
        }
        pst = Controller.connect.prepareStatement("INSERT INTO bms_superstructure (rating_id, ss_crack, ss_powerCrack," +
                "ss_corroisn, ss_damageRust, ss_disSeam, ss_defBeam, ss_resBem, ss_crackBolt)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pst.setInt(1, id);
        pst.setDouble(2, Controller.categiryForGraf.getSuperStructure().crack);
        pst.setDouble(3, Controller.categiryForGraf.getSuperStructure().powerCrack);
        pst.setDouble(4, Controller.categiryForGraf.getSuperStructure().corrosion);
        pst.setDouble(5, Controller.categiryForGraf.getSuperStructure().damageRust);
        pst.setDouble(6, Controller.categiryForGraf.getSuperStructure().disSeam);
        pst.setDouble(7, Controller.categiryForGraf.getSuperStructure().defBeam);
        pst.setDouble(8, Controller.categiryForGraf.getSuperStructure().resBeam);
        pst.setDouble(9, Controller.categiryForGraf.getSuperStructure().crackBolt);
        pst.execute();
        Controller.connect.commit();

        pst = Controller.connect.prepareStatement("SELECT bms_rating.rating_id FROM bms_rating WHERE bms_rating.rating_CategoryStatus = ?");
        pst.setInt(1, Controller.categiryForGraf.getRating().categoruStatus);
        rs = pst.executeQuery();
        id = 0;
        while (rs.next()){
            if (rs.getInt("rating_id") > id)
                id = rs.getInt("rating_id");
        }
        pst = Controller.connect.prepareStatement("INSERT INTO bms_support (rating_id, support_header, support_bodySupport," +
                "support_foundation) VALUES (?, ?, ?, ?)");
        pst.setInt(1, id);
        pst.setDouble(2, Controller.categiryForGraf.getSupport().header);
        pst.setDouble(3, Controller.categiryForGraf.getSupport().bodySupport);
        pst.setDouble(4, Controller.categiryForGraf.getSupport().foundation);
        pst.execute();
        pst.close();
        Controller.connect.commit();
        lll.setText("Дані успішно збережено");
        bd.makeList();

    }
}