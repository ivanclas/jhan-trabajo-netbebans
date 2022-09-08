/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaConexion.Conexion;
import CapaDatos.Categoria;
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
public class CategoriaBD {
    
    private Conexion mysql= new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;
    
    
    public boolean registrarCategoria(Categoria ca){
       sql = "INSERT INTO categoria (idcategoria,caNombre,caDescripcion)values (,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, ca.getCaNombre());
          pst.setString(2, ca.getCaDescripcion());

          pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e,"problemas al registrar ", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        return true;
    }
    
        public DefaultTableModel reportarCategoria (){
            DefaultTableModel modelo;
            String [] titulos = {"CODIGO","NOMBRE", "DESCRIPCION"};
            String [] registros = new String [3];
            modelo = new DefaultTableModel(null,titulos);
            sql = "select idcategoria,caNombre,caDescripcion FROM categoria";
            
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()){
                    registros[0] =rs.getString("idcategoria");
                  registros[1] =rs.getString("caNombre");  
                    registros[2] =rs.getString("caDescripcion");
                    
                    modelo.addRow(registros);
                }
                return modelo;
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e,"Error al reportar categoria...",JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        
    public boolean modificarCategoria(Categoria ca){
        sql = "UPDATE categoria SET caNombre=?,caDescripcion=?, where idcategoria=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, ca.getCaNombre());
             pst.setString(2, ca.getCaDescripcion());
              pst.setInt(3, ca.getIdcategoria());
              
              pst.executeUpdate();
        }catch (Exception e){
           JOptionPane.showMessageDialog(null, e);
           
           return false;
        }
        return true;
    }

      public boolean eliminarCategoria(int codigo){
          sql = "delete FROM categoria WHERE idcategoria='"+codigo+"'";
          
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
