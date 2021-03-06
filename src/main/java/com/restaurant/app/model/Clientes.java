/*
 * Java bean class for entity "clientes" 
 * Created on 2019-06-27 ( Date ISO 2019-06-27 - Time 21:04:01 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package com.restaurant.app.model;

import java.io.Serializable;


/**
 * Java bean for entity "clientes"
 * 
 * @author Telosys Tools Generator
 *
 */
public class Clientes implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Long codigo;
    private String nombre;
    private String cuit;
   

    /**
     * Default constructor
     */
    public Clientes() {
        super();
    }
    
    public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getCuit() {
		return cuit;
	}



	public void setCuit(String cuit) {
		this.cuit = cuit;
	}



	@Override
    public String toString() { 
        StringBuffer sb = new StringBuffer();             
        return sb.toString(); 
    }
}
