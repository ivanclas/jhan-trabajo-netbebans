/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaConexion.Conexion;
import CapaDatos.Producto;
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
public class ProductoBD {
    private Conexion mysql= new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;
    
    
    public boolean registrarProducto(Producto p){
       sql = "INSERT INTO producto (pSerie,pDescripcion,pPrecio,pStock,pFechaVcto,imagen,idmarca,idcategoria)values (?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, p.getpSerie());
          pst.setString(2, p.getpDescripcion());
           pst.setDouble(3, p.getpPrecio());
            pst.setInt(4, p.getpStock());
             pst.setString(5, p.getpFechaVcto());
              pst.setString(6, p.getImagen());
               pst.setInt(7, p.getIdmarca());
                pst.setInt(8, p.getIdcategoria());

          pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e,"problemas al registrar ", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        return true;
    }
    
        public DefaultTableModel reportarProducto (){
            DefaultTableModel modelo;
            String [] titulos = {"SERIE","DESCRIPCION", "PRECIO","STOCK","FECHA_VCTO","MARCA","CATEGORIA"};
            String [] registros = new String [7];
            modelo = new DefaultTableModel(null,titulos);
            sql = "select pSerie,pDescripcion,pPrecio,pStock,pFechaVcto,maNombre AS marca,caNombre AS categoria   FROM producto AS p INNER JOIN marca AS m ON p.idmarca=m.idmarca INNER JOIN categoria AS c ON p.idcategoria=c.idcategoria";
            
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()){
                    registros[0] =rs.getString("pSerie");
                  registros[1] =rs.getString("pDescripcion");  
                    registros[2] =rs.getString("pPrecio");
                    registros[3] =rs.getString("pStock");
                    registros[4] =rs.getString("pFechaVcto");
                    registros[5] =rs.getString("marca");
                    registros[6] =rs.getString("categoria");
                    modelo.addRow(registros);
                }
                return modelo;
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e,"Error al reportar categoria...",JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        
    public boolean modificarProducto(Producto p){
        sql = "UPDATE producto SET pDescripcion=?,pPrecio=?,pStock=?,pFechaVcto=?,imagen=?,idmarca=?,idcategoria=?, where pSerie=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, p.getpSerie());
             pst.setString(2, p.getpDescripcion());
              pst.setDouble(3, p.getpPrecio());
              pst.setInt(4, p.getpStock()); 
              pst.setString(5, p.getpFechaVcto());
               pst.setString(6, p.getImagen());
              pst.setInt(7, p.getIdmarca()); 
              pst.setInt(8, p.getIdcategoria());
              
              pst.executeUpdate();
              
        }catch (Exception e){
           JOptionPane.showMessageDialog(null, e);
           
           return false;
        }
        return true;
    }

      public boolean eliminarProducto(int serie){
          sql = "delete FROM producto WHERE pSerie='"+serie+"'";
          
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


