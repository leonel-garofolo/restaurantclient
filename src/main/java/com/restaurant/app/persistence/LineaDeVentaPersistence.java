package com.restaurant.app.persistence;

import com.restaurant.app.model.LineaDeVenta;

public interface LineaDeVentaPersistence extends CommonPersistence<LineaDeVenta>{
    void cleanForVentaId(Long ventaId);
}
