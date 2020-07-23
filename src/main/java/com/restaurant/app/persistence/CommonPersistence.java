package com.restaurant.app.persistence;

import java.util.List;

public interface CommonPersistence<T> {
	List<T> findAll();
	T findById( Long codigo );
	boolean load( T entidad );
	T save(T entidad);
	boolean deleteById( Long codigo );
	long countAll();
}
