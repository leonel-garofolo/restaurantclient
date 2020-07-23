/*
 * Created on 2019-06-27 ( Date ISO 2019-06-27 - Time 20:59:47 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */

package com.restaurant.app.persistence.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.restaurant.app.model.Usuarios;
import com.restaurant.app.persistence.UsuariosPersistence;
import com.restaurant.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

/**
 * Usuarios persistence implementation 
 * 
 * @author Telosys
 *
 */
@Named("UsuariosPersistence")
public class UsuariosPersistenceJdbc extends GenericJdbcDAO<Usuarios> implements UsuariosPersistence{

	private final static String SQL_SELECT_ALL = 
		"select id, nombre, clave, id_perfil from usuarios"; 

	private final static String SQL_SELECT = 
		"select id, nombre, clave, id_perfil from usuarios where id = ?";
	
	private final static String SQL_SELECT_FOR_NAME = 
			"select id, nombre, clave, id_perfil from usuarios where nombre = ?";
	
	private final static String SQL_SELECT_WITH_FILTER_PERFIL = 
			"select id, nombre, clave, id_perfil from usuarios where id_perfil = ?";

	private final static String SQL_INSERT = 
		"insert into usuarios ( nombre, clave, id_perfil ) values ( ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update usuarios set nombre = ?, clave = ?, id_perfil = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from usuarios where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from usuarios";

	private final static String SQL_COUNT = 
		"select count(*) from usuarios where id = ?";
	
	
    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public UsuariosPersistenceJdbc() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(Usuarios record, long value) {
		record.setId(value);
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Usuarios Usuarios) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, Usuarios.getId() ) ; // "id" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Usuarios usuarios) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, usuarios.getNombre() ) ; // "nombre" : java.lang.String
		setValue(ps, i++, usuarios.getClave() ) ; // "nombre" : java.lang.String
		setValue(ps, i++, usuarios.getIdPerfil() ) ; // "nombre" : java.lang.String	
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Usuarios Usuarios) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, Usuarios.getNombre() ) ; // "nombre" : java.lang.String
		setValue(ps, i++, Usuarios.getClave() ) ; // "nombre" : java.lang.String
		setValue(ps, i++, Usuarios.getIdPerfil() ) ; 
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, Usuarios.getId() ) ; // "id" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Usuarios newInstanceWithPrimaryKey( Long id ) {
		Usuarios Usuarios = new Usuarios();
		Usuarios.setId( id );
		return Usuarios ;
	}

	//----------------------------------------------------------------------
	@Override
	protected Usuarios newInstance() {
		return new Usuarios() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected Usuarios populateBean(ResultSet rs, Usuarios usuarios) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		usuarios.setId(rs.getLong("id")); // java.lang.Integer
		usuarios.setNombre(rs.getString("nombre")); // java.lang.String				
		usuarios.setClave(rs.getString("clave")); // java.lang.String
		usuarios.setIdPerfil(rs.getInt("id_perfil")); // java.lang.Integer
		return usuarios ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public Usuarios findById( Long id ) {
		Usuarios Usuarios = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(Usuarios) ) {
			return Usuarios ;
		}
		else {
			return null ; // Not found
		}
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<Usuarios> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param Usuarios
	 * @return true if found, false if not found
	 */
	//@Override
	public boolean load( Usuarios Usuarios ) {
		return super.doSelect(Usuarios) ;
	}
	
	public Usuarios loadForNombre(String nombre ) {
		Usuarios bean = null;
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_SELECT_FOR_NAME);			
			setValue(ps, 1, nombre);
			//--- Execute SQL SELECT
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				bean = newInstance();
				populateBean(rs, bean);				
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
		return bean;
	}
	
	public List<Usuarios> loadForPerfil(long idPerfil ) {
		List<Usuarios> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_SELECT_WITH_FILTER_PERFIL);			
			setValue(ps, 1, idPerfil);
			//--- Execute SQL SELECT
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				Usuarios bean = newInstance();
				populateBean(rs, bean);
				list.add(bean);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
		return list;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param Usuarios
	 */
	public long insert(Usuarios Usuarios) {
		Long key = super.doInsertAutoIncr(Usuarios);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public Usuarios create(Usuarios Usuarios) {
		insert(Usuarios);
		return Usuarios ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public boolean update(Usuarios Usuarios) {
		int r = super.doUpdate(Usuarios);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public Usuarios save(Usuarios Usuarios) {
		if ( super.doExists(Usuarios) ) {
			super.doUpdate(Usuarios);
		}
		else {
			super.doInsert(Usuarios);
		}
		return Usuarios ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public boolean deleteById( Long id ) {
		Usuarios Usuarios = newInstanceWithPrimaryKey( id ) ;
		int r = super.doDelete(Usuarios);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public boolean delete( Usuarios Usuarios ) {
		int r = super.doDelete(Usuarios);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	// @Override
	public boolean exists( Long id ) {
		Usuarios Usuarios = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(Usuarios);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param Usuarios
	 * @return
	 */
	// @Override
	public boolean exists( Usuarios usuarios ) {
		return super.doExists(usuarios);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long countAll() {
		return super.doCountAll();
	}

    //----------------------------------------------------------------------
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlSelectAll() {
		return SQL_SELECT_ALL;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlUpdate() {
		return SQL_UPDATE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlDelete() {
		return SQL_DELETE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCount() {
		return SQL_COUNT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCountAll() {
		return SQL_COUNT_ALL ;
	}

}
