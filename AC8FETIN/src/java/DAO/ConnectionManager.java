/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isabela
 */
public class ConnectionManager {

    public static Connection con = null;

    public static Connection Conectar() throws SQLException {
        System.out.println("Conectando ao banco...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/sucatalivre", "root", "dinossauro");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
