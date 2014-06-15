package bms;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by computer on 15.06.2014.
 */
public class Insetr {

    public static Controller controller;

    public void insert () throws SQLException {
        Controller.connect.setAutoCommit(false);
        String sql = "INSERT INTO bms_bridge (bridge_name, bridge_barrier," +
                    "bridge_category, bridge_lines, bridge_locality," +
                    "bridge_distanceToLocality, bridge_input, bridge_company," +
                    "bridge_lifetime, bridge_lastInspection, bridge_path) " +
                  "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = Controller.connect.prepareStatement(sql);
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

        sql = "INSERT INTO bms_specification (bridge_id) SELECT bridge_id " +
                "FROM bms_bridge WHERE bms_bridge.bridge_name = ?";
        pst = Controller.connect.prepareStatement(sql);
        pst.setString(1, Controller.categiryForGraf.getBridgePasport().name);
        pst.execute();
        sql = "UPDATE bms_specification SET specific_length = ?, specific_raodWidth = ?," +
                "specific_leftSidewalk = ?, specific_rightSidewalk = ?, specific_fence = ?," +
                "specific_walkfence = ?, specific_angle = ?, specific_load = ?, specific_fLoad = ?," +
                "specific_path = ? WHERE bridge_id = (SELECT bridge_id FROM bms_bridge WHERE bms_bridge.bridge_name = ?)";
        pst = Controller.connect.prepareStatement(sql);
        pst.setString(1, Controller.categiryForGraf.getSpecific().length);
        pst.setInt(2, Controller.categiryForGraf.getSpecific().roadWidth);
        pst.setInt(3, Controller.categiryForGraf.getSpecific().leftWalk);
        pst.setInt(4, Controller.categiryForGraf.getSpecific().rightWalk);
        pst.setInt(5, Controller.categiryForGraf.getSpecific().fence);
        pst.setInt(6, Controller.categiryForGraf.getSpecific().walkFence);
        pst.setDouble(7, Controller.categiryForGraf.getSpecific().angle);
        pst.setInt(8, Controller.categiryForGraf.getSpecific().load);
        pst.setInt(9, Controller.categiryForGraf.getSpecific().fLoad);
        pst.setString(10, Controller.categiryForGraf.getSpecific().pathShema);
        pst.setString(11, Controller.categiryForGraf.getBridgePasport().name);
        pst.execute();

        if (Controller.categiryForGraf.smeta != null)
        for (int i = 0; i < Controller.categiryForGraf.smeta.size(); i++) {
            sql = "INSERT INTO SMETA (bridge_id) SELECT bridge_id " +
                    "FROM bms_bridge WHERE bms_bridge.bridge_name = ?";
            pst = Controller.connect.prepareStatement(sql);
            pst.setString(1, Controller.categiryForGraf.getBridgePasport().name);
            pst.execute();
            sql = "UPDATE SMETA SET smeta_Num = ?, Element = ?," +
                    "smeta_name = ?, smeta_unit = ?, smeta_numder = ?," +
                    "smeta_numberCost = ?, smeta_unitWork = ?, smeta_sumNumCost = ?, " +
                    "smeta_sumUnitWork = ?, smeta_total = ? WHERE bridge_id = " +
                    "(SELECT bridge_id FROM bms_bridge WHERE bms_bridge.bridge_name = ?)";
            pst = Controller.connect.prepareStatement(sql);
            pst.setInt(1, Controller.categiryForGraf.smeta.get(i).Num);
            pst.setString(2, Controller.categiryForGraf.smeta.get(i).BridgeElement);
            pst.setString(3, Controller.categiryForGraf.smeta.get(i).Name);
            pst.setDouble(4, Controller.categiryForGraf.smeta.get(i).unit);
            pst.setDouble(5, Controller.categiryForGraf.smeta.get(i).number);
            pst.setDouble(6, Controller.categiryForGraf.smeta.get(i).numberCost);
            pst.setDouble(7, Controller.categiryForGraf.smeta.get(i).unitWork);
            pst.setDouble(8, Controller.categiryForGraf.smeta.get(i).sumNumbercost);
            pst.setDouble(9, Controller.categiryForGraf.smeta.get(i).sumUnitWork);
            pst.setDouble(10, Controller.categiryForGraf.smeta.get(i).total);
            pst.setString(11, Controller.categiryForGraf.getBridgePasport().name);
            pst.execute();
        }

        pst.close();
        Controller.connect.commit();


    }
}
