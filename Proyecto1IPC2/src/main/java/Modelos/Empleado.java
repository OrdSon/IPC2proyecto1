/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author ordson
 */
public class Empleado {
    private int codigo;
    private String nombre;
    private int area;
    private String contraseña;
    private String dpi;
    private String telefono;
    private String direccion;
    private Date fecha_nacimiento;
    private String salario;
    private Date fecha_contratacion;

    public Empleado(int codigo, String nombre, int area, String contraseña, String dpi, 
            String telefono, String direccion, Date fecha_nacimiento, String salario, Date fecha_contratacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.area = area;
        this.contraseña = contraseña;
        this.dpi = dpi;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.salario = salario;
        this.fecha_contratacion = fecha_contratacion;
    }

    public Empleado(String nombre, int area, String contraseña, String dpi, 
            String telefono, String direccion, Date fecha_nacimiento, String salario, Date fecha_contratacion) {
        this.nombre = nombre;
        this.area = area;
        this.contraseña = contraseña;
        this.dpi = dpi;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.salario = salario;
        this.fecha_contratacion = fecha_contratacion;
    }

    public Empleado() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }
    
    
    
    
}
