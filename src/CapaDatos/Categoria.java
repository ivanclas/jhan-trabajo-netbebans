/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

/**
 *
 * @author PAVILION
 */
public class Categoria {
  
    private int idcategoria;
    private String caNombre;
    private String caDescripcion;

    public Categoria() {
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCaNombre() {
        return caNombre;
    }

    public void setCaNombre(String caNombre) {
        this.caNombre = caNombre;
    }

    public String getCaDescripcion() {
        return caDescripcion;
    }

    public void setCaDescripcion(String caDescripcion) {
        this.caDescripcion = caDescripcion;
    }
    
}