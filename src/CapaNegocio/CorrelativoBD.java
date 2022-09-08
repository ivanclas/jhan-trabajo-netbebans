/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaConexion.Conexion;
import CapaDatos.Cliente;
import CapaDatos.Correlativo;
import CapaDatos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author PAVILION
 */
public class CorrelativoBD {
    private Conexion mysql= new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;
    
    
    public boolean registrarCorrelativo(Correlativo co){
       sql = "INSERT INTO correlativo (coCodigo,coNro,coSerie,coTipoDoc )values (0,?,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, co.getCoNro());
          pst.setString(2, co.getCoSerie());
           pst.setString(3, co.getCoTipoDoc());
        
               
          pst.executeUpdate();
          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e,"problemas al registrar ", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        return true;
    }
    
        public DefaultTableModel reportarCorrelativo (){
            DefaultTableModel modelo;
            String [] titulos = {"CODIGO","NRO", "SERIE","DOCUMENTO"};
            String [] registros = new String [4];
            modelo = new DefaultTableModel(null,titulos);
            sql = "select coCodigo,coNro,coSerie,coTipoDoc FROM correlativo";
            
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()){
                    registros[0] =rs.getString("coCodigo");
                    registros[1] =rs.getString("coNro");  
                    registros[2] =rs.getString("coSerie");
                    registros[3] =rs.getString("coTipoDoc");
                   
                   
                    modelo.addRow(registros);
                }
                return modelo;
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e,"Error al reportar correlativo...",JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        
    public boolean modificarCorrelativo(Correlativo co){
        sql = "UPDATE correlativo SET coNro=?,coSerie=?,coTipoDoc=? where coCodigo=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            
          
          pst.setString(1, co.getCoNro());
           pst.setString(2, co.getCoSerie());
            pst.setString(3, co.getCoTipoDoc());
             pst.setInt(4, co.getCoCodigo());
             
              
              pst.executeUpdate();
              
        }catch (Exception e){
           JOptionPane.showMessageDialog(null, e);
           
           return false;
        }
        return true;
    }

      public boolean eliminarCorrelativo(int codigo){
          sql = "delete FROM correlativo WHERE coCodigo='"+codigo+"'";
          
          try {
              Statement st = cn.createStatement();
              st.executeUpdate(sql);
              
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null,e,"Problemas al eliminar",JOptionPane.ERROR_MESSAGE);
              return false;
          }
          return true;
      }
      
      public boolean actualizarCorrelativo(Correlativo co){
          sql= "UPDATE correlativo SET coSerie=?    WHERE coTipoDoc=?";
          try {
               PreparedStatement pst = cn.prepareStatement(sql);
            

           pst.setString(1, co.getCoSerie());
            pst.setString(2, co.getCoTipoDoc());
           
              pst.executeUpdate();
          } catch (Exception e) {
              
              JOptionPane.showMessageDialog(null,e);
              e.printStackTrace();
              return false;
          }
          return true;
      }
        
        public List<Correlativo> sacarNro(String documento) {
            List<Correlativo> lista = new ArrayList<>();
            
            sql = "SELECT coCodigo,coNro,coSerie,coTipoDoc  FROM correlativo WHERE coTipoDoc='"+documento+"'";
            
            try {
                
              Statement st = cn.createStatement();
              ResultSet rs = st.executeQuery(sql);
              
              if (rs.next()){
                 Correlativo objeto_Correlativo = new Correlativo();
                  objeto_Correlativo.setCoNro(rs.getString(2));
                   objeto_Correlativo.setCoSerie(rs.getString(3));
                   objeto_Correlativo.setCoTipoDoc(rs.getString(4));
                    
                      
                      lista.add(objeto_Correlativo);
              }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el login");
            }
            return lista;
        }
}


 
    

