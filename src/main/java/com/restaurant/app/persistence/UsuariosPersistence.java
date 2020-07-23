package com.restaurant.app.persistence;

import java.util.List;

import com.restaurant.app.model.Usuarios;

public interface UsuariosPersistence extends CommonPersistence<Usuarios>{
	
	List<Usuarios> loadForPerfil(long idPerfil);
	Usuarios loadForNombre(String nombre );
}
