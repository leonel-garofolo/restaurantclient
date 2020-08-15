package com.restaurant.app.persistence;

import com.restaurant.app.model.Venta;

import java.util.Date;
import java.util.List;

public interface VentaPersistence extends CommonPersistence<Venta>{

    List<Venta> findVentasForDate(Date fechaDesde, Date fechaHasta);

}
