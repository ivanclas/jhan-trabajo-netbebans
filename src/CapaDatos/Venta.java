/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

/**
 *
 * @author PAVILION
 */
public class Venta {
    
    
    private int idventa;
    private String vCorrelativo;
    private String documento;
    private String vFecha;
    private double vTotal;
    private String tipo;
    private String estado;
    private String cDni;
    private String uDni;

    public Venta() {
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public String getvCorrelativo() {
        return vCorrelativo;
    }

    public void setvCorrelativo(String vCorrelativo) {
        this.vCorrelativo = vCorrelativo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getvFecha() {
        return vFecha;
    }

    public void setvFecha(String vFecha) {
        this.vFecha = vFecha;
    }

    public double getvTotal() {
        return vTotal;
    }

    public void setvTotal(double vTotal) {
        this.vTotal = vTotal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getcDni() {
        return cDni;
    }

    public void setcDni(String cDni) {
        this.cDni = cDni;
    }

    public String getuDni() {
        return uDni;
    }

    public void setuDni(String uDni) {
        this.uDni = uDni;
    }
    
    
    
}
