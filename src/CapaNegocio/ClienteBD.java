/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaConexion.Conexion;
import CapaDatos.Cliente;
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
public class ClienteBD {
     private Conexion mysql= new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;
    
    
    public boolean registrarCliente(Cliente c){
       sql = "INSERT INTO cliente (cDni,cNombre,cApellidos,cTelefono)values (?,?,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, c.getcDni());
          pst.setString(2, c.getcNombre());
           pst.setString(3, c.getcApellidos());
            pst.setString(4, c.getcTelefono());
            

          pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e,"problemas al registrar ", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        return true;
    }
    
        public DefaultTableModel reportarCliente (){
            DefaultTableModel modelo;
            String [] titulos = {"DNI","NOMBRES", "APELLIDOS","TELEFONO"};
            String [] registros = new String [4];
            modelo = new DefaultTableModel(null,titulos);
            sql = "select cDni,cNombre,cApellidos,cTelefono FROM cliente";
            
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()){
                    registros[0] =rs.getString("cDni");
                  registros[1] =rs.getString("cNombre");  
                    registros[2] =rs.getString("cApellidos");
                    registros[3] =rs.getString("cTelefono");
                    
                }
                return modelo;
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e,"Error al reportar categoria...",JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        
    public boolean modificarCliente(Cliente c){
        sql = "UPDATE cliente SET cNombre=?,cApellidos=?,cTelefono=? where cDni=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, c.getcDni());
           pst.setString(2, c.getcNombre());
           pst.setString(3, c.getcApellidos());
           pst.setString(4, c.getcTelefono());
            
              
              pst.executeUpdate();
              
        }catch (Exception e){
           JOptionPane.showMessageDialog(null, e);
           
           return false;
        }
        return true;
    }

      public boolean eliminarCliente(int dni){
          sql = "delete FROM cliente WHERE cDni='"+dni+"'";
          
          try {
              Statement st = cn.createStatement();
              st.executeUpdate(sql);
              
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null,e,"Problemas al eliminar",JOptionPane.ERROR_MESSAGE);
              return false;
          }
          return true;
      }
      
        
}




