/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaConexion.Conexion;
import CapaDatos.DetalleVenta;
import CapaDatos.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author PAVILION
 */
public class DetalleVentaBD {
    private Conexion mysql= new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;
    
    
    public boolean registrarDetalleVenta(DetalleVenta dt){
       sql = "INSERT INTO detalleventa (idventa,pSerie,cant,precio,importe,fecha ) values (?,?,?,?,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt( 1, dt.getIdventa());
          pst.setString(2, dt.getpSerie());
           pst.setInt(3, dt.getCant());
            pst.setDouble(4, dt.getPrecio());
             pst.setDouble(5, dt.getImporte());
              pst.setString(6, dt.getFecha());
              
          pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e,"problemas al registrar ", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        return true;
    }
}
