package com.restaurant.app.model;

public class Usuarios {
	public final static String P_ADMINISTRADOR = "ADMINISTRADOR";
	public final static String P_SUPERVISOR = "SUPERVISOR";
	public final static String P_CAJA = "CAJA";
	
	
	private static String usuarioLogeado;
	private static String perfilLogeado;
	
	private long id;
	private long idPerfil;
	private String nombre;
	private String clave;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public static String getUsuarioLogeado() {
		return usuarioLogeado;
	}
	public static void setUsuarioLogeado(String usuarioLogeado) {
		Usuarios.usuarioLogeado = usuarioLogeado;
	}
	public static String getPerfilLogeado() {
		return perfilLogeado;
	}
	public static void setPerfilLogeado(String perfilLogeado) {
		Usuarios.perfilLogeado = perfilLogeado;
	}
}
