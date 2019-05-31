/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author IAN
 */
public class db {

    Connection conn = null;

    public static Connection Db() {
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie?zeroDateTimeBehavior=convertToNull", "root", "");
//            JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

}
