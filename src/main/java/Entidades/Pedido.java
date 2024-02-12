/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.Date;

/**
 *
 * @author ale
 */
public class Pedido {
    private int ID;
    private int ID_Cliente;
    private Date fecha;
    private double total;
    private String estado;
    private String nombre;
    private String mes;
    private int anio;
    

    public Pedido() {
    }

    public Pedido(int ID, String mes, int anio) {
        this.ID = ID;
        this.mes = mes;
        this.anio = anio;
    }

    public Pedido(int ID, Date fecha, double total, String estado, String nombre) {
        this.ID = ID;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.nombre = nombre;
    }

    public Pedido(int ID_Cliente, String nombre) {
        this.ID_Cliente = ID_Cliente;
        this.nombre = nombre;
    }

   
    public Pedido(int ID, int ID_Cliente, Date fecha, double total, String estado) {
        this.ID = ID;
        this.ID_Cliente = ID_Cliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public Pedido(int ID_Cliente, double total) {
        this.ID_Cliente = ID_Cliente;
        this.total = total;
    }

    public Pedido(int ID, int ID_Cliente, Date fecha, double total, String estado, String nombre) {
        this.ID = ID;
        this.ID_Cliente = ID_Cliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.nombre = nombre;
    }

    public Pedido(int ID, int ID_Cliente, double total) {
        this.ID = ID;
        this.ID_Cliente = ID_Cliente;
        this.total = total;
    }

    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    
}
