/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaConexion;

import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;

/**
 *
 * @author PAVILION
 */
public class Conexion {
    
    private static String clase = "com.mysql.cj.jdbc.Driver";
    
      private static String url = "jdbc:mysql://localhost:3307/business?servertimezone=UTC";
      private static String usuario = "root";
    private static String clave = "123456";

    public Conexion(){
        
    }
    public Connection conectar(){
         Connection link = null;
         
         
         try {
             Class.forName(clase);
            link=DriverManager.getConnection(url,usuario,clave);
        } catch (Exception e) {
             JOptionPane.showConfirmDialog(null, e);
            
        }
        return link;
    }
}
    


