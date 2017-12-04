/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.superquinto.sisprex.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ronaldo Chaves
 */
public class Conexao {
    //String url = "jdbc:jtds:sqlserver://localhost:18033/nyx";
    String url = "jdbc:mysql://localhost:3306/nyx";
    //String driver = "net.sourceforge.jtds.jdbc.Driver";
    String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String pass = "";
    Connection con;
    
    public Conexao() throws ClassNotFoundException{
            Class.forName(driver);
    }

    public Connection getConnection() throws SQLException {
            if (con == null) {
                con = DriverManager.getConnection(url, user, pass);
            }
        return con;
    }

    public void close() throws SQLException {
            if (con != null) {
                con.close();
                con = null;
            }
    }
}
