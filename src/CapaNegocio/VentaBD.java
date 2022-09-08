/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaConexion.Conexion;
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
public class VentaBD {
    
     private Conexion mysql= new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;
    
    
    public boolean registrarVenta(Venta v){
       sql = "INSERT INTO venta (idventa,vCorrelativo,documento,vFecha,vTotal,tipo,estado,cDni,uDni) values (0,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, v.getvCorrelativo());
          pst.setString(2, v.getDocumento());
           pst.setString(3, v.getvFecha());
            pst.setDouble(4, v.getvTotal());
             pst.setString(5, v.getTipo());
              pst.setString(6, v.getEstado());
               pst.setString(7, v.getcDni());
                pst.setString(8, v.getuDni());

          pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e,"problemas al registrar ", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        return true;
    }
    
        public DefaultTableModel reportarVenta (){
            DefaultTableModel modelo;
            String [] titulos = {"CODIGO","CORRELATIVO", "DOCUMENTO","FECHA","TOTAL","TIPO","ESTADO","CLIENTE","VENDEDOR"};
            String [] registros = new String [9];
            modelo = new DefaultTableModel(null,titulos);
            sql = "select idventa,vCorrelativo,documento,vFecha,vTotal,tipo,estado,CONCAT(cApellidos,' ',cNombre) AS cliente, CONCAT (uApellidos,' ',uNombre) AS vendedor FROM venta AS v INNER JOIN usuario AS u ON v.uDni=u.uDni INNER JOIN cliente AS c ON v.cDni=c.cDni";
            
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()){
                    registros[0] =rs.getString("idventa");
                  registros[1] =rs.getString("vCorrelativo");  
                    registros[2] =rs.getString("documento");
                    registros[3] =rs.getString("vFecha");
                    registros[4] =rs.getString("vTotal");
                    registros[5] =rs.getString("tipo");
                    registros[6] =rs.getString("estado");
                     registros[7] =rs.getString("cliente");
                       registros[8] =rs.getString("vendedor");


                    modelo.addRow(registros);
                }
                return modelo;
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e,"Error al reportar Ventas...",JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
}
