package com.mxinteligente.model.entidades;
// Generated 15/05/2011 12:59:36 PM by Hibernate Tools 3.2.1.GA



/**
 * Catingresos generated by hbm2java
 */
public class Catingresos  implements java.io.Serializable {


     private CatingresosId id;
     private Usuarios usuarios;
     private String nombre;

    public Catingresos() {
    }

	
    public Catingresos(CatingresosId id, Usuarios usuarios) {
        this.id = id;
        this.usuarios = usuarios;
    }
    public Catingresos(CatingresosId id, Usuarios usuarios, String nombre) {
       this.id = id;
       this.usuarios = usuarios;
       this.nombre = nombre;
    }
   
    public CatingresosId getId() {
        return this.id;
    }
    
    public void setId(CatingresosId id) {
        this.id = id;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




}


