/*
 * Java bean class for entity "productos" 
 * Created on 2019-06-27 ( Date ISO 2019-06-27 - Time 21:04:02 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package com.restaurant.app.model;

import java.io.Serializable;

/**
 * Java bean for entity "productos"
 * 
 * @author Telosys Tools Generator
 *
 */
public class Productos implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String codigo;
	private String nombre;
	private String cuit;
	private Double precio;
	private Integer stock;
	private Long categoriaId;

	/**
	 * Default constructor
	 */
	public Productos() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
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





	public Double getPrecio() {
		return precio;
	}





	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}

}
