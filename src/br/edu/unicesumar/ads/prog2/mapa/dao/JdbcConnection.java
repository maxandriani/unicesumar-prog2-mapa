package br.edu.unicesumar.ads.prog2.mapa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author max.andriani
 */
public class JdbcConnection {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/mapa?useSSL=FALSE";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    private static Connection connection;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            } catch(SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JdbcConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return connection;
    }
}
