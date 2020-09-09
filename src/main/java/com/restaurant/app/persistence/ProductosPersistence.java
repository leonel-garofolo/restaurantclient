package com.restaurant.app.persistence;

import com.restaurant.app.model.Productos;

public interface ProductosPersistence extends CommonPersistence<Productos>{

    void incrementStock(long productoId, int productoCount);

    void decrementStock(long productoId, int productoCount);
}
