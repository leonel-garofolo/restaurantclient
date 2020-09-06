/*
 * Created on 2020-07-22 ( Date ISO 2020-07-22 - Time 23:32:14 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */

package com.restaurant.app.persistence.impl.jdbc;

import com.restaurant.app.model.Venta;
import com.restaurant.app.persistence.VentaPersistence;
import com.restaurant.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

import javax.inject.Named;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Venta persistence implementation 
 * 
 * @author Telosys
 *
 */
@Named("VentaPersistence")
public class VentaPersistenceJdbc extends GenericJdbcDAO<Venta> implements VentaPersistence {

	private final static String SQL_SELECT_ALL = 
		"select id, fecha, importe, forma_de_pago, vuelto, mesa, pagado from venta";

	private final static String SQL_SELECT = 
		"select id, fecha, importe, forma_de_pago, vuelto, mesa, pagado from venta where id = ?";

	private final static String SQL_INSERT = 
		"insert into venta ( fecha, importe, forma_de_pago, vuelto, mesa, pagado ) values ( ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update venta set fecha = ?, importe = ?, forma_de_pago = ?, vuelto = ?, mesa = ?, pagado = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from venta where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from venta";

	private final static String SQL_COUNT = 
		"select count(*) from venta where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public VentaPersistenceJdbc() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(Venta record, long value) {
		record.setId(value);
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Venta venta) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, venta.getId() ) ; // "id" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Venta venta) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, venta.getFecha() ) ; // "fecha" : java.util.Date
		setValue(ps, i++, venta.getImporte() ) ; // "importe" : java.math.BigDecimal
		setValue(ps, i++, venta.getFormaDePago() ) ; // "forma_de_pago" : java.lang.String
		setValue(ps, i++, venta.getVuelto() ) ; // "vuelto" : java.math.BigDecimal
		setValue(ps, i++, venta.getMesa() ) ;
		setValue(ps, i++, venta.isPagado() ) ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Venta venta) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, venta.getFecha() ) ; // "fecha" : java.util.Date
		setValue(ps, i++, venta.getImporte() ) ; // "importe" : java.math.BigDecimal
		setValue(ps, i++, venta.getFormaDePago() ) ; // "forma_de_pago" : java.lang.String
		setValue(ps, i++, venta.getVuelto() ) ; // "vuelto" : java.math.BigDecimal
		setValue(ps, i++, venta.getMesa() ) ;
		setValue(ps, i++, venta.isPagado() ) ;
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, venta.getId() ) ; // "id" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Venta newInstanceWithPrimaryKey( Long id ) {
		Venta venta = new Venta();
		venta.setId( id );
		return venta ;
	}

	//----------------------------------------------------------------------
	@Override
	protected Venta newInstance() {
		return new Venta() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected Venta populateBean(ResultSet rs, Venta venta) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		venta.setId(rs.getLong("id")); // java.lang.Integer
		if ( rs.wasNull() ) { venta.setId(null); }; // not primitive number => keep null value if any
		venta.setFecha(rs.getTimestamp("fecha")); // java.util.Date
		venta.setImporte(rs.getBigDecimal("importe")); // java.math.BigDecimal
		if ( rs.wasNull() ) { venta.setImporte(null); }; // not primitive number => keep null value if any
		venta.setFormaDePago(rs.getString("forma_de_pago")); // java.lang.String
		venta.setVuelto(rs.getBigDecimal("vuelto")); // java.math.BigDecimal
		if ( rs.wasNull() ) { venta.setVuelto(null); }; // not primitive number => keep null value if any
		venta.setMesa(rs.getString("mesa"));
		venta.setPagado(rs.getBoolean("pagado"));

		return venta ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public Venta findById( Long id ) {
		Venta venta = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(venta) ) {
			return venta ;
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
	public List<Venta> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param venta
	 * @return true if found, false if not found
	 */
	//@Override
	public boolean load( Venta venta ) {
		return super.doSelect(venta) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param venta
	 */
	public long insert(Venta venta) {
		Long key = super.doInsertAutoIncr(venta);
		return key.longValue();
	}
	
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public Venta save(Venta venta) {
		if ( super.doExists(venta) ) {
			super.doUpdate(venta);
		}
		else {
			Long key = super.doInsertAutoIncr(venta);
			venta.setId(key);
		}
		return venta ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( Long id ) {
		Venta venta = newInstanceWithPrimaryKey( id ) ;
		int r = super.doDelete(venta);
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
		Venta venta = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(venta);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param venta
	 * @return
	 */
	// @Override
	public boolean exists( Venta venta ) {
		return super.doExists(venta);
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
	public List<Venta> findVentasForDate(final Date fechaDesde, final Date fechaHasta) {
		List<Venta> ventas = new ArrayList<>();
		Connection conn= null;
		Statement st= null;
		try{
			conn = getConnection();
			SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from venta where pagado = 1 and fecha >= '" + dFormat.format(fechaDesde) + "' and fecha < '" + dFormat.format(fechaHasta) + "'");
			while(rs.next()) {
				Venta v = new Venta();
				populateBean(rs, v);
				ventas.add(v);
			}
			rs.close();
		}catch (SQLException e){
			e.printStackTrace();
			closeConnection(conn, st);
		}
		return ventas;
	}

    @Override
    public void cancel(long ventaId) {
		Connection conn= null;
		Statement st= null;
		try{
			conn = getConnection();
			st = conn.createStatement();
			st.execute("update venta set cancel = current_date() where id = " + ventaId);
		}catch (SQLException e){
			e.printStackTrace();
			closeConnection(conn, st);
		}
    }
}
