/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaConexion.Conexion;
import CapaDatos.Categoria;
import CapaDatos.Marca;
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
public class MarcaBD {
    
      private Conexion mysql= new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;
    
    
    public boolean registrarMarca(Marca m){
       sql = "INSERT INTO marca (idmarca,maNombre,maDescripcion)values (,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, m.getMaNombre());
          pst.setString(2, m.getMaDescripcion());

          pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e,"problemas al registrar ", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        return true;
    }
    
        public DefaultTableModel reportarMarca (){
            DefaultTableModel modelo;
            String [] titulos = {"CODIGO","NOMBRE", "DESCRIPCION"};
            String [] registros = new String [3];
            modelo = new DefaultTableModel(null,titulos);
            sql = "select idmarca,maNombre,maDescripcion FROM marca";
            
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()){
                    registros[0] =rs.getString("idmarca");
                  registros[1] =rs.getString("maNombre");  
                    registros[2] =rs.getString("maDescripcion");
                    
                    modelo.addRow(registros);
                }
                return modelo;
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e,"Error al reportar marca...",JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        
    public boolean modificarMarca(Marca m){
        sql = "UPDATE marca SET maNombre=?,maDescripcion=?, where idmarca=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, m.getMaNombre());
             pst.setString(2, m.getMaDescripcion());
              pst.setInt(3, m.getIdmarca());
              
              pst.executeUpdate();
        }catch (Exception e){
           JOptionPane.showMessageDialog(null, e);
           
           return false;
        }
        return true;
    }

      public boolean eliminarMarca(int codigo){
          sql = "delete FROM marca WHERE idmarca='"+codigo+"'";
          
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


