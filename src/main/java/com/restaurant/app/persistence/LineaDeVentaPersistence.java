package com.restaurant.app.persistence;

import com.restaurant.app.model.LineaDeVenta;

import java.util.List;

public interface LineaDeVentaPersistence extends CommonPersistence<LineaDeVenta>{
    void cleanForVentaId(Long ventaId);
    List<LineaDeVenta> findLineaDeVentaForVentaId(Long ventaId);
}
