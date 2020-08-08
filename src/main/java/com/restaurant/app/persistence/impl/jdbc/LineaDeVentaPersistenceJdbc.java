/*
 * Created on 2020-07-22 ( Date ISO 2020-07-22 - Time 23:32:14 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */

package com.restaurant.app.persistence.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Named;

import com.restaurant.app.model.LineaDeVenta;
import com.restaurant.app.persistence.LineaDeVentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

/**
 * LineaDeVenta persistence implementation 
 * 
 * @author Telosys
 *
 */
@Named("LineaDeVentaPersistence")
public class LineaDeVentaPersistenceJdbc extends GenericJdbcDAO<LineaDeVenta> implements LineaDeVentaPersistence {

	private final static String SQL_SELECT_ALL = 
		"select id, venta_id, precio, producto_id from linea_de_venta"; 

	private final static String SQL_SELECT = 
		"select id, venta_id, precio, producto_id from linea_de_venta where id = ? and venta_id = ?";

	private final static String SQL_INSERT = 
		"insert into linea_de_venta ( id, venta_id, precio, producto_id ) values ( ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update linea_de_venta set precio = ?, producto_id = ? where id = ? and venta_id = ?";

	private final static String SQL_DELETE = 
		"delete from linea_de_venta where id = ? and venta_id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from linea_de_venta";

	private final static String SQL_COUNT = 
		"select count(*) from linea_de_venta where id = ? and venta_id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public LineaDeVentaPersistenceJdbc() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(LineaDeVenta record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, LineaDeVenta lineaDeVenta) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, lineaDeVenta.getId() ) ; // "id" : java.lang.Integer
		setValue(ps, i++, lineaDeVenta.getVentaId() ) ; // "venta_id" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, LineaDeVenta lineaDeVenta) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, lineaDeVenta.getId() ) ; // "id" : java.lang.Integer
		setValue(ps, i++, lineaDeVenta.getVentaId() ) ; // "venta_id" : java.lang.Integer
		setValue(ps, i++, lineaDeVenta.getSubTotal() ) ; // "precio" : java.math.BigDecimal
		setValue(ps, i++, lineaDeVenta.getProductoId() ) ; // "producto_id" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, LineaDeVenta lineaDeVenta) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, lineaDeVenta.getSubTotal() ) ; // "precio" : java.math.BigDecimal
		setValue(ps, i++, lineaDeVenta.getProductoId() ) ; // "producto_id" : java.lang.Integer
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, lineaDeVenta.getId() ) ; // "id" : java.lang.Integer
		setValue(ps, i++, lineaDeVenta.getVentaId() ) ; // "venta_id" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @param ventaId;
	 * @return the new instance
	 */
	private LineaDeVenta newInstanceWithPrimaryKey( Long id, Long ventaId ) {
		LineaDeVenta lineaDeVenta = new LineaDeVenta();
		lineaDeVenta.setId( id );
		lineaDeVenta.setVentaId( ventaId );
		return lineaDeVenta ;
	}

	//----------------------------------------------------------------------
	@Override
	protected LineaDeVenta newInstance() {
		return new LineaDeVenta() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected LineaDeVenta populateBean(ResultSet rs, LineaDeVenta lineaDeVenta) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		lineaDeVenta.setId(rs.getLong("id")); // java.lang.Integer
		if ( rs.wasNull() ) { lineaDeVenta.setId(null); }; // not primitive number => keep null value if any
		lineaDeVenta.setVentaId(rs.getLong("venta_id")); // java.lang.Integer
		if ( rs.wasNull() ) { lineaDeVenta.setVentaId(null); }; // not primitive number => keep null value if any
		lineaDeVenta.setSubTotal(rs.getBigDecimal("precio")); // java.math.BigDecimal
		if ( rs.wasNull() ) { lineaDeVenta.setSubTotal(null); }; // not primitive number => keep null value if any
		lineaDeVenta.setProductoId(rs.getInt("producto_id")); // java.lang.Integer
		if ( rs.wasNull() ) { lineaDeVenta.setProductoId(null); }; // not primitive number => keep null value if any
		return lineaDeVenta ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public LineaDeVenta findById( Long id, Long ventaId ) {
		LineaDeVenta lineaDeVenta = newInstanceWithPrimaryKey( id, ventaId ) ;
		if ( super.doSelect(lineaDeVenta) ) {
			return lineaDeVenta ;
		}
		else {
			return null ; // Not found
		}
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public List<LineaDeVenta> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param lineaDeVenta
	 * @return true if found, false if not found
	 */
	//@Override
	public boolean load( LineaDeVenta lineaDeVenta ) {
		return super.doSelect(lineaDeVenta) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param lineaDeVenta
	 */
	public long insert(LineaDeVenta lineaDeVenta) {
		super.doInsert(lineaDeVenta);
		return 0L ;
	}

	@Override
	public LineaDeVenta save(LineaDeVenta lineaDeVenta) {
		if ( super.doExists(lineaDeVenta) ) {
			super.doUpdate(lineaDeVenta);
		}
		else {
			super.doInsert(lineaDeVenta);
		}
		return lineaDeVenta ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */

	public boolean deleteById( Long id, Long ventaId ) {
		LineaDeVenta lineaDeVenta = newInstanceWithPrimaryKey( id, ventaId ) ;
		int r = super.doDelete(lineaDeVenta);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @param ventaId;
	 * @return
	 */
	// @Override
	public boolean exists( Long id, Long ventaId ) {
		LineaDeVenta lineaDeVenta = newInstanceWithPrimaryKey( id, ventaId ) ;
		return super.doExists(lineaDeVenta);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param lineaDeVenta
	 * @return
	 */
	// @Override
	public boolean exists( LineaDeVenta lineaDeVenta ) {
		return super.doExists(lineaDeVenta);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
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

	@Override
	public LineaDeVenta findById(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(Long codigo) {
		// TODO Auto-generated method stub
		return false;
	}

}
